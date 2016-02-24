package com.ciyuan.dimera.androidapp.activity;

import android.app.Activity;
import android.app.Service;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.ciyuan.dimera.androidapp.R;
import com.ciyuan.dimera.androidapp.dialog.SharePopuWindow;
import com.ciyuan.dimera.androidapp.dialog.ShareDialog;
import com.ciyuan.dimera.androidapp.utils.IntentUtils;
import com.ciyuan.dimera.androidapp.utils.ToastUtils;
import com.ciyuan.dimera.androidapp.utils.UIUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * ClassName : HotPicDetailAct
 * Author   : 史翔宇
 * Time     : 2015/12/3
 * Desc     :
 */
public class HotPicDetailAct extends Activity implements View.OnClickListener {


    @Bind(R.id.iv_goback)
    ImageView mIvGoback;
    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.iv_window)
    ImageView mIvPopup;
    @Bind(R.id.lv_comment)
    ListView mLvComment;


    private LinearLayout ll_add_care;

    private ImageView iv_comment;
    private ImageView iv_admire;
    private ImageView iv_send;
    private HotPicAdapter mHotPicAdapter;
    private SharePopuWindow popWinShare;
    private Button btn_submit;//；评论提交按钮
    private PopupWindow popupWindow;//自定义的评论输入布局




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_hotpic_detail);
        ButterKnife.bind(this);
        initView();
        senOnListener();
    }

    private void initView() {

        iv_admire= (ImageView) findViewById(R.id.iv_admire);
        iv_comment= (ImageView) findViewById(R.id.iv_comment);
        iv_send= (ImageView) findViewById(R.id.iv_send);

        mHotPicAdapter = new HotPicAdapter();
        View headerView = View.inflate(UIUtils.getContext(), R.layout.item_detail_header, null);
        initViewByheader(headerView);
        mLvComment.addHeaderView(headerView);
        mLvComment.setAdapter(mHotPicAdapter);
        mTvTitle.setText("二次元");
    }

    public void initViewByheader(View view){
        ll_add_care= (LinearLayout) view.findViewById(R.id.ll_add_care);

    }

    private void senOnListener(){
        mIvGoback.setOnClickListener(this);
        mIvPopup.setOnClickListener(this);
        ll_add_care.setOnClickListener(this);

        iv_admire.setOnClickListener(this);
        iv_comment.setOnClickListener(this);
        iv_send.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_goback:
                finish();
                break;
            case R.id.iv_window:
                showShareAndCollectPopuWindow();
                break;
            case R.id.ll_add_care:
                ToastUtils.show(this, "关注成功");
                break;
            case R.id.iv_admire:
                ToastUtils.show(this,"多多赞");
                break;
            case R.id.iv_comment:
                showPopupCommnet(1, 1);
                break;
            case R.id.iv_send:
                ToastUtils.show(this,"多多转");
                break;
        }
    }

    private void showShareAndCollectPopuWindow(){
        if (popWinShare == null) {
            // 自定义的单击事件
            OnClickLintener paramOnClickListener = new OnClickLintener();
            popWinShare = new SharePopuWindow(this, paramOnClickListener, UIUtils.dip2px(140), UIUtils.dip2px(140));
            // 监听窗口的焦点事件，点击窗口外面则取消显示

            popWinShare.getContentView().setOnFocusChangeListener(
                    new View.OnFocusChangeListener() {

                        @Override
                        public void onFocusChange(View v, boolean hasFocus) {
                            if (!hasFocus) {
                                popWinShare.dismiss();
                            }
                        }
                    });
        }
        //设置默认获取焦点
        popWinShare.setFocusable(true);
        //以某个控件的x和y的偏移量位置开始显示窗口
        popWinShare.showAsDropDown(mIvPopup, 0, 24);
        //如果窗口存在，则更新
        popWinShare.update();
    }


    /**
     * show soft input
     */
    private void popupInputMethodWindow() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                InputMethodManager imm = (InputMethodManager) getSystemService(Service.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);

            }
        }.start();
        //
    }
    /**
     * show comment popupwindow（弹出评论的popupWindow）
     */
    private void showPopupCommnet(final int pid, final int type) {// pe表示是评论还是举报1.代表评论。2.代表举报
        View view = LayoutInflater.from(HotPicDetailAct.this).inflate(
                R.layout.comment_input_popupwindow, null);
        final EditText inputComment = (EditText) view
                .findViewById(R.id.comment);

        btn_submit = (Button) view.findViewById(R.id.submit_comment);
        if (type == 1) {
            btn_submit.setText("评论");
            inputComment.setHint("请输入评论");
        }
        popupWindow = new PopupWindow(view, LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT, true);

//        inputComment.setOnPopuWindowListener(new OnPopuWindowListener() {
//
//            @Override
//            public void closePopuWindow() {
//                if (popupWindow != null)
//                    popupWindow.dismiss();;
//            }
//        });
        popupWindow.setTouchable(true);
        popupWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
        popupWindow.setFocusable(true);
        // 设置点击窗口外边窗口消失
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(getResources().getDrawable(
                R.drawable.bt_square_yellow_bg));

        // 设置弹出窗体需要软键盘
        popupWindow.setSoftInputMode(PopupWindow.INPUT_METHOD_NEEDED);
        // 再设置模式，和Activity的一样，覆盖，调整大小。
        popupWindow
                .setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
        ColorDrawable cd = new ColorDrawable(0x000000);
        popupWindow.setBackgroundDrawable(cd);
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.alpha = 0.8f;
        getWindow().setAttributes(params);
        // 设置popWindow的显示和消失动画
        popupWindow.setAnimationStyle(R.style.input_popwindow_anim_style);
        popupWindow.update();
        popupInputMethodWindow();
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {

            // 在dismiss中恢复透明度
            public void onDismiss() {
                WindowManager.LayoutParams params = getWindow().getAttributes();
                params.alpha = 1f;
                getWindow().setAttributes(params);
            }
        });
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // btn_submit.setClickable(false);
                String comment1 = inputComment.getText().toString().trim();
                if (comment1.length() <= 0) {
                    if (type == 1) {
                        Toast.makeText(HotPicDetailAct.this, "评论内容不能为空",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(HotPicDetailAct.this, "有非法内容",
                                Toast.LENGTH_SHORT).show();
                    }
                    return;
                }
                String comment2 = null;
                try {
                    comment2 = URLEncoder.encode(comment1, "utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                final String finalComment = comment2;
                popupWindow.dismiss();

                Toast.makeText(HotPicDetailAct.this, finalComment,
                        Toast.LENGTH_SHORT).show();
                // 提交评论
                // submitComment(finalComment, pid);
            }
        });
    }



    class OnClickLintener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.ll_collect:
                    ToastUtils.show(HotPicDetailAct.this,"恭喜，收藏成功！");
                    break;
                case R.id.ll_share:
                    showShareDialog();
                    break;

                default:
                    break;
            }
            if (popWinShare!=null){
                popWinShare.dismiss();
            }

        }

    }


    /**
     * 分享的dialog
     */
    public void showShareDialog() {
        final ShareDialog dialog = new ShareDialog(this, R.style.dialog_setting);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.dialog_animation);
        dialog.show();
        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        params.width = (int) (display.getWidth());
        dialog.getWindow().setAttributes(params);
        Button choose_picture = (Button) dialog.findViewById(R.id.bt_choose_picture);
        ImageButton wechat_share = (ImageButton) dialog.findViewById(R.id.ibn_wechat_share);
        ImageButton friends_circle_share= (ImageButton) dialog.findViewById(R.id.ibn_friends_circle_share);
        Button cancel = (Button) dialog.findViewById(R.id.bt_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        //微信分享
        wechat_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
                IntentUtils.startActivity(HotPicDetailAct.this,SendToCircleAct.class);
                dialog.dismiss();
            }
        });
        // 朋友圈分享
        friends_circle_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
                dialog.dismiss();
            }
        });
    }



    class HotPicAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 20;
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
            if(convertView!=null){
               holder= (ViewHolder) convertView.getTag();
            }else {
                convertView=View.inflate(UIUtils.getContext(),R.layout.item_list_comment,null);
                holder=new ViewHolder();
                holder.comment_user_avatar= (ImageView) convertView.findViewById(R.id.iv_comment_user_avatar);
                holder.comment_user_nickname= (TextView) convertView.findViewById(R.id.tv_comment_user_nickname);
                holder.commemt_time= (TextView) convertView.findViewById(R.id.tv_comment_time);
                holder.comment_content= (TextView) convertView.findViewById(R.id.tv_comment_content);
                convertView.setTag(holder);
            }
            holder.comment_user_avatar.setImageResource(R.drawable.home_hot_comment_avatar);
            holder.comment_user_nickname.setText("Sun");
            holder.commemt_time.setText("12月31日");
            holder.comment_content.setText("次元时代次元时代次元时代次元时代lalalalala");
            return convertView;
        }
    }

  static class ViewHolder{
        ImageView comment_user_avatar;
        TextView comment_user_nickname;
        TextView commemt_time;
        TextView comment_content;
    }
}
