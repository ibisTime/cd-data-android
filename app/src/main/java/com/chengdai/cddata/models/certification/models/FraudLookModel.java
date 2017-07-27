package com.chengdai.cddata.models.certification.models;

import java.util.List;

/**
 * Created by 李先俊 on 2017/7/27.
 */

public class FraudLookModel {

    private String bizNo;
    private String hit;
    private List<String> riskCodeList;
    public String getHit() {
        return hit;
    }

    public void setHit(String hit) {
        this.hit = hit;
    }

    public List<String> getRiskCodeList() {
        return riskCodeList;
    }

    public void setRiskCodeList(List<String> riskCodeList) {
        this.riskCodeList = riskCodeList;
    }


    public String getBizNo() {

        return bizNo;
    }

    public void setBizNo(String bizNo) {
        this.bizNo = bizNo;
    }
}
