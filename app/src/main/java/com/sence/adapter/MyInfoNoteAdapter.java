package com.sence.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sence.R;
import com.sence.bean.response.PMyInfoBean;
import com.sence.utils.GlideUtils;
import com.sence.view.NiceImageView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyInfoNoteAdapter extends RecyclerView.Adapter<MyInfoNoteAdapter.ViewHolder> {
    private Context context;
    private List<PMyInfoBean.ListBean> list = new ArrayList<>();

    public MyInfoNoteAdapter(Context context){
        this.context = context;
    }
    public void setList(List<PMyInfoBean.ListBean> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyInfoNoteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_item_note,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyInfoNoteAdapter.ViewHolder holder, int position) {
        GlideUtils.getInstance().loadHead( list.get(position).getAvatar(),holder.mImageView);
        holder.mName.setText(list.get(position).getNick_name());
        holder.mContent.setText(list.get(position).getContent());
        holder.mLike.setText(list.get(position).getPraise_count());
        GlideUtils.getInstance().loadHead( list.get(position).getAlbum_url(),holder.mImg);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {


        private NiceImageView mImageView,mImg;
        private TextView mName,mContent,mLike;

        public ViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.item_head);
            mName = itemView.findViewById(R.id.item_name);
            mContent = itemView.findViewById(R.id.item_describe);
            mImg = itemView.findViewById(R.id.item_img);
            mLike = itemView.findViewById(R.id.item_support);
        }
    }
}
