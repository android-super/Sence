package com.sence.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.orhanobut.logger.Logger;
import com.sence.R;
import com.sence.bean.request.RShopDetailsBean;
import com.sence.bean.response.PShopCommendBean;
import com.sence.net.HttpCode;
import com.sence.net.HttpManager;
import com.sence.net.manager.ApiCallBack;
import com.sence.utils.GlideUtils;
import com.sence.utils.LoginStatus;
import com.sence.view.NiceImageView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ShopCommendAdapter extends RecyclerView.Adapter<ShopCommendAdapter.ViewHolder> {
    private Context context;
    private List<PShopCommendBean> list = new ArrayList<>();
    private int num;

    public ShopCommendAdapter(Context context){
        this.context = context;
    }
    public void setList(List<PShopCommendBean> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ShopCommendAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_item_shopcommend,parent,false);
        return new ShopCommendAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final ShopCommendAdapter.ViewHolder holder, final int position) {
        ShopCommendImgAdapter shopCommendImgAdapter = new ShopCommendImgAdapter(context);
        GridLayoutManager linearLayoutManager = new GridLayoutManager(context,2);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        holder.mCommendImg.setLayoutManager(linearLayoutManager);
        holder.mCommendImg.setAdapter(shopCommendImgAdapter);
        shopCommendImgAdapter.setList(list.get(position).getImgs());
        holder.mContent.setText(list.get(position).getContent());
        holder.mName.setText(list.get(position).getNickname());
        holder.mLikeNum.setText(list.get(position).getPraise());
        GlideUtils.getInstance().loadHead( list.get(position).getAvatar(),holder.mImageView);

        if("1".equals(list.get(position).getIsPraise())){
            holder.mLike.setImageResource(R.drawable.shopcommend_dianzan_y);
        }else{
            holder.mLike.setImageResource(R.drawable.myinfo_dianzan);
        }
        num = Integer.parseInt(list.get(position).getPraise());
        holder.mLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(list.get(position).getIsPraise().equals("1")){
                    Like(position);
                    list.get(position).setIsPraise("0");
                    holder.mLike.setImageResource(R.drawable.myinfo_dianzan);
                    num--;
                    holder.mLikeNum.setText(num+"");
                }else if(list.get(position).getIsPraise().equals("0")){
                    Like(position);
                    num++;
                    list.get(position).setIsPraise("1");
                    holder.mLikeNum.setText(num+"");
                    holder.mLike.setImageResource(R.drawable.shopcommend_dianzan_y);
                }
            }
        });


    }



    private void Like(int position) {
        HttpManager.getInstance().PlayNetCode(HttpCode.ORDER_COMMENT_SUPPORT, new RShopDetailsBean(list.get(position).getId(), LoginStatus.getUid())).request(new ApiCallBack<String>() {
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
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {


        private NiceImageView mImageView;
        private ImageView mLike;
        private RecyclerView mCommendImg;
        private TextView mName,mContent,mLikeNum;
        private LinearLayout mLinearLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.iv_img_shopcommend);
            mCommendImg = itemView.findViewById(R.id.recycle_commendimg_shopcommend);
            mName = itemView.findViewById(R.id.tv_name_shopcommend);
            mLike = itemView.findViewById(R.id.iv_like_shopcommend);
            mLinearLayout = itemView.findViewById(R.id.ll_like_shopcommend);
            mLikeNum = itemView.findViewById(R.id.tv_likenum_shopcommend);
            mContent = itemView.findViewById(R.id.tv_content_shopcommend);
        }
    }
}
