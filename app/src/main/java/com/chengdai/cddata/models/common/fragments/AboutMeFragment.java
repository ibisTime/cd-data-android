package com.chengdai.cddata.models.common.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chengdai.cddata.R;
import com.chengdai.cddata.base.BaseFragment;
import com.chengdai.cddata.databinding.FragmentAboutMeBinding;
import com.chengdai.cddata.databinding.FragmentFirstBinding;
import com.chengdai.cddata.models.certification.activitys.CardAndNameInfoCheckActivity;
import com.chengdai.cddata.models.certification.activitys.FourInfoCheckActivity;
import com.chengdai.cddata.models.certification.activitys.FraudLookListActivity;
import com.chengdai.cddata.models.certification.activitys.FraudNumCheckActivity;
import com.chengdai.cddata.models.certification.activitys.GetFraudNumActivity;
import com.chengdai.cddata.models.certification.activitys.IndustryFocusOnActivity;
import com.chengdai.cddata.models.certification.activitys.ZMCertificationActivity;
import com.chengdai.cddata.models.certification.activitys.ZMNumGet2Activity;

/**关于我们
 * Created by 李先俊 on 2017/7/26.
 */

public class AboutMeFragment extends BaseFragment {

    private FragmentAboutMeBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mBinding= DataBindingUtil.inflate(getLayoutInflater(savedInstanceState), R.layout.fragment_about_me, null, false);

        initListener();

        return mBinding.getRoot();
    }


    private void initListener() {

    }
}
