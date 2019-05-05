package com.sence.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.KeyboardUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMedia;
import com.orhanobut.logger.Logger;
import com.sence.LoginActivity;
import com.sence.R;
import com.sence.activity.web.WebConstans;
import com.sence.adapter.CommentAdapter;
import com.sence.adapter.ContentGoodAdapter;
import com.sence.adapter.GoodsAdapter;
import com.sence.base.BaseActivity;
import com.sence.bean.request.RBusAddBean;
import com.sence.bean.request.RCommentDetailBean;
import com.sence.bean.request.RCommentDetailPidBean;
import com.sence.bean.request.RCommentListBean;
import com.sence.bean.request.RContentDetailBean;
import com.sence.bean.request.RFocusBean;
import com.sence.bean.request.RNidBean;
import com.sence.bean.request.RUidBean;
import com.sence.bean.response.PCommentBean;
import com.sence.bean.response.PContentDetailBean;
import com.sence.fragment.CommentFragment;
import com.sence.net.HttpCode;
import com.sence.net.HttpManager;
import com.sence.net.manager.ApiCallBack;
import com.sence.net.manager.MessageApiCallBack;
import com.sence.utils.GlideUtils;
import com.sence.utils.LoginStatus;
import com.sence.utils.NavigationBarUtil;
import com.sence.utils.StatusBarUtil;
import com.sence.view.DividerGoodSpacingItemDecoration;
import com.sence.view.DividerSpacingItemDecoration;
import com.sence.view.NiceImageView;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import java.util.List;

import androidx.core.app.ActivityCompat;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

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
    @BindView(R.id.content_loading)
    ImageView content_loading;
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
    @BindView(R.id.tool_back_press)
    ImageView toolBackPress;
    @BindView(R.id.tool_share_press)
    ImageView toolSharePress;
    private TextView comment_title;
    private TextView comment_num;
    private String content;

    private String nid;
    private String to_uid;
    private ContentGoodAdapter adapter;
    private PContentDetailBean.NoteInfoBean noteInfoBean;

    private List<PContentDetailBean.NoteInfoBean.GoodsInfoBean> goodsInfoBeans;
    private String p_uid;
    private CommentAdapter commentAdapter;
    private BottomSheetDialog commentSheet;
    private BottomSheetDialog goodSheet;
    private BottomSheetDialog mBottomSheetDialog;

    private CommentFragment commentFragment;

    private int page = 1;
    private boolean isShowKeyBorad = false;
    private String content_img;
    private String pid;

    private void initDataView(PContentDetailBean o) {
        if (o == null) {
            return;
        }
        noteInfoBean = o.getNote_info();
        to_uid = o.getNote_info().getUid();
        p_uid = o.getNote_info().getUid();
        PContentDetailBean.NoteInfoBean noteInfoBean = o.getNote_info();
        toolName.setText(noteInfoBean.getNick_name());
        GlideUtils.getInstance().loadHead(noteInfoBean.getAvatar(), toolHead);
        GlideUtils.getInstance().loadHead(noteInfoBean.getAvatar(), contentHead);
        GlideUtils.getInstance().loadNormal(noteInfoBean.getAlbums().get(0).getAlbum_url(), contentImg);

        content_img = noteInfoBean.getAlbums().get(0).getFull_img_link();
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
        if (NavigationBarUtil.hasNavigationBar(this)) {
            NavigationBarUtil.initActivity(findViewById(android.R.id.content));
        }
        StatusBarUtil.setTranslucentForCoordinatorLayout(this, 0);
        StatusBarUtil.setLightMode(this);
        nid = this.getIntent().getStringExtra("nid");
        initAppBarLayout();

        contentRecycle.setLayoutManager(new LinearLayoutManager(ContentDetailActivity.this));
        adapter = new ContentGoodAdapter(R.layout.rv_item_goods);
        contentRecycle.addItemDecoration(new DividerSpacingItemDecoration(DividerSpacingItemDecoration.VERTICAL,
                ConvertUtils.dp2px(15)));
        contentRecycle.setAdapter(adapter);
        contentRecycle.setNestedScrollingEnabled(false);

        commentFragment = CommentFragment.newInstance(nid, "2");
        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, commentFragment).commit();

        initWebSetting();
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int position) {
                if (TextUtils.isEmpty(LoginStatus.getUid())) {
                    startActivity(new Intent(ContentDetailActivity.this, LoginActivity.class));
                    return;
                }
                addBus(adapter.getData().get(position).getId());
            }
        });

        contentComment.setOnClickListener(this);
        contentHead.setOnClickListener(this);
        toolHead.setOnClickListener(this);
        contentSupportNum.setOnClickListener(this);
        contentSupport.setOnClickListener(this);
        contentBuy.setOnClickListener(this);
        contentFocusTv.setOnClickListener(this);
        contentFocus.setOnClickListener(this);
        toolShare.setOnClickListener(this);
        toolContentFocus.setOnClickListener(this);
        toolFocus.setOnClickListener(this);

        bottomSheetDialog();
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
        content_loading.setVisibility(View.VISIBLE);
        contentWeb.setVisibility(View.GONE);
        contentWeb.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                //页面加载完成之后
                contentWeb.setVisibility(View.VISIBLE);
                content_loading.setVisibility(View.GONE);
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
                if (TextUtils.isEmpty(LoginStatus.getUid())) {
                    startActivity(new Intent(ContentDetailActivity.this, LoginActivity.class));
                    return;
                }
                showCommentDialog(false);
                break;
            case R.id.content_head:
            case R.id.tool_head:
                if (TextUtils.isEmpty(LoginStatus.getUid())) {
                    startActivity(new Intent(ContentDetailActivity.this, LoginActivity.class));
                    return;
                }
                Intent intent = new Intent(this, MyInfoActivity.class);
                intent.putExtra("uid", to_uid);
                startActivity(intent);
                break;
            case R.id.content_support_num:
            case R.id.content_support:
                if (TextUtils.isEmpty(LoginStatus.getUid())) {
                    startActivity(new Intent(ContentDetailActivity.this, LoginActivity.class));
                    return;
                }
                support(nid);
                break;
            case R.id.content_buy:
                if (TextUtils.isEmpty(LoginStatus.getUid())) {
                    startActivity(new Intent(ContentDetailActivity.this, LoginActivity.class));
                    return;
                }
                showGoodDialog();
                break;
            case R.id.tool_content_focus:
            case R.id.content_focus_tv:
                if (TextUtils.isEmpty(LoginStatus.getUid())) {
                    startActivity(new Intent(ContentDetailActivity.this, LoginActivity.class));
                    return;
                }
                toFocus();
                break;
            case R.id.tool_focus:
            case R.id.content_focus:
                if (TextUtils.isEmpty(LoginStatus.getUid())) {
                    startActivity(new Intent(ContentDetailActivity.this, LoginActivity.class));
                    return;
                }
                cancelFocus();
                break;
            case R.id.comment_close:
                dismissDialog(commentSheet);
                break;
            case R.id.tool_share:
            case R.id.tool_share_press:
                if (TextUtils.isEmpty(LoginStatus.getUid())) {
                    startActivity(new Intent(ContentDetailActivity.this, LoginActivity.class));
                    return;
                }
                mBottomSheetDialog.show();
                break;
            case R.id.ll_wei_share:
                shareWeb(ContentDetailActivity.this, WebConstans.buildToken(WebConstans.WZXQ, "nid", nid),
                        contentTitle.getText().toString(),
                        "精品生活，从sence开始",
                        SHARE_MEDIA.WEIXIN,
                        content_img);
                mBottomSheetDialog.dismiss();
                break;
            case R.id.ll_friend_share:
                shareWeb(ContentDetailActivity.this, WebConstans.buildToken(WebConstans.WZXQ, "nid", nid),
                        contentTitle.getText().toString(),
                        "精品生活，从sence开始", SHARE_MEDIA.WEIXIN_CIRCLE, content_img);
                mBottomSheetDialog.dismiss();
                break;
            case R.id.tv_cancel_share:
                mBottomSheetDialog.dismiss();
                break;
            case R.id.ll_report_share:
                intent = new Intent(ContentDetailActivity.this, ReportCauseActivity.class);
                intent.putExtra("type", "2");
                intent.putExtra("gid", nid);
                intent.putExtra("uid", to_uid);
                startActivity(intent);
                mBottomSheetDialog.dismiss();
                break;

        }
    }

    @Override
    public void onActivityResult(int req, int res, Intent data) {
        UMShareAPI.get(this).onActivityResult(req, res, data);
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
                if (Math.abs(i) > (appBarLayout.getTotalScrollRange() / 2)) {
                    float alpha_content = ((float) Math.abs(i) * 2 / appBarLayout.getTotalScrollRange()) - 1;
                    toolShare.setAlpha(alpha_content);
                    toolBack.setAlpha(alpha_content);
                    toolSharePress.setAlpha(0f);
                    toolBackPress.setAlpha(0f);
                } else {
                    toolShare.setAlpha(0f);
                    toolBack.setAlpha(0f);
                    float alpha_content = (float) Math.abs(i) * 2 / appBarLayout.getTotalScrollRange();
                    toolSharePress.setAlpha(Math.abs(1 - alpha_content));
                    toolBackPress.setAlpha(Math.abs(1 - alpha_content));
                }

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
        pid = "";
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
        commentAdapter.setEmptyView(R.layout.empty_comment, recycle_view);
        recycle_view.setAdapter(commentAdapter);
        commentSheet.setContentView(view);
        commentSheet.getDelegate().findViewById(com.google.android.material.R.id.design_bottom_sheet)
                .setBackgroundColor(getResources().getColor(android.R.color.transparent));
        BottomSheetBehavior mBehavior = BottomSheetBehavior.from((View) view.getParent());
        mBehavior.setState(STATE_EXPANDED);
        commentAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int position) {
                pid = commentAdapter.getData().get(position).getId();
                comment_release.setHint("回复" + commentAdapter.getData().get(position).getUser().getNick_name());
                recycle_view.scrollToPosition(commentAdapter.getData().size() - 1);
                showSoftInputFromWindow(comment_release);
            }
        });
        comment_release.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    content = comment_release.getText().toString();
                    if (TextUtils.isEmpty(content)) {
                        ToastUtils.showShort("评论内容不能为空");
                        return false;
                    }
                    addMsg(LoginStatus.getUid(), nid, p_uid, content, "2", pid);
                }
                return false;
            }
        });
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
        TextView bottom_good_num = view.findViewById(R.id.bottom_good_num);
        RecyclerView recycle_view = view.findViewById(R.id.recycle_view);
        final ImageView good_close = view.findViewById(R.id.good_close);
        final GoodsAdapter adapter = new GoodsAdapter(R.layout.rv_item_goods);
        recycle_view.setLayoutManager(new LinearLayoutManager(this));
        recycle_view.setAdapter(adapter);
        recycle_view.addItemDecoration(new DividerGoodSpacingItemDecoration(DividerGoodSpacingItemDecoration.VERTICAL
                , ConvertUtils.dp2px(15)));
        adapter.setNewData(goodsInfoBeans);
        bottom_good_num.setText(goodsInfoBeans.size() + "个商品");
        goodSheet.setContentView(view);
        goodSheet.getDelegate().findViewById(com.google.android.material.R.id.design_bottom_sheet)
                .setBackgroundColor(getResources().getColor(android.R.color.transparent));
        BottomSheetBehavior mBehavior = BottomSheetBehavior.from((View) view.getParent());
        mBehavior.setState(STATE_EXPANDED);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int position) {
                addBus(adapter.getData().get(position).getId());
            }
        });
        good_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goodSheet.dismiss();
            }
        });
        goodSheet.show();
    }

    private void bottomSheetDialog() {
        View mView = View.inflate(this, R.layout.layout_share, null);
        mBottomSheetDialog = new BottomSheetDialog(this);
        mBottomSheetDialog.setContentView(mView);
        mBottomSheetDialog.getDelegate().findViewById(com.google.android.material.R.id.design_bottom_sheet)
                .setBackgroundColor(getResources().getColor(android.R.color.transparent));
        mView.findViewById(R.id.ll_report_share).setOnClickListener(this);
        mView.findViewById(R.id.ll_rachel_share).setVisibility(View.GONE);
        mView.findViewById(R.id.ll_code_share).setVisibility(View.GONE);
        mView.findViewById(R.id.ll_wei_share).setOnClickListener(this);
        mView.findViewById(R.id.ll_friend_share).setOnClickListener(this);
        mView.findViewById(R.id.tv_cancel_share).setOnClickListener(this);
    }

    /**
     * 友盟分享
     * 上下文activity、分享的链接、标题、内容、类型
     * 若是要分享视频、音乐可看官方文档
     */
    public static void shareWeb(final Activity activity, String WebUrl, String title, String description, SHARE_MEDIA
            platform, String img) {

        UMImage thumb = new UMImage(activity, img);
        UMWeb web = new UMWeb(WebUrl);//连接地址(注意链接开头必须包含http)
        web.setTitle(title);//标题
        web.setDescription(description);//描述
        web.setThumb(thumb);//缩略图
        new ShareAction(activity)
                //分享的平台
                .setPlatform(platform)
                .withMedia(web)
                .setCallback(new UMShareListener() {
                    /**
                     * @descrption 分享开始的回调
                     * @param share_media 平台类型
                     */
                    @Override
                    public void onStart(SHARE_MEDIA share_media) {
                    }

                    /**
                     * @descrption 分享成功的回调
                     * @param share_media 平台类型
                     */
                    @Override
                    public void onResult(final SHARE_MEDIA share_media) {
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                addWater();
                                Toast.makeText(activity, " 分享成功 ", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    /**
                     * @descrption 分享失败的回调
                     * @param share_media 平台类型
                     * @param throwable 错误原因
                     */
                    @Override
                    public void onError(final SHARE_MEDIA share_media, final Throwable throwable) {
                        if (throwable != null) {
                            //失败原因
                        }
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(activity, share_media + " 分享失败", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    /**
                     * @descrption 分享取消的回调
                     * @param share_media 平台类型
                     */
                    @Override
                    public void onCancel(final SHARE_MEDIA share_media) {
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(activity, " 分享取消 ", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                })
                .share();
    }

    private static void addWater() {
        HttpManager.getInstance().PlayNetCode(HttpCode.SHARE_ADD_WATER, new RUidBean(LoginStatus.getUid())).request(new ApiCallBack<String>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void Message(int code, String message) {

            }

            @Override
            public void onSuccess(String o, String msg) {
                Logger.e("msg=====" + msg);
                ToastUtils.showShort(msg);
            }
        });
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
                contentFocus.setVisibility(View.VISIBLE);
                contentFocusTv.setVisibility(View.GONE);
                toolContentFocus.setVisibility(View.GONE);
                toolFocus.setVisibility(View.VISIBLE);
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
    public void addMsg(String uid, String rid, String p_uid, String content, String type, String pid) {
        HttpManager httpManager;
        if (TextUtils.isEmpty(pid)) {
            httpManager = HttpManager.getInstance().PlayNetCode(HttpCode.COMMENT_DETAIL_ADD,
                    new RCommentDetailBean(uid, rid, p_uid,
                            content, type));
        } else {
            httpManager = HttpManager.getInstance().PlayNetCode(HttpCode.COMMENT_DETAIL_ADD,
                    new RCommentDetailPidBean(uid
                            , rid, p_uid,
                            content, type, pid));
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
                comment_release.setText("");
                KeyboardUtils.hideSoftInput(comment_release);
                getMsgList();
                commentFragment.initData();
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
                contentComment.setText("评论·" + count);
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

    /**
     * 加入购物车
     */
    private void addBus(String gid) {
        HttpManager.getInstance().PlayNetCode(HttpCode.BUS_ADD, new RBusAddBean(gid, LoginStatus.getUid())).request(new ApiCallBack() {
            @Override
            public void onFinish() {

            }

            @Override
            public void Message(int code, String message) {

            }

            @Override
            public void onSuccess(Object o, String msg) {
                ToastUtils.showShort("成功加入购物车");
            }
        });
    }
    /**
     * 取消关注
     */
    private void cancelFocus() {
        HttpManager.getInstance().PlayNetCode(HttpCode.USER_FOCUS_CANCEL, new RFocusBean(LoginStatus.getUid(),
                to_uid)).request(new ApiCallBack<String>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void Message(int code, String message) {

            }

            @Override
            public void onSuccess(String o, String msg) {
                contentFocus.setVisibility(View.GONE);
                contentFocusTv.setVisibility(View.VISIBLE);
                toolContentFocus.setVisibility(View.VISIBLE);
                toolFocus.setVisibility(View.GONE);
                ToastUtils.showShort(msg);
            }
        });
    }
}
