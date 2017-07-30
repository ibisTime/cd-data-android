package com.chengdai.cddata.models.certesults;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.chengdai.cddata.R;
import com.chengdai.cddata.base.AbsBaseActivity;
import com.chengdai.cddata.databinding.ActivityCertResultsBinding;
import com.chengdai.cddata.databinding.ActivityQizhapingfenCertResultsBinding;

/**申请欺诈评分
 * Created by 李先俊 on 2017/7/28.
 */

public class CertiResultsByQizhaPingFenActivity extends AbsBaseActivity{

    private ActivityQizhapingfenCertResultsBinding mBinding;

    /**
     * 打开当前页面
     *
     * @param context
     */
    public static void open(Context context,boolean issuccessful,String name,String idCard,String zmScore) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, CertiResultsByQizhaPingFenActivity.class);

          intent.putExtra("issuccessful",issuccessful);
          intent.putExtra("name",name);
          intent.putExtra("idCard",idCard);
          intent.putExtra("zmScore",zmScore);
        context.startActivity(intent);
    }



    @Override
    public View addMainView() {
        mBinding= DataBindingUtil.inflate(getLayoutInflater(), R.layout.activity_qizhapingfen_cert_results,null,false);
        return mBinding.getRoot();
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {

        setTopTitle("认证结果");

        setSubLeftImgState(true);

        if(getIntent()!=null){

            if(getIntent().getBooleanExtra("issuccessful",true)){
                mBinding.tipslayout.imgTips.setImageResource(R.mipmap.successful);
                mBinding.tipslayout.tvTips.setText("认证成功");
            }else{
                mBinding.tipslayout.imgTips.setImageResource(R.mipmap.failure);
                mBinding.tipslayout.tvTips.setText("认证失败");
            }

            mBinding.tvName.setText(getIntent().getStringExtra("name"));
            mBinding.tvIdcard.setText(getIntent().getStringExtra("idCard"));
            mBinding.layoutZm.setVisibility(View.VISIBLE);
            mBinding.tvZmsource.setText(getIntent().getStringExtra("zmScore"));

        }


    }
}
