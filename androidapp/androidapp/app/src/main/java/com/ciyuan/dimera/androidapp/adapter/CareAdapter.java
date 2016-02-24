package com.ciyuan.dimera.androidapp.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ciyuan.dimera.androidapp.R;
import com.ciyuan.dimera.androidapp.activity.TaAct;
import com.ciyuan.dimera.androidapp.utils.IntentUtils;
import com.ciyuan.dimera.androidapp.utils.ToastUtils;
import com.ciyuan.dimera.androidapp.utils.UIUtils;

/**
 * ClassName : PublishAdapter
 * Author   : 史翔宇
 * Time     : 2015/12/24
 * Desc     :
 */
public class CareAdapter extends BaseAdapter {

    Activity mActivity;

    public CareAdapter(Activity activity){
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
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(UIUtils.getContext(), R.layout.item_me_care, null);
            holder = new ViewHolder();
            holder.item_care= (LinearLayout) convertView.findViewById(R.id.item_care);
            holder.user_avatar = (ImageView) convertView.findViewById(R.id.user_avatar);
            holder.user_nickname = (TextView) convertView.findViewById(R.id.user_nickname);
            holder.user_sex = (ImageView) convertView.findViewById(R.id.user_sex);
            holder.user_autograph = (TextView) convertView.findViewById(R.id.user_autograph);
            holder.img_care = (ImageView) convertView.findViewById(R.id.img_care);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.item_care.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtils.startActivity(mActivity, TaAct.class);
            }
        });
        holder.user_avatar.setImageResource(R.drawable.icon);
        holder.user_nickname.setText("Sun");
        String desc = "美啦没啦美啦美啦没啦美啦美啦没啦美啦美啦没啦美啦美啦没啦美啦美啦没啦美啦美啦没啦" +
                "美啦美啦没啦美啦美啦没啦美啦美啦没啦美啦美啦没啦美啦美啦没啦美啦美啦" +
                "美啦没啦美啦美啦没啦美啦美啦没啦美啦没啦美啦美啦没啦美啦美啦没啦美啦美啦没啦美啦";
        holder.user_sex.setImageResource(R.drawable.sex_male);
        holder.user_autograph.setText(desc);
        holder.img_care.setImageResource(R.drawable.care_alone);
        holder.img_care.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.show(UIUtils.getContext().getApplicationContext(),"点击了关注图标额");
            }
        });
        return convertView;
    }

    class ViewHolder {
        LinearLayout item_care;
        ImageView user_avatar;
        TextView user_nickname;
        ImageView user_sex;
        TextView user_autograph;
        ImageView img_care;
    }
}

