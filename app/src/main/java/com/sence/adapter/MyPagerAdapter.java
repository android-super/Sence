package com.sence.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sence.R;
import com.sence.activity.ShopDetailsActivity;
import com.sence.bean.response.PUserMyInfoBean;
import com.sence.utils.GlideUtils;
import com.sence.view.NiceImageView;

import java.util.List;

import androidx.viewpager.widget.PagerAdapter;

public class MyPagerAdapter extends PagerAdapter {
    private Context mContext;
    private List<PUserMyInfoBean.GoodsInfoBean> mData;

    public MyPagerAdapter(Context context , List<PUserMyInfoBean.GoodsInfoBean> list) {
        mContext = context;
        mData = list;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = View.inflate(mContext, R.layout.vp_shopitem_myinfo,null);
        NiceImageView mImg = (NiceImageView) view.findViewById(R.id.iv_img_myinfo);
        TextView mName = (TextView) view.findViewById(R.id.tv_shopname_myinfo);
        TextView mPrice = (TextView) view.findViewById(R.id.tv_price_myinfo);
        GlideUtils.getInstance().loadNormal(mData.get(position).getImg(),mImg);
        mName.setText(mData.get(position).getName());
        mPrice.setText("￥ " +mData.get(position).getPrice());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ShopDetailsActivity.class);
                intent.putExtra("id", mData.get(position).getId());
                mContext.startActivity(intent);
            }
        });
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // super.destroyItem(container,position,object); 这一句要删除，否则报错
        container.removeView((View)object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}