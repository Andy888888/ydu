package com.ywq.demo.api;

/**
 * @author yanwenqiang
 * @Date 15-7-23
 * @description 接收主叫号码
 */
public class ReceiveHeaderReq {
    //员工编号, 字符串型
    private String empNo;
    //MD5(empNo + key) , 字符串型
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
