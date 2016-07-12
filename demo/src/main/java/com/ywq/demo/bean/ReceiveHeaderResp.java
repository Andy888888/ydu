package com.ywq.demo.bean;

/**
 * @author yanwenqiang
 * @Date 15-7-23
 * @description 待描述
 */
public class ReceiveHeaderResp {
    //返回码，失败时diagnostic为失败的诊断信息
    private String resultCode;
    //失败时的诊断信息，成功时空。
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
