package com.sence.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.ToastUtils;
import com.sence.R;
import com.sence.base.BaseActivity;
import com.sence.utils.StatusBarUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ReportCauseActivity extends BaseActivity {


    @BindView(R.id.et_content_report)
    EditText etContentReport;
    @BindView(R.id.iv_select_report)
    ImageView ivSelectReport;
    @BindView(R.id.ll_rachel_report)
    LinearLayout llRachelReport;
    @BindView(R.id.bt_submint_report)
    Button btSubmintReport;
    private boolean isslect = false;
    private String content;

    @Override
    public int onActLayout() {
        return R.layout.activity_report_cause;
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


    @OnClick({R.id.iv_select_report, R.id.ll_rachel_report, R.id.bt_submint_report})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_select_report:
                isslect=!isslect;
                if(isslect){
                    ivSelectReport.setImageResource(R.drawable.report_xuanzhong);
                }else{
                    ivSelectReport.setImageResource(R.drawable.report_wei);
                }
                break;
            case R.id.ll_rachel_report:
                break;
            case R.id.bt_submint_report:
                content = etContentReport.getText().toString().trim();
                if(TextUtils.isEmpty(content)){
                    ToastUtils.showShort("请先输入举报的原因");
                    return;
                }
                submint();
                break;
        }
    }

    private void submint() {
    }
}
