package com.ciyuan.dimera.androidapp.factory;


import android.support.v4.util.SparseArrayCompat;

import com.ciyuan.dimera.androidapp.fragment.BaseFragment;
import com.ciyuan.dimera.androidapp.fragment.CircleFragment;
import com.ciyuan.dimera.androidapp.fragment.HotPicFragment;
import com.ciyuan.dimera.androidapp.fragment.MessageFragment;
import com.ciyuan.dimera.androidapp.fragment.SettingFragment;
import com.ciyuan.dimera.androidapp.utils.UIUtils;


public class FragmentFactory
{
	// private static Map<Integer, Fragment> mCaches = new HashMap<Integer,
	// Fragment>();
	private static SparseArrayCompat<BaseFragment>	mCaches	= new SparseArrayCompat<BaseFragment>();

	public static BaseFragment getFragment(int position)
	{

		// <item>热门图片</item>
		// <item>最新模型</item>


		// 去缓存中取
		BaseFragment fragment = mCaches.get(position);
		if (fragment != null)
		{
			System.out.println("使用" + position + "的缓存");
			// 缓存中有
			return fragment;
		}

		switch (position)
		{
			case 0:
				fragment=new HotPicFragment(UIUtils.getContext());
				break;
			case 1:
				fragment=new CircleFragment(UIUtils.getContext());
				break;
			case 2:
				fragment=new MessageFragment(UIUtils.getContext());
				break;
			case 3:
				fragment=new SettingFragment(UIUtils.getContext());
				break;


		}

		// 缓存起来
		System.out.println("为" + position + "缓存");
		mCaches.put(position, fragment);

		return fragment;
	}
}
