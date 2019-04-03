package com.sence;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.blankj.utilcode.util.SPUtils;
import com.orhanobut.logger.Logger;
import com.sence.bean.request.RRegisterBean;
import com.sence.fragment.BusFragment;
import com.sence.fragment.KindFragment;
import com.sence.fragment.MainFragment;
import com.sence.fragment.UserFragment;
import com.sence.fragment.VipFragment;
import com.sence.net.HttpCode;
import com.sence.net.HttpManager;
import com.sence.net.manager.ApiCallBack;
import com.sence.utils.StatusBarUtil;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout main_home, main_vip, main_kind, main_bus, main_user;
    private LinearLayout[] mains;
    private MainFragment mainFragment;
    private VipFragment vipFragment;
    private KindFragment kindFragment;
    private BusFragment busFragment;
    private UserFragment userFragment;
    private Fragment[] fragments;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StatusBarUtil.setLightMode(this);
        main_home = findViewById(R.id.main_home);
        main_vip = findViewById(R.id.main_vip);
        main_kind = findViewById(R.id.main_kind);
        main_bus = findViewById(R.id.main_bus);
        main_user = findViewById(R.id.main_user);
        main_home.setTag(0);
        main_vip.setTag(1);
        main_kind.setTag(2);
        main_bus.setTag(3);
        main_user.setTag(4);
        main_home.setOnClickListener(this);
        main_vip.setOnClickListener(this);
        main_kind.setOnClickListener(this);
        main_bus.setOnClickListener(this);
        main_user.setOnClickListener(this);
        mainFragment = new MainFragment();
        vipFragment = new VipFragment();
        kindFragment = new KindFragment();
        busFragment = new BusFragment();
        userFragment = new UserFragment();
        fragments = new Fragment[]{mainFragment, vipFragment, kindFragment, busFragment, userFragment};
        mains = new LinearLayout[]{main_home, main_vip, main_kind, main_bus, main_user};

        setSelect(0);

        HttpManager.getInstance().PlayNetCode(HttpCode.GET_SYSTEM_TIME).request(new ApiCallBack<String>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void Message(int code, String message) {

            }

            @Override
            public void onSuccess(String time, String msg) {
                long sys_time = System.currentTimeMillis() / 1000;
                long resu = Long.parseLong(time) - sys_time;
                SPUtils.getInstance().put("sysTime", String.valueOf(resu));
            }
        });
    }

    @Override
    public void onClick(View view) {
        setSelect((Integer) view.getTag());
    }

    public void setSelect(int position) {
        for (int i = 0; i < mains.length; i++) {
            if (i == position) {
                mains[i].setSelected(true);
                if (fragments[i].isAdded()) {
                    getSupportFragmentManager().beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).show(fragments[i]).commit();
                } else {
                    getSupportFragmentManager().beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).add(R.id.frame_layout, fragments[i]).show(fragments[i]).commit();
                }
            } else {
                mains[i].setSelected(false);
                if (fragments[i].isAdded()) {
                    getSupportFragmentManager().beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).hide(fragments[i]).commit();
                }
            }
        }
    }
}
