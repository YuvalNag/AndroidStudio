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
import com.yn.user.cliantapplication.model.entities.Car;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by USER on 30/12/2017.
 */

public class  BranchesFragment extends Fragment {
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    FloatingActionButton fab;
    SharedPreferences sharedPreferences;


    @Nullable
    @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.branches_fragment, container, false);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
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
               return new BranchesExpandableListAdapter(getActivity(),DBManagerFactory.getManager().getBranches(),DBManagerFactory.getManager().mapCarsByBranch());

            }

            @Override
            protected void onPostExecute(ExpandableListAdapter expandableListAdapter) {
                // setting list adapter
                super.onPostExecute(expandableListAdapter);
                listAdapter=expandableListAdapter;
                expListView.setAdapter(listAdapter);
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
        final Car car = (Car) listAdapter.getChild(groupPosition,childPosition);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, String.valueOf(sharedPreferences.getLong(String.valueOf(R.string.login_user_id),23))+" Order car?", Snackbar.LENGTH_LONG)
                        .setAction("Yes", new View.OnClickListener() {

                            @SuppressLint("StaticFieldLeak")
                            @Override
                            public void onClick(final View view) {
                                Snackbar.make(view, "Client id: " + String.valueOf(sharedPreferences.getLong(getString(R.string.login_user_id),0)), Snackbar.LENGTH_LONG).show();

                                Calendar c = Calendar.getInstance();
                                SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                                String datetime = dateformat.format(c.getTime());

                                final ContentValues orderContentValues= new ContentValues();
                                orderContentValues.put(AppContract.Order.CAR_NUM,String.valueOf(car.getIdCarNumber()));
                                orderContentValues.put(AppContract.Order.KILOMETERS_AT_RENT,String.valueOf(car.getKilometers()));
                                orderContentValues.put(AppContract.Order.CLIENT_ID,String.valueOf(sharedPreferences.getLong(String.valueOf(R.string.login_user_id),1)));
                                orderContentValues.put(AppContract.Order.RENT_DATE,String.valueOf(datetime));
                                orderContentValues.put(AppContract.Order.ORDER_STATUS,String.valueOf(0));




                                new AsyncTask<Void, Void, Long>() {
                                    @Override
                                    protected Long doInBackground(Void... params) {
                                        return DBManagerFactory.getManager().addOrder(orderContentValues);
                                    }

                                    @Override
                                    protected void onPostExecute(Long result) {
                                        super.onPostExecute(result);


                                        if (result > 0) {
                                            Snackbar.make(viewChild, "Car ordered: " + car.getIdCarNumber(), Snackbar.LENGTH_LONG).show();
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
