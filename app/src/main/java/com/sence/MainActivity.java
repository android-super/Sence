package com.sence;

import android.view.View;
import android.widget.LinearLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import butterknife.BindView;
import com.blankj.utilcode.util.SPUtils;
import com.sence.base.BaseActivity;
import com.sence.fragment.*;
import com.sence.net.HttpCode;
import com.sence.net.HttpManager;
import com.sence.net.manager.ApiCallBack;
import com.sence.utils.SocketUtils;
import com.sence.utils.StatusBarUtil;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.main_home)
    LinearLayout mainHome;
    @BindView(R.id.main_vip)
    LinearLayout mainVip;
    @BindView(R.id.main_kind)
    LinearLayout mainKind;
    @BindView(R.id.main_bus)
    LinearLayout mainBus;
    @BindView(R.id.main_user)
    LinearLayout mainUser;

    private LinearLayout[] mains;
    private MainFragment mainFragment;
    private VipFragment vipFragment;
    private KindFragment kindFragment;
    private BusFragment busFragment;
    private UserFragment userFragment;
    private Fragment[] fragments;


    @Override
    public int onActLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        StatusBarUtil.setLightMode(this);
        SocketUtils.getInstance().startSocket();

        mainHome.setTag(0);
        mainVip.setTag(1);
        mainKind.setTag(2);
        mainBus.setTag(3);
        mainUser.setTag(4);
        mainHome.setOnClickListener(this);
        mainVip.setOnClickListener(this);
        mainKind.setOnClickListener(this);
        mainBus.setOnClickListener(this);
        mainUser.setOnClickListener(this);
        mainFragment = new MainFragment();
        vipFragment = new VipFragment();
        kindFragment = new KindFragment();
        busFragment = new BusFragment();
        userFragment = new UserFragment();
        fragments = new Fragment[]{mainFragment, vipFragment, kindFragment, busFragment, userFragment};
        mains = new LinearLayout[]{mainHome, mainVip, mainKind, mainBus, mainUser};

        setSelect(0);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SocketUtils.getInstance().stopSocket();
    }
}
