package com.sence.utils;

import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.sence.R;
import com.sence.base.BaseApp;
import com.sence.net.Urls;

/**
 * Created by zwy on 2019/4/9.
 * package_name is com.sence.utils
 * 描述:SenceGit
 */
public class GlideUtils {
    private static GlideUtils instance;

    public static GlideUtils getInstance() {
        if (instance == null) {
            synchronized (GlideUtils.class) {
                if (instance == null) {
                    instance = new GlideUtils();
                }
            }
        }
        return instance;
    }

    private RequestOptions initHeadOption() {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.hint_head).error(R.drawable.hint_head_error);
        return requestOptions;
    }

    private RequestOptions initNormalOption() {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.shape_loading_error).error(R.drawable.shape_loading_error);
        return requestOptions;
    }

    public void loadHead(String url, ImageView imageView) {
        Glide.with(BaseApp.INSTANCE).load(Urls.base_url + url).apply(initHeadOption()).into(imageView);
    }

    public void loadHead(String url, ImageView imageView, boolean isFull) {
        if (isFull) {
            Glide.with(BaseApp.INSTANCE).load(url).apply(initHeadOption()).into(imageView);
        }
    }

    public void loadNormal(String url, ImageView imageView) {
        Glide.with(BaseApp.INSTANCE).load(Urls.base_url + url).apply(initNormalOption()).into(imageView);
    }

    public void loadNormal(String url, ImageView imageView, boolean isFull) {
        if (isFull) {
            Glide.with(BaseApp.INSTANCE).load(url).apply(initNormalOption()).into(imageView);
        }
    }
}
