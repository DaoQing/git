package com.ciyuan.dimera.androidapp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ciyuan.dimera.androidapp.R;
import com.ciyuan.dimera.androidapp.dialog.DateTimePickDialog;
import com.ciyuan.dimera.androidapp.utils.ShareUtils;

/**
 * ClassName : MyInfoAct
 * Author   : 史翔宇
 * Time     : 2015/12/28
 * Desc     :
 */
public class MyInfoAct extends Activity implements View.OnClickListener {

    private ImageView iv_goback;
    private TextView tv_title;
    private ImageView iv_window;

    private EditText birthday;
    private Button button, button_ok;
    private EditText place;
    int width, height;
    LinearLayout test_pop_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_myinfo);
        // 获取屏幕的高度和宽度
        Display display = this.getWindowManager().getDefaultDisplay();
        width = display.getWidth();
        height = display.getHeight();
        findView();
        setupPager();
    }

    private void findView() {
        iv_goback= (ImageView) findViewById(R.id.iv_goback);
        tv_title= (TextView) findViewById(R.id.tv_title);
        iv_window= (ImageView) findViewById(R.id.iv_window);
        test_pop_layout = (LinearLayout) findViewById(R.id.test_pop_layout);
        birthday = (EditText) findViewById(R.id.et_user_info_birthday);
        place = (EditText) findViewById(R.id.et_user_info_place);
    }



    public void setupPager() {
        iv_goback.setOnClickListener(this);
        birthday.setOnClickListener(this);
        birthday.setText(ShareUtils.getString(MyInfoAct.this, "user_info_birthday"));
        place.setText(ShareUtils.getString(MyInfoAct.this, "user_info_address"));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.et_user_info_birthday:
                DateTimePickDialog dateTimePickDialog = new DateTimePickDialog(this);
                dateTimePickDialog.setOnsurelister(new DateTimePickDialog.onSureLister() {
                    @Override
                    public void onSetbirthday(String initdate) {
                        ShareUtils.setString(MyInfoAct.this, "user_info_birthday", initdate);
                        birthday.setText(initdate);

                    }
                });
                dateTimePickDialog.dateTimePicKDialog();
                break;
            case R.id.iv_goback:
                finish();
        }
    }
}
