package com.chengdai.cddata.models.certification.models;

/**
 * Created by 李先俊 on 2017/7/27.
 */

public class GetFraudNumModel {

    private String bizNo;
    private String score;

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getBizNo() {

        return bizNo;
    }

    public void setBizNo(String bizNo) {
        this.bizNo = bizNo;
    }
}
