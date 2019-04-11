package com.sence.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sence.R;
import com.sence.bean.response.PEnjoyVipBean;
import com.sence.utils.GlideUtils;
import com.sence.view.NiceImageView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class EnjoyVipAdapter extends RecyclerView.Adapter<EnjoyVipAdapter.ViewHolder> {
    private Context context;
    private List<PEnjoyVipBean.ServiceBean> list = new ArrayList<>();

    public EnjoyVipAdapter(Context context){
        this.context = context;
    }
    public void setList(List<PEnjoyVipBean.ServiceBean> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public EnjoyVipAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_item_enjoyvip,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull EnjoyVipAdapter.ViewHolder holder, int position) {
        GlideUtils.getInstance().loadHead(  list.get(position).getImg(),holder.mImageView);
        if(list.get(position).getTag().size()==2){
            holder.mOlaber.setText(list.get(position).getTag().get(0));
            holder.mTlaber.setText(list.get(position).getTag().get(1));
        }else if(list.get(position).getTag().size()==1){
            holder.mOlaber.setText(list.get(position).getTag().get(0));
            holder.mTlaber.setVisibility(View.GONE);
        }
        holder.mNum.setText("体验 "+list.get(position).getNum()+"次");
        EnjoyVipImgAdapter enjoyVipImgAdapter = new EnjoyVipImgAdapter(context);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        holder.mRecyclerView.setLayoutManager(linearLayoutManager);
        holder.mRecyclerView.setAdapter(enjoyVipImgAdapter);
        int num = Integer.parseInt(list.get(position).getStar());
        enjoyVipImgAdapter.setList(num,R.drawable.enjoyvip_xingxing);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {


        private NiceImageView mImageView;
        private TextView mNum,mOlaber,mTlaber;
        private RecyclerView mRecyclerView;

        public ViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.iv_img_enjoyvip_item);
            mOlaber = itemView.findViewById(R.id.tv_olabel_enjoyvip_item);
            mTlaber = itemView.findViewById(R.id.tv_tlabel_enjoyvip_item);
            mNum = itemView.findViewById(R.id.tv_num_enjoyvip_item);
            mRecyclerView = itemView.findViewById(R.id.recycle_enjoyvipimg);
        }
    }
}
