package com.ciyuan.dimera.androidapp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ciyuan.dimera.androidapp.R;

/**
 * ClassName : SendToCircleAct
 * Author   : 史翔宇
 * Time     : 2016/1/6
 * Desc     :
 */
public class SendToCircleAct extends Activity implements View.OnClickListener {
    private ImageView iv_goback;
    private TextView tv_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_send_to_circle);
        initView();
    }


    private void initView() {
        iv_goback = (ImageView) findViewById(R.id.iv_goback);
        tv_title = (TextView) findViewById(R.id.tv_title);
        iv_goback.setOnClickListener(this);
        tv_title.setText("转发到圈子");
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_goback:
                finish();
                break;
        }
    }
}
