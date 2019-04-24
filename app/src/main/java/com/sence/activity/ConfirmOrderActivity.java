package com.sence.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.blankj.utilcode.util.ToastUtils;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.util.DensityUtil;
import com.sence.MainActivity;
import com.sence.R;
import com.sence.adapter.ConfirmOrderAdapter;
import com.sence.base.BaseActivity;
import com.sence.bean.request.RAliPayBean;
import com.sence.bean.request.RConfirmOrderBean;
import com.sence.bean.request.RConfirmOrderGoodBean;
import com.sence.bean.request.RUidBean;
import com.sence.bean.request.RWxPayBean;
import com.sence.bean.response.PBusBean;
import com.sence.bean.response.PConfirmOrderBean;
import com.sence.bean.response.PDefaultAddressBean;
import com.sence.bean.response.PWxPayBean;
import com.sence.net.HttpCode;
import com.sence.net.HttpManager;
import com.sence.net.manager.ApiCallBack;
import com.sence.utils.DateUtils;
import com.sence.utils.LoginStatus;
import com.sence.utils.SharedPreferencesUtil;
import com.sence.utils.StatusBarUtil;
import com.sence.view.PubTitle;
import com.sence.wxapi.WeiXinPayUtils;
import com.sence.zhifubao.PayResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 确认订单
 */
public class ConfirmOrderActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.rl_head_confirmorder)
    PubTitle rlHeadConfirmorder;
    @BindView(R.id.tv_address_confirmorder)
    TextView tvAddressConfirmorder;
    @BindView(R.id.tv_phone_confirmorder)
    TextView tvPhoneConfirmorder;
    @BindView(R.id.tv_name_confirmorder)
    TextView tvNameConfirmorder;
    @BindView(R.id.iv_toaddress_confirmorder)
    ImageView ivToaddressConfirmorder;
    @BindView(R.id.rl_address_confirmorder)
    RelativeLayout rlAddressConfirmorder;
    @BindView(R.id.recycle_confirmorder)
    RecyclerView recycleConfirmorder;
    @BindView(R.id.tv_sprice_confrimorder)
    TextView tvSpriceConfrimorder;
    @BindView(R.id.bt_submint_confirmorder)
    Button btSubmintConfirmorder;
    @BindView(R.id.rl_addaddress_confirmorder)
    RelativeLayout rlAddaddressConfirmorder;
    @BindView(R.id.iv_map_confirmorder)
    ImageView ivMapConfirmorder;
    private ConfirmOrderAdapter confirmOrderAdapter;
    private String isMember;
    private BottomSheetDialog mBottomSheetDialog;
    private TextView tvPricePay, tvTimePay, tvMinutePay, tvSecondPay;
    private ImageView ivZhiPay, ivWeiPay, ivBackPay;
    private Button btPayPay;
    private List<PBusBean.CartBean> listData = new ArrayList<>();
    private Double priceAll = 0.0;
    private int time;
    private boolean isCheckAddress = false;
    private String idAddress;
    private String address;
    private String nameAddress;
    private String phoneAddress;
    private Disposable mDisposable;
    private String[] splitId;
    private int mine = 0;
    private int count = 0;
    private int PAYMENTTYPE = 2;
    private String oid;
    private double allprice;
    private AlertDialog dialog;

    @Override
    public int onActLayout() {
        return R.layout.activity_confirmorder;
    }

    @Override
    public void initView() {
        StatusBarUtil.setLightMode(this);
        Intent intent = getIntent();
        String type = intent.getStringExtra("type");
        List<PBusBean.CartBean> list = (List<PBusBean.CartBean>) intent.getSerializableExtra("data");
        isMember = intent.getStringExtra("isMember");
        confirmOrderAdapter = new ConfirmOrderAdapter(ConfirmOrderActivity.this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ConfirmOrderActivity.this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recycleConfirmorder.setLayoutManager(linearLayoutManager);
        recycleConfirmorder.setAdapter(confirmOrderAdapter);
        allprice = 0.0;
        if(list.size()>0){
            for (int i = 0; i < list.size(); i++) {
                List<PBusBean.CartBean.GoodsBean> goods = list.get(i).getGoods();
                for (int j = 0; j < goods.size() ; j++) {
                    if(goods.get(j).isSelect()){
                        listData.add(list.get(i));
                        break;
                    }
                }
            }
        }
        for (int i = 0; i < list.size(); i++) {
            double price = stringtodouble(list.get(i).getAll_money());
            allprice +=price;
        }
        tvSpriceConfrimorder.setText("￥"+ allprice);
        if ("shop".equals(type)) {
            confirmOrderAdapter.setList(listData);
        }
        bottomSheetDialog();
        defaultAddress();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(dialog != null) {
            dialog.dismiss();
        }
        if(mBottomSheetDialog != null) {
            mBottomSheetDialog.dismiss();
        }
    }
    @Override
    public void initData() {
        isCheckAddress = LoginStatus.getIsCheckShopAddress();
        if(isCheckAddress){
            address = LoginStatus.getAddress();
            nameAddress = LoginStatus.getNameAddress();
            phoneAddress = LoginStatus.getPhoneAddress();
            idAddress = LoginStatus.getIdAddress();
            tvAddressConfirmorder.setText(address);
            tvPhoneConfirmorder.setText(phoneAddress);
            tvNameConfirmorder.setText(nameAddress);
            SharedPreferencesUtil.getInstance().putBoolean("ischeck_shopaddress", false);
        }

    }

    private void defaultAddress() {
        HttpManager.getInstance().PlayNetCode(HttpCode.DEFAULT_ADDRESS, new RUidBean(LoginStatus.getUid())).request(new ApiCallBack<PDefaultAddressBean>() {
            @Override
            public void onFinish() {
            }

            @Override
            public void Message(int code, String message) {
            }

            @Override
            public void onSuccess(PDefaultAddressBean o, String msg) {
                Logger.e("msg==========" + msg );
                if(null == o.getList().getId()){
                    rlAddaddressConfirmorder.setVisibility(View.VISIBLE);
                    rlAddressConfirmorder.setVisibility(View.GONE);
                }else {
                    idAddress = o.getList().getId();
                    rlAddaddressConfirmorder.setVisibility(View.GONE);
                    rlAddressConfirmorder.setVisibility(View.VISIBLE);
                    isCheckAddress=true;
                    tvAddressConfirmorder.setText(o.getList().getArea()+o.getList().getAddress());
                    tvPhoneConfirmorder.setText(o.getList().getPhone());
                    tvNameConfirmorder.setText(o.getList().getUsername());
                }
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        if("paysuccess".equals(LoginStatus.getPayType())){
            SharedPreferencesUtil.getInstance().putString("paytype", "");
            alterDone();
        }else if("payfail".equals(LoginStatus.getPayType())){
            SharedPreferencesUtil.getInstance().putString("paytype", "");
            alter();
        }
    }
    @OnClick({R.id.rl_address_confirmorder, R.id.bt_submint_confirmorder, R.id.rl_addaddress_confirmorder})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_address_confirmorder:
                Intent intentsele = new Intent(ConfirmOrderActivity.this, ManageAddressActivity.class);
                intentsele.putExtra("type", "shop");
                startActivity(intentsele);
                break;
            case R.id.bt_submint_confirmorder:
                if (isCheckAddress) {
                    mBottomSheetDialog.show();
                    createOrder();

                } else {
                    ToastUtils.showShort("请先选择地址");
                }

                break;
            case R.id.rl_addaddress_confirmorder:
                Intent intent = new Intent(ConfirmOrderActivity.this, ManageAddressActivity.class);
                intent.putExtra("type", "shop");
                startActivity(intent);
                break;
        }
    }
    private double stringtodouble(String a){
        double b = Double.valueOf(a);
        DecimalFormat df = new DecimalFormat("#.00");//此为保留1位小数，若想保留2位小数，则填写#.00  ，以此类推
        String temp = df.format(b);
        b = Double.valueOf(temp);
        return b;
    }
    private void createOrder() {
        List<RConfirmOrderGoodBean.Good> listGood = new ArrayList<>();
        for (int i = 0; i < listData.size(); i++) {
            List<PBusBean.CartBean.GoodsBean> goods = listData.get(i).getGoods();
            for (int j = 0; j < goods.size(); j++) {
                RConfirmOrderGoodBean.Good good = new RConfirmOrderGoodBean.Good(goods.get(j).getUid(), goods.get(j).getNum() + "");
                listGood.add(good);
            }
        }
        JSONArray json = new JSONArray();
        for (RConfirmOrderGoodBean.Good a : listGood) {
            try {
                JSONObject jo = new JSONObject();
                jo.put("id", a.getId());
                jo.put("num", a.getNum());
                json.put(jo);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        HttpManager.getInstance().PlayNetCode(HttpCode.ORDER_COMMIT, new RConfirmOrderBean(json, LoginStatus.getUid(), idAddress,"1")).request(new ApiCallBack<PConfirmOrderBean>() {


            @Override
            public void onFinish() {

            }

            @Override
            public void Message(int code, String message) {
            }

            @Override
            public void onSuccess(final PConfirmOrderBean o, String msg) {
                Logger.e("msg==========" + msg);
                oid = o.getOid();
                splitId = oid.split(",");
                time = Integer.parseInt(o.getTime());

                doLastTime();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_zhi_pay:
                ivZhiPay.setImageResource(R.drawable.xuanzhong);
                ivWeiPay.setImageResource(R.drawable.weixuan);
                PAYMENTTYPE = 2;
                break;
            case R.id.ll_wei_pay:
                ivWeiPay.setImageResource(R.drawable.xuanzhong);
                ivZhiPay.setImageResource(R.drawable.weixuan);
                PAYMENTTYPE = 1;
                break;
            case R.id.bt_pay_pay:
                if(PAYMENTTYPE==1){
                    PayWx();
                }else if(PAYMENTTYPE == 2){
                    aLiPay();
                }
                mBottomSheetDialog.dismiss();
                break;
            case R.id.iv_back_pay:
                alter();
                break;
        }
    }
    private void alterDone() {
        View view = View.inflate(ConfirmOrderActivity.this, R.layout.alter_deleteorder, null);
        dialog = new AlertDialog.Builder(ConfirmOrderActivity.this, R.style.AlertDialogStyle).create();
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
                Intent intentall = new Intent(ConfirmOrderActivity.this, MyOrderActivity.class);
                intentall.putExtra("type", 0);
                startActivity(intentall);
                finish();
            }
        });

        mConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent intent = new Intent(ConfirmOrderActivity.this, MainActivity.class);
                intent.putExtra("type",2);
                startActivity(intent);
                finish();
            }
        });
    }
    /**
     * 微信
     */
    private void PayWx() {
        HttpManager.getInstance().PlayNetCode(HttpCode.PAY_WX, new RWxPayBean(LoginStatus.getUid(), "1",allprice+"",oid)).request(new ApiCallBack<PWxPayBean>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void Message(int code, String message) {

            }

            @Override
            public void onSuccess(PWxPayBean o, String msg) {
                Logger.e("msg==========" + msg);
                WeiXinPayUtils wxpay = new WeiXinPayUtils(ConfirmOrderActivity.this, o);
                SharedPreferencesUtil.getInstance().putString("paytype", "shop");
                wxpay.pay();
            }
        });
    }
    /**
     * 支付宝
     */
    private void aLiPay() {
        HttpManager.getInstance().PlayNetCode(HttpCode.PAY_ALI, new RAliPayBean("1", LoginStatus.getUid(), allprice+"", oid)).request(new ApiCallBack<String>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void Message(int code, String message) {

            }

            @Override
            public void onSuccess(final String o, String msg) {
                Runnable payRunnable = new Runnable() {
                    @Override
                    public void run() {
                        PayTask alipay = new PayTask(ConfirmOrderActivity.this);
                        Map<String, String> result = alipay.payV2(o, true);
                        Log.i("msp", result.toString());
                        Message msg = new Message();
                        msg.what = SDK_PAY_FLAG;
                        msg.obj = result;
                        mHandler.sendMessage(msg);
                    }
                };

                Thread payThread = new Thread(payRunnable);
                payThread.start();
            }
        });

    }
    private static final int SDK_PAY_FLAG = 1;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        Toast.makeText(ConfirmOrderActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                        alterDone();
                    } else {
                        alter();
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(ConfirmOrderActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
                default:
                    break;
            }
        }
    };
    private void bottomSheetDialog() {
        View mView = View.inflate(this, R.layout.bottom_pay_layout, null);
        mBottomSheetDialog = new BottomSheetDialog(this);
        mBottomSheetDialog.setContentView(mView);
        tvPricePay = mView.findViewById(R.id.tv_price_pay);
        tvTimePay = mView.findViewById(R.id.tv_time_pay);
        tvMinutePay = mView.findViewById(R.id.tv_minute_pay);
        tvSecondPay = mView.findViewById(R.id.tv_second_pay);
        ivZhiPay = mView.findViewById(R.id.iv_zhi_pay);
        ivWeiPay = mView.findViewById(R.id.iv_wei_pay);
        ivBackPay = mView.findViewById(R.id.iv_back_pay);
        btPayPay = mView.findViewById(R.id.bt_pay_pay);
        mView.findViewById(R.id.ll_zhi_pay).setOnClickListener(this);
        mView.findViewById(R.id.ll_wei_pay).setOnClickListener(this);
        ivBackPay.setOnClickListener(this);
        btPayPay.setOnClickListener(this);
        for (int i = 0; i < listData.size(); i++) {
            priceAll += stringToDouble(listData.get(i).getAll_money());
        }
        tvPricePay.setText("￥" + priceAll);

        mBottomSheetDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                alter();
            }
        });
    }

    private void alter(){
        if(mBottomSheetDialog.isShowing()){
            mBottomSheetDialog.dismiss();
        }
        View view = View.inflate(ConfirmOrderActivity.this, R.layout.alter_deleteorder, null);
        final AlertDialog alertDialog = new AlertDialog.Builder(ConfirmOrderActivity.this,R.style.AlertDialogStyle).create();
        alertDialog.getWindow().setLayout(new DensityUtil().dip2px(270), LinearLayout.LayoutParams.WRAP_CONTENT);
        alertDialog.setView(view);
        alertDialog.show();
        TextView mTitle = view.findViewById(R.id.tv_title_deleteorder);
        mTitle.setText("取消支付");
        TextView mContent = view.findViewById(R.id.tv_content_deleteorder);
        mContent.setText("取消支付后订单进入待支付，在我的订单里可以继续购买。");
        TextView mCancel = view.findViewById(R.id.tv_cancel_deleteorder);
        mCancel.setText("稍后付款");
        TextView mConfirm = view.findViewById(R.id.tv_confirm_deleteorder);
        mConfirm.setText("继续购买");
        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                finish();
            }
        });

        mConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                mBottomSheetDialog.show();
            }
        });
    }

    public static double stringToDouble(String a) {
        double b = Double.valueOf(a);
        DecimalFormat df = new DecimalFormat("#.00");//此为保留1位小数，若想保留2位小数，则填写#.00  ，以此类推
        String temp = df.format(b);
        b = Double.valueOf(temp);
        return b;
    }

    public void doLastTime() {
        mDisposable = Observable.interval(0, 1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()) // 由于interval默认在新线程，所以我们应该切回主线程
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(@NonNull Long aLong) {
                        if (time - aLong <= 0) {
                            if (time - aLong <= 0) {
                                mDisposable.dispose();
                            }

                        } else {
                            int t = (int) (time - aLong);
                            String timedata = DateUtils.getTime(t);
                            String[] spliTime = timedata.split(":");
                            tvMinutePay.setText(spliTime[0]);
                            tvSecondPay.setText(spliTime[1]);
                        }
                    }
                });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.iv_map_confirmorder)
    public void onViewClicked() {
        Intent intent = new Intent(ConfirmOrderActivity.this, MapActivity.class);
        startActivity(intent);
    }
}
