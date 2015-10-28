package com.cliff.araframedemo.ui.main.activity;

import com.cliff.araframedemo.R;
import com.cliff.araframedemo.api.TopicApi;
import com.cliff.araframedemo.api.entity.Replies;
import com.cliff.araframedemo.api.entity.TopicDetialResult;
import com.cliff.araframedemo.common.Config;
import com.cliff.araframedemo.ui.main.adapter.RepliesListAdapter;
import com.cliff.araframedemo.ui.main.view.BaseView;
import com.cliff.hsj.api.ApiClient;
import com.cliff.hsj.exception.HttpException;
import com.cliff.hsj.ui.BaseActivity;
import com.cliff.hsj.ui.widget.IRefreshLayout;
import com.cliff.hsj.utils.ToastUtils;
import com.cliff.material.widget.ListView;
import com.cliff.material.widget.ProgressView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import retrofit.Call;

/**
 * @Description 话题的评论列表
 * @File RepliesActivity.java
 * @Package com.martin.ionichinabystudio.ui.activity
 * @Date 2015/7/1411:54
 * @Author Donghongyu 1358506549@qq.com
 * @Version v1.0.0
 */
@EActivity(R.layout.activity_replies)
public class RepliesActivity extends BaseActivity implements BaseView,
        IRefreshLayout.OnRefreshListener {

    private List<Replies> mDatas;

    private RepliesListAdapter mAdapter;

    @ViewById(R.id.replies_list)
    ListView mListView;
    //谷歌的下拉刷新控件
    @ViewById(R.id.swipe_refresh_layout)
    IRefreshLayout mRefreshLayout;

    @ViewById(R.id.progress_pv_circular)
    ProgressView mProgressView;

    @Extra(Config.TOPIC_ID)
    String mTopicId;

    @AfterViews
    public void init() {
        mAdapter = new RepliesListAdapter(this);
        mListView.setAdapter(mAdapter);
        mRefreshLayout.setOnRefreshListener(this);
        showLoading();
        loadData();
    }

    @Background
    public void loadData() {
        if (mTopicId != null) {
            Call<TopicDetialResult> result = ApiClient.instance().loadService(TopicApi.class).getReplies(mTopicId);
            try {
                setResult(ApiClient.instance().getData(result));
            } catch (HttpException e) {
                showError(e.getMessage());
            }
        }
    }

    @Override
    @UiThread
    public void showLoading() {
        mProgressView.start();

    }

    @Override
    @UiThread
    public void hideLoading() {
        mProgressView.stop();
    }

    @Override
    @UiThread
    public void showError(String msg) {
        hideLoading();
        ToastUtils.show(this, msg);
    }

    @Override
    @UiThread
    public void setResult(Object result) {
        hideLoading();
        TopicDetialResult topicDetialResult = (TopicDetialResult) result;
        if (topicDetialResult != null) {
            mDatas = topicDetialResult.getData().getReplies();
            mAdapter.addList(mDatas);
            mAdapter.notifyDataSetChanged();
        } else {
            showError("result is null");
        }

    }

    //刷新的监听
    @Override
    public void onRefresh() {
        if (mTopicId != null) {
            showLoading();
            loadData();
        }
    }

}
