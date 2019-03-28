package com.sence.net;

/**
 * 网络请求Code识别
 */
public enum HttpCode {
    GET_SYSTEM_TIME,//获取系统消息
    IS_REGISTER,//是否注册
    ADDRESS_ADD,//添加地址
    ORDER_LIST,//订单列表
    ADDRESS_LIST,//地址列表
    ADDRESS_DELETE,//删除地址
    GOOD_DETAIL,//商品详情
    SEND_VERIFY_CODE,//发送验证码
    CHECK_VERIFY_CODE,//校验验证码
    REGISTER,//注册
}
