package com.ciyuan.dimera.androidapp.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ciyuan.dimera.androidapp.view.ClearEditText;
import com.ciyuan.dimera.androidapp.R;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;



public class AyonelFillcodeActivity extends Activity {
    Context context = this;
    String phone = "";
    Toast mToast;
    ClearEditText etValidation;
    TextView tvInfo;
    private static String APPKEY = "9f8ba3de5224";//次元之窗的KEY 字符串不外显
    private static String APPSECRET = "42de4d86ab4aa49d4edd2cbeef7a48ef";//次元之窗的SECRET

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_ayonel_fillcode);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.titlebar);

        context = this;

        TextView tvTitle = (TextView)findViewById(R.id.tvTitle);
        tvTitle.setText("填写验证码");

        tvInfo = (TextView)findViewById(R.id.tvInfo);
        etValidation = (ClearEditText)findViewById(R.id.etValidation);
        Button btNext = (Button)findViewById(R.id.btNext);
        Button btBack = (Button)findViewById(R.id.btBack);
        Button btResend = (Button)findViewById(R.id.btResend);
        Intent intent_receieve = this.getIntent();
        phone = intent_receieve.getStringExtra("phone");

        btBack.setBackgroundDrawable(getResources().getDrawable(R.drawable.back_icon_32));
        btBack.setText("");

        //初始化SDK
        SMSSDK.initSDK(this, APPKEY, APPSECRET);

        btResend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SMSSDK.getVerificationCode("86",phone);
            }
        });
        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkValidation();
            }
        });
        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        EventHandler eh = new EventHandler(){
            @Override
            public void afterEvent(int event, int result, Object data) {
                Message msg = new Message();
                msg.arg1 = event;
                msg.arg2 = result;
                msg.obj = data;
                handler.sendMessage(msg);
            }
        };
        SMSSDK.registerEventHandler(eh);
        //发送短信
        SMSSDK.getVerificationCode("86",phone);
    }
//这块代码存在内存泄露问题 百度This Handler class should be static or leaks might occur,以后解决
     Handler handler=new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int event = msg.arg1;
            int result = msg.arg2;
            Object data = msg.obj;
            Log.e("event", "event=" + event);
            if (result == SMSSDK.RESULT_COMPLETE) {
                //短信注册成功后，返回MainActivity,然后提示新好友
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                //提交验证码成功
                    //Toast.makeText(getApplicationContext(), "提交验证码成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, AyonelPhoneregisterActivity.class);
                    intent.putExtra("phone", phone);
                    startActivity(intent);
                } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE){
                    showToast("短信已发送");
                    String info = "您的手机号+86 " + phone + ",会收到一条带有验证码的短信";
                    tvInfo.setText(info);
                }
            } else {
                ((Throwable) data).printStackTrace();
                showToast("验证码错误");
                //打印错误  新版SDK
//                int resId = getStringRes(AyonelFillcodeActivity.this, "smssdk_network_error");
//                Toast.makeText(AyonelFillcodeActivity.this, "验证码错误", Toast.LENGTH_SHORT).show();
//                if (resId > 0) {
//                    Toast.makeText(AyonelFillcodeActivity.this, resId, Toast.LENGTH_SHORT).show();
//                }
            }

        }

    };

    //发送并且校验验证码
    protected void checkValidation(){
        if(!TextUtils.isEmpty(etValidation.getText().toString())){
            SMSSDK.submitVerificationCode("86", phone, etValidation.getText().toString());
        }else {
            showToast("验证码不能为空");
        }
    }
    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        SMSSDK.unregisterAllEventHandler();
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
