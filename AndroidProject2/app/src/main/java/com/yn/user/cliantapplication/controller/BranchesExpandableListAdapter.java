package com.yn.user.cliantapplication.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import com.yn.user.cliantapplication.R;
import com.yn.user.cliantapplication.model.backend.DBManagerFactory;
import com.yn.user.cliantapplication.model.entities.Branch;
import com.yn.user.cliantapplication.model.entities.Car;

import java.util.List;
import java.util.Map;


/**
 * Created by USER on 30/12/2017.
 */

public class BranchesExpandableListAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<Branch> branches;
    private Map<Long,List<Car>> carMap;

    public BranchesExpandableListAdapter(Context context) {

        this.context = context;
        this.branches = DBManagerFactory.getManager().getBranches();
        this.carMap = DBManagerFactory.getManager().mapCarsByBranch();
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.carMap.get(this.branches.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return this.carMap.get(this.branches.get(groupPosition)).get(childPosition).getIdCarNumber();
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        //final Car childCar = (Car) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.car_item_card, null);
        }

        /*View carListChild = (View) convertView
                .findViewById(R.id.card_view);
*/

        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.carMap.get(this.branches.get(groupPosition))
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.branches.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this.branches.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return this.branches.get(groupPosition).getBranchID();
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        //String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.branch_item, null);
        }
/*
        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.lblListHeader);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);*/

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}

