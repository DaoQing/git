package com.ciyuan.dimera.androidapp.utils;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

public class ToastUtils {


	/**
	 *
	 * @param context
	 * @param text
	 */

	public static void show(Context context,String text){
		Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
	}
	/**
	 * @param activity
	 * @param text
	 */
	public static void show(final Activity activity,final String text){
		if("main".equalsIgnoreCase(Thread.currentThread().getName())){
			Toast.makeText(activity, text, Toast.LENGTH_SHORT).show();
		}else{
			activity.runOnUiThread(new Runnable(){

				@Override
				public void run() {
					Toast.makeText(activity, text, Toast.LENGTH_SHORT).show();
				}
				
			});
		}
	}
	
	/**
	 * @param activity
	 * @param text
	 * @param duration
	 */
	public static void show(final Activity activity,final String text,final int duration){
		if("main".equalsIgnoreCase(Thread.currentThread().getName())){
			Toast.makeText(activity, text, duration).show();
		}else{
			activity.runOnUiThread(new Runnable(){

				@Override
				public void run() {
					Toast.makeText(activity, text, duration).show();
				}
				
			});
		}
	}
}
