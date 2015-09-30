/**
 * DongHongyu
 */
package com.cliff.hsj.ui;

import android.app.Activity;

import com.cliff.hsj.utils.LogUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 管理Activity的栈
 */
public class ActivityTack {
    /**
     * 日志的标记
     */
    public final String TAG = getClass().getName();
    /***/
    public static ActivityTack mActivityTask = new ActivityTack();

    /**
     * 存放应用开启点开的Activity
     */
    public List<Activity> mActivityList = new ArrayList<Activity>();

    public static ActivityTack getInstanse() {
        return mActivityTask;
    }

    /**
     * 向Activity堆栈添加实例<br>
     *
     * @param activity
     */
    public void addActivity(Activity activity) {
        LogUtils.i(TAG, TAG + "-----addActivity-----" + activity);
        mActivityList.add(activity);
    }

    /**
     * 移除指定的Activity实例
     *
     * @param activity
     */
    public void removeActivity(Activity activity) {
        LogUtils.i(TAG, TAG + "-----removeActivity-----" + activity);
        mActivityList.remove(activity);
    }

    /**
     * 完全退出应用程序
     */
    public void exitApp() {
        // 循环取出堆栈的Activity实例,依次销毁
        while (mActivityList.size() > 0) {
            mActivityList.get(mActivityList.size() - 1).finish();
        }
        LogUtils.i(TAG, TAG + "-----exitApp-----" + mActivityList.size());
        // 正常关闭程序
        System.exit(0);
    }

    /**
     * 根据Activity名称查询堆栈中是否已经注册该界面
     *
     * @param activityName
     * @return
     */
    public Activity getActivityByClassName(String activityName) {
        for (Activity ac : mActivityList) {
            if (ac.getClass().getName().indexOf(activityName) >= 0) {
                LogUtils
                        .i(TAG, TAG + "-----getActivityByClassName-----" + ac);
                return ac;
            }
        }
        return null;
    }

    /**
     * 根据class类进行获取
     *
     * @param clazz
     * @return
     */
    @SuppressWarnings("rawtypes")
    public Activity getActivityByClass(Class clazz) {
        for (Activity ac : mActivityList) {
            if (ac.getClass().equals(clazz)) {
                LogUtils.i(TAG, TAG + "-----getActivityByClass-----" + ac);
                return ac;
            }
        }
        return null;
    }

    /**
     * 弹出指定的Activity实例,即销毁指定的界面
     *
     * @param activity
     */
    public void popActivity(Activity activity) {
        removeActivity(activity);
        activity.finish();
    }

    /**
     * 弹出指定的Activity之前的实例
     *
     * @param clzz
     */
    @SuppressWarnings("rawtypes")
    public void popUntilActivity(Class... clzz) {
        List<Activity> list = new ArrayList<Activity>();
        for (int i = mActivityList.size() - 1; i >= 0; i--) {
            Activity ac = mActivityList.get(i);
            boolean isTop = false;
            for (int j = 0; j < clzz.length; j++) {
                if (ac.getClass().equals(clzz[j])) {
                    isTop = true;
                    break;
                }
            }
            if (!isTop) {
                list.add(ac);
            } else
                break;
        }
        for (Iterator<Activity> iterator = list.iterator(); iterator.hasNext(); ) {
            Activity activity = iterator.next();
            popActivity(activity);
        }
    }

}
