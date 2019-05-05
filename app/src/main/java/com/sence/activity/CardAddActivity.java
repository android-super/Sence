package com.sence.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.blankj.utilcode.util.ToastUtils;
import com.sence.R;
import com.sence.base.BaseActivity;
import com.sence.bean.request.RCardAddBean;
import com.sence.net.HttpCode;
import com.sence.net.HttpManager;
import com.sence.net.manager.ApiCallBack;
import com.sence.utils.LoginStatus;
import com.sence.utils.StatusBarUtil;

/**
 * 添加銀行卡界面
 */
public class CardAddActivity extends BaseActivity implements View.OnClickListener {
    public static final int BANK_CODE = 10001;

    @BindView(R.id.card_name)
    EditText cardName;
    @BindView(R.id.card_bank)
    TextView cardBank;
    @BindView(R.id.card_phone)
    EditText cardPhone;
    @BindView(R.id.card_number)
    EditText cardNumber;
    @BindView(R.id.card_point)
    EditText cardPoint;
    @BindView(R.id.card_commit)
    TextView cardCommit;

    private String bank_id;
    private String type = "2";//1实名认证，2添加银行卡
    private String real_name;
    private String card_num;
    private String pre_phone;
    private String branch_name;

    @Override
    public int onActLayout() {
        return R.layout.activity_card_add;
    }

    @Override
    public void initView() {
        StatusBarUtil.setLightMode(this);
        cardBank.setOnClickListener(this);
        cardCommit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.card_bank:
                startActivityForResult(new Intent(CardAddActivity.this, CardListActivity.class), BANK_CODE);
                break;
            case R.id.card_commit:
                real_name = cardName.getText().toString();
                pre_phone = cardPhone.getText().toString();
                card_num = cardNumber.getText().toString();
                branch_name = cardPoint.getText().toString();
                if (TextUtils.isEmpty(real_name)) {
                    ToastUtils.showShort("请输入持卡人姓名");
                    return;
                }
                if (TextUtils.isEmpty(bank_id)) {
                    ToastUtils.showShort("请选择银行");
                    return;
                }
                if (TextUtils.isEmpty(pre_phone)) {
                    ToastUtils.showShort("请输入银行预留手机号码");
                    return;
                }
                if (TextUtils.isEmpty(card_num)) {
                    ToastUtils.showShort("请输入银行卡号");
                    return;
                }
                if (TextUtils.isEmpty(branch_name)) {
                    ToastUtils.showShort("请输入银行开户网点信息");
                    return;
                }
                addBankCard();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == BANK_CODE) {
            cardBank.setText(data.getStringExtra("bank_name"));
            bank_id = data.getStringExtra("bank_id");
        }
    }

    public void addBankCard() {
        HttpManager.getInstance().PlayNetCode(HttpCode.BANK_ADD,
                new RCardAddBean(type, real_name, card_num, bank_id, LoginStatus.getUid(),
                        pre_phone, branch_name
                )).request(new ApiCallBack() {
            @Override
            public void onFinish() {

            }

            @Override
            public void Message(int code, String message) {
                ToastUtils.showShort(message);
            }

            @Override
            public void onSuccess(Object o, String msg) {
                ToastUtils.showShort(msg);
                finish();
            }
        });
    }
}
