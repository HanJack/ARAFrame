package com.cliff.araframedemo.ui.main.view;

/**
 * @Description 自定义的话题列表数据视图，需要显示话题数据列表的需要实现这个接口,这里是定义好界面试图的业务逻辑框架
 * @File setResult.java
 * @Package com.martin.ionichinabystudio.ui.view
 * @Date 2015/7/36:48
 * @Author Donghongyu 1358506549@qq.com
 * @Version v1.0.0
 */
public interface BaseView {
    void showLoading();

    void hideLoading();

    void showError(String msg);

    void setResult(Object result);
}
