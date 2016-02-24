package com.ciyuan.dimera.androidapp.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.ciyuan.dimera.androidapp.R;
import com.ciyuan.dimera.androidapp.fragment.CircleFragment;
import com.ciyuan.dimera.androidapp.fragment.HomeFragment;
import com.ciyuan.dimera.androidapp.fragment.MessageFragment;
import com.ciyuan.dimera.androidapp.fragment.SettingFragment;
import com.unity3d.player.UnityPlayer;

import java.util.ArrayList;
import java.util.List;

public class IndexActivity extends FragmentActivity implements View.OnClickListener {

    private long exitTime = 0;
    private RadioButton mCamera;
    private Context mContext;

    private ViewPager mViewPager;
    private FragmentPagerAdapter mAdapter;//适配器
    private List<Fragment> mFragments;//数据源

    private RadioGroup mRg;
    private RadioButton mHome;
    private RadioButton mCircle;
    private RadioButton mMsg;
    private RadioButton mMe;

    private Button mUserBtn;


//    private Button mCamera;
//    private Button mLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        //设置状态栏为透明
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            Window window = getWindow();
//            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
//                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(Color.TRANSPARENT);
//            window.setNavigationBarColor(Color.TRANSPARENT);
//        }
        setContentView(R.layout.activity_index);
        mContext = this;
//        mCamera = (Button) findViewById(R.id.camera);
//        mCamera.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                UnityPlayer.UnitySendMessage("Main Camera", "showActivity", "ARCam");
//                IndexActivity.this.finish();
//            }
//        });
//        mLogin = (Button) findViewById(R.id.login);
//        mLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showUserActivity("PluginActivity");
//            }
//        });

        mCamera = (RadioButton) findViewById(R.id.rb_camera);
        mCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UnityPlayer.UnitySendMessage("Main Camera", "showActivity", "ARCam");
                IndexActivity.this.finish();
            }
        });

        //#############################################注销代码：翔宇

//        mUserBtn = (Button) findViewById(R.id.UserModelBtn);
//        mUserBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showUserActivity("PluginActivity");
//            }
//        });


        initView();
        initEvent();
        setSelect(0);
    }

    private void initEvent() {
        //为四个LinerLayout添加点击事件
        mHome.setOnClickListener(this);
        mCircle.setOnClickListener(this);
        mMsg.setOnClickListener(this);
        mMe.setOnClickListener(this);
    }

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.id_viewpager);

        mRg= (RadioGroup) findViewById(R.id.rg_bottom);
        mHome = (RadioButton) findViewById(R.id.rb_home);
        mCircle = (RadioButton) findViewById(R.id.rb_circle);
        mMsg = (RadioButton) findViewById(R.id.rb_msg);
        mMe = (RadioButton) findViewById(R.id.rb_me);
        //4个数据源初始化
        mFragments = new ArrayList<Fragment>();
        Fragment homePager = new HomeFragment(IndexActivity.this);
        Fragment circlePager = new CircleFragment(mContext);
        Fragment messagePager = new MessageFragment(mContext);
        Fragment mePager = new SettingFragment(mContext);
        mFragments.add(homePager);
        mFragments.add(circlePager);
        mFragments.add(messagePager);
        mFragments.add(mePager);
        //adapter初始化
        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {


            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }
        };
        mRg.check(R.id.rb_home);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int currentItem = mViewPager.getCurrentItem();


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    public void onClick(View v) {
       switch (v.getId()) {
           case R.id.rb_home :
               setSelect(0);
               break;
           case R.id.rb_circle:
               setSelect(1);
               break;
           case R.id.rb_msg:
               setSelect(2);
               break;
           case R.id.rb_me:
               setSelect(3);
               break;
       }
    }

    private void setSelect(int i) {
        mViewPager.setCurrentItem(i);

    }

    //显示用户登陆界面
    private void showUserActivity(String where) {
        Intent i = new Intent(mContext, AyonelMainActivity.class);
        i.putExtra("from_where", where);
        startActivity(i);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
            if((System.currentTimeMillis()-exitTime) > 2000){
                Toast.makeText(getApplicationContext(), "再按一次就退出了哦", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();


            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
