package com.ywq.demo.bean;

/**
 * @author yanwenqiang
 * @Date 15-7-23
 * @description 待描述
 */
public class ReceiveNumberBean {
    //    {
//        “header”:{
//        “resultCode”:”返回码，失败时diagnostic为失败的诊断信息”,
//        “diagnostic”:”失败时的诊断信息，成功时空。”
//    },
//        ”body”:{
//        “msisdn”:”用户的号码（主叫）, 字符串型”,
//    }
//    }

    private ReceiveHeaderResp header;
    private ReceiveBodyResp body;


    public ReceiveHeaderResp getHeader() {
        return header;
    }

    public void setHeader(ReceiveHeaderResp header) {
        this.header = header;
    }

    public ReceiveBodyResp getBody() {
        return body;
    }

    public void setBody(ReceiveBodyResp body) {
        this.body = body;
    }
}
