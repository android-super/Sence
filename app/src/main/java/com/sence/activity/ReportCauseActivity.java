package com.sence.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.ToastUtils;
import com.orhanobut.logger.Logger;
import com.sence.R;
import com.sence.base.BaseActivity;
import com.sence.bean.request.RReportBean;
import com.sence.net.HttpCode;
import com.sence.net.HttpManager;
import com.sence.net.manager.ApiCallBack;
import com.sence.utils.LoginStatus;
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
    private String gid;
    private String type;
    private String uid = "";

    @Override
    public int onActLayout() {
        return R.layout.activity_report_cause;
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        gid = intent.getStringExtra("gid");
        type = intent.getStringExtra("type");
        uid = intent.getStringExtra("uid");
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

                break;
            case R.id.ll_rachel_report:
                isslect=!isslect;
                if(isslect){
                    ivSelectReport.setImageResource(R.drawable.report_xuanzhong);
                }else{
                    ivSelectReport.setImageResource(R.drawable.report_wei);
                }
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
        HttpManager.getInstance().PlayNetCode(HttpCode.REPORT,
                new RReportBean(LoginStatus.getUid(), gid,type,content,uid)).request(new ApiCallBack<String>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void Message(int code, String message) {

            }

            @Override
            public void onSuccess(String o, String msg){
                Logger.e("msg==========" + msg);
                ToastUtils.showShort(msg);
                finish();
            }
        });

    }
}
