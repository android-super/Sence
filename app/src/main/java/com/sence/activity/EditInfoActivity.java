package com.sence.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.sence.R;
import com.sence.base.BaseActivity;
import com.sence.utils.StatusBarUtil;
import com.sence.view.NiceImageView;

/**
 * 修改个人信息
 */
public class EditInfoActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.edit_head)
    NiceImageView editHead;
    @BindView(R.id.edit_camera)
    ImageView editCamera;
    @BindView(R.id.edit_name)
    EditText editName;
    @BindView(R.id.edit_girl)
    TextView editGirl;
    @BindView(R.id.edit_boy)
    TextView editBoy;
    @BindView(R.id.edit_style)
    EditText editStyle;

    @Override
    public int onActLayout() {
        return R.layout.activity_edit_info;
    }

    public void initView() {
        StatusBarUtil.setLightMode(this);
        
        editHead.setOnClickListener(this);
        editGirl.setOnClickListener(this);
        editBoy.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.edit_head:
                break;
            case R.id.edit_girl:
                break;
            case R.id.edit_boy:
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
