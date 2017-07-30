package com.chengdai.cddata.models.common.fragments;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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
import com.moxie.client.MainActivity;
import com.moxie.client.manager.MoxieSDK;
import com.moxie.client.model.MxParam;
import com.moxie.client.model.TitleParams;

import org.json.JSONObject;

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
        mBinding.layoutShiming.setOnClickListener(v -> { //完成
            CardAndNameInfoCheckActivity.open(mActivity);
        });
        //四要素实名认证
        mBinding.layoutFourCheck.setOnClickListener(v -> {//完成
            FourInfoCheckActivity.open(mActivity);
        });

        //芝麻认证
        mBinding.layoutZmcertification.setOnClickListener(v -> {//完成
            ZMCertificationActivity.open(mActivity);
        });

        //芝麻分获取
        mBinding.layoutZmnumGet.setOnClickListener(v -> { //完成
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

      //运营商认证
        mBinding.layoutMoxie.setOnClickListener(v -> {
            openMoxie();
        });

    }




    public void openMoxie(){
        //合作方系统中的客户ID
        String mUserId = "12364646565655654";
        //获取任务状态时使用(合作方申请接入后由魔蝎数据提供)
//        String mApiKey = "6149056c09e1498ca9b1bcd534b5ad0c";
        String mApiKey = "96ee985a972a4685be2bb423588e008f";
        String mBannerTxtContent= "运营商"; //SDK里title的文字描述
        String mThemeColor= "#ff6702"; //SDK里页面主色调
        String mAgreementUrl="https://api.51datake8y.com/h5/agreement.html"; //SDK里显示的用户使用协议
        MxParam mxParam = new MxParam();
        mxParam.setUserId(mUserId);
        mxParam.setApiKey(mApiKey);
        mxParam.setBannerTxtContent(mBannerTxtContent); // SDK里title的文字描述
        mxParam.setThemeColor(mThemeColor);  // SDK里页面主色调
        mxParam.setAgreementUrl(mAgreementUrl); // SDK里显示的用户使用协议
        mxParam.setFunction(MxParam.PARAM_FUNCTION_CARRIER); // 功能名
        mxParam.setQuitOnFail(MxParam.PARAM_COMMON_YES); // 爬取失败时是否退出SDK(登录阶段之后)
        mxParam.setAgreementEntryText("同意《使用协议》"); // SDK里显示的同意协议描述语
        mxParam.setLoadingViewText("验证过程中不会浪费您任何流量\n请稍等片刻");  //设置导入过程中的自定义提示文案，为居中显示
        mxParam.setQuitDisable(true); //设置导入过程中，触发返回键或者点击actionbar的返回按钮的时候，不执行魔蝎的默认行为
        //设置title
        TitleParams titleParams = new TitleParams.Builder()
                //不设置此方法会默认使用魔蝎的icon
                .leftNormalImgResId(R.mipmap.back_img)
                //用于设置selector，表示按下的效果，不设置默认使用leftNormalImgResId()设置的图片
                .leftPressedImgResId(R.drawable.moxie_client_banner_back_black)
                .titleColor(getContext().getResources().getColor(R.color.white))
//                    .backgroundColor(getContext().getResources().getColor(R.color.colorAccent))
                .backgroundDrawable(R.color.title_bg)
                .rightNormalImgResId(R.mipmap.refresh)
                .immersedEnable(true)
                .build();

        mxParam.setTitleParams(titleParams);
        Bundle bundle = new Bundle();
        bundle.putParcelable("param", mxParam);
        Intent intent = new Intent(mActivity, com.moxie.client.MainActivity.class);
        intent.putExtras(bundle);
        startActivityForResult(intent, 0);

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {


        if (resultCode == Activity.RESULT_OK) {
            Bundle b = data.getExtras();
            String result = b.getString("result");

            LogUtil.E("启动"+result);

            if(TextUtils.isEmpty(result)) {
                Toast.makeText(mActivity, "用户没有进行导入操作!", Toast.LENGTH_SHORT).show();
            } else {
                try {
                    int code = 0;
                    JSONObject jsonObject = new JSONObject(result);
                    code = jsonObject.getInt("code");
                    if (code == 1){
                        //根据taskType进行对应的处理
                        switch (jsonObject.getString("taskType")) {
                            case MxParam.PARAM_FUNCTION_CARRIER:
                                Toast.makeText(mActivity, "成功", Toast.LENGTH_SHORT).show();
                                break;
                            //.....
                            default:
                                Toast.makeText(mActivity, "成功", Toast.LENGTH_SHORT).show();
                        }
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //用来清理数据或解除引用
        MoxieSDK.getInstance().clear();
    }
}
