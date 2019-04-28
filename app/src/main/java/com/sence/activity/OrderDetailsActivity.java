package com.sence.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
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
import com.sence.activity.chat.ui.ChatMsgActivity;
import com.sence.adapter.OrderDetailsAdapter;
import com.sence.base.BaseActivity;
import com.sence.bean.request.RAliPayBean;
import com.sence.bean.request.ROrderDetailsBean;
import com.sence.bean.request.RTimeRemainingBean;
import com.sence.bean.request.RWxPayBean;
import com.sence.bean.response.POrderDetailsBean;
import com.sence.bean.response.PTimeRemainingBean;
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

import java.util.Map;
import java.util.concurrent.TimeUnit;

import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 订单详情
 */
public class OrderDetailsActivity extends BaseActivity implements View.OnClickListener {
    private int PAYMENTTYPE = 2;
    @BindView(R.id.tv_state_orderdetails)
    TextView tvStateOrderdetails;
    @BindView(R.id.tv_number_orderdetails)
    TextView tvNumberOrderdetails;
    @BindView(R.id.tv_time_orderdetails)
    TextView tvTimeOrderdetails;
    @BindView(R.id.tv_name_orderdetails)
    TextView tvNameOrderdetails;
    @BindView(R.id.tv_phone_orderdetails)
    TextView tvPhoneOrderdetails;
    @BindView(R.id.tv_address_orderdetails)
    TextView tvAddressOrderdetails;
    @BindView(R.id.rl_address_orderdetails)
    RelativeLayout rlAddressOrderdetails;
    @BindView(R.id.tv_stroename_orderdetails)
    TextView tvStroenameOrderdetails;
    @BindView(R.id.recycle_orderdetails)
    RecyclerView recycleOrderdetails;
    @BindView(R.id.tv_shopprice_orderdetails)
    TextView tvShoppriceOrderdetails;
    @BindView(R.id.tv_coupon_orderdetails)
    TextView tvCouponOrderdetails;
    @BindView(R.id.tv_postprice_orderdetails)
    TextView tvPostpriceOrderdetails;
    @BindView(R.id.tv_money_orderdetails)
    TextView tvMoneyOrderdetails;
    @BindView(R.id.tv_sprice_orderdetails)
    TextView tvSpriceOrderdetails;
    @BindView(R.id.bt_submint_orderdetails)
    Button btSubmintOrderdetails;
    @BindView(R.id.layout_head)
    PubTitle layoutHead;
    @BindView(R.id.ll_order_orderdetails)
    LinearLayout llOrderOrderdetails;
    @BindView(R.id.ll_buttom_orderdetails)
    LinearLayout llButtomOrderdetails;
    @BindView(R.id.tv_text_orderdetails)
    TextView tvTextOrderdetails;
    @BindView(R.id.iv_map_orderdetails)
    ImageView ivMapOrderdetails;
    @BindView(R.id.rl_alllayout)
    LinearLayout rlAlllayout;
    @BindView(R.id.nv_layout)
    NestedScrollView nvLayout;
    private Disposable mDisposable;
    private OrderDetailsAdapter orderDetailsAdapter;

    private String id;
    private String type;
    private BottomSheetDialog mBottomSheetDialog;
    private POrderDetailsBean bean;
    private TextView mPrice;
    private TextView tvPricePay, tvTimePay, tvMinutePay, tvSecondPay;
    private ImageView ivZhiPay, ivWeiPay, ivBackPay;
    private Button btPayPay;
    private boolean isstart = true;
    private int time = 1800;
    private AlertDialog dialog;


    @Override
    public int onActLayout() {
        return R.layout.activity_orderdetails;
    }

    @Override
    public void initView() {
        StatusBarUtil.setLightMode(this);
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        type = intent.getStringExtra("type");
        Log.i("aaaaa",id+"");
        orderDetailsAdapter = new OrderDetailsAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recycleOrderdetails.setLayoutManager(linearLayoutManager);
        recycleOrderdetails.setAdapter(orderDetailsAdapter);

        if ("4".equals(type)) {
            btSubmintOrderdetails.setText("评价");
        } else if ("3".equals(type)) {
            tvTextOrderdetails.setVisibility(View.GONE);
            tvSpriceOrderdetails.setVisibility(View.GONE);
            btSubmintOrderdetails.setText("确认收货");
        } else if ("2".equals(type)) {
            tvTextOrderdetails.setVisibility(View.GONE);
            tvSpriceOrderdetails.setVisibility(View.GONE);
            btSubmintOrderdetails.setBackgroundResource(R.drawable.shape_myorder_bottom);
            btSubmintOrderdetails.setTextColor(Color.parseColor("#838383"));
            btSubmintOrderdetails.setText("联系客服");
        } else if ("1".equals(type)) {
            layoutHead.setRigthText("取消订单");
        } else if ("8".equals(type) || "5".equals(type) || "6".equals(type)) {
            tvTextOrderdetails.setVisibility(View.GONE);
            tvSpriceOrderdetails.setVisibility(View.GONE);
            btSubmintOrderdetails.setBackgroundResource(R.drawable.shape_myorder_bottom);
            btSubmintOrderdetails.setTextColor(Color.parseColor("#838383"));
            btSubmintOrderdetails.setText("再次购买");
        } else if ("7".equals(type)) {
            tvTextOrderdetails.setVisibility(View.GONE);
            tvSpriceOrderdetails.setVisibility(View.GONE);
            btSubmintOrderdetails.setText("删除订单");
        }
        bottomSheetDialog();
        layoutHead.setRightOnClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = View.inflate(OrderDetailsActivity.this, R.layout.alter_deleteorder, null);
                final AlertDialog alertDialog = new AlertDialog.Builder(OrderDetailsActivity.this,R.style.AlertDialogStyle).create();
                alertDialog.setView(view);
                alertDialog.setCancelable(false);
                alertDialog.show();
                alertDialog.getWindow().setLayout(new DensityUtil().dip2px(270), LinearLayout.LayoutParams.WRAP_CONTENT);
                view.findViewById(R.id.tv_cancel_deleteorder).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });
                view.findViewById(R.id.tv_confirm_deleteorder).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                        HttpManager.getInstance().PlayNetCode(HttpCode.ORDER_DELETE, new ROrderDetailsBean(bean.getId(), LoginStatus.getUid())).request(new ApiCallBack<String>() {
                            @Override
                            public void onFinish() {

                            }

                            @Override
                            public void Message(int code, String message) {

                            }

                            @Override
                            public void onSuccess(String o, String msg) {
                                Logger.e("msg==========" + msg);
                                ToastUtils.showShort(msg);
                                finish();
                            }
                        });
                    }
                });
            }
        });

    }

    public void initData() {
        HttpManager.getInstance().PlayNetCode(HttpCode.ORDER_DETAIL, new ROrderDetailsBean(id, LoginStatus.getUid())).request(new ApiCallBack<POrderDetailsBean>() {


            @Override
            public void onFinish() {

            }

            @Override
            public void Message(int code, String message) {
            }

            @Override
            public void onSuccess(final POrderDetailsBean o, String msg) {
                Logger.e("msg==========" + msg + o);
                bean = o;
                if (o.getGoods().size() > 0) {
                    orderDetailsAdapter.setList(o.getGoods());
                }
                tvStateOrderdetails.setText(o.getStatus());
                tvNumberOrderdetails.setText("订单编号："+o.getOid());
                tvTimeOrderdetails.setText("下单时间："+o.getAddtime());
                tvStroenameOrderdetails.setText(o.getShopname());
                tvNameOrderdetails.setText(o.getAddress().getUsername());
                tvPhoneOrderdetails.setText(o.getAddress().getTel());
                tvAddressOrderdetails.setText(o.getAddress().getArea()+o.getAddress().getAddress());
                if(o.getGmoney().contains(".")){
                    tvShoppriceOrderdetails.setText("￥" + o.getGmoney());
                }else{
                    tvShoppriceOrderdetails.setText("￥" + o.getGmoney()+".00");
                }
                if(o.getGmoney().contains(".")){
                    tvCouponOrderdetails.setText("-￥" + o.getCmoney());
                }else{
                    tvCouponOrderdetails.setText("-￥" + o.getCmoney()+".00");
                }
                if(o.getGmoney().contains(".")){
                    tvPostpriceOrderdetails.setText("+￥" + o.getPmoney());
                }else{
                    tvPostpriceOrderdetails.setText("+￥" + o.getPmoney()+".00");
                }
                if(o.getGmoney().contains(".")){
                    tvMoneyOrderdetails.setText("￥" + o.getGmoney());
                }else{
                    tvMoneyOrderdetails.setText("￥" + o.getGmoney()+".00");
                }
                if(o.getGmoney().contains(".")){
                    tvSpriceOrderdetails.setText("￥" + o.getGmoney());
                }else{
                    tvSpriceOrderdetails.setText("￥" + o.getGmoney()+".00");
                }
                if(o.getGmoney().contains(".")){
                    tvPricePay.setText("￥" + o.getGmoney());
                }else{
                    tvPricePay.setText("￥" + o.getGmoney()+".00");
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
        }
    }
    private void alterDone() {
        View view = View.inflate(OrderDetailsActivity.this, R.layout.alter_deleteorder, null);
        dialog = new AlertDialog.Builder(OrderDetailsActivity.this, R.style.AlertDialogStyle).create();
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
                Intent intentall = new Intent(OrderDetailsActivity.this, MyOrderActivity.class);
                intentall.putExtra("type", 0);
                startActivity(intentall);
                finish();
            }
        });

        mConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent intent = new Intent(OrderDetailsActivity.this, MainActivity.class);
                intent.putExtra("type",2);
                startActivity(intent);
                finish();
            }
        });
    }
    private void ConfirmTakeGood() {
        HttpManager.getInstance().PlayNetCode(HttpCode.CONFIRM_TAKE_GOOD, new ROrderDetailsBean(id, LoginStatus.getUid())).request(new ApiCallBack<String>() {


            @Override
            public void onFinish() {

            }

            @Override
            public void Message(int code, String message) {
            }

            @Override
            public void onSuccess(final String o, String msg) {
                Logger.e("msg==========" + msg + o);
                ToastUtils.showShort(msg);
                SharedPreferencesUtil.getInstance().putString("confirm_take_delivery", "4");
                Intent intent = new Intent(OrderDetailsActivity.this, OrderCommentActivity.class);
                intent.putExtra("url", bean.getGoods().get(0).getImg());
                intent.putExtra("id", bean.getId());
                startActivity(intent);
                finish();
            }
        });
    }

    private void DeleteOreder() {
        HttpManager.getInstance().PlayNetCode(HttpCode.DELETE_DONE_ORDER, new ROrderDetailsBean(id, LoginStatus.getUid())).request(new ApiCallBack<String>() {


            @Override
            public void onFinish() {

            }

            @Override
            public void Message(int code, String message) {
            }

            @Override
            public void onSuccess(final String o, String msg) {
                Logger.e("msg==========" + msg + o);
                ToastUtils.showShort(msg);
            }
        });
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
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }

    @OnClick({ R.id.bt_submint_orderdetails})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_submint_orderdetails:
                if ("4".equals(type)) {
                    Intent intent = new Intent(OrderDetailsActivity.this, OrderCommentActivity.class);
                    intent.putExtra("id", bean.getId());
                    intent.putExtra("url", bean.getGoods().get(0).getImg());
                    startActivity(intent);
                    finish();
                } else if ("3".equals(type)) {
                    ConfirmTakeGood();

                } else if ("2".equals(type)) {
                    Intent intent = new Intent(OrderDetailsActivity.this, ChatMsgActivity.class);
                    intent.putExtra("u_to", bean.getCustom().getId());
                    intent.putExtra("chat_id", "");
                    intent.putExtra("title", "客服");
                    intent.putExtra("name", bean.getCustom().getName());
                    intent.putExtra("u_avatar", bean.getCustom().getAvatar());
                    startActivity(intent);
                } else if ("1".equals(type)) {
                    mBottomSheetDialog.show();
                    if (isstart) {
                        getTime();
                    }
                } else if ("5".equals(type) || "6".equals(type) || "8".equals(type)) {
                    Intent intent = new Intent(OrderDetailsActivity.this, MyInfoActivity.class);
                    intent.putExtra("uid",bean.getSellerUid());
                    startActivity(intent);
                } else if ("7".equals(type)) {
                    DeleteOreder();
                }
                break;
        }
    }

    private void getTime() {
        HttpManager.getInstance().PlayNetCode(HttpCode.TIME_REMAINING, new RTimeRemainingBean(id)).request(new ApiCallBack<PTimeRemainingBean>() {


            @Override
            public void onFinish() {

            }

            @Override
            public void Message(int code, String message) {
            }

            @Override
            public void onSuccess(final PTimeRemainingBean o, String msg) {
                Logger.e("msg==========" + msg);
                time = Integer.parseInt(o.getPayTime());
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
                PAYMENTTYPE=2;
                break;
            case R.id.ll_wei_pay:
                ivWeiPay.setImageResource(R.drawable.xuanzhong);
                ivZhiPay.setImageResource(R.drawable.weixuan);
                PAYMENTTYPE=1;
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
                mBottomSheetDialog.dismiss();
                break;
        }
    }
    /**
     * 微信
     */
    private void PayWx() {
        HttpManager.getInstance().PlayNetCode(HttpCode.PAY_WX, new RWxPayBean(LoginStatus.getUid(), "1",bean.getGmoney(),id)).request(new ApiCallBack<PWxPayBean>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void Message(int code, String message) {

            }

            @Override
            public void onSuccess(PWxPayBean o, String msg) {
                Logger.e("msg==========" + msg);
                SharedPreferencesUtil.getInstance().putString("paytype", "shop");
                WeiXinPayUtils wxpay = new WeiXinPayUtils(OrderDetailsActivity.this, o);
                wxpay.pay();
            }
        });
    }
    /**
     * 支付宝
     */
    private void aLiPay() {
        HttpManager.getInstance().PlayNetCode(HttpCode.PAY_ALI, new RAliPayBean("1", LoginStatus.getUid(), bean.getGmoney(), id)).request(new ApiCallBack<String>() {
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
                        PayTask alipay = new PayTask(OrderDetailsActivity.this);
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
                        Toast.makeText(OrderDetailsActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                        alterDone();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(OrderDetailsActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
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
        mView.findViewById(R.id.iv_back_pay).setOnClickListener(this);
        mView.findViewById(R.id.ll_zhi_pay).setOnClickListener(this);
        mView.findViewById(R.id.ll_wei_pay).setOnClickListener(this);
        ivBackPay.setOnClickListener(this);
        btPayPay.setOnClickListener(this);
        mBottomSheetDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                mDisposable.dispose();
            }
        });
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

}
