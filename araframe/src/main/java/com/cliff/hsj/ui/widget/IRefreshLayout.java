package com.cliff.hsj.ui.widget;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;

import com.cliff.hsj.R;

/**
 * 基于Google官方的SwipeRefreshLayout，自定义的下拉刷新，上拉加载的控件
 */
public class IRefreshLayout extends SwipeRefreshLayout implements OnScrollListener {

    /**
     * listview实例
     */
    private ListView mListView;

    /**
     * 上拉监听器, 到了最底部的上拉加载操作
     */
    private OnLoadListener mOnLoadListener;

    /**
     * ListView的加载中footer
     */
    private View mListViewFooter;

    /**
     * ListView的列表总数
     */
    int totalItemCount;

    /**
     * ListView最后一个可见的item；
     */
    int lastVisibleItem;


    /**
     * 是否处于加载状态
     */
    private boolean isLoading = false;

    /**
     * @param context
     */
    public IRefreshLayout(Context context) {
        this(context, null);
        initView(context);
    }

    public IRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        // 初始化ListView对象
        if (mListView == null) {
            getListView();
        }
    }

    /**
     * 初始化组件
     *
     * @param context
     */
    private void initView(Context context) {
        mListViewFooter = LayoutInflater.from(context).inflate(R.layout.listview_footer, null,
                false);

    }

    /**
     * 获取ListView对象
     */
    private void getListView() {
        int childs = getChildCount();
        if (childs > 0) {
            View childView = getChildAt(1);
            if (childView instanceof ListView) {
                mListView = (ListView) childView;
                //将上拉加载的布局添加到ListView的最低端
                mListView.addFooterView(mListViewFooter, null, false);
                // 设置滚动监听器给ListView, 使得滚动的情况下也可以自动加载
                mListView.setOnScrollListener(this);
            }
        }
    }

    /**
     * 如果到了最底部,而且是上拉操作.那么执行onLoad方法
     */
    private void loadData() {
        if (mOnLoadListener != null) {
            // 设置状态
            setLoading(true);
            //调用加载数据
            mOnLoadListener.onLoad();
        }
    }

    /**
     * 设置上拉加载的视图显示状态
     *
     * @param loading
     */
    public void setLoading(boolean loading) {
        isLoading = loading;
        if (isLoading) {
            mListViewFooter.findViewById(R.id.load_layout).setVisibility(
                    View.VISIBLE);
        } else {
            mListViewFooter.findViewById(R.id.load_layout).setVisibility(
                    View.GONE);
        }
    }

    /**
     * 设置上拉加载的监听器
     *
     * @param loadListener
     */
    public void setOnLoadListener(OnLoadListener loadListener) {
        mOnLoadListener = loadListener;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (totalItemCount == lastVisibleItem
                && scrollState == SCROLL_STATE_IDLE) {
            loadData();
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
                         int totalItemCount) {
        this.lastVisibleItem = firstVisibleItem + visibleItemCount;
        this.totalItemCount = totalItemCount;
    }

    /**
     * 加载更多的监听器
     *
     * @author mrsimple
     */
    public static interface OnLoadListener {
        public void onLoad();
    }
}
