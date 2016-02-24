package com.ciyuan.dimera.androidapp.utils;

import android.app.Activity;
import android.content.Intent;

public class IntentUtils {

	/**
	 * 无延迟的开启新的Activity，无finish
	 * @param activity
	 * @param cls
	 */
	public static void startActivity(Activity activity, Class<?> cls) {
		Intent intent = new Intent(activity, cls);
		activity.startActivity(intent);
	}

	/**
	 * 无延迟的开启新的Activity，有finish
	 * @param activity
	 * @param cls
	 */
	public static void startActivityAndFinish(Activity activity, Class<?> cls) {
		Intent intent = new Intent(activity, cls);
		activity.startActivity(intent);
		activity.finish();
	}

	/**
	 * 有延迟的开启新的Activity，无finish
	 * @param activity
	 * @param cls
	 * @param delayTime 延迟时间
	 */
	public static void startActivityForDelay(final Activity activity,
											 final Class<?> cls, final long delayTime) {

		new Thread() {
			public void run() {
				try {
					Thread.sleep(delayTime);
					Intent intent = new Intent(activity, cls);
					activity.startActivity(intent);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}.start();

	}

	/**
	 * 有延迟的开启新的Activity，有finish
	 * @param activity
	 * @param cls
	 * @param delayTime
	 */
	public static void startActivityForDelayAndFinish(final Activity activity,
													  final Class<?> cls, final long delayTime) {

		new Thread() {
			public void run() {
				try {
					Thread.sleep(delayTime);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Intent intent = new Intent(activity, cls);
				activity.startActivity(intent);
				activity.finish();
			}
		}.start();

	}
}
