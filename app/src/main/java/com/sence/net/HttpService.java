package com.sence.net;

import com.sence.bean.base.BaseResponseBean;
import com.sence.bean.response.PManageAddressBean;
import com.sence.bean.response.PMyOrderBean;
import com.sence.bean.response.POrderDetailsBean;
import com.sence.bean.response.PServiceCommendBean;
import com.sence.bean.response.PServiceeDetails;
import com.sence.bean.response.PShopCommendBean;
import com.sence.bean.response.PShopDetailsBean;

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

    @FormUrlEncoded
    @POST(Urls.GOOD_DETAIL)
    Observable<BaseResponseBean<PShopDetailsBean>> GoodDetail(@FieldMap Map<String, Object> map);//商品详情

    @FormUrlEncoded
    @POST(Urls.SERVE_DETAIL)
    Observable<BaseResponseBean<PServiceeDetails>> ServeDetail(@FieldMap Map<String, Object> map);//服务详情

    @FormUrlEncoded
    @POST(Urls.COMMENT_SHOP_LIST)
    Observable<BaseResponseBean<List<PShopCommendBean>>> CommentShopList(@FieldMap Map<String, Object> map);//商品评论列表

    @FormUrlEncoded
    @POST(Urls.SERVE_COMMENT_LIST)
    Observable<BaseResponseBean<List<PServiceCommendBean>>> ServeCommentList(@FieldMap Map<String, Object> map);//商品评论列表

    @FormUrlEncoded
    @POST(Urls.ORDER_DETAIL)
    Observable<BaseResponseBean<POrderDetailsBean>> OrderDetail(@FieldMap Map<String, Object> map);//商品评论列表
}
