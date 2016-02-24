package com.ciyuan.dimera.androidapp.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ciyuan.dimera.androidapp.R;
import com.ciyuan.dimera.androidapp.utils.UIUtils;

/**
 * Created by quqing on 2016-01-19.
 */
public class MessageLikeAdapter extends BaseAdapter {
    public Activity mActivity;
    public MessageLikeAdapter(Activity activity){
        mActivity = activity;
    }
    class ViewHolder{
        ImageView img_zan_person;
        TextView message_like_name;
        ImageView img_like_time;
        ImageView img_publish_zan;
    }
    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            convertView = View.inflate(UIUtils.getContext(), R.layout.item_message_like,null);
            holder = new ViewHolder();
            holder.img_like_time = (ImageView) convertView.findViewById(R.id.img_like_time);
            holder.img_publish_zan = (ImageView) convertView.findViewById(R.id.img_publish);
            holder.img_zan_person = (ImageView) convertView.findViewById(R.id.img_zan_person);
            holder.message_like_name = (TextView) convertView.findViewById(R.id.message_like_name);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder)convertView.getTag();
        }
        holder.img_zan_person.setImageResource(R.drawable.icon);
        holder.message_like_name.setText("Bonni");
        holder.img_publish_zan.setImageResource(R.drawable.publish_normal);

        return convertView;
    }
}
