package com.ywq.ylib.utils;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author yanwenqiang
 * @Date 15-7-5
 * @description 日志工具
 */
public class YLog {
    private static boolean debug = false;
    private static String mTag = "YLog";

    public static boolean isDebug() {
        return debug;
    }

    public static void setDebug(boolean debug) {
        YLog.debug = debug;
    }

    public static void p(String tag, String msg) {
        if (!debug) {
            return;
        }
        p(tag + "：" + msg);
    }

    public static void p(String msg) {
        if (!debug) {
            return;
        }
        Log.d(mTag, msg);
    }

    public static void p(Object msg) {
        if (!debug) {
            return;
        }
        Log.d(mTag, msg.toString());
    }

    public static void alert(Context context, String msg) {
        if (!debug) {
            return;
        }
        new AlertDialog.Builder(context)
                .setTitle(mTag)
                .setMessage(msg)
                .show();
    }

    public static void toast(Context context, String msg) {
        if (!debug) {
            return;
        }
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public static void write(Context context, String log) {
        if (!debug) {
            return;
        }
        try {
            //todo：这一句有问题，在没有sd卡的手机上会闪退
            String rootPath = StorageUtil.getExternalDir("YLog").toString();
            String path = rootPath + "/agencyLog.txt";

            String writeLog = getCurrentDateTime() + ": " + log + "\r\n";

            FileWriter fw = new FileWriter(path, true);
            fw.append(writeLog);
            fw.close();
        } catch (IOException ex) {
            System.out.println("FileWriter error");
            ex.printStackTrace();
        }
    }

    private static String getCurrentDateTime() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        return df.format(new Date());// new Date()为获取当前系统时间
    }
}
