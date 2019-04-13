package com.sence;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.PhoneUtils;
import com.sence.base.BaseActivity;
import com.sence.bean.request.RRegisterBean;
import com.sence.bean.request.RUserRegisterBean;
import com.sence.bean.response.PUserBean;
import com.sence.bean.response.PVerifyCodeBean;
import com.sence.net.HttpCode;
import com.sence.net.HttpManager;
import com.sence.net.manager.ApiCallBack;
import com.sence.utils.SharedPreferencesUtil;
import com.sence.utils.StatusBarUtil;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 验证码
 */
public class VerifyActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.verify_back)
    ImageView verifyBack;
    @BindView(R.id.verify_number)
    TextView verifyNumber;
    @BindView(R.id.verify_code_one)
    TextView verifyCodeOne;
    @BindView(R.id.verify_code_two)
    TextView verifyCodeTwo;
    @BindView(R.id.verify_code_three)
    TextView verifyCodeThree;
    @BindView(R.id.verify_code_four)
    TextView verifyCodeFour;
    @BindView(R.id.verify_code)
    EditText verifyCode;
    @BindView(R.id.verify_message)
    TextView verifyMessage;
    @BindView(R.id.verify_get_code)
    TextView verifyGetCode;

    private TextView[] textViews;

    private String phone;
    private String code_id;
    private String code;

    private String unionid;
    private String openid;
    private String gender;
    private String profile_image_url;
    private String name;
    private String screen_name;
    private String iconurl;


    private Disposable mDisposable;


    @Override
    public int onActLayout() {
        return R.layout.activity_verify;
    }

    public void initView() {
        StatusBarUtil.setLightMode(this);
        phone = this.getIntent().getStringExtra("phone");
        unionid = this.getIntent().getStringExtra("unionid");
        openid = this.getIntent().getStringExtra("openid");
        gender = this.getIntent().getStringExtra("gender");
        profile_image_url = this.getIntent().getStringExtra("profile_image_url");
        name = this.getIntent().getStringExtra("name");
        screen_name = this.getIntent().getStringExtra("screen_name");
        iconurl = this.getIntent().getStringExtra("iconurl");

        textViews = new TextView[]{verifyCodeOne, verifyCodeTwo, verifyCodeThree, verifyCodeFour};

        verifyCodeOne.setOnClickListener(this);
        verifyCodeTwo.setOnClickListener(this);
        verifyCodeThree.setOnClickListener(this);
        verifyCodeFour.setOnClickListener(this);
        verifyBack.setOnClickListener(this);
        verifyGetCode.setOnClickListener(this);

        verifyNumber.setText(phone);


        verifyCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                setCode(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        sendVerifyCode();
        doLastTime();
    }


    public void setCode(CharSequence s) {
        if (s.length() == 4) {
            code = s.toString();
            register();
        }

        for (int i = 0; i < 4; i++) {
            if (i < s.length()) {
                textViews[i].setText(String.valueOf(s.charAt(i)));
            } else {
                textViews[i].setText("");
            }

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.verify_code_one:
                break;
            case R.id.verify_code_two:
                break;
            case R.id.verify_code_three:
                break;
            case R.id.verify_code_four:
                break;
            case R.id.verify_back:
                finish();
                break;
            case R.id.verify_get_code:
                sendVerifyCode();
                break;
        }
    }

    /**
     * 发送验证码
     */
    private void sendVerifyCode() {
        HttpManager.getInstance().PlayNetCode(HttpCode.SEND_VERIFY_CODE, new RRegisterBean(phone)).request(new ApiCallBack<PVerifyCodeBean>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void Message(int code, String message) {

            }

            @Override
            public void onSuccess(PVerifyCodeBean o, String msg) {
                code_id = o.getId();
                doLastTime();
            }
        });
    }

    /**
     * 注册
     */
    private void register() {
        RUserRegisterBean rUserRegisterBean = new RUserRegisterBean(phone, code_id, code, PhoneUtils.getIMEI(),
                "android"
                , openid, profile_image_url, name, gender, unionid);
        HttpManager.getInstance().PlayNetCode(HttpCode.REGISTER, rUserRegisterBean).request(new ApiCallBack<PUserBean>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void Message(int code, String message) {
                verifyMessage.setText(message);
            }

            @Override
            public void onSuccess(PUserBean o, String msg) {
                SharedPreferencesUtil.getInstance().putBoolean("is_login", true);
                SharedPreferencesUtil.getInstance().putString("uid", o.getId());
                SharedPreferencesUtil.getInstance().putString("user_name", o.getUser_name());
                SharedPreferencesUtil.getInstance().putString("nick_name", o.getNick_name());
                SharedPreferencesUtil.getInstance().putString("sex", o.getSex());
                SharedPreferencesUtil.getInstance().putString("avatar", o.getAvatar());
                String name = o.getReal_name().toString();
                StringBuffer userName = null;
                for (int i = 0; i <name.length() ; i++) {
                    if(i==0){
                        userName.append(name.substring(0,1));
                    }else{
                        userName.append("*");
                    }
                }
                SharedPreferencesUtil.getInstance().putString("real_name", userName.toString());
                SharedPreferencesUtil.getInstance().putString("id_card", o.getNick_name());
                SharedPreferencesUtil.getInstance().putString("id_status", o.getId_status());
                SharedPreferencesUtil.getInstance().putString("img_status", o.getImg_status());
                Intent intent = new Intent(VerifyActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * 做倒计时
     */
    public void doLastTime() {
        mDisposable = Observable.interval(0, 1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()) // 由于interval默认在新线程，所以我们应该切回主线程
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(@NonNull Long aLong) {
                        if (60 - aLong <= 0) {
                            verifyGetCode.setText("重新获取");
                            mDisposable.dispose();
                            verifyGetCode.setEnabled(true);
                            verifyGetCode.setSelected(true);
                        } else {
                            verifyGetCode.setEnabled(false);
                            verifyGetCode.setSelected(false);
                            verifyGetCode.setText("重新获取(" + (60 - aLong) + "s)");
                        }
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mDisposable != null && mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }
}
