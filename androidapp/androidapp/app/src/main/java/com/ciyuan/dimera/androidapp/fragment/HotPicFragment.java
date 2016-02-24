package com.ciyuan.dimera.androidapp.fragment;

import android.content.Context;
import android.view.View;
import android.widget.RadioGroup;

import com.ciyuan.dimera.androidapp.R;
import com.ciyuan.dimera.androidapp.adapter.WaterfallAdapter;
import com.ciyuan.dimera.androidapp.bean.HomeBean;
import com.ciyuan.dimera.androidapp.bean.HotPictureBean;
import com.ciyuan.dimera.androidapp.holder.HomeAutoSwitchPicHolder;
import com.ciyuan.dimera.androidapp.http.HomeProtocol;
import com.ciyuan.dimera.androidapp.utils.UIUtils;
import com.ciyuan.dimera.androidapp.view.PullToRefreshStaggeredGridView;
import com.etsy.android.grid.StaggeredGridView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName : HotPicFragment
 * Author   : 史翔宇
 * Time     : 2015/12/10
 * Desc     :
 */
public class HotPicFragment extends BaseFragment implements PullToRefreshBase.OnRefreshListener2<StaggeredGridView>, RadioGroup.OnCheckedChangeListener {

    public HomeProtocol mProtocol;
    public List<String> mAutoSwitchPics;//轮播图资源
    private List<HotPictureBean> pictures;
    private HomeAutoSwitchPicHolder mAutoSwitchPicHolder;
    private RadioGroup mRadioGroup;
    /** 下来刷新 **/
    private PullToRefreshStaggeredGridView mPullToRefreshStaggerdGridView;
    private StaggeredGridView mDongTaiGridView;
    private WaterfallAdapter mAdapter;
    public HotPicFragment(Context context) {
        super(context);
    }

    @Override
    protected View initView() {
        System.out.println("HotPicFragment的onCreateView方法");
        View view=View.inflate(UIUtils.getContext(), R.layout.fragment_home,null);
        mPullToRefreshStaggerdGridView= (PullToRefreshStaggeredGridView) view.findViewById(R.id.home_pull_grid_view);

        mPullToRefreshStaggerdGridView.setMode(PullToRefreshBase.Mode.BOTH);
        mPullToRefreshStaggerdGridView.setOnRefreshListener(this);
        mDongTaiGridView=mPullToRefreshStaggerdGridView.getRefreshableView();

        //创建轮播的holder
         mAutoSwitchPicHolder = new HomeAutoSwitchPicHolder(mContext);
         View headerView=mAutoSwitchPicHolder.getRootView();
         mRadioGroup.setOnCheckedChangeListener(this);
         mDongTaiGridView.addHeaderView(headerView);

        mAdapter=new WaterfallAdapter(mContext,getData());
        mDongTaiGridView.setAdapter(mAdapter);

        mPullToRefreshStaggerdGridView.getLoadingLayoutProxy(true,false).setPullLabel("下拉刷新");
        mPullToRefreshStaggerdGridView.getLoadingLayoutProxy(true,false).setRefreshingLabel("正在刷新");
        mPullToRefreshStaggerdGridView.getLoadingLayoutProxy(true,false).setReleaseLabel("松开刷新");

        mPullToRefreshStaggerdGridView.getLoadingLayoutProxy(false,true).setPullLabel("上拉加载更多");
        mPullToRefreshStaggerdGridView.getLoadingLayoutProxy(false,true).setRefreshingLabel("正在加载");
        mPullToRefreshStaggerdGridView.getLoadingLayoutProxy(false,true).setReleaseLabel("松开加载更多");
        return view;
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase<StaggeredGridView> refreshView) {


        HotPictureBean bean=new HotPictureBean();
        bean.setDynamic_picurl("http://www.dimeratech.com/Public/Uploads/pictures/carousel_pic_1.jpg");
        bean.setDynamic_id(10);
        bean.setPublish_user("Orlando");
        pictures.add(0,bean);


        bean=new HotPictureBean();
        bean.setDynamic_picurl("http://www.dimeratech.com/Public/Uploads/pictures/carousel_pic_2.jpg");
        bean.setDynamic_id(10);
        bean.setPublish_user("Sun");
        pictures.add(0,bean);

        bean=new HotPictureBean();
        bean.setDynamic_picurl("http://www.dimeratech.com/Public/Uploads/pictures/carousel_pic_3.jpg");
        bean.setDynamic_id(10);
        bean.setPublish_user("Merry");
        pictures.add(0,bean);

        mAdapter.notifyDataSetChanged();
        mPullToRefreshStaggerdGridView.onRefreshComplete();
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


    @Override
    protected void loadData() {

        System.out.println("进入loadData()");
        mProtocol = new HomeProtocol();
       // mProtocol.setOnDataFromNetListener(this);
        try {
            System.out.println("开始加载数据");
            HomeBean bean = mProtocol.getData();
            System.out.println("HomeBean=" + bean);

            mAutoSwitchPics = bean.getCarousel_pic();

            System.out.println("在onResponse的里面输出：mAutoSwitchPics=" + mAutoSwitchPics);

            mAutoSwitchPicHolder.setData(mAutoSwitchPics);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("联网出错");
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

    }

//    @Override
//    public void setDataFromNet(Object data) {
//
//        mAutoSwitchPics = ((HomeBean) data).getCarousel_pic();
//        System.out.println("通过回调函数输出：mAutoSwitchPics=" + mAutoSwitchPics);
//        mAutoSwitchPicHolder.setData(mAutoSwitchPics);
//    }

}
