package com.yn.user.cliantapplication.controller;


import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.ContentValues;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.yn.user.cliantapplication.R;
import com.yn.user.cliantapplication.model.backend.AppContract;
import com.yn.user.cliantapplication.model.backend.DBManagerFactory;
import com.yn.user.cliantapplication.model.entities.Branch;
import com.yn.user.cliantapplication.model.entities.Car;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by USER on 31/12/2017.
 */

public class CarModelFragment extends Fragment {
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    FloatingActionButton fab;
    SharedPreferences sharedPreferences;
    ViewGroup container;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.branches_fragment, container, false);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        this.container=container;
        findView(view);
        return view;
    }

    private void findView(View view) {
        // get the listview
        expListView = (ExpandableListView) view.findViewById(R.id.branch_expandable_list_view);
        fab = (FloatingActionButton) view.findViewById(R.id.open_order_floatingActionButton);
        fab.setVisibility(View.INVISIBLE);
        buildAdapter();
    }

    private void buildAdapter() {
        new AsyncTask<Void, Void, ExpandableListAdapter>() {
            @Override
            protected ExpandableListAdapter doInBackground(Void... voids) {

                return new CarModelExpandableListAdapter(getActivity(), DBManagerFactory.getManager().getCarModels(), DBManagerFactory.getManager().mapBranchsByCarModel());
            }

            @Override
            protected void onPostExecute(ExpandableListAdapter expandableListAdapter) {
                // setting list adapter
                super.onPostExecute(expandableListAdapter);
                listAdapter=expandableListAdapter;
                expListView.setAdapter(expandableListAdapter);

            }
        }.execute();

    }

    @Override
    public void onStart() {
        super.onStart();

        // Listview on child click listener
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                openOrder(v,groupPosition,childPosition);

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
    private  void openOrder(final View viewChild, int groupPosition, int childPosition) {
        fab.setVisibility(View.VISIBLE);
        final Branch branch = (Branch) listAdapter.getChild(groupPosition,childPosition);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, String.valueOf(sharedPreferences.getLong(getString(R.string.login_user_id),-1))+" Order car?", Snackbar.LENGTH_LONG)
                        .setAction("Yes", new View.OnClickListener() {

                            @SuppressLint("StaticFieldLeak")
                            @Override
                            public void onClick(final View view) {
                                // Create fragment and give it an argument for the selected article

                                BranchesFragment branchesFragment= new BranchesFragment();
                                Bundle args = new Bundle();
                                args.putLong(BranchesFragment.BRANCH_ID,branch.getBranchID());
                                branchesFragment.setArguments(args);
                                getActivity().getFragmentManager().beginTransaction()
                                        .replace(container.getId(), branchesFragment,"findThisFragment")
                                        .addToBackStack(null)
                                        .commit();



                            }
                        }).show();
            }
        });

    }

}