package com.cliff.araframedemo.ui.main.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

import com.cliff.material.widget.Slider;
import com.cliff.material.widget.Switch;

/**
 * @Description
 * @File ${FILE_NAME}
 * @Package com.martin.ionichinabystudio.ui.view
 * @Date 2015/7/813:33
 * @Author Donghongyu 1358506549@qq.com
 * @Version v1.0.0
 */
public class CustomViewPager extends ViewPager {

    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomViewPager(Context context) {
        super(context);
    }

    protected boolean canScroll(View v, boolean checkV, int dx, int x, int y) {
        return super.canScroll(v, checkV, dx, x, y) || (checkV && customCanScroll(v));
    }

    protected boolean customCanScroll(View v) {
        if (v instanceof Slider || v instanceof Switch)
            return true;
        return false;
    }
}