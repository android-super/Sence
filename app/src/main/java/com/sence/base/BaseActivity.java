package com.sence.base;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.scwang.smartrefresh.layout.util.DensityUtil;
import com.sence.MainActivity;
import com.sence.R;
import com.sence.bean.response.PAbnormalAccountBean;
import com.sence.utils.JsonParseUtil;
import com.sence.utils.LoginStatus;
import com.sence.utils.SharedPreferencesUtil;
import com.sence.utils.SocketUtils;

public abstract class BaseActivity extends AppCompatActivity implements BaseInterface {
    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onActCreate(savedInstanceState);
        setContentView(onActLayout());
        unbinder = ButterKnife.bind(this);
        initView();
        SocketUtils.getInstance().setOnGetSocketResult(new SocketUtils.OnGetSocketResult() {
            @Override
            public void putSocketResult(String str) {
                PAbnormalAccountBean pAbnormalAccountBean = JsonParseUtil.parseString(str, PAbnormalAccountBean.class);
                if ("other_device_login".equals(pAbnormalAccountBean.getType())) {
                    alter(pAbnormalAccountBean.getTime());
                }
            }
        });
    }
    private void alter(String data){
        View view = View.inflate(this, R.layout.layout_abnormal_account, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AlertDialogStyle);
        builder.setView(view);
        final AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(true);
        alertDialog.show();
        alertDialog.getWindow().setLayout(new DensityUtil().dip2px(270), LinearLayout.LayoutParams.WRAP_CONTENT);
        TextView time = view.findViewById(R.id.tv_time_abnormal);
        time.setText("你的Sence账号于"+data+"在其他设备登录。如非本人操作登录密码可能已经泄露，请修改密码，建议妥善保存个人相关信息。");
        view.findViewById(R.id.bt_confirm_abnormal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferencesUtil.getInstance().removeAll();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                alertDialog.dismiss();
            }
        });
    }
    @Override
    protected void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }

    @Override
    public void onActCreate(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            return;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!SocketUtils.getInstance().isConnected()){
            if (LoginStatus.isLogin()){
                SocketUtils.getInstance().startSocket();
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        initData();
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

}
