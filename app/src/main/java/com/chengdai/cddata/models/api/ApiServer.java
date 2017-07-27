package com.chengdai.cddata.models.api;


import com.chengdai.cddata.models.certification.models.FraudLookModel;
import com.chengdai.cddata.models.certification.models.FraudNumCheckModel;
import com.chengdai.cddata.models.certification.models.GetFraudNumModel;
import com.chengdai.cddata.models.certification.models.IndustryFocusOnModel;
import com.chengdai.cddata.models.certification.models.UserQueryInfoModel;
import com.chengdai.cddata.models.certification.models.UserQueryModel;
import com.chengdai.cddata.models.certification.models.ZMCertFirstStepModel;
import com.chengdai.cddata.models.certification.models.ZmNumGetFirstStepModel;
import com.chengdai.cddata.models.common.models.IsSuccessModes;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by 李先俊 on 2017/6/8.
 */

public interface ApiServer {


    /**
     * 二元素认证
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseModel<IsSuccessModes>> idCardAndNameCheck(@Field("code") String code, @Field("json") String  json);

    /**
     * 芝麻认证第一步
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseModel<ZMCertFirstStepModel>> ZMCertOne(@Field("code") String code, @Field("json") String  json);

    /**
     * 芝麻认证第二步（结果查询）
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseModel<IsSuccessModes>> ZMCertTwo(@Field("code") String code, @Field("json") String  json);

    /**
     * 芝麻分获取第一步
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseModel<ZmNumGetFirstStepModel>> ZMNumGetOne(@Field("code") String code, @Field("json") String  json);

    /**
     * 用户授权查询
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseModel<UserQueryModel>> userQuery(@Field("code") String code, @Field("json") String  json);

    /**
     * 获取信用分
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseModel<String>> getZMNum(@Field("code") String code, @Field("json") String  json);

    /*获取芝麻分 二*/
    /**
     * 获取信用分
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseModel<UserQueryInfoModel>> userInfoQuery(@Field("code") String code, @Field("json") String  json);


    /**
     * 行业关注列表
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseModel<IndustryFocusOnModel>> IndustryFocusOnInfoQuery(@Field("code") String code, @Field("json") String  json);

    /**
     * 申请欺诈评分
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseModel<GetFraudNumModel>> getFraudNum(@Field("code") String code, @Field("json") String  json);

    /**
     * 申请欺诈评分
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseModel<FraudNumCheckModel>> fraudNumCheck(@Field("code") String code, @Field("json") String  json);
    /**
     * 申请欺诈评分
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseModel<FraudLookModel>> fraudLookList(@Field("code") String code, @Field("json") String  json);

}
