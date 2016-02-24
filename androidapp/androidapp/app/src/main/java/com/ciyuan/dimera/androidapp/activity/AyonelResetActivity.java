package com.ciyuan.dimera.androidapp.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.ciyuan.dimera.androidapp.utils.AyonelCustomRequest;
import com.ciyuan.dimera.androidapp.view.ClearEditText;
import com.ciyuan.dimera.androidapp.R;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class AyonelResetActivity extends Activity {

    ClearEditText etPassword,etRepassword;
    TextView tvTitle;
    Button btReset,btBack;
    String phone;
    Context context;
    RequestQueue phoneResetQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_ayonel_reset);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.titlebar);
        context = this;

        phoneResetQueue = Volley.newRequestQueue(context);

        etPassword = (ClearEditText)findViewById(R.id.etPassword);
        etRepassword = (ClearEditText)findViewById(R.id.etRepassword);
        tvTitle = (TextView)findViewById(R.id.tvTitle);
        tvTitle.setText("设置密码");
        btReset = (Button)findViewById(R.id.btReset);
        btBack = (Button) findViewById(R.id.btBack);
        btBack.setBackgroundDrawable(getResources().getDrawable(R.drawable.back_icon_32));
        btBack.setText("");

        btReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Reset();
            }
        });
        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnLast();
            }
        });
        Intent intent_receieve = this.getIntent();
        phone = intent_receieve.getStringExtra("phone");

    }

    private void Reset(){
        String password = etPassword.getText().toString().trim();
        String repassword = etRepassword.getText().toString().trim();
        if (!password.equals(repassword)){
            new AlertDialog.Builder(this).setTitle("提示")
                    .setMessage("两次密码输入不一致")
                    .setPositiveButton("确定",null)
                    .show();
            return;
        }
        String url = this.getString(R.string.localhost_url)+"/Home/Reset/reset";
        Map<String,String> map = new HashMap<>();
        map.put("user_phone",phone);
        map.put("user_psw",password);
        map.put("restype","phone");
        AyonelCustomRequest jsonObjectRequest = new AyonelCustomRequest(Request.Method.POST, url,
                map,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // TODO Auto-generated method stub
                        Log.e("return Json", "response =" + response);
                        int status = response.optInt("status", 0);
                        if (status == 1) {
                            new AlertDialog.Builder(context)
                                    .setMessage("重置成功")
                                    .setPositiveButton("确定", null)
                                    .show();
                        } else {
                            new AlertDialog.Builder(context)
                                    .setMessage("重置失败！")
                                    .setPositiveButton("确定", null)
                                    .show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG", error.getMessage(), error);
                new AlertDialog.Builder(context)
                        .setMessage("重置失败,返回了error")
                        .setPositiveButton("确定", null)
                        .show();
            }
        });

        phoneResetQueue.add(jsonObjectRequest);
    }
    private void returnLast(){
        this.finish();
    }
}
