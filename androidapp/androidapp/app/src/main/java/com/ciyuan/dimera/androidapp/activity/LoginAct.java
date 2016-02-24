package com.ciyuan.dimera.androidapp.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.ciyuan.dimera.androidapp.R;
import com.ciyuan.dimera.androidapp.utils.CacheActivity;
import com.ciyuan.dimera.androidapp.utils.Constans;
import com.ciyuan.dimera.androidapp.utils.IntentUtils;
import com.ciyuan.dimera.androidapp.utils.ParamsUtils;
import com.ciyuan.dimera.androidapp.utils.ToastUtils;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * ClassName : LoginAct
 * Author   : 史翔宇
 * Time     : 2015/12/16
 * Desc     :
 */
public class LoginAct extends Activity implements View.OnClickListener {

    private long exitTime = 0;
    private EditText username;//用户名
    private EditText password;//密码
    private TextView forget_password;//忘记密码框
    private Button login;//登陆按钮
    private Button register;//注册按钮
    ProgressDialog dialog;//自定义对话框

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_login);
        if (!CacheActivity.activityList.contains(LoginAct.this)) {
            CacheActivity.addActivity(LoginAct.this);
        }
        initView();
    }

    protected void initView() {
        username = (EditText) findViewById(R.id.et_username);
        password = (EditText) findViewById(R.id.et_password);
        login = (Button) findViewById(R.id.bt_login);
        register = (Button) findViewById(R.id.bt_register);
        forget_password = (TextView) findViewById(R.id.tv_forget_password);
        forget_password.setOnClickListener(this);
        login.setOnClickListener(this);
        register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_login:
//                if (username.getText().toString().trim().equals("")) {//如果用户名为空，提示用户名不能为空
//                    Toast.makeText(LoginAct.this, R.string.usernameerror_blank,
//                            Toast.LENGTH_SHORT).show();
//                    return;
//
//
//                } else if (isPhoneNo(username.getText().toString().trim())){
//                    Toast.makeText(LoginAct.this, R.string.usernameerror,
//                            Toast.LENGTH_SHORT).show();
//                }
//                else if (password.getText().toString().trim().equals("")) {//如果密码为空，提示密码不能为空
//                    Toast.makeText(LoginAct.this, R.string.passworderror,
//                            Toast.LENGTH_SHORT).show();
//                    return;
//                }
                IntentUtils.startActivityAndFinish(this, IndexActivity.class);
                break;
            case R.id.bt_register:
                Intent register = new Intent(this, RegisterAct.class);
                startActivity(register);
                break;
            case R.id.tv_forget_password:
                Intent resetpassword = new Intent(this, ResetPasswordAct.class);
                startActivity(resetpassword);
                break;
        }

    }

    public void sendLoginRequest(final String phone, final String password) {

        StringRequest request = new StringRequest(Request.Method.POST, Constans.LOGIN, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                int errorCode = volleyError.networkResponse.statusCode;
                String errorDesc = ErrorUtils.processError(errorCode);
                ToastUtils.show(LoginAct.this,errorDesc);
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return ParamsUtils.getRequestParams(Constans.LOGIN,phone,password);
            }

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {

                String parsed;
                try {
                    Map<String,String> responseHeaders=response.headers;
                    //获取Cookie
                    String rawCookies = responseHeaders.get("Set-Cookie");
                    parsed = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
                } catch (UnsupportedEncodingException var4) {
                    parsed = new String(response.data);
                }

                return Response.success(parsed, HttpHeaderParser.parseCacheHeaders(response));
            }
        };
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次就退出了哦", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    //验证手机号是否正确
    protected  boolean isPhoneNo(String mobiles){
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }
}
