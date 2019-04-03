package com.sence.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.orhanobut.logger.Logger;
import com.sence.R;
import com.sence.adapter.ServiceDetailsAdapter;
import com.sence.base.BaseActivity;
import com.sence.bean.request.RShopCommendBean;
import com.sence.bean.request.RShopDetailsBean;
import com.sence.bean.response.PServiceCommendBean;
import com.sence.bean.response.PServiceeDetails;
import com.sence.net.HttpCode;
import com.sence.net.HttpManager;
import com.sence.net.Urls;
import com.sence.net.manager.ApiCallBack;
import com.sence.utils.LoginStatus;
import com.sence.utils.StatusBarUtil;
import com.sence.view.NiceImageView;

import java.util.List;

/**
 * 服务详情
 */
public class ServiceDetailsActivity extends BaseActivity {
    @BindView(R.id.iv_img_servicedetails)
    ImageView ivImgServicedetails;
    @BindView(R.id.tv_name_servicedetails)
    TextView tvNameServicedetails;
    @BindView(R.id.tv_address_servicedetails)
    TextView tvAddressServicedetails;
    @BindView(R.id.iv_userimg_servicedetails)
    NiceImageView ivUserimgServicedetails;
    @BindView(R.id.tv_username_servicedetails)
    TextView tvUsernameServicedetails;
    @BindView(R.id.recycle_servicedetails)
    RecyclerView recycleServicedetails;

    private ServiceDetailsAdapter mServiceDetailsAdapter;
    private int page = 1;

    @Override
    public int onActLayout() {
        return R.layout.activity_servicedetails;
    }

    @Override
    public void initView() {
        StatusBarUtil.setLightMode(this);

        mServiceDetailsAdapter = new ServiceDetailsAdapter(ServiceDetailsActivity.this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ServiceDetailsActivity.this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recycleServicedetails.setLayoutManager(linearLayoutManager);
        recycleServicedetails.setAdapter(mServiceDetailsAdapter);
    }

    public void initData() {
        HttpManager.getInstance().PlayNetCode(HttpCode.SERVE_DETAIL, new RShopDetailsBean("1", LoginStatus.getUid())).request(new ApiCallBack<PServiceeDetails>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void Message(int code, String message) {

            }

            @Override
            public void onSuccess(PServiceeDetails o, String msg) {
                Logger.e("msg==========" + msg);
                tvNameServicedetails.setText(o.getTag());
                tvAddressServicedetails.setText(o.getPosition());
                tvUsernameServicedetails.setText(o.getUsername());
                RequestOptions options = new RequestOptions();
                options.placeholder(R.drawable.hint_img);
                Glide.with(ServiceDetailsActivity.this)
                        .load(Urls.base_url + o.getImg())
                        .into(ivImgServicedetails);
                Glide.with(ServiceDetailsActivity.this)
                        .load(Urls.base_url + o.getAvatar())
                        .into(ivUserimgServicedetails);
            }
        });
        HttpManager.getInstance().PlayNetCode(HttpCode.SERVE_COMMENT_LIST,
                new RShopCommendBean("1", page + "", "10")).request(new ApiCallBack<List<PServiceCommendBean>>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void Message(int code, String message) {

            }

            @Override
            public void onSuccess(List<PServiceCommendBean> o, String msg) {
                Logger.e("msg==========" + msg);
                if (o.size() > 0) {
                    mServiceDetailsAdapter.setList(o);
                }

            }
        });
    }
}
