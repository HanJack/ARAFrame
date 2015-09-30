package com.cliff.araframedemo.ui.welcome;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.cliff.araframedemo.R;
import com.cliff.araframedemo.ui.LoginActivity_;
import com.cliff.araframedemo.ui.main.activity.MainActivity_;
import com.cliff.hsj.ui.BaseActivity;
import com.cliff.hsj.utils.PreferencesUtils;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Fullscreen;

/**
 */
@Fullscreen
@EActivity(R.layout.activity_welcome_main)
public class Welcome extends BaseActivity {
    private static final int TIME = 1500;
    private static final int GO_HOME = 1000;
    private static final int GO_LOGIN = 1001;
    private static final int GO_GUIDE = 1002;

    private static final String KEY_IS_FIRS_TIN = "is_first_in";

    private Jump_Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        handler = new Jump_Handler();

        jumpAty();
    }

    /**
     */
    private void jumpAty() {

        if (PreferencesUtils.getBoolean(getApplicationContext(), KEY_IS_FIRS_TIN, true)) {
            handler.sendEmptyMessageDelayed(GO_GUIDE, TIME);
        } else {
            handler.sendEmptyMessageDelayed(GO_LOGIN, TIME);
        }

        PreferencesUtils.putBoolean(getApplication(), KEY_IS_FIRS_TIN, false);

    }

    /**
     */
    private void goHome() {
        startActivity(new Intent(Welcome.this, MainActivity_.class));
        finish();
    }

    /**
     */
    private void Login() {
        startActivity(new Intent(Welcome.this, LoginActivity_.class));
        finish();
    }

    /**
     */
    private void goGuide() {
        Intent i = new Intent(Welcome.this, Welcome_Guide.class);
        startActivity(i);
        finish();
    }

    /**
     */
    class Jump_Handler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case GO_HOME:
                    goHome();
                    break;
                case GO_LOGIN:
                    Login();
                    break;

                case GO_GUIDE:
                    goGuide();
                    break;

            }
        }
    }


}
