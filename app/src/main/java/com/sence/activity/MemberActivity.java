package com.sence.activity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import com.blankj.utilcode.util.ConvertUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.sence.R;
import com.sence.adapter.MemberAdapter;
import com.sence.base.BaseActivity;
import com.sence.bean.request.RMemberBean;
import com.sence.bean.request.chat.RVidBean;
import com.sence.net.HttpCode;
import com.sence.net.HttpManager;
import com.sence.net.manager.ApiCallBack;
import com.sence.utils.LoginStatus;
import com.sence.utils.StatusBarUtil;
import com.sence.view.GridSpacingItemDecoration;
import com.sence.view.PubTitle;

import java.util.List;

/**
 * V群成员
 */
public class MemberActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.recycle_view)
    RecyclerView recycleView;
    @BindView(R.id.pub_title)
    PubTitle pubTitle;
    @BindView(R.id.member_join)
    TextView memberJoin;

    private MemberAdapter adapter;
    private String v_id;

    @Override
    public int onActLayout() {
        return R.layout.activity_member;
    }

    @Override
    public void initView() {
        StatusBarUtil.setLightMode(this);
        v_id = this.getIntent().getStringExtra("v_id");
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 5);
        recycleView.setLayoutManager(gridLayoutManager);
        recycleView.addItemDecoration(new GridSpacingItemDecoration(5, ConvertUtils.dp2px(20), true));
        adapter = new MemberAdapter(R.layout.rv_item_member);
        recycleView.setAdapter(adapter);

        pubTitle.setRightOnClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Join();
            }
        });
        pubTitle.setRightOnClick(this);
        memberJoin.setOnClickListener(this);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int position) {
                Intent intent = new Intent(MemberActivity.this,MyInfoActivity.class);
                intent.putExtra("uid", adapter.getData().get(position).getUid());
                startActivity(intent);
            }
        });

    }

    @Override
    public void initData() {
        HttpManager.getInstance().PlayNetCode(HttpCode.CHAT_MEMBER_LIST, new RVidBean(LoginStatus.getUid(), v_id)).request(new ApiCallBack<List<RMemberBean>>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void Message(int code, String message) {

            }

            @Override
            public void onSuccess(List<RMemberBean> o, String msg) {
                adapter.setNewData(o);
            }
        });
    }

    /**
     * 加入V群
     */
    public void Join() {
        HttpManager.getInstance().PlayNetCode(HttpCode.CHAT_JOIN, new RVidBean(LoginStatus.getUid(), v_id)).request(new ApiCallBack() {
            @Override
            public void onFinish() {

            }

            @Override
            public void Message(int code, String message) {

            }

            @Override
            public void onSuccess(Object o, String msg) {
                initData();
            }
        });
    }

    @Override
    public void onClick(View v) {
        Join();
    }
}
