package com.sence.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.sence.R;
import com.sence.adapter.ServiceDetailsAdapter;
import com.sence.adapter.ShopDetailsImgAdapter;
import com.sence.base.BaseActivity;
import com.sence.bean.request.RShopCommendBean;
import com.sence.bean.request.RShopDetailsBean;
import com.sence.bean.response.PServiceCommendBean;
import com.sence.bean.response.PServiceeDetails;
import com.sence.net.HttpCode;
import com.sence.net.HttpManager;
import com.sence.net.manager.ApiCallBack;
import com.sence.utils.GlideUtils;
import com.sence.utils.LoginStatus;
import com.sence.utils.StatusBarUtil;
import com.sence.view.NiceImageView;
import com.sence.view.PubTitle;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 服务详情
 */
public class ServiceDetailsActivity extends BaseActivity {
    @BindView(R.id.vp_img_servicedetails)
    ViewPager vpImgServicedetails;
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
    @BindView(R.id.pt_tablayout)
    PubTitle ptPubTitle;
    @BindView(R.id.tv_map_servicedetails)
    TextView tvMapServicedetails;
    @BindView(R.id.tv_imgnum_servicedetails)
    TextView tvImgnumServicedetails;

    private ServiceDetailsAdapter mServiceDetailsAdapter;
    private int page = 1;
    private String id = "";
    private PServiceeDetails bean = null;
    private ShopDetailsImgAdapter shopDetailsImgAdapter;

    @Override
    public int onActLayout() {
        return R.layout.activity_servicedetails;
    }

    @Override
    public void initView() {
        StatusBarUtil.setLightMode(this);
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        boolean isSelf = intent.getBooleanExtra("isSelf", true);
        if(!isSelf){
            ptPubTitle.setRightImg(0);
        }
        mServiceDetailsAdapter = new ServiceDetailsAdapter(ServiceDetailsActivity.this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ServiceDetailsActivity.this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recycleServicedetails.setLayoutManager(linearLayoutManager);
        recycleServicedetails.setAdapter(mServiceDetailsAdapter);
        ptPubTitle.setRightOnClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if ("0".equals(bean.getIsEvaluate())) {
//                    return;
//                }
                Intent intent = new Intent(ServiceDetailsActivity.this, ServiceCommentActivity.class);
                intent.putExtra("id", bean.getId());
                startActivity(intent);
            }
        });
        shopDetailsImgAdapter = new ShopDetailsImgAdapter();
        vpImgServicedetails.setAdapter(shopDetailsImgAdapter);
        vpImgServicedetails.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tvImgnumServicedetails.setText(++position+"/"+bean.getImgs().size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void initData() {
        HttpManager.getInstance().PlayNetCode(HttpCode.SERVE_DETAIL, new RShopDetailsBean(id, LoginStatus.getUid())).request(new ApiCallBack<PServiceeDetails>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void Message(int code, String message) {

            }

            @Override
            public void onSuccess(final PServiceeDetails o, String msg) {
                Logger.e("msg==========" + msg + "==" + o.getImgs().size());
                bean = o;
                if(o.getImgs().size()>0){
                    tvImgnumServicedetails.setText("1/"+o.getImgs().size());
                }
                List<ImageView> list = new ArrayList<>();
                for (int i = 0; i < o.getImgs().size(); i++) {
                    ImageView imageView = new ImageView(ServiceDetailsActivity.this);
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    GlideUtils.getInstance().loadHead(o.getImgs().get(i), imageView);
                    list.add(imageView);
                    final int position = i;
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(ServiceDetailsActivity.this, ImgFlexActivity.class);
                            intent.putStringArrayListExtra("imgs", (ArrayList<String>) o.getImgs());
                            intent.putExtra("position", position);
                            startActivity(intent);
                        }
                    });
                }
                shopDetailsImgAdapter.setList(list);

                if ("0".equals(o.getIsEvaluate())) {
                    ptPubTitle.setRightImg(0);
                }
                tvNameServicedetails.setText(o.getTag());
                tvAddressServicedetails.setText(o.getPosition());
                tvUsernameServicedetails.setText(o.getUsername());
                GlideUtils.getInstance().loadHead(o.getAvatar(), ivUserimgServicedetails);
            }
        });
        HttpManager.getInstance().PlayNetCode(HttpCode.SERVE_COMMENT_LIST,
                new RShopCommendBean(id, page + "", "10")).request(new ApiCallBack<List<PServiceCommendBean>>() {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.tv_map_servicedetails)
    public void onViewClicked() {
        Intent intent = new Intent(ServiceDetailsActivity.this, MapActivity.class);
        intent.putExtra("map", bean.getPosition());
        startActivity(intent);
    }
}
