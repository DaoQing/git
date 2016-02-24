package com.ciyuan.dimera.androidapp.fragment;

import android.content.Context;
import android.view.View;

import com.ciyuan.dimera.androidapp.R;
import com.ciyuan.dimera.androidapp.adapter.WaterfallAdapter;
import com.ciyuan.dimera.androidapp.bean.HotPictureBean;
import com.ciyuan.dimera.androidapp.utils.UIUtils;
import com.ciyuan.dimera.androidapp.view.PullToRefreshStaggeredGridView;
import com.etsy.android.grid.StaggeredGridView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName : NewModelFragment
 * Author   : 史翔宇
 * Time     : 2015/12/10
 * Desc     :
 */
public class NewModelFragment extends BaseFragment implements PullToRefreshBase.OnRefreshListener2<StaggeredGridView> {
    private List<HotPictureBean> pictures;

    /** 下来刷新 **/
    private PullToRefreshStaggeredGridView mPullToRefreshStaggerdGridView;
    private StaggeredGridView mDongTaiGridView;
    private WaterfallAdapter mAdapter;
    public NewModelFragment(Context context) {
        super(context);
    }

    @Override
    protected View initView() {
        View view=View.inflate(UIUtils.getContext(), R.layout.fragment_home,null);
        mPullToRefreshStaggerdGridView= (PullToRefreshStaggeredGridView) view.findViewById(R.id.home_pull_grid_view);
        return view;
    }

    @Override
    protected void initData() {
        mPullToRefreshStaggerdGridView.setMode(PullToRefreshBase.Mode.BOTH);
        mPullToRefreshStaggerdGridView.setOnRefreshListener(this);
        mDongTaiGridView=mPullToRefreshStaggerdGridView.getRefreshableView();
        mAdapter=new WaterfallAdapter(mContext,getData());
        mDongTaiGridView.setAdapter(mAdapter);
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase<StaggeredGridView> refreshView) {

    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase<StaggeredGridView> refreshView) {

    }


    public List<HotPictureBean> getData(){
        pictures=new ArrayList<HotPictureBean>();
        HotPictureBean bean=new HotPictureBean();
        bean.setDynamic_picurl("http://www.dimeratech.com/Public/Uploads/dynamic_pic/c51ce410c124a10e0db5e4b97fc2af39.png");
        bean.setDynamic_id(10);
        bean.setPublish_user("Orlando");
        pictures.add(bean);


        bean=new HotPictureBean();
        bean.setDynamic_picurl("http://www.dimeratech.com/Public/Uploads/dynamic_pic/45c48cce2e2d7fbdea1afc51c7c6ad26.png");
        bean.setDynamic_id(10);
        bean.setPublish_user("Sun");
        pictures.add(bean);

        bean=new HotPictureBean();
        bean.setDynamic_picurl("http://www.dimeratech.com/Public/Uploads/dynamic_pic/6512bd43d9caa6e02c990b0a82652dca.png");
        bean.setDynamic_id(10);
        bean.setPublish_user("Merry");
        pictures.add(bean);

        bean=new HotPictureBean();
        bean.setDynamic_picurl("http://www.dimeratech.com/Public/Uploads/dynamic_pic/45c48cce2e2d7fbdea1afc51c7c6ad26.png");
        bean.setDynamic_id(10);
        bean.setPublish_user("Sun");
        pictures.add(bean);

        bean=new HotPictureBean();
        bean.setDynamic_picurl("http://www.dimeratech.com/Public/Uploads/dynamic_pic/6512bd43d9caa6e02c990b0a82652dca.png");
        bean.setDynamic_id(10);
        bean.setPublish_user("Merry");
        pictures.add(bean);

        bean=new HotPictureBean();
        bean.setDynamic_picurl("http://www.dimeratech.com/Public/Uploads/dynamic_pic/45c48cce2e2d7fbdea1afc51c7c6ad26.png");
        bean.setDynamic_id(10);
        bean.setPublish_user("Sun");
        pictures.add(bean);

        bean=new HotPictureBean();
        bean.setDynamic_picurl("http://www.dimeratech.com/Public/Uploads/dynamic_pic/6512bd43d9caa6e02c990b0a82652dca.png");
        bean.setDynamic_id(10);
        bean.setPublish_user("Merry");
        pictures.add(bean);

        bean=new HotPictureBean();
        bean.setDynamic_picurl("http://www.dimeratech.com/Public/Uploads/dynamic_pic/45c48cce2e2d7fbdea1afc51c7c6ad26.png");
        bean.setDynamic_id(10);
        bean.setPublish_user("Sun");
        pictures.add(bean);

        bean=new HotPictureBean();
        bean.setDynamic_picurl("http://www.dimeratech.com/Public/Uploads/dynamic_pic/6512bd43d9caa6e02c990b0a82652dca.png");
        bean.setDynamic_id(10);
        bean.setPublish_user("Merry");
        pictures.add(bean);

        return  pictures;
    }
}
