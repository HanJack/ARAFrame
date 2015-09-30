package com.cliff.hsj.common;

/**
 * @Description 初始设置类.
 */
public class AppConfig {

    /**
     * UI设计的基准宽度.
     */
    public static int UI_WIDTH = 720;

    /**
     * UI设计的基准高度.
     */
    public static int UI_HEIGHT = 1080;

    /**
     * 默认下载文件地址.
     */
    public static String DOWNLOAD_ROOT_DIR = "download";

    /**
     * 默认下载图片文件地址.
     */
    public static String DOWNLOAD_IMAGE_DIR = "images";

    /**
     * 默认下载文件地址.
     */
    public static String DOWNLOAD_FILE_DIR = "files";

    /**
     * APP缓存目录.
     */
    public static String CACHE_DIR = "cache";

    /**
     * UI设计的密度.
     */
    public static int UI_DENSITY = 2;

    /**
     * 默认缓存超时时间设置，毫秒.
     */
    public static int IMAGE_CACHE_EXPIRES_TIME = 60 * 10000;

    /**
     * 磁盘缓存大小 单位10M.
     */
    public static int MAX_DISK_USAGE_INBYTES = 10 * 1024 * 1024;


}
