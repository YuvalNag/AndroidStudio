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
import com.yn.user.cliantapplication.controller.adapters.CarModelExpandableListAdapter;
import com.yn.user.cliantapplication.model.backend.AppContract;
import com.yn.user.cliantapplication.model.backend.DBManagerFactory;
import com.yn.user.cliantapplication.model.entities.Branch;
import com.yn.user.cliantapplication.model.entities.Car;
import com.yn.user.cliantapplication.model.entities.CarModel;

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
        this.container = container;
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
                listAdapter = expandableListAdapter;
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
                openOrder(v, groupPosition, childPosition);

                return false;
            }
        });

        // Listview Group expanded listener
        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getActivity().getBaseContext(),
                        "group id: " + listAdapter.getGroupId(groupPosition) + " Expanded",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void openOrder(final View viewChild, final int groupPosition, int childPosition) {
        fab.setVisibility(View.VISIBLE);
        final Branch branch = (Branch) listAdapter.getChild(groupPosition, childPosition);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, String.valueOf(sharedPreferences.getLong(getString(R.string.login_user_id), -1)) + " Order car?", Snackbar.LENGTH_LONG)
                        .setAction("Yes", new View.OnClickListener() {

                            @SuppressLint("StaticFieldLeak")
                            @Override
                            public void onClick(final View view) {
                                // Create fragment and give it an argument for the selected article

                              /*  BranchesFragment branchesFragment= new BranchesFragment();
                                Bundle args = new Bundle();
                                args.putLong(BranchesFragment.BRANCH_ID,branch.getBranchID());
                                branchesFragment.setArguments(args);
                                getActivity().getFragmentManager().beginTransaction()
                                        .replace(container.getId(), branchesFragment,"findThisFragment")
                                        .addToBackStack(null)
                                        .commit();*/
                                Snackbar.make(view, "Client id: " + String.valueOf(sharedPreferences.getLong(getString(R.string.login_user_id), 0)), Snackbar.LENGTH_LONG).show();

                                Calendar c = Calendar.getInstance();
                                SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                                final String datetime = dateformat.format(c.getTime());


                                new AsyncTask<Void, Void, Long>() {
                                    @Override
                                    protected Long doInBackground(Void... params) {
                                        Car car = null;
                                        for (Car availableCar : DBManagerFactory.getManager().getAvailableCarsByBranche(branch.getBranchID())) {
                                            if (availableCar.getCarModelID() == ((CarModel) listAdapter.getGroup(groupPosition)).getIdCarModel())
                                                car = availableCar;
                                        }
                                        if (car != null) {
                                            ContentValues orderContentValues = new ContentValues();
                                            orderContentValues.put(AppContract.Order.CAR_NUM, String.valueOf(car.getIdCarNumber()));
                                            orderContentValues.put(AppContract.Order.KILOMETERS_AT_RENT, String.valueOf(car.getKilometers()));
                                            orderContentValues.put(AppContract.Order.CLIENT_ID, String.valueOf(sharedPreferences.getLong(getString(R.string.login_user_id), 1)));
                                            orderContentValues.put(AppContract.Order.RENT_DATE, datetime);
                                            orderContentValues.put(AppContract.Order.ORDER_STATUS, String.valueOf(0));
                                            return DBManagerFactory.getManager().addOrder(orderContentValues);

                                        }
                                        return Long.valueOf(-1);

                                    }

                                    @Override
                                    protected void onPostExecute(Long result) {
                                        super.onPostExecute(result);


                                        if (result > 0) {
                                            Snackbar.make(viewChild, "Car ordered: " + result, Snackbar.LENGTH_LONG).show();
                                            fab.setVisibility(View.INVISIBLE);
                                            buildAdapter();
                                        } else {
                                            Snackbar.make(viewChild, "ERROR ordering car.", Snackbar.LENGTH_LONG).show();

                                        }
                                    }
                                }.execute();
                            }
                        }).show();
            }
        });


    }
}