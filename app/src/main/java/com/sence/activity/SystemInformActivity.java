package com.sence.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.orhanobut.logger.Logger;
import com.sence.R;
import com.sence.adapter.SystemInformAdapter;
import com.sence.base.BaseActivity;
import com.sence.bean.request.RUidBean;
import com.sence.bean.response.PSystemInformBean;
import com.sence.net.HttpCode;
import com.sence.net.HttpManager;
import com.sence.net.manager.ApiCallBack;
import com.sence.utils.LoginStatus;
import com.sence.utils.StatusBarUtil;

import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 系统通知
 */

public class SystemInformActivity extends BaseActivity {

    @BindView(R.id.recycle_systeminform)
    RecyclerView recycleSysteminform;
    @BindView(R.id.iv_img_systeminform)
    ImageView ivImgSysteminform;
    private SystemInformAdapter mSystemInformAdapter;
    private int page = 1;

    @Override
    public int onActLayout() {
        return R.layout.activity_system_inform;
    }

    @Override
    public void initView() {
        StatusBarUtil.setLightMode(this);
        mSystemInformAdapter = new SystemInformAdapter(SystemInformActivity.this);
        LinearLayoutManager linearLayout = new LinearLayoutManager(SystemInformActivity.this);
        linearLayout.setOrientation(RecyclerView.VERTICAL);
        recycleSysteminform.setLayoutManager(linearLayout);
        recycleSysteminform.setAdapter(mSystemInformAdapter);
    }


    @Override
    public void initData() {
        HttpManager.getInstance().PlayNetCode(HttpCode.SYSTEM_MESSAGE, new RUidBean(LoginStatus.getUid())).request(new ApiCallBack<List<PSystemInformBean>>() {
            @Override
            public void onFinish() {
            }

            @Override
            public void Message(int code, String message) {

            }

            @Override
            public void onSuccess(List<PSystemInformBean> o, String msg) {
                Logger.e("msg==========" + msg);
                if (o.size() > 0) {
                    ivImgSysteminform.setVisibility(View.GONE);
                    mSystemInformAdapter.setList(o);
                }


            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
