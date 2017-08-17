package com.chengdai.cddata.models.certification.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 李先俊 on 2017/7/27.
 */

public class IndustryFocusOnModel implements Parcelable {


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
    private String param;
    private String signature;




    /**
     * authorized : true
     * isMatched : true
     * details : [{"bizCode":"AA","code":"AA001003","extendInfo":[{"description":"逾期金额（元）","key":"event_max_amt_code","value":"M02"},{"description":"编号","key":"id","value":"adda98968a9c3af8ceb41f19b13dab1b"},{"description":"最近一次违约时间","key":"event_end_time_desc","value":"2016-06"}],"level":2,"refreshTime":"Jul 28, 2017 12:00:00 AM","settlement":true,"type":"AA001"}]
     * bizNo : ZM201708023000000792100501929310
     */

    private boolean isMatched;
    private String bizNo;
//    private List<DetailsBean> details;
    private List<String> details;

    public boolean isMatched() {
        return isMatched;
    }

    public void setMatched(boolean matched) {
        isMatched = matched;
    }

    public List<String> getDetails() {
        return details;
    }

    public void setDetails(List<String> details) {
        this.details = details;
    }
/*    public List<DetailsBean> getDetails() {
        return details;
    }

    public void setDetails(List<DetailsBean> details) {
        this.details = details;
    }*/

    public static class DetailsBean {
        /**
         * bizCode : AA
         * code : AA001003
         * extendInfo : [{"description":"逾期金额（元）","key":"event_max_amt_code","value":"M02"},{"description":"编号","key":"id","value":"adda98968a9c3af8ceb41f19b13dab1b"},{"description":"最近一次违约时间","key":"event_end_time_desc","value":"2016-06"}]
         * level : 2
         * refreshTime : Jul 28, 2017 12:00:00 AM
         * settlement : true
         * type : AA001
         */

        private String bizCode;
        private String code;
        private int level;
        private String refreshTime;
        private boolean settlement;
        private String type;
        private List<ExtendInfoBean> extendInfo;

        public String getBizCode() {
            return bizCode;
        }

        public void setBizCode(String bizCode) {
            this.bizCode = bizCode;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public String getRefreshTime() {
            return refreshTime;
        }

        public void setRefreshTime(String refreshTime) {
            this.refreshTime = refreshTime;
        }

        public boolean isSettlement() {
            return settlement;
        }

        public void setSettlement(boolean settlement) {
            this.settlement = settlement;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public List<ExtendInfoBean> getExtendInfo() {
            return extendInfo;
        }

        public void setExtendInfo(List<ExtendInfoBean> extendInfo) {
            this.extendInfo = extendInfo;
        }

        public static class ExtendInfoBean {
            /**
             * description : 逾期金额（元）
             * key : event_max_amt_code
             * value : M02
             */

            private String description;
            private String key;
            private String value;

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getKey() {
                return key;
            }

            public void setKey(String key) {
                this.key = key;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }
        }
    }



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

    public IndustryFocusOnModel() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.appId);
        dest.writeByte(this.authorized ? (byte) 1 : (byte) 0);
        dest.writeString(this.param);
        dest.writeString(this.signature);
        dest.writeByte(this.isMatched ? (byte) 1 : (byte) 0);
        dest.writeString(this.bizNo);
        dest.writeStringList(this.details);
    }

    protected IndustryFocusOnModel(Parcel in) {
        this.appId = in.readString();
        this.authorized = in.readByte() != 0;
        this.param = in.readString();
        this.signature = in.readString();
        this.isMatched = in.readByte() != 0;
        this.bizNo = in.readString();
        this.details = in.createStringArrayList();
    }

    public static final Creator<IndustryFocusOnModel> CREATOR = new Creator<IndustryFocusOnModel>() {
        @Override
        public IndustryFocusOnModel createFromParcel(Parcel source) {
            return new IndustryFocusOnModel(source);
        }

        @Override
        public IndustryFocusOnModel[] newArray(int size) {
            return new IndustryFocusOnModel[size];
        }
    };
}
