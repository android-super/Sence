package com.sence.adapter;

import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sence.R;
import com.sence.bean.response.PBusBean;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by zwy on 2019/3/13.
 * package_name is com.sence.adapter
 * 描述:Sence
 */
public class BusTopAdapter extends BaseQuickAdapter<PBusBean.CartBean, BaseViewHolder> {
    public void setIsMember(String isMember) {
        this.isMember = isMember;
    }

    private String isMember;

    public SelectChangeListener getListener() {
        return listener;
    }

    public void setListener(SelectChangeListener listener) {
        this.listener = listener;
    }

    private SelectChangeListener listener;

    public interface SelectChangeListener {
        void selectChanged(int position);
    }

    public BusTopAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(final BaseViewHolder helper, final PBusBean.CartBean item) {
        helper.addOnClickListener(R.id.item_name_select);
        helper.setText(R.id.item_name_select, item.getShopname());
        if ("0".equals(item.getActive())) {
            helper.setGone(R.id.item_discount_layout, false);
        } else {
            helper.setGone(R.id.item_discount_layout, true);
            helper.setText(R.id.item_discount, "活动优惠-￥:" + item.getFavourable());
        }
        if ("1".equals(item.getExpress())) {
            helper.setText(R.id.item_kind_cost, "包邮");
        } else if ("2".equals(item.getExpress())) {
            helper.setText(R.id.item_kind_cost, "已免邮费");
        } else {
            helper.setText(R.id.item_kind_cost, "还差些钱才能免邮");
        }
        final TextView item_name_select = helper.getView(R.id.item_name_select);
        if (item.isSelect()) {
            item_name_select.setSelected(true);
        } else {
            item_name_select.setSelected(false);
        }
        if ("1".equals(item.getActive())) {
            helper.setGone(R.id.item_discount_layout, true);
        } else {
            helper.setGone(R.id.item_discount_layout, false);
        }
        RecyclerView recyclerView = helper.getView(R.id.item_recycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(helper.itemView.getContext()));
        final BusGoodAdapter goodAdapter = new BusGoodAdapter(R.layout.rv_item_bus_good, isMember);
        recyclerView.setAdapter(goodAdapter);
        goodAdapter.setNewData(item.getGoods());
        goodAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (view.getId() == R.id.item_cut) {
                    int num = goodAdapter.getData().get(position).getNum();
                    if (num - 1 <= 0) {
                        num = 1;
                    } else {
                        num = num - 1;
                    }
                    goodAdapter.getData().get(position).setNum(num);
                    goodAdapter.notifyDataSetChanged();
                    listener.selectChanged(helper.getAdapterPosition());
                } else if (view.getId() == R.id.item_add) {
                    int num = goodAdapter.getData().get(position).getNum();
                    num = num + 1;
                    goodAdapter.getData().get(position).setNum(num);
                    goodAdapter.notifyDataSetChanged();
                    listener.selectChanged(helper.getAdapterPosition());
                } else if (view.getId() == R.id.item_select) {
                    if (goodAdapter.getData().get(position).isSelect()) {
                        goodAdapter.getData().get(position).setSelect(false);
                    } else {
                        goodAdapter.getData().get(position).setSelect(true);
                    }
                    listener.selectChanged(helper.getAdapterPosition());
                    goodAdapter.notifyDataSetChanged();
                }
            }
        });
    }
}
