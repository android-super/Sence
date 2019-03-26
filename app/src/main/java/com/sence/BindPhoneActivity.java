package com.sence;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

/**
 * 绑定手机号
 */
public class BindPhoneActivity extends AppCompatActivity {
    private ImageView bind_close;
    private EditText bind_number;
    private TextView bind_get_code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind_phone);
        initView();
    }

    private void initView() {
        bind_close = findViewById(R.id.bind_close);
        bind_number = findViewById(R.id.bind_number);
        bind_get_code = findViewById(R.id.bind_get_code);

        bind_get_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Intent(BindPhoneActivity.this,VerifyActivity.class);
            }
        });
    }
}
