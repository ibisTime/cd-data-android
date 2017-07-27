package com.chengdai.cddata.base;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;

import com.chengdai.cddata.R;
import com.chengdai.cddata.databinding.ActivityQizhapingfenBinding;
import com.chengdai.cddata.models.certification.models.GetFraudNumModel;
import com.chengdai.cddata.widget.configs.MyConfig;
import com.chengdai.cddata.widget.nets.BaseResponseModelCallBack;
import com.chengdai.cddata.widget.nets.RetrofitUtils;
import com.chengdai.cddata.widget.utils.StringUtils;
import com.chengdai.cddata.widget.utils.SystemUtils;
import com.lljjcoder.citypickerview.widget.CityPicker;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;

/**
 * Created by 李先俊 on 2017/7/27.
 */

public abstract class BaseIMEIPermissionsActivity extends AbsBaseActivity{

    protected ActivityQizhapingfenBinding mbinding;
    protected String mProvince;
    protected String mCity;
    protected String mDistrict;
    protected boolean isgetPermissions=true;

    /**
     * 打开当前页面
     *
     * @param context
     */
    public static void open(Context context) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, BaseIMEIPermissionsActivity.class);
        context.startActivity(intent);
    }

    @Override
    public View addMainView() {

        mbinding= DataBindingUtil.inflate(getLayoutInflater(), R.layout.activity_qizhapingfen,null,false);

        return mbinding.getRoot();
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {

        setSubLeftImgState(true);
        initListener();
        PermissionCheck(0);
    }


    private void initListener() {

        mbinding.layoutAddress.setOnClickListener(v -> {
            cityPicker();
        });


        mbinding.btnSure.setOnClickListener(v -> {

            if(TextUtils.isEmpty(mbinding.edtName.getText().toString())){
                showToast("请输入姓名");
                return;
            }
            if(TextUtils.isEmpty(mbinding.editIdcard.getText().toString())){
                showToast("请输入身份证号");
                return;
            }

            checkRequest();

        });
    }


    private void cityPicker(){

        CityPicker cityPicker = new CityPicker.Builder(BaseIMEIPermissionsActivity.this)
                .textSize(18)
                .titleBackgroundColor("#ffffff")
                .titleTextColor("#ffffff")
                .backgroundPop(0xa0000000)
                .confirTextColor("#FE4332")
                .cancelTextColor("#FE4332")
                .province("北京市")
                .city("北京市")
                .district("昌平区")
                .textColor(Color.parseColor("#000000"))
                .provinceCyclic(true)
                .cityCyclic(false)
                .districtCyclic(false)
                .visibleItemsCount(7)
                .itemPadding(10)
                .onlyShowProvinceAndCity(false)
                .build();
        cityPicker.show();

        //监听方法，获取选择结果
        cityPicker.setOnCityItemClickListener(new CityPicker.OnCityItemClickListener() {
            @Override
            public void onSelected(String... citySelected) {
                //省份
                mProvince = citySelected[0];
                //城市
                mCity = citySelected[1];
                //区县（如果设定了两级联动，那么该项返回空）
                mDistrict = citySelected[2];
                mbinding.txtAddress.setText(mProvince +" "+ mCity +" "+ mDistrict);
            }
        });
    }


    public abstract void checkRequest();

    /**
     * 获取地址信息
     * @return
     */
    public String getAddressInfo() {
        StringBuffer sb=new StringBuffer();
        if(!TextUtils.isEmpty(mProvince)) sb.append(mProvince);
        if(!TextUtils.isEmpty(mCity)) sb.append(mCity);
        if(!TextUtils.isEmpty(mDistrict)) sb.append(mDistrict);
        if(!TextUtils.isEmpty(mbinding.edtDetailed.getText().toString()))sb.append(mbinding.edtDetailed.getText().toString());
        return sb.toString();
    }


    @TargetApi(Build.VERSION_CODES.M)
    private void PermissionCheck(int requestcode) {
        if (SystemUtils.getAndroidVersion(Build.VERSION_CODES.M))  //如果运行环境是6.0
        {
            isgetPermissions=false;

            //判断是否有相机权限
            if (ContextCompat.checkSelfPermission(BaseIMEIPermissionsActivity.this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED ) //没有权限
            {
                requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_PHONE_STATE}, requestcode);
            }else {
                isgetPermissions=true;
            }
        }
    }


    //权限申请回调函数
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        isgetPermissions = true;

        for (int i = 0; i < grantResults.length; i++) {
            if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                isgetPermissions = false;
                break;
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }



}