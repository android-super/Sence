package com.sence.activity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import com.orhanobut.logger.Logger;
import com.sence.R;
import com.sence.adapter.ShopCommendAdapter;
import com.sence.base.BaseActivity;
import com.sence.bean.request.RShopCommentBean;
import com.sence.bean.response.PShopCommendBean;
import com.sence.net.HttpCode;
import com.sence.net.HttpManager;
import com.sence.net.manager.ApiCallBack;
import com.sence.utils.LoginStatus;
import com.sence.utils.StatusBarUtil;

import java.util.List;

/**
 * 商品评论
 */
public class ShopCommendActivity extends BaseActivity {
    @BindView(R.id.recycle_shopcommend)
    RecyclerView recycleShopcommend;

    private ShopCommendAdapter mShopCommendAdapter;
    private int page = 1;

    @Override
    public int onActLayout() {
        return R.layout.activity_shopcommend;
    }

    @Override
    public void initView() {
        StatusBarUtil.setLightMode(this);
        mShopCommendAdapter = new ShopCommendAdapter(ShopCommendActivity.this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ShopCommendActivity.this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recycleShopcommend.setLayoutManager(linearLayoutManager);
        recycleShopcommend.setAdapter(mShopCommendAdapter);
    }

    public void initData() {
        HttpManager.getInstance().PlayNetCode(HttpCode.COMMENT_SHOP_LIST, new RShopCommentBean("1", page + "", "10",
                LoginStatus.getUid())).request(new ApiCallBack<List<PShopCommendBean>>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void Message(int code, String message) {

            }

            @Override
            public void onSuccess(List<PShopCommendBean> o, String msg) {
                Logger.e("msg==========" + msg);
                if (o.size() > 0) {
                    mShopCommendAdapter.setList(o);
                }

            }
        });
    }
}
