package com.yn.user.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by USER on 02/11/2017.
 */

public class YuvalItemAdapter extends BaseAdapter {
    List<Flower> content;
    Context context;
    public YuvalItemAdapter(Context c, List<Flower> content) {
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
        ImageView imageView = (ImageView) convertView.findViewById(R.id.my_image);
        imageView.setImageURI(Uri.parse(((Flower) content.get(position)).getImageSrc()));
        TextView textview = (TextView) convertView.findViewById(R.id.Yn_text);
        textview.setText(((Flower) content.get(position)).getName());

        return convertView;
    }
}
