package com.sence.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.orhanobut.logger.Logger;
import com.sence.R;
import com.sence.activity.MyInfoActivity;
import com.sence.bean.request.RCancelFocusBean;
import com.sence.bean.response.PSearchBean;
import com.sence.net.HttpCode;
import com.sence.net.HttpManager;
import com.sence.net.manager.ApiCallBack;
import com.sence.utils.GlideUtils;
import com.sence.utils.LoginStatus;
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
    public void onBindViewHolder(@NonNull final SearchFriendAdapter.ViewHolder holder, final int position) {
        holder.mName.setText(list.get(position).getUsername());
        if(!TextUtils.isEmpty(list.get(position).getAutograph())){
            holder.mContent.setText(list.get(position).getAutograph());
        }
        if(list.get(position).getIsVip().equals("1")){
            holder.mIsv.setVisibility(View.VISIBLE);
        }
        if(list.get(position).getIsFollow().equals("1")){
            holder.mFocus.setText("已关注");
            holder.mFocus.setTextColor(Color.parseColor("#999999"));
            holder.mFocus.setBackgroundResource(R.drawable.shape_searchconcern_yetbg);
        }else{
            holder.mFocus.setText("+  关注");
            holder.mFocus.setTextColor(Color.parseColor("#16A5AF"));
            holder.mFocus.setBackgroundResource(R.drawable.shape_searchconcern_bg);
        }
        holder.mFocus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(LoginStatus.getUid().equals(list.get(position).getId())){
                    ToastUtils.showShort("您不可以关注自己");
                    return;
                }
                if(list.get(position).getIsFollow().equals("1")){
                    cancelFocus(position,holder);
                }else{
                    focus(position,holder);
                }
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MyInfoActivity.class);
                intent.putExtra("uid",list.get(position).getId());
                context.startActivity(intent);
            }
        });
        GlideUtils.getInstance().loadHead( list.get(position).getAvatar(),holder.mImg);
    }

    private void focus(final int position, final ViewHolder holder) {
        HttpManager.getInstance().PlayNetCode(HttpCode.USER_FOCUS, new RCancelFocusBean(LoginStatus.getUid(),list.get(position).getId())).request(new ApiCallBack<String>() {
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
                list.get(position).setIsFollow("1");
                holder.mFocus.setText("已关注");
                holder.mFocus.setTextColor(Color.parseColor("#999999"));
                holder.mFocus.setBackgroundResource(R.drawable.shape_searchconcern_yetbg);
            }
        });
    }

    private void cancelFocus(final int position, final ViewHolder holder) {
        HttpManager.getInstance().PlayNetCode(HttpCode.USER_FOCUS_CANCEL, new RCancelFocusBean(LoginStatus.getUid(),list.get(position).getId())).request(new ApiCallBack<String>() {
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
                list.get(position).setIsFollow("0");
                holder.mFocus.setText("+  关注");
                holder.mFocus.setTextColor(Color.parseColor("#16A5AF"));
                holder.mFocus.setBackgroundResource(R.drawable.shape_searchconcern_bg);
            }
        });
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
