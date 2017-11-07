package com.example.mailo.myacadmyproject.controller.fragment;

import android.app.Fragment;
import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.CursorTreeAdapter;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.Filter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mailo.myacadmyproject.R;
import com.example.mailo.myacadmyproject.controller.AddCourseActivity;


public class ListFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    private ListView itemListView;
    private TextView loadingTextView;
    // EditText filterEditText;
    SearchView filterSearchView;
    //  CursorAdapter adapter;

    CursorAdapter adapter;

    public ListFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false);


    }


    @Override
    public void onResume() {
        super.onResume();

    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        itemListView = (ListView) getActivity().findViewById(R.id.ItemListView);
        loadingTextView = (TextView) getActivity().findViewById(R.id.loadingTextView);
//        filterEditText = (EditText) getActivity().findViewById(R.id.filterEditText);
//        filterEditText.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                adapter.getFilter().filter(s);
//                adapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });

        filterSearchView = (SearchView) getActivity().findViewById(R.id.filterSearchView);
        filterSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
//        adapter.getFilter().filter(query);
//        adapter.notifyDataSetChanged();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                adapter.getFilter().filter(newText);
                adapter.notifyDataSetChanged();
                return false;
            }
        });
    }

    public void UpdateList(final Uri uri) {

        new AsyncTask<Void, Void, Cursor>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                itemListView.setVisibility(View.INVISIBLE);
                loadingTextView.setVisibility(View.VISIBLE);
            }

            @Override
            protected Cursor doInBackground(Void... params) {
                return getActivity().getContentResolver().query(uri, null, null, null, null);
                // return null;
            }

            @Override
            protected void onPostExecute(final Cursor cursor) {
                super.onPostExecute(cursor);

                adapter = new CursorAdapter(getActivity(), cursor) {
                    @Override
                    public View newView(Context context, Cursor cursor, ViewGroup parent) {
                        TextView tv = new TextView(context);

                        return tv;
                    }

                    @Override
                    public void bindView(View view, Context context, Cursor cursor) {
                        TextView tv = (TextView) view;
                        tv.setText("[" + cursor.getString(0) + "]  " + cursor.getString(1));
                    }


                    @Override
                    public Filter getFilter() {
                        return new Filter() {
                            @Override
                            protected FilterResults performFiltering(CharSequence constraint) {
                                FilterResults results = new FilterResults();

                                // We implement here the filter logic
                                if (constraint == null || constraint.length() == 0) {

                                    // No filter implemented we return all the list
                                    results.values = cursor;
                                    results.count = cursor.getCount();
                                } else {
                                    // We perform filtering operation
                                    MatrixCursor returnCursor = new MatrixCursor(new String[]{"_id", "name"});

                                    cursor.moveToPosition(-1);
                                    while (cursor.moveToNext()) {
                                        String id = cursor.getString(0);
                                        String name = cursor.getString(1);

                                        if (name.toUpperCase().startsWith(constraint.toString().toUpperCase())) {
                                            returnCursor.addRow(new Object[]{id, name});
                                        }
                                    }

                                    results.values = returnCursor;
                                    results.count = returnCursor.getCount();
                                }
                                Toast.makeText(getActivity(), "performFiltering", Toast.LENGTH_LONG).show();
                                return results;

                            }

                            @Override
                            protected void publishResults(CharSequence constraint, FilterResults results) {
                                // Now we have to inform the adapter about the new list filtered
                                Toast.makeText(getActivity(), "publishResults", Toast.LENGTH_LONG).show();
                                if (results.count == 0)
                                    notifyDataSetInvalidated();
                                else {
                                    changeCursor((Cursor) results.values);
                                    notifyDataSetChanged();
                                    //    }

                                }
                            }


                        };
                    }


//                adapter = new CursorTreeAdapter(cursor,getActivity()) {
//                    @Override
//                    protected Cursor getChildrenCursor(Cursor groupCursor) {
//                        return null;
//                    }
//
//                    @Override
//                    protected View newGroupView(Context context, Cursor cursor, boolean isExpanded, ViewGroup parent) {
//                        return null;
//                    }
//
//                    @Override
//                    protected void bindGroupView(View view, Context context, Cursor cursor, boolean isExpanded) {
//
//                    }
//
//                    @Override
//                    protected View newChildView(Context context, Cursor cursor, boolean isLastChild, ViewGroup parent) {
//                        return null;
//                    }
//
//                    @Override
//                    protected void bindChildView(View view, Context context, Cursor cursor, boolean isLastChild) {
//
//                    }
//                };

                };

                itemListView.setAdapter(adapter);
                itemListView.setVisibility(View.VISIBLE);
                loadingTextView.setVisibility(View.INVISIBLE);
            }

            ;
        }.execute();

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
