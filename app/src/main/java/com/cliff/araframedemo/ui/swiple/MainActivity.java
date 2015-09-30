package com.cliff.araframedemo.ui.swiple;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.cliff.araframedemo.R;
import com.cliff.hsj.ui.BaseActivity;
import com.cliff.hsj.ui.SwipeBackActivity;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;

@EActivity(R.layout.swip_activity_main)
public class MainActivity extends SwipeBackActivity implements View.OnClickListener {


    @Click({R.id.btn_common, R.id.btn_ListView, R.id.btn_demo,
            R.id.btn_viewpager})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_common:
                skipActivity(CommonActivity_.class);
                break;
            case R.id.btn_ListView:
                skipActivity(ListViewActivity_.class);
                break;
            case R.id.btn_demo:
//                skipActivity(DemoActivity_.class);
                break;
            case R.id.btn_viewpager:
                skipActivity(ViewPagerActivity_.class);
                break;
        }
    }

    private void skipActivity(Class<?> classOf) {
        Intent intent = new Intent(getApplicationContext(), classOf);
        startActivity(intent);
    }

}
