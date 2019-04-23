package com.sence.activity;

import android.widget.TextView;
import butterknife.BindView;
import com.sence.R;
import com.sence.base.BaseActivity;
import com.sence.utils.StatusBarUtil;

/**
 * 关于
 */

public class AboutActivity extends BaseActivity {

    @BindView(R.id.tv_version_about)
    TextView tvVersionAbout;

    @Override
    public int onActLayout() {
        return R.layout.activity_about;
    }

    @Override
    public void initView() {
        StatusBarUtil.setLightMode(this);
    }

}
