package com.sence.fragment.tag;


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
import com.blankj.utilcode.util.ConvertUtils;
import com.sence.R;
import com.sence.bean.request.tag.RTagInfo;
import com.sence.utils.GlideUtils;
import com.sence.utils.JsonParseUtil;
import com.sence.view.PictureShowTagLayout;

import java.util.List;

public class ShowTagFragment extends Fragment {
    private static final String PATH = "path";
    private static final String POSITION = "position";
    private static final String TAG_INFO = "tag_info";

    private ImageView tag_img;
    private PictureShowTagLayout tag_layout;

    private String path;//图片地址
    private int position;
    private String tagInfos;//展示标签信息

    public String getPath() {
        return path;
    }

    public ShowTagFragment() {

    }

    public static ShowTagFragment newInstance(String path, int position, String tag_info, String width, String height) {
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
        List<RTagInfo> list = JsonParseUtil.parseStringArray(tagInfos);
        int real_width = list.get(position).getWidth();
        int real_height = list.get(position).getHeight();
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) tag_img.getLayoutParams();
        layoutParams.width = ConvertUtils.dp2px(real_width);
        layoutParams.height = ConvertUtils.dp2px(real_height);
        tag_img.setLayoutParams(layoutParams);
        GlideUtils.getInstance().loadNormal(path, tag_img);
        RelativeLayout.LayoutParams tag_layoutParams = (RelativeLayout.LayoutParams) tag_layout.getLayoutParams();
        tag_layoutParams.width = ConvertUtils.dp2px(real_width);
        tag_layoutParams.height = ConvertUtils.dp2px(real_height);
        tag_layout.setLayoutParams(tag_layoutParams);
        tag_layout.setTagInfoItems(list.get(position).getTagInfoItems(), ConvertUtils.dp2px(real_width),
                ConvertUtils.dp2px(real_height));
    }
}
