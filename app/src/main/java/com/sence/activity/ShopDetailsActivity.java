package com.sence.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.ToastUtils;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.orhanobut.logger.Logger;
import com.sence.MainActivity;
import com.sence.R;
import com.sence.adapter.ShopDetailsCommendAdapter;
import com.sence.adapter.ShopDetailsImgAdapter;
import com.sence.base.BaseActivity;
import com.sence.bean.request.RBusAddBean;
import com.sence.bean.request.RShopDetailsBean;
import com.sence.bean.response.PShopDetailsBean;
import com.sence.net.HttpCode;
import com.sence.net.HttpManager;
import com.sence.net.manager.ApiCallBack;
import com.sence.utils.GlideUtils;
import com.sence.utils.LoginStatus;
import com.sence.utils.NavigationBarUtil;
import com.sence.utils.StatusBarUtil;
import com.sence.view.NiceImageView;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 商品详情
 */
public class ShopDetailsActivity extends BaseActivity implements View.OnClickListener {
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
    @BindView(R.id.tv_vip_shopdetails)
    TextView tvVipShopdetails;
    @BindView(R.id.tv_name_shopdetails)
    TextView tvNameShopdetails;
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
    @BindView(R.id.rl_vp_shiodetails)
    RelativeLayout rlVpShiodetails;
    @BindView(R.id.ll_service_shopdetails)
    LinearLayout llServiceShopdetails;
    @BindView(R.id.ll_shop_shopdetails)
    LinearLayout llShopShopdetails;
    @BindView(R.id.tv_addshop_shopdetails)
    TextView tvAddshopShopdetails;
    @BindView(R.id.tv_buy_shopdetails)
    TextView tvBuyShopdetails;
    @BindView(R.id.ll_fooler_shopdetails)
    LinearLayout llFoolerShopdetails;
    @BindView(R.id.nsv_content_shopdetails)
    NestedScrollView nsvContentShopdetails;
    @BindView(R.id.tv_shopnum_shopdetails)
    TextView tvShopnumShopdetails;
    @BindView(R.id.iv_share_shopdetails)
    ImageView ivShareShopdetails;
    @BindView(R.id.iv_shop_shopdetails)
    ImageView ivShopShopdetails;
    @BindView(R.id.view_oprice_shopdetails)
    View viewOpriceShopdetails;

    private ShopDetailsImgAdapter shopDetailsImgAdapter;
    private ShopDetailsCommendAdapter mShopDetailsCommendAdapter;

    private WebSettings settings;
    private List<String> imgs = new ArrayList<>();
    private String id;
    private PShopDetailsBean bean = null;
    private int num = 0;
    private BottomSheetDialog mBottomSheetDialog;
    private TextView tvPrice, mNum;
    private ImageView mImg;
    private RelativeLayout mJian, mAdd;
    private Button mConfirm;
    private int numShop;
    private PopupWindow popupWindow;
    private View contentView;
    private static String TAG = "";

    @Override
    public int onActLayout() {
        return R.layout.activity_shopdetails;
    }

    @Override
    public void initView() {
        TAG = getClass().getSimpleName();
        if (NavigationBarUtil.hasNavigationBar(this)) {
            NavigationBarUtil.initActivity(findViewById(android.R.id.content));
        }
        StatusBarUtil.setTranslucentForCoordinatorLayout(this, 0);
        StatusBarUtil.setLightMode(this);
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        llShopcommendShopdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCommend = new Intent(ShopDetailsActivity.this, ShopCommendActivity.class);
                intentCommend.putExtra("id", bean.getId());
                intentCommend.putExtra("num", bean.getCartNum());
                intentCommend.putExtra("postage", bean.getPostage());
                intentCommend.putExtra("price", bean.getPrice());
                intentCommend.putExtra("name", bean.getName());
                intentCommend.putExtra("img", bean.getImg());
                intentCommend.putExtra("username", bean.getUsername());
                startActivity(intentCommend);
            }
        });
        bottomSheetDialog();
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
        HttpManager.getInstance().PlayNetCode(HttpCode.GOOD_DETAIL, new RShopDetailsBean(id, LoginStatus.getUid())).request(new ApiCallBack<PShopDetailsBean>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void Message(int code, String message) {

            }

            @Override
            public void onSuccess(PShopDetailsBean o, String msg) {
                Logger.e("msg==========" + msg);
                imgs = o.getImgs();
                bean = o;
                if (o.getComment().size() > 0) {
                    mShopDetailsCommendAdapter.setList(o.getComment());
                }
                num = Integer.parseInt(o.getCartNum());
                if (num > 0) {
                    tvShopnumShopdetails.setVisibility(View.VISIBLE);
                    tvShopnumShopdetails.setText(num + "");
                }
                if (o.getImgs().size() > 0) {
                    tvImgnumShopdetails.setText("1/" + imgs.size());
                    List<ImageView> list = new ArrayList<>();
                    for (int i = 0; i < imgs.size(); i++) {
                        ImageView imageView = new ImageView(ShopDetailsActivity.this);
                        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                        GlideUtils.getInstance().loadHead(imgs.get(i), imageView);
                        list.add(imageView);
                        final int position = i;
                        imageView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(ShopDetailsActivity.this, ImgFlexActivity.class);
                                intent.putStringArrayListExtra("imgs", (ArrayList<String>) imgs);
                                intent.putExtra("position", position);
                                startActivity(intent);
                            }
                        });
                    }
                    shopDetailsImgAdapter.setList(list);
                }

                tvOpriceShopdetails.setText("￥" + o.getPrice());
                int measuredWidth = tvOpriceShopdetails.getMeasuredWidth();
                ViewGroup.LayoutParams params = viewOpriceShopdetails.getLayoutParams();
                params.width = measuredWidth;
                params.height = 3;
                viewOpriceShopdetails.setLayoutParams(params);
                if ("0".equals(o.getVprice())) {
                    tvCpriceShopdetails.setText("￥" + o.getPrice());
                    tvVipShopdetails.setVisibility(View.GONE);
                } else {
                    tvCpriceShopdetails.setText("￥" + o.getVprice());
                }

                tvNameShopdetails.setText(o.getName());
                doData(o.getDescribe());
                tvShopnameShopdetails.setText(o.getUsername());
                GlideUtils.getInstance().loadHead(o.getAvatar(), ivShopimgShopdetails);
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
        bottomwindow();
//        bottomSheetDialog();
        wvContentShopdetails.loadUrl(url);
    }

    private void bottomwindow() {
        contentView = LayoutInflater.from(ShopDetailsActivity.this).inflate(
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
        GlideUtils.getInstance().loadHead(bean.getImg(), mImg);
        if ("1".equals(bean.getIsMember())) {
            if ("0".equals(bean.getVprice())) {
                tvPrice.setText("￥" + bean.getPrice());
            } else {
                tvPrice.setText("￥" + bean.getVprice());
            }
        } else {
            tvPrice.setText("￥" + bean.getPrice());
        }
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    @OnClick({R.id.tv_addshop_shopdetails, R.id.tv_buy_shopdetails, R.id.ll_service_shopdetails, R.id.ll_shop_shopdetails})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_addshop_shopdetails:
                if (LoginStatus.getUid().isEmpty()) {
                    ToastUtils.showShort("请先登录");
                    return;
                }
                if (num == 0) {
                    tvShopnumShopdetails.setVisibility(View.VISIBLE);
                }
                addShop();
                break;
            case R.id.tv_buy_shopdetails:
                if (LoginStatus.getUid().isEmpty()) {
                    ToastUtils.showShort("请先登录");
                    return;
                }
                bgAlpha(0.5f);
                popupWindow.showAtLocation(contentView, Gravity.BOTTOM, 0, 0);

                break;
            case R.id.ll_service_shopdetails:
                if (LoginStatus.getUid().isEmpty()) {
                    ToastUtils.showShort("请先登录");
                    return;
                }

                break;
            case R.id.ll_shop_shopdetails:
                if (LoginStatus.getUid().isEmpty()) {
                    ToastUtils.showShort("请先登录");
                    return;
                }
                Intent intent = new Intent(ShopDetailsActivity.this, MainActivity.class);
                intent.putExtra("type", 3);
                startActivity(intent);
                break;

        }
    }

    private void addShop() {
        HttpManager.getInstance().PlayNetCode(HttpCode.BUS_ADD, new RBusAddBean(bean.getId(), LoginStatus.getUid())).request(new ApiCallBack() {
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
                Intent intentBuy = new Intent(ShopDetailsActivity.this, ShopConfirmOrderActivity.class);
                intentBuy.putExtra("id", bean.getId());
                intentBuy.putExtra("num", mNum.getText().toString());
                intentBuy.putExtra("postage", bean.getPostage());
                if ("0".equals(bean.getVprice())) {
                    intentBuy.putExtra("price", bean.getPrice());
                } else {
                    intentBuy.putExtra("price", bean.getVprice());
                }
                intentBuy.putExtra("name", bean.getName());
                intentBuy.putExtra("img", bean.getImg());
                intentBuy.putExtra("username", bean.getUsername());
                startActivity(intentBuy);
                break;
            case R.id.ll_wei_share:
                shareWeb(ShopDetailsActivity.this, "http://www.baidu.com", bean.getName(), "女神周边，精品生活", SHARE_MEDIA.WEIXIN, bean.getImg());

                mBottomSheetDialog.dismiss();
                break;
            case R.id.ll_friend_share:
                shareWeb(ShopDetailsActivity.this, "http://www.baidu.com", bean.getName(), "女神周边，精品生活", SHARE_MEDIA.WEIXIN_CIRCLE, bean.getImg());
                mBottomSheetDialog.dismiss();
                break;
            case R.id.tv_cancel_share:
                mBottomSheetDialog.dismiss();
                break;
            case R.id.ll_report_share:
                startActivity(new Intent(ShopDetailsActivity.this,ReportCauseActivity.class));
                mBottomSheetDialog.dismiss();
                break;

        }
    }
    private void bottomSheetDialog() {
        View mView = View.inflate(this, R.layout.layout_share, null);
        mBottomSheetDialog = new BottomSheetDialog(this);
        mBottomSheetDialog.setContentView(mView);
        mView.findViewById(R.id.ll_report_share).setOnClickListener(this);
        LinearLayout linearLayout = mView.findViewById(R.id.ll_code_share);
        linearLayout.setVisibility(View.GONE);
        mView.findViewById(R.id.ll_wei_share).setOnClickListener(this);
        mView.findViewById(R.id.ll_friend_share).setOnClickListener(this);
        mView.findViewById(R.id.tv_cancel_share).setOnClickListener(this);
    }

    /**
     * 友盟分享
     * 上下文activity、分享的链接、标题、内容、类型
     * 若是要分享视频、音乐可看官方文档
     */
    public static void shareWeb(final Activity activity, String WebUrl, String title, String description, SHARE_MEDIA
            platform, String img) {

        UMImage thumb = new UMImage(activity, img);
        UMWeb web = new UMWeb(WebUrl);//连接地址(注意链接开头必须包含http)
        web.setTitle(title);//标题
        web.setDescription(description);//描述
        web.setThumb(thumb);//缩略图
        new ShareAction(activity)
                //分享的平台
                .setPlatform(platform)
                .withMedia(web)
                .setCallback(new UMShareListener() {
                    /**
                     * @descrption 分享开始的回调
                     * @param share_media 平台类型
                     */
                    @Override
                    public void onStart(SHARE_MEDIA share_media) {
                        Log.e(TAG, "onStart开始分享的平台: " + share_media);
                    }

                    /**
                     * @descrption 分享成功的回调
                     * @param share_media 平台类型
                     */
                    @Override
                    public void onResult(final SHARE_MEDIA share_media) {
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(activity, " 分享成功 ", Toast.LENGTH_SHORT).show();
                                Log.e(TAG, "onStart分享成功的平台: " + share_media);
                            }
                        });
                    }

                    /**
                     * @descrption 分享失败的回调
                     * @param share_media 平台类型
                     * @param throwable 错误原因
                     */
                    @Override
                    public void onError(final SHARE_MEDIA share_media, final Throwable throwable) {
                        if (throwable != null) {
                            //失败原因
                            Log.e(TAG, "throw:" + throwable.getMessage());
                        }
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(activity, share_media + " 分享失败", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    /**
                     * @descrption 分享取消的回调
                     * @param share_media 平台类型
                     */
                    @Override
                    public void onCancel(final SHARE_MEDIA share_media) {
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(activity, " 分享取消 ", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                })
                .share();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
        Log.e(TAG, "onActivityResult: " + requestCode + "\n" + resultCode + "\n" + data);
    }

    @OnClick(R.id.iv_share_shopdetails)
    public void onViewClicked() {
        mBottomSheetDialog.show();
    }
}

