package com.sence.activity;

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
import com.sence.base.BaseActivity;
import com.sence.bean.request.RConfirmOrderBean;
import com.sence.bean.response.PConfirmOrderBean;
import com.sence.net.HttpCode;
import com.sence.net.HttpManager;
import com.sence.net.manager.ApiCallBack;
import com.sence.utils.DateUtils;
import com.sence.utils.GlideUtils;
import com.sence.utils.LoginStatus;
import com.sence.utils.SharedPreferencesUtil;
import com.sence.utils.StatusBarUtil;
import com.sence.view.NiceImageView;
import com.sence.view.PubTitle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ShopConfirmOrderActivity extends BaseActivity implements View.OnClickListener {


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
    @BindView(R.id.tv_stroename_confirmorder)
    TextView tvStroenameConfirmorder;
    @BindView(R.id.tv_exemption_confirmorder)
    TextView tvExemptionConfirmorder;
    @BindView(R.id.ll_layout_confirmorder)
    LinearLayout llLayoutConfirmorder;
    @BindView(R.id.iv_img_confirmorder)
    NiceImageView ivImgConfirmorder;
    @BindView(R.id.tv_shopname_confirmorder)
    TextView tvShopnameConfirmorder;
    @BindView(R.id.tv_price_confirmorder)
    TextView tvPriceConfirmorder;
    @BindView(R.id.tv_num_confirmorder)
    TextView tvNumConfirmorder;
    @BindView(R.id.rl_confirmorderitem)
    RelativeLayout rlConfirmorderitem;
    @BindView(R.id.tv_shopprice_confirmorder)
    TextView tvShoppriceConfirmorder;
    @BindView(R.id.tv_postprice_confirmorder)
    TextView tvPostpriceConfirmorder;
    @BindView(R.id.tv_shopnum_confirmorder)
    TextView tvShopnumConfirmorder;
    @BindView(R.id.tv_maxprice_confirmorder)
    TextView tvMaxpriceConfirmorder;
    @BindView(R.id.rl_confirmorder)
    RelativeLayout rlConfirmorder;
    @BindView(R.id.iv_to_confirmorder)
    ImageView ivToConfirmorder;
    @BindView(R.id.tv_sprice_confrimorder)
    TextView tvSpriceConfrimorder;
    @BindView(R.id.bt_submint_confirmorder)
    Button btSubmintConfirmorder;
    @BindView(R.id.rl_addaddress_confirmorder)
    RelativeLayout rlAddaddressConfirmorder;
    @BindView(R.id.iv_map_confirmorder)
    ImageView ivMapConfirmorder;
    private String isMember;
    private BottomSheetDialog mBottomSheetDialog;
    private int time;
    private Disposable mDisposable;
    private TextView tvPricePay;
    private TextView tvTimePay;
    private TextView tvMinutePay;
    private TextView tvSecondPay;
    private ImageView ivZhiPay;
    private ImageView ivWeiPay;
    private ImageView ivBackPay;
    private Button btPayPay;
    private String idAddress;
    private String address;
    private String nameAddress;
    private String phoneAddress;
    private boolean isCheckAddress = false;
    private String id;
    private String num;
    private Double shopMaxPrice;

    @Override
    public int onActLayout() {
        return R.layout.activity_shopconfirmorder;
    }

    @Override
    public void initView() {
        StatusBarUtil.setLightMode(this);
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        num = intent.getStringExtra("num");
        String postage = intent.getStringExtra("postage");
        String price = intent.getStringExtra("price");
        String name = intent.getStringExtra("name");
        String username = intent.getStringExtra("username");
        String img = intent.getStringExtra("img");
        tvNumConfirmorder.setText("х" + num);
        int ShopNum = Integer.parseInt(num);
        Double ShopPrice = stringToDouble(price);
        Double ShopPostPrice = stringToDouble(postage);
        Double ShopAllPrice = ShopNum * ShopPrice;
        shopMaxPrice = ShopAllPrice + ShopPostPrice;
        tvPriceConfirmorder.setText("￥" + ShopPrice);
        tvShoppriceConfirmorder.setText("￥" + ShopAllPrice);
        tvMaxpriceConfirmorder.setText("￥" + shopMaxPrice);
        tvSpriceConfrimorder.setText("￥" + shopMaxPrice);
        tvPostpriceConfirmorder.setText("￥" + ShopPostPrice);
        GlideUtils.getInstance().loadHead(img, ivImgConfirmorder);
        tvShopnameConfirmorder.setText(name);
        tvStroenameConfirmorder.setText(username);
        tvShopnumConfirmorder.setText("共" + num + "件商品");
        bottomSheetDialog();
    }

    @Override
    public void initData() {
        isCheckAddress = LoginStatus.getIsCheckShopAddress();
        idAddress = LoginStatus.getIdAddress();
        address = LoginStatus.getAddress();
        nameAddress = LoginStatus.getNameAddress();
        phoneAddress = LoginStatus.getPhoneAddress();
        SharedPreferencesUtil.getInstance().putBoolean("ischeck_shopaddress", false);
        if (isCheckAddress) {
            rlAddaddressConfirmorder.setVisibility(View.GONE);
            rlAddressConfirmorder.setVisibility(View.VISIBLE);
        } else {
            rlAddaddressConfirmorder.setVisibility(View.VISIBLE);
            rlAddressConfirmorder.setVisibility(View.GONE);
        }
        tvAddressConfirmorder.setText(address);
        tvPhoneConfirmorder.setText(phoneAddress);
        tvNameConfirmorder.setText(nameAddress);
    }


    @OnClick({R.id.rl_address_confirmorder, R.id.bt_submint_confirmorder, R.id.rl_addaddress_confirmorder})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_address_confirmorder:
                Intent intentsele = new Intent(ShopConfirmOrderActivity.this, ManageAddressActivity.class);
                intentsele.putExtra("type", "shopd");
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
                Intent intent = new Intent(ShopConfirmOrderActivity.this, ManageAddressActivity.class);
                intent.putExtra("type", "shopd");
                startActivity(intent);
                break;
        }
    }

    private void createOrder() {
        JSONArray json = new JSONArray();
        try {
            JSONObject jo = new JSONObject();
            jo.put("id", id);
            jo.put("num", num);
            json.put(jo);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        HttpManager.getInstance().PlayNetCode(HttpCode.ORDER_COMMIT, new RConfirmOrderBean(json, LoginStatus.getUid(), idAddress)).request(new ApiCallBack<PConfirmOrderBean>() {


            @Override
            public void onFinish() {

            }

            @Override
            public void Message(int code, String message) {
            }

            @Override
            public void onSuccess(final PConfirmOrderBean o, String msg) {
                Logger.e("msg==========" + msg);
                String oid = o.getOid();
                time = Integer.parseInt(o.getTime());

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
                mBottomSheetDialog.dismiss();
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

        tvPricePay.setText("￥" + shopMaxPrice);
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
        Intent intent = new Intent(ShopConfirmOrderActivity.this, MapActivity.class);
        startActivity(intent);
    }
}
