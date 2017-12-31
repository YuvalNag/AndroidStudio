package com.yn.user.cliantapplication.controller;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.yn.user.cliantapplication.R;
import com.yn.user.cliantapplication.model.backend.DBManagerFactory;
import com.yn.user.cliantapplication.model.entities.Car;
import com.yn.user.cliantapplication.model.entities.CarModel;

import java.util.List;

/**
 * Created by nissy34 on 31/12/2017.
 */

public class CarAdapter extends ArrayAdapter {


    public CarAdapter(@NonNull Context context, @NonNull List objects) {
        super(context, 0, objects);
    }

    @Override
    public long getItemId(int position) {
        return ((Car)getItem(position)).getIdCarNumber();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Car car = ((Car)getItem(position));
        if(convertView==null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.car_item_card, parent, false);

        long modelid = car.getCarModelID();

        CarModel carModel = DBManagerFactory.getManager().getCarModel(modelid);

        if (carModel != null) {

            TextView trans = (TextView) convertView.findViewById(R.id.cars_transmition);
            TextView description = (TextView) convertView.findViewById(R.id.cars_name_description);
            TextView classa = (TextView) convertView.findViewById(R.id.cars_class);
            TextView engine = (TextView) convertView.findViewById(R.id.cars_engineCapacity);
            TextView numseats = (TextView) convertView.findViewById(R.id.cars_numofseats);
            ImageView imageView = (ImageView) convertView.findViewById(R.id.cars_carImage);


            numseats.setText(String.valueOf(carModel.getNumOfSeats()));
            trans.setText(carModel.getTransmissionType().toString());
            description.setText(carModel.getCompenyName() + " " + carModel.getModelName());
            classa.setText(carModel.getCarClass().toString());
            engine.setText(String.valueOf(carModel.getEngineCapacity()));

            GlideApp.with(getContext())
                    .load(carModel.getCarPic())
                    .placeholder(R.drawable.progress_animation)
                    .centerCrop()
                    .into(imageView);
        }

        TextView carid = (TextView) convertView.findViewById(R.id.car_id);
        TextView carkilo = (TextView) convertView.findViewById(R.id.car_kilo);
        TextView branch = (TextView) convertView.findViewById(R.id.car_branch);

        carid.setText(String.valueOf(car.getIdCarNumber()));
        carkilo.setText(String.valueOf(car.getKilometers()));
        branch.setText(String.valueOf(car.getBranchNum()));

        return convertView;

    }
}
