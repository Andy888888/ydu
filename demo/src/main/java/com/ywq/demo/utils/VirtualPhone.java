package com.ywq.demo.utils;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.custom.RequestApi;
import com.android.volley.custom.ResponseListener;
import com.ywq.demo.api.ReceiveBodyReq;
import com.ywq.demo.api.ReceiveHeaderReq;
import com.ywq.demo.api.ReceiveNumberApi;
import com.ywq.demo.bean.ReceiveNumberBean;
import com.ywq.ylib.request.GsonRequest;
import com.ywq.ylib.request.MyVolley;
import com.ywq.ylib.utils.MD5;
import com.ywq.ylib.utils.YLog;

/**
 * @author yanwenqiang
 * @Date 15-7-23
 * @description 拨打虚拟号码
 */
public class VirtualPhone implements ResponseListener {

    public interface GetVirtualListener {
        void getVirtual(ReceiveNumberBean bean);
    }

    private GetVirtualListener mGetVirtualListener;

    public void setGetVirtualListener(GetVirtualListener mGetVirtualListener) {
        this.mGetVirtualListener = mGetVirtualListener;
    }

    public static final String DAS_CALL_KEY = "dascom";//虚拟号接入码
    public static final String DAS_CALL_NUMBER = "13212142740";//虚拟号接入码
    public static final Integer CALL_VIRTUAL = 1;

    private Context mContext;
    /**
     * 请求队列
     */
    protected RequestQueue mRequestQueue;

    public VirtualPhone(Context context) {
        this.mContext = context;
        mRequestQueue = MyVolley.getRequestQueue();
    }


    public void call(String userNo, String callNumber) {
        ReceiveHeaderReq header = new ReceiveHeaderReq();
//        userNo = "tj12090091";
        header.setEmpNo(userNo);
        header.setToken(MD5.md5(userNo + DAS_CALL_KEY));
        ReceiveBodyReq body = new ReceiveBodyReq();
        body.setCalledNumber(callNumber);

        ReceiveNumberApi receiveNumberApi = new ReceiveNumberApi(mContext, this);
        receiveNumberApi.setHeader(header);
        receiveNumberApi.setBody(body);
        request(receiveNumberApi);
    }

    private void callPhone(String masterNum) {

        Toast.makeText(mContext, masterNum, Toast.LENGTH_SHORT).show();

//        TelephonyManager tm = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
//        String otel = tm.getLine1Number();
//        if (otel == null) {
//            Toast.makeText(mContext, "请插入手机卡", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + DAS_CALL_NUMBER));
//        mContext.startActivity(intent);

    }

    private void request(RequestApi requestApi) {
        mRequestQueue.add(new GsonRequest(requestApi));
    }

    @Override
    public void response(Object obj) {
        if (obj instanceof ReceiveNumberBean) {
            ReceiveNumberBean receiveNumberBean = (ReceiveNumberBean) obj;

            //成功
            if (receiveNumberBean.getHeader().getResultCode().equals("0000")) {
                //callPhone(receiveNumberBean.getBody().getMsisdn());
                if (mGetVirtualListener != null) {
                    mGetVirtualListener.getVirtual(receiveNumberBean);
                }
            } else {
                //todo:虚拟号服务商的反馈提示
                YLog.alert(mContext, receiveNumberBean.getHeader().getDiagnostic());
            }
            return;
        }
    }

    @Override
    public void responseError(VolleyError volleyError) {
        YLog.alert(mContext, "访问虚拟号服务商接口失败");
    }


}
