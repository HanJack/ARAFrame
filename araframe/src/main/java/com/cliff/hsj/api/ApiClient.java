package com.cliff.hsj.api;

import android.util.Log;

import com.cliff.hsj.api.common.GsonConverterFactory;
import com.cliff.hsj.exception.HttpException;

import java.io.IOException;

import retrofit.Call;
import retrofit.Converter;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by jack on 2015/9/24.
 */
public class ApiClient {

    private static String ROOT_URL = null;

    private static ApiClient me;

    private Retrofit retrofit;

    private ApiClient(String root_url, Converter.Factory factory) {
        this.ROOT_URL = root_url;

        retrofit = new Retrofit.Builder()
                .baseUrl(root_url)
                .addConverterFactory(factory)
                .build();
    }

    /**
     * 初始化api
     *
     * @param root_url 服务器根路径
     */
    public static void initialize(String root_url) {
        if (me == null)
            me = new ApiClient(root_url, GsonConverterFactory.create());
    }

    /**
     * 初始化api
     *
     * @param root_url 服务器根路径
     * @param factory  接口数据解析工厂
     */
    public static void initialize(String root_url, Converter.Factory factory) {
        if (me == null)
            me = new ApiClient(root_url, factory);
    }

    public static ApiClient instance() {
        if (me == null)
            Log.e("ApiClient", "ApiClient not initialize!");
        return me;
    }

    /**
     * 加载服务接口
     *
     * @param c   要初始化的类
     * @param <T>
     * @return 初始化对象
     */
    public <T> T loadService(Class<T> c) {
        return retrofit.create(c);
    }

    /**
     * 指定根路径的接口
     *
     * @param c   要初始化的类
     * @param <T>
     * @return 初始化对象
     */
    public <T> T loadService(String root_url, Class<T> c) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(root_url).addConverterFactory(GsonConverterFactory.create()).build();
        return retrofit.create(c);
    }

    /**
     * 获取文件上传工具类
     *
     * @return
     */
    public OkHttpClientManager.UploadDelegate getUploadDelegate() {
        return OkHttpClientManager.getUploadDelegate();
    }

    /**
     * 获取文件下载工具类
     *
     * @return
     */
    public OkHttpClientManager.DownloadDelegate getDownloadDelegate() {
        return OkHttpClientManager.getDownloadDelegate();
    }

    /**
     * 分析Retrofit返回结果
     *
     * @param result
     * @param <T>
     * @return
     * @throws HttpException
     */
    public <T> T getData(Call<T> result) throws HttpException {
        if (result == null)
            throw new HttpException("result is NULL");
        try {
            Response<T> response = result.execute();
            if (response.isSuccess())
                return response.body();
            else
                throw new HttpException(response.message());
        } catch (IOException e) {
            throw new HttpException("response Io exception");
        }
    }
}
