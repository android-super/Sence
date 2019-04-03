package com.sence.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.view.View;
import android.webkit.*;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.appbar.AppBarLayout;
import com.orhanobut.logger.Logger;
import com.sence.R;
import com.sence.adapter.ShopDetailsCommendAdapter;
import com.sence.adapter.ShopDetailsImgAdapter;
import com.sence.base.BaseActivity;
import com.sence.bean.request.RShopDetailsBean;
import com.sence.bean.response.PShopDetailsBean;
import com.sence.net.HttpCode;
import com.sence.net.HttpManager;
import com.sence.net.Urls;
import com.sence.net.manager.ApiCallBack;
import com.sence.utils.LoginStatus;
import com.sence.utils.NavigationBarUtil;
import com.sence.utils.StatusBarUtil;
import com.sence.view.NiceImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品详情
 */
public class ShopDetailsActivity extends BaseActivity {
    @BindView(R.id.vp_img_shopdetails)
    ViewPager vpImgShopdetails;
    @BindView(R.id.tv_imgnum_shopdetails)
    TextView tvImgnumShopdetails;
    @BindView(R.id.shop_view)
    View shopView;
    @BindView(R.id.iv_back_shopdetails)
    ImageView ivBackShopdetails;
    @BindView(R.id.ll_head_shopdetails)
    LinearLayout llHeadShopdetails;
    @BindView(R.id.rl_head_shopdetails)
    Toolbar rlHeadShopdetails;
    @BindView(R.id.app_barlayout_shiodetails)
    AppBarLayout appBarlayoutShiodetails;
    @BindView(R.id.tv_cprice_shopdetails)
    TextView tvCpriceShopdetails;
    @BindView(R.id.tv_oprice_shopdetails)
    TextView tvOpriceShopdetails;
    @BindView(R.id.tv_discount_shopdetails)
    TextView tvDiscountShopdetails;
    @BindView(R.id.tv_vip_shopdetails)
    TextView tvVipShopdetails;
    @BindView(R.id.tv_name_shopdetails)
    TextView tvNameShopdetails;
    @BindView(R.id.wb_notload)
    View wbNotload;
    @BindView(R.id.wv_content_shopdetails)
    WebView wvContentShopdetails;
    @BindView(R.id.iv_shopimg_shopdetails)
    NiceImageView ivShopimgShopdetails;
    @BindView(R.id.tv_shopname_shopdetails)
    TextView tvShopnameShopdetails;
    @BindView(R.id.tv_shopcommendnum_shopdetails)
    TextView tvShopcommendnumShopdetails;
    @BindView(R.id.tv_goodcommend_shopdetails)
    TextView tvGoodcommendShopdetails;
    @BindView(R.id.ll_shopcommend_shopdetails)
    LinearLayout llShopcommendShopdetails;
    @BindView(R.id.recycle_shopdetails)
    RecyclerView recycleShopdetails;

    private ShopDetailsImgAdapter shopDetailsImgAdapter;
    private ShopDetailsCommendAdapter mShopDetailsCommendAdapter;

    private WebSettings settings;
    private List<String> imgs;

    @Override
    public int onActLayout() {
        return R.layout.activity_shopdetails;
    }

    @Override
    public void initView() {
        if (NavigationBarUtil.hasNavigationBar(this)) {
            NavigationBarUtil.initActivity(findViewById(android.R.id.content));
        }
        StatusBarUtil.setTranslucentForCoordinatorLayout(this, 0);
        StatusBarUtil.setLightMode(this);
        llShopcommendShopdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ShopDetailsActivity.this, ShopCommendActivity.class));
            }
        });
//        ViewGroup.LayoutParams layoutParams = mRelativeLayout.getLayoutParams();
//        layoutParams.height=new DisplayMetrics().widthPixels;
//        mRelativeLayout.setLayoutParams(layoutParams);
        ivBackShopdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        shopDetailsImgAdapter = new ShopDetailsImgAdapter();
        vpImgShopdetails.setAdapter(shopDetailsImgAdapter);
        vpImgShopdetails.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tvImgnumShopdetails.setText(++position + "/" + imgs.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mShopDetailsCommendAdapter = new ShopDetailsCommendAdapter(ShopDetailsActivity.this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ShopDetailsActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recycleShopdetails.setLayoutManager(linearLayoutManager);
        recycleShopdetails.setAdapter(mShopDetailsCommendAdapter);
        installListener();
    }


    private void installListener() {
        appBarlayoutShiodetails.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                float alpha = (float) Math.abs(i) / appBarLayout.getTotalScrollRange();
                shopView.setAlpha(alpha);
//                StatusBarUtil.setTranslucentForCoordinatorLayout(ShopDetailsActivity.this, (int)alpha);
                if (alpha > 0.8) {
                    llHeadShopdetails.setVisibility(View.VISIBLE);
                } else {
                    llHeadShopdetails.setVisibility(View.GONE);
                }
            }
        });

    }

    public void initData() {
        HttpManager.getInstance().PlayNetCode(HttpCode.GOOD_DETAIL, new RShopDetailsBean("1", LoginStatus.getUid())).request(new ApiCallBack<PShopDetailsBean>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void Message(int code, String message) {

            }

            @Override
            public void onSuccess(PShopDetailsBean o, String msg) {
                imgs = o.getImgs();
                Logger.e("msg==========" + msg + o.getComment().size());
                if (o.getComment().size() > 0) {
                    mShopDetailsCommendAdapter.setList(o.getComment());
                }
                if (o.getImgs().size() > 0) {
                    tvImgnumShopdetails.setText("1/" + imgs.size());
                    List<ImageView> list = new ArrayList<>();
                    for (int i = 0; i < imgs.size(); i++) {
                        ImageView imageView = new ImageView(ShopDetailsActivity.this);
                        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                        RequestOptions options = new RequestOptions();
                        options.placeholder(R.drawable.hint_img);
                        Glide.with(ShopDetailsActivity.this)
                                .load(Urls.base_url + imgs.get(i))
                                .into(imageView);
                        list.add(imageView);
                    }
                    shopDetailsImgAdapter.setList(list);
                }
                tvOpriceShopdetails.setText(new Integer(o.getPrice()) + new Integer(o.getVprice()) + "");
                tvCpriceShopdetails.setText(o.getPrice());
                tvDiscountShopdetails.setText(o.getDiscount() + "折");
                if (new Integer(o.getVprice()) == 0) {
                    tvVipShopdetails.setVisibility(View.GONE);
                }
                tvNameShopdetails.setText(o.getName());
                doData(o.getDescribe());
                tvShopnameShopdetails.setText(o.getUsername());
                Glide.with(ShopDetailsActivity.this)
                        .load(Urls.base_url + o.getAvatar())
                        .into(ivShopimgShopdetails);
                tvShopcommendnumShopdetails.setText("商品评价(" + o.getCommentNum() + ")");
                tvGoodcommendShopdetails.setText("好评" + o.getCommentRate() + "%");
            }
        });
    }

    private void doData(final String url) {
        settings = wvContentShopdetails.getSettings();
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setDomStorageEnabled(true);
        settings.setAllowFileAccess(false);
        settings.setAllowFileAccess(false);
        settings.setUseWideViewPort(false);//禁止webview做自动缩放
        settings.setBuiltInZoomControls(false);
        settings.setSupportZoom(false);
        settings.setDisplayZoomControls(false);
        settings.setJavaScriptEnabled(true);
        settings.setAppCacheEnabled(true);
        settings.setSupportMultipleWindows(false);
        settings.setAppCachePath(getDir("cache", Context.MODE_PRIVATE).getPath());
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        wvContentShopdetails.setFocusable(true);
        wvContentShopdetails.requestFocus();
        wvContentShopdetails.setWebChromeClient(new WebChromeClient());  //解决android与H5协议交互,弹不出对话框问题
        wvContentShopdetails.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                //页面加载完成之后
                wbNotload.setVisibility(View.GONE);
            }

            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                wvContentShopdetails.loadUrl(url);
                if (url.startsWith("tel:")) {
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(url));
                    startActivity
                            (intent);
                    return true;
                }
                return true;

            }
        });
        wvContentShopdetails.loadUrl(url);
    }
}

