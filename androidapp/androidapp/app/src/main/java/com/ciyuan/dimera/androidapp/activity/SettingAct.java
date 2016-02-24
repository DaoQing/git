package com.ciyuan.dimera.androidapp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ciyuan.dimera.androidapp.R;
import com.ciyuan.dimera.androidapp.utils.IntentUtils;

/**
 * ClassName : SettingAct
 * Author   : 史翔宇
 * Time     : 2015/12/29
 * Desc     :
 */
public class SettingAct extends Activity implements View.OnClickListener {

    private ImageView iv_goback;
    private TextView tv_title;
    private ImageView iv_window;

    private Button bt_logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_setting);
        findView();
        setupPager();
    }

    private void findView(){
        iv_goback= (ImageView) findViewById(R.id.iv_goback);
        tv_title=(TextView)findViewById(R.id.tv_title);
        iv_window= (ImageView) findViewById(R.id.iv_window);
        bt_logout= (Button) findViewById(R.id.bt_logout);
    }

    private void setupPager(){
        iv_goback.setOnClickListener(this);
        bt_logout.setOnClickListener(this);
        tv_title.setText("设置");
        iv_window.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_goback:
                finish();
                break;
            case R.id.bt_logout:
                IntentUtils.startActivityAndFinish(this,LoginAct.class);
                break;
        }
    }
}
