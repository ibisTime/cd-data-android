package com.chengdai.cddata.models.certification.activitys;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.chengdai.cddata.R;
import com.chengdai.cddata.base.AbsBaseActivity;
import com.chengdai.cddata.databinding.ActivityCardandnameInfoCheckBinding;
import com.chengdai.cddata.models.certification.models.ZMCertFirstStepModel;
import com.chengdai.cddata.models.common.models.IsSuccessModes;
import com.chengdai.cddata.widget.configs.MyConfig;
import com.chengdai.cddata.widget.nets.BaseResponseModelCallBack;
import com.chengdai.cddata.widget.nets.RetrofitUtils;
import com.chengdai.cddata.widget.utils.LogUtil;
import com.chengdai.cddata.widget.utils.StringUtils;
import com.zmxy.ZMCertification;
import com.zmxy.ZMCertificationListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;

/**
 * 芝麻认证
 * Created by 李先俊 on 2017/7/26.
 */

public class ZMCertificationActivity extends AbsBaseActivity implements ZMCertificationListener {

    private ActivityCardandnameInfoCheckBinding mBinding;

    private ZMCertification zmCertification;//芝麻认证

    private String mBizNum;


    /**
     * 打开当前页面
     *
     * @param context
     */
    public static void open(Context context) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, ZMCertificationActivity.class);
        context.startActivity(intent);
    }


    @Override
    public View addMainView() {
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.activity_cardandname_info_check, null, false);
        return mBinding.getRoot();
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {
//        zmCertification = ZMCertification.getInstance();
        setTopTitle("芝麻认证");

        setSubLeftImgState(true);

        //
        mBinding.butSure.setOnClickListener(v -> {
   /*         if(TextUtils.isEmpty(mBinding.editPhoneNumber.getText().toString())){
                showToast("请填写手机号");
                return;
            }*/
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

    /**
     * 认证请求
     */
    private void checkRequest() {
        Map<String, String> map = new HashMap<>();

        map.put("companyCode", MyConfig.COMPANYCODE);
        map.put("idKind", "1");
        map.put("idNo", mBinding.editCardNumber.getText().toString());
        map.put("realName", mBinding.editName.getText().toString());
        map.put("systemCode", MyConfig.SYSTEMCODE);
        map.put("userId", "U1234567890");
        map.put("localCheck", "0");
        map.put("returnUrl", "cddata://certi.back");

        showLoadingDialog();
        Call call = RetrofitUtils.getLoaderServer().ZMCertOne("798013", StringUtils.getJsonToString(map));
        addCall(call);
        call.enqueue(new BaseResponseModelCallBack<ZMCertFirstStepModel>(this) {
            @Override
            protected void onSuccess(ZMCertFirstStepModel data, String SucMessage) {

                if (!data.isIsSuccess()) {

//                    zmCertification.setZMCertificationListener(ZMCertificationActivity.this);

//                    zmCertification.startCertification(ZMCertificationActivity.this, data.getBizNo(), data.getMerchantId(), null);
                    mBizNum=data.getBizNo();
                    doVerify(data.getUrl());

                }
            }

            @Override
            protected void onFailure(int errorCode, String errorMessage) {
                showSimpleWran(errorMessage);
            }

            @Override
            protected void onNull() {

            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });

    }

    /**
     * 启动支付宝进行认证
     *
     * @param url 开放平台返回的URL
     */
    private void doVerify(String url) {
        if (hasApplication()) {
            Intent action = new Intent(Intent.ACTION_VIEW);
            StringBuilder builder = new StringBuilder();
            // 这里使用固定appid 20000067
            builder.append("alipays://platformapi/startapp?appId=20000067&url=");
            builder.append(URLEncoder.encode(url));
            action.setData(Uri.parse(builder.toString()));
            LogUtil.E("url"+builder.toString());
            startActivity(action);
        } else {
            // 处理没有安装支付宝的情况
            new AlertDialog.Builder(this)
                    .setMessage("是否下载并安装支付宝完成认证?")
                    .setPositiveButton("好的", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent action = new Intent(Intent.ACTION_VIEW);
                            action.setData(Uri.parse("https://m.alipay.com"));
                            startActivity(action);
                        }
                    }).setNegativeButton("算了", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            }).show();
        }
    }

    /**
     * 判断是否安装了支付宝
     *
     * @return true 为已经安装
     */
    private boolean hasApplication() {
        PackageManager manager = getPackageManager();
        Intent action = new Intent(Intent.ACTION_VIEW);
        action.setData(Uri.parse("alipays://"));
        List list = manager.queryIntentActivities(action, PackageManager.GET_RESOLVED_FILTER);
        return list != null && list.size() > 0;
    }

//芝麻认证回调
    @Override
    public void onFinish(boolean isCanceled, boolean isPassed, int errorCode) {
        zmCertification.setZMCertificationListener(null);
        if (isCanceled)
            Toast.makeText(this, "cancel : 芝麻验证失败，原因是：" , Toast.LENGTH_SHORT).show();
        else {
            if (isPassed)
                Toast.makeText(this, "complete : 芝麻验证成功，原因是：", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, "complete : 芝麻验证失败，原因是：", Toast.LENGTH_SHORT).show();
        }
        Log.w("ceshi", "onFinish+++ isPassed = " + isPassed + ", error = " + errorCode);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.e("LQ", "scheme:" + intent.getScheme());
        Uri uri = intent.getData();
        if (uri != null) {
            Log.e("LQ", "scheme: " + uri.getScheme());
            Log.e("LQ", "host: " + uri.getHost());
            Log.e("LQ", "port: " + uri.getPort());
            Log.e("LQ", "path: " + uri.getPath());
            Log.e("LQ", "queryString: " + uri.getQuery());
            Log.e("LQ", "queryParameter: " + uri.getQueryParameterNames());

            check(mBizNum);

/*            if (null != uri.getQueryParameter("biz_content")) {
                try {
                    JSONObject object = new JSONObject(uri.getQueryParameter("biz_content"));
                    check(object.getString("biz_no"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }*/
        }
    }

    public void check(String bizNo){
        Map<String ,String> map=new HashMap<>();
        map.put("bizNo",bizNo);
        map.put("companyCode",MyConfig.COMPANYCODE);
        map.put("systemCode",MyConfig.SYSTEMCODE);


        showLoadingDialog();
        Call call = RetrofitUtils.getLoaderServer().ZMCertTwo("798014", StringUtils.getJsonToString(map));
        addCall(call);
        call.enqueue(new BaseResponseModelCallBack<IsSuccessModes>(this) {
            @Override
            protected void onSuccess(IsSuccessModes data, String SucMessage) {
                if (data.isSuccess()) {
                    showSimpleWran("认证成功");
                }else{
                    showSimpleWran("认证失败");
                }

            }

            @Override
            protected void onFailure(int errorCode, String errorMessage) {
                showSimpleWran(errorMessage);
            }

            @Override
            protected void onNull() {

            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });

    }



}
