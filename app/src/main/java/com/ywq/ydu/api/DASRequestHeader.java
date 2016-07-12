package com.ywq.ydu.api;

import com.ywq.ylib.utils.MD5;

/**
 * @author yanwenqiang
 * @Date 15-8-18
 * @description 待描述
 */
public class DASRequestHeader {

    public DASRequestHeader(String empNo) {
        this.empNo = empNo;
        this.setToken(MD5.md5(this.empNo + "dascom"));
    }

    //员工编号
    private String empNo;
    //token为 empNo＋key（dascom）
    private String token;

    public String getEmpNo() {
        return empNo;
    }

    public void setEmpNo(String empNo) {
        this.empNo = empNo;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
