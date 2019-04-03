package com.sence;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;

import com.sence.activity.AddTagActivity;
import com.sence.utils.PermissionUtil;

import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class SplashActivity extends AppCompatActivity {
    private PermissionUtil permissionUtil;
    private Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        permissionUtil = new PermissionUtil(this);
        if (permissionUtil.requestPermissions(PermissionUtil.READ_PHONE_STATE,
                new String[]{Manifest.permission.READ_PHONE_STATE})) {
            disposable =
                    Observable.interval(2, TimeUnit.SECONDS).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Long>() {
                        @Override
                        public void accept(Long aLong) {
                            disposable.dispose();
                            startActivity(new Intent(SplashActivity.this, AddTagActivity.class));
                            finish();
                        }
                    });
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        permissionUtil.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onDestroy() {
        if (disposable != null && disposable.isDisposed()) {
            disposable.dispose();
        }
        super.onDestroy();
    }
}
