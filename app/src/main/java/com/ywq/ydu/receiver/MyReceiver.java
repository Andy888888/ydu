package com.ywq.ydu.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * @author yanwenqiang
 * @Date 15-7-5
 * @description 待描述
 */
public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_WALLPAPER_CHANGED)) {
            Toast.makeText(context, "你他妈的敢给老子换壁纸，不想活了，快给老子换回来！", Toast.LENGTH_LONG).show();
        }
    }
}
