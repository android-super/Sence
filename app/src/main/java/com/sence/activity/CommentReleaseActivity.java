package com.sence.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.sence.R;
import com.sence.utils.StatusBarUtil;

/**
 * 发布评论
 */
public class CommentReleaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_release);
        StatusBarUtil.setLightMode(this);
    }
}
