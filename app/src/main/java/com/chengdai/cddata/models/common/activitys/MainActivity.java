package com.chengdai.cddata.models.common.activitys;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.chengdai.cddata.R;
import com.chengdai.cddata.base.AbsBaseActivity;
import com.chengdai.cddata.databinding.ActivityMainBinding;
import com.chengdai.cddata.models.common.adapters.ViewPagerAdapter;
import com.chengdai.cddata.models.common.fragments.FirstDataFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 李先俊 on 2017/7/26.
 */

public class MainActivity extends AbsBaseActivity{

    private ActivityMainBinding mBinding;

    @Override
    protected boolean canLoadTopTitleView() {
        return false;
    }

    @Override
    public View addMainView() {
        mBinding= DataBindingUtil.inflate(getLayoutInflater(), R.layout.activity_main,null,false);
        return mBinding.getRoot();
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {

        List<Fragment> fragments=new ArrayList<>(); //设置fragment数据

        fragments.add(new FirstDataFragment());

        mBinding.pagerMain.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(),fragments));

    }

}
