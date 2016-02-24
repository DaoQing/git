package com.ciyuan.dimera.androidapp.dialog;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.ciyuan.dimera.androidapp.R;

public class SharePopuWindow extends PopupWindow {

	private View mainView;
	private LinearLayout layoutCollect, layoutShare;

	public SharePopuWindow(Activity paramActivity,
						   View.OnClickListener paramOnClickListener, int paramInt1,
						   int paramInt2) {
		super(paramActivity);
		//窗口布局  
		mainView = LayoutInflater.from(paramActivity).inflate(
				R.layout.popwin_layout, null);
		//收藏布局  
		layoutCollect = ((LinearLayout) mainView.findViewById(R.id.ll_collect));
		//分享布局
		layoutShare = (LinearLayout) mainView.findViewById(R.id.ll_share);
		 //设置每个子布局的事件监听器  
		if (paramOnClickListener != null) {
			layoutCollect.setOnClickListener(paramOnClickListener);
			layoutShare.setOnClickListener(paramOnClickListener);
		}
		setContentView(mainView);
		//设置宽度  
		setWidth(paramInt1);
		//设置高度  
		setHeight(paramInt2);
		 //设置显示隐藏动画  
		setAnimationStyle(R.style.PopuWindowAnimTools);
		//设置背景透明  
		setBackgroundDrawable(new ColorDrawable(0));
	}
}
