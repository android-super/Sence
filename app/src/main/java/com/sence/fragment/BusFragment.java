package com.sence.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ConvertUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.sence.LoginActivity;
import com.sence.MainActivity;
import com.sence.R;
import com.sence.activity.ConfirmOrderActivity;
import com.sence.activity.OpenVipPageActivity;
import com.sence.activity.ShopDetailsActivity;
import com.sence.adapter.BusBottomAdapter;
import com.sence.adapter.BusTopAdapter;
import com.sence.base.BaseMainFragment;
import com.sence.bean.request.RUidBean;
import com.sence.bean.request.RUidListBean;
import com.sence.bean.response.PBusBean;
import com.sence.bean.response.PBusRecommendBean;
import com.sence.net.HttpCode;
import com.sence.net.HttpManager;
import com.sence.net.manager.ApiCallBack;
import com.sence.utils.Arith;
import com.sence.utils.LoginStatus;
import com.sence.view.DividerSpacingItemDecoration;
import com.sence.view.GridSpacingItemDecoration;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * A simple {@link Fragment} subclass.
 */
public class BusFragment extends BaseMainFragment {
    private SmartRefreshLayout smart_refresh;
    private RecyclerView recycle_view;
    private RecyclerView recycle_view_bottom;

    private TextView bus_all_select;
    private TextView bus_commit;
    private TextView bus_all_price;

    private RelativeLayout bus_open_layout;
    private TextView bus_save_money;
    private ImageView bus_open;
    private RelativeLayout bus_vip_layout;

    private BusTopAdapter topAdapter;
    private BusBottomAdapter bottomAdapter;

    private int page = 1;

    private String isMember = "0";//是否是会员
    private String save_money = "0";//预计一年省

    public BusFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bus, container, false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        smart_refresh = getView().findViewById(R.id.smart_refresh);
        recycle_view = getView().findViewById(R.id.recycle_view);
        recycle_view_bottom = getView().findViewById(R.id.recycle_view_bottom);

        bus_all_select = getView().findViewById(R.id.bus_all_select);
        bus_commit = getView().findViewById(R.id.bus_commit);
        bus_all_price = getView().findViewById(R.id.bus_all_price);

        smart_refresh.setRefreshHeader(new ClassicsHeader(getActivity()));
        smart_refresh.setRefreshFooter(new ClassicsFooter(getActivity()));
        recycle_view.setLayoutManager(new LinearLayoutManager(getActivity()));
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        recycle_view_bottom.setLayoutManager(gridLayoutManager);
        recycle_view.addItemDecoration(new DividerSpacingItemDecoration(DividerSpacingItemDecoration.VERTICAL,
                ConvertUtils.dp2px(13), true));
        topAdapter = new BusTopAdapter(R.layout.rv_item_bus_top);
        recycle_view.setAdapter(topAdapter);
        bottomAdapter = new BusBottomAdapter(R.layout.rv_item_bus_bottom);
        recycle_view_bottom.setAdapter(bottomAdapter);
        recycle_view_bottom.addItemDecoration(new GridSpacingItemDecoration(2, ConvertUtils.dp2px(10), false));
        View top_head = LayoutInflater.from(getActivity()).inflate(R.layout.rv_item_bus_head, null);
        bus_open_layout = top_head.findViewById(R.id.bus_open_layout);
        bus_save_money = top_head.findViewById(R.id.bus_save_money);
        bus_vip_layout = top_head.findViewById(R.id.bus_vip_layout);
        bus_open = top_head.findViewById(R.id.bus_open);
        topAdapter.addHeaderView(top_head);
        topAdapter.setEmptyView(R.layout.rv_item_bus_empty, recycle_view);
        topAdapter.getEmptyView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.putExtra("type", 2);
                startActivity(intent);
            }
        });
        smart_refresh.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                initRecommendData();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                initRecommendData();
            }
        });
        bottomAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getActivity(), ShopDetailsActivity.class);
                intent.putExtra("id", bottomAdapter.getData().get(position).getId());
                startActivity(intent);
            }
        });
        topAdapter.setDeleteListener(new BusTopAdapter.DeleteListener() {
            @Override
            public void deleteChange(int position) {
                if (topAdapter.getData().get(position - 1).getGoods().size() == 0) {
                    topAdapter.remove(position - 1);
                    getValue();
                }
            }
        });
        topAdapter.setListener(new BusTopAdapter.SelectChangeListener() {
            @Override
            public void selectChanged(int position) {
                getItemAllSelect(position - 1);
                getAllSelect();
                getValue();
            }
        });

        topAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (view.getId() == R.id.item_name_select) {
                    if (topAdapter.getData().get(position).isSelect()) {
                        topAdapter.getData().get(position).setSelect(false);
                        changeItemAllSelect(position, false);
                    } else {
                        topAdapter.getData().get(position).setSelect(true);
                        changeItemAllSelect(position, true);
                    }
                    topAdapter.notifyDataSetChanged();
                    getAllSelect();
                    getValue();
                }
            }
        });
        bus_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ConfirmOrderActivity.class);
                intent.putExtra("data", (Serializable) getAllSelectBean());
                intent.putExtra("type", "shop");
                intent.putExtra("isMember", isMember);
                toLogin(intent);
            }
        });
        bus_all_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bus_all_select.isSelected()) {
                    bus_all_select.setSelected(false);
                    setSelect(false);
                } else {
                    bus_all_select.setSelected(true);
                    setSelect(true);
                }
                getValue();
            }
        });

        bus_open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), OpenVipPageActivity.class);
                intent.putExtra("money", save_money);
                toLogin(intent);
            }
        });

        initBusData();
        initRecommendData();
    }

    public void toLogin(Intent intent) {
        if (!LoginStatus.isLogin() || LoginStatus.getUid().isEmpty()) {
            startActivity(new Intent(getActivity(), LoginActivity.class));
            return;
        } else {
            startActivity(intent);
        }
    }

    /**
     * 更改Item內部选中状态
     *
     * @param position
     * @param isSelect
     */
    public void changeItemAllSelect(int position, boolean isSelect) {
        List<PBusBean.CartBean.GoodsBean> goodsBeans = topAdapter.getData().get(position).getGoods();
        for (int j = 0; j < goodsBeans.size(); j++) {
            goodsBeans.get(j).setSelect(isSelect);
        }
    }

    public void getItemAllSelect(int position) {
        List<PBusBean.CartBean.GoodsBean> goodsBeans = topAdapter.getData().get(position).getGoods();
        for (int j = 0; j < goodsBeans.size(); j++) {
            if (!goodsBeans.get(j).isSelect()) {
                topAdapter.getData().get(position).setSelect(false);
                topAdapter.notifyDataSetChanged();
                return;
            }
        }
        topAdapter.getData().get(position).setSelect(true);
        topAdapter.notifyDataSetChanged();
    }

    /**
     * 获取得到的Bean
     *
     * @return
     */
    public void getValue() {
        int all_count = 0;
        double all_count_money = 0;
        for (int i = 0; i < topAdapter.getData().size(); i++) {
            List<PBusBean.CartBean.GoodsBean> goodsBeans = topAdapter.getData().get(i).getGoods();
            int all_num = 0;
            double all_price = 0;
            for (int j = 0; j < goodsBeans.size(); j++) {
                if (goodsBeans.get(j).isSelect()) {
                    double one_price;
                    int num;
                    if (isMember.equals("1")) {
                        one_price = Double.parseDouble(goodsBeans.get(j).getVprice());
                    } else {
                        one_price = Double.parseDouble(goodsBeans.get(j).getPrice());
                    }
                    num = goodsBeans.get(j).getNum();
                    Arith.mul(one_price, num);
                    all_price = Arith.add(Arith.mul(one_price, num), all_price);
                    all_num = all_num + num;
                }
            }
            all_count = all_count + all_num;
            all_count_money = Arith.add(all_count_money, all_price);
            if (topAdapter.getData().get(i).isSelect()) {
                all_count_money = Arith.sub(all_count_money,
                        Double.parseDouble(topAdapter.getData().get(i).getFavourable()));
            }
        }

        bus_all_price.setText("￥" + all_count_money);
        bus_commit.setText("结算(" + all_count + ")");
    }

    public List<PBusBean.CartBean> getAllSelectBean() {
        List<PBusBean.CartBean> cartBeans = new ArrayList<>();
        for (int i = 0; i < topAdapter.getData().size(); i++) {
            double all_price = 0;
            double all_postage = 0;
            double all_money;
            int all_num = 0;
            PBusBean.CartBean cartBean = topAdapter.getItem(i);
            PBusBean.CartBean newCardBean = new PBusBean.CartBean();
            List<PBusBean.CartBean.GoodsBean> goodsBeans = topAdapter.getData().get(i).getGoods();
            List<PBusBean.CartBean.GoodsBean> newGoodsBeans = new ArrayList<>();
            for (int j = 0; j < goodsBeans.size(); j++) {
                if (goodsBeans.get(j).isSelect()) {
                    double one_postage;
                    double one_price;
                    int num;
                    if (isMember.equals("1")) {
                        one_price = Double.parseDouble(goodsBeans.get(j).getVprice());
                    } else {
                        one_price = Double.parseDouble(goodsBeans.get(j).getPrice());
                    }
                    num = goodsBeans.get(j).getNum();
                    one_postage = Double.parseDouble(goodsBeans.get(j).getPostage());
                    Arith.mul(one_price, num);
                    all_price = Arith.add(Arith.mul(one_price, num), all_price);
                    newGoodsBeans.add(goodsBeans.get(j));
                    all_postage = Arith.add(all_postage, one_postage);
                    all_num = all_num + num;
                }
            }
            if (topAdapter.getData().get(i).isSelect()) {
                all_price = Arith.sub(all_price, Double.parseDouble(cartBean.getFavourable()));
            }
            all_money = Arith.add(all_price, all_postage);
            newCardBean.setSelect(cartBean.isSelect());
            newCardBean.setActive(cartBean.getActive());
            newCardBean.setExpress(cartBean.getExpress());
            newCardBean.setFavourable(cartBean.getFavourable());
            newCardBean.setPostage(cartBean.getPostage());
            newCardBean.setShopname(cartBean.getShopname());
            newCardBean.setAll_price(all_price + "");
            newCardBean.setAll_postage(all_postage + "");
            newCardBean.setAll_num(all_num + "");
            newCardBean.setAll_money(all_money + "");
            newCardBean.setGoods(newGoodsBeans);
            cartBeans.add(newCardBean);
        }
        return cartBeans;
    }


    /**
     * 是否全部选中
     */
    public void getAllSelect() {
        for (int i = 0; i < topAdapter.getData().size(); i++) {
            List<PBusBean.CartBean.GoodsBean> goodsBeans = topAdapter.getData().get(i).getGoods();
            for (int j = 0; j < goodsBeans.size(); j++) {
                if (!goodsBeans.get(j).isSelect()) {
                    bus_all_select.setSelected(false);
                    return;
                }
            }
        }
        bus_all_select.setSelected(true);
    }

    /**
     * 全选
     *
     * @param isSelect
     */
    public void setSelect(boolean isSelect) {
        for (int i = 0; i < topAdapter.getData().size(); i++) {
            topAdapter.getData().get(i).setSelect(isSelect);
            List<PBusBean.CartBean.GoodsBean> goodsBeans = topAdapter.getData().get(i).getGoods();
            for (int j = 0; j < goodsBeans.size(); j++) {
                goodsBeans.get(j).setSelect(isSelect);
            }
        }
        topAdapter.notifyDataSetChanged();
    }

    /**
     * 记载推荐数据
     */
    private void initRecommendData() {
        HttpManager.getInstance().PlayNetCode(HttpCode.BUS_RECOMMEND, new RUidListBean(LoginStatus.getUid(), page +
                "")).request(new ApiCallBack<List<PBusRecommendBean>>() {
            @Override
            public void onFinish() {
                smart_refresh.finishLoadMore();
            }

            @Override
            public void Message(int code, String message) {

            }

            @Override
            public void onSuccess(List<PBusRecommendBean> o, String msg) {
                if (page == 1) {
                    bottomAdapter.setNewData(o);
                } else {
                    bottomAdapter.addData(o);
                }
            }
        });
    }

    /**
     * 加载购物车数据
     */
    private void initBusData() {
        HttpManager.getInstance().PlayNetCode(HttpCode.BUS_GOODS, new RUidBean(LoginStatus.getUid())).request(new ApiCallBack<PBusBean>() {
            @Override
            public void onFinish() {
                smart_refresh.finishRefresh();
            }

            @Override
            public void Message(int code, String message) {

            }

            @Override
            public void onSuccess(PBusBean o, String msg) {
                isMember = o.getIsMember();
                save_money = o.getMoney();
                if (isMember.equals("1")) {
                    bus_vip_layout.setVisibility(View.VISIBLE);
                    bus_open_layout.setVisibility(View.GONE);
                } else {
                    bus_open_layout.setVisibility(View.VISIBLE);
                    bus_vip_layout.setVisibility(View.GONE);
                }
                topAdapter.setIsMember(isMember);
                bus_save_money.setText("一年预计省￥" + save_money);
                topAdapter.setNewData(o.getCart());
                if (o.getCart().size() > 0) {
                    setSelect(true);
                    bus_all_select.setSelected(true);
                    getValue();
                } else {
                    bus_all_select.setSelected(false);
                }

            }
        });
    }

    @Override
    public void onRefresh() {
        initBusData();
        initRecommendData();
    }
}
