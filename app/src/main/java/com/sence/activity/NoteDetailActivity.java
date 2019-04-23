package com.sence.activity;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.sence.R;
import com.sence.adapter.CommentAdapter;
import com.sence.adapter.NoteRecommendAdapter;
import com.sence.adapter.pager.ViewShowTagPagerAdapter;
import com.sence.adapter.pager.ViewTagPagerAdapter;
import com.sence.base.BaseActivity;
import com.sence.bean.request.RCommentDetailBean;
import com.sence.bean.request.RCommentListBean;
import com.sence.bean.request.RCommentSupportBean;
import com.sence.bean.request.RNoteDetailBean;
import com.sence.bean.response.PCommentBean;
import com.sence.bean.response.PNoteDetailBean;
import com.sence.fragment.tag.ShowTagFragment;
import com.sence.fragment.tag.TagFragment;
import com.sence.net.HttpCode;
import com.sence.net.HttpManager;
import com.sence.net.manager.ApiCallBack;
import com.sence.utils.GlideUtils;
import com.sence.utils.LoginStatus;
import com.sence.utils.StatusBarUtil;
import com.sence.utils.TagUtils;
import com.sence.view.GridSpacingItemDecoration;
import com.sence.view.NiceImageView;

import java.util.List;

/**
 * 笔记详情
 */
public class NoteDetailActivity extends BaseActivity implements OnItemClickListener,
        ViewPager.OnPageChangeListener, View.OnClickListener {
    @BindView(R.id.view_pager)
    ViewPager noteBanner;
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

    private String nid;
    private int page = 1;

    private NoteRecommendAdapter adapter;
    private CommentAdapter commentAdapter;

    private BottomSheetDialog commentSheet;
    private EditText comment_release;
    private TextView comment_title;
    private TextView comment_num;

    private String content;
    private int msg_page = 1;
    private String p_uid = LoginStatus.getUid();

    private ViewShowTagPagerAdapter tagAdapter;

    @Override
    public int onActLayout() {
        return R.layout.activity_note_detail;
    }

    private void initDataView(PNoteDetailBean o) {
        if (o == null) {
            return;
        }
        GlideUtils.getInstance().loadHead(o.getNote_info().getAvatar(), noteHead);
        noteName.setText(o.getNote_info().getNick_name());
        noteSupport.setText(o.getNote_info().getPraise_count());
        noteComment.setText(o.getNote_info().getMessage_count());
        noteLook.setText(o.getNote_info().getClick_num());
        noteContent.setText(o.getNote_info().getContent());

        initViewPager(o.getNote_info().getAlbums());
    }

    public void initView() {
        StatusBarUtil.setTranslucentForCoordinatorLayout(this, 0);
        StatusBarUtil.setLightMode(this);
        nid = this.getIntent().getStringExtra("nid");
        initAppBarLayout();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        noteRecycle.setLayoutManager(gridLayoutManager);
        GridSpacingItemDecoration gridSpacingItemDecoration = new GridSpacingItemDecoration(2, ConvertUtils.dp2px(10),
                true);
        noteRecycle.addItemDecoration(gridSpacingItemDecoration);
        adapter = new NoteRecommendAdapter(R.layout.rv_item_note);
        noteRecycle.setAdapter(adapter);
        noteRecycle.setNestedScrollingEnabled(false);

        noteCommentRelease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCommentDialog();
            }
        });
        noteBanner.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    private void initViewPager(List<PNoteDetailBean.NoteInfoBean.AlbumsBean> albums) {
        Log.e("TAG", albums.size() + "");
        tagAdapter = new ViewShowTagPagerAdapter(getSupportFragmentManager());
        for (int i = 0; i < albums.size(); i++) {
            Log.e("TAG", albums.get(i).getAlbum_url() + "");
            tagAdapter.addFragment(ShowTagFragment.newInstance(albums.get(i).getAlbum_url(), i,
                    albums.get(i).getTags()),
                    i + "");
        }
        noteBanner.setAdapter(tagAdapter);
        noteBanner.setOffscreenPageLimit(albums.size());
        Log.e("TAG", "执行到这里了吗");
    }

    /**
     * banner点击事件
     *
     * @param position
     */
    @Override
    public void onItemClick(int position) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int index) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

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
            }
        });
        toolBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.finishAfterTransition(NoteDetailActivity.this);
            }
        });
    }

    public void showCommentDialog() {
        getMsgList();
        commentSheet = new BottomSheetDialog(this);
        View view = LayoutInflater.from(this).inflate(R.layout.bottom_sheet_comment, null);
        RecyclerView recycle_view = view.findViewById(R.id.recycle_view);
        recycle_view.setLayoutManager(new LinearLayoutManager(this));
        comment_release = view.findViewById(R.id.comment_release);
        comment_title = view.findViewById(R.id.comment_title);
        comment_num = view.findViewById(R.id.comment_num);
        ImageView comment_close = view.findViewById(R.id.comment_close);
        commentAdapter = new CommentAdapter(R.layout.rv_item_comment);
        recycle_view.setAdapter(commentAdapter);
        commentSheet.setContentView(view);
        commentAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int position) {
                p_uid = commentAdapter.getData().get(position).getId();
            }
        });
        commentAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                CommentSupport(commentAdapter.getData().get(position).getId(), position);
            }
        });
        comment_release.setOnClickListener(this);
        comment_close.setOnClickListener(this);
        commentSheet.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
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
        }
    }

    public void dismissDialog(BottomSheetDialog bottomSheetDialog) {
        if (bottomSheetDialog != null && bottomSheetDialog.isShowing()) {
            bottomSheetDialog.dismiss();
        }
    }

    /**
     * 获取评论列表
     */
    private void getMsgList() {
        HttpManager.getInstance().PlayNetCode(HttpCode.COMMENT_LIST,
                new RCommentListBean(LoginStatus.getUid(), nid, "2", msg_page + "")).request(new ApiCallBack<List<PCommentBean>>() {
            @Override
            public void onFinish() {
                comment_title.setText(commentAdapter.getData().size() + "条评论");
                comment_num.setText("(" + commentAdapter.getData().size() + ")");
            }

            @Override
            public void Message(int code, String message) {

            }

            @Override
            public void onSuccess(List<PCommentBean> o, String msg) {
                if (msg_page == 1) {
                    commentAdapter.setNewData(o);
                } else {
                    commentAdapter.addData(o);
                }

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
}
