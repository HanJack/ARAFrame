package com.cliff.araframedemo.ui.main.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import java.util.List;

/**
 * 自定义的显示一组图片的控件
 * Created by justo on 2015/7/15.
 */
public class ImageGroupView extends LinearLayout {
    private Context mContext;
    private List<String> mImgUrls;


    public ImageGroupView(Context context) {
        this(context, null);
    }

    public ImageGroupView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ImageGroupView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
    }

    public void setImages(List<String> imgUrls) {
        this.removeAllViews();
        if (imgUrls != null) {
            mImgUrls = imgUrls;
            for (final String imgURL : imgUrls) {
//                NetworkImageView imageView = new NetworkImageView(mContext);
//                imageView.setAdjustViewBounds(true);
//                imageView.setTag(imgURL);
//                imageView.setDefaultImageResId(R.mipmap.ic_launcher);
//                imageView.setErrorImageResId(R.mipmap.ic_launcher);
//                imageView.setImageUrl(imgURL, mImageLoader);
//                imageView.setOnClickListener(new OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Intent intent = new Intent(mContext, ImageShowActivity_.class);
//                        intent.putExtra(Constant.IMG_BIG_SHOW, imgURL);
//                        mContext.startActivity(intent);
//                    }
//                });
//                this.addView(imageView);
            }
        }
    }


}
