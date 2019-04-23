package com.sence.activity;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.KeyboardShortcutInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.webkit.*;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.app.ActivityCompat;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.KeyboardUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.sence.R;
import com.sence.adapter.CommentAdapter;
import com.sence.adapter.ContentGoodAdapter;
import com.sence.adapter.GoodsAdapter;
import com.sence.base.BaseActivity;
import com.sence.bean.request.*;
import com.sence.bean.response.PCommentBean;
import com.sence.bean.response.PContentDetailBean;
import com.sence.fragment.CommentFragment;
import com.sence.net.HttpCode;
import com.sence.net.HttpManager;
import com.sence.net.Urls;
import com.sence.net.manager.ApiCallBack;
import com.sence.net.manager.MessageApiCallBack;
import com.sence.utils.GlideUtils;
import com.sence.utils.LoginStatus;
import com.sence.utils.StatusBarUtil;
import com.sence.view.NiceImageView;

import java.util.List;

import static com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_EXPANDED;

/**
 * 内容详情
 */
public class ContentDetailActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.tool_view)
    View toolView;
    @BindView(R.id.tool_back)
    ImageView toolBack;
    @BindView(R.id.tool_head)
    NiceImageView toolHead;
    @BindView(R.id.tool_name)
    TextView toolName;
    @BindView(R.id.tool_focus)
    ImageView toolFocus;
    @BindView(R.id.tool_content_focus)
    TextView toolContentFocus;
    @BindView(R.id.tool_share)
    ImageView toolShare;
    @BindView(R.id.app_bar_layout)
    AppBarLayout appBarLayout;
    @BindView(R.id.content_head)
    NiceImageView contentHead;
    @BindView(R.id.content_name)
    TextView contentName;
    @BindView(R.id.content_describe)
    TextView contentDescribe;
    @BindView(R.id.content_focus)
    ImageView contentFocus;
    @BindView(R.id.content_focus_tv)
    TextView contentFocusTv;
    @BindView(R.id.content_title)
    TextView contentTitle;
    @BindView(R.id.content_web)
    WebView contentWeb;
    @BindView(R.id.content_recycle)
    RecyclerView contentRecycle;
    @BindView(R.id.content_nested)
    NestedScrollView contentNested;
    @BindView(R.id.content_support)
    ImageView contentSupport;
    @BindView(R.id.content_support_num)
    TextView contentSupportNum;
    @BindView(R.id.content_comment)
    TextView contentComment;
    @BindView(R.id.content_buy)
    TextView contentBuy;
    @BindView(R.id.content_buy_num)
    TextView contentBuyNum;
    @BindView(R.id.tool_title_layout)
    LinearLayout toolTitleLayout;
    @BindView(R.id.content_img)
    ImageView contentImg;

    public EditText comment_release;
    private TextView comment_title;
    private TextView comment_num;
    private String content;

    private String nid;
    private String to_uid;
    private ContentGoodAdapter adapter;
    private PContentDetailBean.NoteInfoBean noteInfoBean;

    private List<PContentDetailBean.NoteInfoBean.GoodsInfoBean> goodsInfoBeans;
    private String p_uid = LoginStatus.getUid();
    private CommentAdapter commentAdapter;
    private BottomSheetDialog commentSheet;
    private BottomSheetDialog goodSheet;

    private int page = 1;
    private boolean isShowKeyBorad = false;

    private void initDataView(PContentDetailBean o) {
        if (o == null) {
            return;
        }
        noteInfoBean = o.getNote_info();
        to_uid = o.getNote_info().getUid();
        PContentDetailBean.NoteInfoBean noteInfoBean = o.getNote_info();
        toolName.setText(noteInfoBean.getNick_name());
        GlideUtils.getInstance().loadHead(noteInfoBean.getAvatar(), toolHead);
        GlideUtils.getInstance().loadHead(noteInfoBean.getAvatar(), contentHead);
        GlideUtils.getInstance().loadNormal(noteInfoBean.getAlbums().get(0).getAlbum_url(), contentImg);
        contentName.setText(noteInfoBean.getNick_name());
        contentDescribe.setText(noteInfoBean.getAutograph());
        contentTitle.setText(noteInfoBean.getTitle());

        if ("1".equals(noteInfoBean.getIs_like())) {
            contentSupport.setImageResource(R.drawable.recommend_dianzan_y);
        } else {
            contentSupport.setImageResource(R.drawable.recommend_dianzan);
        }
        if ("1".equals(noteInfoBean.getIs_focus())) {
            toolFocus.setVisibility(View.VISIBLE);
            toolContentFocus.setVisibility(View.GONE);
            contentFocus.setVisibility(View.VISIBLE);
            contentFocusTv.setVisibility(View.GONE);
        } else {
            toolFocus.setVisibility(View.GONE);
            toolContentFocus.setVisibility(View.VISIBLE);
            contentFocus.setVisibility(View.GONE);
            contentFocusTv.setVisibility(View.VISIBLE);
        }
        contentSupportNum.setText("赞·" + noteInfoBean.getPraise_count());
        contentComment.setText("评论·" + noteInfoBean.getMessage_count());

        contentBuyNum.setText(noteInfoBean.getGoods_info().size() + "");
        goodsInfoBeans = noteInfoBean.getGoods_info();
        adapter.setNewData(noteInfoBean.getGoods_info());
        contentWeb.loadUrl(noteInfoBean.getContent());
    }

    @Override
    public int onActLayout() {
        return R.layout.activity_content_detail;
    }

    public void initData() {
        HttpManager.getInstance().PlayNetCode(HttpCode.CONTENT_DETAIL,
                new RContentDetailBean(LoginStatus.getUid(), nid)).request(new ApiCallBack<PContentDetailBean>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void Message(int code, String message) {

            }

            @Override
            public void onSuccess(PContentDetailBean o, String msg) {
                initDataView(o);
            }
        });
    }


    public void initView() {
        StatusBarUtil.setTranslucentForCoordinatorLayout(this, 0);
        StatusBarUtil.setLightMode(this);
        nid = this.getIntent().getStringExtra("nid");
        initAppBarLayout();

        contentRecycle.setLayoutManager(new LinearLayoutManager(ContentDetailActivity.this));
        adapter = new ContentGoodAdapter(R.layout.rv_item_goods);
        contentRecycle.setAdapter(adapter);
        contentRecycle.setNestedScrollingEnabled(false);

        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, CommentFragment.newInstance(nid,
                "2")).commit();

        initWebSetting();

        contentComment.setOnClickListener(this);
        contentHead.setOnClickListener(this);
        toolHead.setOnClickListener(this);
        contentSupportNum.setOnClickListener(this);
        contentSupport.setOnClickListener(this);
        contentBuy.setOnClickListener(this);
        contentFocusTv.setOnClickListener(this);
        toolShare.setOnClickListener(this);
    }

    private void initWebSetting() {
        WebSettings settings = contentWeb.getSettings();
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setDomStorageEnabled(true);
        settings.setAllowFileAccess(false);
        settings.setAllowFileAccess(false);
        settings.setUseWideViewPort(false);//禁止webview做自动缩放
        settings.setBuiltInZoomControls(false);
        settings.setSupportZoom(false);
        settings.setDisplayZoomControls(false);
        settings.setJavaScriptEnabled(true);
        settings.setAppCacheEnabled(true);
        settings.setSupportMultipleWindows(false);
        settings.setAppCachePath(getDir("cache", Context.MODE_PRIVATE).getPath());
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        contentWeb.setFocusable(true);
        contentWeb.requestFocus();
        contentWeb.setWebChromeClient(new WebChromeClient());  //解决android与H5协议交互,弹不出对话框问题
        contentWeb.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                //页面加载完成之后
            }

            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                contentWeb.loadUrl(url);
                if (url.startsWith("tel:")) {
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(url));
                    startActivity
                            (intent);
                    return true;
                }
                return true;

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.content_comment:
                showCommentDialog(false);
                break;
            case R.id.content_head:
            case R.id.tool_head:
                Intent intent = new Intent(this, MyInfoActivity.class);
                intent.putExtra("uid", to_uid);
                startActivity(intent);
                break;
            case R.id.content_support_num:
            case R.id.content_support:
                support(nid);
                break;
            case R.id.content_buy:
                showGoodDialog();
                break;
            case R.id.content_focus_tv:
                toFocus();
                break;
            case R.id.comment_release:
                content = comment_release.getText().toString();
                if (TextUtils.isEmpty(content)) {
                    ToastUtils.showShort("评论内容不能为空");
                    return;
                }
                addMsg(LoginStatus.getUid(), nid, p_uid, content, "2");
                break;
            case R.id.comment_close:
                dismissDialog(commentSheet);
                break;
            case R.id.tool_share:
                PictureSelector.create(ContentDetailActivity.this)
                        .openGallery(PictureMimeType.ofImage())
                        .maxSelectNum(9)
                        .compress(true)
                        .forResult(PictureConfig.CHOOSE_REQUEST);
                break;

        }
    }

    @Override
    public void onActivityResult(int req, int res, Intent data) {
        if (res == RESULT_OK) {
            switch (req) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片、视频、音频选择结果回调
                    List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                    Intent intent = new Intent(ContentDetailActivity.this, AddTagActivity.class);
                    intent.putExtra("data", (Parcelable) selectList);
                    startActivity(intent);
                    break;
            }
        }
    }


    @TargetApi(Build.VERSION_CODES.M)
    private void initAppBarLayout() {
        toolBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.finishAfterTransition(ContentDetailActivity.this);
            }
        });

        toolView.setAlpha(0);
        toolTitleLayout.setAlpha(0);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                float alpha = (float) Math.abs(i) / appBarLayout.getTotalScrollRange();
                toolView.setAlpha(alpha);
            }
        });
        contentNested.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY > oldScrollY && scrollY > ConvertUtils.dp2px(35)) {
                    float title_alpha = (float) scrollY / (float) ConvertUtils.dp2px(35);
                    toolTitleLayout.setAlpha(title_alpha);
                } else if (scrollY < oldScrollY && scrollY < ConvertUtils.dp2px(35)) {
                    float title_alpha = (float) scrollY / (float) ConvertUtils.dp2px(35);
                    toolTitleLayout.setAlpha(title_alpha);
                }
            }
        });
    }


    public void showCommentDialog(final boolean isShow) {
        isShowKeyBorad = isShow;
        getMsgList();
        commentSheet = new BottomSheetDialog(this);
        View view = LayoutInflater.from(this).inflate(R.layout.bottom_sheet_comment, null);
        final RecyclerView recycle_view = view.findViewById(R.id.recycle_view);
        recycle_view.setLayoutManager(new LinearLayoutManager(this));
        comment_release = view.findViewById(R.id.comment_release);
        comment_title = view.findViewById(R.id.comment_title);
        comment_num = view.findViewById(R.id.comment_num);
        ImageView comment_close = view.findViewById(R.id.comment_close);
        commentAdapter = new CommentAdapter(R.layout.rv_item_comment);
        recycle_view.setAdapter(commentAdapter);
        commentSheet.setContentView(view);
        BottomSheetBehavior mBehavior = BottomSheetBehavior.from((View) view.getParent());
        mBehavior.setState(STATE_EXPANDED);
        commentAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int position) {
                p_uid = commentAdapter.getData().get(position).getId();
                recycle_view.scrollToPosition(commentAdapter.getData().size() - 1);
                showSoftInputFromWindow(comment_release);
            }
        });
        comment_release.setOnClickListener(this);
        comment_close.setOnClickListener(this);
        commentSheet.show();
    }

    public void showSoftInputFromWindow(EditText editText) {
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        //显示软键盘
        //如果上面的代码没有弹出软键盘 可以使用下面另一种方式
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editText, 0);
    }

    public void dismissDialog(BottomSheetDialog bottomSheetDialog) {
        if (bottomSheetDialog != null && bottomSheetDialog.isShowing()) {
            bottomSheetDialog.dismiss();
        }
    }

    public void showGoodDialog() {
        goodSheet = new BottomSheetDialog(this);
        View view = LayoutInflater.from(this).inflate(R.layout.bottom_sheet_goods, null);
        RecyclerView recycle_view = view.findViewById(R.id.recycle_view);
        final GoodsAdapter adapter = new GoodsAdapter(R.layout.rv_item_goods);
        recycle_view.setLayoutManager(new LinearLayoutManager(this));
        recycle_view.setAdapter(adapter);
        adapter.setNewData(goodsInfoBeans);
        goodSheet.setContentView(view);
        BottomSheetBehavior mBehavior = BottomSheetBehavior.from((View) view.getParent());
        mBehavior.setState(STATE_EXPANDED);
        goodSheet.show();

    }

    public void toFocus() {
        HttpManager.getInstance().PlayNetCode(HttpCode.USER_FOCUS, new RFocusBean(LoginStatus.getUid(), to_uid)).request(new ApiCallBack<String>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void Message(int code, String message) {

            }

            @Override
            public void onSuccess(String o, String msg) {
                ToastUtils.showShort(msg);
            }
        });
    }

    /**
     * 点赞
     *
     * @param nid
     */
    public void support(String nid) {
        HttpManager.getInstance().PlayNetCode(HttpCode.SUPPORT_NOTE_RECOMMEND, new RNidBean(LoginStatus.getUid(),
                nid)).request(new ApiCallBack() {
            @Override
            public void onFinish() {

            }

            @Override
            public void Message(int code, String message) {

            }

            @Override
            public void onSuccess(Object o, String msg) {
                int support_num = Integer.parseInt(noteInfoBean.getPraise_count());
                if ("0".equals(noteInfoBean.getIs_like())) {
                    noteInfoBean.setIs_like("1");
                    support_num = support_num + 1;
                    contentSupport.setImageResource(R.drawable.recommend_dianzan_y);
                } else {
                    noteInfoBean.setIs_like("0");
                    support_num = support_num - 1;
                    contentSupport.setImageResource(R.drawable.recommend_dianzan);
                }
                noteInfoBean.setPraise_count(support_num + "");
                contentSupportNum.setText("赞·" + noteInfoBean.getPraise_count());
            }
        });
    }

    /**
     * 添加评论
     *
     * @param uid
     * @param rid
     * @param p_uid
     * @param content
     * @param type
     */
    public void addMsg(String uid, String rid, String p_uid, String content, String type) {
        HttpManager.getInstance().PlayNetCode(HttpCode.COMMENT_DETAIL_ADD, new RCommentDetailBean(uid, rid, p_uid,
                content, type)).request(new ApiCallBack() {
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
     * 获取评论列表
     */
    private void getMsgList() {
        HttpManager.getInstance().PlayNetCode(HttpCode.COMMENT_LIST,
                new RCommentListBean(LoginStatus.getUid(), nid, "2", page + "")).request(new MessageApiCallBack<List<PCommentBean>>() {
            @Override
            public void onSuccessCount(List<PCommentBean> pCommentBeans, String msg, int count) {
                if (page == 1) {
                    commentAdapter.setNewData(pCommentBeans);
                    if (isShowKeyBorad) {
                        showSoftInputFromWindow(comment_release);
                    }
                } else {
                    commentAdapter.addData(pCommentBeans);
                }
                comment_title.setText(count + "条评论");
                comment_num.setText("(" + count + ")");
            }

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
}
