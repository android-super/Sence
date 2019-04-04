package com.sence.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.sence.R;
import com.sence.bean.response.PSearchBean;
import com.sence.net.Urls;
import com.sence.view.NiceImageView;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.annotations.NonNull;

public class SearchFriendAdapter extends RecyclerView.Adapter<SearchFriendAdapter.ViewHolder> {
    private Context context;
    private List<PSearchBean.UserListBean> list = new ArrayList<>();

    public SearchFriendAdapter(Context context){
        this.context = context;
    }
    public void setList(List<PSearchBean.UserListBean> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SearchFriendAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_item_searchfriend,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull SearchFriendAdapter.ViewHolder holder, int position) {
        holder.mName.setText(list.get(position).getUsername());
        holder.mContent.setText(list.get(position).getAutograph());
        if(list.get(position).getIsVip().equals("1")){
            holder.mIsv.setVisibility(View.VISIBLE);
        }
        if(list.get(position).getIsFollow().equals("1")){
            holder.mFocus.setText("已关注");
            holder.mFocus.setTextColor(Color.parseColor("#999999"));
            holder.mFocus.setBackgroundResource(R.drawable.shape_searchconcern_yetbg);
        }

        RequestOptions options = new RequestOptions();
        options.placeholder(R.drawable.hint_img);
        Glide.with(context)
                .load(Urls.base_url + list.get(position).getAvatar())
                .into(holder.mImg);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView mIsv;
        private NiceImageView mImg;
        private TextView mName,mContent,mFocus;

        public ViewHolder(View itemView) {
            super(itemView);
            mName = itemView.findViewById(R.id.tv_name_searchfriend);
            mContent = itemView.findViewById(R.id.tv_signer_searchfriend);
            mImg = itemView.findViewById(R.id.iv_img_searchfriend);
            mIsv = itemView.findViewById(R.id.iv_isv_searchfriend);
            mFocus = itemView.findViewById(R.id.tv_focus_searchfriend);
        }
    }
}
