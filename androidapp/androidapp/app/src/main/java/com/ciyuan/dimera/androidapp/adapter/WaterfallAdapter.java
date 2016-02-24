package com.ciyuan.dimera.androidapp.adapter;

import android.content.Context;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ciyuan.dimera.androidapp.R;
import com.ciyuan.dimera.androidapp.bean.HotPictureBean;
import com.etsy.android.grid.util.DynamicHeightImageView;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;
import java.util.Random;

public class WaterfallAdapter extends BaseAdapter {
	private static final String TAG = "MyPhotoAdapter";

	private final LayoutInflater mLayoutInflater;
	protected ImageLoader imageLoader = ImageLoader.getInstance();
	private List<HotPictureBean> data;
	private final Random mRandom;
	Context context;

	private static final SparseArray<Double> sPositionHeightRatios = new SparseArray<Double>();

	public WaterfallAdapter(Context context, List<HotPictureBean> data) {
		mLayoutInflater = LayoutInflater.from(context);
		mRandom = new Random();
		this.context = context;
		this.data = data;
	}

	@Override
	public View getView(final int position, View convertView,
			final ViewGroup parent) {

		ViewHolder vh;
		if (convertView == null) {
			convertView = mLayoutInflater.inflate(R.layout.item_waterfall,
					parent, false);
			vh = new ViewHolder();
			vh.imgContent = (DynamicHeightImageView) convertView
					.findViewById(R.id.iv_content_img);
			vh.tv_name = (TextView) convertView.findViewById(R.id.tv_user_name);
			vh.ll_user = (LinearLayout) convertView.findViewById(R.id.ll_user);
			convertView.setTag(vh);
		} else {
			vh = (ViewHolder) convertView.getTag();
		}

		double positionHeight = getPositionRatio(position);
		Log.d(TAG, "getView position:" + position + " h:" + positionHeight);
		if (position % 2 == 0) {
			vh.imgContent.setHeightRatio(1);
		} else {
			vh.imgContent.setHeightRatio(1.5);
		}


		vh.tv_name.setText(data.get(position).getPublish_user());
		// 异步加载图片
		ImageLoader.getInstance().displayImage(data.get(position).getDynamic_picurl(), vh.imgContent);

		return convertView;
	}

	static class ViewHolder {
		DynamicHeightImageView imgContent;
		TextView tv_name;
		LinearLayout ll_user;
	}

	private double getPositionRatio(final int position) {
		double ratio = sPositionHeightRatios.get(position, 0.0);
		if (ratio == 0) {
			ratio = getRandomHeightRatio();
			sPositionHeightRatios.append(position, ratio);
			Log.d(TAG, "getPositionRatio:" + position + " ratio:" + ratio);
		}
		return ratio;
	}

	private double getRandomHeightRatio() {
		return (mRandom.nextDouble() / 2.0) + 1.0; // height will be 1.0 - 1.5
													// the width
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
}
