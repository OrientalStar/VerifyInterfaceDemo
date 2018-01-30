package com.demo.verify.http;

/**
 * Created by ponos.peng
 * 2017/8/11.
 * Description:接口返回数据封装
 */

public class ResultDesc {
//    private int status_code;//返回码
//    private String reason;//返回说明
    private String result;//返回数据

    public ResultDesc(  String result) {
//        this.status_code = status_code;
//        this.reason = reason;
        this.result = result;
    }

//    public int getStatus_code() {
//        return status_code;
//    }
//
//    public void setStatus_code(int status_code) {
//        this.status_code = status_code;
//    }

//    public String getReason() {
//        return reason;
//    }
//
//    public void setReason(String reason) {
//        this.reason = reason;
//    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "ResultDesc{" +
                ", result='" + result + '\'' +
                '}';
    }
}
