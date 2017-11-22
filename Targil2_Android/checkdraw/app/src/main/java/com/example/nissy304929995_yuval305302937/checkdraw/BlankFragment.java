package com.example.nissy304929995_yuval305302937.checkdraw;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BlankFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BlankFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.fragbar,menu);
        Toast.makeText(getActivity(), "onCreateOptionsMenu", Toast.LENGTH_SHORT).show();
       final MyAdapter arrayAdapter= (MyAdapter)((ListView)(getView().findViewById(R.id.conslistview))).getAdapter();
        MenuItem searchViewItem = menu.findItem(R.id.search);
        final SearchView searchViewAndroidActionBar = (SearchView) MenuItemCompat.getActionView(searchViewItem);
        searchViewAndroidActionBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //searchViewAndroidActionBar.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Toast.makeText(getActivity(), "onQueryTextChange", Toast.LENGTH_SHORT).show();

                arrayAdapter.getFilter().filter(newText);
                arrayAdapter.notifyDataSetChanged();
                return true;
            }
        });
       super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private OnFragmentInteractionListener mListener;

    public BlankFragment() {
        // Required empty public constructor

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BlankFragment newInstance(String param1, String param2) {
        BlankFragment fragment = new BlankFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    AlertDialog.OnClickListener onClickListener = new DialogInterface.OnClickListener() {

        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                case Dialog.BUTTON_NEGATIVE:

                    Toast.makeText(getActivity(), "negative button click", Toast.LENGTH_SHORT).show();
                    break;

                case Dialog.BUTTON_NEUTRAL:

                    Toast.makeText(getActivity(), "netural button click", Toast.LENGTH_SHORT).show();
                    break;
                case Dialog.BUTTON_POSITIVE:
                    Toast.makeText(getActivity(), "positive button click", Toast.LENGTH_SHORT).show();

                    break;
            }
        }};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Toast.makeText(getActivity(), "onCreateView", Toast.LENGTH_SHORT).show();

       final View view1 = inflater.inflate(R.layout.layout, container, false);
      //((TextView) view1.findViewById(R.id.textView2));
        List<String> list = new ArrayList<String>();
        list = Arrays.asList(strings.CHEESES);
       /* ((ListView) view1.findViewById(R.id.conslistview)).setAdapter(new ArrayAdapter<String>(getActivity(),
                R.layout.item, list));*/

 /*((ListView) view1.findViewById(R.id.conslistview)).setAdapter(new ArrayAdapter(getActivity(),
         R.layout.item, list) {
     @NonNull
     @Override
     public Filter getFilter() {
         return new Filter() {
             @Override
             protected FilterResults performFiltering(CharSequence charSequence) {
                 List<String> strings=new ArrayList<>();
                 for (int i=0;i<getCount();i++)
                 {
                    if(((String)getItem(i)).toLowerCase().contains(charSequence.toString().toLowerCase()))
                        strings.add((String)getItem(i));
                 }
                 FilterResults filterResults=new FilterResults();
                 filterResults.count=strings.size();
                 filterResults.values=strings;
                 return filterResults;
             }

             @Override
             protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

             }
         };
     }
 });*/

        ((ListView) view1.findViewById(R.id.conslistview)).setAdapter(new MyAdapter(getActivity(),list));





        ((ListView) view1.findViewById(R.id.conslistview)).setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ((TextView)view1.findViewById(R.id.textView2)).setText(((TextView)view.findViewById(R.id.itemtext)).getText());

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
                alertDialogBuilder.setTitle("dialog title"); //alertDialogBuilder.setMessage("dialog message ....");

                alertDialogBuilder.setNeutralButton("Remind me later",onClickListener);
                alertDialogBuilder.setPositiveButton("Ok",onClickListener);
                alertDialogBuilder.setNegativeButton("Cancel ",onClickListener);


                /*alertDialogBuilder.setAdapter(new ArrayAdapter<String>(getActivity(),
                        R.layout.item, Arrays.asList(strings.CHEESES)), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });*/

                View viewfodialog =getLayoutInflater().inflate(R.layout.activity_maps,null);
//                ((TextView)viewfodialog.findViewById(R.id.itemtext)).setText(((TextView)view.findViewById(R.id.itemtext)).getText());

                alertDialogBuilder.setView(viewfodialog);
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();

            }
        });
return view1;
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
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
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
