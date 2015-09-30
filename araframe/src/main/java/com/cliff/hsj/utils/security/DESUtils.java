package com.cliff.hsj.utils.security;

import android.annotation.SuppressLint;

import com.cliff.hsj.utils.LogUtils;

import java.security.Key;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

/**
 * @Description 数据加密工具类<br>
 * DES全称为Data Encryption Standard，<br>
 * 即数据加密标准，是一种使用密钥加密的块算法，1976年被美国联邦政府的国家标准局确定为联邦资料处理标准
 * （FIPS），随后在国际上广泛流传开来。<br>
 * DES算法的入口参数有三个:Key、Data、Mode。<br>
 * 其中Key为7个字节共56位,是DES算法的工作密钥;<br>
 * Data为8个字节64位,是要被加密或被解密的数据;<br>
 * Mode为DES的工作方式,有两种:加密或解密。
 */
@SuppressLint("TrulyRandom")
public class DESUtils {
    /**
     * Log 输出标签
     */
    public static String TAG = DESUtils.class.getName();

    private static final String ALGORITHM = "DES";

    /**
     * <p>
     * 生成随机密钥
     * </p>
     *
     * @return
     * @throws Exception
     */
    public static String getSecretKey() throws Exception {
        String randomSecretKey = getSecretKey(null);
        LogUtils.d(TAG, "生成随机密钥>>" + randomSecretKey);
        return randomSecretKey;
    }

    /**
     * <p>
     * 生成密钥
     * </p>
     *
     * @param seed 密钥种子
     * @return
     * @throws Exception
     */
    public static String getSecretKey(String seed) throws Exception {
        SecureRandom secureRandom;
        if (seed != null && !"".equals(seed))
            secureRandom = new SecureRandom(seed.getBytes());
        else
            secureRandom = new SecureRandom();
        KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
        keyGenerator.init(secureRandom);
        SecretKey secretKey = keyGenerator.generateKey();
        String seedSecretKey = Base64Utils.encode(secretKey.getEncoded());
        LogUtils.d(TAG, "生成指定秘钥种子的密钥>>" + seedSecretKey);
        return seedSecretKey;
    }

    /**
     * 根据键值进行加密
     *
     * @param key
     * @param data
     * @return
     */
    public static String encode(String key, String data) {
        if (data == null)
            return null;
        try {
            DESKeySpec dks = new DESKeySpec(key.getBytes());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            // key的长度不能够小于8位字节
            Key secretKey = keyFactory.generateSecret(dks);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            IvParameterSpec iv = new IvParameterSpec("12345678".getBytes());
            AlgorithmParameterSpec paramSpec = iv;
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, paramSpec);
            byte[] bytes = cipher.doFinal(data.getBytes());
            LogUtils.d(TAG, "根据键值进行加密结果>>>>" + byte2hex(bytes));
            return byte2hex(bytes);
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.e(TAG, "根据键值进行加密失败>>>>" + e.getMessage());
            return data;
        }
    }

    /**
     * 根据键值进行解密
     *
     * @param key
     * @param data
     * @return
     */
    public static String decode(String key, String data) {
        if (data == null)
            return null;
        try {
            DESKeySpec dks = new DESKeySpec(key.getBytes());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            Key secretKey = keyFactory.generateSecret(dks);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            IvParameterSpec iv = new IvParameterSpec("12345678".getBytes());
            AlgorithmParameterSpec paramSpec = iv;
            cipher.init(Cipher.DECRYPT_MODE, secretKey, paramSpec);
            String decodeResult = new String(cipher.doFinal(hex2byte(data
                    .getBytes())));
            LogUtils.d(TAG, "根据键值进行解密,结果>>>>" + decodeResult);
            return decodeResult;
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.e(TAG, "根据键值进行解密,失败>>>>" + e.getMessage());
            return data;
        }
    }

    /**
     * 将数组转换成16进制
     *
     * @param b
     * @return
     */
    private static String byte2hex(byte[] b) {
        StringBuilder hs = new StringBuilder();
        String stmp;
        for (int n = 0; b != null && n < b.length; n++) {
            stmp = Integer.toHexString(b[n] & 0XFF);
            if (stmp.length() == 1)
                hs.append('0');
            hs.append(stmp);
        }
        return hs.toString().toUpperCase();
    }

    /**
     * 16进制转换成数组
     *
     * @param b
     * @return
     */
    private static byte[] hex2byte(byte[] b) {
        if ((b.length % 2) != 0)
            throw new IllegalArgumentException();
        byte[] b2 = new byte[b.length / 2];
        for (int n = 0; n < b.length; n += 2) {
            String item = new String(b, n, 2);
            b2[n / 2] = (byte) Integer.parseInt(item, 16);
        }
        return b2;
    }

    /**
     * Description 根据键值进行加密
     *
     * @param data
     * @param key  加密键byte数组
     * @return
     * @throws Exception
     */
    private static byte[] encrypt(byte[] data, byte[] key) throws Exception {
        // 生成一个可信任的随机数源
        SecureRandom sr = new SecureRandom();

        // 从原始密钥数据创建DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);

        // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
        SecretKey securekey = keyFactory.generateSecret(dks);

        // Cipher对象实际完成加密操作
        Cipher cipher = Cipher.getInstance(ALGORITHM);

        // 用密钥初始化Cipher对象
        cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);

        return cipher.doFinal(data);
    }

    /**
     * Description 根据键值进行解密
     *
     * @param data
     * @param key  加密键byte数组
     * @return
     * @throws Exception
     */
    private static byte[] decrypt(byte[] data, byte[] key) throws Exception {
        // 生成一个可信任的随机数源
        SecureRandom sr = new SecureRandom();

        // 从原始密钥数据创建DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);

        // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
        SecretKey securekey = keyFactory.generateSecret(dks);

        // Cipher对象实际完成解密操作
        Cipher cipher = Cipher.getInstance(ALGORITHM);

        // 用密钥初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, securekey, sr);

        return cipher.doFinal(data);
    }
}
