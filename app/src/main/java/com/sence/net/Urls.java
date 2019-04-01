package com.sence.net;

/**
 * Created by zwy on 2019/3/19.
 * package_name is com.sence.net
 * 描述:SenceGit
 */
public class Urls {
    public static boolean isTest = true;

    public static final String true_ip = "http://192.168.1.10:8085";
    public static final String test_ip = "http://192.168.1.10:8085";
    public static final String base_url = isTest ? test_ip : true_ip;
    public static final String SYSTEM_TIME = "/index.php/Api/Base/getServerTime";//获取服务器时间
    //评论
    public static final String COMMENT_DELETE = "/index.php/Api/Public/delMsg";//删除评论
    public static final String COMMENT_SUPPORT = "/index.php/Api/Public/giveLike";//评论点赞，取消点赞
    public static final String COMMENT_ADD = "/index.php/Api/Public/addMsg";//添加评论
    public static final String COMMENT_LIST = "/index.php/Api/Public/getMsgList";//评论列表
    //用户
    public static final String USER_CARD_DELETE = "/index.php/Api/mine/delCard";//删除银行卡
    public static final String USER_CARD_LIST = "/index.php/Api/mine/myCardList";//银行卡列表
    public static final String USER_ACCOUNT = "/index.php/Api/mine/myAccount";//我的账户
    public static final String USER_INFO_DATA = "/index.php/api/user/personalInfo";//我的信息
    public static final String USER_VERIFY_CHECK = "/index.php/Api/mine/checkCode";//检查验证码
    public static final String USER_GET_REAL_NAME = "/index.php/Api/mine/getRealName";//获取真实姓名
    public static final String USER_UP_HEAD = "/index.php/Api/mine/uploadAvatar";//上传头像
    public static final String USER_PAY_PW_CHANGE = "/index.php/Api/mine/modPayPwd";//修改支付密码
    public static final String USER_PW_CHANGE = "/index.php/Api/mine/editLoginPwd";//修改登录密码，忘记密码
    public static final String USER_INFO = "/index.php/Api/mine/mine";//我的
    public static final String USER_AUTH = "/index.php/Api/User/realNameAuth";//添加银行卡，视频认证
    public static final String USER_IS_REGISTER = "/index.php/Api/User/isRegister";//用户是否注册
    public static final String USER_SEND_CODE = "/index.php/Api/User/makeCode";//发送验证码
    public static final String USER_LOGIN = "/index.php/Api/User/weChatLogin";//用户登录
    public static final String USER_SIGN_UP = "/index.php/Api/User/weChatSignUp";//用户注册
    public static final String USER_VIP = "/index.php/Api/User/getMemberInfo";//Sence会员界面
    public static final String USER_ENJOY_VIP = "/index.php/Api/User/getEnjoyInfo";//尊享会员界面
    //主页
    public static final String MAIN_NOTE_DETAIL = "/index.php/Api/Notes/getNoteDetail";//笔记详情
    public static final String MAIN_RECOMMEND = "/index.php/Api/Notes/recommendNoteList";//首页--推荐
    public static final String MAIN_FOCUS = "/index.php/Api/Notes/focusNoteList";//首页--关注
    public static final String MAIN_NOTE = "/index.php/Api/Notes/getNoteList";//首页--笔记
    public static final String NOTE_DELETE = "/index.php/Api/Notes/delUserNote";//删除笔记
    public static final String NOTE_ADD = "/index.php/Api/Notes/addUserNote";//添加笔记
    //商品
    public static final String ORDER_DETAIL = "/index.php/Api/Orders/getOrderDetail";//订单详情
    public static final String BUS_RECOMMEND = "/index.php/Api/Goods/getCommentGoods";//购物车页面推荐商品数据
    public static final String BUS_LIST = "/index.php/Api/Goods/getCartList";//购物车列表
    public static final String ORDER_PAY = "/index.php/Api/Orders/payOrder";//支付订单
    public static final String COMMENT_SHOP_LIST = "/index.php/Api/Goods/getGoodsCommentList";//商品评论列表
    public static final String ORDER_COMMENT = "/index.php/Api/Orders/evaluOrder";//评价订单
    public static final String ORDER_COMMIT = "/index.php/Api/Orders/submitUserOrder";//提交订单
    public static final String ORDER_LIST = "/index.php/Api/Orders/getUserOrders";//订单列表
    public static final String BUS_ADD_CUT = "/index.php/Api/Goods/handleToCart";//购物车数量增加减少
    public static final String GOOD_DETAIL = "/index.php/Api/Goods/getGoodsDetail";//商品详情
    public static final String GOOD_LIST = "/index.php/Api/Goods/getGoodsList";//商品列表
    public static final String GOOD_KIND = "/index.php/Api/Goods/getGoodsClassify";//商品分类
    public static final String ADDRESS_LIST = "/index.php/Api/Address/getAddressList";//用户地址列表
    public static final String ADDRESS_DELETE = "/index.php/Api/Address/delAddress";//删除收货地址
    public static final String ADDRESS_ADD = "/index.php/Api/Address/addAddress";//添加收货地址
    public static final String ORDER_DELETE = "/index.php/Api/Orders/cancelOrder";//取消待付款，待支付订单
    public static final String ADDRESS_EDIT = "/index.php/Api/Address/editAddress";//编辑收货地址
    //服务
    public static final String SERVE_DETAIL = "/index.php/Api/Serve/getServeDetail";//服务详情
    public static final String SERVE_COMMENT_LIST = "/index.php/Api/Serve/getServiceComments";//服务评论列表
    public static final String SERVE_COMMENT_ADD = "/index.php/Api/Orders/evaluateOrder";//添加服务评价
}
