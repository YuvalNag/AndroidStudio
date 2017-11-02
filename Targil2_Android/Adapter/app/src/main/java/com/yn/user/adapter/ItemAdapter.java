package com.yn.user.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.yn.user.adapter.R;

import java.util.List;

/**
 * Created by USER on 01/11/2017.
 */

public class ItemAdapter extends BaseAdapter {
    List<String> content;
    Context context;
    public ItemAdapter(Context c, List<String> content) {
        context = c;
        this.content = content;
    }
    @Override
    public int getCount() {
        return content.size();
    }
    @Override
    public Object getItem(int position) {
        return content.get(position);
    }
    @Override
    public long getItemId(int position) {
        return 0;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       // LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
       ;
        if (convertView == null) {
            // get layout from resources
            convertView = View.inflate(context, R.layout.item, null);}

        // set image based on selected text
        Button btn = (Button) convertView.findViewById(R.id.myButton);
        btn.setText(content.get(position));
        TextView textview = (TextView) convertView.findViewById(R.id.myText);
        textview.setText("Button " + position);

        return convertView;
    }
}
