package com.sence.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.sence.R;
import com.sence.net.Urls;
import com.sence.utils.StatusBarUtil;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
/**
 * 订单评论
 */
public class OrderCommentActivity extends AppCompatActivity {

    private TextView mTitle,mGoodComment,mGapComment,mCentreComment;
    private ImageView mImg,mImgOne,mImgTwe,mImgThress,mImgGood,mImgCentre,mImgGap;
    private String star = null;
    private LinearLayout mGood,mCentre,mGap;
    private String url;
    private TextView mSubmit;
    private EditText mContent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopcomment);
        StatusBarUtil.setLightMode(this);
        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        initData();
    }

    private void initData() {
        mTitle = findViewById(R.id.pub_title);
        mTitle.setText("发表评论");
        ImageView mBack = findViewById(R.id.pub_back);
        mSubmit = findViewById(R.id.pub_right_tv);
        mSubmit.setText("提交");
        mSubmit.setTextColor(Color.parseColor("#66ced1"));
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mContent = findViewById(R.id.et_content_shopcomment);
        mImg = findViewById(R.id.iv_shopimg_shopcomment);
        mImgOne = findViewById(R.id.iv_imgone_shopcomment);
        mImgTwe = findViewById(R.id.iv_imgtwe_shopcomment);
        mImgThress = findViewById(R.id.iv_imgthress_shopcomment);
        mImgGood = findViewById(R.id.iv_goodimg_shopcomment);
        mImgCentre = findViewById(R.id.iv_centreimg_shopcomment);
        mImgGap = findViewById(R.id.iv_gapimg_shopcomment);
        mGoodComment = findViewById(R.id.tv_goodcomment_shopcomment);
        mGapComment = findViewById(R.id.tv_gapcomment_shopcomment);
        mCentreComment = findViewById(R.id.tv_centrecomment_shopcomment);
        mGood = findViewById(R.id.ll_good_shopcomment);
        mCentre = findViewById(R.id.ll_centre_shopcomment);
        mGap = findViewById(R.id.ll_gap_shopcomment);
        mImgOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderCommentActivity.this, PhotoSelectActivity.class);
                intent.putExtra("flag",3);
                startActivity(intent);
            }
        });
        Glide.with(this)
                .load(Urls.base_url + url)
                .placeholder(R.drawable.hint_img)
                .fallback(R.drawable.hint_img)
                .into(mImg);
        mGood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGoodComment.setTextColor(Color.parseColor("#fd5c7a"));
                mImgGood.setImageResource(R.drawable.comment_haoping);
                mCentreComment.setTextColor(Color.parseColor("#aaabbb"));
                mImgCentre.setImageResource(R.drawable.comment_cha);
                mGapComment.setTextColor(Color.parseColor("#aaabbb"));
                mImgGap.setImageResource(R.drawable.comment_cha);
                star="1";
            }
        });
        mCentre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCentreComment.setTextColor(Color.parseColor("#fd5c7a"));
                mImgGood.setImageResource(R.drawable.comment_hao);
                mGoodComment.setTextColor(Color.parseColor("#aaabbb"));
                mImgCentre.setImageResource(R.drawable.comment_zhong);
                mGapComment.setTextColor(Color.parseColor("#aaabbb"));
                mImgGap.setImageResource(R.drawable.comment_cha);
                star="2";
            }
        });
        mGap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGapComment.setTextColor(Color.parseColor("#fd5c7a"));
                mImgGood.setImageResource(R.drawable.comment_hao);
                mCentreComment.setTextColor(Color.parseColor("#aaabbb"));
                mImgCentre.setImageResource(R.drawable.comment_cha);
                mGoodComment.setTextColor(Color.parseColor("#aaabbb"));
                mImgGap.setImageResource(R.drawable.comment_zhong);
                star="3";
            }
        });
        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doHttp();
            }
        });
    }

    private void doHttp() {
        String content = mContent.getText().toString().trim();
        if(content.length()<5){
            ToastUtils.showShort("请您至少输入5个字");
            return;
        }
    }
}
