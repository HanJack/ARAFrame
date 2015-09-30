package com.cliff.hsj;

import android.app.Application;

import com.activeandroid.ActiveAndroid;
import com.cliff.hsj.api.ApiClient;

/**
 * Created by jack on 2015/9/25.
 */
public abstract class BaseApplication extends Application {


    private static BaseApplication instance;

    public static BaseApplication instance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        //初始化数据库操作库
        ActiveAndroid.initialize(getBaseContext());
        //初始化api服务
        ApiClient.initialize(getRootUrl());
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        //释放数据库操作
        ActiveAndroid.dispose();
    }

    /**
     * 获取接口服务器根路径
     *
     * @return
     */
    public abstract String getRootUrl();
}
