package com.aiyouwai.aphotoalbum.base;

import android.content.Context;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

public abstract class AywBaseAdapter<T> extends BaseAdapter {

	protected Context context;
	
	protected List<T> data;
	
	public AywBaseAdapter(Context context) {
		super();
		this.context = context;
	}

	public void setData(List<T> data) {
		this.data = data;
	}
	
	public void addData(List<T> data) {
		if (null == this.data) {
			this.data = new ArrayList<T>();
		}
		if (null != data) {
			this.data.addAll(data);
		}
	}

	public List<T> getData() {
		if (null == this.data) {
			this.data = new ArrayList<T>();
		}
		return this.data;
	}
	
	@Override
	public int getCount() {
		return data == null ? 0 : data.size();
	}

	@Override
	public T getItem(int position) {
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
}
