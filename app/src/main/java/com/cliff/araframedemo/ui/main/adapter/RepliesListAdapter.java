package com.cliff.araframedemo.ui.main.adapter;

import android.content.Context;
import android.os.Handler;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.cliff.araframedemo.R;
import com.cliff.araframedemo.api.entity.Replies;
import com.cliff.araframedemo.ui.main.view.ImageGroupView;
import com.cliff.hsj.utils.DateUtils;
import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 */
public class RepliesListAdapter extends BaseAdapter {

    //布局填充
    private LayoutInflater mInflater;

    // 创建话题的的详情的解析结果集合
    private List<Replies> mDatas = new ArrayList<Replies>();


    private Context mContext;

    private Handler handler;

    public RepliesListAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }


    public void addList(List<Replies> datas) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        Replies replies = mDatas.get(position);

        ViewHolder holder = null;
        if (null == convertView) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.item_replies, null);
            holder.mLinearLayout = (LinearLayout) convertView.findViewById(R.id.ll_reply);
            holder.mTVReplyContent = (TextView) convertView.findViewById(R.id.item_topic_reply_content);
            holder.mNIVAuthorHead = (ImageView) convertView.findViewById(R.id.item_topic_author_head);
            holder.mTVAuthorNames = (TextView) convertView.findViewById(R.id.item_topic_reply_author_name);
            holder.mTVReplyCreateDate = (TextView) convertView.findViewById(R.id.item_topic_reply_create_at);
            holder.mImageGroupView = (ImageGroupView) convertView.findViewById(R.id.item_topic_reply_imgs);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        //设置头像
        //得到图片的路径
        String imgURL = replies.getAuthor().getAvatar_url();
        //判断图片路径的合法性
        Picasso.with(mContext).load(imgURL).placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher).into(holder.mNIVAuthorHead);

        //设置用户名称
        holder.mTVAuthorNames.setText(replies.getAuthor().getLoginname());

        //设置创建时间
        String dateStr = replies.getCreate_at();
        dateStr = dateStr.replace("T", " ");
        dateStr = dateStr.replace("Z", " ");
        holder.mTVReplyCreateDate.setText(DateUtils
                .formatDateStr2Desc(dateStr, DateUtils.dateFormatYMDHMS));

//        initItemContent(holder, replies);
        List<String> replyImgs = new ArrayList<String>();
        // 内容
        Document doc = Jsoup.parse(replies.getContent());
        Element contentEle = doc.getElementsByClass("markdown-text").get(0);
        //得到评论中的所有图片
        Elements imgEles = contentEle.getElementsByTag("img");
        // 图片
        if (imgEles.size() > 0) {
            for (Element imgEle : imgEles) {
                if (imgEle.attr("src").equals(""))
                    continue;
                replyImgs.add(imgEle.attr("src"));
            }
        }
        if (imgEles != null)
            // 移除图片
            imgEles.remove();

        holder.mTVReplyContent.setText(Html.fromHtml(contentEle.outerHtml()));


        //获取条目中的子元素个数
        if (replyImgs.size() > 0) {
            holder.mImageGroupView.setVisibility(View.VISIBLE);
            holder.mImageGroupView.setImages(replyImgs);
        } else {
            holder.mImageGroupView.setVisibility(View.GONE);
        }
        return convertView;
    }


    private final class ViewHolder {
        LinearLayout mLinearLayout;
        TextView mTVReplyContent, mTVAuthorNames, mTVReplyCreateDate;
        ImageView mNIVAuthorHead;
        ImageGroupView mImageGroupView;
    }
}
