package com.cliff.araframedemo.ui.welcome;

import android.content.Intent;
import android.os.Bundle;

import com.cliff.araframedemo.ui.LoginActivity_;
import com.cliff.hsj.ui.AppIntro;


/**
 * Created by LOVE on 2015/6/10 010.
 */
public class Welcome_Guide extends AppIntro {
    //    @Override
    public void init(Bundle savedInstanceState) {
        addSlide(new Slide_First(), getApplicationContext());
        addSlide(new Slide_Second(), getApplicationContext());
        addSlide(new Slide_Third(), getApplicationContext());
        addSlide(new Slide_Fourth(), getApplicationContext());
    }

    @Override
    public void onSkipPressed() {

        startActivity(new Intent(Welcome_Guide.this, LoginActivity_.class));
        finish();

    }

    @Override
    public void onDonePressed() {
        startActivity(new Intent(Welcome_Guide.this, LoginActivity_.class));
        finish();
    }


}
