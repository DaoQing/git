package com.ciyuan.dimera.androidapp.fragment;


import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ciyuan.dimera.androidapp.R;

public class MessageLikeFragment extends BaseFragment {
    protected ImageView iv_goback;
    protected TextView tv_title;
    protected ImageView iv_window;


    public MessageLikeFragment(Context context) {
        super(context);
    }

    @Override
    protected View initView() {
        View rootView=View.inflate(mContext, R.layout.item_message_like,null);
        tv_title = (TextView)rootView.findViewById(R.id.tv_title);
        tv_title.setText("点 赞");
        return rootView;
    }
}
