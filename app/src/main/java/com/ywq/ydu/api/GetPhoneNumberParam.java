package com.ywq.ydu.api;

/**
 * @author yanwenqiang
 * @Date 15-8-18
 * @description 待描述
 */
public class GetPhoneNumberParam {
    private DASRequestHeader header;
    private String body;

    public DASRequestHeader getHeader() {
        return header;
    }

    public void setHeader(DASRequestHeader header) {
        this.header = header;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
