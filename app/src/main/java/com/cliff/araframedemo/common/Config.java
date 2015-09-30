package com.cliff.araframedemo.common;

import com.cliff.hsj.common.AppConfig;

/**
 * Created by jack on 2015/9/28.
 */
public class Config extends AppConfig {

    /**
     * 请求访问话题的分类的标识
     */
    public static final String TOPIC_TOP_ALL = "all";
    public static final String TOPIC_TOP_SHARE = "share";
    public static final String TOPIC_TOP_ASK = "ask";
    public static final String TOPIC_TOP_JOB = "job";
    public static final String TOPIC_TOP_BB = "bb";

    /**
     * 请求跟用户相关的时候加上
     */
    public static final String TOPIC_ID = "topic_id";

    /**
     * 话题的实体类标识
     */
    public static final String TOPIC_ENTITY = "topic_entity";

    /**
     * 要查询的话题的类型的标识，用来传值
     */
    public static final String QUERY_TOPIC_TYPE = "QUERY_TOPIC_TYPE";

    /**
     * 话题详情的类型，
     */
    public static final int TOPIC_TYPE_TITLE = 1000;
    public static final int TOPIC_TYPE_CREATE_DATE = 1001;
    public static final int TOPIC_TYPE_IMG_URL = 1002;
    public static final int TOPIC_TYPE_CONTENT = 1003;
    public static final int TOPIC_TYPE_CONTENT_LINK = 1004;
    public static final int TOPIC_TYPE_AUTHOR = 1005;


    /**
     * 查看大图
     */
    public static final String IMG_BIG_SHOW = "IMG_BIG_SHOW";

}
