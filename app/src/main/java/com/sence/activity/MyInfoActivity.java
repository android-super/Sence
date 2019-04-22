package com.sence.activity;

import android.app.Activity;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.ToastUtils;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.tabs.TabLayout;
import com.orhanobut.logger.Logger;
import com.sence.R;
import com.sence.activity.chat.ui.ChatMsgActivity;
import com.sence.activity.chat.ui.ChatMsgGroupActivity;
import com.sence.adapter.MyInfoRecommendViewPagerAdatpter;
import com.sence.base.BaseActivity;
import com.sence.bean.request.RCancelFocusBean;
import com.sence.bean.request.RRachelBean;
import com.sence.bean.request.RUserinfoBean;
import com.sence.bean.response.PUserMyInfoBean;
import com.sence.fragment.MyInfoRecommendFragment;
import com.sence.fragment.UserNoteFragment;
import com.sence.fragment.UserRecommendFragment;
import com.sence.net.HttpCode;
import com.sence.net.HttpManager;
import com.sence.net.Urls;
import com.sence.net.manager.ApiCallBack;
import com.sence.utils.FastBlurUtil;
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

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 个人信息
 */
public class MyInfoActivity extends BaseActivity implements View.OnClickListener {
    private static String TAG = "";
    @BindView(R.id.iv_focus_myinfo)
    ImageView ivFocusMyinfo;
    @BindView(R.id.iv_chat_myinfo)
    ImageView ivChatMyinfo;
    @BindView(R.id.iv_edit_myinfo)
    ImageView ivEditMyinfo;
    @BindView(R.id.iv_groupchat_myinfo)
    ImageView ivGroupchatMyinfo;
    @BindView(R.id.iv_share_myinfo)
    ImageView ivShareMyinfo;
    @BindView(R.id.cl_layout_myinfo)
    CoordinatorLayout clLayoutMyinfo;
    @BindView(R.id.iv_notimg_myinfo)
    ImageView ivNotimgMyinfo;
    private ViewPager mViewPager;
    private AppBarLayout mAppBarLayout;
    private TabLayout mTabLayoutButtom;
    private ImageView mBack, mShare;
    private View mView;
    private TextView mName, mAddress, mFocusNum, mFansNum, mSigner, mShopName, mShopPrice;
    private NiceImageView mImg, mImageView;
    private LinearLayout mShop;
    private ImageView mHead;
    private NiceImageView mIsV;
    private boolean recommendShow,noteShow;
    private MyInfoRecommendViewPagerAdatpter mMyInfoRecommendViewPagerAdatpter;
    private String[] list = {"推荐", "笔记", "共享"};
    private int scaleRatio;
    private PUserMyInfoBean bean;
    private String uid = "";
    private String is_focus;
    private UserNoteFragment noteFragment;
    private Fragment[] fragmentList;
    private View layout;
    private UserRecommendFragment recommendFragment;
    private BottomSheetDialog mBottomSheetDialog;
    private boolean touch = true;

    @Override
    public int onActLayout() {
        return R.layout.activity_myinfo;
    }

    @Override
    public void initView() {
        TAG = getClass().getSimpleName();
        if (NavigationBarUtil.hasNavigationBar(this)) {
            NavigationBarUtil.initActivity(findViewById(android.R.id.content));
        }
        StatusBarUtil.setTranslucentForCoordinatorLayout(this, 0);
        StatusBarUtil.setLightMode(this);
        mAppBarLayout = findViewById(R.id.al_appbar_myinfo);
        mTabLayoutButtom = findViewById(R.id.tl_tabhid_myinfo);
        mView = findViewById(R.id.shop_view_myinfo);
        mName = findViewById(R.id.tv_name_myinfo);
        mAddress = findViewById(R.id.tv_address_myinfo);
        mShare = findViewById(R.id.iv_share_myinfo);
        mHead = findViewById(R.id.iv_head_myinfo);
        mFocusNum = findViewById(R.id.tv_focusnum_myinfo);
        mFansNum = findViewById(R.id.tv_fansnum_myinfo);
        mSigner = findViewById(R.id.tv_signer_myinfo);
        mShop = findViewById(R.id.rl_shop_myinfo);
        findViewById(R.id.rl_num_myinfo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyInfoActivity.this, MyFansFocusNoteActivity.class));
            }
        });
        mImg = findViewById(R.id.iv_img_myinfo);
        mShopName = findViewById(R.id.tv_shopname_myinfo);
        mShopPrice = findViewById(R.id.tv_price_myinfo);
        mImageView = findViewById(R.id.iv_imgico_myinfo);
        mViewPager = findViewById(R.id.vp_content_myinfo);
        layout = findViewById(R.id.v_layout_myinfo);
        mIsV = findViewById(R.id.iv_isv_myinfo);
        mBack = findViewById(R.id.iv_back_myinfo);
        mBack.setOnClickListener(this);
        recommendFragment = new UserRecommendFragment();
        noteFragment = new UserNoteFragment();
        MyInfoRecommendFragment myInfoRecommendFragment = new MyInfoRecommendFragment();
        fragmentList = new Fragment[]{recommendFragment, noteFragment, myInfoRecommendFragment};
        mMyInfoRecommendViewPagerAdatpter = new MyInfoRecommendViewPagerAdatpter(getSupportFragmentManager(), this, fragmentList, list);
        mViewPager.setAdapter(mMyInfoRecommendViewPagerAdatpter);
        mTabLayoutButtom.setupWithViewPager(mViewPager);
        Intent intent = getIntent();
        uid = intent.getStringExtra("uid");
        if (TextUtils.isEmpty(uid) || LoginStatus.getUid().equals(uid)) {
            ivEditMyinfo.setVisibility(View.VISIBLE);
            ivFocusMyinfo.setVisibility(View.GONE);
            ivChatMyinfo.setVisibility(View.GONE);
            uid = LoginStatus.getUid();
        }

        myInfoRecommendFragment.result(new MyInfoRecommendFragment.DeleteServiceListener() {
            @Override
            public void delete() {

            }
        });

        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                float alpha = (float) Math.abs(i) / appBarLayout.getTotalScrollRange();
                mView.setAlpha(alpha);
                if (alpha > 0.8) {
                    mBack.setImageResource(R.drawable.login_fanhui);
                    mShare.setImageResource(R.drawable.hei);
                } else {
                    mBack.setImageResource(R.drawable.myinfo_back);
                    mShare.setImageResource(R.drawable.feixiang);
                }
            }
        });
        bottomSheetDialog();
        findViewById(R.id.rl_shop_myinfo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyInfoActivity.this, ShopDetailsActivity.class);
                intent.putExtra("id", bean.getGoods_info().getId());
                startActivity(intent);
            }
        });
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position==0){
                    if(recommendShow){
                        ivNotimgMyinfo.setVisibility(View.VISIBLE);
                    }else{
                        ivNotimgMyinfo.setVisibility(View.GONE);
                    }
                }else if(position==1){
                    if(noteShow){
                        ivNotimgMyinfo.setVisibility(View.VISIBLE);
                    }else{
                        ivNotimgMyinfo.setVisibility(View.GONE);
                    }
                }else if(position==2){
                    ivNotimgMyinfo.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                int[] location = new int[2];
                mTabLayoutButtom.getLocationOnScreen(location);
                int y = location[1];
                Resources resources = getResources();
                DisplayMetrics dm = resources.getDisplayMetrics();
                int height3 = dm.heightPixels;
                int height = height3 - y;
                Log.i("aaas", y + "==" + height3 + "==" + height);
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams)ivNotimgMyinfo.getLayoutParams();
                layoutParams.height =  height/2;
                ivNotimgMyinfo.setLayoutParams(layoutParams);
            }
        });
    }


    private void dim(final String url) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                scaleRatio = 10;
                //  下面的这个方法必须在子线程中执行
                Bitmap blurBitmap = FastBlurUtil.GetUrlBitmap(url, scaleRatio);
                Message message = new Message();
                message.what = 1;
                message.obj = blurBitmap;
                handler.sendMessage(message);
                //                        刷新ui必须在主线程中执行

            }
        }).start();
    }


    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    Bitmap blurBitmap = (Bitmap) msg.obj;
                    mHead.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    mHead.setImageBitmap(blurBitmap);
                    handler.removeCallbacksAndMessages(null);
                    break;

            }

        }
    };

    public void initData() {
        HttpManager.getInstance().PlayNetCode(HttpCode.USER_MYINFO, new RUserinfoBean(LoginStatus.getUid(), uid)).request(new ApiCallBack<PUserMyInfoBean>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void Message(int code, String message) {

            }

            @Override
            public void onSuccess(PUserMyInfoBean o, String msg) {
                Logger.e("msg==========" + msg);
                bean = o;
                is_focus = o.getIs_focus();
                if ("1".equals(is_focus)) {
                    ivFocusMyinfo.setImageResource(R.drawable.yiguanzhu);
                    ivChatMyinfo.setImageResource(R.drawable.si);
                } else {
                    ivFocusMyinfo.setImageResource(R.drawable.guanzhu);
                    ivChatMyinfo.setImageResource(R.drawable.sixin);
                }
                if ("0".equals(o.getIs_kol())) {
                    mShop.setVisibility(View.GONE);
                    layout.setVisibility(View.VISIBLE);
                    mIsV.setVisibility(View.GONE);

                    ivGroupchatMyinfo.setVisibility(View.GONE);
                } else {
                    mShopName.setText(o.getGoods_info().getName());
                    mShopPrice.setText("￥ " + o.getGoods_info().getPrice());
                    GlideUtils.getInstance().loadHead(o.getGoods_info().getImg(), mImg);
                }
                mName.setText(o.getNick_name());
                mAddress.setText(o.getDetails());
                mFocusNum.setText(o.getFocus_num());
                mFansNum.setText(o.getFans_num());
                mSigner.setText(o.getAutograph());
                GlideUtils.getInstance().loadHead(o.getAvatar(), mImageView);
                dim(Urls.base_url + o.getAvatar());
                if ("0".equals(o.getIs_have_service())) {
                    final Fragment[] fragments = {recommendFragment, noteFragment};
                    String[] listTitle = {"推荐", "笔记"};
                    mMyInfoRecommendViewPagerAdatpter.setList(fragments, listTitle);
                }
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back_myinfo:
                finish();
                break;
            case R.id.ll_wei_share:
                shareWeb(MyInfoActivity.this, "http://192.168.1.10:8085/Public/web/share/datum.html?uid=" + LoginStatus.getUid() + "&to_uid=" + uid, bean.getUser_name() + bean.getNick_name() + "的精彩生活", "女神的日常", SHARE_MEDIA.WEIXIN, bean.getAvatar());
                mBottomSheetDialog.dismiss();
                break;
            case R.id.ll_friend_share:
                shareWeb(MyInfoActivity.this, "http://192.168.1.10:8085/Public/web/share/datum.html?uid=" + LoginStatus.getUid() + "&to_uid=" + uid, bean.getUser_name() + bean.getNick_name() + "的精彩生活", "女神的日常", SHARE_MEDIA.WEIXIN_CIRCLE, bean.getAvatar());
                mBottomSheetDialog.dismiss();
                break;
            case R.id.tv_cancel_share:
                mBottomSheetDialog.dismiss();
                break;
            case R.id.ll_report_share:
                rachel();
                mBottomSheetDialog.dismiss();
                break;
            case R.id.ll_code_share:
                ToastUtils.showShort("复制成功");
                CopyToClipboard(this, bean.getInviteCode());
                mBottomSheetDialog.dismiss();
                break;
        }
    }

    public static void CopyToClipboard(Context context, String text) {
        ClipboardManager clip = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        //clip.getText(); // 粘贴
        clip.setText(text); // 复制
    }

    @OnClick({R.id.iv_focus_myinfo, R.id.iv_chat_myinfo, R.id.iv_groupchat_myinfo, R.id.iv_edit_myinfo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_focus_myinfo:
                if ("1".equals(is_focus)) {
                    cancelFocus();
                } else {
                    focus();
                }

                break;
            case R.id.iv_chat_myinfo:
                Intent intentchat = new Intent(MyInfoActivity.this, ChatMsgActivity.class);
                intentchat.putExtra("u_to", bean.getUid());
                intentchat.putExtra("chat_id", "");
                intentchat.putExtra("u_avatar", bean.getAvatar());
                startActivity(intentchat);
                break;
            case R.id.iv_edit_myinfo:
                startActivity(new Intent(MyInfoActivity.this, EditInfoActivity.class));
                break;
            case R.id.iv_groupchat_myinfo:
                Intent intent = new Intent(MyInfoActivity.this, ChatMsgGroupActivity.class);
                intent.putExtra("v_id", bean.getVid());
                startActivity(intent);
                break;
        }
    }

    private void focus() {
        HttpManager.getInstance().PlayNetCode(HttpCode.USER_FOCUS, new RCancelFocusBean(LoginStatus.getUid(), uid)).request(new ApiCallBack<String>() {
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
                is_focus = "1";
                ivFocusMyinfo.setImageResource(R.drawable.yiguanzhu);
                ivChatMyinfo.setImageResource(R.drawable.si);
            }
        });
    }
    private void rachel() {
        HttpManager.getInstance().PlayNetCode(HttpCode.RACHEL, new RRachelBean(LoginStatus.getUid(), uid,"1")).request(new ApiCallBack<String>() {
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
            }
        });
    }
    private void cancelFocus() {
        HttpManager.getInstance().PlayNetCode(HttpCode.USER_FOCUS_CANCEL, new RCancelFocusBean(LoginStatus.getUid(), uid)).request(new ApiCallBack<String>() {
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
                is_focus = "0";
                ivFocusMyinfo.setImageResource(R.drawable.guanzhu);
                ivChatMyinfo.setImageResource(R.drawable.sixin);
            }
        });
    }

    private void bottomSheetDialog() {
        View mView = View.inflate(this, R.layout.layout_share, null);
        mBottomSheetDialog = new BottomSheetDialog(this);
        mBottomSheetDialog.setContentView(mView);
        ImageView mImg = mView.findViewById(R.id.iv_type_share);
        TextView mName = mView.findViewById(R.id.tv_type_share);
        mImg.setImageResource(R.drawable.shape_lahei);
        mName.setText("拉黑");
        if (TextUtils.isEmpty(uid) || LoginStatus.getUid().equals(uid)) {
            mView.findViewById(R.id.ll_layout_share).setVisibility(View.GONE);
        }
        mView.findViewById(R.id.ll_report_share).setOnClickListener(this);
        mView.findViewById(R.id.ll_code_share).setOnClickListener(this);
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
            platform, String url) {

        UMImage thumb = new UMImage(activity, url);
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.iv_share_myinfo)
    public void onViewClicked() {
        mBottomSheetDialog.show();
    }

    public void setIsShow(boolean isShow) {
        noteShow = isShow;
    }

    public void setRecommendShowImg(boolean isShow) {
        recommendShow = isShow;
    }
}
