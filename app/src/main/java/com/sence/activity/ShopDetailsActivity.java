package com.sence.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.AppBarLayout;
import com.orhanobut.logger.Logger;
import com.sence.R;
import com.sence.adapter.ShopDetailsCommendAdapter;
import com.sence.adapter.ShopDetailsImgAdapter;
import com.sence.bean.request.RShopDetailsBean;
import com.sence.bean.response.PShopDetailsBean;
import com.sence.net.HttpCode;
import com.sence.net.HttpManager;
import com.sence.net.Urls;
import com.sence.net.manager.ApiCallBack;
import com.sence.utils.NavigationBarUtil;
import com.sence.utils.StatusBarUtil;
import com.sence.view.NiceImageView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

public class ShopDetailsActivity extends AppCompatActivity {

    private AppBarLayout mAppBarLayout;
    private ViewPager mViewPager;
    private TextView mNum;
    private RecyclerView mRecyclerView;
    private ShopDetailsCommendAdapter mShopDetailsCommendAdapter;
    private ImageView mBack;
    private NiceImageView mShopImg;
    private LinearLayout mLinearLayout;
    private View mView;
    private ShopDetailsImgAdapter shopDetailsImgAdapter;
    private List<String> imgs;
    private WebView mWebView;
    private TextView mCprice, mOprice, mDisCount, mVip, mName, mShopName, mShopCommendNum, mGoodCommend;
    private WebSettings settings;
    private View mNotLoad;
    private RelativeLayout mRelativeLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopdetails);
        if(NavigationBarUtil.hasNavigationBar(this)){
            NavigationBarUtil.initActivity(findViewById(android.R.id.content));
        }

        StatusBarUtil.setTranslucentForCoordinatorLayout(this, 0);
        StatusBarUtil.setLightMode(this);
        initData();
    }



    private void installListener() {

        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                float alpha = (float) Math.abs(i) / appBarLayout.getTotalScrollRange();
                mView.setAlpha(alpha);
//                StatusBarUtil.setTranslucentForCoordinatorLayout(ShopDetailsActivity.this, (int)alpha);
                if (alpha > 0.8) {
                    mLinearLayout.setVisibility(View.VISIBLE);
                } else {
                    mLinearLayout.setVisibility(View.GONE);
                }
            }
        });

    }

    private void initData() {
        mAppBarLayout = findViewById(R.id.app_barlayout_shiodetails);
        mLinearLayout = findViewById(R.id.ll_head_shopdetails);
        findViewById(R.id.ll_shopcommend_shopdetails).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ShopDetailsActivity.this, ShopCommendActivity.class));
            }
        });
        mNotLoad = findViewById(R.id.wb_notload);
        mViewPager = findViewById(R.id.vp_img_shopdetails);
        mRelativeLayout = findViewById(R.id.rl_vp_shiodetails);
//        ViewGroup.LayoutParams layoutParams = mRelativeLayout.getLayoutParams();
//        layoutParams.height=new DisplayMetrics().widthPixels;
//        mRelativeLayout.setLayoutParams(layoutParams);
        mNum = findViewById(R.id.tv_imgnum_shopdetails);
        mCprice = findViewById(R.id.tv_cprice_shopdetails);
        mOprice = findViewById(R.id.tv_oprice_shopdetails);
        mDisCount = findViewById(R.id.tv_discount_shopdetails);
        mName = findViewById(R.id.tv_name_shopdetails);
        mWebView = findViewById(R.id.wv_content_shopdetails);
        mVip = findViewById(R.id.tv_vip_shopdetails);
        mRecyclerView = findViewById(R.id.recycle_shopdetails);
        mView = findViewById(R.id.shop_view);
        mShopCommendNum = findViewById(R.id.tv_shopcommendnum_shopdetails);
        mShopImg = findViewById(R.id.iv_shopimg_shopdetails);
        mShopName = findViewById(R.id.tv_shopname_shopdetails);
        mGoodCommend = findViewById(R.id.tv_goodcommend_shopdetails);
        mBack = findViewById(R.id.iv_back_shopdetails);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        shopDetailsImgAdapter = new ShopDetailsImgAdapter();
        mViewPager.setAdapter(shopDetailsImgAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mNum.setText(++position+ "/" + imgs.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mShopDetailsCommendAdapter = new ShopDetailsCommendAdapter(ShopDetailsActivity.this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ShopDetailsActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mShopDetailsCommendAdapter);
        installListener();
        doHttp();
    }



    private void doHttp() {
        HttpManager.getInstance().PlayNetCode(HttpCode.GOOD_DETAIL, new RShopDetailsBean("1", "")).request(new ApiCallBack<PShopDetailsBean>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void Message(int code, String message) {

            }

            @Override
            public void onSuccess(PShopDetailsBean o, String msg) {
                imgs = o.getImgs();
                Logger.e("msg==========" + msg);
                if (o.getComment().size() > 0) {
                    mShopDetailsCommendAdapter.setList(o.getComment());
                }
                if (o.getImgs().size() > 0) {
                    mNum.setText("1/" + imgs.size());
                    List<ImageView> list = new ArrayList<>();
                    for (int i = 0; i < imgs.size(); i++) {
                        ImageView imageView = new ImageView(ShopDetailsActivity.this);
                        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                        Glide.with(ShopDetailsActivity.this)
                                .load(Urls.base_url + imgs.get(i))
                                .placeholder(R.drawable.hint_img)
                                .fallback(R.drawable.hint_img)
                                .into(imageView);
                        list.add(imageView);
                    }
                    shopDetailsImgAdapter.setList(list);
                }
                mOprice.setText(new Integer(o.getPrice()) + new Integer(o.getVprice()) + "");
                mCprice.setText(o.getPrice());
                mDisCount.setText(o.getDiscount() + "折");
                if (new Integer(o.getVprice()) == 0) {
                    mVip.setVisibility(View.GONE);
                }
                mName.setText(o.getName());
                doData(o.getDescribe());
                mShopName.setText(o.getUsername());
                Glide.with(ShopDetailsActivity.this)
                        .load(Urls.base_url + o.getAvatar())
                        .placeholder(R.drawable.hint_img)
                        .fallback(R.drawable.hint_img)
                        .into(mShopImg);
                mShopCommendNum.setText("商品评价(" + o.getCommentNum() + ")");
                mGoodCommend.setText("好评" + o.getCommentRate() + "%");
            }
        });
    }

    private void doData(final String url) {
        settings = mWebView.getSettings();
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
        mWebView.setFocusable(true);
        mWebView.requestFocus();
        mWebView.setWebChromeClient(new WebChromeClient());  //解决android与H5协议交互,弹不出对话框问题
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                //页面加载完成之后
                mNotLoad.setVisibility(View.GONE);
            }

            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                mWebView.loadUrl(url);
                if (url.startsWith("tel:")) {
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(url));
                    startActivity
                            (intent);
                    return true;
                }
                return true;

            }
        });
        mWebView.loadUrl(url);
    }

}

