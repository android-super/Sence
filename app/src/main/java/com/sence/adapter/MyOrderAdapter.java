package com.sence.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.orhanobut.logger.Logger;
import com.sence.R;
import com.sence.activity.OrderCommentActivity;
import com.sence.activity.OrderDetailsActivity;
import com.sence.bean.request.ROrderDetailsBean;
import com.sence.bean.response.PMyOrderBean;
import com.sence.net.HttpCode;
import com.sence.net.HttpManager;
import com.sence.net.Urls;
import com.sence.net.manager.ApiCallBack;
import com.sence.utils.LoginStatus;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyOrderAdapter extends RecyclerView.Adapter<MyOrderAdapter.ViewHolder> {
    private static final int FOOTER = 2;
    private Context context;
    private List<PMyOrderBean.ListBean> list = new ArrayList<>();

    public MyOrderAdapter(Context context) {
        this.context = context;
    }

    public void setList(List<PMyOrderBean.ListBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyOrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_item_myorder, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyOrderAdapter.ViewHolder holder, final int position) {
        RequestOptions options = new RequestOptions();
        options.placeholder(R.drawable.hint_img);
        Glide.with(context).load(Urls.base_url + list.get(position).getGoods().getImg()).into(holder.mImageView);
        holder.mName.setText(list.get(position).getGoods().getName());
        holder.mTime.setText(list.get(position).getAddtime());
        if (list.get(position).getGoods().getPrice().contains(".")) {
            holder.mPprice.setText("￥" + list.get(position).getGoods().getPrice());
            holder.mPrice.setText("￥" + Integer.parseInt(list.get(position).getGoods().getPrice()) * Integer.parseInt(list.get(position).getGoods().getNum()));
        } else {
            holder.mPprice.setText("￥" + list.get(position).getGoods().getPrice() + ".00");
            holder.mPrice.setText("￥" + Integer.parseInt(list.get(position).getGoods().getPrice()) * Integer.parseInt(list.get(position).getGoods().getNum()) + ".00");
        }
        if (list.get(position).getStatusMsg().equals("等待支付")) {
            holder.mAlipay.setText("立即支付");
        } else if (list.get(position).getStatusMsg().equals("等待发货")) {
            holder.mAlipay.setText("提醒发货");
        } else if (list.get(position).getStatusMsg().equals("等待收货")) {
            holder.mAlipay.setText("确认收货");
        } else if (list.get(position).getStatusMsg().equals("等待评价")) {
            holder.mSevice.setVisibility(View.GONE);
            holder.mCancel.setText("查看订单");
            holder.mAlipay.setText("评价");
        }

        holder.mPnum.setText("×" + list.get(position).getGoods().getNum());
        holder.mNum.setText("共" + list.get(position).getGoods().getNum() + "件");
        holder.mState.setText(list.get(position).getStatusMsg());
        holder.mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (list.get(position).getStatusMsg().equals("等待评价")) {
                    Intent intent = new Intent(context, OrderDetailsActivity.class);
                    intent.putExtra("id", list.get(position).getId());
                    context.startActivity(intent);
                } else {
                    View view = View.inflate(context, R.layout.alter_deleteorder, null);
                    final AlertDialog alertDialog = new AlertDialog.Builder(context).create();
                    alertDialog.setView(view);
                    alertDialog.show();
                    view.findViewById(R.id.tv_cancel_deleteorder).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            alertDialog.dismiss();
                        }
                    });
                    view.findViewById(R.id.tv_confirm_deleteorder).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            alertDialog.dismiss();
                            HttpManager.getInstance().PlayNetCode(HttpCode.ORDER_DELETE, new ROrderDetailsBean(list.get(position).getId(), LoginStatus.getUid())).request(new ApiCallBack<String>() {
                                @Override
                                public void onFinish() {

                                }

                                @Override
                                public void Message(int code, String message) {

                                }

                                @Override
                                public void onSuccess(String o, String msg) {
                                    Logger.e("msg==========" + msg);
                                    listener.delete(position);
                                }
                            });
                        }
                    });

                }
            }
        });

        holder.mAlipay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (list.get(position).getStatusMsg().equals("等待评价")) {
                    Intent intent = new Intent(context, OrderCommentActivity.class);
                    intent.putExtra("url", list.get(position).getGoods().getImg());
                    context.startActivity(intent);
                    return;
                }else{
                    Intent intent = new Intent(context, OrderDetailsActivity.class);
                    intent.putExtra("id", list.get(position).getId());
                    intent.putExtra("type", list.get(position).getStatusMsg());
                    context.startActivity(intent);
                }

            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, OrderDetailsActivity.class);
                intent.putExtra("id", list.get(position).getId());
                intent.putExtra("type", list.get(position).getStatusMsg());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        private ImageView mImageView;
        private TextView mName, mPrice, mPnum, mState, mNum, mSevice, mCancel, mAlipay, mTime, mPprice;

        public ViewHolder(View itemView) {
            super(itemView);
            mState = itemView.findViewById(R.id.tv_state_myorder);
            mTime = itemView.findViewById(R.id.tv_time_myorder);
            mImageView = itemView.findViewById(R.id.iv_img_myorder);
            mName = itemView.findViewById(R.id.tv_name_myorder);
            mPprice = itemView.findViewById(R.id.tv_pprice_myorder);
            mPnum = itemView.findViewById(R.id.tv_pnum_myorder);
            mNum = itemView.findViewById(R.id.tv_num_myorder);
            mPrice = itemView.findViewById(R.id.tv_price_myorder);
            mSevice = itemView.findViewById(R.id.tv_service_myorder);
            mCancel = itemView.findViewById(R.id.tv_cancel_myorder);
            mAlipay = itemView.findViewById(R.id.tv_alipay_myorder);
        }
    }

    private DeleteOrderListener listener;

    public void result(DeleteOrderListener listener) {
        this.listener = listener;
    }

    public interface DeleteOrderListener {
        void delete(int i);
    }
}
