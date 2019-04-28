package com.sence.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;
import com.sence.MainActivity;
import com.sence.R;
import com.sence.activity.SearchActivity;
import com.sence.activity.chat.bean.ChatSocketBean;
import com.sence.adapter.pager.CustomViewPagerAdapter;
import com.sence.base.BaseMainFragment;
import com.sence.fragment.main.FocusFragment;
import com.sence.fragment.main.NoteFragment;
import com.sence.fragment.main.RecommendFragment;
import com.sence.utils.JsonParseUtil;
import com.sence.utils.LoginStatus;
import com.sence.utils.SharedPreferencesUtil;
import com.sence.utils.SocketUtils;
import com.sence.view.FadeTransformer;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends BaseMainFragment {
    private ViewPager viewPager;
    private TabLayout tab_layout;
    private ImageView release;
    private LinearLayout mSearch;

    private CustomViewPagerAdapter pagerAdapter;


    private String[] titles = {"关注", "推荐", "笔记"};


    public MainFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SocketUtils.getInstance().setOnGetSocketResult(new SocketUtils.OnGetSocketResult() {
            @Override
            public void putSocketResult(String str) {
                ChatSocketBean chatSocketBean = JsonParseUtil.parseString(str, ChatSocketBean.class);
                if (chatSocketBean.getType().equals("user")) {
                    if (chatSocketBean.getData().getCode().equals("101001")) {
                        SharedPreferencesUtil.getInstance().putString("is_vip", "1");
                        onRefresh();
                    }
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tab_layout = getView().findViewById(R.id.tab_layout);
        viewPager = getView().findViewById(R.id.view_pager);
        mSearch = getView().findViewById(R.id.ll_search);
        release = getView().findViewById(R.id.release);
        initTabLayout();
        if (LoginStatus.isVip()) {
            release.setVisibility(View.VISIBLE);
        } else {
            release.setVisibility(View.GONE);
        }
        release.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).sheet_content.setVisibility(View.VISIBLE);
            }
        });
    }

    private void initTabLayout() {
        mSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), SearchActivity.class));
            }
        });
        final Fragment[] fragments = {new FocusFragment(), new RecommendFragment(), new NoteFragment()};
        pagerAdapter = new CustomViewPagerAdapter(getChildFragmentManager(), getActivity());
        for (int i = 0; i < titles.length; i++) {
            tab_layout.addTab(tab_layout.newTab());
            pagerAdapter.addFragment(fragments[i], titles);
        }
        viewPager.setAdapter(pagerAdapter);
        tab_layout.setupWithViewPager(viewPager);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setPageTransformer(false, new FadeTransformer());
        for (int i = 0; i < tab_layout.getTabCount(); i++) {
            TabLayout.Tab tab = tab_layout.getTabAt(i);
            if (tab != null) {
                tab.setCustomView(pagerAdapter.getTabView(i));
            }
        }
        tab_layout.getTabAt(0).getCustomView().setSelected(true);
    }

    @Override
    public void onRefresh() {
        if (release == null) {
            return;
        }
        if (LoginStatus.isVip()) {
            release.setVisibility(View.VISIBLE);
        } else {
            release.setVisibility(View.GONE);
        }
    }
}
