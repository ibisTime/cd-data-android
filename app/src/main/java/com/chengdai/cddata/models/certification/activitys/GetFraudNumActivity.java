package com.chengdai.cddata.models.certification.activitys;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.chengdai.cddata.R;
import com.chengdai.cddata.base.BaseIMEIPermissionsActivity;
import com.chengdai.cddata.models.certesults.CertiResultsByNameAndIdCardActivity;
import com.chengdai.cddata.models.certesults.CertiResultsByQizhaPingFenActivity;
import com.chengdai.cddata.models.certification.models.GetFraudNumModel;
import com.chengdai.cddata.widget.configs.MyConfig;
import com.chengdai.cddata.widget.nets.BaseResponseModelCallBack;
import com.chengdai.cddata.widget.nets.RetrofitUtils;
import com.chengdai.cddata.widget.utils.StringUtils;
import com.chengdai.cddata.widget.utils.SystemUtils;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;

/**申请诈骗评分
 * Created by 李先俊 on 2017/7/27.
 */

public class GetFraudNumActivity extends BaseIMEIPermissionsActivity{

    /**
     * 打开当前页面
     *
     * @param context
     */
    public static void open(Context context) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, GetFraudNumActivity.class);
        context.startActivity(intent);
    }

    @Override
    public View addMainView() {

        mbinding= DataBindingUtil.inflate(getLayoutInflater(), R.layout.activity_qizhapingfen,null,false);

        return mbinding.getRoot();
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        super.afterCreate(savedInstanceState);
        setSubLeftImgState(true);
        setTopTitle("申请欺诈评分");
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
        if(isgetPermissions){         //6.0以上需要申请权限
            map.put("imei",SystemUtils.getIMEI(this));
        }
        map.put("ip", SystemUtils.getIPAddress(true));
//        map.put("mac", SystemUtils.getMacAddress(this));
        map.put("wifimac",  SystemUtils.getMacAddress(this));


        showLoadingDialog();
        Call call = RetrofitUtils.getLoaderServer().getFraudNum("798019", StringUtils.getJsonToString(map));
        addCall(call);
        call.enqueue(new BaseResponseModelCallBack<GetFraudNumModel>(this) {
            @Override
            protected void onSuccess(GetFraudNumModel data, String SucMessage) {
                CertiResultsByQizhaPingFenActivity.open(GetFraudNumActivity.this,true,mbinding.edtName.getText().toString(),
                        mbinding.editIdcard.getText().toString(),data.getScore(),"");
            }

            @Override
            public void onReqFailure(int errorCode, String errorMessage) {
                CertiResultsByQizhaPingFenActivity.open(GetFraudNumActivity.this,false,mbinding.edtName.getText().toString(),
                        mbinding.editIdcard.getText().toString(),"",errorMessage);
            }

            @Override
            protected void onBuinessFailure(String code, String error) {
                CertiResultsByQizhaPingFenActivity.open(GetFraudNumActivity.this,false,mbinding.edtName.getText().toString(),
                        mbinding.editIdcard.getText().toString(),"",error);
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