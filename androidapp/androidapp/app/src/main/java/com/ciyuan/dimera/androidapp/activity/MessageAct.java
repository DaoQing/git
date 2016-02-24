package com.ciyuan.dimera.androidapp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.ciyuan.dimera.androidapp.R;

/**
 * Created by quqing on 2016-01-19.
 */
public class MessageAct extends Activity{
    private LinearLayout ll_zanr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tap_message);
//        ll_zanr.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ll_zanr = (LinearLayout) findViewById(R.id.ll_zan);
//                Intent intent = new Intent(MessageAct.this,MessageLikeAct.class);
//                startActivity(intent);
//            }
//        });
    }

}
