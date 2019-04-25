package com.sence.fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.blankj.utilcode.util.ToastUtils;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.scwang.smartrefresh.layout.util.DensityUtil;
import com.sence.MainActivity;
import com.sence.R;
import com.sence.activity.MyOrderActivity;
import com.sence.adapter.MyOrderAdapter;
import com.sence.bean.request.RAliPayBean;
import com.sence.bean.request.RMyOrderBean;
import com.sence.bean.request.RTimeRemainingBean;
import com.sence.bean.request.RWxPayBean;
import com.sence.bean.response.PMyOrderBean;
import com.sence.bean.response.PTimeRemainingBean;
import com.sence.bean.response.PWxPayBean;
import com.sence.net.HttpCode;
import com.sence.net.HttpManager;
import com.sence.net.manager.ApiCallBack;
import com.sence.utils.DateUtils;
import com.sence.utils.LoginStatus;
import com.sence.utils.SharedPreferencesUtil;
import com.sence.wxapi.WeiXinPayUtils;
import com.sence.zhifubao.PayResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MyOrderFragment extends Fragment implements View.OnClickListener {

    private RecyclerView mRecyclerView;
    private TextView mMore;
    private SmartRefreshLayout mSmartRefreshLayout;
    private MyOrderAdapter mMyOrderAdapter;
    private int page=1;
    private int status;
    private List<PMyOrderBean.ListBean> listBeans = new ArrayList<>();
    private ImageView mNot;
    private Disposable mDisposable;
    private BottomSheetDialog mBottomSheetDialog;
    private TextView tvPricePay;
    private TextView tvTimePay;
    private TextView tvMinutePay;
    private TextView tvSecondPay;
    private ImageView ivZhiPay;
    private ImageView ivWeiPay;
    private int time;
    private int PAYMENTTYPE = 2;
    private AlertDialog dialog;
    private int postion;
    private boolean issetnum = true;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_myorder, container, false);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle arguments = getArguments();
        status = arguments.getInt("status",0);
        init();
    }
    

    private void init() {
        mRecyclerView = getView().findViewById(R.id.recycle_myorder);
        mSmartRefreshLayout = getView().findViewById(R.id.srl_more_myorder);
        mMore = getView().findViewById(R.id.tv_more_myorder);
        mNot = getView().findViewById(R.id.iv_not_myorder);
        mMyOrderAdapter = new MyOrderAdapter(getContext());
        LinearLayoutManager linearLayout = new LinearLayoutManager(getContext());
        linearLayout.setOrientation(RecyclerView.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayout);
        mRecyclerView.setAdapter(mMyOrderAdapter);
        mSmartRefreshLayout.setRefreshHeader(new ClassicsHeader(getActivity()));
        mSmartRefreshLayout.setRefreshFooter(new ClassicsFooter(getActivity()));
        mSmartRefreshLayout.setEnableLoadMoreWhenContentNotFull(false);
        mSmartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                if(listBeans.size()<10){
                    ToastUtils.showShort("没有更多了！");
                }else{
                    page++;
                    loadData();
                }
                mSmartRefreshLayout.finishLoadMore();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mSmartRefreshLayout.finishRefresh();
                page=1;
                loadData();
            }
        });
        mMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page++;
                loadData();
            }
        });
        bottomSheetDialog();
        mMyOrderAdapter.result(new MyOrderAdapter.DeleteOrderListener() {
            @Override
            public void delete(int i) {
                ((MyOrderActivity)getActivity()).setNum(listBeans.get(i).getStatus());
                ((MyOrderActivity)getActivity()).refresh(listBeans.get(i).getStatus());
                listBeans.remove(i);
                if(listBeans.size()==0){
                    mNot.setVisibility(View.VISIBLE);
                }
                mMyOrderAdapter.setList(listBeans);

            }

            @Override
            public void pay(int i) {
                postion = i;
                mBottomSheetDialog.show();
                getTime();

            }
        });

    }


    private void getTime() {
        HttpManager.getInstance().PlayNetCode(HttpCode.TIME_REMAINING, new RTimeRemainingBean(listBeans.get(postion).getId())).request(new ApiCallBack<PTimeRemainingBean>() {


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
        HttpManager.getInstance().PlayNetCode(HttpCode.PAY_WX, new RWxPayBean(LoginStatus.getUid(), "1",listBeans.get(postion).getNeedpay(),listBeans.get(postion).getId())).request(new ApiCallBack<PWxPayBean>() {
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
                WeiXinPayUtils wxpay = new WeiXinPayUtils(getActivity(), o);
                wxpay.pay();
            }
        });
    }
    /**
     * 支付宝
     */
    private void aLiPay() {
        HttpManager.getInstance().PlayNetCode(HttpCode.PAY_ALI, new RAliPayBean("1", LoginStatus.getUid(), listBeans.get(postion).getNeedpay(), listBeans.get(postion).getId())).request(new ApiCallBack<String>() {
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
                        PayTask alipay = new PayTask(getActivity());
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
                        Toast.makeText(getContext(), "支付成功", Toast.LENGTH_SHORT).show();
                        ((MyOrderActivity)getActivity()).setNum(listBeans.get(postion).getStatus());
                        ((MyOrderActivity)getActivity()).refresh(listBeans.get(postion).getStatus());
                        listBeans.remove(postion);
                        if(listBeans.size()==0){
                            mNot.setVisibility(View.VISIBLE);
                        }
                        mMyOrderAdapter.setList(listBeans);
                        alterDone();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(getContext(), "支付失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
                default:
                    break;
            }
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(dialog != null) {
            dialog.dismiss();
        }
        if(mBottomSheetDialog != null) {
            mBottomSheetDialog.dismiss();
        }
    }

    private void alterDone() {
        View view = View.inflate(getContext(), R.layout.alter_deleteorder, null);
        dialog = new AlertDialog.Builder(getContext(), R.style.AlertDialogStyle).create();
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
            }
        });

        mConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent intent = new Intent(getContext(), MainActivity.class);
                intent.putExtra("type",2);
                startActivity(intent);
                getActivity().finish();
            }
        });
    }
    private void bottomSheetDialog() {
        View mView = View.inflate(getContext(), R.layout.bottom_pay_layout, null);
        mBottomSheetDialog = new BottomSheetDialog(getContext());
        mBottomSheetDialog.setContentView(mView);
        tvPricePay = mView.findViewById(R.id.tv_price_pay);
        tvTimePay = mView.findViewById(R.id.tv_time_pay);
        tvMinutePay = mView.findViewById(R.id.tv_minute_pay);
        tvSecondPay = mView.findViewById(R.id.tv_second_pay);
        ivZhiPay = mView.findViewById(R.id.iv_zhi_pay);
        ivWeiPay = mView.findViewById(R.id.iv_wei_pay);
        ImageView ivBackPay = mView.findViewById(R.id.iv_back_pay);
        Button btPayPay = mView.findViewById(R.id.bt_pay_pay);
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
        // 由于interval默认在新线程，所以我们应该切回主线程
        mDisposable = Observable.interval(0, 1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()) // 由于interval默认在新线程，所以我们应该切回主线程
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull Long aLong) {
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
    public void onResume() {
        super.onResume();
        if("paysuccess".equals(LoginStatus.getPayType())){
            SharedPreferencesUtil.getInstance().putString("paytype", "");
            alterDone();
        }else if("payfail".equals(LoginStatus.getPayType())){
            SharedPreferencesUtil.getInstance().putString("paytype", "");
        }
        loadData();
    }

    private void loadData() {
        HttpManager.getInstance().PlayNetCode(HttpCode.ORDER_LIST, new RMyOrderBean(LoginStatus.getUid(),page+"","10",status+"")).request(new ApiCallBack<PMyOrderBean>() {
            @Override
            public void onFinish() {
                mSmartRefreshLayout.finishRefresh();
                mSmartRefreshLayout.finishLoadMore();
            }

            @Override
            public void Message(int code, String message) {

            }

            @Override
            public void onSuccess(PMyOrderBean o, String msg) {
                Logger.e("msg==========" + msg);
                if(issetnum){
                    ((MyOrderActivity)getActivity()).setTitleNum(o.getAllNum(),o.getWaitPay(),o.getWaitSend(),o.getWaitConfirm(),o.getWaitEvlua());
                }
                listBeans = o.getList();
                if(listBeans.size()>0) {
                    mNot.setVisibility(View.GONE);
                }
                mMyOrderAdapter.setList(listBeans);
            }
        });

    }

    public void reresh() {
        loadData();
    }
}
