package com.sence.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.tools.PictureFileUtils;
import com.orhanobut.logger.Logger;
import com.sence.MainActivity;
import com.sence.R;
import com.sence.base.BaseActivity;
import com.sence.bean.request.RNoteAddImgBean;
import com.sence.bean.request.RNoteAddVideoBean;
import com.sence.bean.request.RNoteFileAddBean;
import com.sence.bean.request.tag.RTagInfo;
import com.sence.bean.request.tag.RTagInfoItem;
import com.sence.bean.request.tag.RTagMultiItem;
import com.sence.net.HttpCode;
import com.sence.net.HttpManager;
import com.sence.net.manager.ApiCallBack;
import com.sence.utils.*;
import com.sence.view.GridSpacingItemDecoration;
import com.sence.view.SuccinctProgress;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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
    private File video_img;

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
                                .imageFormat(PictureMimeType.PNG)
                                .forResult(PictureConfig.REQUEST_CAMERA);
                    } else {
                        PictureSelector.create(CommitTagActivity.this)
                                .openGallery(PictureMimeType.ofImage())
                                .maxSelectNum(9 - paths.size())
                                .compress(true)
                                .imageFormat(PictureMimeType.PNG)
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
        HttpManager httpManager;
        if (is_video) {
            type = "2";
            width = ConvertUtils.px2dp(adapter.getData().get(0).getLocalMedia().getWidth());
            height = ConvertUtils.px2dp(adapter.getData().get(0).getLocalMedia().getHeight());
            video_img = BitmapUtils.Bitmap2File(getVideoThumb(adapter.getData().get(0).getLocalMedia().getPath()),
                    getPackageName(), 100);
            video = new File(adapter.getData().get(0).getLocalMedia().getPath());
            httpManager = HttpManager.getInstance().PlayNetCode(HttpCode.NOTE_ADD, new RNoteAddVideoBean(uid, type,
                            content, width + "", height + ""),
                    new RNoteFileAddBean(video, video_img, true));
        } else {
            type = "1";
            width = ConvertUtils.px2dp(width);
            height = ConvertUtils.px2dp(height);
            Log.e("TAG", getTagInfo());
            httpManager = HttpManager.getInstance().PlayNetCode(HttpCode.NOTE_ADD, new RNoteAddImgBean(uid, type,
                            content, width + ""
                            , height +
                            "", getTagInfo()),
                    new RNoteFileAddBean(getImages(), false));
        }
        httpManager.request(new ApiCallBack() {
            @Override
            public void onFinish() {
                SuccinctProgress.dismiss();
            }

            @Override
            public void Message(int code, String message) {

            }

            @Override
            public void onSuccess(Object o, String msg) {
                Intent intent = new Intent(CommitTagActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public String getTagInfo() {
        List<RTagInfo> list = new ArrayList<>();
        for (int i = 0; i < TagUtils.tagInfos.size(); i++) {
            RTagInfo rTagInfo = TagUtils.tagInfos.get(i);
            List<RTagInfoItem> rTagInfoItems = rTagInfo.getTagInfoItems();
            ArrayList<RTagInfoItem> newTagInfoItems = new ArrayList<>();
            for (int j = 0; j < rTagInfoItems.size(); j++) {
                if (!TextUtils.isEmpty(rTagInfoItems.get(j).getContent())) {
                    newTagInfoItems.add(rTagInfoItems.get(j));
                }
            }
            rTagInfo.setTagInfoItems(newTagInfoItems);
            list.add(rTagInfo);
        }
        return JsonParseUtil.toJson(list);
    }


    public static Bitmap getVideoThumb(String path) {
        MediaMetadataRetriever media = new MediaMetadataRetriever();
        media.setDataSource(path);
        return media.getFrameAtTime();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tag_back:
                finish();
                break;
            case R.id.tag_next:
                content = tagContent.getText().toString();
                if (TextUtils.isEmpty(content)) {
                    ToastUtils.showShort("分享内容不能为空");
                    return;
                }
                content = filterCharToNormal(content);
                SuccinctProgress.showSuccinctProgress(CommitTagActivity.this, "", SuccinctProgress.THEME_LINE, false,
                        true);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        addTag(is_video);
                    }
                }).start();
                break;
        }
    }

    /**
     * 过滤表情符号
     *
     * @param oldString
     * @return
     */
    public static String filterCharToNormal(String oldString) {
        StringBuilder stringBuilder = new StringBuilder();
        int length = oldString.length();
        for (int i = 0; i < length; i++) {//遍历传入的String的所有字符
            char codePoint = oldString.charAt(i);
            if (//如果当前字符为常规字符,则将该字符拼入StringBuilder
                    ((codePoint >= 0x4e00) && (codePoint <= 0x9fa5)) ||//表示汉字区间
                            ((codePoint >= 0x30) && (codePoint <= 0x39)) ||//表示数字区间
                            ((codePoint >= 0x41) && (codePoint <= 0x5a)) ||//表示大写字母区间
                            ((codePoint >= 0x61) && (codePoint <= 0x7a))) {//小写字母区间
                stringBuilder.append(codePoint);
            } else {//如果当前字符为非常规字符,则忽略掉该字符
            }
        }
        return stringBuilder.toString();
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
                adapter.setNewData(getPaths());
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PictureFileUtils.deleteCacheDirFile(CommitTagActivity.this);
        TagUtils.clear();
    }

}
