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
 * ClassName : PublishAdapter
 * Author   : 史翔宇
 * Time     : 2015/12/24
 * Desc     :
 */
public class PublishAdapter extends BaseAdapter {

    public Activity mActivity;
    public PublishAdapter(Activity activity){
        mActivity=activity;
    }
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
        if(convertView==null){
            convertView = View.inflate(UIUtils.getContext(), R.layout.item_me_publish, null);
            holder=new ViewHolder();
            holder.time= (TextView) convertView.findViewById(R.id.time_publish);
            holder.desc= (TextView) convertView.findViewById(R.id.desc_publish);
            holder.img= (ImageView) convertView.findViewById(R.id.img_publish);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        View dividerLine=convertView.findViewById(R.id.me_publish_divider);
        if(position==0){
            dividerLine.setVisibility(View.INVISIBLE);
        }else {
            dividerLine.setVisibility(View.VISIBLE);
        }

        holder.time.setText("11月21日");
        String desc="美啦没啦美啦美啦没啦美啦美啦没啦美啦美啦没啦美啦美啦没啦美啦美啦没啦美啦美啦没啦" +
                "美啦美啦没啦美啦美啦没啦美啦美啦没啦美啦美啦没啦美啦美啦没啦美啦美啦" +
                "美啦没啦美啦美啦没啦美啦美啦没啦美啦没啦美啦美啦没啦美啦美啦没啦美啦美啦没啦美啦";
        holder.desc.setText(desc);
        holder.img.setImageResource(R.drawable.me_publish_item_img);
        return convertView;
    }

    class ViewHolder{
        TextView time;
        TextView desc;
        ImageView img;
    }
}

