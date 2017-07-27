package com.chengdai.cddata.models.certification.models;

import java.util.List;

/**
 * Created by 李先俊 on 2017/7/27.
 */

public class FraudNumCheckModel {

    private String bizNo;
    private List<String> verifyCodeList;

    public List<String> getVerifyCodeList() {
        return verifyCodeList;
    }

    public void setVerifyCodeList(List<String> verifyCodeList) {
        this.verifyCodeList = verifyCodeList;
    }

    public String getBizNo() {

        return bizNo;
    }

    public void setBizNo(String bizNo) {
        this.bizNo = bizNo;
    }
}
