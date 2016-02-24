package com.ciyuan.dimera.androidapp.fragment;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ciyuan.dimera.androidapp.R;

/**
 * ClassName : MessageFragment
 * Author   : 史翔宇
 * Time     : 2015/12/1
 * Desc     :
 */
public class MessageFragment extends BaseFragment implements View.OnClickListener {
    private LinearLayout ll_zan;

    protected ImageView iv_goback;
    protected TextView tv_title;
    protected ImageView iv_window;
    public MessageFragment(Context context) {
        super(context);
    }

    @Override
    protected View initView() {
        View rootView=View.inflate(mContext, R.layout.tap_message,null);
        iv_goback= (ImageView) rootView.findViewById(R.id.iv_goback);
        tv_title= (TextView) rootView.findViewById(R.id.tv_title);
        iv_window= (ImageView) rootView.findViewById(R.id.iv_window);
        ll_zan = (LinearLayout) rootView.findViewById(R.id.ll_zan);//给点赞
        ll_zan.setOnClickListener(this);//给点赞添加点击事件
        iv_goback.setVisibility(View.GONE);
        iv_window.setVisibility(View.GONE);
        tv_title.setText("消息中心");
        return rootView;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_zan:
                MessageLikeFragment fragment1 = new MessageLikeFragment(getActivity());
                FragmentManager fm=getChildFragmentManager();
                FragmentTransaction ft=fm.beginTransaction();
                ft.addToBackStack("tag");
                ft.replace(R.id.ll_tab_me, fragment1).commit();

        }
    }
}
