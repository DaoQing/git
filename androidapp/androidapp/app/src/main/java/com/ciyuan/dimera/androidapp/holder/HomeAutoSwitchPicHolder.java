package com.ciyuan.dimera.androidapp.holder;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ciyuan.dimera.androidapp.R;
import com.ciyuan.dimera.androidapp.utils.Constans;
import com.ciyuan.dimera.androidapp.utils.UIUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

public class HomeAutoSwitchPicHolder extends BaseHolder<List<String>>
        implements ViewPager.OnPageChangeListener {

    @ViewInject(R.id.item_home_viewPager)
    private ViewPager mPager;

    private HomeAutoSwitchPicAdapter adapter;

    @ViewInject(R.id.item_home_point_container)
    private LinearLayout mPointContainer;

    private List<String> mPictures;
    private AutoSwitchTask mSwitchTask;
    public HomeAutoSwitchPicHolder(Context context) {
        super(context);

    }


    protected View initView() {
        View view = View.inflate(UIUtils.getContext(), R.layout.home_autoswitchpic, null);
        ViewUtils.inject(this, view);
        return view;
    }


    protected void refreshUI(List<String> data) {
        this.mPictures = data;
        adapter=new HomeAutoSwitchPicAdapter();
        this.mPager.setAdapter(adapter);
        addPointToContainer(data);
        this.mPager.setOnPageChangeListener(this);
        int middle = Integer.MAX_VALUE/1000;
        int extra = middle % mPictures.size();
        this.mPager.setCurrentItem(middle - extra);

        if (mSwitchTask == null)
            mSwitchTask = new AutoSwitchTask();
        //开始轮播
       this.mSwitchTask.start();




        // 给ViewPager设置touch的监听
        mPager.setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // 希望轮播停止
                        mSwitchTask.stop();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        // 希望播放
                        mSwitchTask.start();
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
    }


    /**
     * 给容器添加点
     * @param data
     */
    protected void addPointToContainer(List<String> data) {
        mPointContainer.removeAllViews();

        for (int i = 0; i < data.size(); i++) {
            View view = new View(UIUtils.getContext());
            view.setBackgroundResource(R.drawable.home_point_normal);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(UIUtils.dip2px(6), UIUtils.dip2px(6));
            if (i != 0) {
                params.leftMargin = UIUtils.dip2px(8);
                params.bottomMargin = UIUtils.dip2px(8);
            } else {
                view.setBackgroundResource(R.drawable.home_point_select);
            }

            mPointContainer.addView(view,params);
        }
    }

    /**
     * 轮播任务
     */
    class AutoSwitchTask implements Runnable {

        public void run() {
            int item = mPager.getCurrentItem();
            mPager.setCurrentItem(++item);
            UIUtils.postDelayed(this, 3000);
        }

        public void start() {
            stop();
            UIUtils.postDelayed(this, 3000);
        }

        public void stop() {
            UIUtils.removeCallbacks(this);
        }
    }

    class HomeAutoSwitchPicAdapter extends PagerAdapter {

        @Override
        public int getCount() {
           return mPictures != null ?Integer.MAX_VALUE : 0;
          //return mPictures != null ? mPictures.size() : 0;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
           position = position % mPictures.size();
            ImageView iv = new ImageView(UIUtils.getContext());
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            // 设置网络图片

            // BitmapHelper.display(iv, mPictures.get(position));
           ImageLoader.getInstance().displayImage(Constans.IMAGE_BASE_URL+mPictures.get(position),iv);
            container.addView(iv);
            return iv;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {

            container.removeView((View) object);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

        // 页面选中时

      position = position % mPictures.size();

        int count = mPointContainer.getChildCount();
        for (int i = 0; i < count; i++)
        {
            View view = mPointContainer.getChildAt(i);
            view.setBackgroundResource(i == position ? R.drawable.home_point_select
                    : R.drawable.point_normal);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

}