package com.ciyuan.dimera.androidapp.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.ciyuan.dimera.androidapp.R;
import com.ciyuan.dimera.androidapp.adapter.CareAdapter;
import com.ciyuan.dimera.androidapp.adapter.FansAdapter;
import com.ciyuan.dimera.androidapp.adapter.PublishAdapter;
import com.ciyuan.dimera.androidapp.utils.UIUtils;

/**
 * ClassName : TaAct
 * Author   : 史翔宇
 * Time     : 2016/1/4
 * Desc     :
 */
public class TaAct extends FragmentActivity implements View.OnClickListener {


    private ListView mLv;
    private LinearLayout ll_publish;
    private LinearLayout ll_care;
    private LinearLayout ll_fans;

    private Button bt_pulish;
    private Button bt_care;
    private Button bt_fans;

    private TextView tv_publish_count;
    private TextView tv_care_count;
    private TextView tv_fans_count;

    private ImageView iv_goback;

    private BaseAdapter adapter;
    private PublishAdapter publishAdapter;
    private CareAdapter careAdapter;
    private FansAdapter fansAdapter;

    private int showType = 0;
    private LinearLayout tabs;
    private View headerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_ta);
        mLv = (ListView) findViewById(R.id.lv_ta);
        headerView = View.inflate(this, R.layout.ta_header, null);
        mLv.addHeaderView(headerView);
        findViewsByHeader(headerView);
        setupPager();
        setupTabs();
        setSelect(showType);
    }

    private void findViewsByHeader(View view) {
        ll_publish = (LinearLayout) view.findViewById(R.id.ll_publish);

        ll_care = (LinearLayout) view.findViewById(R.id.ll_care);
        ll_fans = (LinearLayout) view.findViewById(R.id.ll_fans);

        bt_pulish = (Button) view.findViewById(R.id.bt_pulish);
        bt_care = (Button) view.findViewById(R.id.bt_care);
        bt_fans = (Button) view.findViewById(R.id.bt_fans);

        tv_publish_count = (TextView) view.findViewById(R.id.tv_publish_count);
        tv_care_count = (TextView) view.findViewById(R.id.tv_care_count);
        tv_fans_count = (TextView) view.findViewById(R.id.tv_fans_count);

        iv_goback= (ImageView) view.findViewById(R.id.iv_goback);

        tabs = (LinearLayout) view.findViewById(R.id.tabs);
    }


    private void setupPager() {

    }

    private void setupTabs() {
        ll_publish.setOnClickListener(this);
        ll_care.setOnClickListener(this);
        ll_fans.setOnClickListener(this);
        iv_goback.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_publish:
                setSelect(0);
                break;
            case R.id.ll_care:
                setSelect(1);
                break;
            case R.id.ll_fans:
                setSelect(2);
                break;
            case R.id.iv_goback:
                finish();
                break;
        }
    }

    private void setSelect(int i) {
        //设置字体为选中色，切换内容区域
        checkImgs(i);
        setTab(i);
    }

    private void setTab(int i) {
        switch (i) {
            case 0:
                //  切换到列表1时：
                if (publishAdapter == null) {
                    publishAdapter = new PublishAdapter(this);
                    mLv.setAdapter(publishAdapter);
                } else {
                    // 刷新数据
                    //  publishAdapter.notifyInfo(list1);//此方法为adapter中写的方法
                    // 设置适配器
                    mLv.setAdapter(publishAdapter);
                }

                break;
            case 1:
                //  切换到列表2时：
                if ( careAdapter== null) {
                    careAdapter = new CareAdapter(this);
                    mLv.setAdapter(careAdapter);
                } else {
                    // 刷新数据
                    //  publishAdapter.notifyInfo(list1);//此方法为adapter中写的方法
                    // 设置适配器
                    mLv.setAdapter(careAdapter);
                }
                break;
            case 2:
                //  切换到列表2时：
                if (fansAdapter == null) {
                    fansAdapter = new FansAdapter(this);
                    mLv.setAdapter(fansAdapter);
                } else {
                    // 刷新数据
                    //  publishAdapter.notifyInfo(list1);//此方法为adapter中写的方法
                    // 设置适配器
                    mLv.setAdapter(fansAdapter);
                }
                break;
        }
    }

    private void checkImgs(int i) {
        resetImgs();

        switch (i) {
            case 0:
                bt_pulish.setTextColor(UIUtils.getColor(R.color.light_red));
                tv_publish_count.setTextColor(UIUtils.getColor(R.color.light_red));
                break;
            case 1:
                bt_care.setTextColor(UIUtils.getColor(R.color.light_red));
                tv_care_count.setTextColor(UIUtils.getColor(R.color.light_red));
                break;
            case 2:
                bt_fans.setTextColor(UIUtils.getColor(R.color.light_red));
                tv_fans_count.setTextColor(UIUtils.getColor(R.color.light_red));
                break;
        }
    }

    private void resetImgs() {
        //重置字体颜色为未选中色
        bt_pulish.setTextColor(UIUtils.getColor(R.color.white));
        bt_care.setTextColor(UIUtils.getColor(R.color.white));
        bt_fans.setTextColor(UIUtils.getColor(R.color.white));

        tv_publish_count.setTextColor(UIUtils.getColor(R.color.white));
        tv_care_count.setTextColor(UIUtils.getColor(R.color.white));
        tv_fans_count.setTextColor(UIUtils.getColor(R.color.white));
    }
}
