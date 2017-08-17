package com.chengdai.cddata.models.certesults;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.chengdai.cddata.R;
import com.chengdai.cddata.base.AbsBaseActivity;
import com.chengdai.cddata.databinding.ActivityCardandnameInfoCheckBinding;
import com.chengdai.cddata.databinding.ActivityCertResultsIndustryBinding;
import com.chengdai.cddata.databinding.ActivityDetailsInfoResultsBinding;
import com.chengdai.cddata.models.certification.models.FraudLookModel;
import com.chengdai.cddata.models.certification.models.IndustryFocusOnModel;

/**
 * 行业关注名单结果
 * Created by 李先俊 on 2017/7/28.
 */

public class CertiResultsByIndustryFocusOnActivity extends AbsBaseActivity {

    private ActivityCertResultsIndustryBinding mBinding;

    private IndustryFocusOnModel mData;

    /**
     * 打开当前页面
     *
     * @param context
     */
    public static void open(Context context, IndustryFocusOnModel data,String info) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, CertiResultsByIndustryFocusOnActivity.class);
        intent.putExtra("data", data);
        intent.putExtra("info", info);
        context.startActivity(intent);
    }


    @Override
    public View addMainView() {
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.activity_cert_results_industry, null, false);
        return mBinding.getRoot();
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {

        setTopTitle("认证结果");

        setSubLeftImgState(true);

        if (getIntent() != null) {

            mData = getIntent().getParcelableExtra("data");

            if (mData == null) {
                setFailureInfo(getIntent().getStringExtra("info"));
                return;
            }

            setSuccessInfo();

        }
    }

    public void setSuccessInfo(){
        mBinding.tipslayout.imgTips.setImageResource(R.mipmap.successful);
        mBinding.tipslayout.tvTips.setText(R.string.txt_success);

        mBinding.tvCertInfo.setText("是否被行业关注 ："+mData.isMatched());

        if(!mData.isMatched()){

            return;
        }
        StringBuffer sb = new StringBuffer();

        if(mData.getDetails()!=null){
            for (String s:mData.getDetails()){
                sb.append(s);
            }
        }

        mBinding.tvSuccess.setText(sb.toString());

    }

    public void setFailureInfo(String ss){
        mBinding.tipslayout.imgTips.setImageResource(R.mipmap.failure);
        mBinding.tipslayout.tvTips.setText(R.string.txt_failure);
        mBinding.tvSuccess.setText(ss);

    }

}
