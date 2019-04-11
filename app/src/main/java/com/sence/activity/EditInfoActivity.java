package com.sence.activity;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import com.sence.R;
import com.sence.base.BaseActivity;
import com.sence.bean.request.RUserEditBean;
import com.sence.net.HttpCode;
import com.sence.net.HttpManager;
import com.sence.net.manager.ApiCallBack;
import com.sence.utils.LoginStatus;
import com.sence.utils.StatusBarUtil;
import com.sence.view.NiceImageView;
import com.sence.view.PubTitle;

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
    private String head;
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

                editFinish();
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.edit_head:
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
//        HttpManager.getInstance().PlayNetCode(HttpCode.USER_EDIT,
//                new RUserEditBean(LoginStatus.getUid(), name, style, sex, head)).request(new ApiCallBack() {
//            @Override
//            public void onFinish() {
//
//            }
//
//            @Override
//            public void Message(int code, String message) {
//
//            }
//
//            @Override
//            public void onSuccess(Object o, String msg) {
//
//            }
//        });
    }

}
