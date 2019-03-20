package com.sence.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.sence.R;
import com.sence.adapter.MyOrderAdapter;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MyOrderFragment extends Fragment {

    private RecyclerView recyclerView;
    private TextView more;
    private SmartRefreshLayout smartRefreshLayout;
    private MyOrderAdapter myOrderAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_myorder, container, false);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initRefresh();
    }

    private void initRefresh() {
        recyclerView = getView().findViewById(R.id.recycle_myorder);
        smartRefreshLayout = getView().findViewById(R.id.srl_more_myorder);
        more = getView().findViewById(R.id.tv_more_myorder);
        myOrderAdapter = new MyOrderAdapter(getContext());
        LinearLayoutManager linearLayout = new LinearLayoutManager(getContext());
        linearLayout.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayout);
        recyclerView.setAdapter(myOrderAdapter);
    }

}
