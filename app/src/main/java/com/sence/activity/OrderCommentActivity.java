package com.sence.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.orhanobut.logger.Logger;
import com.sence.R;
import com.sence.base.BaseActivity;
import com.sence.bean.request.RImageListBean;
import com.sence.bean.request.ROrderCommentBean;
import com.sence.net.HttpCode;
import com.sence.net.HttpManager;
import com.sence.net.manager.ApiCallBack;
import com.sence.utils.GlideUtils;
import com.sence.utils.LoginStatus;
import com.sence.utils.StatusBarUtil;
import com.sence.view.PubTitle;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 订单评论
 */
public class OrderCommentActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.iv_shopimg_shopcomment)
    ImageView ivShopimgShopcomment;
    @BindView(R.id.iv_goodimg_shopcomment)
    ImageView ivGoodimgShopcomment;
    @BindView(R.id.tv_goodcomment_shopcomment)
    TextView tvGoodcommentShopcomment;
    @BindView(R.id.ll_good_shopcomment)
    LinearLayout llGoodShopcomment;
    @BindView(R.id.iv_centreimg_shopcomment)
    ImageView ivCentreimgShopcomment;
    @BindView(R.id.tv_centrecomment_shopcomment)
    TextView tvCentrecommentShopcomment;
    @BindView(R.id.ll_centre_shopcomment)
    LinearLayout llCentreShopcomment;
    @BindView(R.id.iv_gapimg_shopcomment)
    ImageView ivGapimgShopcomment;
    @BindView(R.id.tv_gapcomment_shopcomment)
    TextView tvGapcommentShopcomment;
    @BindView(R.id.ll_gap_shopcomment)
    LinearLayout llGapShopcomment;
    @BindView(R.id.et_content_shopcomment)
    EditText etContentShopcomment;
    @BindView(R.id.iv_imgone_shopcomment)
    ImageView ivImgoneShopcomment;
    @BindView(R.id.iv_closeone_shopcomment)
    ImageView ivCloseoneShopcomment;
    @BindView(R.id.iv_imgtwe_shopcomment)
    ImageView ivImgtweShopcomment;
    @BindView(R.id.iv_closetwe_shopcomment)
    ImageView ivClosetweShopcomment;
    @BindView(R.id.iv_imgthress_shopcomment)
    ImageView ivImgthressShopcomment;
    @BindView(R.id.iv_closethress_shopcomment)
    ImageView ivClosethressShopcomment;
    @BindView(R.id.pt_tablayout)
    PubTitle ptTablayout;
    private String url;
    private List<LocalMedia> selectList = new ArrayList<>();
    private BottomSheetDialog mBottomSheetDialog;
    private String star = "1";
    private static String path = "/sdcard/myHead/";// sd路径
    private File[] files;
    private String id;

    @Override
    public int onActLayout() {
        return R.layout.activity_shopcomment;
    }

    @Override
    public void initView() {
        StatusBarUtil.setLightMode(this);
        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        id = intent.getStringExtra("id");
        GlideUtils.getInstance().loadNormal(url, ivShopimgShopcomment);
        View view = View.inflate(this, R.layout.bottom_dialog, null);
        TextView mTakePhoto = (TextView) view.findViewById(R.id.tv_takephoto);
        TextView mPhoto = (TextView) view.findViewById(R.id.tv_photo);
        TextView mCancel = (TextView) view.findViewById(R.id.tv_cancel);
        mTakePhoto.setOnClickListener(this);
        mPhoto.setOnClickListener(this);
        mCancel.setOnClickListener(this);
        mBottomSheetDialog = new BottomSheetDialog(this);
        mBottomSheetDialog.setContentView(view);
        ptTablayout.setRightOnClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doHttp();
            }
        });
    }

    public void initData() {
//        String content = mContent.getText().toString().trim();
//        if (content.length() < 5) {
//            ToastUtils.showShort("请您至少输入5个字");
//            return;
//        }
    }

    private void doHttp() {
        if (selectList.size() == 0) {
            files = null;
        } else {
            files = new File[selectList.size()];
            for (int i = 0; i < selectList.size(); i++) {
                Log.i("aaa", selectList.get(i).getCompressPath());
                files[i] = new File(selectList.get(i).getCompressPath());
            }
        }
        final String content = etContentShopcomment.getText().toString();
        if (TextUtils.isEmpty(content)) {
            ToastUtils.showShort("亲，你不准备说点什么吗？");
            return;
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                uploadImg(content, files);
            }
        }).start();


    }

    public void uploadImg(String content, File[] imgs) {
        HttpManager.getInstance().PlayNetCode(HttpCode.COMMENT_ADD, new ROrderCommentBean(LoginStatus.getUid(), star, content, id
        ), new RImageListBean(imgs)).request(new ApiCallBack<String>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void Message(int code, String message) {

            }

            @Override
            public void onSuccess(String o, String msg) {
                Logger.e("msg==========" + msg);
                ToastUtils.showShort(msg);
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片、视频、音频选择结果回调
                    List<LocalMedia> localMedia = PictureSelector.obtainMultipleResult(data);
                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true  注意：音视频除外
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true  注意：音视频除外
                    // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
                    selectList.clear();
                    selectList.addAll(localMedia);
                    imgAss();
//                    adapter.setList(selectList);
//                    adapter.notifyDataSetChanged();
                    break;
                case PictureConfig.REQUEST_CAMERA:
                    List<LocalMedia> localMediat = PictureSelector.obtainMultipleResult(data);
                    if (selectList.size() == 3) {
                        selectList.remove(2);
                    }
                    selectList.addAll(localMediat);
                    imgAss();

                    break;
            }
        }
    }

    private void imgAss() {
        if (selectList.size() == 0) {
            ivImgoneShopcomment.setImageResource(R.drawable.comment_tianjia);
            ivImgtweShopcomment.setVisibility(View.GONE);
            ivImgthressShopcomment.setVisibility(View.GONE);
            ivCloseoneShopcomment.setVisibility(View.GONE);
            ivClosetweShopcomment.setVisibility(View.GONE);
            ivClosethressShopcomment.setVisibility(View.GONE);
        } else if (selectList.size() == 1) {
            ivImgtweShopcomment.setVisibility(View.VISIBLE);
            ivImgtweShopcomment.setImageResource(R.drawable.comment_yi);
            ivImgthressShopcomment.setVisibility(View.GONE);
            Glide.with(OrderCommentActivity.this).load(selectList.get(0).getPath()).into(ivImgoneShopcomment);
            ivCloseoneShopcomment.setVisibility(View.VISIBLE);
            ivClosetweShopcomment.setVisibility(View.GONE);
            ivClosethressShopcomment.setVisibility(View.GONE);
        } else if (selectList.size() == 2) {
            ivImgtweShopcomment.setVisibility(View.VISIBLE);
            ivImgthressShopcomment.setVisibility(View.VISIBLE);
            ivImgthressShopcomment.setImageResource(R.drawable.comment_er);
            Glide.with(OrderCommentActivity.this).load(selectList.get(0).getPath()).into(ivImgoneShopcomment);
            Glide.with(OrderCommentActivity.this).load(selectList.get(1).getPath()).into(ivImgtweShopcomment);
            ivCloseoneShopcomment.setVisibility(View.VISIBLE);
            ivClosetweShopcomment.setVisibility(View.VISIBLE);
            ivClosethressShopcomment.setVisibility(View.GONE);
        } else if (selectList.size() == 3) {
            ivImgtweShopcomment.setVisibility(View.VISIBLE);
            ivImgthressShopcomment.setVisibility(View.VISIBLE);
            Glide.with(OrderCommentActivity.this).load(selectList.get(0).getPath()).into(ivImgoneShopcomment);
            Glide.with(OrderCommentActivity.this).load(selectList.get(1).getPath()).into(ivImgtweShopcomment);
            Glide.with(OrderCommentActivity.this).load(selectList.get(2).getPath()).into(ivImgthressShopcomment);
            ivCloseoneShopcomment.setVisibility(View.VISIBLE);
            ivClosetweShopcomment.setVisibility(View.VISIBLE);
            ivClosethressShopcomment.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_takephoto:
                mBottomSheetDialog.dismiss();
                takePhoto();
                break;
            case R.id.tv_photo:
                mBottomSheetDialog.dismiss();
                photo();
                break;
            case R.id.tv_cancel:
                mBottomSheetDialog.dismiss();
                break;
        }
    }

    private void takePhoto() {
        PictureSelector.create(OrderCommentActivity.this)
                .openCamera(PictureMimeType.ofImage())
                .forResult(PictureConfig.REQUEST_CAMERA);
    }

    private void photo() {
        //从照片选择并裁剪
        PictureSelector.create(OrderCommentActivity.this)
                .openGallery(PictureMimeType.ofAll())//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
//                                        .theme()//主题样式(不设置为默认样式) 也可参考demo values/styles下 例如：R.style.picture.white.style
                .maxSelectNum(3)// 最大图片选择数量 int
                .minSelectNum(1)// 最小选择数量 int
                .imageSpanCount(4)// 每行显示个数 int
                .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                .previewImage(true)// 是否可预览图片 true or false
                .previewVideo(true)// 是否可预览视频 true or false
                .enablePreviewAudio(true) // 是否可播放音频 true or false
                .isCamera(true)// 是否显示拍照按钮 true or false
                .imageFormat(PictureMimeType.PNG)// 拍照保存图片格式后缀,默认jpeg
                .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                .sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                .setOutputCameraPath("/CustomPath")// 自定义拍照保存路径,可不填
                .enableCrop(false)// 是否裁剪 true or false
                .compress(true)// 是否压缩 true or false
//                                        .glideOverride()// int glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                .withAspectRatio(1, 1)// int 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                .hideBottomControls(true)// 是否显示uCrop工具栏，默认不显示 true or false
                .isGif(true)// 是否显示gif图片 true or false
//                                        .compressSavePath(getPath())//压缩图片保存地址
                .freeStyleCropEnabled(true)// 裁剪框是否可拖拽 true or false
                .circleDimmedLayer(false)// 是否圆形裁剪 true or false
                .showCropFrame(true)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false   true or false
//                                        .showCropGrid(true)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false    true or false
                .openClickSound(true)// 是否开启点击声音 true or false
                .selectionMedia(selectList)// 是否传入已选图片 List<LocalMedia> list
                .previewEggs(true)// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中) true or false
//                                        .cropCompressQuality()// 裁剪压缩质量 默认90 int
                .minimumCompressSize(100)// 小于100kb的图片不压缩
                .synOrAsy(true)//同步true或异步false 压缩 默认同步
//                                        .cropWH()// 裁剪宽高比，设置如果大于图片本身宽高则无效 int
                .rotateEnabled(true) // 裁剪是否可旋转图片 true or false
                .scaleEnabled(true)// 裁剪是否可放大缩小图片 true or false
                .videoQuality(0)// 视频录制质量 0 or 1 int
                .videoMaxSecond(15)// 显示多少秒以内的视频or音频也可适用 int
                .videoMinSecond(10)// 显示多少秒以内的视频or音频也可适用 int
                .recordVideoSecond(60)//视频秒数录制 默认60s int
                .isDragFrame(true)// 是否可拖动裁剪框(固定)
                .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code

    }

//    private void doHttp() {
//        HttpManager.getInstance().PlayNetCode(HttpCode.ORDER_COMMENT, new ROrderDetailsBean(LoginStatus.getUid(),star,etContentShopcomment.getText().toString().trim(),)).request(new ApiCallBack<String>() {
//            @Override
//            public void onFinish() {
//            }
//
//            @Override
//            public void Message(int code, String message) {
//
//            }
//
//            @Override
//            public void onSuccess(String o, String msg) {
//                Logger.e("msg==========" + msg);
//            }
//        });
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.ll_good_shopcomment, R.id.ll_centre_shopcomment, R.id.ll_gap_shopcomment, R.id.iv_imgone_shopcomment, R.id.iv_closeone_shopcomment, R.id.iv_imgtwe_shopcomment, R.id.iv_closetwe_shopcomment, R.id.iv_imgthress_shopcomment, R.id.iv_closethress_shopcomment})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_good_shopcomment:
                tvGoodcommentShopcomment.setTextColor(Color.parseColor("#fd5c7a"));
                ivGoodimgShopcomment.setImageResource(R.drawable.comment_haoping);
                tvCentrecommentShopcomment.setTextColor(Color.parseColor("#aaabbb"));
                ivCentreimgShopcomment.setImageResource(R.drawable.comment_cha);
                tvGapcommentShopcomment.setTextColor(Color.parseColor("#aaabbb"));
                ivGapimgShopcomment.setImageResource(R.drawable.comment_cha);
                star = "1";
                break;
            case R.id.ll_centre_shopcomment:
                tvCentrecommentShopcomment.setTextColor(Color.parseColor("#fd5c7a"));
                ivGoodimgShopcomment.setImageResource(R.drawable.comment_hao);
                tvGoodcommentShopcomment.setTextColor(Color.parseColor("#aaabbb"));
                ivCentreimgShopcomment.setImageResource(R.drawable.comment_zhong);
                tvGapcommentShopcomment.setTextColor(Color.parseColor("#aaabbb"));
                ivGapimgShopcomment.setImageResource(R.drawable.comment_cha);
                star = "2";
                break;
            case R.id.ll_gap_shopcomment:
                tvGapcommentShopcomment.setTextColor(Color.parseColor("#fd5c7a"));
                ivGoodimgShopcomment.setImageResource(R.drawable.comment_hao);
                tvCentrecommentShopcomment.setTextColor(Color.parseColor("#aaabbb"));
                ivCentreimgShopcomment.setImageResource(R.drawable.comment_cha);
                tvGoodcommentShopcomment.setTextColor(Color.parseColor("#aaabbb"));
                ivGapimgShopcomment.setImageResource(R.drawable.comment_zhong);
                star = "3";
                break;
            case R.id.iv_imgone_shopcomment:
                mBottomSheetDialog.show();
                break;
            case R.id.iv_closeone_shopcomment:
                selectList.remove(0);
                imgAss();

                break;
            case R.id.iv_imgtwe_shopcomment:
                mBottomSheetDialog.show();
                break;
            case R.id.iv_closetwe_shopcomment:
                selectList.remove(1);
                imgAss();
                break;
            case R.id.iv_imgthress_shopcomment:
                mBottomSheetDialog.show();
                break;
            case R.id.iv_closethress_shopcomment:
                selectList.remove(2);
                imgAss();
                break;
        }
    }
}
