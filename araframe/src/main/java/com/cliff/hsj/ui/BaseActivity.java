package com.cliff.hsj.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.cliff.hsj.utils.LogUtils;


/**
 */
public class BaseActivity extends AppCompatActivity {
    /**
     * 日志的标记
     */
    public final String TAG = getClass().getSimpleName();

    /**
     * Activity启动的管理堆栈
     */
    private ActivityTack mTack = ActivityTack.getInstanse();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTack.addActivity(this);
        LogUtils.i(TAG, TAG + "-----onCreate-----");
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        LogUtils.i(TAG, TAG + "-----setContentView-----");

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LogUtils.i(TAG, TAG + "-----onRestart-----");
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtils.i(TAG, TAG + "-----onStart-----");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtils.i(TAG, TAG + "-----onResume-----");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtils.i(TAG, TAG + "-----onStop-----");
    }

    @Override
    public void finish() {
        super.finish();
        mTack.removeActivity(this);
        LogUtils.i(TAG, TAG + "-----finish-----");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtils.i(TAG, TAG + "-----onDestroy-----");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        LogUtils.i(TAG, TAG + "-----onSaveInstanceState-----");
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        LogUtils.i(TAG, TAG + "-----onRestoreInstanceState-----");
        super.onRestoreInstanceState(savedInstanceState);
    }

    /**
     * 退出程序
     */
    protected void exitApp() {
        mTack.exitApp();
    }
}
