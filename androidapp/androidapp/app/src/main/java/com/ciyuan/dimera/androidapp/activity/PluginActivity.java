package com.ciyuan.dimera.androidapp.activity;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ciyuan.dimera.androidapp.R;

import java.io.File;

//public class PluginActivity extends UnityPlayerActivity {
public class PluginActivity extends Activity {
    Context mContext = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_plugin);
        Button b = (Button) findViewById(R.id.btLink);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showIndexActivity();
            }
        });
    }

    //显示用户登陆界面
    public void showUserActivity(String where) {
        Intent i = new Intent(mContext, AyonelMainActivity.class);
        i.putExtra("from_where", where);
        startActivity(i);
    }

    //显示主界面
    public void showIndexActivity () {
        Intent i = new Intent(mContext, IndexActivity.class);
        startActivity(i);
    }

    //强制相册刷新
    public void scanPhoto(String imgFileName) {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File file = new File(imgFileName);
        Uri contentUri = Uri.fromFile(file);
        mediaScanIntent.setData(contentUri);
        mContext.sendBroadcast(mediaScanIntent);
    }

    //显示系统相册界面
    public void showAlbum(String str) {
        Intent intent = new Intent(mContext, AlbumActivity.class);
        intent.putExtra("type", str);
        this.startActivity(intent);
    }
}
