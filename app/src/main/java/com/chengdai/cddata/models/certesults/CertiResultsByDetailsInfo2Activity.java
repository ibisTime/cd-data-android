package com.chengdai.cddata.models.certesults;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.chengdai.cddata.R;
import com.chengdai.cddata.base.AbsBaseActivity;
import com.chengdai.cddata.databinding.ActivityDetailsInfoResultsBinding;
import com.chengdai.cddata.models.certification.models.FraudLookModel;
import com.chengdai.cddata.models.certification.models.FraudNumCheckModel;

/**
 * 详细信息认证结果
 * Created by 李先俊 on 2017/7/28.
 */

public class CertiResultsByDetailsInfo2Activity extends AbsBaseActivity {

    private ActivityDetailsInfoResultsBinding mBinding;

    private FraudLookModel mData;

    /**
     * 打开当前页面
     *
     * @param context
     */
    public static void open(Context context, boolean issuccessful, FraudLookModel data, String info) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, CertiResultsByDetailsInfo2Activity.class);

        intent.putExtra("issuccessful", issuccessful);
        intent.putExtra("data", data);
        intent.putExtra("info", info);
        context.startActivity(intent);
    }


    @Override
    public View addMainView() {
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.activity_details_info_results, null, false);
        return mBinding.getRoot();
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {

        setTopTitle("认证结果");

        setSubLeftImgState(true);

        if (getIntent() == null) {
            showFailure("结果获取失败,请重试");
            return;
        }

        mData = getIntent().getParcelableExtra("data");

        if (getIntent().getBooleanExtra("issuccessful", false)) {
            mBinding.tipslayout.imgTips.setImageResource(R.mipmap.successful);
            mBinding.tipslayout.tvTips.setText(R.string.txt_success);
            mBinding.tvSuccess.setVisibility(View.VISIBLE);

            if (mData != null && mData.getRiskInofList()!=null) {

                StringBuffer sb = new StringBuffer();

                for (String s : mData.getRiskInofList()) {
                    sb.append(s);
                    sb.append("\n\n");
                }
                mBinding.tvSuccess.setText(sb.toString());
            }


        } else {
            showFailure(getIntent().getStringExtra("info"));


          /*      mBinding.tvName.setText(getIntent().getStringExtra("name"));
                mBinding.tvIdcard.setText(getIntent().getStringExtra("idCard"));
                mBinding.tvIdcard.setText(getIntent().getStringExtra("idCard"));
*/


        }


    }

    private void showFailure(String text) {
        mBinding.tipslayout.imgTips.setImageResource(R.mipmap.failure);
        mBinding.tipslayout.tvTips.setText(R.string.txt_failure);
        mBinding.tvSuccess.setVisibility(View.VISIBLE);
        mBinding.tvSuccess.setText(text);
    }
}
