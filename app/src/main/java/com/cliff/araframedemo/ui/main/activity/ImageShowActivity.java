package com.cliff.araframedemo.ui.main.activity;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.view.View;

import com.cliff.araframedemo.R;
import com.cliff.araframedemo.common.Config;
import com.cliff.hsj.ui.BaseActivity;
import com.cliff.hsj.utils.ImageUtils;
import com.polites.android.GestureImageView;
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

/**
 * @Description
 * @File ${FILE_NAME}
 * @Package com.martin.ionichinabystudio.ui.activity
 * @Date 2015/7/1316:19
 * @Author Donghongyu 1358506549@qq.com
 * @Version v1.0.0
 */

@EActivity(R.layout.activity_img_show)
public class ImageShowActivity extends BaseActivity {

    @ViewById(R.id.image)
    GestureImageView mGestureImageView;


    @Extra(Config.IMG_BIG_SHOW)
    String url;

    private Bitmap mBitmap;


    @AfterViews
    public void init() {
        Picasso.with(this).load(url).into(mGestureImageView);
//        new DownloadImgTask().execute();
    }

    /**
     * 点击下载按钮
     *
     * @param view
     */
    public void downloadImg(View view) {
        mGestureImageView.setDrawingCacheEnabled(true);
//        if (FileUtil.writeSDcard(url, mGestureImageView.getDrawingCache()))
//        {
//            Toast.makeText(getApplicationContext(), "保存成功", Toast.LENGTH_SHORT).show();
//        } else
//        {
//            Toast.makeText(getApplicationContext(), "保存失败", Toast.LENGTH_SHORT).show();
//        }
        mGestureImageView.setDrawingCacheEnabled(false);
    }

//    class DownloadImgTask extends AsyncTask<Void, Void, Void> {
//        @Override
//        protected Void doInBackground(Void... params) {
//            mBitmap = ImageUtils.getBitmap(url, ImageUtils.ORIGINALIMG, 0, 0);
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Void result) {
////            mGestureImageView.setImageBitmap(mBitmap);
//            super.onPostExecute(result);
//        }
//
//    }
}
