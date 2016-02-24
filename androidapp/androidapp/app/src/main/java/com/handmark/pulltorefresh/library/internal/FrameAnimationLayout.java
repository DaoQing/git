package com.handmark.pulltorefresh.library.internal;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;

import com.ciyuan.dimera.androidapp.R;
import com.handmark.pulltorefresh.library.PullToRefreshBase;

/**
 * ClassName : FrameAnimationLayout
 * Author   : 史翔宇
 * Time     : 2015/12/11
 * Desc     :
 */
public class FrameAnimationLayout extends LoadingLayout {

    private AnimationDrawable mAnimationDrawable;

    public FrameAnimationLayout(Context context, PullToRefreshBase.Mode mode, PullToRefreshBase.Orientation scrollDirection, TypedArray attrs) {
        super(context, mode, scrollDirection, attrs);
        mHeaderImage.setImageResource(R.anim.loading);
        mAnimationDrawable = (AnimationDrawable) mHeaderImage.getDrawable();
    }


    @Override
    protected int getDefaultDrawableResId() {
        return R.drawable.pull_anim_pic0;
    }

    @Override
    protected void onLoadingDrawableSet(Drawable imageDrawable) {
    }

    @Override
    protected void onPullImpl(float scaleOfLayout) {
    }

    @Override
    protected void pullToRefreshImpl() {
    }

    @Override
    protected void refreshingImpl() {
        mAnimationDrawable.start();
    }

    @Override
    protected void releaseToRefreshImpl() {
    }

    @Override
    protected void resetImpl() {
    }
}
