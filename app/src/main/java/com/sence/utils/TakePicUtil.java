package com.sence.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import androidx.annotation.NonNull;
import androidx.core.content.FileProvider;
import com.sence.BuildConfig;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import static com.sence.utils.TakePicUtil.CropType.Any;


public class TakePicUtil {
    private PermissionUtil permissionUtil;

    // 启动手机相机拍摄照片作为头像
    public void choseHeadImageFromCameraCapture() {
        if (permissionUtil.requestPermissions(PermissionUtil.CAMERA, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE})) {
            chooseTakePhoto();
        }
    }

    // 从本地相册选取图片作为头像
    public void choseHeadImageFromGallery() {
        if (permissionUtil.requestPermissions(PermissionUtil.CAMERA, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE})) {
            chooseSelectPic();
        }
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        permissionUtil.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private Activity context;

    private CropType cropType = Any;
    private int width_scale = 1;//宽比
    private int height_scale = 1;//高比

    /**
     * 裁剪类型
     */
    public enum CropType {
        Normal,//默认比例
        Middle,//3:2比例
        Big,//4:3比例
        Any//任意比例
    }

    public TakePicUtil(Activity context) {
        this.context = context;
        permissionUtil = new PermissionUtil(context);
    }


    public TakePicUtil(Activity context, CropType cropType) {
        this.context = context;
        this.cropType = cropType;
        permissionUtil = new PermissionUtil(context);
    }

    public TakePicUtil(Activity context, int width_scale, int height_scale) {
        this.context = context;
        this.width_scale = width_scale;
        this.height_scale = height_scale;
        permissionUtil = new PermissionUtil(context);
    }

    /* 头像文件 */
    private static final String IMAGE_FILE_NAME = "temp_image.jpg";

    /* 请求识别码 */
    public static final int CODE_GALLERY_REQUEST = 0xa0;
    public static final int CODE_CAMERA_REQUEST = 0xa1;
    public static final int CODE_RESULT_REQUEST = 0xa2;

    // 裁剪后图片的宽(X)和高(Y),480 X 480的正方形。
    private static int output_X = 480;
    private static int output_Y = 480;

    public Bitmap getResultBitmap = null;// 获取到的头像
    public Uri imageUri = null;

    /**
     * 从相册选择
     */
    public void chooseSelectPic() {
        File outputImage = new File(Environment.getExternalStorageDirectory(),
                IMAGE_FILE_NAME);
        imageUri = Uri.fromFile(outputImage);
        try {
            if (outputImage.exists()) {
                outputImage.delete();
            }
            outputImage.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Intent intent = new Intent(Intent.ACTION_PICK, null);
        //此处调用了图片选择器
        //如果直接写intent.setDataAndType("image/*");
        //调用的是系统图库
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        context.startActivityForResult(intent, CODE_GALLERY_REQUEST);
    }


    /**
     * 照相获取
     */
    public void chooseTakePhoto() {
        Intent intentFromCapture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        // 判断存储卡是否可用，存储照片文件
        if (hasSdcard()) {
            File file = new File(Environment
                    .getExternalStorageDirectory(), IMAGE_FILE_NAME);
            try {
                if (file.exists()) {
                    file.delete();
                }
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            imageUri = getImageUri(file);
            intentFromCapture.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        }
        context.startActivityForResult(intentFromCapture, CODE_CAMERA_REQUEST);
    }


    /**
     * 裁剪原始的图片
     */

    public void cropRawPhoto(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 设置裁剪
        intent.putExtra("crop", "true");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        // aspectX , aspectY :宽高的比例
        switch (cropType) {
            case Normal:
                intent.putExtra("aspectX", 1);
                intent.putExtra("aspectY", 1);
                break;
            case Middle:
                intent.putExtra("aspectX", 3);
                intent.putExtra("aspectY", 2);
                break;
            case Big:
                intent.putExtra("aspectX", 4);
                intent.putExtra("aspectY", 3);
                break;
            case Any:
                intent.putExtra("aspectX", width_scale);
                intent.putExtra("aspectY", height_scale);
                break;
        }
        // outputX , outputY : 裁剪图片宽高
//        intent.putExtra("outputX", output_X);
//        intent.putExtra("outputY", output_Y);
        intent.putExtra("return-data", false);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        context.startActivityForResult(intent, CODE_RESULT_REQUEST);
    }

    /**
     * 提取保存裁剪之后的图片数据，并设置头像部分的View
     */
    public Bitmap setImageToHeadView(Intent intent) {
        if (imageUri != null) {
            Bitmap bitmap = decodeUriAsBitmap(imageUri);
            // 把解析到的位图显示出来
            return bitmap;
        }
        return null;
    }

    private Bitmap decodeUriAsBitmap(Uri uri) {
        Bitmap bitmap = null;
        try {
            // 先通过getContentResolver方法获得一个ContentResolver实例，
            // 调用openInputStream(Uri)方法获得uri关联的数据流stream
            // 把上一步获得的数据流解析成为bitmap
            final byte[] data = getBytes(context.getContentResolver().openInputStream(uri));
            bitmap = BitmapFactory.decodeStream(context.getContentResolver().openInputStream(imageUri));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return bitmap;
    }

    public static byte[] getBytes(InputStream is) throws IOException {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024]; // 用数据装
        int len = -1;
        while ((len = is.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        outStream.close();
        // 关闭流一定要记得。
        return outStream.toByteArray();
    }


    /**
     * 检查设备是否存在SDCard的工具方法
     */
    public static boolean hasSdcard() {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            // 有存储的SDCard
            return true;
        } else {
            return false;
        }
    }


    public Uri getImageUri(File file) {
        Uri imageUri;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            imageUri = FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID + ".fileProvider", file);
        } else {
            imageUri = Uri.fromFile(file);
        }
        return imageUri;
    }


    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        // 用户没有进行有效的设置操作，返回
        if (resultCode == context.RESULT_CANCELED) {
            return;
        }
        switch (requestCode) {
            case CODE_GALLERY_REQUEST:
                cropRawPhoto(intent.getData());
                break;
            case CODE_CAMERA_REQUEST:
                cropRawPhoto(imageUri);
                break;
            case CODE_RESULT_REQUEST:
                if (intent != null) {
                    getResultBitmap = setImageToHeadView(intent);
                }
                break;
        }
    }
}
