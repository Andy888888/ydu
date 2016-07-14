package com.ywq.ydu.activity;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import com.ywq.demo.bean.ReceiveNumberBean;
import com.ywq.demo.utils.VirtualPhone;
import com.ywq.ydu.R;
import com.ywq.ylib.base.BaseActivity;

/**
 * @author yanwenqiang
 * @Date 15-7-11
 * @description 待描述
 */
public class JniActivity extends BaseActivity implements View.OnClickListener, VirtualPhone.GetVirtualListener {

    private Button btn_jni;
    private AppCompatEditText atv_staff;
    private AppCompatTextView atv_staff_phone;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_jni;
    }

    @Override
    protected void initView() {

        setToolbar(R.id.toolbar);
        setToolbar("获取手机号码", true);

        btn_jni = (Button) findViewById(R.id.btn_jni);
        atv_staff = (AppCompatEditText) findViewById(R.id.aet_staff);
        atv_staff_phone = (AppCompatTextView) findViewById(R.id.atv_staff_phone);
    }

    @Override
    protected void appendEvents() {
        btn_jni.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        int viewId = v.getId();
        if (viewId == R.id.btn_jni) {
            jniBtn_click();
        }
    }

    private void jniBtn_click() {
        String staff = atv_staff.getText().toString().trim();
        if(TextUtils.isEmpty(staff))
        {
            toast("请输入员工号!");
            return;
        }
        VirtualPhone virtualPhone = new VirtualPhone(this);
        virtualPhone.setGetVirtualListener(this);
        virtualPhone.call(staff, "15699888656");
    }

    @Override
    public void getVirtual(ReceiveNumberBean bean) {
        atv_staff_phone.setText("员工手机号："+bean.getBody().getMsisdn());
    }

    class A implements Thread.UncaughtExceptionHandler {

        @Override
        public void uncaughtException(Thread thread, Throwable ex) {

        }
    }
}

