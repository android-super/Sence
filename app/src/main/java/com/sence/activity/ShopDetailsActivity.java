package com.sence.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.appbar.AppBarLayout;
import com.sence.R;
import com.sence.adapter.ShopDetailsCommendAdapter;
import com.sence.adapter.ShopDetailsImgAdapter;

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
    private List<Integer> list = new ArrayList<>();
    private TextView mNum;
    private RecyclerView mRecyclerView;
    private ShopDetailsCommendAdapter mShopDetailsCommendAdapter;
    private ImageView mBack;
    private LinearLayout mLinearLayout;
    private View mView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopdetails);
        initData();
    }
    private void installListener() {

        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                float alpha = (float)Math.abs(i)/appBarLayout.getTotalScrollRange();
                mView.setAlpha(alpha);
                if(alpha>0.8){
                    mLinearLayout.setVisibility(View.VISIBLE);
                }else{
                    mLinearLayout.setVisibility(View.GONE);
                }
            }
        });

    }
    private void initData() {
        mAppBarLayout = findViewById(R.id.app_bar_layout);
        mLinearLayout = findViewById(R.id.ll_head_shopdetails);
        findViewById(R.id.ll_shopcommend_shopdetails).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ShopDetailsActivity.this,ShopCommendActivity.class));
            }
        });
        mViewPager = findViewById(R.id.vp_img_shopdetails);
        mNum = findViewById(R.id.tv_imgnum_shopdetails);
        mRecyclerView = findViewById(R.id.recycle_shopdetails);
        mView = findViewById(R.id.shop_view);
        mBack = findViewById(R.id.iv_back_shopdetails);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mShopDetailsCommendAdapter = new ShopDetailsCommendAdapter(ShopDetailsActivity.this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ShopDetailsActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mShopDetailsCommendAdapter);
        list.add(R.drawable.hint_img);
        list.add(R.drawable.hint_img);
        list.add(R.drawable.hint_img);
        list.add(R.drawable.hint_img);
        installListener();
        ShopDetailsImgAdapter shopDetailsImgAdapter = new ShopDetailsImgAdapter(ShopDetailsActivity.this, list, mViewPager);
        mViewPager.setAdapter(shopDetailsImgAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mNum.setText(++position + "/" + list.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

}

