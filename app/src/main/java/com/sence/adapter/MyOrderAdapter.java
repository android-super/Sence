package com.sence.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.util.DensityUtil;
import com.sence.R;
import com.sence.activity.OrderCommentActivity;
import com.sence.activity.OrderDetailsActivity;
import com.sence.activity.chat.ui.ChatMsgActivity;
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
            holder.mAlipay.setText("评价");
            holder.mState.setText("写评价");
        } else if ("5".equals(list.get(position).getStatus())) {
            holder.mSevice.setVisibility(View.GONE);
            holder.mAlipay.setVisibility(View.GONE);
            holder.mCancel.setText("再次购买");
            holder.mState.setText("用户关闭");
        } else if ("6".equals(list.get(position).getStatus())) {
            holder.mSevice.setVisibility(View.GONE);
            holder.mAlipay.setVisibility(View.GONE);
            holder.mCancel.setText("再次购买");
            holder.mState.setText("后台关闭");
        } else if ("7".equals(list.get(position).getStatus())) {
            holder.mSevice.setVisibility(View.GONE);
            holder.mAlipay.setVisibility(View.GONE);
            holder.mState.setText("已完成");
            holder.mCancel.setText("删除订单");
        } else if ("8".equals(list.get(position).getStatus())) {
            holder.mSevice.setVisibility(View.GONE);
            holder.mAlipay.setVisibility(View.GONE);
            holder.mState.setText("系统取消");
            holder.mCancel.setText("再次购买");
        }
        MyOrderItemAdapter myOrderItemAdapter = new MyOrderItemAdapter(context);
        LinearLayoutManager linearLayout = new LinearLayoutManager(context);
        linearLayout.setOrientation(RecyclerView.VERTICAL);
        holder.mRecyclerView.setLayoutManager(linearLayout);
        holder.mRecyclerView.setAdapter(myOrderItemAdapter);
        myOrderItemAdapter.setList(list.get(position).getGoods());
        holder.mTime.setText(list.get(position).getAddtime());
        if (list.get(position).getNeedpay().contains(".")) {
            holder.mPrice.setText("￥" + list.get(position).getNeedpay());
        } else {
            holder.mPrice.setText("￥" + list.get(position).getNeedpay() + ".00");
        }
        holder.mNum.setText("共" + list.get(position).getNum() + "件");
        myOrderItemAdapter.result(new MyOrderItemAdapter.OnclickListener() {
            @Override
            public void click() {
                Intent intent = new Intent(context, OrderDetailsActivity.class);
                intent.putExtra("id", list.get(position).getId());
                intent.putExtra("type", list.get(position).getStatus());
                context.startActivity(intent);
            }
        });
        holder.mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("4".equals(list.get(position).getStatus()) || "3".equals(list.get(position).getStatus()) || "2".equals(list.get(position).getStatus())) {
                    Intent intent = new Intent(context, OrderDetailsActivity.class);
                    intent.putExtra("id", list.get(position).getId());
                    intent.putExtra("type", list.get(position).getStatus());
                    context.startActivity(intent);
                } else if ("5".equals(list.get(position).getStatus())) {
                    Intent intent = new Intent(context, OrderDetailsActivity.class);
                    intent.putExtra("id", list.get(position).getId());
                    intent.putExtra("type", list.get(position).getStatus());
                    context.startActivity(intent);
                } else if ("6".equals(list.get(position).getStatus())) {
                    Intent intent = new Intent(context, OrderDetailsActivity.class);
                    intent.putExtra("id", list.get(position).getId());
                    intent.putExtra("type", list.get(position).getStatus());
                    context.startActivity(intent);
                } else if ("7".equals(list.get(position).getStatus())) {
                    DeleteOreder(position);
                } else if ("8".equals(list.get(position).getStatus())) {
                    Intent intent = new Intent(context, OrderDetailsActivity.class);
                    intent.putExtra("id", list.get(position).getId());
                    intent.putExtra("type", list.get(position).getStatus());
                    context.startActivity(intent);
                } else if ("1".equals(list.get(position).getStatus())) {
                    View view = View.inflate(context, R.layout.alter_deleteorder, null);
                    AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.AlertDialogStyle);
                    builder.setView(view);
                    final AlertDialog alertDialog = builder.create();
                    alertDialog.setCancelable(true);
                    alertDialog.show();
                    alertDialog.getWindow().setLayout(new DensityUtil().dip2px(270), LinearLayout.LayoutParams.WRAP_CONTENT);
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
                                    ToastUtils.showShort(msg);
                                    Intent intent = new Intent(context, OrderCommentActivity.class);
                                    intent.putExtra("url", list.get(position).getGoods().get(0).getImg());
                                    intent.putExtra("id", list.get(position).getId());
                                    context.startActivity(intent);
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
                    intent.putExtra("id", list.get(position).getId());
                    context.startActivity(intent);
                    return;
                } else if ("3".equals(list.get(position).getStatus())) {
                    ConfirmTakeGood(position);
                } else if ("1".equals(list.get(position).getStatus())) {
                    listener.pay(position);
                } else{
                    Intent intent = new Intent(context, OrderDetailsActivity.class);
                    intent.putExtra("id", list.get(position).getId());
                    intent.putExtra("type", list.get(position).getStatus());
                    context.startActivity(intent);
                }

            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, OrderDetailsActivity.class);
                intent.putExtra("id", list.get(position).getId());
                intent.putExtra("type", list.get(position).getStatus());
                context.startActivity(intent);
            }
        });
        holder.mSevice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ChatMsgActivity.class);
                intent.putExtra("u_to", list.get(position).getCustom().getId());
                intent.putExtra("chat_id", "");
                intent.putExtra("name", list.get(position).getCustom().getName());
                intent.putExtra("u_avatar", list.get(position).getCustom().getAvatar());
                context.startActivity(intent);
            }
        });
    }

    private void ConfirmTakeGood(final int position) {
        HttpManager.getInstance().PlayNetCode(HttpCode.CONFIRM_TAKE_GOOD, new ROrderDetailsBean(list.get(position).getId(), LoginStatus.getUid())).request(new ApiCallBack<String>() {


            @Override
            public void onFinish() {

            }

            @Override
            public void Message(int code, String message) {
            }

            @Override
            public void onSuccess(final String o, String msg) {
                Logger.e("msg==========" + msg + o);
                ToastUtils.showShort(msg);
                Intent intent = new Intent(context, OrderCommentActivity.class);
                intent.putExtra("url", list.get(position).getGoods().get(0).getImg());
                intent.putExtra("id",  list.get(position).getId());
                context.startActivity(intent);
            }
        });
    }


    private void DeleteOreder(final int position) {
        HttpManager.getInstance().PlayNetCode(HttpCode.DELETE_DONE_ORDER, new ROrderDetailsBean(list.get(position).getId(), LoginStatus.getUid())).request(new ApiCallBack<String>() {

            @Override
            public void onFinish() {

            }

            @Override
            public void Message(int code, String message) {
            }

            @Override
            public void onSuccess(final String o, String msg) {
                Logger.e("msg==========" + msg + o);
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
        private LinearLayout mLinearLayout;
        private RecyclerView mRecyclerView;
        private TextView mState, mSevice, mCancel, mAlipay, mTime, mPrice, mNum;
        private RelativeLayout mRelativeLayout;

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
            mRelativeLayout = itemView.findViewById(R.id.rl_layout_myorder);
        }
    }

    private DeleteOrderListener listener;

    public void result(DeleteOrderListener listener) {
        this.listener = listener;
    }

    public interface DeleteOrderListener {
        void delete(int i);
        void pay(int i);
    }

}
