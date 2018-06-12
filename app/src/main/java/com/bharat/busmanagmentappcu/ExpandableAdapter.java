package com.bharat.busmanagmentappcu;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

/**
 * Created by Bharat on 3/10/2018.
 */

public class ExpandableAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> parentList;
    private Map<String,List<String>> childeList;

    public ExpandableAdapter(Context context,List<String> parentList, Map<String, List<String>> childeList) {
        this.context = context;
        this.parentList = parentList;
        this.childeList = childeList;
    }

    @Override
    public int getGroupCount() {
        return parentList.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return childeList.get(parentList.get(i)).size();
    }

    @Override
    public Object getGroup(int i) {
        return parentList.get(i);
    }
    @Override
    public Object getChild(int i, int i1) {
        return childeList.get(parentList.get(i)).get(i1);
    }

        @Override
        public long getGroupId(int i) {        return i;    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_parent,null);
        }

        TextView parentText = view.findViewById(R.id.parent_txt);
        parentText.setText(getGroup(i).toString());
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_child,null);
        }

        TextView parentText = view.findViewById(R.id.child_txt);
        parentText.setText(getChild(i,i1).toString());
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
