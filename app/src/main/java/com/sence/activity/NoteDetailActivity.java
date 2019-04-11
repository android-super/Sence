package com.sence.activity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.blankj.utilcode.util.ConvertUtils;
import com.google.android.material.appbar.AppBarLayout;
import com.sence.R;
import com.sence.adapter.NoteRecommendAdapter;
import com.sence.base.BaseActivity;
import com.sence.bean.request.RNoteDetailBean;
import com.sence.bean.response.PNoteDetailBean;
import com.sence.net.HttpCode;
import com.sence.net.HttpManager;
import com.sence.net.manager.ApiCallBack;
import com.sence.utils.GlideUtils;
import com.sence.utils.LoginStatus;
import com.sence.utils.StatusBarUtil;
import com.sence.view.GridSpacingItemDecoration;
import com.sence.view.NiceImageView;

/**
 * 笔记详情
 */
public class NoteDetailActivity extends BaseActivity implements OnItemClickListener,
        ViewPager.OnPageChangeListener {
    @BindView(R.id.note_banner)
    ConvenientBanner noteBanner;
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

        noteBanner.setPages(new CBViewHolderCreator() {
            @Override
            public Object createHolder() {
                return new LocalImageHolderView();
            }
        }, o.getNote_info().getAlbums()).setOnPageChangeListener(this).setOnItemClickListener(this);
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
        noteRecycle.setNestedScrollingEnabled(true);

        noteCommentRelease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NoteDetailActivity.this, AddTagActivity.class));
            }
        });
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


    public class LocalImageHolderView implements Holder<PNoteDetailBean.NoteInfoBean.AlbumsBean> {
        private ImageView banner_tag_img;

        @Override
        public View createView(Context context) {
            View view = LayoutInflater.from(context).inflate(R.layout.banner_tag, null);
            banner_tag_img = view.findViewById(R.id.banner_tag_img);
            return view;
        }

        @Override
        public void UpdateUI(Context context, final int position, PNoteDetailBean.NoteInfoBean.AlbumsBean data) {
            GlideUtils.getInstance().loadNormal(data.getAlbum_url(), banner_tag_img);
        }
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
}
