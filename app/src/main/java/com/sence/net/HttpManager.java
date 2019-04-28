package com.sence.net;

import android.widget.Toast;

import com.blankj.utilcode.util.NetworkUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.orhanobut.logger.Logger;
import com.sence.base.BaseApp;
import com.sence.bean.base.BaseFileRequestBean;
import com.sence.bean.base.BaseImageRequestBean;
import com.sence.bean.base.BaseRequestBean;
import com.sence.bean.base.BaseResponseBean;
import com.sence.net.bean.ErrorConstants;
import com.sence.net.bean.HttpJsonErrorBean;
import com.sence.net.manager.ApiCallBack;
import com.sence.net.manager.HttpClientManager;
import com.sence.net.manager.MessageApiCallBack;

import java.io.EOFException;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeoutException;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by zwy on 2019/3/19.
 * package_name is com.sence.net
 * 描述:SenceGit
 */
public class HttpManager<P> {
    private static HttpManager httpManager;

    public static HttpManager getInstance() {
        if (httpManager == null) {
            synchronized (HttpManager.class) {
                if (httpManager == null) {
                    httpManager = new HttpManager();
                }
            }
        }
        return httpManager;
    }

    private Observable observable;

    /**
     * 添加请求识别Code
     *
     * @param code
     * @return
     */
    public HttpManager PlayNetCode(HttpCode code, BaseRequestBean requestBean) {
        HttpService httpService = HttpClientManager.Instance.httpService;
        switch (code) {
            case IS_REGISTER:
                observable = httpService.IsRegister(requestBean.getMap());
                break;
            case ADDRESS_ADD:
                observable = httpService.AddressAdd(requestBean.getMap());
                break;
            case ADDRESS_EDIT:
                observable = httpService.AddressEdit(requestBean.getMap());
                break;
            case ORDER_LIST:
                observable = httpService.OrderList(requestBean.getMap());
                break;
            case ADDRESS_LIST:
                observable = httpService.AddressList(requestBean.getMap());
                break;
            case ADDRESS_DELETE:
                observable = httpService.AddressDelete(requestBean.getMap());
                break;
            case SEND_VERIFY_CODE:
                observable = httpService.SendVerifyCode(requestBean.getMap());
                break;
            case REGISTER:
                observable = httpService.Register(requestBean.getMap());
                break;
            case GOOD_DETAIL:
                observable = httpService.GoodDetail(requestBean.getMap());
                break;
            case SERVE_DETAIL:
                observable = httpService.ServeDetail(requestBean.getMap());
                break;
            case COMMENT_SHOP_LIST:
                observable = httpService.CommentShopList(requestBean.getMap());
                break;
            case SERVE_COMMENT_LIST:
                observable = httpService.ServeCommentList(requestBean.getMap());
                break;
            case CONFIRM_TAKE_GOOD:
                observable = httpService.ConfirmTakeGood(requestBean.getMap());
                break;
            case DELETE_DONE_ORDER:
                observable = httpService.Delete_DoneOrder(requestBean.getMap());
                break;
            case ORDER_DETAIL:
                observable = httpService.OrderDetail(requestBean.getMap());
                break;
            case TIME_REMAINING:
                observable = httpService.Time_Remaining(requestBean.getMap());
                break;
            case ORDER_COMMENT_SUPPORT:
                observable = httpService.OrderCommentSupport(requestBean.getMap());
                break;
            case USER_ENJOY_VIP:
                observable = httpService.EnjoyVip(requestBean.getMap());
                break;
            case USER_FOCUS:
                observable = httpService.UserFocus(requestBean.getMap());
                break;
            case USER_FOCUS_CANCEL:
                observable = httpService.UserFocusCancel(requestBean.getMap());
                break;
            case ORDER_DELETE:
                observable = httpService.OrderDelete(requestBean.getMap());
                break;
            case USER_AUTH:
                observable = httpService.RealAutonym(requestBean.getMap());
                break;
            case USER_INFO_DATA:
                observable = httpService.UserInfoData(requestBean.getMap());
                break;
            case USER_MYINFO:
                observable = httpService.UserMyInfo(requestBean.getMap());
                break;
            case USER_INFO_DATA_SERVICE:
                observable = httpService.UserInfoDataService(requestBean.getMap());
                break;
            case USER_INFO:
                observable = httpService.UserInfo(requestBean.getMap());
                break;
            case BUS_GOODS:
                observable = httpService.BusList(requestBean.getMap());
                break;
            case USER_DETAIL:
                observable = httpService.UserDetail(requestBean.getMap());
                break;
            case MAIN_NOTE:
                observable = httpService.MainNote(requestBean.getMap());
                break;
            case MAIN_FOCUS:
                observable = httpService.MainFocus(requestBean.getMap());
                break;
            case MAIN_RECOMMEND:
                observable = httpService.MainRecommend(requestBean.getMap());
                break;
            case MESSAGE_INFORM:
                observable = httpService.MessageInform(requestBean.getMap());
                break;
            case MESSAGE_LIST:
                observable = httpService.MessageList(requestBean.getMap());
                break;
            case GET_UPNAME:
                observable = httpService.GetUpName(requestBean.getMap());
                break;
            case BLACK_LIST:
                observable = httpService.BlackList(requestBean.getMap());
                break;
            case GET_INTRODUCER:
                observable = httpService.GetIntreoducer(requestBean.getMap());
                break;
            case SHARE_ADD_WATER:
                observable = httpService.ShareAddWater(requestBean.getMap());
                break;
            case RACHEL:
                observable = httpService.Rachel(requestBean.getMap());
                break;
            case REPORT:
                observable = httpService.Report(requestBean.getMap());
                break;
            case SYSTEM_MESSAGE:
                observable = httpService.SystemMessage(requestBean.getMap());
                break;
            case MESSAGE_CENTRE:
                observable = httpService.MessageCentre(requestBean.getMap());
                break;
            case KIND_GOODS_LIST:
                observable = httpService.GoodList(requestBean.getMap());
                break;
            case BUS_RECOMMEND:
                observable = httpService.BusRecommend(requestBean.getMap());
                break;
            case USER_VIP:
                observable = httpService.UserVip(requestBean.getMap());
                break;
            case USER_LOGIN:
                observable = httpService.Login(requestBean.getMap());
                break;
            case KIND_GOODS:
                observable = httpService.GoodKind(requestBean.getMap());
                break;
            case BUS_ADD:
                observable = httpService.BusAddCut(requestBean.getMap());
                break;
            case MAIN_SEARCH:
                observable = httpService.Search(requestBean.getMap());
                break;
            case USER_FANS_LIST:
                observable = httpService.UserFansList(requestBean.getMap());
                break;
            case USER_FOCUS_LIST:
                observable = httpService.UserFocusList(requestBean.getMap());
                break;
            case USER_NOTE_LIST:
                observable = httpService.UserNoteList(requestBean.getMap());
                break;
            case USER_ACCOUNT:
                observable = httpService.MyAccount(requestBean.getMap());
                break;
            case NOTE_DETAIL:
                observable = httpService.NoteDetail(requestBean.getMap());
                break;
            case CONTENT_DETAIL:
                observable = httpService.ContentDetail(requestBean.getMap());
                break;
            case COMMENT_LIST:
                observable = httpService.CommentList(requestBean.getMap());
                break;
            case SUPPORT_NOTE_RECOMMEND:
                observable = httpService.SupportNoteRecommend(requestBean.getMap());
                break;
            case BANK_CARD:
                observable = httpService.BankCardList(requestBean.getMap());
                break;
            case USER_CASH:
                observable = httpService.UserCash(requestBean.getMap());
                break;
            case DEFAULT_ADDRESS:
                observable = httpService.DefaultAddress(requestBean.getMap());
                break;
            case USER_GOOD_LIST:
                observable = httpService.UserGoodList(requestBean.getMap());
                break;
            case USER_SERVE_LIST:
                observable = httpService.UserServeList(requestBean.getMap());
                break;
            case BANK_LIST:
                observable = httpService.BankList(requestBean.getMap());
                break;
            case ORDER_COMMIT:
                observable = httpService.OrderCommit(requestBean.getMap());
                break;
            case BANK_ADD:
                observable = httpService.BankCardAdd(requestBean.getMap());
                break;
            case BIND_CLIENT_ID:
                observable = httpService.BindClientID(requestBean.getMap());
                break;
            case CHAT_CREATE_GROUP:
                observable = httpService.ChatCreateGroup(requestBean.getMap());
                break;
            case CHAT_GROUP_LIST:
                observable = httpService.ChatGroupList(requestBean.getMap());
                break;
            case PRIVATE_CHAT_LIST:
                observable = httpService.PrivateChatList(requestBean.getMap());
                break;
            case CHAT_MEMBER_LIST:
                observable = httpService.ChatMemberList(requestBean.getMap());
                break;
            case CHAT_ENTER:
                observable = httpService.ChatEnter(requestBean.getMap());
                break;
            case CHAT_JOIN:
                observable = httpService.ChatJoin(requestBean.getMap());
                break;
            case CHAT_SEND_MESSAGE:
                observable = httpService.ChatSendMessage(requestBean.getMap());
                break;
            case CHAT_READ_GROUP:
                observable = httpService.ChatRead(requestBean.getMap());
                break;
            case COMMENT_DETAIL_ADD:
                observable = httpService.CommentAdd(requestBean.getMap());
                break;
            case COMMENT_SUPPORT:
                observable = httpService.CommentSupport(requestBean.getMap());
                break;
            case PAY_ALI:
                observable = httpService.PayAli(requestBean.getMap());
                break;
            case PAY_WX:
                observable = httpService.PayWx(requestBean.getMap());
                break;
            case START_PICTURE:
                observable = httpService.StartPicture(requestBean.getMap());
                break;
            case UPDATE_APP:
                observable = httpService.UpdateApp(requestBean.getMap());
                break;
            case CHAT_PRIVATE_LIST:
                observable = httpService.ChatPrivateList(requestBean.getMap());
                break;
            case CHAT_PRIVATE_READ:
                observable = httpService.ChatPrivateRead(requestBean.getMap());
                break;
            case CHAT_PRIVATE_SEND:
                observable = httpService.ChatSendPrivateMessage(requestBean.getMap());
                break;
            case VIP_OPEN:
                observable = httpService.VipOpen(requestBean.getMap());
                break;
            case CHAT_BAN:
                observable = httpService.ChatBan(requestBean.getMap());
                break;
            case NOTE_DELETE:
                observable = httpService.NoteDelete(requestBean.getMap());
                break;

        }
        observable = observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        return this;
    }

    public HttpManager PlayNetCode(HttpCode code, BaseImageRequestBean requestBean,
                                   BaseFileRequestBean fileRequestBean) {
        HttpService httpService = HttpClientManager.Instance.httpService;
        switch (code) {
            case COMMENT_ADD:
                observable = httpService.CommentOrder(requestBean.getMap(), fileRequestBean.getRequestImg());
                break;
            case SERVICE_ADD_COMMENT:
                observable = httpService.SearviceAddComment(requestBean.getMap(), fileRequestBean.getRequestImg());
                break;
            case USER_EDIT:
                observable = httpService.UserEdit(requestBean.getMap(), fileRequestBean.getRequestImg());
                break;
            case CHAT_SEND_MESSAGE:
                observable = httpService.ChatSendImgMessage(requestBean.getMap(), fileRequestBean.getRequestImg());
                break;
            case NOTE_ADD:
                observable = httpService.NoteAdd(requestBean.getMap(), fileRequestBean.getRequestImg());
                break;
            case CHAT_PRIVATE_SEND:
                observable = httpService.ChatSendPrivateImgMessage(requestBean.getMap(),
                        fileRequestBean.getRequestImg());
                break;
        }
        return this;
    }


    /**
     * 添加请求识别Code
     *
     * @param code
     * @return
     */
    public HttpManager PlayNetCode(HttpCode code) {
        switch (code) {
            case GET_SYSTEM_TIME:
                observable = HttpClientManager.Instance.httpService.getSystemTime();
                break;
            case SEARCH_RECOMMEND:
                observable = HttpClientManager.Instance.httpService.SearchRecommend();
                break;


        }
        observable = observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        return this;
    }


    /**
     * 去请求
     *
     * @param apiCallBack
     */
    public void request(final ApiCallBack<P> apiCallBack) {
        if (apiCallBack == null) {
            return;
        }
        observable.subscribe(new Observer<BaseResponseBean<P>>() {
            Disposable disposable = null;

            @Override
            public void onSubscribe(Disposable d) {
                disposable = d;
            }

            @Override
            public void onNext(BaseResponseBean<P> result) {
                Logger.e("status is " + result.getStatus() + "\nmsg is " + result.getMsg() + "\ndata is " + result.getData().toString());
                if (!disposable.isDisposed()) {
                    if (result.getStatus() == 1) {
                        if (apiCallBack instanceof MessageApiCallBack) {
                            ((MessageApiCallBack) apiCallBack).onSuccessCount(result.getData(), result.getMsg(),
                                    result.getCount());
                        } else {
                            apiCallBack.onSuccess(result.getData(), result.getMsg());
                        }
                    } else {
                        ToastUtils.showShort(result.getMsg());
                        apiCallBack.Message(result.getStatus(), result.getMsg());
                    }
                    disposable.dispose();
                }
                apiCallBack.onFinish();
            }


            @Override
            public void onError(Throwable e) {
                Logger.e(e.toString());
                if (!NetworkUtils.isConnected()) {
                    Toast.makeText(BaseApp.INSTANCE,
                            "无法连接网络",
                            Toast.LENGTH_SHORT).show();
                } else if (e instanceof SocketTimeoutException
                        || e instanceof TimeoutException) {
                    Toast.makeText(BaseApp.INSTANCE,
                            "网络不给力",
                            Toast.LENGTH_SHORT).show();
                } else if (e instanceof JsonSyntaxException) {
                    Logger.e("error_content====" + ErrorConstants.error_content);
                    HttpJsonErrorBean errorBean = new Gson().fromJson(ErrorConstants.error_content,
                            HttpJsonErrorBean.class);
                    apiCallBack.Message(errorBean.getStatus(), errorBean.getMsg());
                } else if (e instanceof EOFException) {
                    Toast.makeText(BaseApp.INSTANCE,
                            "上传失败", Toast.LENGTH_SHORT).show();
                } else {
                    Logger.e("error_content====" + ErrorConstants.error_content);
                    Toast.makeText(BaseApp.INSTANCE,
                            "请求失败", Toast.LENGTH_SHORT).show();
                }
                apiCallBack.onFinish();
            }

            @Override
            public void onComplete() {
                apiCallBack.onFinish();
            }
        });
    }

}
