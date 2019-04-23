package com.sence.fragment.tag;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.sence.R;
import com.sence.bean.request.tag.RTagInfo;
import com.sence.net.Urls;
import com.sence.utils.JsonParseUtil;
import com.sence.view.PictureShowTagLayout;

import java.util.List;

public class ShowTagFragment extends Fragment {
    private static final String PATH = "path";
    private static final String POSITION = "position";
    private static final String TAG_INFO = "tag_info";

    private ImageView tag_img;
    private PictureShowTagLayout tag_layout;
    private int height;//第一张也就是所以图片高
    private int width;//第一张也就是所以图片宽

    private String path;//图片地址
    private int position;
    private String tagInfos;//展示标签信息

    public String getPath() {
        return path;
    }

    public ShowTagFragment() {

    }

    public static ShowTagFragment newInstance(String path, int position, String tag_info) {
        ShowTagFragment fragment = new ShowTagFragment();
        Bundle args = new Bundle();
        args.putString(PATH, path);
        args.putInt(POSITION, position);
        args.putString(TAG_INFO, tag_info);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            path = getArguments().getString(PATH);
            position = getArguments().getInt(POSITION);
            tagInfos = getArguments().getString(TAG_INFO);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tag_show, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tag_img = getView().findViewById(R.id.tag_img);
        tag_layout = getView().findViewById(R.id.tag_layout);
        Glide.with(this).asBitmap()
                .load(Urls.base_url + path)
                .apply(new RequestOptions().error(R.drawable.shape_loading_error).placeholder(R.drawable.shape_loading_error))
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
            List<RTagInfo> list = JsonParseUtil.parseStringArray(tagInfos);
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) tag_layout.getLayoutParams();
            layoutParams.width = width;
            layoutParams.height = height;
            tag_layout.setLayoutParams(layoutParams);
            tag_layout.setTagInfoItems(list.get(position).getTagInfoItems(), width, height);
        }
    };
}
