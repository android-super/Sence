package com.sence;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.PhoneUtils;
import com.orhanobut.logger.Logger;
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

import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 验证码
 */
public class VerifyActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView verify_back;
    private TextView verify_number;
    private EditText verify_code;
    private TextView verify_code_one, verify_code_two, verify_code_three, verify_code_four;
    private TextView verify_message;
    private TextView verify_get_code;

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);
        StatusBarUtil.setLightMode(this);
        phone = this.getIntent().getStringExtra("phone");
        unionid = this.getIntent().getStringExtra("unionid");
        openid = this.getIntent().getStringExtra("openid");
        gender = this.getIntent().getStringExtra("gender");
        profile_image_url = this.getIntent().getStringExtra("profile_image_url");
        name = this.getIntent().getStringExtra("name");
        screen_name = this.getIntent().getStringExtra("screen_name");
        iconurl = this.getIntent().getStringExtra("iconurl");

        initView();
    }

    private void initView() {
        verify_back = findViewById(R.id.verify_back);
        verify_number = findViewById(R.id.verify_number);
        verify_code = findViewById(R.id.verify_code);
        verify_code_one = findViewById(R.id.verify_code_one);
        verify_code_two = findViewById(R.id.verify_code_two);
        verify_code_three = findViewById(R.id.verify_code_three);
        verify_code_four = findViewById(R.id.verify_code_four);
        verify_message = findViewById(R.id.verify_message);
        verify_get_code = findViewById(R.id.verify_get_code);

        textViews = new TextView[]{verify_code_one, verify_code_two, verify_code_three, verify_code_four};

        verify_code_one.setOnClickListener(this);
        verify_code_two.setOnClickListener(this);
        verify_code_three.setOnClickListener(this);
        verify_code_four.setOnClickListener(this);
        verify_back.setOnClickListener(this);
        verify_get_code.setOnClickListener(this);

        verify_number.setText(phone);


        verify_code.addTextChangedListener(new TextWatcher() {
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
                Logger.e("code_id===" + code_id);
                doLastTime();
            }
        });
    }

    /**
     * 注册
     */
    private void register() {
        Logger.e(phone + " " + code + " " + code_id + " " + openid + " " + profile_image_url + " " + name + " " + gender +
                " " + unionid);
        RUserRegisterBean rUserRegisterBean = new RUserRegisterBean(phone, code_id, code, PhoneUtils.getIMEI(),
                "android"
                , openid, profile_image_url, name, gender, unionid);
        HttpManager.getInstance().PlayNetCode(HttpCode.REGISTER, rUserRegisterBean).request(new ApiCallBack<PUserBean>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void Message(int code, String message) {
                verify_message.setText(message);
            }

            @Override
            public void onSuccess(PUserBean o, String msg) {
                SharedPreferencesUtil.getInstance().putBoolean("is_login", true);
                SharedPreferencesUtil.getInstance().putString("uid", o.getId());
                SharedPreferencesUtil.getInstance().putString("user_name", o.getUser_name());
                SharedPreferencesUtil.getInstance().putString("sex", o.getSex());
                SharedPreferencesUtil.getInstance().putString("avatar", o.getAvatar());
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
                            verify_get_code.setText("重新获取");
                            mDisposable.dispose();
                            verify_get_code.setEnabled(true);
                            verify_get_code.setSelected(true);
                        } else {
                            verify_get_code.setEnabled(false);
                            verify_get_code.setSelected(false);
                            verify_get_code.setText("重新获取(" + (60 - aLong) + "s)");
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
