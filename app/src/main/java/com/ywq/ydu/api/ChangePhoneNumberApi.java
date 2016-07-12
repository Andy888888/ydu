package com.ywq.ydu.api;

import android.content.Context;

import com.android.volley.custom.ResponseListener;

/**
 * @author yanwenqiang
 * @Date 15-8-18
 * @description 待描述
 */
public class ChangePhoneNumberApi extends DASApi {
    public ChangePhoneNumberApi(Context context, ResponseListener responseListener) {
        super(context, responseListener);
    }

    @Override
    public String getPath() {
        return "ChangeMsisdn.do";
    }

    @Override
    public Object getParams() {
        return null;
    }

    @Override
    public Class<?> getBean() {
        return null;
    }
}
