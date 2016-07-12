package com.ywq.ydu.api;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.custom.ResponseListener;
import com.ywq.ydu.bean.GetPhoneNumberBean;

/**
 * @author yanwenqiang
 * @Date 15-8-18
 * @description 待描述
 */
public class GetPhoneNumberApi extends DASApi {

    private GetPhoneNumberParam param;

    public GetPhoneNumberParam getParam() {
        return param;
    }

    public void setParam(GetPhoneNumberParam param) {
        this.param = param;
    }

    public GetPhoneNumberApi(Context context, ResponseListener responseListener) {
        super(context, responseListener);
    }

    @Override
    public String getPath() {
        return "GetSsmnNumber.do";
    }

    @Override
    public Object getParams() {
        return param;
    }

    @Override
    public Class<?> getBean() {
        return GetPhoneNumberBean.class;
    }

    @Override
    public int getMethod() {
        return Request.Method.POST;
    }
}
