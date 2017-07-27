package com.chengdai.cddata.models.common.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chengdai.cddata.R;
import com.chengdai.cddata.base.BaseFragment;
import com.chengdai.cddata.databinding.FragmentFirstBinding;
import com.chengdai.cddata.models.certification.activitys.CardAndNameInfoCheckActivity;
import com.chengdai.cddata.models.certification.activitys.FourInfoCheckActivity;
import com.chengdai.cddata.models.certification.activitys.FraudLookListActivity;
import com.chengdai.cddata.models.certification.activitys.FraudNumCheckActivity;
import com.chengdai.cddata.models.certification.activitys.GetFraudNumActivity;
import com.chengdai.cddata.models.certification.activitys.IndustryFocusOnActivity;
import com.chengdai.cddata.models.certification.activitys.ZMCertificationActivity;
import com.chengdai.cddata.models.certification.activitys.ZMNumGet2Activity;
import com.chengdai.cddata.models.certification.activitys.ZMNumGetActivity;
import com.chengdai.cddata.widget.utils.LogUtil;
import com.chengdai.cddata.widget.utils.SystemUtils;

/**
 * Created by 李先俊 on 2017/7/26.
 */

public class FirstDataFragment extends BaseFragment {

    private FragmentFirstBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mBinding= DataBindingUtil.inflate(getLayoutInflater(savedInstanceState), R.layout.fragment_first, null, false);

        initListener();

        return mBinding.getRoot();
    }


    private void initListener() {
        //二要素实名认证
        mBinding.layoutShiming.setOnClickListener(v -> {
            CardAndNameInfoCheckActivity.open(mActivity);
        });
        //四要素实名认证
        mBinding.layoutFourCheck.setOnClickListener(v -> {
            FourInfoCheckActivity.open(mActivity);
        });

        //芝麻认证
        mBinding.layoutZmcertification.setOnClickListener(v -> {
            ZMCertificationActivity.open(mActivity);
        });

        //芝麻分获取
        mBinding.layoutZmnumGet.setOnClickListener(v -> {
//            ZMNumGetActivity.open(mActivity);
            ZMNumGet2Activity.open(mActivity);
        });
        //行业关注名单
        mBinding.layoutIndustry.setOnClickListener(v -> {
            IndustryFocusOnActivity.open(mActivity);
        });

        //申请欺诈评分
        mBinding.layoutFraundnum.setOnClickListener(v -> {
            GetFraudNumActivity.open(mActivity);
        });

        //欺诈评分验证
        mBinding.layoutFraudnumCheck.setOnClickListener(v -> {
            FraudNumCheckActivity.open(mActivity);
        });

        //欺诈关注清单
        mBinding.layoutFraudlook.setOnClickListener(v -> {
            FraudLookListActivity.open(mActivity);
        });



    }
}
