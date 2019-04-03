package com.sence.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sence.R;
import com.sence.utils.StatusBarUtil;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
/**
 * 服务评论
 */
public class ServiceCommentActivity extends AppCompatActivity {

    private TextView mTitle,mGoodComment,mGapComment,mCentreComment;
    private ImageView mImg,mImgOne,mImgTwe,mImgThress;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicecomment);
        StatusBarUtil.setLightMode(this);
        initData();
    }

    private void initData() {
        mTitle = findViewById(R.id.pub_title);
        mTitle.setText("服务评价");
        ImageView mBack = findViewById(R.id.pub_back);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mImg = findViewById(R.id.iv_shopimg_shopcomment);
        mImgOne = findViewById(R.id.iv_imgone_shopcomment);
        mImgTwe = findViewById(R.id.iv_imgtwe_shopcomment);
        mImgThress = findViewById(R.id.iv_imgthress_shopcomment);
        mGoodComment = findViewById(R.id.tv_goodcomment_shopcomment);
        mGapComment = findViewById(R.id.tv_gapcomment_shopcomment);
        mCentreComment = findViewById(R.id.tv_centrecomment_shopcomment);
    }
}
