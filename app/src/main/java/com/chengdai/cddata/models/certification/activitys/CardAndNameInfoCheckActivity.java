package com.chengdai.cddata.models.certification.activitys;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.chengdai.cddata.R;
import com.chengdai.cddata.base.AbsBaseActivity;
import com.chengdai.cddata.databinding.ActivityCardandnameInfoCheckBinding;
import com.chengdai.cddata.models.certesults.CertiResultsByNameAndIdCardActivity;
import com.chengdai.cddata.models.common.models.IsSuccessModes;
import com.chengdai.cddata.widget.configs.MyConfig;
import com.chengdai.cddata.widget.nets.BaseResponseModelCallBack;
import com.chengdai.cddata.widget.nets.RetrofitUtils;
import com.chengdai.cddata.widget.utils.StringUtils;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;

/**
 * 二要素实名认证 （身份证和姓名）
 * Created by 李先俊 on 2017/7/26.
 */

public class CardAndNameInfoCheckActivity extends AbsBaseActivity {

    private ActivityCardandnameInfoCheckBinding mBinding;

    /**
     * 打开当前页面
     *
     * @param context
     */
    public static void open(Context context) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, CardAndNameInfoCheckActivity.class);
        context.startActivity(intent);
    }


    @Override
    public View addMainView() {
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.activity_cardandname_info_check, null, false);
        return mBinding.getRoot();
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {

        setTopTitle("二要素实名认证");

        setSubLeftImgState(true);

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

            checkRequest();

        });

    }

    /**
     * 认证请求
     */
    private void checkRequest() {
        Map<String, String> map = new HashMap<>();

        map.put("companyCode", MyConfig.COMPANYCODE);
        map.put("idKind", "1");
        map.put("idNo", mBinding.editCardNumber.getText().toString());
        map.put("realName", mBinding.editName.getText().toString());
        map.put("systemCode", MyConfig.SYSTEMCODE);
        map.put("userId", "U1234567890");

        showLoadingDialog();
        Call call = RetrofitUtils.getLoaderServer().idCardAndNameCheck("798001", StringUtils.getJsonToString(map));
        addCall(call);
        call.enqueue(new BaseResponseModelCallBack<IsSuccessModes>(this) {
            @Override
            protected void onSuccess(IsSuccessModes data, String SucMessage) {
                CertiResultsByNameAndIdCardActivity.open(CardAndNameInfoCheckActivity.this, data.isSuccess(),
                        mBinding.editName.getText().toString(), mBinding.editCardNumber.getText().toString(), "", "");
            }

            @Override
            protected void onBuinessFailure(String code, String error) {
                CertiResultsByNameAndIdCardActivity.open(CardAndNameInfoCheckActivity.this, false, mBinding.editName.getText().toString(),
                        mBinding.editCardNumber.getText().toString(), "", error);
            }

            @Override
            public void onReqFailure(int errorCode, String errorMessage) {
                CertiResultsByNameAndIdCardActivity.open(CardAndNameInfoCheckActivity.this, false, mBinding.editName.getText().toString(),
                        mBinding.editCardNumber.getText().toString(), "", errorMessage);
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
