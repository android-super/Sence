package com.sence;

import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.AppUtils;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.sence.activity.AddTagActivity;
import com.sence.activity.CommitTagActivity;
import com.sence.base.BaseActivity;
import com.sence.base.BaseMainFragment;
import com.sence.bean.request.RUpdateAppBean;
import com.sence.bean.response.PUpDataAppInfo;
import com.sence.fragment.BusFragment;
import com.sence.fragment.KindFragment;
import com.sence.fragment.MainFragment;
import com.sence.fragment.UserFragment;
import com.sence.fragment.VipFragment;
import com.sence.net.HttpCode;
import com.sence.net.HttpManager;
import com.sence.net.manager.ApiCallBack;
import com.sence.utils.HProgressDialogUtils;
import com.sence.utils.LoginStatus;
import com.sence.utils.SocketUtils;
import com.sence.utils.StatusBarUtil;
import com.sence.utils.UpdateService;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AlertDialog;
import butterknife.BindView;
import cn.jpush.android.api.JPushInterface;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private static final String APP_NAME = "Sence";

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
    private BaseMainFragment[] fragments;

    private int tag = 0;//底部点击Tag防止多次点击

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
        fragments = new BaseMainFragment[]{mainFragment, vipFragment, kindFragment, busFragment, userFragment};
        mains = new LinearLayout[]{mainHome, mainVip, mainKind, mainBus, mainUser};

        setSelect(0);
//        showUpdateDialog();
//        initUpdataApp();
    }

    private void initUpdataApp() {
        HttpManager.getInstance().PlayNetCode(HttpCode.UPDATE_APP, new RUpdateAppBean("android",
                AppUtils.getAppVersionName())).request(new ApiCallBack<PUpDataAppInfo>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void Message(int code, String message) {

            }

            @Override
            public void onSuccess(PUpDataAppInfo o, String msg) {
                UpdateApp(o);
            }
        });
    }

    /**
     * 底部导航点击
     *
     * @param view
     */
    @Override
    public void onClick(View view) {
        if (tag == (int) view.getTag()) {
            return;
        }
        tag = (int) view.getTag();
        setSelect(tag);
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
                            .imageFormat(PictureMimeType.PNG)
                            .forResult(PictureConfig.CHOOSE_REQUEST);
                    break;
                case R.id.sheet_video:
                    PictureSelector.create(MainActivity.this)
                            .openGallery(PictureMimeType.ofVideo())
                            .maxSelectNum(1)
                            .compress(true)
                            .imageFormat(PictureMimeType.PNG)
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
                    getSupportFragmentManager().beginTransaction().show(fragments[i]).commit();
                    fragments[i].onRefresh();
                } else {
                    getSupportFragmentManager().beginTransaction().add(R.id.frame_layout, fragments[i]).show(fragments[i]).commit();
                }
            } else {
                mains[i].setSelected(false);
                if (fragments[i].isAdded()) {
                    getSupportFragmentManager().beginTransaction().hide(fragments[i]).commit();
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
        if (intent != null) {
            int type = intent.getIntExtra("type", 0);
            setSelect(type);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        JPushInterface.setAlias(getApplicationContext(), -1, LoginStatus.getUid());
        for (int i = 0; i < fragments.length; i++) {
            if (fragments[i].isAdded()) {
                fragments[i].onRefresh();
            }
        }
    }

    private void UpdateApp(final PUpDataAppInfo versionBean) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("版本更新");
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setMessage("更新提示：" + versionBean.getContents());
        builder.setPositiveButton("更新", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                updateApp(APP_NAME, versionBean.getLink());
                dialog.dismiss();
            }
        });
        if (versionBean.getUpgrade_type() == 1) {
            builder.setNegativeButton("取消", null);
        }
        builder.setCancelable(false);
        builder.create();
        builder.show();
    }

    /**
     * 检查版本并更新
     */
    private void updateApp(final String appName, final String appUrl) {
        final UpdateService.DownloadCallback downloadCallback = new UpdateService.DownloadCallback() {
            @Override
            public void onStart() {
                HProgressDialogUtils.showHorizontalProgressDialog(MainActivity.this, "下载进度", false);
            }

            @Override
            public void onProgress(float progress, long totalSize) {
                HProgressDialogUtils.setProgress(Math.round(progress));
            }

            @Override
            public void setMax(long totalSize) {

            }

            @Override
            public boolean onFinish(File file) {
                HProgressDialogUtils.cancel();
                return false;
            }

            @Override
            public void onError(String msg) {
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                HProgressDialogUtils.cancel();
            }
        };

        UpdateService.bindService(getApplicationContext(), new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                ((UpdateService.DownloadBinder) service).start(downloadCallback, appName, appUrl);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        });
    }

    public void showUpdateDialog(){
        View update_dialog = LayoutInflater.from(this).inflate(R.layout.update_dialog,null);
        TextView update_content = update_dialog.findViewById(R.id.update_content);
        Button update_download = update_dialog.findViewById(R.id.update_download);
        ImageView update_close = update_dialog.findViewById(R.id.update_close);
        AlertDialog dialog  = new AlertDialog.Builder(this,R.style.update_alert_dialog).create();
        dialog.setView(update_dialog);
        update_content.setText("1、我爱中国"+"\n" + "2、更新内容提示，我爱你大爷，你大爷爱我，我是你大爷，你大爷是我，我是你最爱的人你最爱的认识我"+"\n" + "3、测试时最好的");
        update_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        update_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        dialog.show();
        dialog.setCancelable(false);
    }

}
