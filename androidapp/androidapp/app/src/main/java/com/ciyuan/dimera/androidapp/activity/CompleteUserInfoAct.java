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
 * ClassName : CompleteUserInfoAct
 * Author   : 史翔宇
 * Time     : 2015/12/17
 * Desc     :
 */
public class CompleteUserInfoAct extends Activity implements View.OnClickListener {

    protected ImageView iv_goback;
    protected TextView tv_title;
    protected ImageView iv_window;
    private Button bt_complete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_complete_userinfo);
        initView();
    }

    public void initView() {
        iv_goback = (ImageView) findViewById(R.id.iv_goback);
        tv_title = (TextView) findViewById(R.id.tv_title);
        iv_window = (ImageView) findViewById(R.id.iv_window);
        bt_complete = (Button) findViewById(R.id.bt_complete);
        iv_goback.setOnClickListener(this);
        bt_complete.setOnClickListener(this);
        tv_title.setText("完善资料");
        iv_window.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_goback:
                finish();
                break;
            case R.id.bt_complete:
               IntentUtils.startActivityAndFinish(this, IndexActivity.class);
                break;
        }
    }
}
