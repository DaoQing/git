package com.ciyuan.dimera.androidapp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.ciyuan.dimera.androidapp.R;
import com.ciyuan.dimera.androidapp.adapter.MessageLikeAdapter;

public class MessageLikeAct extends Activity {
    private ListView lv_message_like;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_like);
        lv_message_like = (ListView) findViewById(R.id.lv_message_like);
        lv_message_like.setAdapter(new MessageLikeAdapter(MessageLikeAct.this));
    }



}
