package com.chengdai.cddata.widget.nets;

import com.chengdai.cddata.models.api.ApiServer;

import retrofit2.Retrofit;


/**
 *
 * 服务器api
 * Created by Administrator on 2016/9/1.
 */
public class RetrofitUtils {

    private static RetrofitUtils retrofitUtils;

    private  ApiServer apiServer;

    public RetrofitUtils() {
        apiServer = new Retrofit.Builder()
                .baseUrl(getBaseURL())
                .client(OkHttpUtils.getInstance())
                .addConverterFactory(FastJsonConVerter.create())
                .build().create(ApiServer.class);
    }

    /**
     * 服务接口单例
     *
     * @return Retrofit
     */
    public static RetrofitUtils bulid() {
        if (retrofitUtils == null) {
            retrofitUtils = new RetrofitUtils();
        }
        return retrofitUtils;
    }


    private ApiServer getApiServer() {
        return apiServer;
    }

    public static ApiServer getLoaderServer() {
        return bulid().getApiServer();
    }

    /**
     * 获取URL  根据版本切换不同版本
     *
     * @return
     */
    public static String getBaseURL() {

        return "http://121.43.101.148:8903/std-certi/";

    }

}
