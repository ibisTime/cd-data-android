package com.chengdai.cddata.models.certesults;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.chengdai.cddata.R;
import com.chengdai.cddata.base.AbsBaseActivity;
import com.chengdai.cddata.databinding.ActivityCertResultsBinding;
import com.chengdai.cddata.databinding.ActivityFourCertResultsBinding;

/**
 * 四要素认证结果
 * Created by 李先俊 on 2017/7/28.
 */

public class CertiResultsByFourActivity extends AbsBaseActivity {

    private ActivityFourCertResultsBinding mBinding;

    /**
     * 打开当前页面
     *
     * @param context
     */
    public static void open(Context context, boolean issuccessful, String name, String idCard, String phone, String banknum,String info) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, CertiResultsByFourActivity.class);

        intent.putExtra("phone", phone);
        intent.putExtra("name", name);
        intent.putExtra("idCard", idCard);
        intent.putExtra("banknum", banknum);
        intent.putExtra("issuccessful", issuccessful);
        intent.putExtra("info", info);
        context.startActivity(intent);
    }


    @Override
    public View addMainView() {
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.activity_four_cert_results, null, false);
        return mBinding.getRoot();
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {

        setTopTitle("认证结果");

        setSubLeftImgState(true);

        if (getIntent() != null) {

            if (getIntent().getBooleanExtra("issuccessful", false)) {
                mBinding.tipslayout.imgTips.setImageResource(R.mipmap.successful);
                mBinding.tipslayout.tvTips.setText(R.string.txt_success);
            } else {
                mBinding.tipslayout.imgTips.setImageResource(R.mipmap.failure);
                mBinding.tipslayout.tvTips.setText(R.string.txt_failure);
                mBinding.tvInfo.setVisibility(View.VISIBLE);
                mBinding.tvInfo.setText(getIntent().getStringExtra("info"));
            }

            mBinding.tvName.setText(getIntent().getStringExtra("name"));
            mBinding.tvIdcard.setText(getIntent().getStringExtra("idCard"));

            String phone = getIntent().getStringExtra("phone");
            String banknum = getIntent().getStringExtra("banknum");

            if (!TextUtils.isEmpty(phone)) {
                mBinding.tvPhonenum.setText(phone);
                mBinding.layoutPhone.setVisibility(View.VISIBLE);
            } else {
                mBinding.layoutPhone.setVisibility(View.GONE);
            }


            if (!TextUtils.isEmpty(banknum)) {
                mBinding.tvBankNum.setText(banknum);
                mBinding.layoutBankNum.setVisibility(View.VISIBLE);
            } else {
                mBinding.layoutBankNum.setVisibility(View.GONE);
            }


        }


    }
}
