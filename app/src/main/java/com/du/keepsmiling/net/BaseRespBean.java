package com.du.keepsmiling.net;

/**
 * ClassName: annerViewLayout
 * Function: Banner
 * date: 2018/5/24.
 * author dushiguang
 * version 0.1
 * Copyright (c) 2017, www.leadfund.com.cn All Rights Reserved.
 * 上海利得金融科技集团版权所有.
 */
public class BaseRespBean {
    private String respCode;//": "000",
    private String respMessage;//": "??",
    private String errorCode;//": null,
    private String errorMessage;//": null,
    private Object data;

    public String getRespCode() {
        return respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    public String getRespMessage() {
        return respMessage;
    }

    public void setRespMessage(String respMessage) {
        this.respMessage = respMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }


    @Override
    public String toString() {
        return "BaseRespBean{" +
                "respCode='" + respCode + '\'' +
                ", respMessage='" + respMessage + '\'' +
                ", errorCode='" + errorCode + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                ", data=" + data +
                '}';
    }

    //定义 输出返回数据 的方法
    public void show() {

    }
}
