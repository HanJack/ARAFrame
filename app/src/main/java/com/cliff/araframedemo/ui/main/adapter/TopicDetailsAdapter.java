package com.cliff.araframedemo.ui.main.adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cliff.araframedemo.R;
import com.cliff.araframedemo.api.entity.TopicDetailsEntity;
import com.cliff.araframedemo.common.Config;
import com.cliff.hsj.utils.DateUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * @Description 话题的详情列表数据
 */
public class TopicDetailsAdapter extends BaseAdapter {

    //布局填充
    private LayoutInflater mInflater;

    // 创建话题的的详情的解析结果集合
    private ArrayList<TopicDetailsEntity> mDatas = new ArrayList<TopicDetailsEntity>();

    private Context mContext;

    public TopicDetailsAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    public void addList(ArrayList<TopicDetailsEntity> datas) {
        mDatas.addAll(datas);
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        switch (mDatas.get(position).getTopicType()) {
            case Config.TOPIC_TYPE_TITLE:
                return 0;
            case Config.TOPIC_TYPE_CREATE_DATE:
                return 1;
            case Config.TOPIC_TYPE_CONTENT:
                return 2;
            case Config.TOPIC_TYPE_IMG_URL:
                return 3;

        }
        return -1;
    }

    @Override
    public int getViewTypeCount() {
        return 4;
    }

    @Override
    public boolean isEnabled(int position) {
        switch (mDatas.get(position).getTopicType()) {
            case Config.TOPIC_TYPE_IMG_URL:
                return true;
            default:
                return false;
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        TopicDetailsEntity entity = mDatas.get(position);
        ViewHolder holder = null;
        if (null == convertView) {
            holder = new ViewHolder();
            switch (entity.getTopicType()) {
                case Config.TOPIC_TYPE_TITLE:
                    convertView = mInflater.inflate(R.layout.item_topic_details_title, null);
                    holder.mTextView = (TextView) convertView.findViewById(R.id.tv_topic_title);
                    break;
                case Config.TOPIC_TYPE_CREATE_DATE:
                    convertView = mInflater.inflate(R.layout.item_topic_details_date, null);
                    holder.mTextView = (TextView) convertView.findViewById(R.id.tv_topic_date);
                    break;
                case Config.TOPIC_TYPE_CONTENT:
                    convertView = mInflater.inflate(R.layout.item_topic_details_content, null);
                    holder.mTextView = (TextView) convertView.findViewById(R.id.tv_topic_content);
                    break;
                case Config.TOPIC_TYPE_IMG_URL:
                    convertView = mInflater.inflate(R.layout.item_topic_details_img, null);
                    holder.mImageView = (ImageView) convertView.findViewById(R.id.item_topic_img);
                    break;
            }
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (null != entity) {
            switch (entity.getTopicType()) {
                case Config.TOPIC_TYPE_IMG_URL:
                    //得到图片的路径
                    String imgURL = entity.getTopicImgUrl();
                    //判断图片路径的合法性
                    Picasso.with(mContext).load(imgURL).placeholder(R.drawable.discover_image_default)
                            .error(R.drawable.discover_image_default).into(holder.mImageView);

                    break;
                case Config.TOPIC_TYPE_TITLE:
                    holder.mTextView.setText(entity.getTopicTitle());
                    break;
                case Config.TOPIC_TYPE_CREATE_DATE:
                    // 设置话题的最后更新日期
                    String dateStr = entity.getTopicCreateDate();
                    dateStr = dateStr.replace("T", " ");
                    dateStr = dateStr.replace("Z", " ");
                    holder.mTextView.setText(DateUtils
                            .formatDateStr2Desc(dateStr, DateUtils.dateFormatYMDHMS));

                    break;
                case Config.TOPIC_TYPE_CONTENT:
                    holder.mTextView.setText(Html.fromHtml(entity.getTopicContent()));
                    break;
            }
        }
        return convertView;
    }

    private final class ViewHolder {
        TextView mTextView;
        ImageView mImageView;
    }
}
