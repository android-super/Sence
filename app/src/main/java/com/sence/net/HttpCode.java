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
    MAIN_FOCUS,//首页关注
    MAIN_NOTE,//首頁筆記
    MAIN_RECOMMEND,//首页推荐
    VIP_MEMBER,//会员
    KIND_GOODS,//分类
    KIND_GOODS_LIST,//分类下商品列表
    BUS_GOODS,//购物车
    USER_INFO,//我的
    BUS_ADD,//加入购物车
    BUS_RECOMMEND,//购物车推荐
    USER_VIP,//Sence会员
    USER_LOGIN,//用户登录
    SERVE_DETAIL,//服务详情
    COMMENT_SHOP_LIST,//商品评论列表
    SERVE_COMMENT_LIST,//服务评论列表
    ORDER_DETAIL,//订单详情
    ORDER_DELETE,//取消待付款，待支付订单
    ENJOY_VIP,//尊享会员
}
