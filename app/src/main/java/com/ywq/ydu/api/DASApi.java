package com.ywq.ydu.api;

import android.content.Context;

import com.android.volley.custom.ResponseListener;

import java.util.Map;

/**
 * @author yanwenqiang
 * @Date 15-8-18
 * @description 待描述
 */
public abstract class DASApi extends BaseApi {
    public DASApi(Context context, ResponseListener responseListener) {
        super(context, responseListener);
    }

    @Override
    public String getBaseUrl() {
        return "http://220.194.66.22/SSMN_ZY_Server_1.0.2_PR2/";
    }


    @Override
    public Map<String, String> getHeaders() {
        return null;
    }

}
