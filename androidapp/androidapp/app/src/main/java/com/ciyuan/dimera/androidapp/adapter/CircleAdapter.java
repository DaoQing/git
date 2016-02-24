package com.ciyuan.dimera.androidapp.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ciyuan.dimera.androidapp.R;
import com.ciyuan.dimera.androidapp.bean.CircleBean;
import com.ciyuan.dimera.androidapp.utils.UIUtils;

import java.util.List;

/**
 * ClassName : CircleAdapter
 * Author   : 史翔宇
 * Time     : 2015/12/15
 * Desc     :圈子的Adapter
 */
public class CircleAdapter extends BaseAdapter{

    List<CircleBean> mData;
//    public CircleAdapter(List<CircleBean> data){
//        mData=data;
//    }
    @Override
    public int getCount() {

       // return mData!=null? mData.size():0;
        return 8;
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
            convertView = View.inflate(UIUtils.getContext(),R.layout.item_circle,null);
            holder = new ViewHolder();
            holder.author_avatar= (ImageView) convertView.findViewById(R.id.iv_author_avatar);
            holder.desc= (TextView) convertView.findViewById(R.id.tv_desc);
            holder.img_content = (ImageView) convertView
                    .findViewById(R.id.iv_img_content);
            holder.author_name = (TextView) convertView.findViewById(R.id.tv_author_name);
            holder.pubtime = (TextView) convertView.findViewById(R.id.tv_publish_time);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //异步加载网络数据
//        ImageLoader.getInstance().displayImage(mData.get(position).getAuthor().getAvatar(), holder.author_avatar);
//        holder.author_name.setText(mData.get(position).getAuthor().getNickname());
//        holder.pubtime.setText(mData.get(position).getPubtime());
//        holder.desc.setText(mData.get(position).getDescription());
//        ImageLoader.getInstance().displayImage(mData.get(position).getPicurl(), holder.img_content);


          //模拟假数据
        holder.author_avatar.setImageResource(R.drawable.icon);
        holder.author_name.setText("Dimera");
        holder.pubtime.setText("发布于15分钟前");
        holder.desc.setText("哥就是帅哥就是帅哥就是帅哥就是帅");
        holder.img_content.setImageResource(R.drawable.home_banner);
        return convertView;
    }

    static class ViewHolder {

        ImageView author_avatar;
        TextView  author_name;
        TextView pubtime;
        TextView desc;
        ImageView img_content;



    }
}
