package com.cliff.hsj.ui.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cliff.hsj.utils.LogUtils;
import com.squareup.picasso.Picasso;

/**
 * ListView万能适配器
 */
public class ViewHolder {
    //
    private SparseArray<View> mViews;

    //
    private int mPostion;

    //
    private View mConvertView;

    private Context mContext;


    public ViewHolder(Context context, ViewGroup parent, int layoutId,
                      int position) {
        mContext = context;
        this.mPostion = position;
        this.mViews = new SparseArray<View>();
        // 填充条目布局为布局对象
        this.mConvertView = LayoutInflater.from(context).inflate(layoutId,
                parent, false);
        this.mConvertView.setTag(this);
    }

    /**
     * 根据传入的参数返回一个ViewHolder
     *
     * @param context     上下文
     * @param convertView 要转换的控件
     * @param parent      父控件
     * @param layoutId    要填充的布局id
     * @param position    位置信息
     * @return
     */
    public static ViewHolder get(Context context, View convertView,
                                   ViewGroup parent, int layoutId, int position) {
        // 如果传入的转换convertView为null,创建一个新的Viewholder返回使用
        if (convertView == null) {
            LogUtils.e("ViewHolder", "getView>>>");
            return new ViewHolder(context, parent, layoutId, position);
        }
        // 如果不为null,那么直接从tag中取出并返回,节省时间
        else {
            ViewHolder saViewHolder = (ViewHolder) convertView.getTag();
            // 需要更新ViewHolder对应的位置信息
            saViewHolder.mPostion = position;
            return saViewHolder;
        }
    }

    /**
     * 跟据指定的id进行view组件的获取,获取后保存到mViews中方便下次使用的时快速得到;
     *
     * @param viewId 控件id
     * @return
     */
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    public View getConvertView() {
        return mConvertView;
    }

    /**
     * 给TextView设置值,并采用链式编程将当前ViewHolder返回
     *
     * @param viewId
     * @param text
     * @return
     */
    public ViewHolder setText(int viewId, String text) {
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;
    }

    /**
     * 给View设置显示与隐藏,并采用链式编程将当前ViewHolder返回
     *
     * @param viewId
     * @param isVisable
     * @return
     */
    public ViewHolder setVisable(int viewId, boolean isVisable) {
        View view = getView(viewId);
        if (isVisable) {
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.INVISIBLE);
        }
        return this;
    }

    /**
     * 给ImageVie设置本地资源,并采用链式编程将当前ViewHolder返回
     *
     * @param viewId
     * @param resId
     * @return
     */
    public ViewHolder setImageResource(int viewId, int resId) {
        ImageView view = getView(viewId);
        view.setImageResource(resId);
        return this;
    }

    /**
     * 给ImageVie设置Drawable,并采用链式编程将当前ViewHolder返回
     *
     * @param viewId
     * @param drawable
     * @return
     */
    public ViewHolder setImageDrawable(int viewId, Drawable drawable) {
        ImageView view = getView(viewId);
        view.setImageDrawable(drawable);
        return this;
    }

    /**
     * 给ImageVie设置Bitmap,并采用链式编程将当前ViewHolder返回
     *
     * @param viewId
     * @param bm
     * @return
     */
    public ViewHolder setImageBitmap(int viewId, Bitmap bm) {
        ImageView view = getView(viewId);
        view.setImageBitmap(bm);
        return this;
    }


    /**
     * 给ImageView设置网络图片
     *
     * @param viewId       控件的id
     * @param imgUrl       网络图片地址
     * @param loadingImg   加载中的图片
     * @param loaderrorImg 加载失败的图片
     * @return
     */
    public ViewHolder setImageUrl(int viewId, String imgUrl, int loadingImg, int loaderrorImg) {
        ImageView view = getView(viewId);
        view.setTag(imgUrl);
        Picasso.with(mContext).load(imgUrl).placeholder(loadingImg).error(loaderrorImg).into(view);
        return this;
    }


}
