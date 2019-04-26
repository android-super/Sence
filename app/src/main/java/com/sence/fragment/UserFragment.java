package com.sence.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sence.LoginActivity;
import com.sence.R;
import com.sence.activity.ManageAddressActivity;
import com.sence.activity.MessageActivity;
import com.sence.activity.MyAccountActivity;
import com.sence.activity.MyFansFocusNoteActivity;
import com.sence.activity.MyInfoActivity;
import com.sence.activity.MyOrderActivity;
import com.sence.activity.OpenVipPageActivity;
import com.sence.activity.SettingActivity;
import com.sence.activity.web.WebConstans;
import com.sence.activity.web.WebNotitleActivity;
import com.sence.base.BaseMainFragment;
import com.sence.bean.request.RUidBean;
import com.sence.bean.response.PUserInfoBean;
import com.sence.net.HttpCode;
import com.sence.net.HttpManager;
import com.sence.net.manager.ApiCallBack;
import com.sence.utils.GlideUtils;
import com.sence.utils.LoginStatus;
import com.sence.utils.SharedPreferencesUtil;
import com.sence.view.NiceImageView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserFragment extends BaseMainFragment implements View.OnClickListener {
    private RelativeLayout user_message_layout;
    private TextView user_message_num;
    private NiceImageView user_head;
    private TextView user_name;
    private LinearLayout user_focus_layout, user_fans_layout, user_release_layout;
    private TextView user_focus, user_fans, user_release;

    private RelativeLayout user_open_layout;
    private TextView user_price;
    private ImageView user_open;
    private RelativeLayout user_vip_layout;

    private TextView user_all_order;
    private TextView user_pay, user_send, user_get, user_comment;
    private TextView user_pay_point, user_send_point, user_get_point, user_comment_point;
    private RelativeLayout user_flower;
    private ImageView user_garden_point;
    private TextView user_account, user_address, user_set;

    private String save_money;//预计一年省
    private PUserInfoBean bean;

    public UserFragment() {
        // Required empty public constructor
    }

    private void initView() {
        user_open_layout = getView().findViewById(R.id.user_open_layout);
        user_price = getView().findViewById(R.id.user_price);
        user_open = getView().findViewById(R.id.user_open);
        user_vip_layout = getView().findViewById(R.id.user_vip_layout);

        user_head = getView().findViewById(R.id.user_head);
        user_name = getView().findViewById(R.id.user_name);
        user_focus_layout = getView().findViewById(R.id.user_focus_layout);
        user_fans_layout = getView().findViewById(R.id.user_fans_layout);
        user_release_layout = getView().findViewById(R.id.user_release_layout);
        user_focus = getView().findViewById(R.id.user_focus);
        user_fans = getView().findViewById(R.id.user_fans);
        user_release = getView().findViewById(R.id.user_release);
        user_all_order = getView().findViewById(R.id.user_all_order);
        user_message_layout = getView().findViewById(R.id.user_message_layout);
        user_message_num = getView().findViewById(R.id.user_message_num);

        user_pay = getView().findViewById(R.id.user_pay);
        user_send = getView().findViewById(R.id.user_send);
        user_get = getView().findViewById(R.id.user_get);
        user_comment = getView().findViewById(R.id.user_comment);
        user_pay_point = getView().findViewById(R.id.user_pay_point);
        user_send_point = getView().findViewById(R.id.user_send_point);
        user_get_point = getView().findViewById(R.id.user_get_point);
        user_comment_point = getView().findViewById(R.id.user_comment_point);

        user_flower = getView().findViewById(R.id.user_flower);
        user_account = getView().findViewById(R.id.user_account);
        user_address = getView().findViewById(R.id.user_address);
        user_set = getView().findViewById(R.id.user_set);
        user_garden_point = getView().findViewById(R.id.user_garden_point);

        user_message_layout.setOnClickListener(this);
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
        user_open.setOnClickListener(this);

        initMyInfo();
    }

    public void initFirstView() {
        GlideUtils.getInstance().loadHead(LoginStatus.getAvatar(), user_head);
        user_open_layout.setVisibility(View.VISIBLE);
        user_vip_layout.setVisibility(View.GONE);
        user_name.setText("登录 / 注册");
        user_focus.setText("0");
        user_fans.setText("0");
        user_release.setText("0");
        user_pay_point.setVisibility(View.GONE);
        user_send_point.setVisibility(View.GONE);
        user_get_point.setVisibility(View.GONE);
        user_comment_point.setVisibility(View.GONE);
        user_garden_point.setVisibility(View.GONE);
        user_message_num.setVisibility(View.GONE);
    }

    private void initDataView(PUserInfoBean o) {
        if (o == null) {
            return;
        }
        bean = o;
        SharedPreferencesUtil.getInstance().putString("upuid", o.getUpuid());
        SharedPreferencesUtil.getInstance().putString("inviteUsername", o.getInviteUsername());
        SharedPreferencesUtil.getInstance().putString("is_vip", o.getIs_kol());
        GlideUtils.getInstance().loadHead(LoginStatus.getAvatar(), user_head);
        save_money = o.getSave_money();
        if (o.getIs_kol().equals("1")) {
            user_vip_layout.setVisibility(View.VISIBLE);
            user_open_layout.setVisibility(View.GONE);
        } else {
            user_open_layout.setVisibility(View.VISIBLE);
            user_vip_layout.setVisibility(View.GONE);
        }
        user_name.setText(o.getNick_name());
        user_focus.setText(o.getFollow_num());
        user_fans.setText(o.getFans_num());
        user_release.setText(o.getNote_num());
        user_price.setText("一年预计省￥" + o.getSave_money());

        if (o.getOrder_info().getWait_pay().equals("0")) {
            user_pay_point.setVisibility(View.GONE);
        } else {
            user_pay_point.setVisibility(View.VISIBLE);
            user_pay_point.setText(o.getOrder_info().getWait_pay());
        }

        if (o.getOrder_info().getWait_send().equals("0")) {
            user_send_point.setVisibility(View.GONE);
        } else {
            user_send_point.setVisibility(View.VISIBLE);
            user_send_point.setText(o.getOrder_info().getWait_send());
        }

        if (o.getOrder_info().getWait_confirm().equals("0")) {
            user_get_point.setVisibility(View.GONE);
        } else {
            user_get_point.setVisibility(View.VISIBLE);
            user_get_point.setText(o.getOrder_info().getWait_confirm());
        }

        if (o.getOrder_info().getWait_evaluate().equals("0")) {
            user_comment_point.setVisibility(View.GONE);
        } else {
            user_comment_point.setVisibility(View.VISIBLE);
            user_comment_point.setText(o.getOrder_info().getWait_evaluate());
        }
        if (o.getGardenRed().equals("0")) {
            user_garden_point.setVisibility(View.GONE);
        } else {
            user_garden_point.setVisibility(View.VISIBLE);
        }
        if (o.getMsg_num().equals("0")) {
            user_message_num.setVisibility(View.GONE);
        } else {
            user_message_num.setVisibility(View.VISIBLE);
            user_message_num.setText(o.getMsg_num());
        }


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
    }

    private void initMyInfo() {
        HttpManager.getInstance().PlayNetCode(HttpCode.USER_INFO, new RUidBean(LoginStatus.getUid())).request(new ApiCallBack<PUserInfoBean>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void Message(int code, String message) {

            }

            @Override
            public void onSuccess(PUserInfoBean o, String msg) {
                initDataView(o);
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.user_message_layout:
                Intent intent = new Intent(getContext(), MessageActivity.class);
                toLogin(intent);
                break;
            case R.id.user_head:
                intent = new Intent(getContext(), MyInfoActivity.class);
                intent.putExtra("uid", LoginStatus.getUid());
                toLogin(intent);
                break;
            case R.id.user_name:
                intent = new Intent(getContext(), MyInfoActivity.class);
                intent.putExtra("uid", LoginStatus.getUid());
                toLogin(intent);
                break;
            case R.id.user_focus_layout:
                intent = new Intent(getContext(), MyFansFocusNoteActivity.class);
                intent.putExtra("position", 1);
                toLogin(intent);
                break;
            case R.id.user_fans_layout:
                intent = new Intent(getContext(), MyFansFocusNoteActivity.class);
                intent.putExtra("position", 0);
                toLogin(intent);
                break;
            case R.id.user_release_layout:
                intent = new Intent(getContext(), MyFansFocusNoteActivity.class);
                intent.putExtra("position", 2);
                toLogin(intent);
                break;
            case R.id.user_all_order:
                startMyOrder(0);
                break;
            case R.id.user_pay:
                startMyOrder(1);
                break;
            case R.id.user_send:
                startMyOrder(2);
                break;
            case R.id.user_get:
                startMyOrder(3);
                break;
            case R.id.user_comment:
                startMyOrder(4);
                break;
            case R.id.user_flower:
                intent = new Intent(getContext(), WebNotitleActivity.class);
                intent.putExtra("code", WebConstans.WebCode.HY);
                toLogin(intent);
                break;
            case R.id.user_account:
                intent = new Intent(getContext(), MyAccountActivity.class);
                toLogin(intent);
                break;
            case R.id.user_address:
                intent = new Intent(getContext(), ManageAddressActivity.class);
                toLogin(intent);
                break;
            case R.id.user_set:
                intent = new Intent(getContext(), SettingActivity.class);
                toLogin(intent);
                break;
            case R.id.user_open:
                intent = new Intent(getActivity(), OpenVipPageActivity.class);
                intent.putExtra("money", save_money);
                toLogin(intent);
                break;
        }
    }

    private void startMyOrder(int i) {
        Intent intent = new Intent(getContext(), MyOrderActivity.class);
        intent.putExtra("type", i);
        toLogin(intent);
    }

    public void toLogin(Intent intent) {
        if (!LoginStatus.isLogin() || LoginStatus.getUid().isEmpty()) {
            startActivity(new Intent(getActivity(), LoginActivity.class));
            return;
        } else {
            startActivity(intent);
        }
    }

    @Override
    public void onRefresh() {
        if (!LoginStatus.isLogin()) {
            initFirstView();
        }
        initMyInfo();
    }
}
