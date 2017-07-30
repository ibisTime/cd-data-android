package com.chengdai.cddata.models.certification.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by 李先俊 on 2017/7/27.
 */

public class FraudLookModel implements Parcelable {

    private String bizNo;
    private String hit;
    private List<String> riskCodeList;
    private List<String> riskInofList;
    public String getHit() {
        return hit;
    }

    public List<String> getRiskInofList() {
        return riskInofList;
    }

    public void setRiskInofList(List<String> riskInofList) {
        this.riskInofList = riskInofList;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.bizNo);
        dest.writeString(this.hit);
        dest.writeStringList(this.riskCodeList);
        dest.writeStringList(this.riskInofList);
    }

    public FraudLookModel() {
    }

    protected FraudLookModel(Parcel in) {
        this.bizNo = in.readString();
        this.hit = in.readString();
        this.riskCodeList = in.createStringArrayList();
        this.riskInofList = in.createStringArrayList();
    }

    public static final Parcelable.Creator<FraudLookModel> CREATOR = new Parcelable.Creator<FraudLookModel>() {
        @Override
        public FraudLookModel createFromParcel(Parcel source) {
            return new FraudLookModel(source);
        }

        @Override
        public FraudLookModel[] newArray(int size) {
            return new FraudLookModel[size];
        }
    };
}
