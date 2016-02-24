package com.ciyuan.dimera.androidapp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ciyuan.dimera.androidapp.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * ClassName : ModelDetailAct
 * Author   : 史翔宇
 * Time     : 2015/12/2
 * Desc     :
 */
public class ModelDetailAct extends Activity implements View.OnClickListener {
    @Bind(R.id.iv_goback)
    ImageView mIvGoback;           //返回按钮
    @Bind(R.id.tv_title)
    TextView mTvTitle;            //标题
    @Bind(R.id.iv_window)
    ImageView mIvPopup;             //右侧弹出框
    @Bind(R.id.user_head)
    ImageView mUserHead;             //发布人头像
    @Bind(R.id.user_name)
    TextView mUserName;                //发布人
    @Bind(R.id.publish_time)
    TextView mPublishTime;          //发布时间
    @Bind(R.id.add_care)
    TextView mAddCare;            //加关注
    @Bind(R.id.desc)
    TextView mDesc;          //描述
    @Bind(R.id.pic)
    ImageView mPic;          //展示的图片
    @Bind(R.id.iv_admire)
    ImageView mIvPraise;       //赞
    @Bind(R.id.iv_comment)
    ImageView mIvComment;      //评论
    @Bind(R.id.iv_send)
    ImageView mIvDownload;     //下载

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_model_detail);
        ButterKnife.bind(this);
        onload();

    }

    private void onload() {
        mIvGoback.setOnClickListener(this);
        mTvTitle.setText("3D模型");
        mIvComment.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_goback:
                finish();
        }
    }
}
