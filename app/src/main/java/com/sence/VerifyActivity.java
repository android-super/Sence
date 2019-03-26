package com.sence;

import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

/**
 * 验证码
 */
public class VerifyActivity extends AppCompatActivity {
    private ImageView verify_back;
    private TextView verify_number;
    private TextView verify_code_one, verify_code_two, verify_code_three, verify_code_four;
    private TextView verify_message;
    private TextView verify_get_code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);

        initView();
    }

    private void initView() {
        verify_back = findViewById(R.id.verify_back);
        verify_number = findViewById(R.id.verify_number);
        verify_code_one = findViewById(R.id.verify_code_one);
        verify_code_two = findViewById(R.id.verify_code_two);
        verify_code_three = findViewById(R.id.verify_code_three);
        verify_code_four = findViewById(R.id.verify_code_four);
        verify_message = findViewById(R.id.verify_message);
        verify_get_code = findViewById(R.id.verify_get_code);
    }
}
