package com.ciyuan.dimera.androidapp.fragment;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ciyuan.dimera.androidapp.R;

/**
 * Created by quqing on 2016-01-21.
 */
public class ZanFragment extends BaseFragment {
    protected ImageView iv_goback;
    protected TextView tv_title;
    protected ImageView iv_window;
    public ZanFragment(Context context) {
        super(context);
    }

    @Override
    protected View initView() {
        View viewRoot = View.inflate(mContext, R.layout.item_message_like,null);
        return null;
    }
}
