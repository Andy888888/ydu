package com.ywq.ydu.application;

import android.app.Application;

import com.iflytek.cloud.SpeechUtility;
import com.ywq.ylib.request.MyVolley;
import com.ywq.ylib.utils.NetWorkChangeReceiver;
import com.ywq.ylib.utils.YLog;

/**
 * @author yanwenqiang
 * @Date 15-7-5
 * @description 待描述
 */
public class YduApplication extends Application {
    @Override
    public void onCreate() {

        // 应用程序入口处调用，避免手机内存过小，杀死后台进程后通过历史intent进入Activity造成SpeechUtility对象为null
        // 如在Application中调用初始化，需要在Mainifest中注册该Applicaiton
        // 注意：此接口在非主进程调用会返回null对象，如需在非主进程使用语音功能，请增加参数：SpeechConstant.FORCE_LOGIN+"=true"
        // 参数间使用半角“,”分隔。
        // 设置你申请的应用appid,请勿在'='与appid之间添加空格及空转义符
        SpeechUtility.createUtility(this, "appid=55b721ee");

        // 以下语句用于设置日志开关（默认开启），设置成false时关闭语音云SDK日志打印
//         Setting.showLogcat(false);

        super.onCreate();

        //打开调试
        YLog.setDebug(true);

        MyVolley.init(getApplicationContext());

        NetWorkChangeReceiver netWorkChangeReceiver=new NetWorkChangeReceiver(getApplicationContext());
        netWorkChangeReceiver.startListener();
    }
}
