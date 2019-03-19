package com.sence.fragment.main;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.sence.R;
import com.sence.adapter.MainFocusAdapter;
import com.sence.view.GridSpacingItemDecoration;

/**
 * 关注Fragment
 */
public class FocusFragment extends Fragment {
    private SmartRefreshLayout smartRefreshLayout;
    private RecyclerView recyclerView;

    private MainFocusAdapter adapter;

    public FocusFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_focus, container, false);
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
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new MainFocusAdapter(R.layout.rv_item_main_focus);
        recyclerView.setNestedScrollingEnabled(true);
        recyclerView.setAdapter(adapter);
        GridSpacingItemDecoration gridSpacingItemDecoration = new GridSpacingItemDecoration(2, 10, false);
        recyclerView.addItemDecoration(gridSpacingItemDecoration);
        adapter.addData("");
        adapter.addData("");
        adapter.addData("");
        adapter.addData("");
        adapter.addData("");
        adapter.addData("");
    }
}
