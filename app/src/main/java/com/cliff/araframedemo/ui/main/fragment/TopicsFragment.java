package com.cliff.araframedemo.ui.main.fragment;

import android.content.Context;
import android.os.Bundle;

import com.cliff.araframedemo.R;
import com.cliff.araframedemo.api.TopicApi;
import com.cliff.araframedemo.api.entity.TopicListResult;
import com.cliff.araframedemo.common.Config;
import com.cliff.araframedemo.db.Topic;
import com.cliff.araframedemo.ui.main.activity.MainActivity;
import com.cliff.araframedemo.ui.main.adapter.TopicListAdapter;
import com.cliff.araframedemo.ui.main.view.BaseView;
import com.cliff.hsj.api.ApiClient;
import com.cliff.hsj.exception.HttpException;
import com.cliff.hsj.ui.BaseFragment;
import com.cliff.hsj.ui.widget.IRefreshLayout;
import com.cliff.hsj.utils.LogUtils;
import com.cliff.hsj.utils.ToastUtils;
import com.cliff.material.widget.ListView;
import com.cliff.material.widget.ProgressView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Response;

/**
 */
@EFragment(R.layout.fragment_topics)
public class TopicsFragment extends BaseFragment implements
        BaseView, IRefreshLayout.OnRefreshListener, IRefreshLayout.OnLoadListener {

    Context mContext;

    MainActivity mActivity;

    //查询话题的类型
    private String mTopicType;

    //谷歌的下拉刷新控件
    @ViewById(R.id.swipe_refresh_layout)
    IRefreshLayout mRefreshLayout;

    //显示话题的列表控件
    @ViewById(R.id.topic_list)
    ListView listView;

    @ViewById(R.id.progress_pv_circular)
    ProgressView mProgressView;

    private TopicApi mTopicApi;

    //话题的数据适配器
    TopicListAdapter topicListAdapter;

    //数据源
    List<Topic> topicList = new ArrayList<Topic>();


    //当前的数据页
    int currentPage = 1;

    //获取数据的类型，刷新出来的数据，加载出来的数据
    boolean isRefresh = true;

    @AfterViews
    public void init() {
        LogUtils.e(TAG, "查询话题。。。。");


        //接收到初始化的时候传入的类型
        Bundle bundle = getArguments();
        mTopicType = bundle != null ? bundle.getString(Config.QUERY_TOPIC_TYPE) : "";
        mActivity = (MainActivity) getActivity();
        mContext = mActivity;

        //设置刷新监听
        mRefreshLayout.setOnRefreshListener(this);
        //设置加载监听
        mRefreshLayout.setOnLoadListener(this);
        //设置刷新控件的样式
        mRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);
        //设置刷新控件的背景色
        mRefreshLayout.setProgressBackgroundColorSchemeColor(getResources().getColor(R.color.gray));
        //创建话题的数据适配器
        topicListAdapter = new TopicListAdapter(mActivity, mContext, topicList, R.layout.item_topics);
        //给列表控件设置数据适配器
        listView.setAdapter(topicListAdapter);

        mTopicApi = ApiClient.instance().loadService(TopicApi.class);
        loadData();
    }

    //刚初始化是图片段的数据查询操作
    @Background
    public void loadData() {
        showLoading();
        Call<TopicListResult> result = mTopicApi.getTopics(mTopicType, currentPage, 10, true);
        try {
            setResult(ApiClient.instance().getData(result));
        } catch (HttpException e) {
            hideLoading();
            showError(e.getMessage());
        }
    }

    @Override
    @UiThread
    public void setResult(Object topic) {
        TopicListResult topicListResult = (TopicListResult) topic;
        if (isRefresh) {
            topicListAdapter.addRefreshDatas(topicListResult.getData());
        } else {
            topicListAdapter.addAll(topicListResult.getData());
        }

        topicListAdapter.notifyDataSetChanged();
        hideLoading();
    }

    @Override
    @UiThread
    public void showLoading() {
        //开始进度条
        mProgressView.start();
//        mRefreshLayout.setRefreshing(true);
//        SaToastUtils.showToast(mContext, "显示加载的控件。。。");
    }

    @Override
    public void hideLoading() {
        //开始进度条
        mProgressView.stop();
        mRefreshLayout.setRefreshing(false);
        mRefreshLayout.setLoading(false);
    }

    @Override
    @UiThread
    public void showError(String msg) {
        hideLoading();
        ToastUtils.show(mContext, msg);
    }


    //刷新的监听
    @Override
    public void onRefresh() {
        isRefresh = true;
//        mRefreshLayout.setLoading(false);
        currentPage = 1;
        loadData();
    }

    //加载的监听
    @Override
    public void onLoad() {
        isRefresh = false;
//        mRefreshLayout.setRefreshing(false);
        currentPage++;
        loadData();
    }


}
