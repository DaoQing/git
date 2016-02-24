package com.ciyuan.dimera.androidapp.utils;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * 首选项菜单读取类，方便获取统一的内容
 * @author Administrator
 *
 */
public class AppUserInfo {
	//private Context context;
	private String uid=null;
	private String userName = null;
	private String ukey = null;
	
	public AppUserInfo(Context context) {
		// 通过首选菜单获得登陆判断
		SharedPreferences share = context.getSharedPreferences("Dimera",
				Application.MODE_PRIVATE);
		// 获得uid
		uid = share.getString("uid", null);
		// 获得username
		userName = share.getString("username", null);
		ukey = share.getString("key", null);
	}
	/**获取用户id
	 * @return String
	 */
	public String getUID() {
		return uid;
	}

	/**获取用户名
	 * @return String
	 */
	public String getUserName() {
		return userName;
	}

	/**获取用户key
	 * @return String
	 */
	public String getUkey() {
		return ukey;
	}

	

}
