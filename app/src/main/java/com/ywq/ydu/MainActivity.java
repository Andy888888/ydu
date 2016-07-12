package com.ywq.ydu;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatTextView;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.LinearLayout;

import com.ywq.ydu.activity.VoiceRecognizeActivity;
import com.ywq.ydu.api.DASRequestHeader;
import com.ywq.ydu.api.GetPhoneNumberApi;
import com.ywq.ydu.api.GetPhoneNumberParam;
import com.ywq.ydu.bean.DASBodyBean;
import com.ywq.ydu.bean.GetPhoneNumberBean;
import com.ywq.ydu.widget.MyBadgeView;
import com.ywq.ylib.base.NetWorkActivity;
import com.ywq.ywidgets.common.BadgeView;

/**
 * @author yanwenqiang
 * @Date 15-7-5
 * @description 主界面
 */
public class MainActivity extends NetWorkActivity implements View.OnClickListener {

    private AppCompatTextView tv_main;
    private LinearLayout ll_title;
    private MyBadgeView badgeView;
    private AppCompatTextView atv_other;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_main;
    }


    @Override
    protected void initView() {
        tv_main = (AppCompatTextView) findViewById(R.id.tv_main);
        ll_title = (LinearLayout) findViewById(R.id.ll_title);
        atv_other = (AppCompatTextView) findViewById(R.id.atv_other);

        badgeView = new MyBadgeView(this);
        setBadge(badgeView);

        setToolbar(R.id.toolbar);
        setToolbar("首页", false);

    }

    @Override
    protected void appendEvents() {
        badgeView.setTargetView(ll_title);
        badgeView.setBadgeCount(5);

        tv_main.setOnClickListener(this);
        atv_other.setOnClickListener(this);


    }

    private void setBadge(BadgeView badgeView) {
        badgeView.setBadgeMargin(0, 10, 10, 0);
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        switch (viewId) {
            case R.id.tv_main:
                tv_click();
                break;
            case R.id.atv_other:
//                requestGetPhone();
//                loadingDialog();
                break;
        }
    }

    private void requestGetPhone() {
        DASRequestHeader header = new DASRequestHeader("2015020036");

        GetPhoneNumberParam getPhoneNumberParam = new GetPhoneNumberParam();
        getPhoneNumberParam.setHeader(header);

        GetPhoneNumberApi getPhoneNumberApi = new GetPhoneNumberApi(this, this);
        getPhoneNumberApi.setParam(getPhoneNumberParam);

        request(getPhoneNumberApi);
    }

    @Override
    public void response(Object obj) {
        super.response(obj);
        if (obj instanceof GetPhoneNumberBean) {
            GetPhoneNumberBean getPhoneNumberBean = (GetPhoneNumberBean) obj;
            DASBodyBean bodyBean = getPhoneNumberBean.getBody().get(0);

            cancelLoadingDiloag();

            new AlertDialog.Builder(this)
                    .setMessage("主号码：" + bodyBean.getMsisdn() + "\n"
                            + "副号码：" + bodyBean.getSsmnNumber()).show();
        }
    }

    private void tv_click() {
//
//        String tel=getSimNo(this);
//        if (TextUtils.isEmpty(tel)) {R
//            tel="未能获取到手机号码";
//        }
//        tv_main.setText(tel);


        badgeView.setBadgeCount(0);
        loadingDialog();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                cancelLoadingDiloag();
                //Intent intent = new Intent(MainActivity.this, JniActivity.class);
                Intent intent = new Intent(MainActivity.this, VoiceRecognizeActivity.class);
                startActivity(intent);
            }
        }, 1000);
    }

    private String getSimNo(Context mContext) {
        TelephonyManager tm = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
        String otel = tm.getLine1Number();

        return otel;
    }


}
