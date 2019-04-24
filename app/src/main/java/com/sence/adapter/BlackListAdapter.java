package com.sence.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.orhanobut.logger.Logger;
import com.sence.R;
import com.sence.activity.MyInfoActivity;
import com.sence.bean.request.RRachelBean;
import com.sence.bean.response.PBlackListBean;
import com.sence.net.HttpCode;
import com.sence.net.HttpManager;
import com.sence.net.manager.ApiCallBack;
import com.sence.utils.GlideUtils;
import com.sence.utils.LoginStatus;
import com.sence.view.NiceImageView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BlackListAdapter extends RecyclerView.Adapter<BlackListAdapter.ViewHolder> {
    private Context context;
    private List<PBlackListBean> list = new ArrayList<>();

    public BlackListAdapter(Context context){
        this.context = context;
    }
    public void setList(List<PBlackListBean> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BlackListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_item_blacklist,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull BlackListAdapter.ViewHolder holder, final int position) {
        GlideUtils.getInstance().loadHead( list.get(position).getAvatar(),holder.mImageView);
        holder.mName.setText(list.get(position).getNick_name());
        holder.mRelieve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relieve(position);
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MyInfoActivity.class);
                intent.putExtra("uid", LoginStatus.getUid());
                context.startActivity(intent);
            }
        });
    }

    private void relieve(final int position) {
        HttpManager.getInstance().PlayNetCode(HttpCode.RACHEL, new RRachelBean(LoginStatus.getUid(), list.get(position).getUid(),"2")).request(new ApiCallBack<String>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void Message(int code, String message) {

            }

            @Override
            public void onSuccess(String o, String msg) {
                Logger.e("msg==========" + msg);
                ToastUtils.showShort(msg);
                listener.delete(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {


        private NiceImageView mImageView;
        private TextView mName;
        private TextView mRelieve;

        public ViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.iv_img_blacklist);
            mName = itemView.findViewById(R.id.tv_name_blacklist);
            mRelieve = itemView.findViewById(R.id.tv_relieve_blacklist);
        }
    }
    private DeleteBlackListener listener;

    public void result(DeleteBlackListener listener) {
        this.listener = listener;
    }

    public interface DeleteBlackListener {
        void delete(int i);
    }
}
