package com.sence.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.sence.LoginActivity;
import com.sence.R;
import com.sence.activity.EnjoyVipActivity;
import com.sence.activity.MyInfoActivity;
import com.sence.activity.MyOrderActivity;
import com.sence.activity.ShopDetailsActivity;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.sence.utils.LoginStatus;
import com.sence.view.NiceImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserFragment extends Fragment implements View.OnClickListener {
    private ImageView user_message;
    private NiceImageView user_head;
    private TextView user_name;
    private LinearLayout user_focus_layout, user_fans_layout, user_release_layout;
    private TextView user_focus, user_fans, user_release;
    private TextView user_all_order;
    private TextView user_pay, user_send, user_get, user_comment;
    private ImageView user_flower;
    private TextView user_account, user_address, user_set;


    public UserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initMyInfo();
    }

    private void initMyInfo() {

    }

    private void initView() {
        user_message = getView().findViewById(R.id.user_message);
        user_head = getView().findViewById(R.id.user_head);
        user_name = getView().findViewById(R.id.user_name);
        user_focus_layout = getView().findViewById(R.id.user_focus_layout);
        user_fans_layout = getView().findViewById(R.id.user_fans_layout);
        user_release_layout = getView().findViewById(R.id.user_release_layout);
        user_focus = getView().findViewById(R.id.user_focus);
        user_fans = getView().findViewById(R.id.user_fans);
        user_release = getView().findViewById(R.id.user_release);
        user_all_order = getView().findViewById(R.id.user_all_order);
        user_pay = getView().findViewById(R.id.user_pay);
        user_send = getView().findViewById(R.id.user_send);
        user_get = getView().findViewById(R.id.user_get);
        user_comment = getView().findViewById(R.id.user_comment);
        user_flower = getView().findViewById(R.id.user_flower);
        user_account = getView().findViewById(R.id.user_account);
        user_address = getView().findViewById(R.id.user_address);
        user_set = getView().findViewById(R.id.user_set);

        user_message.setOnClickListener(this);
        user_head.setOnClickListener(this);
        user_name.setOnClickListener(this);
        user_focus_layout.setOnClickListener(this);
        user_fans_layout.setOnClickListener(this);
        user_release_layout.setOnClickListener(this);
        user_all_order.setOnClickListener(this);
        user_pay.setOnClickListener(this);
        user_send.setOnClickListener(this);
        user_get.setOnClickListener(this);
        user_comment.setOnClickListener(this);
        user_flower.setOnClickListener(this);
        user_account.setOnClickListener(this);
        user_address.setOnClickListener(this);
        user_set.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.user_message:
                startActivity(new Intent(getContext(), EnjoyVipActivity.class));
                break;
            case R.id.user_head:
                break;
            case R.id.user_name:
                if (LoginStatus.isLogin()) {
                    startActivity(new Intent(getContext(), MyInfoActivity.class));
                } else {
                    startActivity(new Intent(getContext(), LoginActivity.class));
                }
                break;
            case R.id.user_focus_layout:
                break;
            case R.id.user_fans_layout:
                break;
            case R.id.user_release_layout:
                break;
            case R.id.user_all_order:
                startActivity(new Intent(getContext(), MyOrderActivity.class));
                break;
            case R.id.user_pay:
                break;
            case R.id.user_send:
                break;
            case R.id.user_get:
                break;
            case R.id.user_comment:
                break;
            case R.id.user_flower:
                break;
            case R.id.user_account:
                startActivity(new Intent(getContext(), ShopDetailsActivity.class));
                break;
            case R.id.user_address:
                break;
            case R.id.user_set:
                break;
        }
    }
}
