package com.cliff.araframedemo.ui.main.activity;

import android.content.Context;
import android.content.Intent;

import com.cliff.araframedemo.R;
import com.cliff.araframedemo.api.entity.TopicDetailsEntity;
import com.cliff.araframedemo.common.Config;
import com.cliff.araframedemo.db.Topic;
import com.cliff.araframedemo.ui.main.adapter.TopicDetailsAdapter;
import com.cliff.araframedemo.ui.main.view.BaseView;
import com.cliff.hsj.ui.BaseActivity;
import com.cliff.hsj.utils.LogUtils;
import com.cliff.material.widget.FloatingActionButton;
import com.cliff.material.widget.ListView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

/**
 * @Description 话题的详情界面
 * @File TopicDetialsActivity.java
 * @Package com.martin.ionichinabystudio.ui.activity
 * @Date 2015/7/1022:03
 * @Author Donghongyu 1358506549@qq.com
 * @Version v1.0.0
 */
@EActivity(R.layout.activity_topic_details)
public class TopicDetailsActivity extends BaseActivity implements BaseView {


    @Extra(Config.TOPIC_ENTITY)
    Topic mTopic;

    //详情的列表
    @ViewById(R.id.topic_detials_list)
    ListView mListView;

    @ViewById(R.id.button_bt_float_wave_color)
    FloatingActionButton button_bt_float_wave_color;


    //话题详情的实体
    private ArrayList<TopicDetailsEntity> mTopicDetialEntities = new ArrayList<TopicDetailsEntity>();

    //详情的数据适配器
    private TopicDetailsAdapter mAdapter;

    @AfterViews
    public void init() {
        mAdapter = new TopicDetailsAdapter(this);
        mListView.setAdapter(mAdapter);
        getTopicDetails(mTopic);
    }


    /**
     * 进入话题的评论列表页面
     */
    @Click(R.id.button_bt_float_wave_color)
    public void openTopicReply() {
        Intent replyIntent = new Intent(this, RepliesActivity_.class);
        replyIntent.putExtra(Config.TOPIC_ID, mTopic.getTpId());
        startActivity(replyIntent);
    }

    @Background
    public void getTopicDetails(Topic topic) {
        // 创建话题的的详情的解析结果集合
        ArrayList<TopicDetailsEntity> mEntities = new ArrayList<TopicDetailsEntity>();

        // 标题
        TopicDetailsEntity entity = new TopicDetailsEntity();
        entity.setTopicTitle(topic.getTitle());
        entity.setTopicType(Config.TOPIC_TYPE_TITLE);
        mEntities.add(entity);

        // 创建时间
        entity = new TopicDetailsEntity();
        entity.setTopicCreateDate(topic.getCreate_at());
        entity.setTopicType(Config.TOPIC_TYPE_CREATE_DATE);
        mEntities.add(entity);

//        // 作者
//        entity = new TopicDetailsEntity();
//        entity.setTopicAuthor(topic.getAuthor());
//        entity.setTopicType(Constant.TOPIC_TYPE_AUTHOR);
//        mEntities.add(entity);

        // 内容
        Document doc = Jsoup.parse(topic.getContent());
        Element contentEle = doc.getElementsByClass("markdown-text").get(0);
        // 获得话题内容中的子元素
        Elements childrenEle = contentEle.children();

        LogUtils.e("childrenEle", childrenEle.size() + "");
        // 循环的遍历内容
        for (Element child : childrenEle) {
            Elements imgEles = child.getElementsByTag("img");

            // 图片
            if (imgEles.size() > 0) {
                for (Element imgEle : imgEles) {
                    if (imgEle.attr("src").equals(""))
                        continue;
                    entity = new TopicDetailsEntity();
                    entity.setTopicImgUrl(imgEle.attr("src"));
                    entity.setTopicType(Config.TOPIC_TYPE_IMG_URL);
                    mEntities.add(entity);
                }
            }
            if (imgEles != null)
                // 移除图片
                imgEles.remove();

            if (child.text().equals(""))
                continue;
            entity = new TopicDetailsEntity();
            entity.setTopicType(Config.TOPIC_TYPE_CONTENT);

            try {
                if (child.children().size() == 1) {
                    Element cc = child.child(0);
                    if (cc.tagName().equals("b")) {
                        entity.setTopicType(Config.TOPIC_TYPE_CONTENT);
                    }
                }
            } catch (IndexOutOfBoundsException e) {
                e.printStackTrace();
            }
            //获取元素外HTML内容
            entity.setTopicContent(child.outerHtml());
            mEntities.add(entity);
        }

        if (mEntities.size() != 0) {
            //回调成功的监听
//            listener.onSuccess(mEntities);
            setResult(mEntities);
        } else {
            //回调成功的监听
            showError("load error");
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
    @UiThread
    public void setResult(Object result) {
        // 创建话题的的详情的解析结果集合
        ArrayList<TopicDetailsEntity> mEntities = (ArrayList<TopicDetailsEntity>) result;
        mAdapter.addList(mEntities);
//        mAdapter.notifyDataSetChanged();
    }

    @ItemClick(R.id.topic_detials_list)
    public void onItemClick(TopicDetailsEntity entity) {
        LogUtils.e(TAG, entity.toString());
        Intent intent = new Intent(this, ImageShowActivity_.class);
        intent.putExtra(Config.IMG_BIG_SHOW, entity.getTopicImgUrl());
        startActivity(intent);
    }

}
