package com.yn.user.cliantapplication.controller;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.yn.user.cliantapplication.R;

/**
 * Created by USER on 30/12/2017.
 */

public class BranchesFragment extends Fragment {
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;

    @Nullable
    @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view=     inflater.inflate(R.layout.branches_fragment,container,false);
            findView(view);
            return view;
        }

    private void findView(View view) {
        // get the listview
        expListView = (ExpandableListView) view.findViewById(R.id.branch_expandable_list_view);
        listAdapter = new BranchesExpandableListAdapter(getActivity());
        // setting list adapter
        expListView.setAdapter(listAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        // Listview on child click listener
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                Toast.makeText(
                        getActivity().getApplicationContext(),
                        "chid id: "+listAdapter.getChildId(groupPosition,childPosition), Toast.LENGTH_SHORT)
                        .show();
                return false;
            }
        });

        // Listview Group expanded listener
        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getActivity().getBaseContext(),
                        "group id: "+ listAdapter.getGroupId(groupPosition) + " Expanded",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
