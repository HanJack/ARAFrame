package com.cliff.material.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.cliff.material.drawable.RippleDrawable;

/**
 * @Description 实现点击的后产生水波纹效果，需要配合 style="@style/Material.Drawable.Ripple.Wave.Light"使用
 * @File RelativeLayout.java
 * @Package com.cliff.material.widget
 * @Date 2015/7/1018:23
 * @Author Donghongyu 1358506549@qq.com
 * @Version v1.0.0
 */
public class RelativeLayout extends android.widget.RelativeLayout {
    private RippleManager mRippleManager;

    public interface OnSelectionChangedListener {
        public void onSelectionChanged(View v, int selStart, int selEnd);
    }

    private OnSelectionChangedListener mOnSelectionChangedListener;

    public RelativeLayout(Context context) {
        super(context);
        init(context, null, 0, 0);
    }

    public RelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0, 0);
    }

    public RelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr, 0);
    }

    public RelativeLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr);

        init(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        applyStyle(context, attrs, defStyleAttr, defStyleRes);
    }


    public void applyStyle(int resId) {
        applyStyle(getContext(), null, 0, resId);
    }

    private void applyStyle(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        getRippleManager().onCreate(this, context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void setBackgroundDrawable(Drawable drawable) {
        Drawable background = getBackground();
        if (background instanceof RippleDrawable && !(drawable instanceof RippleDrawable))
            ((RippleDrawable) background).setBackgroundDrawable(drawable);
        else
            super.setBackgroundDrawable(drawable);
    }

    protected RippleManager getRippleManager() {
        if (mRippleManager == null) {
            synchronized (RippleManager.class) {
                if (mRippleManager == null)
                    mRippleManager = new RippleManager();
            }
        }

        return mRippleManager;
    }

    @Override
    public void setOnClickListener(OnClickListener l) {
        RippleManager rippleManager = getRippleManager();
        if (l == rippleManager)
            super.setOnClickListener(l);
        else {
            rippleManager.setOnClickListener(l);
            setOnClickListener(rippleManager);
        }
    }

    @Override
    public boolean onTouchEvent(@NonNull MotionEvent event) {
        boolean result = super.onTouchEvent(event);
        boolean res = getRippleManager().onTouchEvent(event) || result;
        return res;
    }

    public void setOnSelectionChangedListener(OnSelectionChangedListener listener) {
        mOnSelectionChangedListener = listener;
    }

//    @Override
//    protected void onSelectionChanged(int selStart, int selEnd) {
//        super.onSelectionChanged(selStart, selEnd);
//
//        if (mOnSelectionChangedListener != null)
//            mOnSelectionChangedListener.onSelectionChanged(this, selStart, selEnd);
//    }


}
