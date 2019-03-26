package com.sence.activity;

import android.os.Bundle;

import com.sence.R;
import com.sence.adapter.ShopDetailsViewPagerAdatpter;
import com.sence.fragment.ShopCommendFragment;
import com.sence.fragment.ShopDetailsFragment;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

public class ShopDetailsActivity extends AppCompatActivity {


    private ViewPager viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopdetails);
        viewPager = findViewById(R.id.vp_content_shopdetails);
        Fragment [] fragments = {new ShopDetailsFragment(),new ShopCommendFragment()};
        ShopDetailsViewPagerAdatpter shopDetailsViewPagerAdatpter = new ShopDetailsViewPagerAdatpter(getSupportFragmentManager(), this, fragments);
        viewPager.setAdapter(shopDetailsViewPagerAdatpter);
    }

    public void setchangeradapter() {
        viewPager.setCurrentItem(1);
    }
}

