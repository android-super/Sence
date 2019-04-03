package com.sence.activity;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.sence.R;
import com.sence.utils.StatusBarUtil;
import com.sence.view.NiceImageView;

/**
 * 修改个人信息
 */
public class EditInfoActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView pub_back;
    private TextView pub_title;

    private NiceImageView edit_head;
    private ImageView edit_camera;
    private EditText edit_name;
    private TextView edit_girl,edit_boy;
    private EditText edit_style;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_info);
        StatusBarUtil.setLightMode(this);
        initTitle();
        initView();
    }
    private void initTitle() {
        pub_back = findViewById(R.id.pub_back);
        pub_title =  findViewById(R.id.pub_title);
        pub_title.setText("个人信息");
        pub_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        edit_head = findViewById(R.id.edit_head);
        edit_camera = findViewById(R.id.edit_camera);
        edit_name = findViewById(R.id.edit_name);
        edit_girl = findViewById(R.id.edit_girl);
        edit_boy = findViewById(R.id.edit_boy);
        edit_style = findViewById(R.id.edit_style);

        edit_head.setOnClickListener(this);
        edit_girl.setOnClickListener(this);
        edit_boy.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.edit_head:
                break;
            case R.id.edit_girl:
                break;
            case R.id.edit_boy:
                break;
        }
    }
}
