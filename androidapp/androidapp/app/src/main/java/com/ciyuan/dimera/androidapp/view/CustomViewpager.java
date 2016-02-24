package com.ciyuan.dimera.androidapp.view;

import android.content.Context;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * ClassName : CustomViewpager
 * Author   : 史翔宇
 * Time     : 2015/11/19
 * Desc     :自定义的VeiwPager，针对3.0以下适配的viewpager
 */
public class CustomViewpager extends ViewPager {

    private float downX;
    private float downY;

    public CustomViewpager(Context context) {
        super(context);
    }

    public CustomViewpager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            //如果小于3.0
            switch (ev.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    // 请求不要拦截
                    getParent().requestDisallowInterceptTouchEvent(true);

                    downX = ev.getX();
                    downY = ev.getY();

                    break;
                case MotionEvent.ACTION_MOVE:
                    float moveX = ev.getX();
                    float moveY = ev.getY();

                    float diffX = moveX - downX;
                    float diffY = moveY - downY;

                    if (Math.abs(diffX) > Math.abs(diffY)) {
                        //左右移动，请求父容器不要拦截
                        getParent().requestDisallowInterceptTouchEvent(true);
                    } else {
                        //上下移动，请求父容器要拦截
                        getParent().requestDisallowInterceptTouchEvent(false);
                    }
                    break;

                case MotionEvent.ACTION_UP:

                    break;

                default:
                    break;
            }
        }
        return super.dispatchTouchEvent(ev);
    }
}
