package com.ciyuan.dimera.androidapp.holder;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.TextView;

import com.ciyuan.dimera.androidapp.R;
import com.ciyuan.dimera.androidapp.bean.HotPictureBean;
import com.ciyuan.dimera.androidapp.utils.UIUtils;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HotPicHolder extends BaseHolder<List<HotPictureBean>>
        implements AdapterView.OnItemSelectedListener {

    private GalleryAdapter adapter;

    @Bind(R.id.home_tv_title)
    public TextView mTitle;
    @Bind(R.id.home_gallery)
    public Gallery mGallery;
    @Bind(R.id.tv_more)
    public TextView mMore;

    private List<HotPictureBean> mPictures;

    public HotPicHolder(Context context) {
        super(context);
    }

    protected View initView() {
        View view = View.inflate(UIUtils.getContext(), R.layout.home_gallery, null);
        ButterKnife.bind(this, view);

        return view;
    }

    protected void refreshUI(List<HotPictureBean> data) {
        mPictures = data;
        adapter = new GalleryAdapter();
        mGallery.setAdapter(adapter);
        mGallery.setOnItemSelectedListener(this);
        int middle = Integer.MAX_VALUE / 2;
        int extra = middle % mPictures.size();
        mGallery.setSelection(middle - extra);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        position = position % mPictures.size();
        adapter.setSelectItem(position);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    class GalleryAdapter extends BaseAdapter {
        private int selectItem;

        public int getCount() {
            return mPictures != null ? Integer.MAX_VALUE : 0;
            // return mPictures != null ? mPictures.size() : 0;
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {

            position = position % mPictures.size();
            ImageView iv;
            if (convertView == null) {

                iv = new ImageView(UIUtils.getContext());
            } else {
                iv = (ImageView) convertView;
            }

            //使用ImageLoader加载图片

               iv.setScaleType(ImageView.ScaleType.FIT_XY);

            ImageLoader.getInstance().displayImage("http://" + mPictures.get(position).getDynamic_picurl(), iv, getDefaultDisplayOptionOfNormal());

            if (selectItem == position) {
                Animation animation = AnimationUtils.loadAnimation(UIUtils.getContext(), R.anim.gallery_scale);
                iv.setLayoutParams(new Gallery.LayoutParams(UIUtils.dip2px(170), UIUtils.dip2px(190)));
                iv.startAnimation(animation);

            } else {
                iv.setLayoutParams(new Gallery.LayoutParams(UIUtils.dip2px(140), UIUtils.dip2px(160)));
            }
            return iv;
        }

        public void setSelectItem(int position) {
            if (selectItem != position) {
                selectItem = position;
                notifyDataSetChanged();
            }
        }
    }

    private DisplayImageOptions getDefaultDisplayOptionOfNormal() {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageForEmptyUri(R.drawable.icon)  // 设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.drawable.icon)     //  设置图片加载或解码过程中发生错误显示的图片
                .cacheInMemory(true) // 设置下载的图片是否缓存在内存中
                .cacheOnDisk(true) // 设置下载的图片是否缓存在SD卡中
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();

        return options;
    }
}