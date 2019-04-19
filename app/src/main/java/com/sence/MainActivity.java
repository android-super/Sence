package com.sence;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import butterknife.BindView;
import cn.jpush.android.api.JPushInterface;
import com.blankj.utilcode.util.SPUtils;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.sence.activity.AddTagActivity;
import com.sence.activity.CommitTagActivity;
import com.sence.base.BaseActivity;
import com.sence.fragment.*;
import com.sence.net.HttpCode;
import com.sence.net.HttpManager;
import com.sence.net.manager.ApiCallBack;
import com.sence.utils.LoginStatus;
import com.sence.utils.SocketUtils;
import com.sence.utils.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    public static final int bus_code = 3;

    @BindView(R.id.main_home)
    LinearLayout mainHome;
    @BindView(R.id.main_vip)
    LinearLayout mainVip;
    @BindView(R.id.main_kind)
    LinearLayout mainKind;
    @BindView(R.id.main_bus)
    LinearLayout mainBus;
    @BindView(R.id.main_user)
    LinearLayout mainUser;

    public RelativeLayout sheet_content;
    private TextView sheet_img, sheet_video;
    private ImageView sheet_close;
    private List<LocalMedia> localMedia;

    private LinearLayout[] mains;
    private MainFragment mainFragment;
    private VipFragment vipFragment;
    private KindFragment kindFragment;
    private BusFragment busFragment;
    private UserFragment userFragment;
    private Fragment[] fragments;


    @Override
    public int onActLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        StatusBarUtil.setLightMode(this);
        SocketUtils.getInstance().startSocket();

        sheet_content = findViewById(R.id.sheet_content);
        sheet_img = findViewById(R.id.sheet_img);
        sheet_video = findViewById(R.id.sheet_video);
        sheet_close = findViewById(R.id.sheet_close);

        mainHome.setTag(0);
        mainVip.setTag(1);
        mainKind.setTag(2);
        mainBus.setTag(3);
        mainUser.setTag(4);
        mainHome.setOnClickListener(this);
        mainVip.setOnClickListener(this);
        mainKind.setOnClickListener(this);
        mainBus.setOnClickListener(this);
        mainUser.setOnClickListener(this);

        sheet_content.setVisibility(View.GONE);
        sheet_img.setOnClickListener(OnClick_Release);
        sheet_video.setOnClickListener(OnClick_Release);
        sheet_close.setOnClickListener(OnClick_Release);
        sheet_content.setOnClickListener(OnClick_Release);

        mainFragment = new MainFragment();
        vipFragment = new VipFragment();
        kindFragment = new KindFragment();
        busFragment = new BusFragment();
        userFragment = new UserFragment();
        fragments = new Fragment[]{mainFragment, vipFragment, kindFragment, busFragment, userFragment};
        mains = new LinearLayout[]{mainHome, mainVip, mainKind, mainBus, mainUser};

        setSelect(0);
    }

    /**
     * 底部导航点击
     *
     * @param view
     */
    @Override
    public void onClick(View view) {
        setSelect((Integer) view.getTag());
    }

    /**
     * 发布点击
     */
    private View.OnClickListener OnClick_Release = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.sheet_img:
                    PictureSelector.create(MainActivity.this)
                            .openGallery(PictureMimeType.ofImage())
                            .maxSelectNum(9)
                            .compress(true)
                            .forResult(PictureConfig.CHOOSE_REQUEST);
                    break;
                case R.id.sheet_video:
                    PictureSelector.create(MainActivity.this)
                            .openGallery(PictureMimeType.ofVideo())
                            .maxSelectNum(1)
                            .compress(true)
                            .forResult(PictureConfig.REQUEST_CAMERA);
                    break;
                case R.id.sheet_close:
                    sheet_content.setVisibility(View.GONE);
                    break;
                case R.id.sheet_content:
                    sheet_content.setVisibility(View.GONE);
                    return;
            }
        }
    };


    public void setSelect(int position) {
        for (int i = 0; i < mains.length; i++) {
            if (i == position) {
                mains[i].setSelected(true);
                if (fragments[i].isAdded()) {
                    getSupportFragmentManager().beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).show(fragments[i]).commit();
                } else {
                    getSupportFragmentManager().beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).add(R.id.frame_layout, fragments[i]).show(fragments[i]).commit();
                }
            } else {
                mains[i].setSelected(false);
                if (fragments[i].isAdded()) {
                    getSupportFragmentManager().beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).hide(fragments[i]).commit();
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SocketUtils.getInstance().stopSocket();
    }

    @Override
    public void onActivityResult(int req, int res, Intent data) {
        if (res == RESULT_OK) {
            localMedia = PictureSelector.obtainMultipleResult(data);
            switch (req) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片、视频、音频选择结果回调
                    handler.sendEmptyMessage(0);
                    break;
                case PictureConfig.REQUEST_CAMERA:
                    handler.sendEmptyMessage(1);
                    break;
            }
        }
    }

    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                Intent intent = new Intent(MainActivity.this, AddTagActivity.class);
                intent.putParcelableArrayListExtra("data", (ArrayList<? extends Parcelable>) localMedia);
                startActivity(intent);
            }
            if (msg.what == 1) {
                Intent intent = new Intent(MainActivity.this, CommitTagActivity.class);
                intent.putParcelableArrayListExtra("data", (ArrayList<? extends Parcelable>) localMedia);
                intent.putExtra("is_video", true);
                startActivity(intent);
            }
        }
    };

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        int type = intent.getIntExtra("type", 0);
        setSelect(type);
    }

    @Override
    protected void onResume() {
        super.onResume();
        JPushInterface.setAlias(getApplicationContext(), -1, LoginStatus.getUid());
    }
}
