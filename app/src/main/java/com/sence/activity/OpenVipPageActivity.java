package com.sence.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.sence.R;
import com.sence.base.BaseActivity;
import com.sence.utils.LoginStatus;

public class OpenVipPageActivity extends BaseActivity {
    @BindView(R.id.vip_name)
    TextView vipName;
    @BindView(R.id.vip_describe)
    TextView vipDescribe;
    @BindView(R.id.vip_open)
    ImageView vipOpen;
    @BindView(R.id.vip_back)
    ImageView vipBack;

    @Override
    public int onActLayout() {
        return R.layout.activity_open_vip_page;
    }

    @Override
    public void initView() {
        vipName.setText("Hey "+ LoginStatus.getName());
        vipBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        vipOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OpenVipPageActivity.this,OpenVipActivity.class));
            }
        });
    }
}
