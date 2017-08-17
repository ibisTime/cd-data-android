package com.chengdai.cddata.models.certification.activitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.chengdai.cddata.base.BaseIMEIPermissionsActivity;
import com.chengdai.cddata.models.certesults.CertiResultsByDetailsInfo2Activity;
import com.chengdai.cddata.models.certification.models.FraudLookModel;
import com.chengdai.cddata.widget.configs.MyConfig;
import com.chengdai.cddata.widget.nets.BaseResponseModelCallBack;
import com.chengdai.cddata.widget.nets.RetrofitUtils;
import com.chengdai.cddata.widget.utils.StringUtils;
import com.chengdai.cddata.widget.utils.SystemUtils;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;

/**欺诈关注清单
 * Created by 李先俊 on 2017/7/27.
 */

public class FraudLookListActivity extends BaseIMEIPermissionsActivity{


    /**
     * 打开当前页面
     *
     * @param context
     */
    public static void open(Context context) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, FraudLookListActivity.class);
        context.startActivity(intent);
    }


    @Override
    public void afterCreate(Bundle savedInstanceState) {
        super.afterCreate(savedInstanceState);
        setTopTitle("欺诈关注清单");

    }

    @Override
    public void checkRequest() {
        Map<String,String> map=new HashMap<>();
        map.put("address",getAddressInfo());
        map.put("bankCard",mbinding.editBankNumber.getText().toString());
        map.put("companyCode", MyConfig.COMPANYCODE);
        map.put("email",mbinding.editEmail.getText().toString());
        map.put("idNo",mbinding.editIdcard.getText().toString());
        map.put("mobile",mbinding.edtPhone.getText().toString());
        map.put("realName",mbinding.edtName.getText().toString());
        map.put("systemCode",MyConfig.SYSTEMCODE);
        if(isgetPermissions){
            map.put("imei",SystemUtils.getIMEI(this));
        }
        map.put("ip", SystemUtils.getIPAddress(true));

//        map.put("mac", SystemUtils.getMacAddress(this));

        map.put("wifimac", SystemUtils.getMacAddress(this));


        showLoadingDialog();
        Call call = RetrofitUtils.getLoaderServer().fraudLookList("798021", StringUtils.getJsonToString(map));
        addCall(call);
        call.enqueue(new BaseResponseModelCallBack<FraudLookModel>(this) {
            @Override
            protected void onSuccess(FraudLookModel data, String SucMessage) {
                CertiResultsByDetailsInfo2Activity.open(FraudLookListActivity.this,true,data,"结果获取失败，请重试。");
            }

            @Override
            public void onReqFailure(int errorCode, String errorMessage) {
                CertiResultsByDetailsInfo2Activity.open(FraudLookListActivity.this,false,null,errorMessage);
            }

            @Override
            protected void onBuinessFailure(String code,String error) {
                CertiResultsByDetailsInfo2Activity.open(FraudLookListActivity.this,false,null,error);
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