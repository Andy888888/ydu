package com.ywq.ydu.api;

import android.content.Context;

import com.android.volley.custom.RequestApi;
import com.android.volley.custom.ResponseListener;

/**
 * @author yanwenqiang
 * @Date 15-8-18
 * @description 待描述
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
