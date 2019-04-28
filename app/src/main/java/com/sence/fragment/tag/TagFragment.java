package com.sence.fragment.tag;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.ConvertUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.sence.R;
import com.sence.activity.AddTagActivity;
import com.sence.bean.request.tag.RTagInfoItem;
import com.sence.utils.Arith;
import com.sence.utils.TagUtils;
import com.sence.view.PictureTagLayout;

import java.util.ArrayList;
import java.util.List;

public class TagFragment extends Fragment {
    private static final String PATH = "path";
    private static final String POSITION = "position";
    private static final String IS_ADD = "is_add";

    private ImageView tag_img;
    private PictureTagLayout tag_layout;
    private int height;//第一张也就是所以图片高
    private int width;//第一张也就是所以图片宽

    private String path;//图片地址
    private int position;
    private boolean is_add = true;//是否是要新添加也就是新增否則是修改
    private int item_height;//每一张的高
    private int item_width;//每一张的宽


    public int getItem_width() {
        return item_width;
    }

    public int getItem_height() {
        return item_height;
    }

    public String getPath() {
        return path;
    }

    public TagFragment() {

    }

    public static TagFragment newInstance(String path, int position, boolean is_add) {
        TagFragment fragment = new TagFragment();
        Bundle args = new Bundle();
        args.putString(PATH, path);
        args.putInt(POSITION, position);
        args.putBoolean(IS_ADD, is_add);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            path = getArguments().getString(PATH);
            position = getArguments().getInt(POSITION);
            is_add = getArguments().getBoolean(IS_ADD, true);
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
                        item_height = resource.getHeight();
                        item_width = resource.getWidth();
                        Log.e("TAG", width + "===============" + height);
                        handler.sendEmptyMessage(0);
                        return false;
                    }
                }).into(tag_img);
        tag_layout.addOnEditListener(new PictureTagLayout.EditListener() {
            @Override
            public void onEditChanged(boolean isEdit) {
                if (isEdit) {
                    showDescribeDialog();
                }
            }
        });
    }

    /**
     * 获取标签信息
     *
     * @return
     */
    public ArrayList<RTagInfoItem> getTagItemInfo() {
        if (tag_layout == null) {
            return new ArrayList<>();
        }
        return tag_layout.getTagInfoItems();
    }

    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (position == 0) {
                if (TagUtils.width == 0) {
                    TagUtils.width = width;
                }
                if (TagUtils.height == 0) {
                    TagUtils.height = height;
                }
            }
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) tag_layout.getLayoutParams();
            layoutParams.width = width;
            layoutParams.height = height;
            tag_layout.setLayoutParams(layoutParams);
            if (!is_add) {
                int width = ConvertUtils.dp2px(TagUtils.tagInfos.get(position).getWidth());
                int height = ConvertUtils.dp2px(TagUtils.tagInfos.get(position).getHeight());
                tag_layout.setTagInfoItems(TagUtils.tagInfos.get(position).getTagInfoItems(), width, height);
            } else {
                tag_layout.setTagInfoItems(null);
            }

        }
    };

    public void showDescribeDialog() {
        final BottomSheetDialog goodSheet = new BottomSheetDialog(getActivity());
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.bottom_sheet_tag_edit, null);
        final TextView cancel = view.findViewById(R.id.cancel);
        final TextView save = view.findViewById(R.id.save);
        final EditText content = view.findViewById(R.id.content);
        goodSheet.setContentView(view);
        goodSheet.show();
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goodSheet.dismiss();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goodSheet.dismiss();
                tag_layout.setContent(content.getText().toString());
            }
        });
        content.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    save.setEnabled(true);
                    save.setSelected(true);
                } else {
                    save.setEnabled(false);
                    save.setSelected(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
}
