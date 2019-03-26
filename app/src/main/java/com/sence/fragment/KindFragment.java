package com.sence.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.sence.R;
import com.sence.adapter.KindLeftAdapter;
import com.sence.adapter.KindRightAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class KindFragment extends Fragment {
    private RecyclerView recycle_view_horizontal;
    private RecyclerView recycle_view_vertical;

    private KindLeftAdapter leftAdapter;
    private KindRightAdapter rightAdapter;

    public KindFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_kind, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recycle_view_horizontal = getView().findViewById(R.id.recycle_view_horizontal);
        recycle_view_vertical = getView().findViewById(R.id.recycle_view_vertical);

        recycle_view_horizontal.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycle_view_vertical.setLayoutManager(new LinearLayoutManager(getActivity()));

        leftAdapter = new KindLeftAdapter(R.layout.rv_item_kind_left);
        recycle_view_horizontal.setAdapter(leftAdapter);
        rightAdapter = new KindRightAdapter(R.layout.rv_item_kind_right);
        recycle_view_vertical.setAdapter(rightAdapter);

        leftAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                leftAdapter.setPosition(position);
            }
        });

        for (int i = 0; i < 8; i++) {
            leftAdapter.addData("上衣" + i);
            rightAdapter.addData("");
        }
    }
}
