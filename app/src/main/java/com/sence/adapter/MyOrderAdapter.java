package com.sence.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.sence.R;
import com.sence.activity.OrderCommentActivity;
import com.sence.activity.OrderDetailsActivity;
import com.sence.bean.request.ROrderDetailsBean;
import com.sence.bean.response.PMyOrderBean;
import com.sence.net.HttpCode;
import com.sence.net.HttpManager;
import com.sence.net.manager.ApiCallBack;
import com.sence.utils.LoginStatus;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
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
        if ("1".equals(list.get(position).getStatus())) {
            holder.mAlipay.setText("立即支付");
            holder.mState.setText("待支付");
        } else if ("2".equals(list.get(position).getStatus())) {
            holder.mCancel.setText("查看订单");
            holder.mState.setText("待发货");
            holder.mAlipay.setVisibility(View.GONE);
        } else if ("3".equals(list.get(position).getStatus())) {
            holder.mCancel.setText("查看订单");
            holder.mState.setText("待收货");
            holder.mAlipay.setText("确认收货");
        } else if ("4".equals(list.get(position).getStatus())) {
            holder.mSevice.setVisibility(View.GONE);
            holder.mCancel.setText("查看订单");
            holder.mAlipay.setText("写评价");
            holder.mState.setText("待支付");
        }else if ("5".equals(list.get(position).getStatus())) {
            holder.mLinearLayout.setVisibility(View.GONE);
            holder.mState.setText("用户关闭");
        }else if ("6".equals(list.get(position).getStatus())) {
            holder.mLinearLayout.setVisibility(View.GONE);
            holder.mState.setText("后台关闭");
        }else if ("7".equals(list.get(position).getStatus())) {
            holder.mSevice.setVisibility(View.GONE);
            holder.mAlipay.setVisibility(View.GONE);
            holder.mState.setText("已完成");
            holder.mCancel.setText("再次购买");
        }else if ("8".equals(list.get(position).getStatus())) {
            holder.mSevice.setVisibility(View.GONE);
            holder.mAlipay.setVisibility(View.GONE);
            holder.mState.setText("系统取消");
            holder.mCancel.setText("删除订单");
        }
        MyOrderItemAdapter myOrderItemAdapter = new MyOrderItemAdapter(context);
        LinearLayoutManager linearLayout = new LinearLayoutManager(context);
        linearLayout.setOrientation(RecyclerView.VERTICAL);
        holder.mRecyclerView.setLayoutManager(linearLayout);
        holder.mRecyclerView.setAdapter(myOrderItemAdapter);
        myOrderItemAdapter.setList(list.get(position).getGoods());
        holder.mTime.setText(list.get(position).getAddtime());
        holder.mPrice.setText("￥"+list.get(position).getNeedpay());
        holder.mNum.setText("共"+list.get(position).getNum()+"件");

        holder.mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("4".equals(list.get(position).getStatus())||"3".equals(list.get(position).getStatus())||"2".equals(list.get(position).getStatus())) {
                    Intent intent = new Intent(context, OrderDetailsActivity.class);
                    intent.putExtra("id", list.get(position).getId());
                    intent.putExtra("type", list.get(position).getStatusMsg());
                    context.startActivity(intent);
                } else if("5".equals(list.get(position).getStatus())){

                }else if("6".equals(list.get(position).getStatus())){

                }else if("7".equals(list.get(position).getStatus())){

                }else{
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
                if ("4".equals(list.get(position).getStatus())) {
                    Intent intent = new Intent(context, OrderCommentActivity.class);
                    intent.putExtra("url", list.get(position).getGoods().get(0).getImg());
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
        private LinearLayout mLinearLayout;
        private RecyclerView mRecyclerView;
        private TextView mState, mSevice, mCancel, mAlipay, mTime,mPrice,mNum;

        public ViewHolder(View itemView) {
            super(itemView);
            mState = itemView.findViewById(R.id.tv_state_myorder);
            mTime = itemView.findViewById(R.id.tv_time_myorder);
            mRecyclerView = itemView.findViewById(R.id.recycle_myorderitem);
            mSevice = itemView.findViewById(R.id.tv_service_myorder);
            mCancel = itemView.findViewById(R.id.tv_cancel_myorder);
            mPrice = itemView.findViewById(R.id.tv_price_myorder);
            mNum = itemView.findViewById(R.id.tv_num_myorder);
            mAlipay = itemView.findViewById(R.id.tv_alipay_myorder);
            mLinearLayout = itemView.findViewById(R.id.ll_fooler_myorder);
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
