package com.sence.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.sence.R;
import com.sence.bean.response.PServiceCommendBean;
import com.sence.net.Urls;
import com.sence.view.NiceImageView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ServiceDetailsAdapter extends RecyclerView.Adapter<ServiceDetailsAdapter.ViewHolder> {
    private Context context;
    private List<PServiceCommendBean> list = new ArrayList<>();

    public ServiceDetailsAdapter(Context context){
        this.context = context;
    }
    public void setList(List<PServiceCommendBean> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ServiceDetailsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_item_servicedetails,parent,false);
        return new ServiceDetailsAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ServiceDetailsAdapter.ViewHolder holder, int position) {
        holder.mContent.setText(list.get(position).getContent());
        holder.mName.setText(list.get(position).getUsername());
        holder.mTime.setText(list.get(position).getTime());
        RequestOptions options = new RequestOptions();
        options.placeholder(R.drawable.hint_img);
        Glide.with(context)
                .load(Urls.base_url + list.get(position).getAvatar())
                .into(holder.mImageView);
        ServiceDetailsImgAdapter mServiceDetailsImgAdapter = new ServiceDetailsImgAdapter(context);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context,3);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        holder.mRecyclerView.setLayoutManager(gridLayoutManager);
        holder.mRecyclerView.setAdapter(mServiceDetailsImgAdapter);
        int i = Integer.parseInt(list.get(position).getStar());
        Log.i("aaa",i+"=");
        EnjoyVipImgAdapter mEnjoyVipImgAdapter = new EnjoyVipImgAdapter(context);
        LinearLayoutManager linearLayoutManagero = new LinearLayoutManager(context);
        linearLayoutManagero.setOrientation(linearLayoutManagero.HORIZONTAL);
        holder.mXing.setLayoutManager(linearLayoutManagero);
        holder.mXing.setAdapter(mEnjoyVipImgAdapter);
        mEnjoyVipImgAdapter.setList(i,R.drawable.star_orange);
        if(i<5){
            EnjoyVipImgAdapter mEnjoyVipImgAdaptert = new EnjoyVipImgAdapter(context);
            LinearLayoutManager linearLayoutManagert = new LinearLayoutManager(context);
            linearLayoutManagert.setOrientation(linearLayoutManagert.HORIZONTAL);
            holder.mBXing.setLayoutManager(linearLayoutManagert);
            holder.mBXing.setAdapter(mEnjoyVipImgAdaptert);
            mEnjoyVipImgAdaptert.setList(5-i,R.drawable.star_gray);
        }
        if(list.get(position).getImgs().size()>0){
            mServiceDetailsImgAdapter.setList(list.get(position).getImgs());
        }else{
            holder.mRecyclerView.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {


        private NiceImageView mImageView;
        private TextView mName,mContent,mTime;
        private RecyclerView mRecyclerView,mXing,mBXing;

        public ViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.iv_vimg_servicedetails);
            mTime = itemView.findViewById(R.id.tv_vtime_servicedetails);
            mName = itemView.findViewById(R.id.tv_vname_servicedetails);
            mContent = itemView.findViewById(R.id.tv_vcontent_servicedetails);
            mRecyclerView = itemView.findViewById(R.id.recycle_servicedetails_img);
            mXing = itemView.findViewById(R.id.recycle_xing_servicedetails);
            mBXing = itemView.findViewById(R.id.recycle_bxing_servicedetails);
        }
    }
}
