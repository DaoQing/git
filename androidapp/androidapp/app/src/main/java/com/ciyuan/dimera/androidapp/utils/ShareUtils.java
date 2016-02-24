package com.ciyuan.dimera.androidapp.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * @类名： CacheUtils
 * @创建者： 史翔宇
 * @创建时间：2015年12月17日
 *
 * @描述： SharedPreferences工具类
 */
public class ShareUtils
{
	private static SharedPreferences	mPreferences;
	private final static String			SP_NAME	= "dimera_config";

	private static SharedPreferences getSp(Context context)
	{
		if (mPreferences == null)
		{
			mPreferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
		}
		return mPreferences;
	}

	/**
	 * 通过SharedPreferences存储boolean类型的值
	 * 
	 * @param context
	 * @param key
	 * @param value
	 */

	public static void setBoolean(Context context, String key, boolean value)
	{
		SharedPreferences sp = getSp(context);
		Editor editor = sp.edit();
		editor.putBoolean(key, value);
		editor.commit();
	}

	/**
	 * 通过SharedPreferences获得boolean类型的值
	 * 
	 * @param context
	 *            上下文
	 * @param key
	 *            存储的key
	 * @return
	 */
	public static boolean getBoolean(Context context, String key)
	{
		SharedPreferences sp = getSp(context);
		return sp.getBoolean(key, false);
	}

	/**
	 * 通过SharedPreferences获得boolean类型的值
	 * 
	 * @param context
	 *            上下文
	 * @param key
	 *            存储的key
	 * @param defValue
	 *            指定默认返回的boolean类型的值
	 * @return
	 */
	public static boolean getBoolean(Context context, String key, boolean defValue)
	{
		SharedPreferences sp = getSp(context);
		return sp.getBoolean(key, defValue);

	}

	/**
	 * 通过SharedPreferences存储String类型的值
	 * 
	 * @param context  上下文
	 * @param key     存储的key
	 * @param value    存的值
	 */
	public static void setString(Context context, String key, String value)
	{
		SharedPreferences sp = getSp(context);
		Editor editor = sp.edit();
		editor.putString(key, value);
		editor.commit();
	}

	/**
	 * 通过SharedPreferences获得String类型的值，有默认返回值
	 * @param context    上下文
	 * @param key      存储的key
	 * @param defValue    获得为空时默认返回的值
	 * @return
	 */
	public static String getString(Context context, String key, String defValue)
	{
		SharedPreferences sp = getSp(context);
		return sp.getString(key, defValue);

	}

	/**
	 * 通过SharedPreferences获得String类型的值
	 * @param context    上下文
	 * @param key      存储的key
	 * @return
	 */
	public static String getString(Context context, String key)
	{
		SharedPreferences sp = getSp(context);
		return sp.getString(key, null);
	}

	/**
	 * 通过SharedPreferences存储long类型的值
	 * @param context    上下文
	 * @param key      存储的key
	 * @param value    存的值
	 * @return
	 */
	public static void setLong(Context context, String key, Long value)
	{
		SharedPreferences sp = getSp(context);
		Editor editor = sp.edit();
		editor.putLong(key, value);
		editor.commit();
	}

	/**
	 * 通过SharedPreferences获得long类型的值，有默认返回值
	 * @param context    上下文
	 * @param key      存储的key
	 * @param defValue    取值为空时默认返回的值
	 * @return
	 */
	public static Long getLong(Context context, String key, Long defValue)
	{
		SharedPreferences sp = getSp(context);
		return sp.getLong(key, defValue);

	}

	/**
	 * 通过SharedPreferences获得long类型的值，无默认返回值
	 * @param context    上下文
	 * @param key      存储的key
	 * @return
	 */
	public static Long getLong(Context context, String key)
	{
		SharedPreferences sp = getSp(context);
		return sp.getLong(key, -1);
	}
	
	public static void setInt(Context context, String key, int value)
	{
		SharedPreferences sp = getSp(context);
		Editor editor = sp.edit();
		editor.putInt(key, value);
		editor.commit();
	}

	public static int getInt(Context context, String key, int defValue)
	{
		SharedPreferences sp = getSp(context);
		return sp.getInt(key, defValue);

	}

	public static int getInt(Context context, String key)
	{
		SharedPreferences sp = getSp(context);
		return sp.getInt(key, -1);
	}
}
