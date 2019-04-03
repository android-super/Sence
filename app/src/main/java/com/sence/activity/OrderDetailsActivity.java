package com.sence.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.orhanobut.logger.Logger;
import com.sence.R;
import com.sence.adapter.OrderDetailsAdapter;
import com.sence.bean.request.ROrderDetailsBean;
import com.sence.bean.response.POrderDetailsBean;
import com.sence.net.HttpCode;
import com.sence.net.HttpManager;
import com.sence.net.manager.ApiCallBack;
import com.sence.utils.LoginStatus;
import com.sence.utils.StatusBarUtil;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
/**
 * 订单详情
 */
public class OrderDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mState,mNumber,mTime,mName,mPhone,mAddress,mCoupon,mStroeName,mPostPrice,mTaxPrice,mMoney,mSprice,mShopPrice;
    private String id;
    private RecyclerView mRecyclerView;
    private OrderDetailsAdapter orderDetailsAdapter;
    private TextView mTv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderdetails);
        StatusBarUtil.setLightMode(this);
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        initData();

    }

    private void initData() {
        findViewById(R.id.rl_address_orderdetails).setOnClickListener(this);
        TextView mTitle = findViewById(R.id.pub_title);
        mTitle.setText("订单详情");
        ImageView mBack = findViewById(R.id.pub_back);
        mTv = findViewById(R.id.pub_right_tv);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mTv.setText("取消订单");
        mTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CancelOreder();
            }
        });
        mRecyclerView = findViewById(R.id.recycle_orderdetails);
        mState = findViewById(R.id.tv_state_orderdetails);
        mNumber = findViewById(R.id.tv_number_orderdetails);
        mTime = findViewById(R.id.tv_time_orderdetails);
        mName = findViewById(R.id.tv_name_orderdetails);
        mPhone = findViewById(R.id.tv_phone_orderdetails);
        mAddress = findViewById(R.id.tv_address_orderdetails);
        mCoupon = findViewById(R.id.tv_coupon_orderdetails);
        mPostPrice = findViewById(R.id.tv_postprice_orderdetails);
        mTaxPrice = findViewById(R.id.tv_taxprice_orderdetails);
        mShopPrice = findViewById(R.id.tv_shopprice_orderdetails);
        mStroeName = findViewById(R.id.tv_stroename_orderdetails);
        mMoney = findViewById(R.id.tv_money_orderdetails);
        mSprice = findViewById(R.id.tv_sprice_orderdetails);
        orderDetailsAdapter = new OrderDetailsAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(orderDetailsAdapter);

        doHttp();
    }

    private void doHttp() {
        HttpManager.getInstance().PlayNetCode(HttpCode.ORDER_DETAIL, new ROrderDetailsBean(id,LoginStatus.getUid())).request(new ApiCallBack<POrderDetailsBean>() {



            @Override
            public void onFinish() {

            }

            @Override
            public void Message(int code, String message) {
            }

            @Override
            public void onSuccess(final POrderDetailsBean o, String msg) {
                Logger.e("msg==========" + msg);
                if(o.getGoods().size()>0){
                    orderDetailsAdapter.setList(o.getGoods());
                }
                mState.setText(o.getStatus());
                mNumber.setText(o.getOid());
                mTime.setText(o.getAddtime());
                mStroeName.setText(o.getShopname());
                mName.setText(o.getAddress().getUsername());
                mPhone.setText(o.getAddress().getPhone());
                mAddress.setText(o.getAddress().getAddress());
                mShopPrice.setText("￥"+o.getGmoney());
                mCoupon.setText("-￥"+o.getCmoney());
                mPostPrice.setText("+￥"+o.getPmoney());
                mTaxPrice.setText("+￥"+o.getFee());
                mMoney.setText("￥"+o.getNeedpay());
                mSprice.setText("￥"+o.getNeedpay());
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_address_orderdetails:
                startActivity(new Intent(OrderDetailsActivity.this,ManageAddressActivity.class));
                break;
        }
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
                if(msg.equals("取消成功")){
                    ToastUtils.showShort("订单取消成功");
                    mTv.setText("取消成功");
                    mTv.setClickable(false);
                    mState.setText("订单已取消");

                }
            }
        });

    }
}
