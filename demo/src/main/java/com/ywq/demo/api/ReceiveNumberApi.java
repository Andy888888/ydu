package com.ywq.demo.api;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.custom.RequestApi;
import com.android.volley.custom.ResponseListener;
import com.ywq.demo.bean.ReceiveNumberBean;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yanwenqiang
 * @Date 15-7-23
 * @description 该接口是在网络送号方式呼叫中，接收客户端发送来的经纪人员工编号和被叫号码，根据员工编号找到注册的主叫号码
 */
public class ReceiveNumberApi extends RequestApi {
    private ReceiveHeaderReq header;
    private ReceiveBodyReq body;

    public ReceiveHeaderReq getHeader() {
        return header;
    }

    public void setHeader(ReceiveHeaderReq header) {
        this.header = header;
    }

    public ReceiveBodyReq getBody() {
        return body;
    }

    public void setBody(ReceiveBodyReq body) {
        this.body = body;
    }

    public ReceiveNumberApi(Context context, ResponseListener responseListener) {
        super(context, responseListener);
    }

    @Override
    public String getUrl() {
        return "http://220.194.66.24:50580/SSMN_ZY_Server/ReceiveNumber.do";
    }

//    {
//        “header”: {
//        “empNo”:”员工编号, 字符串型”,
//        “token”:”MD5(empNo + key) , 字符串型”
//    },
//        ”body”:{
//        “calledNumber”:” 被叫号码,字符型”
//    }
//    }


    @Override
    public Map<String, String> getHeaders() {
//        Map<String, String> header = new HashMap<>();
//        header.put("empNo", "2015020036");
//        header.put("token", MD5.md5("2015020036dascom"));
        return null;
    }

    @Override
    public Object getParams() {
        Map<String, Object> map = new HashMap<>();
        map.put("header", header);
        map.put("body", body);
        return map;
    }

    @Override
    public Class<?> getBean() {
        return ReceiveNumberBean.class;
    }

    @Override
    public int getMethod() {
        return Request.Method.POST;
    }
}
