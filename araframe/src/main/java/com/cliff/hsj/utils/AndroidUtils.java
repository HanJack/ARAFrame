/*
 * Copyright (C) 2015 Drakeet <drakeet.me@gmail.com>
 *
 * This file is part of Meizhi
 *
 * Meizhi is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Meizhi is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Meizhi.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.cliff.hsj.utils;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.widget.Toast;

import com.cliff.hsj.ui.headsup.HeadsUp;
import com.cliff.hsj.ui.headsup.HeadsUpManager;

import java.util.Calendar;

/**
 * 通用的工具方法
 */
public class AndroidUtils {

    /**
     * 拷贝字符串到剪切板
     *
     * @param context
     * @param text    拷贝内容
     * @param success 成功提示语
     */
    public static void copyToClipBoard(Context context, String text, String success) {
        ClipData clipData = ClipData.newPlainText("ara_copy", text);
        ClipboardManager manager = (ClipboardManager) context.getSystemService(
                Context.CLIPBOARD_SERVICE);
        manager.setPrimaryClip(clipData);
        ToastUtils.show(context, success, Toast.LENGTH_SHORT);
    }

    /**
     * 定时提醒注册
     *
     * @param context
     * @param receiver  广播接收器
     * @param filterStr 标识字符串
     */
    public static void registerAlarm(Context context, Class<?> receiver, String filterStr) {
        Calendar today = Calendar.getInstance();
        Calendar now = Calendar.getInstance();

        today.set(Calendar.HOUR_OF_DAY, 12);
        today.set(Calendar.MINUTE, 11);
        today.set(Calendar.SECOND, 38);

        if (now.after(today)) {
            return;
        }

        Intent intent = new Intent(filterStr);
        intent.setClass(context, receiver);

        PendingIntent broadcast = PendingIntent.getBroadcast(context, 520, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        manager.set(AlarmManager.RTC_WAKEUP, today.getTimeInMillis(), broadcast);
    }

    /**
     * 显示通知栏提示
     *
     * @param context
     * @param targetActivity 目标界面
     * @param title          标题
     * @param content        提示内容
     * @param largeIcon      提示图标
     * @param smallIcon      小图标
     * @param code           回调码
     */
    public static void showNotify(Context context, Class<?> targetActivity, String title, String content, int largeIcon, int smallIcon, int code) {
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 11,
                new Intent(context, targetActivity), PendingIntent.FLAG_UPDATE_CURRENT);
        HeadsUpManager manage = HeadsUpManager.getInstant(context);
        HeadsUp.Builder builder = new HeadsUp.Builder(context);
        builder.setContentTitle(title)
                .setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_LIGHTS)
                .setContentIntent(pendingIntent)
                .setFullScreenIntent(pendingIntent, false)
                .setAutoCancel(true)
                .setContentText(content);

        if (Build.VERSION.SDK_INT >= 21) {
            builder.setLargeIcon(
                    BitmapFactory.decodeResource(context.getResources(), largeIcon))
                    .setSmallIcon(smallIcon);
        } else {
            builder.setSmallIcon(largeIcon);
        }

        HeadsUp headsUp = builder.buildHeadUp();
        headsUp.setSticky(true);
        manage.notify(code, headsUp);
    }
}
