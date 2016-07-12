package com.ywq.ylib.base;


import android.app.AlertDialog;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.ywq.ylib.provider.NetWorkProvider;
import com.ywq.ylib.utils.YLog;
import com.ywq.ylib.widget.LoadingDialog;

/**
 * @author yanwenqiang
 * @Date 15-7-5
 * @description Activity基类
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected Toolbar mToolbar;
    /**
     * 加载框
     */
    protected AlertDialog loadingDialog;
    protected Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayoutId());
        initView();
        appendEvents();
        YLog.p("onCreate：" + this.getClass().getName());
    }

    /**
     * 设置页面ID
     */
    protected abstract int setLayoutId();

    /**
     * 初始化View
     */
    protected abstract void initView();


    /**
     * 追加事件
     */
    protected abstract void appendEvents();

    @Override
    public Resources getResources() {
        Resources resources = super.getResources();
        Configuration configuration = new Configuration();
        configuration.setToDefaults();
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
        return resources;
    }


    /**
     * 设置toolbarId
     */
    protected void setToolbar(int toolbarId) {
        mToolbar = (Toolbar) findViewById(toolbarId);
        setSupportActionBar(mToolbar);
    }

    /**
     * 设置页面标题
     */
    protected void setToolbar(int resourseId, boolean homeAsUp) {
        setToolbar(getString(resourseId), homeAsUp);
    }

    /**
     * 设置页面标题
     */
    protected void setToolbar(String title, boolean homeAsUp) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
            getSupportActionBar().setDisplayHomeAsUpEnabled(homeAsUp);
        }
    }

    /**
     * 设置页面标题
     */
    protected void setmToolbarTitle(String title) {
        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle(title);
    }


    @Override
    protected void onDestroy() {
        YLog.p("onDestroy: " + this.getClass().getName());
        super.onDestroy();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    protected void loadingDialog() {
        loadingDialog("");
    }

    /**
     * 自定义加载框
     */
    protected void loadingDialog(String message) {
        loadingDialog(message, false);
    }

    /**
     * 自定义加载框
     *
     * @param message    内容
     * @param cancelable 是否取消
     */
    protected void loadingDialog(String message, boolean cancelable) {
        if (loadingDialog == null)
            loadingDialog = new LoadingDialog(this);
        if (!TextUtils.isEmpty(message)) {
            loadingDialog.setMessage(message);
        }
        loadingDialog.setCancelable(cancelable);
        if (!loadingDialog.isShowing()) {
            loadingDialog.show();
        }
    }

    /**
     * 取消加载框
     */
    protected void cancelLoadingDiloag() {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.cancel();
            loadingDialog = null;
        }
    }

    /**
     * Toast统一入口
     */
    protected void toast(String msg) {
        if (toast == null) {
            toast = Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT);
            toast.show();
        } else {
            toast.setText(msg);
            toast.show();
        }
    }

    /**
     * 检查网络
     */
    protected boolean checkNetWork() {
        if (NetWorkProvider.netWorkEnable) {
            return true;
        } else {
            networkUnenable();
            return false;
        }
    }

    /**
     * 网络状态不好
     */
    protected void networkUnenable() {
        toast("网络不给力");
    }

    /**
     * 隐藏键盘
     */
    protected void hideSoftInPut(View view) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
