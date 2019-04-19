package com.sence.wxapi;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.util.DensityUtil;
import com.sence.MainActivity;
import com.sence.R;
import com.sence.activity.MyOrderActivity;
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
                    SharedPreferencesUtil.getInstance().putString("paytype", "");
                    alterDone();
                }
                finish();
            } else {
                Toast.makeText(this, "取消支付", Toast.LENGTH_SHORT).show();
                Log.d("TAG", "======微信支付失败");
                finish();
            }
        }

    }
    private void alterDone() {
        View view = View.inflate(this, R.layout.alter_deleteorder, null);
        final AlertDialog dialog = new AlertDialog.Builder(this, R.style.AlertDialogStyle).create();
        dialog.setView(view);
        dialog.getWindow().setLayout(new DensityUtil().dip2px(270), LinearLayout.LayoutParams.WRAP_CONTENT);
        dialog.show();
        TextView mTitle = view.findViewById(R.id.tv_title_deleteorder);
        mTitle.setText("购买完成");
        TextView mContent = view.findViewById(R.id.tv_content_deleteorder);
        mContent.setText("已成功购买这些商品，可以在我的订单里查看订单最新状态。");
        TextView mCancel = view.findViewById(R.id.tv_cancel_deleteorder);
        mCancel.setText("我的订单");
        TextView mConfirm = view.findViewById(R.id.tv_confirm_deleteorder);
        mConfirm.setText("继续购买");
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent intentall = new Intent(WXPayEntryActivity.this, MyOrderActivity.class);
                intentall.putExtra("type", 0);
                startActivity(intentall);
                finish();
            }
        });

        mConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent intent = new Intent(WXPayEntryActivity.this, MainActivity.class);
                intent.putExtra("type",2);
                startActivity(intent);
                finish();
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //EventBus.getDefault().unregister(this);
    }
}