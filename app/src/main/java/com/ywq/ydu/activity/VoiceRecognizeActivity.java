package com.ywq.ydu.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.ui.RecognizerDialogListener;
import com.ywq.demo.utils.SpeechUtil;
import com.ywq.ydu.R;
import com.ywq.ylib.base.BaseActivity;

/**
 * @author yanwenqiang
 * @Date 15-7-29
 * @description 待描述
 */
public class VoiceRecognizeActivity extends BaseActivity implements View.OnClickListener, RecognizerDialogListener {

    private Button btn_start;
    private EditText et_result;
    private SpeechUtil speechUtil;


    @Override
    protected int setLayoutId() {
        return R.layout.activity_voice;
    }

    @Override
    protected void initView() {
        et_result = (EditText) findViewById(R.id.et_result);
        btn_start = (Button) findViewById(R.id.btn_start);
    }

    @Override
    protected void appendEvents() {
        btn_start.setOnClickListener(this);
        speechUtil = new SpeechUtil(this, this);
    }

    @Override
    protected void onDestroy() {
        speechUtil.destory();
        super.onDestroy();
    }


    @Override
    public void onClick(View v) {
        speechUtil.startListen();
    }

    /**
     * 识别回调错误.
     */
    @Override
    public void onResult(RecognizerResult recognizerResult, boolean b) {
        String result = speechUtil.paseResult(recognizerResult);
        et_result.setText(result.toString());
        et_result.setSelection(et_result.length());
    }

    @Override
    public void onError(SpeechError speechError) {

    }
}
