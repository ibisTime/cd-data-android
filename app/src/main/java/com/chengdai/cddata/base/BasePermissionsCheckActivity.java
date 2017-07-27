package com.chengdai.cddata.base;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.android.moblie.zmxy.antgroup.creditsdk.app.CreditApp;
import com.android.moblie.zmxy.antgroup.creditsdk.app.ICreditListener;
import com.chengdai.cddata.R;
import com.chengdai.cddata.databinding.ActivityCardandnameInfoCheckBinding;

/**
 * 是否授权检测
 * Created by 李先俊 on 2017/7/27.
 */

public abstract class BasePermissionsCheckActivity extends AbsBaseActivity implements ICreditListener {

    protected CreditApp creditApp;
    protected ActivityCardandnameInfoCheckBinding mBinding;

    @Override
    public View addMainView() {
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.activity_cardandname_info_check, null, false);
        return mBinding.getRoot();
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {

        setSubLeftImgState(true);

        creditApp = CreditApp.getOrCreateInstance(this.getApplicationContext());

        mBinding.butSure.setOnClickListener(v -> {

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

    protected abstract void checkRequest();


    @Override
    public void onComplete(Bundle bundle) {
        checkRequest();
    }

    @Override
    public void onError(Bundle bundle) {
        showToast("授权失败");
    }

    @Override
    public void onCancel() {

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // 回调事件相应
        creditApp.onActivityResult(requestCode, resultCode, data);
    }


}
