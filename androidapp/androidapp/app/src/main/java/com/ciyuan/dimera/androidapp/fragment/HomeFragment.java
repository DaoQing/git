package com.ciyuan.dimera.androidapp.fragment;


import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

import com.ciyuan.dimera.androidapp.R;
import com.ciyuan.dimera.androidapp.activity.HotPicDetailAct;
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


public class HomeFragment extends BaseFragment implements PullToRefreshBase.OnRefreshListener2<StaggeredGridView>, AdapterView.OnItemClickListener, View.OnClickListener {

    public HomeProtocol mProtocol;
    public List<String> mAutoSwitchPics;//轮播图资源
    private List<HotPictureBean> pictures;
    private HomeAutoSwitchPicHolder mAutoSwitchPicHolder;
    private Button btn_hotpic;
    private Button btn_newmodel;
    private int showType = 1;
    /**
     * 下来刷新
     **/
    private PullToRefreshStaggeredGridView mPullToRefreshStaggerdGridView;
    private StaggeredGridView mDongTaiGridView;
    private WaterfallAdapter mAdapter;
    private WaterfallAdapter HotPicAdapter;
    private WaterfallAdapter NewModelAdapter;
    public Context mActivity;

    public HomeFragment(Context context) {
        super(context);
    }

    @Override
    protected View initView() {
        System.out.println("进入HomeFragment的initView()方法中——————————————————————————————————————————————————————");
        View rootView = View.inflate(UIUtils.getContext(), R.layout.fragment_home, null);
        mPullToRefreshStaggerdGridView = (PullToRefreshStaggeredGridView) rootView.findViewById(R.id.home_pull_grid_view);

        mPullToRefreshStaggerdGridView.setMode(PullToRefreshBase.Mode.BOTH);
        mPullToRefreshStaggerdGridView.setOnRefreshListener(this);
        mDongTaiGridView = mPullToRefreshStaggerdGridView.getRefreshableView();

        //创建轮播的holder
        System.out.println("创建轮播的holder——————————————————————————————————————————————————————");
        mAutoSwitchPicHolder = new HomeAutoSwitchPicHolder(mContext);
        View headerView = mAutoSwitchPicHolder.getRootView();
        //  mRadioGroup = (RadioGroup) headerView.findViewById(R.id.home_rg);
        //  mRadioGroup.setOnCheckedChangeListener(this);
        btn_hotpic = (Button) headerView.findViewById(R.id.home_btn_pic);
        btn_newmodel = (Button) headerView.findViewById(R.id.home_btn_model);
        btn_hotpic.setOnClickListener(this);
        btn_newmodel.setOnClickListener(this);
        System.out.println("添加headerView到listView中——————————————————————————————————————————————————————");
        mDongTaiGridView.addHeaderView(headerView);
        System.out.println("选中home_rb_pic—————————————————————————————————————————————————————");
        setSelect(showType);

        mDongTaiGridView.setOnItemClickListener(this);

        mPullToRefreshStaggerdGridView.getLoadingLayoutProxy(true, false).setPullLabel("下拉刷新");
        mPullToRefreshStaggerdGridView.getLoadingLayoutProxy(true, false).setRefreshingLabel("正在刷新");
        mPullToRefreshStaggerdGridView.getLoadingLayoutProxy(true, false).setReleaseLabel("松开刷新");

        mPullToRefreshStaggerdGridView.getLoadingLayoutProxy(false, true).setPullLabel("上拉加载更多");
        mPullToRefreshStaggerdGridView.getLoadingLayoutProxy(false, true).setRefreshingLabel("正在加载");
        mPullToRefreshStaggerdGridView.getLoadingLayoutProxy(false, true).setReleaseLabel("松开加载更多");


        return rootView;

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase<StaggeredGridView> refreshView) {


        HotPictureBean bean = new HotPictureBean();
        bean.setDynamic_picurl("http://www.dimeratech.com/Public/Uploads/pictures/carousel_pic_1.jpg");
        bean.setDynamic_id(10);
        bean.setPublish_user("Orlando");
        pictures.add(0, bean);


        bean = new HotPictureBean();
        bean.setDynamic_picurl("http://www.dimeratech.com/Public/Uploads/pictures/carousel_pic_2.jpg");
        bean.setDynamic_id(10);
        bean.setPublish_user("Sun");
        pictures.add(0, bean);

        bean = new HotPictureBean();
        bean.setDynamic_picurl("http://www.dimeratech.com/Public/Uploads/pictures/carousel_pic_3.jpg");
        bean.setDynamic_id(10);
        bean.setPublish_user("Merry");
        pictures.add(0, bean);

        mAdapter.notifyDataSetChanged();
        mPullToRefreshStaggerdGridView.onRefreshComplete();
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase<StaggeredGridView> refreshView) {

    }


    public List<HotPictureBean> getData() {
        pictures = new ArrayList<HotPictureBean>();
        HotPictureBean bean = new HotPictureBean();
        bean.setDynamic_picurl("http://www.dimeratech.com/Public/Uploads/dynamic_pic/c51ce410c124a10e0db5e4b97fc2af39.png");
        bean.setDynamic_id(10);
        bean.setPublish_user("Orlando");
        pictures.add(bean);


        bean = new HotPictureBean();
        bean.setDynamic_picurl("http://www.dimeratech.com/Public/Uploads/dynamic_pic/45c48cce2e2d7fbdea1afc51c7c6ad26.png");
        bean.setDynamic_id(10);
        bean.setPublish_user("Sun");
        pictures.add(bean);

        bean = new HotPictureBean();
        bean.setDynamic_picurl("http://www.dimeratech.com/Public/Uploads/dynamic_pic/6512bd43d9caa6e02c990b0a82652dca.png");
        bean.setDynamic_id(10);
        bean.setPublish_user("Merry");
        pictures.add(bean);

        bean = new HotPictureBean();
        bean.setDynamic_picurl("http://www.dimeratech.com/Public/Uploads/dynamic_pic/45c48cce2e2d7fbdea1afc51c7c6ad26.png");
        bean.setDynamic_id(10);
        bean.setPublish_user("Sun");
        pictures.add(bean);

        bean = new HotPictureBean();
        bean.setDynamic_picurl("http://www.dimeratech.com/Public/Uploads/dynamic_pic/6512bd43d9caa6e02c990b0a82652dca.png");
        bean.setDynamic_id(10);
        bean.setPublish_user("Merry");
        pictures.add(bean);

        bean = new HotPictureBean();
        bean.setDynamic_picurl("http://www.dimeratech.com/Public/Uploads/dynamic_pic/45c48cce2e2d7fbdea1afc51c7c6ad26.png");
        bean.setDynamic_id(10);
        bean.setPublish_user("Sun");
        pictures.add(bean);

        bean = new HotPictureBean();
        bean.setDynamic_picurl("http://www.dimeratech.com/Public/Uploads/dynamic_pic/6512bd43d9caa6e02c990b0a82652dca.png");
        bean.setDynamic_id(10);
        bean.setPublish_user("Merry");
        pictures.add(bean);

        bean = new HotPictureBean();
        bean.setDynamic_picurl("http://www.dimeratech.com/Public/Uploads/dynamic_pic/45c48cce2e2d7fbdea1afc51c7c6ad26.png");
        bean.setDynamic_id(10);
        bean.setPublish_user("Sun");
        pictures.add(bean);

        bean = new HotPictureBean();
        bean.setDynamic_picurl("http://www.dimeratech.com/Public/Uploads/dynamic_pic/6512bd43d9caa6e02c990b0a82652dca.png");
        bean.setDynamic_id(10);
        bean.setPublish_user("Merry");
        pictures.add(bean);

        return pictures;
    }

    public List<HotPictureBean> getData2() {
        pictures = new ArrayList<HotPictureBean>();
        HotPictureBean bean = new HotPictureBean();
        bean.setDynamic_picurl("http://pic.qqmail.com/imagecache/20101016/1287208885.png");
        bean.setDynamic_id(10);
        bean.setPublish_user("Orlando");
        pictures.add(bean);

        bean = new HotPictureBean();
        bean.setDynamic_picurl("http://v1.qzone.cc/pic/201308/01/16/44/51fa1fd3d9f0d545.jpg!600x600.jpg");
        bean.setDynamic_id(10);
        bean.setPublish_user("Merry");
        pictures.add(bean);

        bean = new HotPictureBean();
        bean.setDynamic_picurl("http://img.blog.cctv.com/attachments/2009/02/810583_200902231053501.jpg");
        bean.setDynamic_id(10);
        bean.setPublish_user("Merry");
        pictures.add(bean);


        return pictures;

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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent intent = new Intent(getActivity(), HotPicDetailAct.class);
        startActivity(intent);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.home_btn_pic:
                setSelect(1);
                break;
            case R.id.home_btn_model:
                setSelect(2);
                break;
        }


    }

    private void setSelect(int i) {
        //设置图片为亮色，切换内容区域
        showType = i;
        checkBtnBg(i);
        setTab(i);

    }

    private void checkBtnBg(int i) {
        resetBtnBg();

        switch (i) {
            case 1:
                btn_hotpic.setBackgroundResource(R.drawable.rb_shape_left_selected);
                btn_hotpic.setTextColor(UIUtils.getColor(R.color.white));
                break;
            case 2:
                btn_newmodel.setBackgroundResource(R.drawable.rb_shape_right_selected);
                btn_newmodel.setTextColor(UIUtils.getColor(R.color.white));
                break;
        }
    }

    private void resetBtnBg() {
        //设置Button的背景为默认
        btn_hotpic.setBackgroundResource(R.drawable.rb_shape_left_normal);
        btn_hotpic.setTextColor(UIUtils.getColor(R.color.yellow_top_title));
        btn_newmodel.setBackgroundResource(R.drawable.rb_shape_right_normal);
        btn_newmodel.setTextColor(UIUtils.getColor(R.color.yellow_top_title));


    }

    private void setTab(int i) {
        switch (i) {
            case 1:
                HotPicAdapter = new WaterfallAdapter(getActivity(), getData());
                mDongTaiGridView.setAdapter(HotPicAdapter);
                mAdapter=HotPicAdapter;
                break;
            case 2:
                NewModelAdapter = new WaterfallAdapter(getActivity(), getData2());
                mDongTaiGridView.setAdapter(NewModelAdapter);
                mAdapter=NewModelAdapter;
                break;
        }
    }


//    @Override
//    public void setDataFromNet(Object data) {
//
//        mAutoSwitchPics = ((HomeBean) data).getCarousel_pic();
//        System.out.println("通过回调函数输出：mAutoSwitchPics=" + mAutoSwitchPics);
//        mAutoSwitchPicHolder.setData(mAutoSwitchPics);
//    }

}
