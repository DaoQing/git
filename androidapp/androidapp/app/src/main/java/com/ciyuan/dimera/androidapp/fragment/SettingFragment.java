package com.ciyuan.dimera.androidapp.fragment;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.ciyuan.dimera.androidapp.R;
import com.ciyuan.dimera.androidapp.activity.MyInfoAct;
import com.ciyuan.dimera.androidapp.activity.SettingAct;
import com.ciyuan.dimera.androidapp.adapter.CareAdapter;
import com.ciyuan.dimera.androidapp.adapter.CollectAdapter;
import com.ciyuan.dimera.androidapp.adapter.FansAdapter;
import com.ciyuan.dimera.androidapp.adapter.PublishAdapter;
import com.ciyuan.dimera.androidapp.dialog.TakePhotosDialog;
import com.ciyuan.dimera.androidapp.utils.IntentUtils;
import com.ciyuan.dimera.androidapp.utils.PictureUtils;
import com.ciyuan.dimera.androidapp.utils.ShareUtils;
import com.ciyuan.dimera.androidapp.utils.UIUtils;

import java.io.File;

public class SettingFragment extends BaseFragment implements View.OnClickListener, AbsListView.OnScrollListener {


    public LinearLayout tabs;
    private ListView mLv;

    private LinearLayout ll_publish;
    private LinearLayout ll_collect;
    private LinearLayout ll_care;
    private LinearLayout ll_fans;

    private Button bt_pulish;
    private Button bt_collect;
    private Button bt_care;
    private Button bt_fans;

    private TextView tv_publish_count;
    private TextView tv_collect_count;
    private TextView tv_care_count;
    private TextView tv_fans_count;

    private BaseAdapter adapter;
    private PublishAdapter mPublishAdapter;
    private CollectAdapter mCollectAdapter;
    private CareAdapter mCareAdapter;
    private FansAdapter mFansAdapter;

    private ImageView setting;

    private View rootView;

    private View headerView;

    public ImageView showBg;

    private ImageView user_avatar;

    private int WHICH_CHANGE_BG;

    private ImageView edit_user_info_autograph;

    private int showType = 0;

    private int position1;
    private int position2;


    private Button toTopBtn;// 返回顶部的按钮
    private boolean scrollFlag = false;// 标记是否滑动
    private int lastVisibleItemPosition = 0;// 标记上次滑动位置

    public SettingFragment(Context context) {
        super(context);
    }


    @Override
    protected View initView() {
        rootView = View.inflate(getActivity(), R.layout.tap_me, null);
        mLv = (ListView) rootView.findViewById(R.id.lv_me);
        toTopBtn = (Button) rootView.findViewById(R.id.top_btn);
        headerView = View.inflate(getActivity(), R.layout.me_header, null);
        findViews(headerView);
        mLv.addHeaderView(headerView);
        mLv.setOnScrollListener(this);
        if (ShareUtils.getString(getActivity(), "show_bg_path") != null) {
            showBg.setImageURI(Uri.parse(ShareUtils.getString(getActivity(), "show_bg_path")));
        } else {
            showBg.setImageResource(R.drawable.me_top_bg);
        }
        setClick();
        setSelect(showType);
        return rootView;
    }

    private void findViews(View view) {
        ll_publish = (LinearLayout) view.findViewById(R.id.me_ll_publish);
        ll_collect = (LinearLayout) view.findViewById(R.id.me_ll_collect);
        ll_care = (LinearLayout) view.findViewById(R.id.me_ll_care);
        ll_fans = (LinearLayout) view.findViewById(R.id.me_ll_fans);

        bt_pulish = (Button) view.findViewById(R.id.me_bt_pulish);
        bt_collect = (Button) view.findViewById(R.id.me_bt_collect);
        bt_care = (Button) view.findViewById(R.id.me_bt_care);
        bt_fans = (Button) view.findViewById(R.id.me_bt_fans);

        tv_publish_count = (TextView) view.findViewById(R.id.tv_publish_count);
        tv_collect_count = (TextView) view.findViewById(R.id.tv_collect_count);
        tv_care_count = (TextView) view.findViewById(R.id.tv_care_count);
        tv_fans_count = (TextView) view.findViewById(R.id.tv_fans_count);

        tabs = (LinearLayout) view.findViewById(R.id.tabs);
        setting = (ImageView) view.findViewById(R.id.iv_setting);
        user_avatar = (ImageView) view.findViewById(R.id.user_avatar);
        showBg = (ImageView) view.findViewById(R.id.show_bg);
        edit_user_info_autograph = (ImageView) view.findViewById(R.id.edit_user_info_autograph);
    }


    private void setClick() {
        ll_publish.setOnClickListener(this);
        ll_collect.setOnClickListener(this);
        ll_care.setOnClickListener(this);
        ll_fans.setOnClickListener(this);
        edit_user_info_autograph.setOnClickListener(this);
        setting.setOnClickListener(this);
        toTopBtn.setOnClickListener(this);
        showBg.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.edit_user_info_autograph:
                IntentUtils.startActivity(getActivity(), MyInfoAct.class);
                break;
            case R.id.iv_setting:
                IntentUtils.startActivity(getActivity(), SettingAct.class);
                break;
            case R.id.me_ll_publish:
                setSelect(0);
                break;
            case R.id.me_ll_collect:

                setSelect(1);
                break;
            case R.id.me_ll_care:
                setSelect(2);
                break;
            case R.id.me_ll_fans:
                setSelect(3);
                break;
            case R.id.top_btn:// 点击按钮返回到ListView的第一项
                setListViewPos(0);
                break;
            case R.id.user_avatar://换头像
                WHICH_CHANGE_BG = 0;
                showChoosePicDialog();
                break;
            case R.id.show_bg://换顶部背景
                WHICH_CHANGE_BG = 1;
                showChoosePicDialog();
                break;
        }
    }

    /**
     * 滚动ListView到指定位置
     *
     * @param pos
     */
    private void setListViewPos(int pos) {
        if (android.os.Build.VERSION.SDK_INT >= 8) {
            mLv.smoothScrollToPosition(pos);
        } else {
            mLv.setSelection(pos);
        }
    }

    private void setSelect(int i) {
        //设置图片为亮色，切换内容区域
        showType = i;
        checkImgs(i);
        setTab(i);

    }

    private void checkImgs(int i) {
        resetImgs();

        switch (i) {
            case 0:
                bt_pulish.setTextColor(UIUtils.getColor(R.color.light_red));
                tv_publish_count.setTextColor(UIUtils.getColor(R.color.light_red));
                break;
            case 1:
                bt_collect.setTextColor(UIUtils.getColor(R.color.light_red));
                tv_collect_count.setTextColor(UIUtils.getColor(R.color.light_red));
                break;
            case 2:
                bt_care.setTextColor(UIUtils.getColor(R.color.light_red));
                tv_care_count.setTextColor(UIUtils.getColor(R.color.light_red));
                break;
            case 3:
                bt_fans.setTextColor(UIUtils.getColor(R.color.light_red));
                tv_fans_count.setTextColor(UIUtils.getColor(R.color.light_red));
                break;
        }
    }

    private void resetImgs() {
        //设置字体颜色为白色
        bt_pulish.setTextColor(UIUtils.getColor(R.color.white));
        bt_collect.setTextColor(UIUtils.getColor(R.color.white));
        bt_care.setTextColor(UIUtils.getColor(R.color.white));
        bt_fans.setTextColor(UIUtils.getColor(R.color.white));
        tv_publish_count.setTextColor(UIUtils.getColor(R.color.white));
        tv_collect_count.setTextColor(UIUtils.getColor(R.color.white));
        tv_care_count.setTextColor(UIUtils.getColor(R.color.white));
        tv_fans_count.setTextColor(UIUtils.getColor(R.color.white));

    }

    private void setTab(int i) {
        switch (i) {
            case 0:
//                adapter = new PublishAdapter();
//                mLv.setAdapter(adapter);
                //  切换到列表1时：

                if (mPublishAdapter == null) {
                    mPublishAdapter = new PublishAdapter(getActivity());
                    mLv.setAdapter(mPublishAdapter);
                } else {
// 刷新数据
                    //  publishAdapter.notifyInfo(list1);//此方法为adapter中写的方法

// 设置适配器
                    mLv.setAdapter(mPublishAdapter);

// 显示在之前显示的item上
                    mLv.setSelection(position1);
                }


                break;
            case 1:
//                adapter = new CollectAdapter();
//                mLv.setAdapter(adapter);

                if (mCollectAdapter == null) {
                    mCollectAdapter = new CollectAdapter(getActivity());
                    mLv.setAdapter(mCollectAdapter);
                } else {
// 刷新数据
                    //  publishAdapter.notifyInfo(list1);//此方法为adapter中写的方法

// 设置适配器
                    mLv.setAdapter(mCollectAdapter);

// 显示在之前显示的item上
                    mLv.setSelection(position2);
                }

                break;
            case 2:
                adapter = new CareAdapter(getActivity());
                mLv.setAdapter(adapter);
                break;
            case 3:
                adapter = new FansAdapter(getActivity());
                mLv.setAdapter(adapter);
                break;
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {


        switch (scrollState) {
            // 当不滚动时
            case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:// 是当屏幕停止滚动时
                scrollFlag = false;
                // 判断滚动到底部
                if (mLv.getLastVisiblePosition() == (mLv
                        .getCount() - 1)) {
                    toTopBtn.setVisibility(View.VISIBLE);
                }
                // 判断滚动到顶部
                if (mLv.getFirstVisiblePosition() == 0) {
                    toTopBtn.setVisibility(View.GONE);
                }

                break;
            case AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:// 滚动时
                scrollFlag = true;
                break;
            case AbsListView.OnScrollListener.SCROLL_STATE_FLING:// 是当用户由于之前划动屏幕并抬起手指，屏幕产生惯性滑动时
                scrollFlag = false;
                break;
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {


        if (firstVisibleItem > lastVisibleItemPosition) {// 上滑
            toTopBtn.setVisibility(View.GONE);
        } else if (firstVisibleItem < lastVisibleItemPosition) {// 下滑
            toTopBtn.setVisibility(View.VISIBLE);
        } else {
            return;
        }
        lastVisibleItemPosition = firstVisibleItem;
    }

    protected static final int CHOOSE_PICTURE = 0;
    protected static final int TAKE_PICTURE = 1;
    private static final int CROP_SMALL_PICTURE = 2;
    protected static Uri tempUri;
    private ImageView iv_personal_icon;

    /**
     * 选择相册或者拍照的dialog
     */
    public void showChoosePicDialog() {
        final TakePhotosDialog dialog = new TakePhotosDialog(getActivity(), R.style.dialog_setting);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.dialog_animation);
        dialog.show();
        WindowManager windowManager = getActivity().getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        params.width = (int) (display.getWidth());
        dialog.getWindow().setAttributes(params);
        Button choose_picture = (Button) dialog.findViewById(R.id.bt_choose_picture);
        Button take_picture = (Button) dialog.findViewById(R.id.bt_take_picture);
        Button cancel = (Button) dialog.findViewById(R.id.bt_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        //选择本地照片
        choose_picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openAlbumIntent = new Intent(
                        Intent.ACTION_GET_CONTENT);
                openAlbumIntent.setType("image/*");
                startActivityForResult(openAlbumIntent, CHOOSE_PICTURE);
                dialog.dismiss();
            }
        });
        // 拍照
        take_picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openCameraIntent = new Intent(
                        MediaStore.ACTION_IMAGE_CAPTURE);
                tempUri = Uri.fromFile(new File(Environment
                        .getExternalStorageDirectory(), "image.jpg"));
                // 指定照片保存路径（SD卡），image.jpg为一个临时文件，每次拍照后这个图片都会被替换
                openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, tempUri);
                startActivityForResult(openCameraIntent, TAKE_PICTURE);
                dialog.dismiss();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) { // 如果返回码是可以用的
            switch (requestCode) {
                case TAKE_PICTURE:
                    startPhotoZoom(tempUri); // 开始对图片进行裁剪处理
                    break;
                case CHOOSE_PICTURE:
                    startPhotoZoom(data.getData()); // 开始对图片进行裁剪处理
                    break;
                case CROP_SMALL_PICTURE:
                    if (data != null) {
                        setImageToView(data); // 让刚才选择裁剪得到的图片显示在界面上
                    }
                    break;
            }
        }
    }

    /**
     * 裁剪图片方法实现
     *
     * @param uri
     */
    protected void startPhotoZoom(Uri uri) {
        if (uri == null) {
            Log.i("tag", "The uri is not exist.");
        }
        tempUri = uri;
        Intent intent = new Intent("com.android.camera.action.CROP");

        // 设置裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 300);
        intent.putExtra("outputY", 300);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, CROP_SMALL_PICTURE);
    }

    protected void setImageToView(Intent data) {
        Bundle extras = data.getExtras();
        if (extras != null) {
            Bitmap photo = extras.getParcelable("data");
            //  photo = PictureUtils.toRoundBitmap(photo, tempUri); // 把图片处理成圆形的
            switch (WHICH_CHANGE_BG) {
                case 0:
                    user_avatar.setImageBitmap(photo);
                    uploadPic(photo);
                    break;
                case 1:
                    showBg.setImageBitmap(photo);
                    uploadPic(photo);
                    break;
            }

        }
    }

    private static String show_bg_path;

    private void uploadPic(Bitmap bitmap) {
        // 上传至服务器
        // ... 可以在这里把Bitmap转换成file，然后得到file的url，做文件上传操作
        // 注意这里得到的图片已经是圆形图片了
        // bitmap是没有做个圆形处理的，但已经被裁剪了

        show_bg_path = PictureUtils.savePhoto(bitmap, Environment
                .getExternalStorageDirectory().getAbsolutePath(), String
                .valueOf(System.currentTimeMillis()));
        ShareUtils.setString(getActivity(), "show_bg_path", show_bg_path);
        Log.e("imagePath", show_bg_path + "");
        if (show_bg_path != null) {
            // 拿着imagePath上传
        }
    }
}