package com.cliff.araframedemo.ui.main.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;


import com.cliff.araframedemo.R;
import com.cliff.araframedemo.common.Config;
import com.cliff.araframedemo.db.Topic;
import com.cliff.araframedemo.ui.main.activity.TopicDetailsActivity_;
import com.cliff.araframedemo.util.TopicTop2Label;
import com.cliff.hsj.ui.adapter.IBaseAdapter;
import com.cliff.hsj.ui.adapter.ViewHolder;
import com.cliff.hsj.utils.DateUtils;

import java.util.List;

/**
 * @Description 话题列表数据适配器
 * @File TopicListAdapter.java
 * @Package com.martin.ionichina.module.topic.adapter
 * @Date 2015年6月16日下午7:39:41
 * @Author Donghongyu 1358506549@qq.com
 * @Version v1.0.0
 */
public class TopicListAdapter extends IBaseAdapter<Topic> implements View.OnClickListener {
    private Activity mActivity;

    public TopicListAdapter(Activity activity, Context context, List<Topic> dates, int lyoutId) {
        super(context, dates, lyoutId);
        this.mActivity = activity;
    }

    @Override
    public void convert(ViewHolder holder, Topic mTopic) {
        // 设置发帖用户的头像
        holder.setImageUrl(R.id.item_topic_author_head, mTopic.getAuthor()
                .getAvatar_url(), R.mipmap.ic_launcher, R.mipmap.ic_launcher);
        // 设置标题
        holder.setText(R.id.item_topic_title, mTopic.getTitle());
        // 设置回复数量与阅读数量
        holder.setText(R.id.item_topic_reply_ratio_visit_count,
                mTopic.getReply_count() + "/" + mTopic.getVisit_count());
        // 设置话题的最后更新日期
        String dateStr = mTopic.getLast_reply_at();
        dateStr = dateStr.replace("T", " ");
        dateStr = dateStr.replace("Z", " ");
        holder.setText(R.id.item_topic_create_at, DateUtils
                .formatDateStr2Desc(dateStr, DateUtils.dateFormatYMD));
        // 判断是否是 置顶的贴
        holder.setVisable(R.id.item_topic_mark_icon, mTopic.getTop());
        // 根据返回的话题表示进行转换成显示用的数据
        TopicTop2Label.top2Label(mContext,
                (TextView) holder.getView(R.id.item_topic_tab),
                mTopic.getTab(), mTopic.getTop(), mTopic.getGood());

        //设置条目的监听
        holder.getView(R.id.rl_topic_item).setTag(mTopic);
        holder.getView(R.id.rl_topic_item).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Topic mTopic = (Topic) v.getTag();
        //创建打开话题详情的意图
        Intent intent = new Intent(mActivity, TopicDetailsActivity_.class);
        //传入话题对象
        intent.putExtra(Config.TOPIC_ENTITY, mTopic);
        //打开详情页
        mActivity.startActivity(intent);
    }
}
