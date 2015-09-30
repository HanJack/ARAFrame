package com.cliff.araframedemo.ui.main.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * @Description 主页的片段的数据据适配
 * @File ${FILE_NAME}
 * @Package com.martin.ionichinabystudio.ui.adapter
 * @Date 2015/7/816:31
 * @Author Donghongyu 1358506549@qq.com
 * @Version v1.0.0
 */
public class MianActPagerAdapter extends FragmentStatePagerAdapter {

    //要显示的片段的集合，数量需要跟mItems对应
    private ArrayList<Fragment> mFragments;

    //选项卡的显示的文字数组
    private String[] mTabs;

    public MianActPagerAdapter(FragmentManager fm, String[] tabs, ArrayList<Fragment> fragments) {
        super(fm);
        this.mTabs = tabs;
        this.mFragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);

    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTabs[position].toString().toUpperCase();
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}
