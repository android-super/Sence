package com.sence.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sence.R;
import com.sence.bean.response.PMyInfoBean;
import com.sence.net.Urls;
import com.sence.view.NiceImageView;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.annotations.NonNull;

public class SearchFriendAdapter extends RecyclerView.Adapter<SearchFriendAdapter.ViewHolder> {
    private Context context;
    private List<PMyInfoBean.OtherInfoBean> list = new ArrayList<>();

    public SearchFriendAdapter(Context context){
        this.context = context;
    }
    public void setList(List<PMyInfoBean.OtherInfoBean> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SearchFriendAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_item_note,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull SearchFriendAdapter.ViewHolder holder, int position) {
        holder.mName.setText(list.get(position).getNick_name());
        holder.mContent.setText(list.get(position).getContent());
        holder.mLike.setText(list.get(position).getPraise_count());
        Glide.with(context)
                .load(Urls.base_url + list.get(position).getAvatar())
                .placeholder(R.drawable.hint_img)
                .fallback(R.drawable.hint_img)
                .into(holder.mImageView);
        Glide.with(context)
                .load(Urls.base_url + list.get(position).getAlbum_url())
                .placeholder(R.drawable.hint_img)
                .fallback(R.drawable.hint_img)
                .into(holder.mImg);
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
