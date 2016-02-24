package com.ciyuan.dimera.androidapp.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class AyonelFillphoneActivity extends Activity {

    Context context;
    TextView tvTitle;
    ClearEditText etPhone;
    Toast mToast;
    Button btBack,btNext;
    RequestQueue phoneFillQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_ayonel_fillphone);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.titlebar);
        context = this;
        //初始化volley队列
        phoneFillQueue = Volley.newRequestQueue(context);

        tvTitle = (TextView)findViewById(R.id.tvTitle);
        tvTitle.setText("请输入手机号");
        etPhone = (ClearEditText)findViewById(R.id.etPhone);
        btNext = (Button)findViewById(R.id.btNext);
        btBack = (Button)findViewById(R.id.btBack);
        btBack.setBackgroundDrawable(getResources().getDrawable(R.drawable.back_icon_32));
        btBack.setText("");

        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String phone = etPhone.getText().toString().trim();
                if (!isPhoneNo(phone)) {
                    Animation shake = AnimationUtils.loadAnimation(context, R.anim.shake);
                    etPhone.startAnimation(shake);
                    showToast("请输入正确的手机号码");
                    return;
                }else{
                    //手机号输入正确，向服务器验证手机号是否注册
                    HashMap<String,String> map = new HashMap<String, String>();
                    map.put("user_phone",phone);
                    String url = context.getString(R.string.localhost_url)+"/Home/Validate/isRegistered";
                    AyonelCustomRequest jsonObjectRequest = new AyonelCustomRequest(Request.Method.POST, url,
                            map,
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    // TODO Auto-generated method stub
                                    Log.e("return Json", "response =" + response);
                                    int status = response.optInt("status", 1);
                                    if (status == 1) {
                                        //手机号已注册，提示用户
                                        showToast("该手机号已注册");
                                    }
                                    if (status == 0){
                                        //手机号未注册，可以进行下一步
                                        Intent intent = new Intent(context, AyonelFillcodeActivity.class);
                                        intent.putExtra("phone",phone);
                                        startActivity(intent);
                                    }
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("TAG", error.getMessage(), error);
                            showToast("手机号验证出错！返回error");
                        }
                    });
                    phoneFillQueue.add(jsonObjectRequest);
                }
            }
        });
    }

    //验证手机号是否正确
    protected  boolean isPhoneNo(String mobiles){
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }
    /**
     * 显示Toast消息
     * @param msg
     */
    private void showToast(String msg){
        if(mToast == null){
            mToast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        }else{
            mToast.setText(msg);
        }
        mToast.show();
    }

}
