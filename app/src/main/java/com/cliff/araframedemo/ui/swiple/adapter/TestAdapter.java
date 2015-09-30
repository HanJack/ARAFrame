package com.cliff.araframedemo.ui.swiple.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.cliff.araframedemo.ui.swiple.TestFragment;


public class TestAdapter extends FragmentPagerAdapter {

    public TestAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int arg0) {
        switch (arg0) {
            case 0:
                return TestFragment.newInstance("Fragment1");
            case 1:
                return TestFragment.newInstance("Fragment2");
            case 2:
                return TestFragment.newInstance("Fragment3");
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

}
