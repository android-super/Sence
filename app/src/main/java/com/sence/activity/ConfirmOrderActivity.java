package com.sence.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.orhanobut.logger.Logger;
import com.sence.R;
import com.sence.adapter.ConfirmOrderAdapter;
import com.sence.base.BaseActivity;
import com.sence.bean.request.RConfirmOrderBean;
import com.sence.bean.request.RConfirmOrderGoodBean;
import com.sence.bean.response.PBusBean;
import com.sence.bean.response.PConfirmOrderBean;
import com.sence.net.HttpCode;
import com.sence.net.HttpManager;
import com.sence.net.manager.ApiCallBack;
import com.sence.utils.DateUtils;
import com.sence.utils.LoginStatus;
import com.sence.utils.SharedPreferencesUtil;
import com.sence.utils.StatusBarUtil;
import com.sence.view.PubTitle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
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
    private ConfirmOrderAdapter confirmOrderAdapter;
    private String isMember;
    private BottomSheetDialog mBottomSheetDialog;
    private TextView tvPricePay, tvTimePay, tvMinutePay, tvSecondPay;
    private ImageView ivZhiPay, ivWeiPay, ivBackPay;
    private Button btPayPay;
    private List<PBusBean.CartBean> list;
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

    @Override
    public int onActLayout() {
        return R.layout.activity_confirmorder;
    }

    @Override
    public void initView() {
        StatusBarUtil.setLightMode(this);
        Intent intent = getIntent();
        String type = intent.getStringExtra("type");

        list = (List<PBusBean.CartBean>) intent.getSerializableExtra("data");
        isMember = intent.getStringExtra("isMember");
        confirmOrderAdapter = new ConfirmOrderAdapter(ConfirmOrderActivity.this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ConfirmOrderActivity.this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recycleConfirmorder.setLayoutManager(linearLayoutManager);
        recycleConfirmorder.setAdapter(confirmOrderAdapter);
        if ("shop".equals(type)) {
            confirmOrderAdapter.setList(list);
        }
        bottomSheetDialog();
    }

    @Override
    public void initData() {
        isCheckAddress = LoginStatus.getIsCheckAddress();
        idAddress = LoginStatus.getIdAddress();
        address = LoginStatus.getAddress();
        nameAddress = LoginStatus.getNameAddress();
        phoneAddress = LoginStatus.getPhoneAddress();
        SharedPreferencesUtil.getInstance().putBoolean("ischeck_address", false);
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

    private void createOrder() {
        List<RConfirmOrderGoodBean.Good> listGood = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            List<PBusBean.CartBean.GoodsBean> goods = list.get(i).getGoods();
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
                splitId = oid.split(",");
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
                Intent intent = new Intent(ConfirmOrderActivity.this, OrderDetailsActivity.class);
                intent.putExtra("type","2");
                intent.putExtra("id",splitId[0]);
                startActivity(intent);
                finish();
                break;
            case R.id.iv_back_pay:
                alter();
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
        for (int i = 0; i < list.size(); i++) {
            priceAll += stringToDouble(list.get(i).getAll_money());
        }
        tvPricePay.setText("￥" + priceAll);
        mBottomSheetDialog.setCancelable(false);

        mBottomSheetDialog.setCanceledOnTouchOutside(false);
        mBottomSheetDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                alter();
            }
        });
    }

    private void alter(){
        View view = View.inflate(ConfirmOrderActivity.this, R.layout.alter_deleteorder, null);
        final AlertDialog alertDialog = new AlertDialog.Builder(ConfirmOrderActivity.this).create();
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
                finish();
                alertDialog.dismiss();
            }
        });

        mConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();

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
