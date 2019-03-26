package com.sence.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.blankj.utilcode.util.ConvertUtils;
import com.sence.R;
import com.sence.adapter.VipBottomAdapter;
import com.sence.adapter.VipTopAdapter;
import com.sence.view.DividerSpacingItemDecoration;
import com.sence.view.GridSpacingItemDecoration;

/**
 * A simple {@link Fragment} subclass.
 */
public class VipFragment extends Fragment {
    private RecyclerView recycle_view_top;
    private RecyclerView recycle_view_bottom;

    private VipTopAdapter topAdapter;
    private VipBottomAdapter bottomAdapter;

    public VipFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_vip, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recycle_view_top = getView().findViewById(R.id.recycle_view_top);
        recycle_view_bottom = getView().findViewById(R.id.recycle_view_bottom);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recycle_view_top.addItemDecoration(new DividerSpacingItemDecoration(DividerSpacingItemDecoration.HORIZONTAL,
                ConvertUtils.dp2px(15)));
        recycle_view_top.setLayoutManager(linearLayoutManager);
        recycle_view_bottom.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recycle_view_bottom.addItemDecoration(new GridSpacingItemDecoration(2, ConvertUtils.dp2px(15), false));
        topAdapter = new VipTopAdapter(R.layout.rv_item_vip_top);
        recycle_view_top.setAdapter(topAdapter);
        bottomAdapter = new VipBottomAdapter(R.layout.rv_item_vip_bottom);
        recycle_view_bottom.setAdapter(bottomAdapter);
        topAdapter.addData("");
        topAdapter.addData("");
        topAdapter.addData("");
        bottomAdapter.addData("");
        bottomAdapter.addData("");
        bottomAdapter.addData("");
    }
}
