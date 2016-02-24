package com.ciyuan.dimera.androidapp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ciyuan.dimera.androidapp.R;
import com.ciyuan.dimera.androidapp.utils.UIUtils;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * ClassName : ModelMoreAct
 * Author   : 史翔宇
 * Time     : 2015/12/1
 * Desc     :
 */
public class ModelMoreAct extends Activity implements View.OnClickListener, PullToRefreshBase.OnRefreshListener2<ListView> {

    @Bind(R.id.iv_goback)
    ImageView mGobackArrow;
    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.modelMore_lv)
    PullToRefreshListView mListView;

    private ModelMoreAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_model_more);
        ButterKnife.bind(this);
        adapter = new ModelMoreAdapter();
        mListView.setMode(PullToRefreshBase.Mode.BOTH);
        mListView.setAdapter(adapter);
        mListView.setOnRefreshListener(this);
        mTvTitle.setText("最新模型");
        mGobackArrow.setOnClickListener(this);
        init();
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {

    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

    }

    private void init() {
        ILoadingLayout startLabels = mListView
                .getLoadingLayoutProxy(true, false);
        startLabels.setPullLabel("下拉刷新...");// 刚下拉时，显示的提示
        startLabels.setRefreshingLabel("正在载入...");// 刷新时
        startLabels.setReleaseLabel("放开刷新...");// 下来达到一定距离时，显示的提示

        ILoadingLayout endLabels = mListView.getLoadingLayoutProxy(
                false, true);
        endLabels.setPullLabel("上拉刷新...");// 刚下拉时，显示的提示
        endLabels.setRefreshingLabel("正在载入...");// 刷新时
        endLabels.setReleaseLabel("放开刷新...");// 下来达到一定距离时，显示的提示
    }

    class ModelMoreAdapter extends BaseAdapter implements View.OnClickListener {

        @Override
        public int getCount() {
            return 5;
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

            if (convertView != null) {
                holder = (ViewHolder) convertView.getTag();
            } else {
                convertView = View.inflate(UIUtils.getContext(), R.layout.item_new_model, null);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            }

            holder.user_head.setImageResource(R.drawable.daren_default);
            holder.user_name.setText("Sun");
            holder.publish_time.setText("12月31日");
            holder.add_care.setOnClickListener(this);
            holder.model_desc.setText("次元时代次元时代次元时代次元时代lalalalala");
            holder.model_pic.setImageResource(R.drawable.home_banner);
            return convertView;
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.pic:
                    Toast.makeText(UIUtils.getContext(), "图片放大", Toast.LENGTH_SHORT).show();

                case R.id.add_care:
                    Toast.makeText(UIUtils.getContext(), "++++++", Toast.LENGTH_SHORT).show();
                    break;
            }

        }
    }

    static class ViewHolder {
        @Bind(R.id.user_head)
        ImageView user_head;
        @Bind(R.id.user_name)
        TextView user_name;
        @Bind(R.id.add_care)
        TextView add_care;
        @Bind(R.id.publish_time)
        TextView publish_time;
        @Bind(R.id.desc)
        TextView model_desc;
        @Bind(R.id.pic)
        ImageView model_pic;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_goback:
                finish();
                break;
        }
    }
}
