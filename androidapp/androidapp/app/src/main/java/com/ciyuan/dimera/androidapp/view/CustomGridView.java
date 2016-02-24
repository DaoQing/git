package com.ciyuan.dimera.androidapp.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * ClassName : CustomGridView
 * Author   : 史翔宇
 * Time     : 2015/12/24
 * Desc     :
 */
public class CustomGridView extends GridView {
    public CustomGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    /**
     * 设置不滚动
     */

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
