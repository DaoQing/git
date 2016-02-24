package com.ciyuan.dimera.androidapp.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * ClassName : BaseFragment
 * Author   : 史翔宇
 * Time     : 2015/11/18
 * Desc     :
 */
public abstract class BaseFragment extends Fragment {

    Context mContext;
    public BaseFragment(Context context){
        mContext=context;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return initView();
    }

    protected abstract View initView() ;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        loadData();
        initData();
        super.onActivityCreated(savedInstanceState);

    }

    /**
     * 如果子孩子有数据加载，就复写该方法
     */
    protected void loadData(){}
    protected void initData(){}
}
