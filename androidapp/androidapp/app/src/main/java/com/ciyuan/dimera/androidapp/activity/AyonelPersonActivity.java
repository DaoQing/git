package com.ciyuan.dimera.androidapp.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.ciyuan.dimera.androidapp.R;
import com.ciyuan.dimera.androidapp.utils.PhotoMultipartRequest;

import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;


public class AyonelPersonActivity extends Activity {

    private static int CAMERA_REQUEST_CODE = 1;
    private static int GALLERY_REQUEST_CODE =2;
    private static int CROP_REQUEST_CODE =3;
    private AlertDialog selectProfileDialog = null;
    Toast mToast;
    RequestQueue personQueue;
    ImageView ivProfile;
    Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayonel_person);
        ivProfile = (ImageView)findViewById(R.id.ivProfile);
        ivProfile.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                selectProfileDialog = new AlertDialog.Builder(AyonelPersonActivity.this).create();
                selectProfileDialog.show();
                selectProfileDialog.getWindow().setContentView(R.layout.profile_select);
                selectProfileDialog.getWindow()
                        .findViewById(R.id.ivAlbum)
                        .setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                                intent.setType("image/*");
                                startActivityForResult(intent, GALLERY_REQUEST_CODE);
                                selectProfileDialog.dismiss();
                            }
                        });
                selectProfileDialog.getWindow()
                        .findViewById(R.id.ivCamera)
                        .setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //检查sk卡是否可用
                                String sdStatus = Environment.getExternalStorageState();
                                if(!sdStatus.equals(Environment.MEDIA_MOUNTED)){
                                    Log.v("TestFile", "SD card is not avaiable/writeable right now.");
                                    return;
                                }
                                File tmpDir = new File(Environment.getExternalStorageDirectory() + "/com.ciyuan.dimera.androidapp.avater");
                                File img = new File(tmpDir.getAbsolutePath() + "avater.jpeg");
                                Uri uri=Uri.fromFile(img);
                                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                intent.putExtra(MediaStore.Images.Media.ORIENTATION, 0);
                                intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                                startActivityForResult(intent, CAMERA_REQUEST_CODE);
                                selectProfileDialog.dismiss();}
                        });

            }
        });
        personQueue = Volley.newRequestQueue(context);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == CAMERA_REQUEST_CODE){
            File tmpDir = new File(Environment.getExternalStorageDirectory() + "/com.ciyuan.dimera.androidapp.avater");
            File img = new File(tmpDir.getAbsolutePath() + "avater.jpeg");
            if(img != null){
                startImageZoom(Uri.fromFile(img));
            }
        }else if(requestCode == GALLERY_REQUEST_CODE) {
            if(data == null) {
                return;
            }
            Uri uri;
            uri = data.getData();
            Uri fileUri = convertUri(uri);
            startImageZoom(fileUri);
        }
        else if(requestCode == CROP_REQUEST_CODE) {
            if(data == null) {
                return;
            }
            Bundle extras = data.getExtras();
            if(extras == null){
                return;
            }
            Bitmap bm=data.getParcelableExtra("data");
            ivProfile.setImageBitmap(bm);
            //发送图片
            File image = saveBitmapFile(bm);
            sendImage(image);
        }
    }
    private Uri convertUri(Uri uri){
        InputStream is = null;
        try {
            is = getContentResolver().openInputStream(uri);
            Bitmap bitmap = BitmapFactory.decodeStream(is);
            is.close();
            return saveBitmap(bitmap);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    private Uri saveBitmap(Bitmap bm)
    {

        File tmpDir = new File(Environment.getExternalStorageDirectory() + "/com.ciyuan.dimera.androidapp.avater");
        if(!tmpDir.exists())
        {
            tmpDir.mkdir();
        }
        File img = new File(tmpDir.getAbsolutePath() + "avater.jpeg");
        try {
            FileOutputStream fos = new FileOutputStream(img);
            bm.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
            return Uri.fromFile(img);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    //将裁减后返回的bitmap保存成file，调用volley上传
    public File saveBitmapFile(Bitmap bitmap){
        File file=new File(getApplicationContext().getFilesDir().getAbsolutePath()+"/test.jpg");//将要保存图片的路径
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bos.flush();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }
    private void startImageZoom(Uri uri)
    {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 250);
        intent.putExtra("outputY", 250);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, CROP_REQUEST_CODE);
    }
    //volley 还未实现
    private void sendImage(File file){
        String url = context.getString(R.string.localhost_url)+"/Home/Person/upload";
        PhotoMultipartRequest imageUploadReq = new PhotoMultipartRequest(url,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        showToast("上传失败，返回了error");
                    }
                }, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                int status = response.optInt("status", 0);
                if (status == 1) {
                    showToast("上传成功");
                } else {
                    showToast("上传失败");
                }
            }
        }, file);
        personQueue.add(imageUploadReq);
    }
    private void showToast(String msg){
        if(mToast == null){
            mToast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        }else{
            mToast.setText(msg);
        }
        mToast.show();
    }
}
