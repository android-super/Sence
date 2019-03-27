package com.sence.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import com.sence.R;

import java.util.ArrayList;
import java.util.List;

public class PermissionUtil {
    public static final int PHONE_CALL = 0;
    public static final int CAMERA = 1;
    public static final int READ_CONTACTS = 2;
    public static final int RECODE_VIDEO = 3;
    public static final int READ_PHONE_STATE = 4;
    public static final int LOCATION = 5;
    public static final int READ_PHONE_STATE_NORMAL = 6;

    private Activity activity;

    public PermissionUtil(Activity activity) {
        this.activity = activity;
    }


    @TargetApi(value = Build.VERSION_CODES.M)
    public List<String> findDeniedPermissions(String... permission) {
        List<String> denyPermissions = new ArrayList<>();
        for (String value : permission) {
            if (ContextCompat.checkSelfPermission(activity, value) != PackageManager.PERMISSION_GRANTED) {
                denyPermissions.add(value);
            }
        }
        return denyPermissions;
    }

    @TargetApi(value = Build.VERSION_CODES.M)
    public boolean requestPermissions(int requestCode, String[] permissions) {
        List<String> deniedPermissions = findDeniedPermissions(permissions);
        if (deniedPermissions.size() > 0) {
            activity.requestPermissions(deniedPermissions.toArray(new String[deniedPermissions.size()]), requestCode);
            return false;
        } else {
            return true;
        }
    }

    public boolean isPermissions(String[] permissions) {
        List<String> deniedPermissions = findDeniedPermissions(permissions);
        if (deniedPermissions.size() > 0) {
            return false;
        } else {
            return true;
        }
    }

    @TargetApi(value = Build.VERSION_CODES.M)
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (grantResults != null) {
            if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
                int stringResId = -1;
                if (requestCode == PHONE_CALL) {
                    stringResId = R.string.permission_phone_call;
                }
                if (requestCode == CAMERA) {
                    stringResId = R.string.permission_camera;
                }
                if (requestCode == READ_CONTACTS) {
                    stringResId = R.string.permission_contacts;
                }
                if (requestCode == RECODE_VIDEO) {
                    stringResId = R.string.permission_recode_video;
                }
                if (requestCode == READ_PHONE_STATE) {
                    stringResId = R.string.permission_phone_state;
                }
                if (requestCode == LOCATION) {
                    stringResId = R.string.permission_location;
                }
                if (requestCode == READ_PHONE_STATE_NORMAL) {
                    stringResId = R.string.permission_phone_state_normal;
                }
                createDialog(activity.getResources().getString(stringResId), requestCode);
            }
        }
    }

    /**
     * 创建对话框
     *
     * @param message
     * @param requestCode
     */
    public void createDialog(String message, final int requestCode) {
        AlertDialog.Builder builder =
                new AlertDialog.Builder(activity).setTitle(activity.getResources().getString(R.string.permission_title));
        builder.setMessage(message);
        builder.setPositiveButton(activity.getResources().getString(R.string.permission_summit),
                new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", activity.getPackageName(), null);
                intent.setData(uri);
                activity.startActivity(intent);
            }
        });
        builder.setNegativeButton(activity.getResources().getString(R.string.permission_cancel),
                new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                if (requestCode == READ_PHONE_STATE) {
                    activity.finish();
                } else if (requestCode == LOCATION) {
                    activity.finish();
                }
            }
        });
        builder.setCancelable(false);
        builder.create();
        builder.show();
    }

}