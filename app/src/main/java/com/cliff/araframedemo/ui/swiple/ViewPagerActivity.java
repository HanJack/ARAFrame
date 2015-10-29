package com.cliff.araframedemo.ui.swiple;

import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.cliff.araframedemo.R;
import com.cliff.araframedemo.ui.swiple.adapter.TestAdapter;
import com.cliff.hsj.ui.SwipeBackActivity;
import com.cliff.hsj.ui.widget.swipeback.SwipeBackHelper;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Created by Eric on 15/2/27.
 */
@EActivity(R.layout.swip_activity_viewpager)
public class ViewPagerActivity extends SwipeBackActivity {
    @ViewById(R.id.toolbar)
    Toolbar toolbar;
    @ViewById(R.id.viewpager_demo)
    ViewPager viewPager;

    @AfterViews
    void initViews() {
        toolbar.setTitle("activity_viewpager");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);


        TestAdapter adapter = new TestAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                SwipeBackHelper.getCurrentPage(ViewPagerActivity.this).setSwipeBackEnable(position == 0);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

}
