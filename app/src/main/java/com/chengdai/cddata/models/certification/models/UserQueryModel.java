package com.chengdai.cddata.models.certification.models;

/**
 * Created by 李先俊 on 2017/7/26.
 */

public class UserQueryModel {


    /**
     * authorized : false
     * openId : 34234354353543543
     */

    private boolean authorized;
    private String openId;

    public boolean isAuthorized() {
        return authorized;
    }

    public void setAuthorized(boolean authorized) {
        this.authorized = authorized;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }
}
