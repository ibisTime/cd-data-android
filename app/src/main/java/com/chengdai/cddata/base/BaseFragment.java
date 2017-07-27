package com.chengdai.cddata.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.chengdai.cddata.widget.dialog.CommonDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;



/**
 * 碎片基类
 */
public abstract class BaseFragment extends Fragment {

    protected Activity mActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = getActivity();
    }
    @Subscribe
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Subscribe
    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
        mActivity = null;
    }



    /**
     * Dialog
     *
     * @param str
     */
    protected void showSimpleWran(String str) {

        if (mActivity != null && mActivity.isFinishing()) {
            return;
        }

        CommonDialog commonDialog = new CommonDialog(mActivity).builder()
                .setTitle("提示").setContentMsg(str)
                .setNegativeBtn("确定", null, false);

        commonDialog.show();
    }

    /**
     * @param str
     * @param onNegativeListener
     */
    protected void showWarnListen(String str, CommonDialog.OnNegativeListener onNegativeListener) {

        if (mActivity.isFinishing()) {
            return;
        }

        CommonDialog commonDialog = new CommonDialog(mActivity).builder()
                .setTitle("提示").setContentMsg(str)
                .setNegativeBtn("确定", onNegativeListener, false);

        commonDialog.show();
    }


}

