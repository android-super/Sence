package com.sence.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.sence.utils.LoginStatus;
import com.sence.utils.SharedPreferencesUtil;
import com.tencent.mm.sdk.constants.ConstantsAPI;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {

    private IWXAPI api;
    public final static int RepaymentCode = 11;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EventBus.getDefault().register(this);
        api = WXAPIFactory.createWXAPI(this, Constants.APP_ID);
        api.handleIntent(getIntent(), this);

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq baseReq) {
    }

    @Override
    public void onResp(BaseResp baseResp) {
        if (baseResp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            if (baseResp.errCode == 0) {
//                MyApp.getInstance().getActivity(WXPayActivity.class).finish();
//                Intent intent = new Intent("SCUU");
//                intent.putExtra("succeed", "succeed");
//                sendBroadcast(intent);
//                EventBus.getDefault().postSticky(new MessageEvent(MessageEvent.RepaymentCode));
                if("shop".equals(LoginStatus.getPayType())){
                    SharedPreferencesUtil.getInstance().putString("paytype", "paysuccess");
                }
                finish();
            } else {
                if("shop".equals(LoginStatus.getPayType())){
                    SharedPreferencesUtil.getInstance().putString("paytype", "payfail");
                }
                Toast.makeText(this, "取消支付", Toast.LENGTH_SHORT).show();
                Log.d("TAG", "======微信支付失败");
                finish();
            }
        }

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //EventBus.getDefault().unregister(this);
    }
}