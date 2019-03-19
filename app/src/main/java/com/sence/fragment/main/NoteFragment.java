package com.sence.fragment.main;


import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.*;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.sence.R;
import com.sence.adapter.FocusAdapter;
import com.sence.adapter.MainFocusAdapter;
import com.sence.adapter.NoteAdapter;
import com.sence.view.GridDividerItemDecoration;
import com.sence.view.GridSpacingItemDecoration;

/**
 * 笔记Fragment
 */
public class NoteFragment extends Fragment {
    private SmartRefreshLayout smartRefreshLayout;
    private RecyclerView recyclerView;

    private NoteAdapter adapter;

    public NoteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_note, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initRefresh();
    }

    public void initRefresh() {
        smartRefreshLayout = getView().findViewById(R.id.smart_refresh);
        recyclerView = getView().findViewById(R.id.recycle_view);
        smartRefreshLayout.setRefreshHeader(new ClassicsHeader(getActivity()));
        smartRefreshLayout.setRefreshFooter(new ClassicsFooter(getActivity()));
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        GridSpacingItemDecoration gridSpacingItemDecoration = new GridSpacingItemDecoration(2, 10, false);
        recyclerView.addItemDecoration(gridSpacingItemDecoration);
        adapter = new NoteAdapter(R.layout.rv_item_note);
        recyclerView.setAdapter(adapter);
        adapter.addData("");
        adapter.addData("");
        adapter.addData("");
        adapter.addData("");
        adapter.addData("");
        adapter.addData("");
        adapter.addData("");
        adapter.addData("");
    }

}
