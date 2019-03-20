package com.sence.net;

import com.sence.bean.base.BaseResponseBean;
import com.sence.bean.response.PManageAddressBean;
import com.sence.bean.response.PMyOrderBean;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

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

    @FormUrlEncoded
    @POST(Urls.ORDER_LIST)
    Observable<BaseResponseBean<PMyOrderBean>> OrderList(@FieldMap Map<String, Object> map);//订单列表

    @FormUrlEncoded
    @POST(Urls.ADDRESS_LIST)
    Observable<BaseResponseBean<List<PManageAddressBean>>> AddressList(@FieldMap Map<String, Object> map);//用户地址列表

    @FormUrlEncoded
    @POST(Urls.ADDRESS_DELETE)
    Observable<BaseResponseBean<String>> AddressDelete(@FieldMap Map<String, Object> map);//删除收货地址
}
