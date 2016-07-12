package com.ywq.ylib.request;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * @author yanwenqiang
 * @Date 15-7-23
 * @description Volley请求队列
 */
public class MyVolley {
    private static RequestQueue REQUEST_QUEUE;

    public static void init(Context context) {
        REQUEST_QUEUE = Volley.newRequestQueue(context);
    }

    public static RequestQueue getRequestQueue() {
        if (REQUEST_QUEUE == null)
            throw new IllegalArgumentException("RequestQueue not initialized");
        return REQUEST_QUEUE;
    }
}
