package com.chengdai.cddata.models.common.activitys;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.chengdai.cddata.R;
import com.chengdai.cddata.base.AbsBaseActivity;
import com.chengdai.cddata.databinding.ActivityMainBinding;
import com.chengdai.cddata.models.common.adapters.ViewPagerAdapter;
import com.chengdai.cddata.models.common.fragments.AboutMeFragment;
import com.chengdai.cddata.models.common.fragments.FirstDataFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 李先俊 on 2017/7/26.
 */

public class MainActivity extends AbsBaseActivity {

    private ActivityMainBinding mBinding;

    @Override
    protected boolean canLoadTopTitleView() {
        return false;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        if (getIntent()!=null && (getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {
            finish();
            return;
        }

        super.onCreate(savedInstanceState);

    }

    @Override
    public View addMainView() {
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.activity_main, null, false);
        return mBinding.getRoot();
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {

        List<Fragment> fragments = new ArrayList<>(); //设置fragment数据

        fragments.add(new FirstDataFragment());
        fragments.add(new AboutMeFragment());

        mBinding.pagerMain.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), fragments));
        mBinding.pagerMain.setOffscreenPageLimit(fragments.size());
        mBinding.layoutMainButtom.radiogroup.setOnCheckedChangeListener((group, checkedId) -> {

            switch (checkedId) {
                case R.id.radio_main_tab_1:

                    mBinding.pagerMain.setCurrentItem(0,false);

                    break;
                case R.id.radio_main_tab_2:
                    mBinding.pagerMain.setCurrentItem(1,false);
                    break;
            }

        });

    }


}
