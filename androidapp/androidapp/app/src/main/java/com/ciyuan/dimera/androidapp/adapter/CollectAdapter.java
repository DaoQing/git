package com.ciyuan.dimera.androidapp.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.ciyuan.dimera.androidapp.R;
import com.ciyuan.dimera.androidapp.utils.UIUtils;

/**
 * ClassName : CollectAdapter
 * Author   : 史翔宇
 * Time     : 2015/12/24
 * Desc     :
 */
public class CollectAdapter extends BaseAdapter {

    public  Activity mActivity;
    public  CollectAdapter(Activity activity){
        mActivity=activity;
    }
    private int[] imageId = new int[] {
            R.drawable.me_publish_item_img,R.drawable.me_publish_item_img, R.drawable.me_publish_item_img, R.drawable.me_publish_item_img,R.drawable.me_publish_item_img}; // 定义并初始化保存图片id的数组
    @Override
    public int getCount() {
        return 15;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(UIUtils.getContext(), R.layout.item_me_collect,null);
            holder = new ViewHolder();
            holder.gridView = (GridView) convertView
                    .findViewById(R.id.collect_gridView);
            holder.tiem = (TextView) convertView.findViewById(R.id.collect_time);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        View dividerLine=convertView.findViewById(R.id.me_collect_divider);
        if(position==0){
            dividerLine.setVisibility(View.INVISIBLE);
        }

        holder.tiem.setText("12月13日");
        holder.gridView.setAdapter(new GridViewAdapter());

        return convertView;
    }

    static class ViewHolder {

        TextView tiem;
        GridView gridView;
    }


    class GridViewAdapter extends BaseAdapter {

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageview; // 声明ImageView的对象
            if (convertView == null) {
                imageview = new ImageView(UIUtils.getContext()); // 实例化ImageView的对象
                imageview.setScaleType(ImageView.ScaleType.FIT_CENTER); // 设置缩放方式
                imageview.setLayoutParams(new GridView.LayoutParams(210,200));
              //  imageview.setPadding(5, 0, 5, 0); // 设置ImageView的内边距
            } else {
                imageview = (ImageView) convertView;
            }

            imageview.setBackgroundResource(imageId[position]); // 为ImageView设置要显示的图片
            return imageview; // 返回ImageView
        }


        @Override
        public long getItemId(int position) {
            //System.out.println("getItemId = " + position);
            return position;
        }


        @Override
        public Object getItem(int position) {
            return position;
        }


        @Override
        public int getCount() {
            return imageId.length;
        }


    }


}
