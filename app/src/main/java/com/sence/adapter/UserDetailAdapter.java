package com.sence.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sence.R;
import com.sence.bean.response.PUserDetailBean;
import com.sence.utils.GlideUtils;
import com.sence.view.NiceImageView;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.annotations.NonNull;

public class UserDetailAdapter extends RecyclerView.Adapter<UserDetailAdapter.ViewHolder> {
    private Context context;
    private List<PUserDetailBean> list = new ArrayList<>();

    public UserDetailAdapter(Context context) {
        this.context = context;
    }

    public void setList(List<PUserDetailBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserDetailAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_item_userdetail, parent, false);
        return new UserDetailAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull UserDetailAdapter.ViewHolder holder, int position) {
        GlideUtils.getInstance().loadHead( list.get(position).getImg(),holder.mImg);
        holder.mName.setText(list.get(position).getNote());
        holder.mTime.setText(list.get(position).getAdd_time());
        if(list.get(position).getNum().contains("-")){
            holder.mPrice.setText(list.get(position).getNum());
        }else{
            holder.mPrice.setText("+"+list.get(position).getNum());
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        private NiceImageView mImg;
        private TextView mName, mTime, mPrice;

        public ViewHolder(View itemView) {
            super(itemView);
            mImg = itemView.findViewById(R.id.iv_img_userdetail);
            mName = itemView.findViewById(R.id.tv_name_usedetail);
            mTime = itemView.findViewById(R.id.tv_time_userdetail);
            mPrice = itemView.findViewById(R.id.tv_price_userdetail);
        }
    }
}
