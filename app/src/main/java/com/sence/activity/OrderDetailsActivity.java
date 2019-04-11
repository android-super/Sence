package com.sence.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.orhanobut.logger.Logger;
import com.sence.R;
import com.sence.adapter.OrderDetailsAdapter;
import com.sence.base.BaseActivity;
import com.sence.bean.request.ROrderDetailsBean;
import com.sence.bean.request.RTimeRemainingBean;
import com.sence.bean.response.POrderDetailsBean;
import com.sence.bean.response.PTimeRemainingBean;
import com.sence.net.HttpCode;
import com.sence.net.HttpManager;
import com.sence.net.manager.ApiCallBack;
import com.sence.utils.DateUtils;
import com.sence.utils.LoginStatus;
import com.sence.utils.StatusBarUtil;
import com.sence.view.PubTitle;

import java.text.DecimalFormat;
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
    @BindView(R.id.tv_taxprice_orderdetails)
    TextView tvTaxpriceOrderdetails;
    @BindView(R.id.tv_money_orderdetails)
    TextView tvMoneyOrderdetails;
    @BindView(R.id.tv_sprice_orderdetails)
    TextView tvSpriceOrderdetails;
    @BindView(R.id.bt_submint_orderdetails)
    Button btSubmintOrderdetails;
    @BindView(R.id.layout_head)
    PubTitle layoutHead;
    @BindView(R.id.rl_number_orderdetails)
    LinearLayout rlNumberOrderdetails;
    @BindView(R.id.iv_toaddress_orderdetails)
    ImageView ivToaddressOrderdetails;
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
        } else if ("8".equals(type)||"5".equals(type)||"6".equals(type)) {
            tvTextOrderdetails.setVisibility(View.GONE);
            tvSpriceOrderdetails.setVisibility(View.GONE);
            btSubmintOrderdetails.setBackgroundResource(R.drawable.shape_myorder_bottom);
            btSubmintOrderdetails.setTextColor(Color.parseColor("#838383"));
            btSubmintOrderdetails.setText("删除订单");
        } else if ("7".equals(type)) {
            tvTextOrderdetails.setVisibility(View.GONE);
            tvSpriceOrderdetails.setVisibility(View.GONE);
            btSubmintOrderdetails.setText("再次购买");
        }
        bottomSheetDialog();
        layoutHead.setRightOnClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = View.inflate(OrderDetailsActivity.this, R.layout.alter_deleteorder, null);
                final AlertDialog alertDialog = new AlertDialog.Builder(OrderDetailsActivity.this).create();
                alertDialog.setView(view);
                alertDialog.show();
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
                        HttpManager.getInstance().PlayNetCode(HttpCode.ORDER_DELETE, new ROrderDetailsBean(bean.getOid(), LoginStatus.getUid())).request(new ApiCallBack<String>() {
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
        HttpManager.getInstance().PlayNetCode(HttpCode.ORDER_DETAIL, new ROrderDetailsBean(id, "1")).request(new ApiCallBack<POrderDetailsBean>() {


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
//
                tvStateOrderdetails.setText(o.getStatus());
                tvNumberOrderdetails.setText(o.getOid());
                tvTimeOrderdetails.setText(o.getAddtime());
                tvStroenameOrderdetails.setText(o.getShopname());
                tvNameOrderdetails.setText(o.getAddress().getUsername());
                tvPhoneOrderdetails.setText(o.getAddress().getPhone());
                tvAddressOrderdetails.setText(o.getAddress().getAddress());
                tvShoppriceOrderdetails.setText("￥" + o.getGmoney());
                tvCouponOrderdetails.setText("-￥" + o.getCmoney());
                tvPostpriceOrderdetails.setText("+￥" + o.getPmoney());
                tvTaxpriceOrderdetails.setText("+￥" + o.getFee());
                tvMoneyOrderdetails.setText("￥" + o.getNeedpay());
                tvSpriceOrderdetails.setText("￥" + o.getNeedpay());
                tvPricePay.setText("￥" + o.getNeedpay());
            }
        });
    }


    private void CancelOreder() {

        HttpManager.getInstance().PlayNetCode(HttpCode.ORDER_DELETE, new ROrderDetailsBean(id, LoginStatus.getUid())).request(new ApiCallBack<String>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void Message(int code, String message) {

            }

            @Override
            public void onSuccess(String o, String msg) {
                Logger.e("msg==========" + msg);
                if (msg.equals("取消成功")) {
                    ToastUtils.showShort("订单取消成功");
//                    mTv.setText("取消成功");
//                    mTv.setClickable(false);
                    tvStateOrderdetails.setText("订单已取消");
                }
            }
        });

    }


    @OnClick({R.id.rl_address_orderdetails, R.id.bt_submint_orderdetails})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_address_orderdetails:
                startActivity(new Intent(OrderDetailsActivity.this, ManageAddressActivity.class));
                break;
            case R.id.bt_submint_orderdetails:
                if ("写评价".equals(type)) {
                    Intent intent = new Intent(OrderDetailsActivity.this, OrderDetailsActivity.class);
                    intent.putExtra("id", bean.getId());
                    intent.putExtra("type", type);
                    startActivity(intent);
                    finish();
                } else if ("待收货".equals(type)) {

                } else if ("待发货".equals(type)) {

                } else if ("待支付".equals(type)) {
                    mBottomSheetDialog.show();
                    if (isstart) {
                        getTime();
                    }
                } else if ("交易关闭".equals(type)) {

                } else if ("交易成功".equals(type)) {

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
            case R.id.iv_zhi_pay:
                ivZhiPay.setImageResource(R.drawable.xuanzhong);
                ivWeiPay.setImageResource(R.drawable.weixuan);
                break;
            case R.id.iv_wei_pay:
                ivWeiPay.setImageResource(R.drawable.xuanzhong);
                ivZhiPay.setImageResource(R.drawable.weixuan);
                break;
            case R.id.bt_pay_pay:
                mBottomSheetDialog.dismiss();
                break;
            case R.id.iv_back_pay:

                break;
        }
    }


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
        ivZhiPay.setOnClickListener(this);
        ivWeiPay.setOnClickListener(this);
        ivBackPay.setOnClickListener(this);
        btPayPay.setOnClickListener(this);
        mBottomSheetDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                mDisposable.dispose();
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

    @OnClick(R.id.iv_map_orderdetails)
    public void onViewClicked() {
        Intent intent = new Intent(OrderDetailsActivity.this, MapActivity.class);
        startActivity(intent);
    }
}
