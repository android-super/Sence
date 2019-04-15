package com.sence.wxapi;

import android.app.Activity;

import com.blankj.utilcode.util.ToastUtils;
import com.sence.bean.response.PWxPayBean;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.LinkedList;
import java.util.List;


/**
 * Created by aw on 2016/12/8.
 */
public class WeiXinPayUtils {

    private final PWxPayBean wxBean;
    private Activity context;
    PayReq req;
    IWXAPI msgApi;
    StringBuffer sb;

    public WeiXinPayUtils(Activity context, PWxPayBean wxBean) {
        this.context = context;
        this.wxBean = wxBean;
        req = new PayReq();
        sb = new StringBuffer();
        registerAPP();
    }

    public void registerAPP() {
        msgApi = WXAPIFactory.createWXAPI(context, wxBean.getAppid(), true);
        msgApi.registerApp(wxBean.getAppid());
    }

    public void pay() {
        genPayReq();
        sendPayReq();
    }

    private void genPayReq() {
        IWXAPI api = WXAPIFactory.createWXAPI(context, wxBean.getAppid(), true);
        if (!api.isWXAppInstalled()) {
            ToastUtils.showShort("您还未安装微信客户端");
            return;
        }
        String returnCode = wxBean.getReturn_code();
        req.appId = wxBean.getAppid();
        req.partnerId = wxBean.getMch_id();
        req.prepayId = wxBean.getPrepay_id();
        req.packageValue = wxBean.getPackageX();
        req.nonceStr = wxBean.getNonce_str();
        req.timeStamp = wxBean.getTimestamp();

        List<NameValuePair> signParams = new LinkedList<NameValuePair>();
        signParams.add(new BasicNameValuePair("appid", req.appId));
        signParams.add(new BasicNameValuePair("noncestr", req.nonceStr));
        signParams.add(new BasicNameValuePair("package", req.packageValue));
        signParams.add(new BasicNameValuePair("partnerid", req.partnerId));
        signParams.add(new BasicNameValuePair("prepayid", req.prepayId));
        signParams.add(new BasicNameValuePair("timestamp", req.timeStamp));

        req.sign = wxBean.getSign();
    }


    private void sendPayReq() {
        msgApi.registerApp(wxBean.getAppid());
        msgApi.sendReq(req);
    }
}
