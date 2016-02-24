package com.ciyuan.dimera.androidapp.fragment;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.AbsListView;
import android.widget.LinearLayout;

import com.ciyuan.dimera.androidapp.R;
import com.ciyuan.dimera.androidapp.adapter.CircleAdapter;
import com.ciyuan.dimera.androidapp.utils.UIUtils;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.nineoldandroids.animation.ObjectAnimator;

/**
 * A simple {@link Fragment} subclass.
 */
public class CircleFragment extends BaseFragment implements AbsListView.OnScrollListener {

    private LinearLayout search_layout;
    private PullToRefreshListView mlv;
    private CircleAdapter mAdapter;
    private boolean mIsShowTitle = true;
    private int lastVisibleItemPosition = 0;// 标记上次滑动位置

    public CircleFragment(Context context) {
        super(context);
    }

    @Override
    protected View initView() {
        View rootView = View.inflate(UIUtils.getContext(), R.layout.tap_circle, null);
        search_layout = (LinearLayout) rootView.findViewById(R.id.search_layout);
        mlv = (PullToRefreshListView) rootView.findViewById(R.id.circle_lv);

        AbsListView.LayoutParams params = new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, 100);
        View headerView = new View(getActivity());
        headerView.setLayoutParams(params);
        mlv.getRefreshableView().addHeaderView(headerView);
        mlv.setMode(PullToRefreshBase.Mode.BOTH);
        return rootView;
    }

    @Override
    protected void initData() {
        mAdapter = new CircleAdapter();
        mlv.setAdapter(mAdapter);
        mlv.setOnScrollListener(this);
    }

    @Override
    protected void loadData() {


    }


    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (firstVisibleItem > lastVisibleItemPosition) {// 上滑
            if (mIsShowTitle) {
                ObjectAnimator.ofFloat(search_layout, "y", 0, -100f).setDuration(300).start();
                mIsShowTitle = false;
            }

        } else if (firstVisibleItem < lastVisibleItemPosition) {// 下滑
            if (!mIsShowTitle) {
                ObjectAnimator.ofFloat(search_layout, "y", -100, 0).setDuration(300).start();
                mIsShowTitle = true;
            }
        } else {
            return;
        }
        lastVisibleItemPosition = firstVisibleItem;
    }
}