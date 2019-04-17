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

import com.blankj.utilcode.util.ToastUtils;
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
import com.sence.activity.WebActivity;
import com.sence.bean.request.RUidBean;
import com.sence.bean.response.PUserInfoBean;
import com.sence.net.HttpCode;
import com.sence.net.HttpManager;
import com.sence.net.manager.ApiCallBack;
import com.sence.utils.GlideUtils;
import com.sence.utils.LoginStatus;
import com.sence.view.NiceImageView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserFragment extends Fragment implements View.OnClickListener {
    private ImageView user_message;
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
    private TextView user_account, user_address, user_set;

    public UserFragment() {
        // Required empty public constructor
    }

    private void initView() {

        user_open_layout = getView().findViewById(R.id.user_open_layout);
        user_price = getView().findViewById(R.id.user_price);
        user_open = getView().findViewById(R.id.user_open);
        user_vip_layout = getView().findViewById(R.id.user_vip_layout);

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
        user_pay_point = getView().findViewById(R.id.user_pay_point);
        user_send_point = getView().findViewById(R.id.user_send_point);
        user_get_point = getView().findViewById(R.id.user_get_point);
        user_comment_point = getView().findViewById(R.id.user_comment_point);

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
        user_open.setOnClickListener(this);
    }


    private void initDataView(PUserInfoBean o) {
        if (o == null) {
            return;
        }

        GlideUtils.getInstance().loadHead(LoginStatus.getAvatar(), user_head);

        if (o.getIs_kol().equals("1")) {
            user_vip_layout.setVisibility(View.VISIBLE);
        } else {
            user_vip_layout.setVisibility(View.GONE);
        }
        user_name.setText(o.getNick_name());
        user_focus.setText(o.getFollow_num());
        user_fans.setText(o.getFans_num());
        user_release.setText(o.getNote_num());
        user_price.setText("一年预计省￥" + o.getMoney());

        if (o.getOrder_info().getWait_pay().equals("0")) {
            user_pay_point.setVisibility(View.GONE);
        } else {
            user_pay_point.setText(o.getOrder_info().getWait_pay());
        }

        if (o.getOrder_info().getWait_send().equals("0")) {
            user_send_point.setVisibility(View.GONE);
        } else {
            user_send_point.setText(o.getOrder_info().getWait_send());
        }

        if (o.getOrder_info().getWait_confirm().equals("0")) {
            user_get_point.setVisibility(View.GONE);
        } else {
            user_get_point.setText(o.getOrder_info().getWait_confirm());
        }

        if (o.getOrder_info().getWait_evaluate().equals("0")) {
            user_comment_point.setVisibility(View.GONE);
        } else {
            user_comment_point.setText(o.getOrder_info().getWait_evaluate());
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
        initMyInfo();
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
            case R.id.user_message:
                if(LoginStatus.getUid().isEmpty()){
                    ToastUtils.showShort("请先登录");
                    return;
                }
                startActivity(new Intent(getContext(), MessageActivity.class));
                break;
            case R.id.user_head:
                if(LoginStatus.getUid().isEmpty()){
                    ToastUtils.showShort("请先登录");
                    return;
                }
                Intent intent = new Intent(getContext(), MyInfoActivity.class);
                intent.putExtra("uid", LoginStatus.getUid());
                startActivity(intent);
                break;
            case R.id.user_name:
                if (LoginStatus.isLogin()) {
                    intent = new Intent(getContext(), MyInfoActivity.class);
                    intent.putExtra("uid", LoginStatus.getUid());
                    startActivity(intent);
                } else {
                    startActivity(new Intent(getContext(), LoginActivity.class));
                }
                break;
            case R.id.user_focus_layout:
                if(LoginStatus.getUid().isEmpty()){
                    ToastUtils.showShort("请先登录");
                    return;
                }
                startActivity(new Intent(getContext(), MyFansFocusNoteActivity.class));
                break;
            case R.id.user_fans_layout:
                if(LoginStatus.getUid().isEmpty()){
                    ToastUtils.showShort("请先登录");
                    return;
                }
                startActivity(new Intent(getContext(), MyFansFocusNoteActivity.class));
                break;
            case R.id.user_release_layout:
                if(LoginStatus.getUid().isEmpty()){
                    ToastUtils.showShort("请先登录");
                    return;
                }
                startActivity(new Intent(getContext(), MyFansFocusNoteActivity.class));
                break;
            case R.id.user_all_order:
                if(LoginStatus.getUid().isEmpty()){
                    ToastUtils.showShort("请先登录");
                    return;
                }
                Intent intentall = new Intent(getContext(), MyOrderActivity.class);
                intentall.putExtra("type", 0);
                startActivity(intentall);
                break;
            case R.id.user_pay:
                if(LoginStatus.getUid().isEmpty()){
                    ToastUtils.showShort("请先登录");
                    return;
                }
                Intent intentPay = new Intent(getContext(), MyOrderActivity.class);
                intentPay.putExtra("type", 1);
                startActivity(intentPay);
                break;
            case R.id.user_send:
                if(LoginStatus.getUid().isEmpty()){
                    ToastUtils.showShort("请先登录");
                    return;
                }
                Intent intentSend = new Intent(getContext(), MyOrderActivity.class);
                intentSend.putExtra("type", 2);
                startActivity(intentSend);
                break;
            case R.id.user_get:
                if(LoginStatus.getUid().isEmpty()){
                    ToastUtils.showShort("请先登录");
                    return;
                }
                Intent intentGet = new Intent(getContext(), MyOrderActivity.class);
                intentGet.putExtra("type", 3);
                startActivity(intentGet);
                break;
            case R.id.user_comment:
                if(LoginStatus.getUid().isEmpty()){
                    ToastUtils.showShort("请先登录");
                    return;
                }
                Intent intentComment = new Intent(getContext(), MyOrderActivity.class);
                intentComment.putExtra("type", 4);
                startActivity(intentComment);
                break;
            case R.id.user_flower:
                if(LoginStatus.getUid().isEmpty()){
                    ToastUtils.showShort("请先登录");
                    return;
                }
                startActivity(new Intent(getContext(), WebActivity.class));
                break;
            case R.id.user_account:
                if(LoginStatus.getUid().isEmpty()){
                    ToastUtils.showShort("请先登录");
                    return;
                }
                startActivity(new Intent(getContext(), MyAccountActivity.class));
                break;
            case R.id.user_address:
                if(LoginStatus.getUid().isEmpty()){
                    ToastUtils.showShort("请先登录");
                    return;
                }
                startActivity(new Intent(getContext(), ManageAddressActivity.class));
                break;
            case R.id.user_set:
                if(LoginStatus.getUid().isEmpty()){
                    ToastUtils.showShort("请先登录");
                    return;
                }
                startActivity(new Intent(getContext(), SettingActivity.class));
                break;
            case R.id.user_open:
                if(LoginStatus.getUid().isEmpty()){
                    ToastUtils.showShort("请先登录");
                    return;
                }
                startActivity(new Intent(getContext(), OpenVipPageActivity.class));
                break;
        }
    }
}
