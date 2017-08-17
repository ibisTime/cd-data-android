package com.chengdai.cddata.models.certification.activitys;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.android.moblie.zmxy.antgroup.creditsdk.app.CreditApp;
import com.android.moblie.zmxy.antgroup.creditsdk.app.ICreditListener;
import com.chengdai.cddata.R;
import com.chengdai.cddata.base.AbsBaseActivity;
import com.chengdai.cddata.base.BasePermissionsCheckActivity;
import com.chengdai.cddata.databinding.ActivityCardandnameInfoCheckBinding;
import com.chengdai.cddata.models.api.BaseResponseModel;
import com.chengdai.cddata.models.certesults.CertiResultsByIndustryFocusOnActivity;
import com.chengdai.cddata.models.certification.models.IndustryFocusOnModel;
import com.chengdai.cddata.models.certification.models.UserQueryInfoModel;
import com.chengdai.cddata.widget.configs.MyConfig;
import com.chengdai.cddata.widget.nets.BaseResponseModelCallBack;
import com.chengdai.cddata.widget.nets.RetrofitUtils;
import com.chengdai.cddata.widget.utils.StringUtils;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;

/**
 * 行业关注名单
 * Created by 李先俊 on 2017/7/26.
 */

public class IndustryFocusOnActivity extends BasePermissionsCheckActivity {

    /**
     * 打开当前页面
     *
     * @param context
     */
    public static void open(Context context) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, IndustryFocusOnActivity.class);
        context.startActivity(intent);
    }


    @Override
    public void afterCreate(Bundle savedInstanceState) {
        super.afterCreate(savedInstanceState);
        setTopTitle("行业关注名单");
    }

    @Override
    protected void checkRequest() {
        Map<String, String> map = new HashMap<>();

        map.put("companyCode", MyConfig.COMPANYCODE);
        map.put("systemCode", MyConfig.SYSTEMCODE);
        map.put("realName",mBinding.editName .getText().toString());
        map.put("idNo",mBinding.editCardNumber .getText().toString());

        showLoadingDialog();
        Call call = RetrofitUtils.getLoaderServer().IndustryFocusOnInfoQuery("798016", StringUtils.getJsonToString(map));
        addCall(call);
        call.enqueue(new BaseResponseModelCallBack<IndustryFocusOnModel>(this) {
            @Override
            protected void onSuccess(IndustryFocusOnModel data, String SucMessage) {
                if(data.isAuthorized()){
                    CertiResultsByIndustryFocusOnActivity.open(IndustryFocusOnActivity.this,data,"信息获取失败，请重新认证");
                }else{
                    creditApp.authenticate(IndustryFocusOnActivity.this, data.getAppId(), null,data.getParam(), data.getSignature(), null,IndustryFocusOnActivity.this);
                }
            }

            @Override
            public void onReqFailure(int errorCode, String errorMessage) {
                CertiResultsByIndustryFocusOnActivity.open(IndustryFocusOnActivity.this,null,errorMessage);
            }

            @Override
            protected void onBuinessFailure(String code,String error) {
                CertiResultsByIndustryFocusOnActivity.open(IndustryFocusOnActivity.this,null,error);
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
