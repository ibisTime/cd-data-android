package com.chengdai.cddata.models.certification.activitys;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.android.moblie.zmxy.antgroup.creditsdk.app.CreditApp;
import com.android.moblie.zmxy.antgroup.creditsdk.app.ICreditListener;
import com.chengdai.cddata.R;
import com.chengdai.cddata.base.AbsBaseActivity;
import com.chengdai.cddata.databinding.ActivityCardandnameInfoCheckBinding;
import com.chengdai.cddata.models.certification.models.UserQueryModel;
import com.chengdai.cddata.models.certification.models.ZmNumGetFirstStepModel;
import com.chengdai.cddata.widget.configs.MyConfig;
import com.chengdai.cddata.widget.nets.BaseResponseModelCallBack;
import com.chengdai.cddata.widget.nets.RetrofitUtils;
import com.chengdai.cddata.widget.utils.LogUtil;
import com.chengdai.cddata.widget.utils.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import retrofit2.Call;

/**
 * 芝麻分数获取
 * Created by 李先俊 on 2017/7/26.
 */

public class ZMNumGetActivity extends AbsBaseActivity{

    private ActivityCardandnameInfoCheckBinding mBinding;
    private CreditApp creditApp;
    /**
     * 打开当前页面
     *
     * @param context
     */
    public static void open(Context context) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, ZMNumGetActivity.class);
        context.startActivity(intent);
    }


    @Override
    public View addMainView() {
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.activity_cardandname_info_check, null, false);
        return mBinding.getRoot();
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {
//        zmCertification = ZMCertification.getInstance();
        setTopTitle("芝麻信用评分");

        setSubLeftImgState(true);

        creditApp = CreditApp.getOrCreateInstance(this.getApplicationContext());

        //
        mBinding.butSure.setOnClickListener(v -> {
   /*         if(TextUtils.isEmpty(mBinding.editPhoneNumber.getText().toString())){
                showToast("请填写手机号");
                return;
            }*/
            if (TextUtils.isEmpty(mBinding.editName.getText().toString())) {
                showToast("请填写姓名");
                return;
            }
            if (TextUtils.isEmpty(mBinding.editCardNumber.getText().toString())) {
                showToast("请填写身份证号码");
                return;
            }

            firstCheckRequest();

        });

    }

    /**
     * 认证请求
     */
    private void checkRequest() {
        Map<String, String> map = new HashMap<>();

        map.put("companyCode", MyConfig.COMPANYCODE);
        map.put("systemCode", MyConfig.SYSTEMCODE);
        map.put("realName",mBinding.editName .getText().toString());
        map.put("idNo",mBinding.editCardNumber .getText().toString());

        showLoadingDialog();
        Call call = RetrofitUtils.getLoaderServer().ZMNumGetOne("798017", StringUtils.getJsonToString(map));
        addCall(call);
        call.enqueue(new BaseResponseModelCallBack<ZmNumGetFirstStepModel>(this) {
            @Override
            protected void onSuccess(ZmNumGetFirstStepModel data, String SucMessage) {
                LogUtil.E("请求成功");
                creditApp.authenticate(ZMNumGetActivity.this, data.getAppId(), null,data.getParam(), data.getSignature(), null, new ICreditListener() {
                    @Override
                    public void onComplete(Bundle result) {

                        LogUtil.E("支付宝成功");

                        firstCheckRequest();

                        if (result != null) {
                            Set<String> keys = result.keySet();
                            for (String key : keys) {
                                Log.d("lxj", key + " = " + result.getString(key));
                            }
                        }
                    }

                    @Override
                    public void onError(Bundle bundle) {
                        LogUtil.E("支付宝失败");

                    }

                    @Override
                    public void onCancel() {
                        LogUtil.E("支付宝onCancel");
                    }
                });


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

    /**
     * 检查是否授权过
     */
    private void firstCheckRequest() {
        Map<String, String> map = new HashMap<>();

        map.put("companyCode", MyConfig.COMPANYCODE);
        map.put("systemCode", MyConfig.SYSTEMCODE);
        map.put("realName",mBinding.editName .getText().toString());
        map.put("idNo",mBinding.editCardNumber .getText().toString());

        showLoadingDialog();
        Call call = RetrofitUtils.getLoaderServer().userQuery("798018", StringUtils.getJsonToString(map));
        addCall(call);
        call.enqueue(new BaseResponseModelCallBack<UserQueryModel>(this) {
            @Override
            protected void onSuccess(UserQueryModel data, String SucMessage) {
                    if(data.isAuthorized()){
                        getZmNumRequest(data.getOpenId());
                    }else{
                        checkRequest();
                    }
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

    /**
     * 获取芝麻分
     */
    private void getZmNumRequest(String openId) {
        Map<String, String> map = new HashMap<>();

        map.put("companyCode", MyConfig.COMPANYCODE);
        map.put("systemCode", MyConfig.SYSTEMCODE);
        map.put("openId",openId);

        showLoadingDialog();
        Call call = RetrofitUtils.getLoaderServer().getZMNum("798015", StringUtils.getJsonToString(map));
        addCall(call);
        call.enqueue(new BaseResponseModelCallBack<String>(this) {
            @Override
            protected void onSuccess(String data, String SucMessage) {


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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // 回调事件相应
        creditApp.onActivityResult(requestCode, resultCode, data);
    }


}
