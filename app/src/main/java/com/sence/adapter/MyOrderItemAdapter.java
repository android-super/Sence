package com.sence.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sence.R;
import com.sence.bean.response.PMyOrderBean;
import com.sence.utils.GlideUtils;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyOrderItemAdapter extends RecyclerView.Adapter<MyOrderItemAdapter.ViewHolder> {
    private Context context;
    private List<PMyOrderBean.ListBean.GoodsBean> list = new ArrayList<>();

    public MyOrderItemAdapter(Context context) {
        this.context = context;
    }

    public void setList(List<PMyOrderBean.ListBean.GoodsBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyOrderItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_item_myorderitem, parent, false);
        return new MyOrderItemAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyOrderItemAdapter.ViewHolder holder, final int position) {
        GlideUtils.getInstance().loadHead( list.get(position).getImg(),holder.mImageView);
        holder.mName.setText(list.get(position).getName());

        if (list.get(position).getPrice().contains(".")) {
            holder.mPprice.setText("￥" + list.get(position).getPrice());
        } else {
            holder.mPprice.setText("￥" + list.get(position).getPrice() + ".00");
        }
        holder.mPnum.setText("×" + list.get(position).getNum());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        private ImageView mImageView;
        private TextView mName, mPnum, mPprice;

        public ViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.iv_img_myorder);
            mName = itemView.findViewById(R.id.tv_name_myorder);
            mPprice = itemView.findViewById(R.id.tv_pprice_myorder);
            mPnum = itemView.findViewById(R.id.tv_pnum_myorder);
        }
    }

    private MyOrderAdapter.DeleteOrderListener listener;

    public void result(MyOrderAdapter.DeleteOrderListener listener) {
        this.listener = listener;
    }

    public interface DeleteOrderListener {
        void delete(int i);
    }
}

