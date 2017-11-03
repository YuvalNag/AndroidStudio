package com.yn.user.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

/**
 * Created by USER on 02/11/2017.
 */

public class YuvalItemAdapter extends BaseAdapter
        {
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        // LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ;
        if (convertView == null) {
// get layout from resources
            convertView = View.inflate(context, R.layout.yuvalitem, null);}
// set image based on selected text
        ImageView imageView = (ImageView) convertView.findViewById(R.id.my_image);
        int resId=(((Flower) content.get(position)).getImageId());
        imageView.setImageResource(resId);
        TextView textview = (TextView) convertView.findViewById(R.id.Yn_text);
        textview.setText(((Flower) content.get(position)).getName());
        RatingBar ratingBar=((RatingBar)convertView.findViewById(R.id.my_rating));
        ratingBar.setRating((float)(((Flower) content.get(position)).getRating()));
        final Button my_button =(Button) convertView.findViewById(R.id.my_button);
        final int my_pos=position;
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            int pos=my_pos; Button m_button =my_button;
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

            content.get(pos).setRating((double)ratingBar.getRating());
            m_button.setText(Float.toString(ratingBar.getRating()));



            }
        });




        return convertView;
    }


        }
