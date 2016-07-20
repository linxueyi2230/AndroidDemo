package com.example.lxy.androiddemo.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

/**
 * 
 * @author lxy
 *
 * @param <G> Group
 * @param <C> child
 */
public abstract class AbsExpandableAdapter<G,C> extends BaseExpandableListAdapter {
	
	private Context mContext;
	private List<G> mGroups;
	private List<List<C>> mChilds;
	
	public AbsExpandableAdapter(Context context,List<G> groups,List<List<C>> childs) {
		this.mContext = context;
		this.mGroups = groups;
		this.mChilds = childs;
	}
	
	protected abstract int getGroupLayout();
	protected abstract int getChildLayout();
	
	protected abstract void onBindGroupData(View convertView, int groupPosition, G group);
	protected abstract void onBindChildData(View convertView, int groupPosition, int childPosition, C child);

	@Override
	public int getGroupCount() {
		if (mGroups == null) {
			return 0;
		}
		return mGroups.size();
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		if (mChilds == null) {
			return 0;
		}
		return mChilds.size();
	}

	@Override
	public G getGroup(int groupPosition) {
		return mGroups.get(groupPosition);
	}

	@Override
	public C getChild(int groupPosition, int childPosition) {
		return mChilds.get(groupPosition).get(childPosition);
	}

	@Override
	public long getGroupId(int groupPosition) {
		return 0;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return 0;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(getGroupLayout(), null);
		}
		
		G group = mGroups.get(groupPosition);
		onBindGroupData(convertView,groupPosition, group);
		
		return convertView;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(getChildLayout(), null);
		}
		
		C child = mChilds.get(groupPosition).get(childPosition);
		onBindChildData(convertView, groupPosition, childPosition, child);
		
		return convertView;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return false;
	}
	
	protected <V extends View> V getViewById(View convertView,int id){
		return ViewHolder.findView(convertView, id);
	}

}
