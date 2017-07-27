package com.chengdai.cddata.models.certification.models;

/**
 * Created by 李先俊 on 2017/7/27.
 */

public class IndustryFocusOnModel {


    /**
     * appId : 1004013
     * authorized : false
     * bizNo : ZM201707273000000811800469127207
     * isMatched : false
     * param : E90ZUqxjDBkOS04XY7%2FJYs4C50lg8
     * signature : OIfM%2F9EJtcKdrDdW
     */

    private String appId;
    private boolean authorized;
    private String bizNo;
    private boolean isMatched;
    private String param;
    private String signature;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public boolean isAuthorized() {
        return authorized;
    }

    public void setAuthorized(boolean authorized) {
        this.authorized = authorized;
    }

    public String getBizNo() {
        return bizNo;
    }

    public void setBizNo(String bizNo) {
        this.bizNo = bizNo;
    }

    public boolean isIsMatched() {
        return isMatched;
    }

    public void setIsMatched(boolean isMatched) {
        this.isMatched = isMatched;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
