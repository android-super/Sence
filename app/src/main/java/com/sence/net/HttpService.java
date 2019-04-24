package com.sence.net;

import com.sence.bean.base.BaseResponseBean;
import com.sence.bean.request.RMemberBean;
import com.sence.bean.response.*;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.*;

/**
 * Created by zwy on 2019/3/19.
 * package_name is com.sence.net
 * 描述:SenceGit
 */
public interface HttpService {
    @GET(Urls.SYSTEM_TIME)
    Observable<BaseResponseBean<String>> getSystemTime();//获取系统时间

    @POST(Urls.SEARCH_RECOMMEND)
    Observable<BaseResponseBean<List<PSearchRecommendBean>>> SearchRecommend();//搜索推荐数据

    @FormUrlEncoded
    @POST(Urls.USER_IS_REGISTER)
    Observable<BaseResponseBean<String>> IsRegister(@FieldMap Map<String, Object> map);//用户是否注册

    @FormUrlEncoded
    @POST(Urls.ADDRESS_ADD)
    Observable<BaseResponseBean<String>> AddressAdd(@FieldMap Map<String, Object> map);//添加收货地址

    @FormUrlEncoded
    @POST(Urls.ADDRESS_EDIT)
    Observable<BaseResponseBean<String>> AddressEdit(@FieldMap Map<String, Object> map);//编辑收货地址

    @FormUrlEncoded
    @POST(Urls.CONFIRM_TAKEGOOD)
    Observable<BaseResponseBean<String>> ConfirmTakeGood(@FieldMap Map<String, Object> map);//确认收货

    @FormUrlEncoded
    @POST(Urls.DELETE_DONEORDER)
    Observable<BaseResponseBean<String>> Delete_DoneOrder(@FieldMap Map<String, Object> map);//确认收货

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
    Observable<BaseResponseBean<POrderDetailsBean>> OrderDetail(@FieldMap Map<String, Object> map);//订单详情

    @FormUrlEncoded
    @POST(Urls.USER_SEND_CODE)
    Observable<BaseResponseBean<PVerifyCodeBean>> SendVerifyCode(@FieldMap Map<String, Object> map);//发送验证码

    @FormUrlEncoded
    @POST(Urls.USER_FOCUS_CANCEL)
    Observable<BaseResponseBean<Object>> UserFocusCancel(@FieldMap Map<String, Object> map);//取消关注

    @FormUrlEncoded
    @POST(Urls.USER_FOCUS)
    Observable<BaseResponseBean<String>> UserFocus(@FieldMap Map<String, Object> map);//关注

    @FormUrlEncoded
    @POST(Urls.USER_LOGIN)
    Observable<BaseResponseBean<PUserBean>> Login(@FieldMap Map<String, Object> map);//登录

    @FormUrlEncoded
    @POST(Urls.USER_AUTH)
    Observable<BaseResponseBean<String>> RealAutonym(@FieldMap Map<String, Object> map);//添加银行卡，实名认证

    @FormUrlEncoded
    @POST(Urls.USER_SIGN_UP)
    Observable<BaseResponseBean<PUserBean>> Register(@FieldMap Map<String, Object> map);//注册

    @FormUrlEncoded
    @POST(Urls.USER_INFO)
    Observable<BaseResponseBean<PUserInfoBean>> UserInfo(@FieldMap Map<String, Object> map);//我的信息

    @FormUrlEncoded
    @POST(Urls.USER_PW_CHANGE)
    Observable<BaseResponseBean<String>> ChangeLoginPW(@FieldMap Map<String, Object> map);//修改登录密码

    @FormUrlEncoded
    @POST(Urls.USER_PAY_PW_CHANGE)
    Observable<BaseResponseBean<String>> ChangePayPW(@FieldMap Map<String, Object> map);//修改支付密码

    @FormUrlEncoded
    @POST(Urls.USER_UP_HEAD)
    Observable<BaseResponseBean<String>> UpHead(@FieldMap Map<String, Object> map);//编辑个人资料，上传头像

    @FormUrlEncoded
    @POST(Urls.USER_GET_REAL_NAME)
    Observable<BaseResponseBean<String>> GetRealName(@FieldMap Map<String, Object> map);//获取用户真实姓名

    @FormUrlEncoded
    @POST(Urls.USER_VERIFY_CHECK)
    Observable<BaseResponseBean<String>> CheckVerify(@FieldMap Map<String, Object> map);//检查验证码（忘记密码时）

    @FormUrlEncoded
    @POST(Urls.USER_ACCOUNT)
    Observable<BaseResponseBean<PAccountBean>> MyAccount(@FieldMap Map<String, Object> map);//我的账户

    @FormUrlEncoded
    @POST(Urls.USER_CARD_LIST)
    Observable<BaseResponseBean<List<PBankCardBean>>> BankCardList(@FieldMap Map<String, Object> map);//我的银行卡列表

    @FormUrlEncoded
    @POST(Urls.MESSAGE_INFORM)
    Observable<BaseResponseBean<List<PInformBean>>> MessageInform(@FieldMap Map<String, Object> map);//通知列表

    @FormUrlEncoded
    @POST(Urls.GET_INTRODUCER)
    Observable<BaseResponseBean<String>> GetIntreoducer(@FieldMap Map<String, Object> map);//通过邀请码绑定介绍人

    @FormUrlEncoded
    @POST(Urls.BLACK_LIST)
    Observable<BaseResponseBean<List<PBlackListBean>>> BlackList(@FieldMap Map<String, Object> map);//拉黑列表

    @FormUrlEncoded
    @POST(Urls.RACHEL)
    Observable<BaseResponseBean<String>> Rachel(@FieldMap Map<String, Object> map);//拉黑

    @FormUrlEncoded
    @POST(Urls.REPORT)
    Observable<BaseResponseBean<String>> Report(@FieldMap Map<String, Object> map);//举报

    @FormUrlEncoded
    @POST(Urls.GET_UPNAME)
    Observable<BaseResponseBean<PBindingLinkBean>> GetUpName(@FieldMap Map<String, Object> map);//通过邀请码获得用户名

    @FormUrlEncoded
    @POST(Urls.MESSAGE_LIST)
    Observable<BaseResponseBean<List<PMessageHdBean>>> MessageList(@FieldMap Map<String, Object> map);//消息中心 - 评论、点赞列表

    @FormUrlEncoded
    @POST(Urls.SYSTEM_MESSAGE)
    Observable<BaseResponseBean<List<PInformBean>>> SystemMessage(@FieldMap Map<String, Object> map);//系统消息列表

    @FormUrlEncoded
    @POST(Urls.MESSAGE_CENTRE)
    Observable<BaseResponseBean<PMessageBean>> MessageCentre(@FieldMap Map<String, Object> map);//消息中心

    @FormUrlEncoded
    @POST(Urls.USER_CARD_DELETE)
    Observable<BaseResponseBean<String>> BankCardDelete(@FieldMap Map<String, Object> map);//删除银行卡

    @FormUrlEncoded
    @POST(Urls.USER_BANK_CARD_LIST)
    Observable<BaseResponseBean<List<PBankCardBean>>> BankList(@FieldMap Map<String, Object> map);//银行卡列表

    @FormUrlEncoded
    @POST(Urls.USER_AUTH)
    Observable<BaseResponseBean<Object>> BankCardAdd(@FieldMap Map<String, Object> map);//银行卡列表

    @FormUrlEncoded
    @POST(Urls.COMMENT_LIST)
    Observable<BaseResponseBean<List<PCommentBean>>> CommentList(@FieldMap Map<String, Object> map);//评论列表

    @FormUrlEncoded
    @POST(Urls.COMMENT_ADD)
    Observable<BaseResponseBean<String>> CommentAdd(@FieldMap Map<String, Object> map);//添加评论

    @FormUrlEncoded
    @POST(Urls.COMMENT_SUPPORT)
    Observable<BaseResponseBean<String>> CommentSupport(@FieldMap Map<String, Object> map);//评论点赞，取消点赞

    @FormUrlEncoded
    @POST(Urls.COMMENT_DELETE)
    Observable<BaseResponseBean<String>> CommentDelete(@FieldMap Map<String, Object> map);//删除评论

    @Multipart
    @POST(Urls.SERVICE_ADDCOMMENT)
    Observable<BaseResponseBean<String>> SearviceAddComment(@PartMap Map<String, RequestBody> map,
                                                            @Part List<MultipartBody.Part> partList);//添加服务评价

    @FormUrlEncoded
    @POST(Urls.MAIN_NOTE_DETAIL)
    Observable<BaseResponseBean<PNoteDetailBean>> NoteDetail(@FieldMap Map<String, Object> map);//笔记详情

    @FormUrlEncoded
    @POST(Urls.MAIN_RECOMMEND)
    Observable<BaseResponseBean<PMainBean>> MainRecommend(@FieldMap Map<String, Object> map);//首页推荐

    @FormUrlEncoded
    @POST(Urls.MAIN_FOCUS)
    Observable<BaseResponseBean<PMainBean>> MainFocus(@FieldMap Map<String, Object> map);//首页关注

    @FormUrlEncoded
    @POST(Urls.NOTE_DELETE)
    Observable<BaseResponseBean<String>> NoteDelete(@FieldMap Map<String, Object> map);//删除笔记

    @Multipart
    @POST(Urls.NOTE_ADD)
    Observable<BaseResponseBean<String>> NoteAdd(@PartMap Map<String, RequestBody> map,
                                                 @Part List<MultipartBody.Part> partList);//添加笔记

    @FormUrlEncoded
    @POST(Urls.MAIN_SEARCH)
    Observable<BaseResponseBean<PSearchBean>> Search(@FieldMap Map<String, Object> map);//主页搜索

    @FormUrlEncoded
    @POST(Urls.MAIN_NOTE)
    Observable<BaseResponseBean<PMainBean>> MainNote(@FieldMap Map<String, Object> map);//首页笔记


    @FormUrlEncoded
    @POST(Urls.GOOD_KIND)
    Observable<BaseResponseBean<List<PKindBean>>> GoodKind(@FieldMap Map<String, Object> map);//商品分类列表


    @FormUrlEncoded
    @POST(Urls.USER_ENJOY_VIP)
    Observable<BaseResponseBean<PEnjoyVipBean>> EnjoyVip(@FieldMap Map<String, Object> map);//尊享会员界面

    @FormUrlEncoded
    @POST(Urls.ORDER_DELETE)
    Observable<BaseResponseBean<String>> OrderDelete(@FieldMap Map<String, Object> map);//取消待付款，待支付订单

    @FormUrlEncoded
    @POST(Urls.GOOD_LIST)
    Observable<BaseResponseBean<List<PGoodListBean>>> GoodList(@FieldMap Map<String, Object> map);//商品列表

    @FormUrlEncoded
    @POST(Urls.BUS_LIST)
    Observable<BaseResponseBean<PBusBean>> BusList(@FieldMap Map<String, Object> map);//购物车列表

    @FormUrlEncoded
    @POST(Urls.BUS_RECOMMEND)
    Observable<BaseResponseBean<List<PBusRecommendBean>>> BusRecommend(@FieldMap Map<String, Object> map);//购物车页面推荐商品数据

    @FormUrlEncoded
    @POST(Urls.USER_VIP)
    Observable<BaseResponseBean<PUserVipBean>> UserVip(@FieldMap Map<String, Object> map);//Sence会员

    @FormUrlEncoded
    @POST(Urls.BUS_ADD_CUT)
    Observable<BaseResponseBean<Object>> BusAddCut(@FieldMap Map<String, Object> map);//添加购物车

    @FormUrlEncoded
    @POST(Urls.USER_FANS_LIST)
    Observable<BaseResponseBean<List<PFansBean>>> UserFansList(@FieldMap Map<String, Object> map);//我的粉絲列表

    @FormUrlEncoded
    @POST(Urls.USER_FOCUS_LIST)
    Observable<BaseResponseBean<List<PFansBean>>> UserFocusList(@FieldMap Map<String, Object> map);//我的关注列表

    @FormUrlEncoded
    @POST(Urls.USER_NOTE_LIST)
    Observable<BaseResponseBean<List<PMainNoteBean>>> UserNoteList(@FieldMap Map<String, Object> map);//我的笔记列表

    @FormUrlEncoded
    @POST(Urls.MAIN_CONTENT_DETAIL)
    Observable<BaseResponseBean<PContentDetailBean>> ContentDetail(@FieldMap Map<String, Object> map);//内容详情

    @FormUrlEncoded
    @POST(Urls.USER_INFO_DATA)
    Observable<BaseResponseBean<PUserMyInfoBean>> UserMyInfo(@FieldMap Map<String, Object> map);//我的信息

    @FormUrlEncoded
    @POST(Urls.USER_INFO_CONTENT)
    Observable<BaseResponseBean<PMyInfoBean>> UserInfoData(@FieldMap Map<String, Object> map);//我的信息内容

    @FormUrlEncoded
    @POST(Urls.USER_INFO_CONTENT)
    Observable<BaseResponseBean<PMyInfoServiceBean>> UserInfoDataService(@FieldMap Map<String, Object> map);//我的信息服务

    @FormUrlEncoded
    @POST(Urls.USER_DETAIL)
    Observable<BaseResponseBean<List<PUserDetailBean>>> UserDetail(@FieldMap Map<String, Object> map);//账户明细

    @FormUrlEncoded
    @POST(Urls.SUPPORT_NOTE_RECOMMEND)
    Observable<BaseResponseBean<Object>> SupportNoteRecommend(@FieldMap Map<String, Object> map);//笔记推荐点赞

    @FormUrlEncoded
    @POST(Urls.USER_CASH)
    Observable<BaseResponseBean<Object>> UserCash(@FieldMap Map<String, Object> map);//余额提现

    @FormUrlEncoded
    @POST(Urls.DEFAULT_ADDRESS)
    Observable<BaseResponseBean<PDefaultAddressBean>> DefaultAddress(@FieldMap Map<String, Object> map);//余额提现

    @FormUrlEncoded
    @POST(Urls.ORDER_COMMIT)
    Observable<BaseResponseBean<PConfirmOrderBean>> OrderCommit(@FieldMap Map<String, Object> map);//提交订单

    @Multipart
    @POST(Urls.ORDER_COMMENT)
    Observable<BaseResponseBean<String>> CommentOrder(@PartMap Map<String, RequestBody> map,@Part List<MultipartBody.Part> partList);//订单评论

    @Multipart
    @POST(Urls.USER_EDIT)
    Observable<BaseResponseBean<Object>> UserEdit(@PartMap Map<String, RequestBody> map,
                                                  @Part List<MultipartBody.Part> partList);//修改个人信息

    @FormUrlEncoded
    @POST(Urls.TIME_REMAINING)
    Observable<BaseResponseBean<PTimeRemainingBean>> Time_Remaining(@FieldMap Map<String, RequestBody> map);//订单剩余时间

    @FormUrlEncoded
    @POST(Urls.ORDER_COMMENT_SUPPORT)
    Observable<BaseResponseBean<String>> OrderCommentSupport(@FieldMap Map<String, RequestBody> map);//评论点赞

    @FormUrlEncoded
    @POST(Urls.USER_GOOD_LIST)
    Observable<BaseResponseBean<List<PGoodBean>>> UserGoodList(@FieldMap Map<String, Object> map);//专享列表-商品列表

    @FormUrlEncoded
    @POST(Urls.USER_SERVE_LIST)
    Observable<BaseResponseBean<List<PServeBean>>> UserServeList(@FieldMap Map<String, Object> map);//大V分享列表-服务列表

    @FormUrlEncoded
    @POST(Urls.USER_CLIENT_BIND)
    Observable<BaseResponseBean<Object>> BindClientID(@FieldMap Map<String, Object> map);//绑定Client_id

    @FormUrlEncoded
    @POST(Urls.CHAT_SEND_MESSAGE)
    Observable<BaseResponseBean<Object>> ChatSendMessage(@FieldMap Map<String, Object> map);//发送消息

    @Multipart
    @POST(Urls.CHAT_SEND_MESSAGE)
    Observable<BaseResponseBean<Object>> ChatSendImgMessage(@PartMap Map<String, RequestBody> map,
                                                            @Part List<MultipartBody.Part> partList);//发送消息

    @FormUrlEncoded
    @POST(Urls.CHAT_CREATE_GROUP)
    Observable<BaseResponseBean<Object>> ChatCreateGroup(@FieldMap Map<String, Object> map);//创建V群

    @FormUrlEncoded
    @POST(Urls.CHAT_JOIN)
    Observable<BaseResponseBean<Object>> ChatJoin(@FieldMap Map<String, Object> map);//加入V群

    @FormUrlEncoded
    @POST(Urls.CHAT_MEMBER_LIST)
    Observable<BaseResponseBean<List<RMemberBean>>> ChatMemberList(@FieldMap Map<String, Object> map);//V群成员列表

    @FormUrlEncoded
    @POST(Urls.CHAT_ENTER)
    Observable<BaseResponseBean<PChatMessageBean>> ChatEnter(@FieldMap Map<String, Object> map);//进入V群

    @FormUrlEncoded
    @POST(Urls.CHAT_GROUP_LIST)
    Observable<BaseResponseBean<Object>> ChatGroupList(@FieldMap Map<String, Object> map);//V群列表

    @FormUrlEncoded
    @POST(Urls.PRIVATE_CHAT_LIST)
    Observable<BaseResponseBean<List<PPrivateChatBean>>> PrivateChatList(@FieldMap Map<String, Object> map);//私聊列表

    @FormUrlEncoded
    @POST(Urls.CHAT_READ)
    Observable<BaseResponseBean<Object>> ChatRead(@FieldMap Map<String, Object> map);//消息已读

    @FormUrlEncoded
    @POST(Urls.PAY_ALI)
    Observable<BaseResponseBean<String>> PayAli(@FieldMap Map<String, Object> map);//支付宝支付

    @FormUrlEncoded
    @POST(Urls.PAY_WX)
    Observable<BaseResponseBean<PWxPayBean>> PayWx(@FieldMap Map<String, Object> map);//微信支付

    @FormUrlEncoded
    @POST(Urls.START_PICTURE)
    Observable<BaseResponseBean<PStartPictureBean>> StartPicture(@FieldMap Map<String, Object> map);//启动图片

    @FormUrlEncoded
    @POST(Urls.CHAT_PRIVATE_SEND)
    Observable<BaseResponseBean<Object>> ChatSendPrivateMessage(@FieldMap Map<String, Object> map);//发送消息

    @Multipart
    @POST(Urls.CHAT_PRIVATE_SEND)
    Observable<BaseResponseBean<Object>> ChatSendPrivateImgMessage(@PartMap Map<String, RequestBody> map,
                                                                   @Part List<MultipartBody.Part> partList);//发送消息

    @FormUrlEncoded
    @POST(Urls.CHAT_PRIVATE_LIST)
    Observable<BaseResponseBean<List<PChatPrivateMessageBean>>> ChatPrivateList(@FieldMap Map<String, Object> map);
    //消息列表

    @FormUrlEncoded
    @POST(Urls.CHAT_PRIVATE_READ)
    Observable<BaseResponseBean<Object>> ChatPrivateRead(@FieldMap Map<String, Object> map);//发送消息

    @FormUrlEncoded
    @POST(Urls.USER_VIP_OPEN)
    Observable<BaseResponseBean<PMoneyBean>> VipOpen(@FieldMap Map<String, Object> map);//搜索推荐数据

    @FormUrlEncoded
    @POST(Urls.CHAT_BAN)
    Observable<BaseResponseBean<Object>> ChatBan(@FieldMap Map<String, Object> map);//搜索推荐数据
}
