package com.ciyuan.dimera.androidapp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ciyuan.dimera.androidapp.R;


public class AyonelAgreementActivity extends Activity {

    Button btReturn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayonel_agreement);

        btReturn = (Button)findViewById(R.id.btReturn);
        btReturn.setOnClickListener(new Button.OnClickListener() {//创建监听
            public void onClick(View v) {
                AyonelAgreementActivity.this.finish();
            }
        });

    }

}
