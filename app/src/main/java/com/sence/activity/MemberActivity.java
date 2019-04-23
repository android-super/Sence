package com.sence.activity;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import com.blankj.utilcode.util.ConvertUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.android.material.bottomsheet.BottomSheetDialog;
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
import com.sence.view.NiceImageView;
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
    private String to_uid;

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
        memberJoin.setOnClickListener(this);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int position) {
                to_uid = adapter.getData().get(position).getUid();
                showBottom(adapter.getData().get(position).getAvatar());

            }
        });

    }

    @Override
    public void initData() {
        HttpManager.getInstance().PlayNetCode(HttpCode.CHAT_MEMBER_LIST, new RVidBean(LoginStatus.getUid(), v_id)).request(new ApiCallBack<RMemberBean>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void Message(int code, String message) {

            }

            @Override
            public void onSuccess(RMemberBean o, String msg) {
                adapter.setNewData(o.getList());
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
        switch (v.getId()) {
            case R.id.into_user:
                Intent intent = new Intent(MemberActivity.this, MyInfoActivity.class);
                intent.putExtra("uid", to_uid);
                startActivity(intent);
                break;
            case R.id.into_ban:
                break;
            case R.id.member_join:
                Join();
                break;
        }

    }

    public void showBottom(String avatar) {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        View view = LayoutInflater.from(this).inflate(R.layout.bottom_sheet_member, null);
        bottomSheetDialog.setContentView(view);
        bottomSheetDialog.getDelegate().findViewById(com.google.android.material.R.id.design_bottom_sheet)
                .setBackgroundColor(getResources().getColor(android.R.color.transparent));
        bottomSheetDialog.show();
        NiceImageView into_head = view.findViewById(R.id.into_head);
        TextView into_user = view.findViewById(R.id.into_user);
        TextView into_ban = view.findViewById(R.id.into_ban);
        into_user.setOnClickListener(this);
        into_ban.setOnClickListener(this);
        bottomSheetDialog.show();
    }
}
