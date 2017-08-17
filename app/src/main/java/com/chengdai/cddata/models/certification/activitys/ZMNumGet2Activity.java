package com.chengdai.cddata.models.certification.activitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.chengdai.cddata.base.BasePermissionsCheckActivity;
import com.chengdai.cddata.models.certesults.CertiResultsByNameAndIdCardActivity;
import com.chengdai.cddata.models.certification.models.UserQueryInfoModel;
import com.chengdai.cddata.widget.configs.MyConfig;
import com.chengdai.cddata.widget.nets.BaseResponseModelCallBack;
import com.chengdai.cddata.widget.nets.RetrofitUtils;
import com.chengdai.cddata.widget.utils.StringUtils;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;

/**
 * 芝麻分数获取 第二版
 * Created by 李先俊 on 2017/7/26.
 */

public class ZMNumGet2Activity extends BasePermissionsCheckActivity{
    /**
     * 打开当前页面
     *
     * @param context
     */
    public static void open(Context context) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, ZMNumGet2Activity.class);
        context.startActivity(intent);
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {

        super.afterCreate(savedInstanceState);
        setTopTitle("芝麻信用评分");

    }

    /**
     * 检查是否授权过 如果已授权直接展示芝麻分 如果没授权则申请授权
     */

    @Override
    protected void checkRequest() {
        Map<String, String> map = new HashMap<>();

        map.put("companyCode", MyConfig.COMPANYCODE);
        map.put("systemCode", MyConfig.SYSTEMCODE);
        map.put("realName",mBinding.editName .getText().toString());
        map.put("idNo",mBinding.editCardNumber .getText().toString());

        showLoadingDialog();
        Call call = RetrofitUtils.getLoaderServer().userInfoQuery("798015", StringUtils.getJsonToString(map));
        addCall(call);
        call.enqueue(new BaseResponseModelCallBack<UserQueryInfoModel>(this) {
            @Override
            protected void onSuccess(UserQueryInfoModel data, String SucMessage) {
                if(data.isAuthorized()){
                    CertiResultsByNameAndIdCardActivity.open(ZMNumGet2Activity.this,true,mBinding.editName.getText().toString(),
                            mBinding.editCardNumber.getText().toString(),data.getZmScore(),"");
                }else{
                    creditApp.authenticate(ZMNumGet2Activity.this, data.getAppId(), null,data.getParam(), data.getSignature(), null,ZMNumGet2Activity.this);
                }
            }

            @Override
            public void onReqFailure(int errorCode, String errorMessage) {
                CertiResultsByNameAndIdCardActivity.open(ZMNumGet2Activity.this,false,mBinding.editName.getText().toString(),
                        mBinding.editCardNumber.getText().toString(),"",errorMessage);
            }

            @Override
            protected void onBuinessFailure(String code, String error) {
                CertiResultsByNameAndIdCardActivity.open(ZMNumGet2Activity.this,false,mBinding.editName.getText().toString(),
                        mBinding.editCardNumber.getText().toString(),"",error);
            }

            @Override
            protected void onNull() {

            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });

    }


}
