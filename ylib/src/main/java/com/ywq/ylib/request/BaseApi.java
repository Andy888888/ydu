package com.ywq.ylib.request;

import android.content.Context;

import com.android.volley.custom.RequestApi;
import com.android.volley.custom.ResponseListener;

/**
 * @author yanwenqiang
 * @Date 15-7-23
 * @description 请求api基类
 */
public abstract class BaseApi extends RequestApi {

    public BaseApi(Context context, ResponseListener responseListener) {
        super(context, responseListener);
    }

    public abstract String getBaseUrl();

    public abstract String getPath();

    @Override
    public String getUrl() {
        return getBaseUrl() + getPath();
    }
}

