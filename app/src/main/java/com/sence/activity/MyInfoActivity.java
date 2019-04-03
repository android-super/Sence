package com.sence.activity;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ConvertUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import com.orhanobut.logger.Logger;
import com.sence.R;
import com.sence.adapter.MyInfoRecommendViewPagerAdatpter;
import com.sence.bean.request.RMyInfoBean;
import com.sence.bean.response.PMyInfoBean;
import com.sence.fragment.MyInfoRecommendFragment;
import com.sence.fragment.MyOrderFragment;
import com.sence.fragment.main.NoteFragment;
import com.sence.net.HttpCode;
import com.sence.net.HttpManager;
import com.sence.net.Urls;
import com.sence.net.manager.ApiCallBack;
import com.sence.utils.FastBlurUtil;
import com.sence.utils.NavigationBarUtil;
import com.sence.utils.StatusBarUtil;
import com.sence.view.NiceImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
/**
 * 个人信息
 */
public class MyInfoActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewPager mViewPager;
    private MyInfoRecommendViewPagerAdatpter mMyInfoRecommendViewPagerAdatpter;
    private String[] list = {"推荐","笔记","共享"};
    private AppBarLayout mAppBarLayout;
    private TabLayout mTabLayoutButtom;
    private ImageView mBack;
    private View mView;
    private TextView mDeditor,mName,mAddress,mFocusNum,mFansNum,mSigner,mShopName,mShopPrice;
    private NiceImageView  mImg,mImageView;
    private RelativeLayout mShop;
    private ImageView mHead;
    private NiceImageView mIsV;
    private int scaleRatio;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myinfo);
        if(NavigationBarUtil.hasNavigationBar(this)){
            NavigationBarUtil.initActivity(findViewById(android.R.id.content));
        }
        StatusBarUtil.setTranslucentForCoordinatorLayout(this, 0);
        StatusBarUtil.setLightMode(this);
        initData();
    }

    private void initData() {
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
        mImg = findViewById(R.id.iv_img_myinfo);
        mShopName = findViewById(R.id.tv_shopname_myinfo);
        mShopPrice = findViewById(R.id.tv_price_myinfo);
        mImageView = findViewById(R.id.iv_imgico_myinfo);
        mViewPager = findViewById(R.id.vp_content_myinfo);
        mIsV = findViewById(R.id.iv_isv_myinfo);
        mBack = findViewById(R.id.iv_back_myinfo);
        mBack.setOnClickListener(this);

        final Fragment[] fragmentList = {new MyInfoRecommendFragment(), new NoteFragment(), new MyInfoRecommendFragment(), new MyOrderFragment(), new MyOrderFragment()};
        mMyInfoRecommendViewPagerAdatpter = new MyInfoRecommendViewPagerAdatpter(getSupportFragmentManager(),this,fragmentList,list);
        mViewPager.setAdapter(mMyInfoRecommendViewPagerAdatpter);
        mTabLayoutButtom.setupWithViewPager(mViewPager);
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
        doHttp();
    }

    private void dim(final  String url) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                scaleRatio = 5;
                //  下面的这个方法必须在子线程中执行
                 Bitmap blurBitmap = FastBlurUtil.GetUrlBitmap(url, scaleRatio);
                Message message = new Message();
                message.obj=blurBitmap;
                handler.sendMessage(message);
                //                        刷新ui必须在主线程中执行

            }
        }).start();


    }
    private Handler handler = new Handler(){
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bitmap blurBitmap = (Bitmap) msg.obj;
            mHead.setScaleType(ImageView.ScaleType.CENTER_CROP);
            mHead.setImageBitmap(blurBitmap);
            handler.removeCallbacksAndMessages(null);
        }
    };

    private void doHttp() {
        HttpManager.getInstance().PlayNetCode(HttpCode.USER_INFO_DATA, new RMyInfoBean("4","1")).request(new ApiCallBack<PMyInfoBean>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void Message(int code, String message) {

            }

            @Override
            public void onSuccess(PMyInfoBean o, String msg) {
                Logger.e("msg==========" + msg);
                mName.setText(o.getNick_name());
                mAddress.setText(o.getDetails());
                mFocusNum.setText(o.getFocus_num());
                mFansNum.setText(o.getFans_num());
                mSigner.setText(o.getAutograph());
                if(o.getIs_kol().equals("1")){
                    mIsV.setVisibility(View.VISIBLE);
                }else {
                    mIsV.setVisibility(View.GONE);
                }
                RequestOptions options = new RequestOptions();
                options.placeholder(R.drawable.hint_img);
                Glide.with(MyInfoActivity.this)
                        .load(Urls.base_url + o.getAvatar())
                        .into(mImageView);
//                Glide.with(MyInfoActivity.this)
//                        .load(Urls.base_url + o.getAvatar())
//                        .placeholder(R.drawable.hint_img)
//                        .fallback(R.drawable.hint_img)
//                        .into(mHead);
                dim(Urls.base_url + o.getAvatar());
                if(o.getIs_kol().equals("1")){
                    mShopName.setText(o.getGoods_info().getName());
                    mShopPrice.setText("￥ "+o.getGoods_info().getPrice());
                    Glide.with(MyInfoActivity.this)
                            .load(Urls.base_url + o.getGoods_info().getImg())
                            .into(mImg);
                }else{
                    mShop.setVisibility(View.GONE);
                }
            }
        });
    }


    public void Scale(View view, float scaleY){

        view.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, Math.round(ConvertUtils.dp2px(100)*scaleY)));
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back_myinfo:
                finish();
                break;
        }
    }


}
