package com.sence.activity;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.sence.MainActivity;
import com.sence.R;
import com.sence.adapter.ShopCommendAdapter;
import com.sence.base.BaseActivity;
import com.sence.bean.request.RBusAddBean;
import com.sence.bean.request.RShopCommentBean;
import com.sence.bean.response.PShopCommendBean;
import com.sence.net.HttpCode;
import com.sence.net.HttpManager;
import com.sence.net.manager.ApiCallBack;
import com.sence.utils.GlideUtils;
import com.sence.utils.LoginStatus;
import com.sence.utils.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 商品评论
 */
public class ShopCommendActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.recycle_shopcommend)
    RecyclerView recycleShopcommend;
    @BindView(R.id.srl_layout_shopcommend)
    SmartRefreshLayout srlLayoutShopcommend;
    @BindView(R.id.tv_buy_shopdetails)
    TextView tvBuyShopdetails;
    @BindView(R.id.ll_fooler_shopdetails)
    LinearLayout llFoolerShopdetails;
    @BindView(R.id.tv_shopnum_shopdetails)
    TextView tvShopnumShopdetails;
    @BindView(R.id.tv_notdata_shopcommend)
    TextView tvNotdataShopcommend;

    private ShopCommendAdapter mShopCommendAdapter;
    private int page = 1;
    private String id;
    private List<PShopCommendBean> beanList = new ArrayList<>();
    private View contentView;
    private PopupWindow popupWindow;
    private TextView tvPrice;
    private ImageView mImg;
    private RelativeLayout mJian;
    private RelativeLayout mAdd;
    private Button mConfirm;
    private TextView mNum;
    private int numShop;
    private int num;
    private String postage;
    private String price;
    private String name;
    private String username;
    private String img;

    @Override
    public int onActLayout() {
        return R.layout.activity_shopcommend;
    }

    @Override
    public void initView() {
        StatusBarUtil.setLightMode(this);
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        String numshop = intent.getStringExtra("num");
        num = Integer.parseInt(numshop);
        postage = intent.getStringExtra("postage");
        price = intent.getStringExtra("price");
        name = intent.getStringExtra("name");
        username = intent.getStringExtra("username");
        img = intent.getStringExtra("img");
        if (num > 0) {
            tvShopnumShopdetails.setVisibility(View.VISIBLE);
            tvShopnumShopdetails.setText(num + "");
        }
        mShopCommendAdapter = new ShopCommendAdapter(ShopCommendActivity.this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ShopCommendActivity.this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recycleShopcommend.setLayoutManager(linearLayoutManager);
        srlLayoutShopcommend.setRefreshHeader(new ClassicsHeader(ShopCommendActivity.this));
        srlLayoutShopcommend.setRefreshFooter(new ClassicsFooter(ShopCommendActivity.this));
        srlLayoutShopcommend.setEnableLoadMoreWhenContentNotFull(false);
        recycleShopcommend.setAdapter(mShopCommendAdapter);
        srlLayoutShopcommend.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                if (beanList.size() == 0) {
                    ToastUtils.showShort("没有更多了！");
                } else {
                    initData();
                }
                srlLayoutShopcommend.finishLoadMore();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                srlLayoutShopcommend.finishRefresh();
                page = 1;
                initData();
            }
        });
        bottomwindow();
    }

    public void initData() {
        HttpManager.getInstance().PlayNetCode(HttpCode.COMMENT_SHOP_LIST, new RShopCommentBean(id, page + "", "10",
                LoginStatus.getUid())).request(new ApiCallBack<List<PShopCommendBean>>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void Message(int code, String message) {

            }

            @Override
            public void onSuccess(List<PShopCommendBean> o, String msg) {
                Logger.e("msg==========" + msg);
                beanList = o;
                if (o.size() > 0) {
                    tvNotdataShopcommend.setVisibility(View.GONE);
                    mShopCommendAdapter.setList(o);
                }

            }
        });
    }

    private void bottomwindow() {
        contentView = LayoutInflater.from(ShopCommendActivity.this).inflate(
                R.layout.layout_buyshop, null);
        popupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);// 取得焦点
        //点击外部消失
        popupWindow.setOutsideTouchable(true);
        //设置可以点击
        popupWindow.setTouchable(true);
        //注意  要是点击外部空白处弹框消息  那么必须给弹框设置一个背景色  不然是不起作用的
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        //进入退出的动画，指定刚才定义的style
        popupWindow.setAnimationStyle(R.style.mypopwindow_anim_style);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                //popupwindow消失时使背景不透明
                bgAlpha(1f);
            }
        });
        tvPrice = contentView.findViewById(R.id.tv_buyshop_price);
        mImg = contentView.findViewById(R.id.iv_img_buyshop);
        mNum = contentView.findViewById(R.id.tv_buyshop_num);
        mJian = contentView.findViewById(R.id.rl_buyshop_jian);
        mAdd = contentView.findViewById(R.id.rl_buyshop_add);
        mConfirm = contentView.findViewById(R.id.bt_buyshop_confirm);
        GlideUtils.getInstance().loadNormal(img, mImg);
        tvPrice.setText("￥" + price);
        mJian.setOnClickListener(this);
        mAdd.setOnClickListener(this);
        mConfirm.setOnClickListener(this);
        numShop = Integer.parseInt(mNum.getText().toString());
        // 按下android回退物理键 PopipWindow消失解决
    }

    private void bgAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setAttributes(lp);
    }

    @OnClick({R.id.tv_addshop_shopdetails, R.id.tv_buy_shopdetails, R.id.ll_service_shopdetails, R.id.ll_shop_shopdetails})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_addshop_shopdetails:
                if (num == 0) {
                    tvShopnumShopdetails.setVisibility(View.VISIBLE);
                }
                addShop();
                break;
            case R.id.tv_buy_shopdetails:
                bgAlpha(0.5f);
                popupWindow.showAtLocation(contentView, Gravity.BOTTOM, 0, 0);

                break;
            case R.id.ll_service_shopdetails:

                break;
            case R.id.ll_shop_shopdetails:
                Intent intent = new Intent(ShopCommendActivity.this, MainActivity.class);
                intent.putExtra("type", 3);
                startActivity(intent);
                break;
        }
    }

    private void addShop() {
        HttpManager.getInstance().PlayNetCode(HttpCode.BUS_ADD, new RBusAddBean(id, LoginStatus.getUid())).request(new ApiCallBack() {
            @Override
            public void onFinish() {

            }

            @Override
            public void Message(int code, String message) {

            }

            @Override
            public void onSuccess(Object o, String msg) {
                ToastUtils.showShort("成功加入购物车");
                num++;
                tvShopnumShopdetails.setText(num + "");
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_buyshop_jian:
                if (numShop > 1) {
                    numShop--;
                    mNum.setText(numShop + "");
                }
                break;
            case R.id.rl_buyshop_add:
                numShop++;
                mNum.setText(numShop + "");
                break;
            case R.id.bt_buyshop_confirm:
                popupWindow.dismiss();
                Intent intentBuy = new Intent(ShopCommendActivity.this, ShopConfirmOrderActivity.class);
                intentBuy.putExtra("id", id);
                intentBuy.putExtra("num", numShop + "");
                intentBuy.putExtra("postage", postage);
                intentBuy.putExtra("price", price);
                intentBuy.putExtra("name", name);
                intentBuy.putExtra("img", img);
                intentBuy.putExtra("username", username);
                startActivity(intentBuy);
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
