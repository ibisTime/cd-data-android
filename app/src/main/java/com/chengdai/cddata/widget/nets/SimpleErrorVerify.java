package com.chengdai.cddata.widget.nets;


import android.content.Context;

import com.chengdai.cddata.base.BaseApplication;
import com.chengdai.cddata.widget.utils.LogUtil;
import com.chengdai.cddata.widget.utils.ToastUtil;

import java.lang.ref.SoftReference;

/**
 * Created by jiaoyu on 17/4/20.
 */
public class SimpleErrorVerify implements ErrorVerify {
   //软引用
    private SoftReference<Context> mContext;

    public SimpleErrorVerify(Context mContext) {
        this.mContext = new SoftReference<>(mContext);
    }

    @Override
    public void call(String code, String desc) {

        LogUtil.E("请求错误 "+desc);
        ToastUtil.show(BaseApplication.getInstance(),desc);
    }

}
