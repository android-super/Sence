package com.sence.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.scwang.smartrefresh.layout.util.DensityUtil;
import com.sence.R;
import com.sence.activity.SettingActivity;
import com.sence.bean.request.RBusAddBean;
import com.sence.bean.response.PBusBean;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.sence.net.HttpCode;
import com.sence.net.HttpManager;
import com.sence.net.manager.ApiCallBack;
import com.sence.utils.DataCleanManager;
import com.sence.utils.LoginStatus;

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


    public void setDeleteListener(DeleteListener deleteListener) {
        this.deleteListener = deleteListener;
    }

    private DeleteListener deleteListener;

    public interface DeleteListener {
        void deleteChange(int position);
    }


    public BusTopAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(final BaseViewHolder helper, final PBusBean.CartBean item) {
        helper.addOnClickListener(R.id.item_name_select);
        helper.addOnClickListener(R.id.item_look);
        helper.setText(R.id.item_name_select, item.getShopname());
        if ("0".equals(item.getActive())) {
            helper.setGone(R.id.item_discount_layout, false);
        } else {
            helper.setGone(R.id.item_discount_layout, true);
            helper.setText(R.id.item_discount, "活动优惠-￥:" + item.getFavourable());
        }
        if ("0".equals(item.getPostage())||"0.00".equals(item.getPostage())||"0.0".equals(item.getPostage())) {
            helper.setVisible(R.id.item_kind_cost,false);
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
                        deleteDialog(helper.itemView.getContext(), goodAdapter, position, helper.getAdapterPosition());
                    } else {
                        num = num - 1;
                        changeBus(goodAdapter.getData().get(position).getId(), "1", "2");
                        goodAdapter.getData().get(position).setNum(num);
                        goodAdapter.notifyDataSetChanged();
                        listener.selectChanged(helper.getAdapterPosition());
                    }
                } else if (view.getId() == R.id.item_add) {
                    int num = goodAdapter.getData().get(position).getNum();
                    num = num + 1;
                    changeBus(goodAdapter.getData().get(position).getId(), "1", "1");
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

    /**
     * 删除购物车
     *
     * @param context
     * @param goodAdapter
     * @param position
     */
    public void deleteDialog(Context context, final BusGoodAdapter goodAdapter, final int position,
                             final int adapterPosition) {
        View viewClear = View.inflate(context, R.layout.alter_delete_bus, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.AlertDialogStyle);
        builder.setView(viewClear);
        final AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(true);
        alertDialog.show();
        alertDialog.getWindow().setLayout(DensityUtil.dp2px(270), LinearLayout.LayoutParams.WRAP_CONTENT);
        TextView mContent = viewClear.findViewById(R.id.tv_content_deleteorder);
        mContent.setText("亲亲，确定要删除此商品吗？");
        TextView mCancel = viewClear.findViewById(R.id.tv_cancel_deleteorder);
        TextView mConfirm = viewClear.findViewById(R.id.tv_confirm_deleteorder);
        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        mConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                changeBus(goodAdapter.getData().get(position).getId(), "0", "2");
                goodAdapter.remove(position);
                deleteListener.deleteChange(adapterPosition);
            }
        });
    }

    /**
     * 加入购物车
     */
    private void changeBus(String gid, String num, String type) {
        HttpManager.getInstance().PlayNetCode(HttpCode.BUS_ADD,
                new RBusAddBean(gid, LoginStatus.getUid(), num, type)).request(new ApiCallBack() {
            @Override
            public void onFinish() {

            }

            @Override
            public void Message(int code, String message) {

            }

            @Override
            public void onSuccess(Object o, String msg) {

            }
        });
    }
}
