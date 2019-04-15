package com.sence.fragment.tag;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.sence.R;
import com.sence.view.PictureTagLayout;
public class TagFragment extends Fragment {
    private static final String PATH = "path";

    private ImageView tag_img;
    private PictureTagLayout tag_layout;
    private int height;
    private int width;

    private String path;


    public TagFragment() {

    }

    public static TagFragment newInstance(String path) {
        TagFragment fragment = new TagFragment();
        Bundle args = new Bundle();
        args.putString(PATH, path);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            path = getArguments().getString(PATH);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tag, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tag_img = getView().findViewById(R.id.tag_img);
        tag_layout = getView().findViewById(R.id.tag_layout);
        Glide.with(this).asBitmap()
                .load(path)
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
