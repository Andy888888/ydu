package com.ywq.ylib.widget;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

import com.ywq.ylib.R;

/**
 * @author yanwenqiang
 * @Date 15-7-5
 * @description 加载对话框
 */
public class LoadingDialog extends AlertDialog {
    private TextView tips_loading_msg;

    private CharSequence mMessage = null;

    public LoadingDialog(Context context) {
        this(context, null);
    }

    public LoadingDialog(Context context, String message) {
        this(context, message, R.style.LoadingDialog);
        if (TextUtils.isEmpty(message)) {
            mMessage = getContext().getResources().getString(
                    R.string.loading);
        } else {
            mMessage = message;
        }
    }

    public LoadingDialog(Context context, String message, int theme) {
        super(context, theme);
        this.setCancelable(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.tip_loading);
        tips_loading_msg = (TextView) findViewById(R.id.tips_loading_msg);
        tips_loading_msg.setText(mMessage);
    }

    @Override
    public void setMessage(CharSequence message) {
        mMessage = message;
        if (tips_loading_msg != null) {
            tips_loading_msg.setText(message);
        }
    }

    @Override
    public void setCancelable(boolean flag) {
        super.setCancelable(flag);
    }
}
