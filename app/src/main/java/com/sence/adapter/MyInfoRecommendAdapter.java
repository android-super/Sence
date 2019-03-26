package com.sence.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sence.R;
import com.sence.bean.response.PMyOrderBean;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyInfoRecommendAdapter extends RecyclerView.Adapter<MyInfoRecommendAdapter.ViewHolder> {
    private Context context;
    private List<PMyOrderBean.ListBean> list = new ArrayList<>();

    public MyInfoRecommendAdapter(Context context){
        this.context = context;
    }
    public void setList(List<PMyOrderBean.ListBean> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyInfoRecommendAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_item_myinforecommend,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyInfoRecommendAdapter.ViewHolder holder, int position) {
//        Glide.with(context).load(list.get(position).getGoods().getImg()).placeholder(R.drawable.hint_img).fallback(R.drawable.hint_img).into(holder.imageView);
//        holder.name.setText(list.get(position).getGoods().getName());
//        holder.time.setText(list.get(position).getAddtime());
    }

    @Override
    public int getItemCount() {
        return 10;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {


        private ImageView mImageView;
        private TextView mName,mPrice,mPnum,mState,mNum,mSevice,mCancel,mAlipay,mTime,mPprice;

        public ViewHolder(View itemView) {
            super(itemView);
            mState = itemView.findViewById(R.id.tv_state_myorder);
//            time = itemView.findViewById(R.id.tv_time_myorder);
            mImageView = itemView.findViewById(R.id.iv_img_myorder);
            mName = itemView.findViewById(R.id.tv_name_myorder);
//            pprice = itemView.findViewById(R.id.tv_pprice_myorder);
//            pnum = itemView.findViewById(R.id.tv_pnum_myorder);
//            num = itemView.findViewById(R.id.tv_num_myorder);
//            price = itemView.findViewById(R.id.tv_price_myorder);
//            sevice = itemView.findViewById(R.id.tv_service_myorder);
//            cancel = itemView.findViewById(R.id.tv_cancel_myorder);
//            alipay = itemView.findViewById(R.id.tv_alipay_myorder);
        }
    }
}
