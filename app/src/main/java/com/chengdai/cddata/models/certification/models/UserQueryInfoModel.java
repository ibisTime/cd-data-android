package com.chengdai.cddata.models.certification.models;

/**
 * Created by 李先俊 on 2017/7/27.
 */

public class UserQueryInfoModel {


    /**
     * appId : 1004013
     * authorized : false
     * bizNo : ZM201707273000000951800468406324
     * param : E90ZUqxjDBkOS06
     * signature : OIfM%2F9EJtcKdrDd
     * zmScore : 808
     */

    private String appId;
    private boolean authorized;
    private String bizNo;
    private String param;
    private String signature;
    private String zmScore;

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

    public String getZmScore() {
        return zmScore;
    }

    public void setZmScore(String zmScore) {
        this.zmScore = zmScore;
    }
}
