package com.sence.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.luck.picture.lib.entity.LocalMedia;
import com.sence.R;
import com.sence.base.BaseActivity;
import com.sence.bean.request.RNoteAddImgBean;
import com.sence.bean.request.RNoteAddVideoBean;
import com.sence.bean.request.RNoteFileAddBean;
import com.sence.net.HttpCode;
import com.sence.net.HttpManager;
import com.sence.net.manager.ApiCallBack;
import com.sence.utils.LoginStatus;

import java.io.File;
import java.util.List;

public class CommitTagActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.tag_back)
    ImageView tagBack;
    @BindView(R.id.tag_title)
    TextView tagTitle;
    @BindView(R.id.tag_next)
    TextView tagNext;
    @BindView(R.id.recycle_view)
    RecyclerView recycleView;
    @BindView(R.id.tag_content)
    EditText tagContent;

    private String uid;
    private String type;
    private String content;
    private String width;
    private String height;

    private String video_path;
    private List<LocalMedia> img_paths;

    private File video;
    private File[] imgs;

    @Override
    public int onActLayout() {
        return R.layout.activity_commit_tag;
    }

    @Override
    public void initView() {
        video_path = this.getIntent().getStringExtra("video_path");
        img_paths = this.getIntent().getParcelableArrayListExtra("img_paths");
        width = this.getIntent().getStringExtra("width");
        height = this.getIntent().getStringExtra("height");
        tagBack.setOnClickListener(this);
        tagNext.setOnClickListener(this);
        tagTitle.setText("发表标签");


    }

    public void addTag(boolean is_video) {
        uid = LoginStatus.getUid();
        content = tagContent.getText().toString();
        HttpManager httpManager = null;
        if (is_video) {
            type = "2";
            httpManager.PlayNetCode(HttpCode.NOTE_ADD, new RNoteAddVideoBean(uid, type, content),
                    new RNoteFileAddBean(video, true));
        } else {
            type = "1";
            httpManager.PlayNetCode(HttpCode.NOTE_ADD, new RNoteAddImgBean(uid, type, content, width, height),
                    new RNoteFileAddBean(imgs, false));
        }
        httpManager.request(new ApiCallBack() {
            @Override
            public void onFinish() {

            }

            @Override
            public void Message(int code, String message) {

            }

            @Override
            public void onSuccess(Object o, String msg) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tag_back:
                finish();
                break;
            case R.id.tag_next:
                addTag(true);
                break;
        }
    }
}
