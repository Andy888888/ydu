package com.ywq.ylib.provider;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.IOException;

/**
 * @author yanwenqiang
 * @Date 15-7-5
 * @description 网络支持
 */
public final class NetWorkProvider {
    /**
     * 网络是否可用
     */
    public static boolean netWorkEnable;

    /**
     * 网络类型 none;mobile;wifi
     */
    public static String netWorkType;

    /**
     * wifi开关是否开启
     */
    public static boolean wifiSwicth;

    public static void checkNetWork(Context context) {
        netWorkEnable = ping();
        if (netWorkEnable) {
            ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = manager.getActiveNetworkInfo();
            if (networkInfo == null || networkInfo.isConnectedOrConnecting()) {
                netWorkType = networkInfo.getTypeName().equalsIgnoreCase("WIFI") ? "wifi" : "mobile";
            } else {
                netWorkType = "none";
            }
        } else {
            netWorkType = "none";
        }
    }

    public static void setWifiSwicth(boolean swicth) {
        wifiSwicth = swicth;
    }

    private static boolean ping() {
        final String IP = "www.baidu.com";
        try {
            Process process = Runtime.getRuntime().exec("ping -c 3 -w 100 " + IP);
            int status = process.waitFor();
            return status == 0;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

}
