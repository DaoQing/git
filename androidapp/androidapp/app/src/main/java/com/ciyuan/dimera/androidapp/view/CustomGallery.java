package com.ciyuan.dimera.androidapp.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Gallery;

/**
 * ClassName : CustomGallery
 * Author   : 史翔宇
 * Time     : 2015/11/30
 * Desc     :
 */
 class CustomGallery extends Gallery {
    public CustomGallery(Context context) {
        super(context);
    }

    public CustomGallery(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomGallery(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CustomGallery(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }




    private boolean isScrollingLeft(MotionEvent e1, MotionEvent e2)
    {
        return e2.getX() > e1.getX();
    }
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

        return false;
    }

   private float downX;
   private float downY;
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                downX=ev.getX();
                downY=ev.getY();
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
        }
        return super.dispatchTouchEvent(ev);
    }
}
