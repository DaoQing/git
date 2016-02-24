package com.ciyuan.dimera.androidapp.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

/**
 * 页面缓存工具类
 * 
 * @author Mill
 * @version 2014-7-21 上午9:59:46
 * @ModifiedBy
 */
public class CacheUtils {

	/**
	 * 得到基本目录
	 * 
	 * @author Mill
	 * @return
	 */
	private static String getBaseDir(Context context) {
		StringBuffer dirPath = new StringBuffer();
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			dirPath.append(Environment.getExternalStorageDirectory().getPath());
			dirPath.append(File.separator);
			dirPath.append(context.getPackageName());
			dirPath.append(File.separator);
		} else {
			dirPath.append(context.getCacheDir().getAbsolutePath());
			dirPath.append(File.separator);
		}
		dirPath.append("pageCache");
		dirPath.append(File.separator);

		String path = dirPath.toString();

		Log.i("LL", path);
		new File(path).mkdirs();
		return path;
	}

	/**
	 * 缓存到本地文件
	 * 
	 * @author Mill
	 * @param context
	 * @param obj
	 */
	public static void cacheToLocal(Context context, String fileName, Object obj) {
		String baseDir = getBaseDir(context);
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(baseDir + fileName);
			out.write(obj.toString().getBytes());
			out.flush();
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
				}
			}
		}
	}

	/**
	 * 缓存到本地文件
	 * 
	 * @author Mill
	 * @param context
	 * @param dir
	 * @param fileName
	 * @param obj
	 */
	public static void cacheToLocal(Context context, String dir,
			String fileName, Object obj) {

	}

	/**
	 * 从本地 获取bean
	 * 
	 * @author Mill
	 * @param context
	 * @param fileName
	 * @return
	 */
	public static Object readObjFromLocal(Context context, String fileName) {
		String baseDir = getBaseDir(context);
		FileInputStream inStream = null;
		try {
			System.out.println("-----从本地获取文件-----basedir+filename:"
					+ (baseDir + fileName));
			inStream = new FileInputStream(baseDir + fileName);
			System.out
					.println("baseDir+fileName:" + (baseDir + "," + fileName));
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			byte[] buffer = new byte[512];
			int length = -1;
			while ((length = inStream.read(buffer)) != -1) {
				outStream.write(buffer, 0, length);
			}
			outStream.close();
			inStream.close();

			// JSONObject json=new JSONObject();
			return outStream.toString();
		} catch (IOException e) {
		}
		return null;
	}

	/**
	 * 从本地 获取bean
	 * 
	 * @author Mill
	 * @param context
	 * @param fileName
	 * @return
	 */
	public static Object readObjFromLocal(Context context, String dir,
			String fileName) {
		return fileName;
	}
}
