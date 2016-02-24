package com.ciyuan.dimera.androidapp.factory;

import android.graphics.Color;
import android.widget.ListView;

import com.ciyuan.dimera.androidapp.R;
import com.ciyuan.dimera.androidapp.utils.UIUtils;

/**
 * ClassName : ListViewFactory
 * Author   : 史翔宇
 * Time     : 2015/12/10
 * Desc     :
 */
public class ListViewFactory
{

	public static ListView getListView()
	{
		ListView listView = new ListView(UIUtils.getContext());

		// 属性设置
		listView.setCacheColorHint(Color.TRANSPARENT);
		listView.setSelector(android.R.color.transparent);
		listView.setDividerHeight(0);
		listView.setScrollingCacheEnabled(false);
		listView.setBackgroundColor(UIUtils.getColor(R.color.bg));

		return listView;
	}
}
