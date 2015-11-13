package com.cliff.araframedemo.ui.main.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.cliff.araframedemo.R;
import com.cliff.araframedemo.common.Config;
import com.cliff.araframedemo.ui.main.adapter.MainActDrawerAdapter;
import com.cliff.araframedemo.ui.main.adapter.MianActPagerAdapter;
import com.cliff.araframedemo.ui.main.fragment.TopicsFragment;
import com.cliff.araframedemo.ui.main.fragment.TopicsFragment_;
import com.cliff.araframedemo.ui.main.view.CustomViewPager;
import com.cliff.hsj.ui.BaseActivity;
import com.cliff.hsj.utils.ToastUtils;
import com.cliff.material.app.ToolbarManager;
import com.cliff.material.widget.FloatingActionButton;
import com.cliff.material.widget.ListView;
import com.cliff.material.widget.SnackBar;
import com.cliff.material.widget.TabPageIndicator;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

@EActivity(R.layout.activity_main)
public class MainActivity extends BaseActivity implements ToolbarManager.OnToolbarGroupChangedListener {

    private MainActivity mActivity;

    private Context mContext;

    //带有侧滑菜单的滑动布局控件
    @ViewById(R.id.main_dl)
    public DrawerLayout dl_navigator;

    //侧滑出来的显示布局
    @ViewById(R.id.main_fl_drawer)
    public FrameLayout fl_drawer;

    //策划出来的显示布局中的列表，这里根据自己的定义来获取
    @ViewById(R.id.main_lv_drawer)
    public ListView lv_drawer;

    //头部的工具栏
    @ViewById(R.id.main_toolbar)
    public Toolbar mToolbar;

    //中间的的显示区域控件
    @ViewById(R.id.main_vp)
    public CustomViewPager vp;

    //切换中间的显示的选项卡控件
    @ViewById(R.id.main_tpi)
    public TabPageIndicator tpi;

    //
    @ViewById(R.id.main_sn)
    public SnackBar mSnackBar;


    @ViewById(R.id.button_bt_float_wave_color)
    public FloatingActionButton mActionButton;

    //选项卡的显示的文字
    private String[] mTopicTypeLabel;

    //选项卡的显示的文字对应的要查询的类型
    private String[] mTopicType;

    //要显示的片段的集合，数量需要跟mItems对应
    private ArrayList<Fragment> mFragments = new ArrayList<Fragment>();

    //选项卡的数据适配
    private MainActDrawerAdapter mDrawerAdapter;

    //显示区域的显示片段的数据适配
    private MianActPagerAdapter mPagerAdapter;

    //工具栏的管理工具
    public ToolbarManager mToolbarManager;

    @AfterViews
    public void init() {
        mActivity = this;
        mContext = mActivity;
        // 获取资源管理对象
        Resources res = getResources();

        // 配置话题标识
        mTopicTypeLabel = res.getStringArray(R.array.topic_tab_label);

        // 配置话题标识对应的类型
        mTopicType = res.getStringArray(R.array.topic_tab_type);

        //初始化工具栏管理器
        initToolBar();

        //初始化选项卡中的数据
        mDrawerAdapter = new MainActDrawerAdapter(mTopicTypeLabel, mContext, vp,
                dl_navigator, fl_drawer);
        lv_drawer.setAdapter(mDrawerAdapter);


        //循环建立显示的片段
        for (int i = 0; i < mTopicTypeLabel.length; i++) {
            //要传入显示片段的数据包
            Bundle data = new Bundle();
            //查询的话题类型
            data.putString(Config.QUERY_TOPIC_TYPE, mTopicType[i]);
            TopicsFragment newfragment = new TopicsFragment_();
            newfragment.setArguments(data);
            //将显示片段加入显示的集合
            mFragments.add(newfragment);
        }

        //设置选项卡对应的片段的数据适配器
        mPagerAdapter = new MianActPagerAdapter(getSupportFragmentManager(), mTopicTypeLabel, mFragments);
        vp.setAdapter(mPagerAdapter);
        //增加缓存的Fragment的数量
        vp.setOffscreenPageLimit(4);
        tpi.setViewPager(vp);
        //设置页面被改变的监听
        tpi.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                mDrawerAdapter.setSelected(mTopicTypeLabel[position]);
                mSnackBar.dismiss();
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }

        });
        //设置选项卡的默认选择
        mDrawerAdapter.setSelected(mTopicTypeLabel[0]);
        vp.setCurrentItem(0);
//        Snackbar
//                .make(dl_navigator, "snaker test", Snackbar.LENGTH_LONG)
//                .setAction("retry",v -> openTestActivity()).show();
        mSnackBar.actionText("snaker test").duration(Snackbar.LENGTH_LONG).show();
//        SnackBar.make(getBaseContext()).actionText("snaker Text").duration(Snackbar.LENGTH_SHORT).show();
    }

    /**
     * 初始化工具栏
     */
    public void initToolBar() {
        mToolbarManager = new ToolbarManager(this, mToolbar, 0, R.style.ToolbarRippleStyle,
                R.anim.abc_fade_in, R.anim.abc_fade_out);
        mToolbarManager.setNavigationManager(new ToolbarManager.BaseNavigationManager(R.style.NavigationDrawerDrawable,
                this, mToolbar, dl_navigator) {
            @Override
            public void onNavigationClick() {
                if (mToolbarManager.getCurrentGroup() != 0)
                    mToolbarManager.setCurrentGroup(0);
                else
                    dl_navigator.openDrawer(GravityCompat.START);
            }

            @Override
            public boolean isBackState() {
                return super.isBackState() || mToolbarManager.getCurrentGroup() != 0;
            }

            @Override
            protected boolean shouldSyncDrawerSlidingProgress() {
                return super.shouldSyncDrawerSlidingProgress() && mToolbarManager.getCurrentGroup() == 0;
            }

        });
        //给工具栏注册改变的监听
        mToolbarManager.registerOnToolbarGroupChangedListener(this);
    }

    @Click(R.id.button_bt_float_wave_color)
    public void openTestActivity() {
        Intent openTest = new Intent(this, com.cliff.araframedemo.ui.swiple.MainActivity_.class);
        startActivity(openTest);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        mToolbarManager.createMenu(R.menu.menu_main);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        mToolbarManager.onPrepareMenu();
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.tb_contextual:
//                Intent intent = new Intent(mActivity, CaptureActivity.class);
//                startActivityForResult(intent, SaAppConfig.QR_REQUESTCODE);
                ToastUtils.show(this, "启动二维码扫描");
                break;
        }
        return true;
    }

    @Override
    public void onToolbarGroupChanged(int oldGroupId, int groupId) {
        mToolbarManager.notifyNavigationStateChanged();
    }
}
