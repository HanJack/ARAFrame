package com.cliff.araframedemo.ui.main.adapter;

import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.cliff.araframedemo.R;
import com.cliff.araframedemo.ui.main.view.CustomViewPager;
import com.cliff.material.util.ThemeUtil;

/**
 * @Description
 * @File ${FILE_NAME}
 * @Package com.martin.ionichinabystudio.ui.adapter
 * @Date 2015/7/816:43
 * @Author Donghongyu 1358506549@qq.com
 * @Version v1.0.0
 */
public class MainActDrawerAdapter extends BaseAdapter implements View.OnClickListener {
    //选项卡的显示的文字数组
    private String[] mTabs;

    //选中的现实的额选项卡的文字
    private String mSelectedTab;

    //应用的上下文
    private Context mContext;

    public DrawerLayout dl_navigator;

    public CustomViewPager vp;

    public FrameLayout fl_drawer;

    public MainActDrawerAdapter(String[] mTabs, Context mContext,CustomViewPager vp,
                                DrawerLayout dl_navigator,FrameLayout fl_drawer) {
        this.mTabs = mTabs;
        this.mContext = mContext;
        this.dl_navigator=dl_navigator;
        this.vp=vp;
        this.fl_drawer=fl_drawer;
    }

    public void setSelected(String tab){
        if(tab != mSelectedTab){
            mSelectedTab = tab;
            notifyDataSetInvalidated();
        }
    }

    public String getSelectedTab(){
        return mSelectedTab;
    }

    @Override
    public int getCount() {
        return mTabs.length;
    }

    @Override
    public Object getItem(int position) {
        return mTabs[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if(v == null) {
            v = LayoutInflater.from(mContext).inflate(R.layout.row_drawer, null);
            v.setOnClickListener(this);
        }

        v.setTag(position);
        String tab = (String)getItem(position);
        ((TextView)v).setText(tab);

        if(tab.equals(mSelectedTab)) {
            v.setBackgroundColor(ThemeUtil.colorPrimary(mContext, 0));
            ((TextView)v).setTextColor(0xFFFFFFFF);
        }
        else {
            v.setBackgroundResource(0);
            ((TextView)v).setTextColor(0xFF000000);
        }

        return v;
    }

    @Override
    public void onClick(View v) {
        int position = (Integer)v.getTag();
        vp.setCurrentItem(position);
        dl_navigator.closeDrawer(fl_drawer);
    }
}
