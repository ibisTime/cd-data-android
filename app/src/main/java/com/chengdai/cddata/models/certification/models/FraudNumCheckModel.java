package com.chengdai.cddata.models.certification.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by 李先俊 on 2017/7/27.
 */

public class FraudNumCheckModel implements Parcelable {

    private String bizNo;
    private List<String> verifyCodeList;
    private List<String> verifyInfoList;

    public List<String> getVerifyInfoList() {
        return verifyInfoList;
    }

    public void setVerifyInfoList(List<String> verifyInfoList) {
        this.verifyInfoList = verifyInfoList;
    }

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


    public FraudNumCheckModel() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.bizNo);
        dest.writeStringList(this.verifyCodeList);
        dest.writeStringList(this.verifyInfoList);
    }

    protected FraudNumCheckModel(Parcel in) {
        this.bizNo = in.readString();
        this.verifyCodeList = in.createStringArrayList();
        this.verifyInfoList = in.createStringArrayList();
    }

    public static final Parcelable.Creator<FraudNumCheckModel> CREATOR = new Parcelable.Creator<FraudNumCheckModel>() {
        @Override
        public FraudNumCheckModel createFromParcel(Parcel source) {
            return new FraudNumCheckModel(source);
        }

        @Override
        public FraudNumCheckModel[] newArray(int size) {
            return new FraudNumCheckModel[size];
        }
    };
}
