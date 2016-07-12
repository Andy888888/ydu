package com.ywq.ylib.request;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.custom.RequestApi;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.ywq.ylib.utils.GZipUtil;
import com.ywq.ylib.utils.YLog;

import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.Map;

/**
 * @author yanwenqiang
 * @Date 15-7-23
 * @description Gson请求，在{@link com.android.volley.toolbox.StringRequest}基础上添加Gson解析
 */
public class GsonRequest extends Request<String> {
    private final Response.Listener<String> mListener;
    protected static final Gson mGson = new Gson();

    public GsonRequest(final RequestApi requestApi) {
        this(requestApi, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                if (requestApi.getResponseListener() != null) {
                    try {
                        if (requestApi.getBean() == null) {
                            requestApi.getResponseListener().response(
                                    response);
                        } else {
                            requestApi.getResponseListener().response(
                                    mGson.fromJson(response,
                                            requestApi.getBean()));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        requestApi.getResponseListener().response(response);
                    }
                } else {
                    YLog.p("requestListener == null");
                }
            }
        });

        // log
        YLog.p("request Mehtod = " + requestApi.getMethod());
        YLog.p("request URL = " + requestApi.getRealUrl());
        YLog.p("headers = " + mGson.toJson(requestApi.getHeaders()));
        if (requestApi.getParams() != null) {
            YLog.p("params = " + mGson.toJson(requestApi.getParams()));
        }

    }

    private GsonRequest(RequestApi requestApi,
                        final Response.Listener<String> listener) {
        super(requestApi);
        mListener = listener;
        YLog.p(requestApi.getTag());
        setTag(requestApi.getTag());
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        if (headers == null || headers.isEmpty()) {
            return Collections.emptyMap();
        } else {
            return headers;
        }
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        if (params != null) {
            try {
                return mGson.toJson(params).getBytes(getParamsEncoding());
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException("Encoding not supported");
            }
        }
        return null;
    }

    @Override
    protected void deliverResponse(String arg0) {
        mListener.onResponse(arg0);
    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse arg0) {
        String parsed;
        try {
            parsed = GZipUtil.decompressGZip(arg0.data);
        } catch (Exception e) {
            parsed = new String(arg0.data);
        }
        YLog.p(parsed);
        return Response.success(parsed,
                HttpHeaderParser.parseCacheHeaders(arg0));
    }
}
