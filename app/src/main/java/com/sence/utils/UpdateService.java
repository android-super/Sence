package com.sence.utils;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.*;
import android.widget.RemoteViews;
import android.widget.Toast;
import androidx.core.content.FileProvider;
import com.orhanobut.logger.Logger;
import com.sence.BuildConfig;
import com.sence.R;
import com.umeng.socialize.utils.DeviceConfig;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class UpdateService extends Service {
    private static final int TIMEOUT = 10 * 1000;
    private static final int DOWN_OK = 1;
    private static final int DOWN_ERROR = 0;
    private String app_name;
    private String down_url;
    private NotificationManager notificationManager;
    private Notification notification;
    private Notification.Builder builder;
    private Intent updateIntent;
    private PendingIntent pendingIntent;
    private int notification_id = 96;

    private UpdateService.DownloadBinder binder = new UpdateService.DownloadBinder();
    private UpdateService.DownloadCallback callback;


    /**
     * 进度条回调接口
     */
    public interface DownloadCallback {
        /**
         * 开始
         */
        void onStart();

        /**
         * 进度
         *
         * @param progress  进度 0.00 -1.00 ，总大小
         * @param totalSize 总大小 单位B
         */
        void onProgress(float progress, long totalSize);

        /**
         * 总大小
         *
         * @param totalSize 单位B
         */
        void setMax(long totalSize);

        /**
         * 下载完了
         *
         * @param file 下载的app
         * @return true ：下载完自动跳到安装界面，false：则不进行安装
         */
        boolean onFinish(File file);

        /**
         * 下载异常
         *
         * @param msg 异常信息
         */
        void onError(String msg);
    }

    /**
     * DownloadBinder中定义了一些实用的方法
     *
     * @author user
     */
    public class DownloadBinder extends Binder {
        public void start(UpdateService.DownloadCallback callback, String app_name, String down_url) {
            //下载
            startDownload(callback, app_name, down_url);
        }
    }

    /**
     * 下载模块
     */
    private void startDownload(final UpdateService.DownloadCallback callback, String app_name, String down_url) {
        this.callback = callback;
        this.app_name = app_name;
        this.down_url = down_url;
        createFile(app_name);
        createNotification();
        createThread();
    }

    public static void bindService(Context context, ServiceConnection connection) {
        Intent intent = new Intent(context, UpdateService.class);
        context.startService(intent);
        context.bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }


    @Override
    public IBinder onBind(Intent arg0) {
        return binder;
    }

//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId) {
//        if (null == intent) {
//            return Service.START_STICKY_COMPATIBILITY;
//        }
//        app_name = intent.getStringExtra("app_name");
//        down_url = intent.getStringExtra("down_url");
//        return super.onStartCommand(intent, flags, startId);
//    }

    public File updateDir = null;
    public File updateFile = null;

    private void createFile(String name) {

        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            updateDir = new File(Environment.getExternalStorageDirectory() + "/");
            updateFile = new File(updateDir + "/" + name + ".apk");
            if (!updateDir.exists()) {
                updateDir.mkdirs();
            }
            if (!updateFile.exists()) {
                try {
                    updateFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            Toast.makeText(this, "无法下载安装文件，请检查SD卡是否挂载", Toast.LENGTH_SHORT).show();
        }
    }

    private RemoteViews contentView;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void createNotification() {
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        builder = new Notification.Builder(this)
                .setSmallIcon(R.drawable.ic_down)
                .setContentTitle(app_name)
                .setContentIntent(pendingIntent)
                .setProgress(100, 0, false);
        if (callback != null) {
            callback.onStart();
        }
    }

    public void createThread() {

        final Handler handler = new Handler() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case DOWN_OK:
                        callback.onFinish(updateFile);
                        notificationManager.cancel(notification_id);
                        Intent intent = new Intent();
                        intent.setAction("com.downapp");
                        sendBroadcast(intent);
                        installApk();
                        stopSelf();
                        break;
                    case DOWN_ERROR:
                        callback.onError("下载失败");
                        builder.setContentText("下载失败");
                        builder.setProgress(100, 0, false);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            notification = builder.build();
                        } else {
                            notification = builder.getNotification();
                        }
                        break;
                    default:
                        stopService(updateIntent);
                        break;
                }
            }
        };
        final Message message = new Message();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    long downloadSize = downloadUpdateFile(down_url, updateFile.toString());
                    if (downloadSize > 0) {
                        message.what = DOWN_OK;
                        handler.sendMessage(message);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    message.what = DOWN_ERROR;
                    handler.sendMessage(message);
                }
            }
        }).start();
    }

    public long downloadUpdateFile(String down_url, String file) throws Exception {
        int down_step = 1;
        int totalSize;
        int downloadCount = 0;
        int updateCount = 0;
        InputStream inputStream;
        OutputStream outputStream;
        URL url = new URL(down_url);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setConnectTimeout(TIMEOUT);
        httpURLConnection.setReadTimeout(TIMEOUT);
        totalSize = httpURLConnection.getContentLength();
        if (httpURLConnection.getResponseCode() == 404) {
            throw new Exception("下载地址不存在！");
        }
        inputStream = httpURLConnection.getInputStream();
        outputStream = new FileOutputStream(file, false);
        byte buffer[] = new byte[1024];
        int readsize = 0;
        callback.setMax(100);
        while ((readsize = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, readsize);
            downloadCount += readsize;

            if (updateCount == 0 || (downloadCount * 100 / totalSize - down_step) >= updateCount) {
                updateCount += down_step;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    callback.onProgress(updateCount, 100);
                    builder.setContentText("正在下载..." + updateCount + "%" + "");
                    builder.setProgress(100, updateCount, false);
                    notification = builder.build();
                } else {
                    notification = builder.getNotification();
                }
                notificationManager.notify(notification_id, notification);
            }
        }
        if (httpURLConnection != null) {
            httpURLConnection.disconnect();
        }
        inputStream.close();
        outputStream.close();
        return downloadCount;
    }

    private void installApk() {
        File apkFile = updateFile;
        if (!apkFile.exists()) {
            Logger.e("apkFile不存在");
            return;
        }
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri imageUri;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            imageUri = FileProvider.getUriForFile(DeviceConfig.context, BuildConfig.APPLICATION_ID + ".fileProvider",
                    apkFile);
            i.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        } else {
            imageUri = Uri.fromFile(apkFile);
        }
        i.setDataAndType(imageUri, "application/vnd.android.package-archive");
        this.startActivity(i);
    }
}