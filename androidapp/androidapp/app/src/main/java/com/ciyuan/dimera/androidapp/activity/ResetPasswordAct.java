package com.ciyuan.dimera.androidapp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ciyuan.dimera.androidapp.R;
import com.ciyuan.dimera.androidapp.utils.CacheActivity;

/**
 * ClassName : RegisterAct
 * Author   : 史翔宇
 * Time     : 2015/12/16
 * Desc     :
 */
public class ResetPasswordAct extends Activity implements View.OnClickListener {

    protected ImageView iv_goback;
    protected TextView tv_title;
    protected ImageView iv_window;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!CacheActivity.activityList.contains(ResetPasswordAct.this)) {
            CacheActivity.addActivity(ResetPasswordAct.this);
        }
        setContentView(R.layout.act_resetpassword);
        initView();
    }

    public void  initView(){
        iv_goback= (ImageView) findViewById(R.id.iv_goback);
        tv_title= (TextView) findViewById(R.id.tv_title);
        iv_window= (ImageView) findViewById(R.id.iv_window);
        iv_goback.setOnClickListener(this);
        tv_title.setText("重置密码");
        iv_window.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.iv_goback:
                finish();
                break;
        }
    }
}
