package com.cliff.araframedemo.ui.main.activity;

import android.content.Context;

import com.cliff.araframedemo.R;
import com.cliff.araframedemo.api.TopicApi;
import com.cliff.araframedemo.api.entity.Replies;
import com.cliff.araframedemo.api.entity.TopicDetialResult;
import com.cliff.araframedemo.common.Config;
import com.cliff.araframedemo.ui.main.adapter.RepliesListAdapter;
import com.cliff.araframedemo.ui.main.view.BaseView;
import com.cliff.hsj.api.ApiClient;
import com.cliff.hsj.ui.BaseActivity;
import com.cliff.material.widget.ListView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import java.io.IOException;
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
public class RepliesActivity extends BaseActivity implements BaseView {

    private List<Replies> mDatas;

    private RepliesListAdapter mAdapter;

    @ViewById(R.id.replies_list)
    ListView mListView;

    @Extra(Config.TOPIC_ID)
    String mTopicId;

    @AfterViews
    @Background
    public void init() {
        mAdapter = new RepliesListAdapter(this);
        mListView.setAdapter(mAdapter);
        if (mTopicId != null) {
            Call<TopicDetialResult> result = ApiClient.instance().loadService(TopicApi.class).getReplies(mTopicId);
            try {
                TopicDetialResult re = result.execute().body();
                if (re == null)
                    showError(result.execute().raw().message());
                else
                    setResult(re);
            } catch (IOException ex) {

            }
        }
    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void setResult(Object result) {
        TopicDetialResult topicDetialResult = (TopicDetialResult) result;
        if (topicDetialResult != null) {
            mDatas = topicDetialResult.getData().getReplies();
            mAdapter.addList(mDatas);
            mAdapter.notifyDataSetChanged();
        }
    }
}
