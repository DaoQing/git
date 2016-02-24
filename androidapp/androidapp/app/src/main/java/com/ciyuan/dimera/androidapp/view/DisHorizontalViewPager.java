package com.ciyuan.dimera.androidapp.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * ClassName : DisHorizontalViewPager
 * Author   : 史翔宇
 * Time     : 2015/12/1
 * Desc     :禁止左右滑动的ViewPager
 */
public class DisHorizontalViewPager extends ViewPager {



    public DisHorizontalViewPager(Context context) {
        super(context);
    }

    public DisHorizontalViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return false;
    }
}
