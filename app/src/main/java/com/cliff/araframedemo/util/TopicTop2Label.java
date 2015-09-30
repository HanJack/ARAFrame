package com.cliff.araframedemo.util;

import android.content.Context;
import android.widget.TextView;

import com.cliff.araframedemo.R;
import com.cliff.araframedemo.common.Config;


/**
 * @Description 话题的分类英文转成对应的标记的工具
 * @File TopicTop2Label.java
 * @Package com.martin.ionichina.util
 * @Date 2015年6月17日下午2:05:46
 * @Author Donghongyu 1358506549@qq.com
 * @Version v1.0.0
 */
public class TopicTop2Label {

    /**
     * 将话题分类转换成对应的标记文字
     *
     * @param topicTop
     * @return
     */
    public static void top2Label(Context mContext, TextView mTextView,
                                 String topicTop, boolean isTop, boolean isGood) {
        int topTabStrId = R.string.topic_tab_label_other;
        int topTabStyleId = R.style.item_topic_tab_gray;
        int topTabBgId = R.drawable.item_topic_content_tab_gray_bg;
        // 判断是否是指定/精华
        if (isTop) {
            topTabStrId = R.string.topic_tab_label_top;
            topTabStyleId = R.style.item_topic_tab_blue;
            topTabBgId = R.drawable.item_topic_content_tab_blue_bg;
            setTopicTabStyle(mContext, mTextView, topTabStrId, topTabStyleId,
                    topTabBgId);
            return;
        }
        if (isGood) {
            topTabStrId = R.string.topic_tab_label_good;
            topTabStyleId = R.style.item_topic_tab_blue;
            topTabBgId = R.drawable.item_topic_content_tab_blue_bg;
            setTopicTabStyle(mContext, mTextView, topTabStrId, topTabStyleId,
                    topTabBgId);
            return;
        }
        switch (topicTop) {
            case Config.TOPIC_TOP_ALL:
                topTabStrId = R.string.topic_tab_label_all;
                topTabStyleId = R.style.item_topic_tab_gray;
                topTabBgId = R.drawable.item_topic_content_tab_gray_bg;
                break;
            case Config.TOPIC_TOP_SHARE:
                topTabStrId = R.string.topic_tab_label_shearl;
                topTabStyleId = R.style.item_topic_tab_gray;
                topTabBgId = R.drawable.item_topic_content_tab_gray_bg;
                break;
            case Config.TOPIC_TOP_ASK:
                topTabStrId = R.string.topic_tab_label_ask;
                topTabStyleId = R.style.item_topic_tab_gray;
                topTabBgId = R.drawable.item_topic_content_tab_gray_bg;
                break;
            case Config.TOPIC_TOP_JOB:
                topTabStrId = R.string.topic_tab_label_job;
                topTabStyleId = R.style.item_topic_tab_gray;
                topTabBgId = R.drawable.item_topic_content_tab_gray_bg;
                break;
            case Config.TOPIC_TOP_BB:
                topTabStrId = R.string.topic_tab_label_bb;
                topTabStyleId = R.style.item_topic_tab_gray;
                topTabBgId = R.drawable.item_topic_content_tab_gray_bg;
                break;
            default:
                topTabStrId = R.string.topic_tab_label_other;
                topTabStyleId = R.style.item_topic_tab_gray;
                topTabBgId = R.drawable.item_topic_content_tab_gray_bg;
                break;
        }
        setTopicTabStyle(mContext, mTextView, topTabStrId, topTabStyleId,
                topTabBgId);
        return;
    }

    /**
     * 设置话题的样式以及文字
     */
    public static void setTopicTabStyle(Context mContext, TextView mTextView,
                                        int topTabStrId, int topTabStyleId, int topTabBgId) {
        // 获取指定的文字标识
        String topicTabStr = mContext.getResources().getString(topTabStrId);
        mTextView.setText(topicTabStr.trim());
        mTextView.setTextAppearance(mContext, topTabStyleId);
        mTextView.setBackgroundResource(topTabBgId);
    }

}
