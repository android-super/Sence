package com.sence.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.sence.R;
import com.sence.base.BaseActivity;
import com.sence.bean.request.RUserEditBean;
import com.sence.bean.request.RUserEditHeadBean;
import com.sence.bean.response.PChatMessageBean;
import com.sence.net.HttpCode;
import com.sence.net.HttpManager;
import com.sence.net.manager.ApiCallBack;
import com.sence.utils.BitmapUtils;
import com.sence.utils.GlideUtils;
import com.sence.utils.LoginStatus;
import com.sence.utils.StatusBarUtil;
import com.sence.view.NiceImageView;
import com.sence.view.PubTitle;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.List;

import butterknife.BindView;

/**
 * 修改个人信息
 */
public class EditInfoActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.edit_head)
    NiceImageView editHead;
    @BindView(R.id.edit_camera)
    ImageView editCamera;
    @BindView(R.id.edit_name)
    EditText editName;
    @BindView(R.id.edit_girl)
    TextView editGirl;
    @BindView(R.id.edit_boy)
    TextView editBoy;
    @BindView(R.id.edit_style)
    EditText editStyle;
    @BindView(R.id.edit_title)
    PubTitle editTitle;

    private String name;
    private File head;
    private String sex;
    private String style;

    @Override
    public int onActLayout() {
        return R.layout.activity_edit_info;
    }

    public void initView() {
        StatusBarUtil.setLightMode(this);

        editHead.setOnClickListener(this);
        editGirl.setOnClickListener(this);
        editBoy.setOnClickListener(this);

        editTitle.setRightOnClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = editName.getText().toString();
                if (editBoy.isSelected()){
                    sex = "男";
                }
                if (editGirl.isSelected()){
                    sex = "女";
                }
                style = editStyle.getText().toString();
                editFinish();
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.edit_head:
                PictureSelector.create(EditInfoActivity.this)
                        .openGallery(PictureMimeType.ofImage())
                        .maxSelectNum(1)
                        .compress(true)
                        .forResult(PictureConfig.CHOOSE_REQUEST);
                break;
            case R.id.edit_girl:
                editGirl.setSelected(true);
                editBoy.setSelected(false);
                break;
            case R.id.edit_boy:
                editGirl.setSelected(false);
                editBoy.setSelected(true);
                break;
        }
    }

    public void editFinish() {
        HttpManager.getInstance().PlayNetCode(HttpCode.USER_EDIT,
                new RUserEditBean(LoginStatus.getUid(), name, style, sex),new RUserEditHeadBean(head)).request(new ApiCallBack() {
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

    @Override
    public void onActivityResult(int req, int res, Intent data) {
        if (res == RESULT_OK) {
            switch (req) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片、视频、音频选择结果回调
                    List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                    if (selectList.get(0).isCompressed()) {
//                        Glide.with(EditInfoActivity.this).load(selectList.get(0).getPath()).into(ivImgoneServicecomment);
                        GlideUtils.getInstance().loadHead(selectList.get(0).getCompressPath(),editHead,true);
                        Bitmap bitmap = BitmapUtils.getSmallBitmap(selectList.get(0).getCompressPath());
                        head = BitmapUtils.Bitmap2File(bitmap, getPackageName(), 100);
                        PChatMessageBean.MessageBean bean = new PChatMessageBean.MessageBean();
                        bean.setContent(selectList.get(0).getCompressPath());
                        bean.setType(2);
                        EventBus.getDefault().post(bean);
                    }
                    break;
            }
        }
    }

}
