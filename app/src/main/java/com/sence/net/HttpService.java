package com.sence.net;

import com.sence.bean.base.BaseResponseBean;
import io.reactivex.Observable;
import retrofit2.http.*;

import java.util.Map;

/**
 * Created by zwy on 2019/3/19.
 * package_name is com.sence.net
 * 描述:SenceGit
 */
public interface HttpService {
    @GET(Urls.SYSTEM_TIME)
    Observable<BaseResponseBean<String>> getSystemTime();//获取系统时间

    @FormUrlEncoded
    @POST(Urls.USER_IS_REGISTER)
    Observable<BaseResponseBean<String>> IsRegister(@FieldMap Map<String, Object> map);//用户是否注册

    @FormUrlEncoded
    @GET(Urls.ADDRESS_ADD)
    Observable<BaseResponseBean<String>> AddressAdd(@FieldMap Map<String, Object> map);//添加收货地址


}
