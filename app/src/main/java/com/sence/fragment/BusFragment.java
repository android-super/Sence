package com.sence.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.sence.R;
import com.sence.adapter.BusBottomAdapter;
import com.sence.adapter.BusTopAdapter;
import com.sence.adapter.KindRightAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class BusFragment extends Fragment {
    private SmartRefreshLayout smart_refresh;
    private RecyclerView recycle_view;
    private RecyclerView recycle_view_bottom;

    private BusTopAdapter topAdapter;
    private BusBottomAdapter bottomAdapter;

    public BusFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bus, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        smart_refresh = getView().findViewById(R.id.smart_refresh);
        recycle_view = getView().findViewById(R.id.recycle_view);
        recycle_view_bottom = getView().findViewById(R.id.recycle_view_bottom);
        smart_refresh.setRefreshHeader(new ClassicsHeader(getActivity()));
        smart_refresh.setRefreshFooter(new ClassicsFooter(getActivity()));
        recycle_view.setLayoutManager(new LinearLayoutManager(getActivity()));
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        recycle_view_bottom.setLayoutManager(gridLayoutManager);
        topAdapter = new BusTopAdapter(R.layout.rv_item_bus_top);
        recycle_view.setAdapter(topAdapter);
        bottomAdapter = new BusBottomAdapter(R.layout.rv_item_bus_bottom);
        recycle_view_bottom.setAdapter(bottomAdapter);
        View top_head = LayoutInflater.from(getActivity()).inflate(R.layout.rv_item_bus_head, null);
        topAdapter.addHeaderView(top_head);
        topAdapter.setEmptyView(R.layout.rv_item_bus_empty, recycle_view);
        topAdapter.addData("");
        topAdapter.addData("");
        bottomAdapter.addData("");
        bottomAdapter.addData("");
        bottomAdapter.addData("");
    }
}
