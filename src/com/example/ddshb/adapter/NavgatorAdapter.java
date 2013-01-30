package com.example.ddshb.adapter;

import java.util.List;

import com.example.ddshb.html.Navigator;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class NavgatorAdapter extends BaseAdapter {

	private List<Navigator> list;
	private Context mContext;
	private Handler loadLandler;

	public NavgatorAdapter(Context mContext, List<Navigator> list, Handler loadLandler) {
		super();
		this.list = list;
		this.mContext = mContext;
		this.loadLandler = loadLandler;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		TextView tvNav;
		if (convertView == null) {
			convertView = new TextView(mContext);
		}
		tvNav = (TextView) convertView;
		tvNav.setPadding(20, 10, 20, 10);
		tvNav.setText(list.get(position).getTitleContent());
		tvNav.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				loadLandler.sendEmptyMessage(position);
			}
		});
		return tvNav;
	}

}
