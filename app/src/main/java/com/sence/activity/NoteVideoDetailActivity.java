package com.sence.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;
import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.KeyboardUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.sence.LoginActivity;
import com.sence.R;
import com.sence.activity.web.WebConstans;
import com.sence.adapter.CommentAdapter;
import com.sence.adapter.NoteRecommendAdapter;
import com.sence.base.BaseActivity;
import com.sence.bean.request.*;
import com.sence.bean.response.PCommentBean;
import com.sence.bean.response.PNoteDetailBean;
import com.sence.net.HttpCode;
import com.sence.net.HttpManager;
import com.sence.net.Urls;
import com.sence.net.manager.ApiCallBack;
import com.sence.net.manager.MessageApiCallBack;
import com.sence.utils.GlideUtils;
import com.sence.utils.LoginStatus;
import com.sence.utils.NavigationBarUtil;
import com.sence.utils.StatusBarUtil;
import com.sence.view.GridStagSpacingItemDecoration;
import com.sence.view.NiceImageView;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.List;

import static com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_EXPANDED;

/**
 * 笔记详情
 */
public class NoteVideoDetailActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.note_video)
    JzvdStd noteVideo;
    @BindView(R.id.tool_view)
    View toolView;
    @BindView(R.id.tool_back)
    ImageView toolBack;
    @BindView(R.id.tool_title)
    TextView toolTitle;
    @BindView(R.id.app_bar_layout)
    AppBarLayout appBarLayout;
    @BindView(R.id.note_head)
    NiceImageView noteHead;
    @BindView(R.id.note_name)
    TextView noteName;
    @BindView(R.id.note_support)
    TextView noteSupport;
    @BindView(R.id.note_look)
    TextView noteLook;
    @BindView(R.id.note_comment)
    TextView noteComment;
    @BindView(R.id.note_content)
    TextView noteContent;
    @BindView(R.id.note_recycle)
    RecyclerView noteRecycle;
    @BindView(R.id.note_comment_release)
    TextView noteCommentRelease;
    @BindView(R.id.tool_back_press)
    ImageView toolBackPress;
    @BindView(R.id.tool_more)
    ImageView toolMore;
    @BindView(R.id.tool_more_press)
    ImageView toolMorePress;

    private boolean isMy = false;
    private String nid;
    private float width;
    private float height;
    private int page = 1;
    private PNoteDetailBean.NoteInfoBean noteInfoBean;

    private NoteRecommendAdapter adapter;
    private CommentAdapter commentAdapter;

    private BottomSheetDialog commentSheet;
    private BottomSheetDialog mBottomSheetDialog;
    private EditText comment_release;
    private TextView comment_title;
    private TextView comment_num;

    private String content;
    private int msg_page = 1;
    private String p_uid;
    private boolean isShowKeyBorad = false;
    private String pid;

    @Override
    public int onActLayout() {
        return R.layout.activity_note_video_detail;
    }

    private void initDataView(PNoteDetailBean o) {
        if (o == null) {
            return;
        }
        noteInfoBean = o.getNote_info();
        p_uid = o.getNote_info().getUid();
        GlideUtils.getInstance().loadHead(o.getNote_info().getAvatar(), noteHead);
        noteName.setText(o.getNote_info().getNick_name());
        noteSupport.setText(o.getNote_info().getPraise_count());
        noteComment.setText(o.getNote_info().getMessage_count());
        noteLook.setText(o.getNote_info().getClick_num());
        noteContent.setText(o.getNote_info().getContent());

        initVideo(o.getNote_info().getAlbums().get(0));
    }

    public void initView() {
        if (NavigationBarUtil.hasNavigationBar(this)) {
            NavigationBarUtil.initActivity(findViewById(android.R.id.content));
        }
        StatusBarUtil.setTranslucentForCoordinatorLayout(this, 0);
        StatusBarUtil.setLightMode(this);
        nid = this.getIntent().getStringExtra("nid");
        width = this.getIntent().getFloatExtra("width", 0);
        height = this.getIntent().getFloatExtra("height", 0);
        isMy = this.getIntent().getBooleanExtra("isMy", false);
        if (isMy) {
            toolMore.setVisibility(View.VISIBLE);
            toolMorePress.setVisibility(View.VISIBLE);
        } else {
            toolMore.setVisibility(View.GONE);
            toolMorePress.setVisibility(View.GONE);
        }
        initAppBarLayout();
        CollapsingToolbarLayout.LayoutParams layoutParams =
                (CollapsingToolbarLayout.LayoutParams) noteVideo.getLayoutParams();
        layoutParams.height = ConvertUtils.dp2px(height);
        StaggeredGridLayoutManager gridLayoutManager = new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL);
        noteRecycle.setLayoutManager(gridLayoutManager);
        GridStagSpacingItemDecoration gridSpacingItemDecoration = new GridStagSpacingItemDecoration(2,
                ConvertUtils.dp2px(4));
        noteRecycle.addItemDecoration(gridSpacingItemDecoration);
        adapter = new NoteRecommendAdapter(R.layout.rv_item_note);
        noteRecycle.setAdapter(adapter);
        noteRecycle.setNestedScrollingEnabled(false);
        noteSupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(LoginStatus.getUid())) {
                    startActivity(new Intent(NoteVideoDetailActivity.this, LoginActivity.class));
                    return;
                }
                support(nid, false, -1);
            }
        });

        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int position) {
                if (TextUtils.isEmpty(LoginStatus.getUid())) {
                    startActivity(new Intent(NoteVideoDetailActivity.this, LoginActivity.class));
                    return;
                }
                support(adapter.getData().get(position).getNid(), true, position);
            }
        });
        noteHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(LoginStatus.getUid())) {
                    startActivity(new Intent(NoteVideoDetailActivity.this, LoginActivity.class));
                    return;
                }
                Intent intent = new Intent(NoteVideoDetailActivity.this, MyInfoActivity.class);
                intent.putExtra("uid", noteInfoBean.getUid());
                startActivity(intent);
            }
        });
        noteComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(LoginStatus.getUid())) {
                    startActivity(new Intent(NoteVideoDetailActivity.this, LoginActivity.class));
                    return;
                }
                showCommentDialog(false);
            }
        });
        noteCommentRelease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(LoginStatus.getUid())) {
                    startActivity(new Intent(NoteVideoDetailActivity.this, LoginActivity.class));
                    return;
                }
                showCommentDialog(false);
            }
        });
        bottomSheetDialog();
    }


    private void initVideo(PNoteDetailBean.NoteInfoBean.AlbumsBean albumsBean) {
        noteVideo.setUp(Urls.base_url + albumsBean.getAlbum_url()
                , "", JzvdStd.SCREEN_NORMAL);
        GlideUtils.getInstance().loadNormal(albumsBean.getImg_url(), noteVideo.thumbImageView);
    }


    public void initData() {
        HttpManager.getInstance().PlayNetCode(HttpCode.NOTE_DETAIL,
                new RNoteDetailBean(LoginStatus.getUid(), nid, page + "")).request(new ApiCallBack<PNoteDetailBean>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void Message(int code, String message) {

            }

            @Override
            public void onSuccess(PNoteDetailBean o, String msg) {
                initDataView(o);
                adapter.setNewData(o.getRecommend_note());
            }
        });
    }


    private void initAppBarLayout() {
        toolView.setAlpha(0);
        toolTitle.setAlpha(0);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                float alpha = (float) Math.abs(i) / appBarLayout.getTotalScrollRange();
                toolView.setAlpha(alpha);
                toolTitle.setAlpha(alpha);
                if (Math.abs(i) > (appBarLayout.getTotalScrollRange() / 2)) {
                    float alpha_content = ((float) Math.abs(i) * 2 / appBarLayout.getTotalScrollRange()) - 1;
                    toolBack.setAlpha(alpha_content);
                    toolBackPress.setAlpha(0f);
                    toolMore.setAlpha(alpha_content);
                    toolMorePress.setAlpha(0f);
                } else {
                    toolBack.setAlpha(0f);
                    toolMore.setAlpha(0f);
                    float alpha_content = (float) Math.abs(i) * 2 / appBarLayout.getTotalScrollRange();
                    toolBackPress.setAlpha(Math.abs(1 - alpha_content));
                    toolMorePress.setAlpha(Math.abs(1 - alpha_content));
                }
            }
        });
        toolBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
//                ActivityCompat.finishAfterTransition(NoteVideoDetailActivity.this);
            }
        });
        toolMore.setOnClickListener(this);
        toolMorePress.setOnClickListener(this);
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
        commentAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                CommentSupport(commentAdapter.getData().get(position).getId(), position);
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
                    addMsg(LoginStatus.getUid(), nid, p_uid, content, "1", pid);
                }
                return false;
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.comment_close:
                dismissDialog(commentSheet);
                break;
            case R.id.tool_more:
            case R.id.tool_more_press:
                if (TextUtils.isEmpty(LoginStatus.getUid())) {
                    startActivity(new Intent(NoteVideoDetailActivity.this, LoginActivity.class));
                    return;
                }
                mBottomSheetDialog.show();
                break;
            case R.id.ll_report_share:
                Intent intent = new Intent(NoteVideoDetailActivity.this, ReportCauseActivity.class);
                intent.putExtra("type", "2");
                intent.putExtra("gid", nid);
                intent.putExtra("uid", p_uid);
                startActivity(intent);
                mBottomSheetDialog.dismiss();
                break;
            case R.id.ll_delete_share:
                noteDelete();
                mBottomSheetDialog.dismiss();
                break;
            case R.id.tv_cancel_share:
                mBottomSheetDialog.dismiss();
                break;
        }
    }

    private void bottomSheetDialog() {
        View mView = View.inflate(this, R.layout.layout_note_delete, null);
        mBottomSheetDialog = new BottomSheetDialog(this);
        mBottomSheetDialog.setContentView(mView);
        mBottomSheetDialog.getDelegate().findViewById(com.google.android.material.R.id.design_bottom_sheet)
                .setBackgroundColor(getResources().getColor(android.R.color.transparent));
        mView.findViewById(R.id.ll_report_share).setOnClickListener(this);
        mView.findViewById(R.id.ll_delete_share).setOnClickListener(this);
        mView.findViewById(R.id.tv_cancel_share).setOnClickListener(this);
    }

    public void dismissDialog(BottomSheetDialog bottomSheetDialog) {
        if (bottomSheetDialog != null && bottomSheetDialog.isShowing()) {
            bottomSheetDialog.dismiss();
        }
    }

    private void noteDelete(){
        HttpManager.getInstance().PlayNetCode(HttpCode.NOTE_DELETE,new RNidBean(LoginStatus.getUid(),nid)).request(new ApiCallBack() {
            @Override
            public void onFinish() {

            }

            @Override
            public void Message(int code, String message) {

            }

            @Override
            public void onSuccess(Object o, String msg){
                finish();
            }
        });
    }

    /**
     * 获取评论列表
     */
    private void getMsgList() {
        HttpManager.getInstance().PlayNetCode(HttpCode.COMMENT_LIST,
                new RCommentListBean(LoginStatus.getUid(), nid, "1", msg_page + "")).request(new MessageApiCallBack<List<PCommentBean>>() {
            @Override
            public void onSuccessCount(List<PCommentBean> pCommentBeans, String msg, int count) {
                if (msg_page == 1) {
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
            }
        });
    }

    /**
     * 点赞
     *
     * @param nid
     */
    public void support(String nid, boolean is_from_list, int position) {
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
                if (is_from_list) {
                    int support_num = Integer.parseInt(adapter.getData().get(position).getPraise_count());
                    if (adapter.getData().get(position).getIs_like().equals("0")) {
                        adapter.getData().get(position).setIs_like("1");
                        support_num = support_num + 1;
                    } else {
                        adapter.getData().get(position).setIs_like("0");
                        support_num = support_num - 1;
                    }
                    adapter.getData().get(position).setPraise_count(support_num + "");
                    adapter.notifyDataSetChanged();
                } else {
                    int support_num = Integer.parseInt(noteInfoBean.getPraise_count());
                    if ("0".equals(noteInfoBean.getIs_like())) {
                        noteInfoBean.setIs_like("1");
                        support_num = support_num + 1;
                        noteSupport.setSelected(true);
                    } else {
                        noteInfoBean.setIs_like("0");
                        support_num = support_num - 1;
                        noteSupport.setSelected(false);
                    }
                    noteInfoBean.setPraise_count(support_num + "");
                    noteSupport.setText(noteInfoBean.getPraise_count());
                }
            }
        });
    }

    /**
     * 评论点赞
     *
     * @param mid
     * @param position
     */
    public void CommentSupport(String mid, final int position) {
        HttpManager.getInstance().PlayNetCode(HttpCode.COMMENT_SUPPORT,
                new RCommentSupportBean(LoginStatus.getUid(), mid)).request(new ApiCallBack() {
            @Override
            public void onFinish() {

            }

            @Override
            public void Message(int code, String message) {

            }

            @Override
            public void onSuccess(Object o, String msg) {
                int support_num = Integer.parseInt(commentAdapter.getData().get(position).getLike_num());
                if (commentAdapter.getData().get(position).getIs_like().equals("0")) {
                    commentAdapter.getData().get(position).setIs_like("1");
                    support_num = support_num + 1;
                } else {
                    commentAdapter.getData().get(position).setIs_like("0");
                    support_num = support_num - 1;
                }
                commentAdapter.getData().get(position).setLike_num(support_num + "");
                commentAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        Jzvd.resetAllVideos();
    }
}
