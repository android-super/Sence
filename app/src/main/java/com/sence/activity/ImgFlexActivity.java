package com.sence.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.chrisbanes.photoview.OnPhotoTapListener;
import com.github.chrisbanes.photoview.PhotoView;
import com.sence.R;
import com.sence.adapter.ImgFlexAdapter;
import com.sence.utils.GlideUtils;
import com.sence.utils.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class ImgFlexActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private ImgFlexAdapter mImgFlexAdapter;
    private TextView mNum;
    private List<String> listImg = new ArrayList<>();
    private int position = 0;
    private String img = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_img_flex);
        StatusBarUtil.setLightMode(this);
        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        ArrayList<String> imgs = intent.getStringArrayListExtra("imgs");
        img = intent.getStringExtra("img");
        if(TextUtils.isEmpty(img)){
            listImg.addAll(imgs);
        }
        position = intent.getIntExtra("position", 0);
        mViewPager = findViewById(R.id.vp_img_imgflex);
        findViewById(R.id.rl_layout_imgflex).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mNum = findViewById(R.id.tv_num_imgflex);
        mImgFlexAdapter = new ImgFlexAdapter();
        mViewPager.setAdapter(mImgFlexAdapter);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mNum.setText(++position + "/" + listImg.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        if (listImg.size() > 0) {
            mNum.setText(++position +"/"+ listImg.size());
            List<PhotoView> list = new ArrayList<>();
            for (int i = 0; i < listImg.size(); i++) {
                PhotoView photoView = new PhotoView(ImgFlexActivity.this);
                photoView.setOnPhotoTapListener(new OnPhotoTapListener() {
                    @Override
                    public void onPhotoTap(ImageView view, float x, float y) {
                        finish();
                    }
                });
                photoView.setScaleType(ImageView.ScaleType.FIT_XY);
                GlideUtils.getInstance().loadHead( listImg.get(i), photoView);
                list.add(photoView);
            }
            mImgFlexAdapter.setList(list);
            mViewPager.setCurrentItem(--position);
        }else{
            PhotoView photoView = new PhotoView(ImgFlexActivity.this);
            photoView.setOnPhotoTapListener(new OnPhotoTapListener() {
                @Override
                public void onPhotoTap(ImageView view, float x, float y) {
                    finish();
                }
            });
            photoView.setScaleType(ImageView.ScaleType.FIT_XY);
            GlideUtils.getInstance().loadHead( img,photoView);
            List<PhotoView> list = new ArrayList<>();
            list.add(photoView);
            mImgFlexAdapter.setList(list);
            mViewPager.setCurrentItem(position);
        }

    }
}
