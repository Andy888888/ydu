package com.ywq.ylib.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.widget.Toast;

import com.ywq.ylib.provider.NetWorkProvider;

/**
 * @author yanwenqiang
 * @Date 15-7-23
 * @description 待描述
 */
public class NetWorkChangeReceiver {

    Context mContext;

    public NetWorkChangeReceiver(Context context){
        mContext=context;
    }

    public interface changedListener{
        void changeed();
    }

    mBroadcastReceiver netWorkReceiver=new mBroadcastReceiver();


    public void startListener(){

        netWorkReceiver.setMchangedListener(new changedListener() {
            @Override
            public void changeed() {
                Toast.makeText(mContext,"网络发生了改变",Toast.LENGTH_LONG).show();
                NetWorkProvider.checkNetWork(mContext);
            }
        });

        // 注册网络状态监听
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        mContext.registerReceiver(netWorkReceiver, intentFilter);
    }




    /**
     * 网络变化监听 *
     */
    class mBroadcastReceiver extends BroadcastReceiver {

        private changedListener mchangedListener;
        public void setMchangedListener(changedListener mchangedListener){
            this.mchangedListener=mchangedListener;
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            if(mchangedListener!=null) {
                mchangedListener.changeed();
            }
        }
    }


}
