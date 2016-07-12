package com.ywq.ydu.bean;

/**
 * @author yanwenqiang
 * @Date 15-8-18
 * @description 待描述
 */
public class DASHeaderBean {
    //返回码
    private String resultCode;
    //失败时的诊断信息
    private String diagnostic;

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getDiagnostic() {
        return diagnostic;
    }

    public void setDiagnostic(String diagnostic) {
        this.diagnostic = diagnostic;
    }
}
