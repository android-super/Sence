package com.sence.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import com.orhanobut.logger.Logger;
import com.sence.R;
import com.sence.activity.chat.ui.ChatMsgActivity;
import com.sence.adapter.MyInfoRecommendViewPagerAdatpter;
import com.sence.base.BaseActivity;
import com.sence.bean.request.RCancelFocusBean;
import com.sence.bean.request.RMyInfoBean;
import com.sence.bean.response.PMyInfoBean;
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

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 个人信息
 */
public class MyInfoActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.iv_focus_myinfo)
    ImageView ivFocusMyinfo;
    @BindView(R.id.iv_chat_myinfo)
    ImageView ivChatMyinfo;
    @BindView(R.id.iv_groupchat_myinfo)
    ImageView ivGroupchatMyinfo;
    private ViewPager mViewPager;
    private AppBarLayout mAppBarLayout;
    private TabLayout mTabLayoutButtom;
    private ImageView mBack;
    private View mView;
    private TextView mDeditor, mName, mAddress, mFocusNum, mFansNum, mSigner, mShopName, mShopPrice;
    private NiceImageView mImg, mImageView;
    private RelativeLayout mShop;
    private ImageView mHead;
    private NiceImageView mIsV;
    private LinearLayout ll_group_myinfo;

    private MyInfoRecommendViewPagerAdatpter mMyInfoRecommendViewPagerAdatpter;
    private String[] list = {"推荐", "笔记", "共享"};
    private int scaleRatio;
    private PMyInfoBean bean;
    private int page = 1;
    private String uid = "";
    private String is_focus;

    @Override
    public int onActLayout() {
        return R.layout.activity_myinfo;
    }

    @Override
    public void initView() {
        if (NavigationBarUtil.hasNavigationBar(this)) {
            NavigationBarUtil.initActivity(findViewById(android.R.id.content));
        }
        StatusBarUtil.setTranslucentForCoordinatorLayout(this, 0);
        StatusBarUtil.setLightMode(this);
        Intent intent = getIntent();
        Log.i("aaaaaa",uid+"==="+LoginStatus.getUid());
        uid = intent.getStringExtra("uid");
        if (TextUtils.isEmpty(uid)) {
            uid = LoginStatus.getUid();
        }
        mAppBarLayout = findViewById(R.id.al_appbar_myinfo);
        mDeditor = findViewById(R.id.tv_deditor_myinfo);
        mTabLayoutButtom = findViewById(R.id.tl_tabhid_myinfo);
        mView = findViewById(R.id.shop_view_myinfo);
        mName = findViewById(R.id.tv_name_myinfo);
        mAddress = findViewById(R.id.tv_address_myinfo);
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
        mIsV = findViewById(R.id.iv_isv_myinfo);
        mBack = findViewById(R.id.iv_back_myinfo);
        ll_group_myinfo = findViewById(R.id.ll_group_myinfo);
        mBack.setOnClickListener(this);
        ll_group_myinfo.setOnClickListener(this);
        findViewById(R.id.tv_deditor_myinfo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyInfoActivity.this, EditInfoActivity.class));
            }
        });
        final UserRecommendFragment recommendFragment = new UserRecommendFragment();
        final UserNoteFragment noteFragment = new UserNoteFragment();
        MyInfoRecommendFragment myInfoRecommendFragment = new MyInfoRecommendFragment();
        final Fragment[] fragmentList = {recommendFragment, noteFragment, myInfoRecommendFragment};
        mMyInfoRecommendViewPagerAdatpter = new MyInfoRecommendViewPagerAdatpter(getSupportFragmentManager(), this, fragmentList, list);
        mViewPager.setAdapter(mMyInfoRecommendViewPagerAdatpter);
        mTabLayoutButtom.setupWithViewPager(mViewPager);
        myInfoRecommendFragment.result(new MyInfoRecommendFragment.DeleteServiceListener() {
            @Override
            public void delete() {
                final Fragment[] fragments = {recommendFragment, noteFragment};
                String[] listTitle = {"推荐", "笔记"};
                mMyInfoRecommendViewPagerAdatpter.setList(fragments, listTitle);
            }
        });
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                float alpha = (float) Math.abs(i) / appBarLayout.getTotalScrollRange();
                mView.setAlpha(alpha);
                if (alpha > 0.8) {
                    mBack.setImageResource(R.drawable.login_fanhui);
                    mDeditor.setTextColor(Color.parseColor("#222222"));
                } else {
                    mBack.setImageResource(R.drawable.myinfo_back);
                    mDeditor.setTextColor(Color.parseColor("#ffffff"));
                }
            }
        });
        findViewById(R.id.rl_shop_myinfo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyInfoActivity.this, ShopDetailsActivity.class);
                intent.putExtra("id", bean.getGoods_info().getId());
                startActivity(intent);
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
                message.obj = blurBitmap;
                handler.sendMessage(message);
                //                        刷新ui必须在主线程中执行

            }
        }).start();


    }

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bitmap blurBitmap = (Bitmap) msg.obj;
            mHead.setScaleType(ImageView.ScaleType.CENTER_CROP);
            mHead.setImageBitmap(blurBitmap);
            handler.removeCallbacksAndMessages(null);
        }
    };

    public void initData() {
        HttpManager.getInstance().PlayNetCode(HttpCode.USER_INFO_DATA, new RMyInfoBean(LoginStatus.getUid(), "1", uid, page + "", "10")).request(new ApiCallBack<PMyInfoBean>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void Message(int code, String message) {

            }

            @Override
            public void onSuccess(PMyInfoBean o, String msg) {
                Logger.e("msg==========" + msg);
                bean = o;
                is_focus = o.getIs_focus();
                if("1".equals(is_focus)){
                    ivFocusMyinfo.setImageResource(R.drawable.yiguanzhu);
                    ivChatMyinfo.setImageResource(R.drawable.si);
                }else{
                    ivFocusMyinfo.setImageResource(R.drawable.guanzhu);
                    ivChatMyinfo.setImageResource(R.drawable.sixin);
                }
                mName.setText(o.getNick_name());
                mAddress.setText(o.getDetails());
                mFocusNum.setText(o.getFocus_num());
                mFansNum.setText(o.getFans_num());
                mSigner.setText(o.getAutograph());
                if (o.getIs_kol().equals("1")) {
                    mIsV.setVisibility(View.VISIBLE);
                } else {
                    mIsV.setVisibility(View.GONE);
                }
                GlideUtils.getInstance().loadHead(o.getAvatar(), mImageView);
                dim(Urls.base_url + o.getAvatar());
                if (o.getIs_kol().equals("1")) {
                    mShopName.setText(o.getGoods_info().getName());
                    mShopPrice.setText("￥ " + o.getGoods_info().getPrice());
                    GlideUtils.getInstance().loadHead(o.getGoods_info().getImg(), mImg);
                } else {
                    mShop.setVisibility(View.GONE);
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
            case R.id.ll_group_myinfo:
                Intent intent = new Intent(MyInfoActivity.this, ChatMsgActivity.class);
                intent.putExtra("v_id", bean.getVid());
                startActivity(intent);
                break;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_focus_myinfo, R.id.iv_chat_myinfo, R.id.iv_groupchat_myinfo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_focus_myinfo:
                if("1".equals(is_focus)){
                    cancelFocus();
                }else{
                    focus();
                }

                break;
            case R.id.iv_chat_myinfo:

                break;
            case R.id.iv_groupchat_myinfo:
                Intent intent = new Intent(MyInfoActivity.this,ChatMsgActivity.class);
                intent.putExtra("v_id",bean.getVid());
                startActivity(intent);
                break;
        }
    }
    private void focus() {
        HttpManager.getInstance().PlayNetCode(HttpCode.USER_FOCUS, new RCancelFocusBean(LoginStatus.getUid(),uid)).request(new ApiCallBack<Object>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void Message(int code, String message) {

            }

            @Override
            public void onSuccess(Object o, String msg) {
                Logger.e("msg==========" + msg);
                ToastUtils.showShort(msg);
                is_focus="1";
                ivFocusMyinfo.setImageResource(R.drawable.yiguanzhu);
                ivChatMyinfo.setImageResource(R.drawable.si);
            }
        });
    }

    private void cancelFocus() {
        HttpManager.getInstance().PlayNetCode(HttpCode.USER_FOCUS_CANCEL, new RCancelFocusBean(LoginStatus.getUid(),uid)).request(new ApiCallBack<String>() {
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
                is_focus="0";
                ivFocusMyinfo.setImageResource(R.drawable.guanzhu);
                ivChatMyinfo.setImageResource(R.drawable.sixin);
            }
        });
    }

}
