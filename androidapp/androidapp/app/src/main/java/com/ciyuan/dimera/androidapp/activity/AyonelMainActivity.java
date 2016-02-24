package com.ciyuan.dimera.androidapp.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
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
import com.mob.tools.utils.UIHandler;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;


public class AyonelMainActivity extends Activity implements PlatformActionListener,Handler.Callback {

    private Button btLogin,btRegister,btBack,btRetrieve,btWeiboLogin,btQQLogin,btWeixinLogin,btTest;
    private ClearEditText etAccount,etPassword;
    private static final int MSG_TOAST = 1;
    private static final int MSG_ACTION_CCALLBACK = 2;
    private static final int MSG_CANCEL_NOTIFY = 3;
    private Context context;
    private RequestQueue loginQueue;
    private Toast mToast;
    private TextView tvTitle;
    // 短信注册，随机产生头像,可不用
    //试试git好坏


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_ayonel_main);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.titlebar);

        context = this;
        loginQueue = Volley.newRequestQueue(context);
        etAccount = (ClearEditText)findViewById(R.id.etAccount);
        etPassword = (ClearEditText)findViewById(R.id.etPassword);
        tvTitle = (TextView)findViewById(R.id.tvTitle);
        tvTitle.setText("登录");
        btLogin = (Button)findViewById(R.id.btLogin);
        btBack = (Button)findViewById(R.id.btBack);
        btRetrieve = (Button)findViewById(R.id.btRetrieve);
        btRegister = (Button)findViewById(R.id.btRegister);
        //btShare = (Button) findViewById(R.id.btShare);
        btWeiboLogin = (Button) findViewById(R.id.btWeiboLogin);
        btQQLogin = (Button) findViewById(R.id.btQQLogin);
        btWeixinLogin = (Button) findViewById(R.id.btWeixinLogin);
        btTest = (Button)findViewById(R.id.btTest);

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //context.finish();
            }
        });
        btRetrieve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //启动输入手机号界面
                Intent intent = new Intent(context,AyonelFillphoneActivity.class);
                startActivity(intent);
            }
        });
        btRegister.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View v) {
               Intent intent = new Intent(context,AyonelFillphoneActivity.class);
               startActivity(intent);
           }
        });
        //分享例子 暂时保留
//        btShare.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showShare();
//            }
//        });

        btWeiboLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getWeiboAuth();
            }
        });
        btQQLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getQQAuth();
            }
        });
        btWeixinLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getWeixinAuth();
            }
        });
        btTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AyonelMainActivity.this, AyonelPersonActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ShareSDK.stopSDK(context);
    }

    @Override
    public void onCancel(Platform platform, int action) {
        Message msg = new Message();
        msg.what = MSG_ACTION_CCALLBACK;
        msg.arg1 = 3;
        msg.arg2 = action;
        msg.obj = platform;
        UIHandler.sendMessage(msg, this);
    }

    @Override
    public void onComplete(Platform platform, int action, HashMap<String, Object> res) {
        /** res是返回的数据，例如showUser(null),返回用户信息，对其解析就行
         *   http://sharesdk.cn/androidDoc/cn/sharesdk/framework/PlatformActionListener.html
         *   1、不懂如何解析hashMap的，可以上网搜索一下
         *   2、可以参考官网例子中的GetInforPage这个类解析用户信息
         *   3、相关的key-value,可以看看对应的开放平台的api
         *     如新浪的：http://open.weibo.com/wiki/2/users/show
         *     腾讯微博：http://wiki.open.t.qq.com/index.php/API%E6%96%87%E6%A1%A3/%E5%B8%90%E6%88%B7%E6%8E%A5%E5%8F%A3/%E8%8E%B7%E5%8F%96%E5%BD%93%E5%89%8D%E7%99%BB%E5%BD%95%E7%94%A8%E6%88%B7%E7%9A%84%E4%B8%AA%E4%BA%BA%E8%B5%84%E6%96%99
         *
         *	The param of res is the showUser(null) method action return data.
         *   You should analyse the return data of res by yourself
         *   1、Also,you can refer our GetInforPage.java in our official sample
         *   2、If you want to see the official document of each Weibo ,you can see the ShareSDK.xml this file
         *   For example, SinaWeibo Development Website: http://open.weibo.com/wiki/2/users/show
         *   TecentWeibo Development Website:http://wiki.open.t.qq.com/index.php/API%E6%96%87%E6%A1%A3/%E5%B8%90%E6%88%B7%E6%8E%A5%E5%8F%A3/%E8%8E%B7%E5%8F%96%E5%BD%93%E5%89%8D%E7%99%BB%E5%BD%95%E7%94%A8%E6%88%B7%E7%9A%84%E4%B8%AA%E4%BA%BA%E8%B5%84%E6%96%99
         */

        //hashMap object transform into json object
        //JsonUtils ju = new JsonUtils();
        //String json = ju.fromHashMap(res);
        String uid = platform.getDb().getUserId();
        String uname = platform.getDb().getUserName();
        String uicon = platform.getDb().getUserIcon();

        Log.e("weixinceshi", uid + uname+uicon);
        String pl = platform.getName();
        thirdLogin(uid, uname,uicon, pl);
//        Message msg = new Message();
//        msg.what = MSG_ACTION_CCALLBACK;
//        msg.arg1 = 1;
//        msg.arg2 = action;
//        msg.obj = platform;
//        UIHandler.sendMessage(msg, this);

    }

    @Override
    public void onError(Platform platform, int action, Throwable t) {

        t.printStackTrace();
        t.getMessage();


        Message msg = new Message();
        msg.what = MSG_ACTION_CCALLBACK;
        msg.arg1 = 2;
        msg.arg2 = action;
        msg.obj = t;
        UIHandler.sendMessage(msg, this);
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case MSG_TOAST: {
                String text = String.valueOf(msg.obj);
                Toast.makeText(AyonelMainActivity.this, text, Toast.LENGTH_SHORT).show();
            }
            break;
            case MSG_ACTION_CCALLBACK: {
                switch (msg.arg1) {
                    case 1: { // 成功, successful notification
                        showNotification(2000, "Auth successfully");

                        //授权成功后,获取用户信息，要自己解析，看看oncomplete里面的注释
                        //ShareSDK只保存以下这几个通用值
                        Platform pf = (Platform) msg.obj;
                        Log.e("sharesdk use_id", pf.getDb().getUserId()); //获取用户id
                        Log.e("sharesdk use_name", pf.getDb().getUserName());//获取用户名称
                        Log.e("sharesdk use_icon", pf.getDb().getUserIcon());//获取用户头像
                        //pf.author()这个方法每一次都会调用授权，出现授权界面
                        //如果要删除授权信息，重新授权
                        //pf.getDb().removeAccount();
                        //调用后，用户就得重新授权，否则下一次就不用授权
                    }
                    break;
                    case 2: { // 失败, fail notification
                        String expName = msg.obj.getClass().getSimpleName();
                        if ("WechatClientNotExistException".equals(expName)
                                || "WechatTimelineNotSupportedException".equals(expName)) {
                            showNotification(2000, getString(R.string.wechat_client_inavailable));
                        } else if ("GooglePlusClientNotExistException".equals(expName)) {
                            showNotification(2000, getString(R.string.google_plus_client_inavailable));
                        } else if ("QQClientNotExistException".equals(expName)) {
                            showNotification(2000, getString(R.string.qq_client_inavailable));
                        } else {
                            showNotification(2000, "Auth unsuccessfully");
                        }
                    }
                    break;
                    case 3: { // 取消, cancel notification
                        showNotification(2000, "Cancel authorization");
                    }
                    break;
                }
            }
            break;
            case MSG_CANCEL_NOTIFY: {
                NotificationManager nm = (NotificationManager) msg.obj;
                if (nm != null) {
                    nm.cancel(msg.arg1);
                }
            }
            break;
        }
        return false;
    }



    private void login(){
        final String phone = etAccount.getText().toString().trim();//获取账户输入信息
        final String password = etPassword.getText().toString().trim();//获取密码输入信息
        //账户不能为空
        if (TextUtils.isEmpty(phone)) {
            //设置提示
            Animation shake = AnimationUtils.loadAnimation(context, R.anim.shake);
            etAccount.startAnimation(shake);
            showToast("用户名不能为空");
            return;
        }
        //密码不能为空
        if (TextUtils.isEmpty(password)) {
            //设置提示
            Animation shake = AnimationUtils.loadAnimation(context, R.anim.shake);
            etPassword.startAnimation(shake);
            showToast("密码不能为空");
            return;
        }
        //账户长度为6-22位
        if (password.length() < 6 || password.length() > 22) {
            Animation shake = AnimationUtils.loadAnimation(context, R.anim.shake);
            etPassword.startAnimation(shake);
            showToast("密码长度为6-22位");
            return;
        }

//        String url = this.getString(R.string.host_url) + "/Home/Login/login";
        String url = this.getString(R.string.localhost_url) + "/Home/Userinfo/test";
        Log.e("url", url);
        Map<String, String> map = new HashMap<>();
        //存入Post数据
        map.put("user_phone", phone);
        map.put("user_psw", password);
//        map.put("logtype", "phone");
        map.put("session_id", "3hpjh48vcdm8lf6n36a18lnfi1");
        AyonelCustomRequest jsonObjectRequest = new AyonelCustomRequest(Request.Method.POST, url,
                map,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // TODO Auto-generated method stub
                        Log.i("return Json", "response =" + response);
                        int status = response.optInt("code", 0);
                        String number = response.optString("number",null);
                        if (status == 1) {
                            showToast("登陆成功,number:" + number);
                        } else {
                            showToast("用户名密码错误");
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG", error.getMessage(), error);
                new AlertDialog.Builder(context)
                        .setMessage("登录失败！返回error")
                        .setPositiveButton("确定", null)
                        .show();
            }
        });

        loginQueue.add(jsonObjectRequest);
    }

    private void getWeiboAuth() {
        ShareSDK.initSDK(this);
        Platform weibo = ShareSDK.getPlatform(SinaWeibo.NAME);
        weibo.SSOSetting(false);  //设置false表示使用SSO授权方式
        weibo.setPlatformActionListener(this); // 设置分享事件回调
        weibo.authorize();
        weibo.showUser(null);
    }

    private void getQQAuth() {
        ShareSDK.initSDK(this);
        Platform qq = ShareSDK.getPlatform(QQ.NAME);
        qq.SSOSetting(false);  //设置false表示使用SSO授权方式
        qq.setPlatformActionListener(this); // 设置分享事件回调
        qq.authorize();
        qq.showUser(null);
    }
    private void getWeixinAuth(){
        ShareSDK.initSDK(this);
        Platform weixin = ShareSDK.getPlatform(this,Wechat.NAME);
        weixin.SSOSetting(false);
        weixin.setPlatformActionListener(this);
        weixin.authorize();
        weixin.showUser(null);

    }

    private void thirdLogin(String uid, String uname,String uicon, String pl) {
//        String url = this.getString(R.string.host_url) + "/Home/Login/thirdLogin";
        String url = this.getString(R.string.localhost_url) + "/Home/Login/thirdLogin";


        Log.d("url", url);
        Map<String, String> map = new HashMap<>();
        //存入Post数据,hah
        map.put("user_thirdinfo", pl + '_' + uid);
        map.put("userinfo_nickname", uname);
        map.put("userinfo_avator",uicon);
        Log.e("testssss", map.get("user_thirdinfo"));
        AyonelCustomRequest jsonObjectRequest = new AyonelCustomRequest(Request.Method.POST, url, map,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("return Json", "response =" + response);
                        int status = response.optInt("status", 0);
                        if (status == 1) {
                            showToast("登陆成功");
                        } else {
                           showToast("登录失败");
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG", error.getMessage(), error);
                showToast("登录失败，返回error");
            }
        });
        loginQueue.add(jsonObjectRequest);
    }
    // 提交用户信息
//    private void registerUser(String country, String phone) {
//        Random rnd = new Random();
//        int id = Math.abs(rnd.nextInt());
//        String uid = String.valueOf(id);
//        String nickName = "SmsSDK_User_" + uid;
//        String avatar = AVATARS[id % 12];
//        SMSSDK.submitUserInfo(uid, nickName, avatar, country, phone);
//    }
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
    private void showShare() {
        ShareSDK.initSDK(context);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

// 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
//        oks.setTitle(getString(R.string.share));
//        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
//        oks.setTitleUrl("http://sharesdk.cn");
//        // text是分享文本，所有平台都需要这个字段
//        oks.setText("我是分享文本");
//        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
//        oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
//        // url仅在微信（包括好友和朋友圈）中使用
//        oks.setUrl("http://sharesdk.cn");
//        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
//        oks.setComment("我是测试评论文本");
//        // site是分享此内容的网站名称，仅在QQ空间使用
//        oks.setSite(getString(R.string.app_name));
//        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
//        oks.setSiteUrl("http://sharesdk.cn");

        oks.setTitle("分享标题--Title");
        oks.setTitleUrl("http://mob.com");
        oks.setText("分享测试文--Text");
        oks.setImageUrl("http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg");
//
// 启动分享GUI
        oks.show(context);
    }
    // 在状态栏提示分享操作,the notification on the status bar
    private void showNotification(long cancelTime, String text) {
        try {
            Context app = getApplicationContext();
            NotificationManager nm = (NotificationManager) app
                    .getSystemService(Context.NOTIFICATION_SERVICE);
            final int id = Integer.MAX_VALUE / 13 + 1;
            nm.cancel(id);

            long when = System.currentTimeMillis();
            Notification notification = new Notification(R.drawable.ic_launcher, text, when);
            PendingIntent pi = PendingIntent.getActivity(app, 0, new Intent(), 0);
            notification.setLatestEventInfo(app, "sharesdk test", text, pi);
            notification.flags = Notification.FLAG_AUTO_CANCEL;
            nm.notify(id, notification);

            if (cancelTime > 0) {
                Message msg = new Message();
                msg.what = MSG_CANCEL_NOTIFY;
                msg.obj = nm;
                msg.arg1 = id;
                UIHandler.sendMessageDelayed(msg, cancelTime, this);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
