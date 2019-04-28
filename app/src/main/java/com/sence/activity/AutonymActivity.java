package com.sence.activity;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.sence.R;
import com.sence.base.BaseActivity;
import com.sence.bean.request.RAutonymBean;
import com.sence.net.HttpCode;
import com.sence.net.HttpManager;
import com.sence.net.manager.ApiCallBack;
import com.sence.utils.LoginStatus;
import com.sence.utils.SharedPreferencesUtil;
import com.sence.utils.StatusBarUtil;
import com.sence.view.PubTitle;

import butterknife.BindView;
import butterknife.OnClick;
/**
 * 实名认证
 * */
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
        btSubmintAutonym.setClickable(false);
        if("2".equals(LoginStatus.getIdStatus())){
            llHeadAutonym.setVisibility(View.GONE);
            llShowAutonym.setVisibility(View.VISIBLE);
            tvNameAutonym.setText(LoginStatus.getRealName());
            tvIdentityAutonym.setText(LoginStatus.getIdentity());
        }
        etIdentityAutonym.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().trim().length()==18&& !TextUtils.isEmpty(etNameAutonym.getText().toString().trim())){
                    btSubmintAutonym.setBackgroundResource(R.drawable.shape_autonym_donebg);
                    btSubmintAutonym.setClickable(true);
                }else{
                    btSubmintAutonym.setBackgroundResource(R.drawable.shape_autonym_bg);
                }
            }
        });
    }

    @OnClick(R.id.bt_submint_autonym)
    public void onViewClicked() {
        HttpManager.getInstance().PlayNetCode(HttpCode.USER_AUTH, new RAutonymBean("1",etNameAutonym.getText().toString().trim(),etIdentityAutonym.getText().toString().trim(),LoginStatus.getUid())).request(new ApiCallBack<String>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void Message(int code, String message) {

            }

            @Override
            public void onSuccess(String o, String msg) {
                Logger.e("msg==========" + msg);
                SharedPreferencesUtil.getInstance().putString("id_status", "2");
                String name = etNameAutonym.getText().toString().trim();
                String autonym = etIdentityAutonym.getText().toString().trim();
                StringBuffer userName = null;
                for (int i = 0; i <name.length() ; i++) {
                    if(i==0){
                        userName.append(name.substring(0,1));
                    }else{
                        userName.append("*");
                    }
                }
                String autonymhead = autonym.substring(0, 1);
                String autonymtail = autonym.substring(autonym.length() - 1, autonym.length());
                SharedPreferencesUtil.getInstance().putString("real_name",userName.toString() );
                SharedPreferencesUtil.getInstance().putString("id_card", autonymhead+"**************"+autonymtail);
                tvNameAutonym.setText(userName.toString());
                tvIdentityAutonym.setText(autonymhead+"**************"+autonymtail);
                llHeadAutonym.setVisibility(View.GONE);
                llShowAutonym.setVisibility(View.VISIBLE);
            }
        });
    }
}
