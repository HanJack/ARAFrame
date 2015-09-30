package com.cliff.hsj.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * 万能ListView适配器
 * @param <T>
 */
public abstract class IBaseAdapter<T> extends BaseAdapter {

    protected Context mContext;
    protected List<T> mDatas;
    protected LayoutInflater mInflater;
    protected int mLyoutId;

    public IBaseAdapter(Context context, List<T> dates, int lyoutId) {
        this.mContext = context;
        this.mDatas = dates;
        this.mLyoutId = lyoutId;
        mInflater = LayoutInflater.from(mContext);
    }


    /**
     * 向ListView数据列表的上面添加数据，需要T中重写equals方法
     *
     * @param mDatas
     */
    public void addRefreshDatas(List<T> mDatas) {
        for (int i = mDatas.size() - 1; i >= 0; i--) {
            T topic = mDatas.get(i);
            int index = this.mDatas.indexOf(topic);
            if (index == -1) {
                //添加到列表数据的第一位
                this.mDatas.add(0, topic);
            }
        }
    }

    public void addAll(List<T> mDatas) {
        this.mDatas.addAll(mDatas);
    }

    public void setDatas(List<T> mDatas) {
        this.mDatas.clear();
        this.mDatas.addAll(mDatas);
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public T getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = ViewHolder.get(mContext, convertView, parent,
                mLyoutId, position);
        convert(holder, getItem(position));
        return holder.getConvertView();
    }

    public abstract void convert(ViewHolder holder, T t);
}
