package com.sence.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.appbar.AppBarLayout;
import com.sence.R;
import com.sence.activity.ShopDetailsActivity;
import com.sence.adapter.ShopDetailsCommendAdapter;
import com.sence.adapter.ShopDetailsImgAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

public class ShopDetailsFragment extends Fragment {
    private AppBarLayout bar;
    private ViewPager viewPager;
    private List<Integer> list = new ArrayList<>();
    private TextView num;
    private RecyclerView recyclerView;
    private ShopDetailsCommendAdapter shopDetailsCommendAdapter;
    private NestedScrollView nestedScrollView;
    private ImageView back;
    private LinearLayout linearLayout;
    private View view;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_shopdetails, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    private void installListener() {

        bar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                float alpha = (float)Math.abs(i)/appBarLayout.getTotalScrollRange();
                view.setAlpha(alpha);
                if(alpha>=1){
                    linearLayout.setVisibility(View.VISIBLE);
                }else{
                    linearLayout.setVisibility(View.GONE);
                }
            }
        });

    }
    private void initData() {
        bar = getView().findViewById(R.id.app_bar_layout);
        linearLayout = getView().findViewById(R.id.ll_head_shopdetails);
        viewPager = getView().findViewById(R.id.vp_img_shopdetails);
        num = getView().findViewById(R.id.tv_imgnum_shopdetails);
        recyclerView = getView().findViewById(R.id.recycle_shopdetails);
        nestedScrollView = getView().findViewById(R.id.nsv_content_shopdetails);
        view = getView().findViewById(R.id.shop_view);
        getView().findViewById(R.id.ll_shopcommend_shopdetails).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ShopDetailsActivity)getActivity()).setchangeradapter();
            }
        });
        getView().findViewById(R.id.tv_commend_shopdetails).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        back = getView().findViewById(R.id.iv_back_shopdetails);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nestedScrollView.fullScroll(NestedScrollView.FOCUS_DOWN);
            }
        });
        shopDetailsCommendAdapter = new ShopDetailsCommendAdapter(getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(shopDetailsCommendAdapter);
        list.add(R.drawable.hint_img);
        list.add(R.drawable.hint_img);
        list.add(R.drawable.hint_img);
        list.add(R.drawable.hint_img);
        installListener();
        ShopDetailsImgAdapter shopDetailsImgAdapter = new ShopDetailsImgAdapter(getContext(), list, viewPager);
        viewPager.setAdapter(shopDetailsImgAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                num.setText(++position + "/" + list.size());
                if(position==list.size()-1){

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
