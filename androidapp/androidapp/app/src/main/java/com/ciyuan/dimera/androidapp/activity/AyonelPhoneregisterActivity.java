package com.ciyuan.dimera.androidapp.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

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


public class AyonelPhoneregisterActivity extends Activity {

    ClearEditText etNickname,etPassword,etRepassword;
    Button btRegister,btBack;
    TextView tvAgreementLink,tvTitle;
    Context context;
    RequestQueue phoneRegisterQueue;
    String phone;
    Toast mToast;
    CheckBox cbAgreement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_ayonel_phoneregister);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.titlebar);
        context = this;
        tvTitle = (TextView)findViewById(R.id.tvTitle);
        tvTitle.setText("设置密码");
        phoneRegisterQueue = Volley.newRequestQueue(context);
        tvAgreementLink = (TextView)findViewById(R.id.tvAgreementLink);
        etNickname = (ClearEditText)findViewById(R.id.etNickname);
        etPassword = (ClearEditText)findViewById(R.id.etPassword);
        etRepassword = (ClearEditText)findViewById(R.id.etRepassword);
        cbAgreement = (CheckBox)findViewById(R.id.cbAgreement);
        btRegister = (Button) findViewById(R.id.btRegister);
        btBack = (Button) findViewById(R.id.btBack);
        btBack.setBackgroundDrawable(getResources().getDrawable(R.drawable.back_icon_32));
        btBack.setText("");
        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });

        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnLast();
            }
        });
        tvAgreementLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAgreement();
            }
        });
        Intent intent_receieve = this.getIntent();
        phone = intent_receieve.getStringExtra("phone");
    }

    private void register(){
        String nickname = etNickname.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String repassword = etRepassword.getText().toString().trim();

        if (TextUtils.isEmpty(nickname)) {
            //设置提示
            Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
            etPassword.startAnimation(shake);
            showToast("昵称不能为空");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            //设置提示
            Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
            etPassword.startAnimation(shake);
            showToast("密码不能为空");
            return;
        }
        if (TextUtils.isEmpty(repassword)) {
            //设置提示
            Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
            etPassword.startAnimation(shake);
            showToast("确认密码不能为空");
            return;
        }
        if(!cbAgreement.isChecked()){
            showToast("请先同意次元时代用户协议");
            return;
        }
        //Log.e("kaishi","kaishi");
        String url = this.getString(R.string.localhost_url) + "/Home/Register/register";
        Map<String, String> map = new HashMap<>();
        //存入Post数据
        map.put("user_phone",phone);
        map.put("user_psw",password);
        map.put("userinfo_nickname",nickname);
        map.put("regtype","phone");

        AyonelCustomRequest jsonObjectRequest = new AyonelCustomRequest(Request.Method.POST, url,
                map,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // TODO Auto-generated method stub
                        Log.e("return Json", "response =" + response);
                        int status = response.optInt("status", 0);
                        if (status == 1) {
                            showToast("注册成功");
                            Intent intent = new Intent(context,AyonelPersonActivity.class);
                            startActivity(intent);
                        } else {
                            showToast("注册失败");
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG", error.getMessage(), error);
                showToast("注册失败,返回了error");
            }
        });

        phoneRegisterQueue.add(jsonObjectRequest);
    }



    private void returnLast(){
        this .finish();
    }
    private void showAgreement(){
        startActivity(new Intent(context, AyonelAgreementActivity.class));
    }
    private void showToast(String msg){
        if(mToast == null){
            mToast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        }else{
            mToast.setText(msg);
        }
        mToast.show();
    }
}
