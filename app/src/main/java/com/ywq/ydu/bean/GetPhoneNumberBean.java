package com.ywq.ydu.bean;

import java.util.List;

/**
 * @author yanwenqiang
 * @Date 15-8-18
 * @description 待描述
 */
public class GetPhoneNumberBean {
    private DASHeaderBean header;
    private List<DASBodyBean> body;

    public DASHeaderBean getHeader() {
        return header;
    }

    public void setHeader(DASHeaderBean header) {
        this.header = header;
    }

    public List<DASBodyBean> getBody() {
        return body;
    }

    public void setBody(List<DASBodyBean> body) {
        this.body = body;
    }
}
