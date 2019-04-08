package com.sence.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
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
import com.sence.bean.response.POrderDetailsBean;
import com.sence.net.HttpCode;
import com.sence.net.HttpManager;
import com.sence.net.manager.ApiCallBack;
import com.sence.utils.LoginStatus;
import com.sence.utils.StatusBarUtil;
import com.sence.view.PubTitle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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

    private OrderDetailsAdapter orderDetailsAdapter;

    private String id;
    private String type;
    private BottomSheetDialog mBottomSheetDialog;
    private POrderDetailsBean bean;
    private TextView mPrice;
    private TextView tvPricePay, tvTimePay, tvMinutePay, tvSecondPay;
    private ImageView ivZhiPay, ivWeiPay, ivBackPay;
    private Button btPayPay;


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
        if (!"等待支付".equals(type)) {
//            btSubmintOrderdetails.setText("评价");
            layoutHead.setRigthText("");
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
                Logger.e("msg==========" + msg);
                bean = o;
                if (o.getGoods().size() > 0) {
                    orderDetailsAdapter.setList(o.getGoods());
                }
                mPrice.setText("￥" + o.getNeedpay());
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
                mBottomSheetDialog.show();
                break;
        }
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
