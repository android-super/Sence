package com.sence.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.sence.R;
import com.sence.view.PictureTagLayout;
import com.sence.view.PubTitle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * 添加标签
 */
public class AddTagActivity extends AppCompatActivity {
    private ImageView tag_img;
    private PictureTagLayout tag_layout;
    private Bitmap bitmap;
    private int height;
    private int width;

    private PubTitle title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tag);
        title = findViewById(R.id.title);
        title.setRightOnClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showShort("點擊了右側按鈕");
            }
        });
        tag_img = findViewById(R.id.tag_img);
        tag_layout = findViewById(R.id.tag_layout);
        Glide.with(this).asBitmap()
                .load("http://pic29.nipic.com/20130508/12516822_185004197132_2.jpg")
                //强制Glide返回一个Bitmap对象
                .listener(new RequestListener<Bitmap>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target,
                                                boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target,
                                                   DataSource dataSource, boolean isFirstResource) {
                        bitmap = resource;
                        width = resource.getWidth();
                        height = resource.getHeight();
                        handler.sendEmptyMessage(0);
                        return false;
                    }
                }).into(tag_img);
    }

    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) tag_layout.getLayoutParams();
            layoutParams.width = width;
            layoutParams.height = height;
            tag_layout.setLayoutParams(layoutParams);
        }
    };
}
