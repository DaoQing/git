package com.ciyuan.dimera.androidapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.KeyEvent;
import android.widget.ImageView;

import com.ciyuan.dimera.androidapp.R;
import com.ciyuan.dimera.androidapp.utils.SystemTools;
import com.unity3d.player.UnityPlayer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class AlbumActivity extends Activity {

    ImageView imageView = null;

    public static final int NONE = 0;
    public static final int PHOTOHRAPH = 1;// 拍照
    public static final int PHOTOZOOM = 2; // 缩放
    public static final int PHOTORESOULT = 3;// 结果
    public static final String IMAGE_UNSPECIFIED = "image/*";

    public static String fileName = "image.png";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);
        imageView = (ImageView) this.findViewById(R.id.imageID);
        String type = this.getIntent().getStringExtra("type");

        //在这里判断是打开本地相册还是直接照相
        if(type.equals("takePhoto")) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "temp.jpg")));
            startActivityForResult(intent, PHOTOHRAPH);
        } else {
            Intent intent = new Intent(Intent.ACTION_PICK, null);
            intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, IMAGE_UNSPECIFIED);
            startActivityForResult(intent, PHOTOZOOM);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == NONE)
            return;
        // 拍照
        if (requestCode == PHOTOHRAPH) {
            //设置文件保存路径这里放在跟目录下
            File picture = new File(Environment.getExternalStorageDirectory() + "/Dimera/capture.jpg");
            startPhotoZoom(Uri.fromFile(picture));
        }

        if (data == null) {
            return;
        }

        // 读取相册缩放图片
        if (requestCode == PHOTOZOOM) {
            startPhotoZoom(data.getData());
        }
        // 处理结果
        if (requestCode == PHOTORESOULT) {
            Bundle extras = data.getExtras();
            if (extras != null) {
                Bitmap photo = extras.getParcelable("data");
                imageView.setImageBitmap(photo);
                try {
                    SaveBitmap(photo);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    public void startPhotoZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, IMAGE_UNSPECIFIED);
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 300);
        intent.putExtra("outputY", 300);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, PHOTORESOULT);
    }

    public void SaveBitmap(Bitmap bitmap) throws IOException {
        FileOutputStream fOut = null;
        String path = Environment.getExternalStorageDirectory() + "/Dimera/tmp/";
        try {
            File destDir = new File(path);
            if (!destDir.exists()) {
                destDir.mkdirs();
            }

            fileName = SystemTools.getTimestamp() + ".png";
            fOut = new FileOutputStream(path + "/" + fileName) ;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //将Bitmap对象写入本地路径中，Unity在去相同的路径来读取这个文件
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
        try {
            fOut.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            //当用户点击返回键是 通知Unity开始在路径中读取图片资源，并且显示在Unity中
            UnityPlayer.UnitySendMessage("RawImage", "setTexture", fileName);
        }
        return super.onKeyDown(keyCode, event);
    }
}
