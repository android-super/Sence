package com.sence.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sence.R;
import com.sence.base.BaseActivity;
import com.sence.utils.StatusBarUtil;
import com.sence.view.PubTitle;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AutonymActivity extends BaseActivity {
    @BindView(R.id.pb_title_setting)
    PubTitle pbTitleSetting;
    @BindView(R.id.et_name_autonym)
    EditText etNameAutonym;
    @BindView(R.id.et_identity_autonym)
    EditText etIdentityAutonym;
    @BindView(R.id.tv_hint_autonym)
    TextView tvHintAutonym;
    @BindView(R.id.bt_submint_autonym)
    Button btSubmintAutonym;
    @BindView(R.id.tv_name_autonym)
    TextView tvNameAutonym;
    @BindView(R.id.tv_identity_autonym)
    TextView tvIdentityAutonym;
    @BindView(R.id.ll_head_autonym)
    LinearLayout llHeadAutonym;
    @BindView(R.id.ll_show_autonym)
    LinearLayout llShowAutonym;

    @Override
    public int onActLayout() {
        return R.layout.activity_autonym;
    }

    @Override
    public void initView() {
        StatusBarUtil.setLightMode(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.bt_submint_autonym)
    public void onViewClicked() {
    }
}
