package com.sence.activity;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.orhanobut.logger.Logger;
import com.sence.R;
import com.sence.base.BaseActivity;
import com.sence.bean.request.RBindInviteUserBean;
import com.sence.bean.request.RBindingLinkBean;
import com.sence.bean.response.PBindingLinkBean;
import com.sence.net.HttpCode;
import com.sence.net.HttpManager;
import com.sence.net.manager.ApiCallBack;
import com.sence.utils.GlideUtils;
import com.sence.utils.LoginStatus;
import com.sence.utils.StatusBarUtil;
import com.sence.view.NiceImageView;
import com.sence.view.PubTitle;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BindingLinkManActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.rl_head_systeminform)
    PubTitle rlHeadSysteminform;
    @BindView(R.id.iv_img_bindinglinkman)
    ImageView ivImgBindinglinkman;
    @BindView(R.id.ll_bind_bindinglinkman)
    LinearLayout llBindBindinglinkman;
    @BindView(R.id.et_content_bindinglinkman)
    EditText etContentBindinglinkman;
    @BindView(R.id.iv_delete_bindinglinkman)
    ImageView ivDeleteBindinglinkman;
    @BindView(R.id.bt_bind_bindinglinkman)
    Button btBindBindinglinkman;
    @BindView(R.id.ll_notbind_bindinglinkman)
    LinearLayout llNotbindBindinglinkman;
    @BindView(R.id.tv_name_bindlinkman)
    TextView tvNameBindlinkman;
    private String upuid;
    private String inviteUserName;
    private View contentView;
    private PopupWindow popupWindow;
    private NiceImageView mImg;
    private PBindingLinkBean bean;
    private String content;
    private TextView mName;

    @Override
    public int onActLayout() {
        return R.layout.activity_binding_link_man;
    }

    @Override
    public void initView() {
        StatusBarUtil.setLightMode(this);
        upuid = LoginStatus.getUpuid();
        inviteUserName = LoginStatus.getInviteUserName();
        if ("0".equals(upuid)) {
            ivImgBindinglinkman.setImageResource(R.drawable.set_weibangding);
            llBindBindinglinkman.setVisibility(View.GONE);
            llNotbindBindinglinkman.setVisibility(View.VISIBLE);
        } else {
            tvNameBindlinkman.setText(inviteUserName);
            ivImgBindinglinkman.setImageResource(R.drawable.set_yibangding);
            llBindBindinglinkman.setVisibility(View.VISIBLE);
            llNotbindBindinglinkman.setVisibility(View.GONE);
        }
        bottomwindow();
    }

    @Override
    public void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.bt_bind_bindinglinkman)
    public void onViewClicked() {
        content = etContentBindinglinkman.getText().toString().trim();
        if(!TextUtils.isEmpty(content)){
            dohttp();
        }
    }

    private void dohttp() {
        HttpManager.getInstance().PlayNetCode(HttpCode.GET_UPNAME, new RBindingLinkBean(content)).request(new ApiCallBack<PBindingLinkBean>() {
            @Override
            public void onFinish() {
            }

            @Override
            public void Message(int code, String message) {
                if("0".equals(code)){
                    ToastUtils.showShort(message);
                }
            }

            @Override
            public void onSuccess(PBindingLinkBean o, String msg) {
                Logger.e("msg==========" + msg);
                bean = o;
                bgAlpha(0.5f);
                popupWindow.showAtLocation(contentView, Gravity.CENTER, 0, 0);
                mName.setText(bean.getUsername());
                GlideUtils.getInstance().loadHead(bean.getAvatar(), mImg);
            }
        });
    }
    private void bottomwindow() {
        contentView = LayoutInflater.from(BindingLinkManActivity.this).inflate(
                R.layout.layout_bindinglinkman, null);
        popupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);// 取得焦点
        //点击外部消失
        popupWindow.setOutsideTouchable(true);
        //设置可以点击
        popupWindow.setTouchable(true);
        //注意  要是点击外部空白处弹框消息  那么必须给弹框设置一个背景色  不然是不起作用的
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        //进入退出的动画，指定刚才定义的style
        popupWindow.setAnimationStyle(R.style.mypopwindow_anim_style);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                //popupwindow消失时使背景不透明
                bgAlpha(1f);
            }
        });
        mImg = contentView.findViewById(R.id.iv_img_bind);
        mName = contentView.findViewById(R.id.tv_name_bind);
        contentView.findViewById(R.id.tv_cancel_bind).setOnClickListener(this);
        contentView.findViewById(R.id.tv_confirm_bind).setOnClickListener(this);
        // 按下android回退物理键 PopipWindow消失解决
    }
    private void bgAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setAttributes(lp);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_cancel_bind:
                popupWindow.dismiss();
                break;
            case R.id.tv_confirm_bind:
                bind();
                popupWindow.dismiss();
                break;
        }
    }

    private void bind() {
        HttpManager.getInstance().PlayNetCode(HttpCode.GET_INTRODUCER, new RBindInviteUserBean(LoginStatus.getUid(),content)).request(new ApiCallBack<String>() {
            @Override
            public void onFinish() {
            }

            @Override
            public void Message(int code, String message) {

            }

            @Override
            public void onSuccess(String o, String msg) {
                Logger.e("msg==========" + msg);
                ToastUtils.showShort(msg);
                tvNameBindlinkman.setText(inviteUserName);
                ivImgBindinglinkman.setImageResource(R.drawable.set_yibangding);
                llBindBindinglinkman.setVisibility(View.VISIBLE);
                llNotbindBindinglinkman.setVisibility(View.GONE);
            }
        });
    }
}
