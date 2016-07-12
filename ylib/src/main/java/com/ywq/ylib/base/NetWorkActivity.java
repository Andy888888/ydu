package com.ywq.ylib.base;

import android.os.Bundle;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.custom.RequestApi;
import com.android.volley.custom.ResponseListener;
import com.ywq.ylib.R;
import com.ywq.ylib.provider.NetWorkProvider;
import com.ywq.ylib.request.GsonRequest;
import com.ywq.ylib.request.MyVolley;

/**
 * @author yanwenqiang
 * @Date 15-7-5
 * @description 有网络请求的Activity
 */
public abstract class NetWorkActivity extends BaseActivity implements ResponseListener {

    /**
     * 请求队列
     */
    protected RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mRequestQueue = MyVolley.getRequestQueue();
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        mRequestQueue.cancelAll(this.getClass().getName());
        super.onDestroy();
    }

    /**
     * 统一请求入口
     */
    protected void request(RequestApi requestApi) {
        mRequestQueue.add(new GsonRequest(requestApi));
    }

    @Override
    public void response(Object obj) {
    }

    @Override
    public void responseError(VolleyError volleyError) {
        cancelLoadingDiloag();
        toast(getString(R.string.load_error));
    }

    /**
     * 检查网络
     */
    protected boolean checkNetWork() {
        return checkNetWork(true);
    }

    /**
     * 检查网络
     *
     * @param showToast 是否显示网络toast
     */
    protected boolean checkNetWork(boolean showToast) {
        if (NetWorkProvider.netWorkEnable) {
            return true;
        } else if (showToast) {
            networkUnenable();
            return false;
        } else {
            return false;
        }
    }

    /**
     * 网络不给力
     */
    protected void networkUnenable() {
        toast(getString(R.string.network_unenable));
    }
}
