package com.chengdai.cddata.widget.nets;

import android.content.Context;


import com.chengdai.cddata.models.api.BaseResponseModel;
import com.chengdai.cddata.widget.utils.LogUtil;
import com.chengdai.cddata.widget.utils.ToastUtil;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 网络请求回调
 * Created by Administrator on 2016/9/3.
 */
public abstract class BaseResponseModelCallBack<T> implements Callback<BaseResponseModel<T>> {


    /*0=成功；1=权限错误；2=参数错误；3=业务错误；9=未知错误*/

    public static final String REQUESTOK = "0";   //请求后台成功

    public static final String REQUESTFECODE3= "3";
    public static final String REQUESTFECODE2= "2";

    public static final String REQUESTFECODE4 = "4";//重新登录

    public static final String REQUESTFECODE9 = "9";

    public static final String NET_ERROR = "-1";


    /**
     * 网络异常状态错误码
     */
    public static final int NETERRORCODE0 = 0;  //请求成功，但是服务器返回除1000外错误码
    public static final int NETERRORCODE1 = 1;  //网络异常
    public static final int NETERRORCODE2 = 2;  //响应超时
    public static final int NETERRORCODE3 = 3;  //连接超时
    public static final int NETERRORCODE4 = 4;  //其它错误

    private Context context;

    public BaseResponseModelCallBack(Context context) {
        this.context = context;
    }

    @Override
    public void onResponse(Call<BaseResponseModel<T>> call, Response<BaseResponseModel<T>> response) {

        onFinish();

        if (response == null || response.body() == null) {
            onNull();
            this.context = null;
            return;
        }

        if (response.isSuccessful()) {

            try {
                BaseResponseModel t = response.body();
                checkState(t);      //根据返回错误的状态码实现相应的操作
            } catch (Exception e) {
                if (LogUtil.isLog) {
                    onFailure(NETERRORCODE4, "出现未知错误" + e.toString());
                }
                onFailure(NETERRORCODE4, "出现未知错误");
            }

        } else {
            onFailure(NETERRORCODE4, "网络请求失败");
        }

        this.context = null;
    }

    @Override
    public void onFailure(Call<BaseResponseModel<T>> call, Throwable t) {

        onFinish();

        if (call.isCanceled()) {                //如果是主动请求取消的就不执行
            return;
        }

        if (!NetUtils.isNetworkConnected()) {
            onNoNet("暂无网络");
            return;
        }

        String errorString = "";

        int errorCode = 0;

        if (t instanceof UnknownHostException) { // 网络错误
            errorString = "网络加载异常";
            errorCode = NETERRORCODE1;
        } else if (t instanceof SocketTimeoutException) {//响应超时
            errorString = "服务器响应超时";
            errorCode = NETERRORCODE2;
        } else if (t instanceof ConnectException) {//请求超时
            errorString = "网络请求超时";
            errorCode = NETERRORCODE3;
        } else {
            errorString = "未知错误";
            errorCode = NETERRORCODE4;
        }

        if (LogUtil.isLog) {
            errorString += t.toString();
        }

        onFailure(errorCode, errorString);
        this.context = null;
    }

    /**
     * 检查错误码
     *
     * @param baseModelNew 根据返回错误的状态码实现相应的操作
     */
    protected void checkState(BaseResponseModel baseModelNew) {

        String state = baseModelNew.getErrorCode();

        if (REQUESTOK.equals(state)) { //请求成功

            T t = (T) baseModelNew.getData();

            if(t==null){
                onFinish();
                onNull();
                this.context=null;
                return;
            }

            onSuccess(t, baseModelNew.getErrorInfo());

        } else if ( REQUESTFECODE4.equals(state)) {
            onOKFailure(baseModelNew.getErrorInfo());
        } else if(REQUESTFECODE2.equals(state)|| REQUESTFECODE3.equals(state) || REQUESTFECODE9.equals(state)){
            ToastUtil.show(context,baseModelNew.getErrorInfo());
        } else {
            onFailure(NETERRORCODE0, baseModelNew.getErrorInfo());
        }

    }


    /**
     * 请求成功
     *
     * @param data
     */
    protected abstract void onSuccess(T data, String SucMessage);

    /**
     * 请求失败
     *
     * @param errorCode
     * @param errorMessage
     */
    private   void onFailure(int errorCode, String errorMessage){
        ToastUtil.show(context,errorMessage);
    }

    /**
     * 请求成功 但是服务器状态错误  如 被迫下线
     *
     * @param error
     */
    protected void onOKFailure(String error) {
        OnOkFailure.StartDoFailure(context, error);
    }


    /**
     * 请求数据为空
     */
    protected abstract void onNull();

    /**
     * 请求结束 无论请求成功或者失败都会被调用
     */
    protected abstract void onFinish();

    /**
     * 无网络
     */
    protected  void onNoNet(String msg){
       ToastUtil.show(context,msg);
    }

}
