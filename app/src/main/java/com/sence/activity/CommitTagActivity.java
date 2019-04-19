package com.sence.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.tools.PictureFileUtils;
import com.orhanobut.logger.Logger;
import com.sence.R;
import com.sence.base.BaseActivity;
import com.sence.bean.request.RNoteAddImgBean;
import com.sence.bean.request.RNoteAddVideoBean;
import com.sence.bean.request.RNoteFileAddBean;
import com.sence.bean.request.tag.RTagInfo;
import com.sence.bean.request.tag.RTagMultiItem;
import com.sence.net.HttpCode;
import com.sence.net.HttpManager;
import com.sence.net.manager.ApiCallBack;
import com.sence.utils.*;
import com.sence.view.GridSpacingItemDecoration;

import java.io.File;
import java.util.ArrayList;
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

    private List<LocalMedia> localMedia;

    private String uid;
    private String type;
    private String content;
    private int width;
    private int height;
    private boolean is_video = false;

    private List<LocalMedia> paths;
    private String tagInfo;
    private List<RTagInfo> tagInfos;

    private TagAdapter adapter;

    private File video;

    @Override
    public int onActLayout() {
        return R.layout.activity_commit_tag;
    }

    @Override
    public void initView() {
        StatusBarUtil.setLightMode(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4);
        recycleView.setLayoutManager(gridLayoutManager);
        recycleView.addItemDecoration(new GridSpacingItemDecoration(4, 5, false));
        adapter = new TagAdapter(null);
        recycleView.setAdapter(adapter);
        tagBack.setOnClickListener(this);
        tagNext.setOnClickListener(this);
        tagTitle.setText("发表标签");
        initImages(this.getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        initImages(intent);
    }

    private void initImages(Intent intent) {
        is_video = intent.getBooleanExtra("is_video", false);
        paths = intent.getParcelableArrayListExtra("data");
        if (!is_video) {
            tagInfo = intent.getStringExtra("tagInfo");
            width = intent.getIntExtra("width", 0);
            height = intent.getIntExtra("height", 0);
            tagInfos = JsonParseUtil.parseStringArray(tagInfo);
        } else {
            getVideoPath(true);
        }
        adapter.setNewData(getPaths());
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int position) {
                if (adapter.getData().get(position).getItemType() == RTagMultiItem.NORMAL) {
                    if (is_video) {
                        PictureSelector.create(CommitTagActivity.this).externalPictureVideo(adapter.getData().get(position).getLocalMedia().getPath());
                    } else {
                        Intent intent = new Intent(CommitTagActivity.this, EditTagActivity.class);
                        startActivity(intent);
                    }
                } else {
                    if (is_video) {
                        PictureSelector.create(CommitTagActivity.this)
                                .openGallery(PictureMimeType.ofVideo())
                                .maxSelectNum(1)
                                .compress(true)
                                .forResult(PictureConfig.REQUEST_CAMERA);
                    } else {
                        PictureSelector.create(CommitTagActivity.this)
                                .openGallery(PictureMimeType.ofImage())
                                .maxSelectNum(9 - paths.size())
                                .compress(true)
                                .forResult(PictureConfig.CHOOSE_REQUEST);
                    }
                }

            }
        });
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int position) {
                if (!is_video) {
                    paths.remove(position);
                    tagInfos.remove(position);
                    TagUtils.localMedia.remove(position);
                    TagUtils.tagInfos.remove(position);
                    adapter.setNewData(getPaths());
                } else {
                    paths.remove(position);
                    adapter.setNewData(getPaths());
                }
            }
        });
    }

    /**
     * 获取Adapter 新数据
     *
     * @return
     */
    public List<RTagMultiItem> getPaths() {
        List<RTagMultiItem> list = new ArrayList<>();
        for (int i = 0; i < paths.size(); i++) {
            RTagMultiItem rTagMultiItem = new RTagMultiItem(RTagMultiItem.NORMAL);
            rTagMultiItem.setLocalMedia(paths.get(i));
            list.add(rTagMultiItem);
        }
        if (is_video) {
            if (list.size() == 0) {
                list.add(new RTagMultiItem(RTagMultiItem.LAST));
            }
        } else {
            if (list.size() < 9) {
                list.add(new RTagMultiItem(RTagMultiItem.LAST));
            }
        }
        return list;
    }

    public List<File> getImages() {
        List<File> files = new ArrayList<>();
        for (int i = 0; i < paths.size(); i++) {
            if (paths.get(i).isCompressed()) {
                File file = new File(paths.get(i).getCompressPath());
                files.add(file);
            }
        }
        return files;
    }

    public void addTag(boolean is_video) {
        uid = LoginStatus.getUid();
        content = tagContent.getText().toString();
        HttpManager httpManager = null;
        if (is_video) {
            type = "2";
            httpManager = HttpManager.getInstance().PlayNetCode(HttpCode.NOTE_ADD, new RNoteAddVideoBean(uid, type,
                            content),
                    new RNoteFileAddBean(video, true));
        } else {
            type = "1";
            Log.e("TAG",tagInfo);
            httpManager = HttpManager.getInstance().PlayNetCode(HttpCode.NOTE_ADD, new RNoteAddImgBean(uid, type,
                            content, width + ""
                            , height +
                            "", tagInfo),
                    new RNoteFileAddBean(getImages(), false));
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

    /**
     * 获取视频文件
     *
     * @param is_out 外部与内部获取
     */
    public void getVideoPath(boolean is_out) {
        if (is_out) {
            video = new File(paths.get(0).getPath());
            Logger.e(video.exists() + "" + video.getAbsolutePath());
        } else {
            paths = localMedia;
            video = new File(localMedia.get(0).getPath());
            Logger.e(video.exists() + "" + video.getAbsolutePath());
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tag_back:
                finish();
                break;
            case R.id.tag_next:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        addTag(is_video);
                    }
                }).start();
                break;
        }
    }


    @Override
    public void onActivityResult(int req, int res, Intent data) {
        if (res == RESULT_OK) {
            localMedia = PictureSelector.obtainMultipleResult(data);
            switch (req) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片、视频、音频选择结果回调
                    handler.sendEmptyMessage(0);
                    break;
                case PictureConfig.REQUEST_CAMERA:
                    handler.sendEmptyMessage(1);
                    break;
            }
        }
    }

    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                Intent intent = new Intent(CommitTagActivity.this, AddTagActivity.class);
                intent.putParcelableArrayListExtra("data", (ArrayList<? extends Parcelable>) localMedia);
                startActivity(intent);
            }
            if (msg.what == 1) {
                getVideoPath(false);
                adapter.setNewData(getPaths());
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PictureFileUtils.deleteCacheDirFile(CommitTagActivity.this);
    }

}
