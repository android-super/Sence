package com.sence.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sence.R;
import com.sence.bean.response.PPrivateChatBean;
import com.sence.utils.GlideUtils;
import com.sence.view.NiceImageView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MessagePrivateAdapter extends RecyclerView.Adapter<MessagePrivateAdapter.ViewHolder> {
    private Context context;
    private List<PPrivateChatBean> list = new ArrayList<>();

    public MessagePrivateAdapter(Context context){
        this.context = context;
    }
    public void setList(List<PPrivateChatBean> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_item_messagechat,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GlideUtils.getInstance().loadHead( list.get(position).getAvatar(),holder.mImageView);
        holder.mName.setText(list.get(position).getNick_name());
        holder.mSign.setText(list.get(position).getContent());
        holder.mTime.setText(list.get(position).getTime());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {


        private NiceImageView mImageView;
        private TextView mName,mSign,mTime;

        public ViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.iv_img_messagechat);
            mName = itemView.findViewById(R.id.tv_name_messagechat);
            mSign = itemView.findViewById(R.id.tv_sign_messagechat);
            mTime = itemView.findViewById(R.id.tv_time_messagechat);
        }
    }
}
