package com.yn.user.rentacat.model.datasource;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import com.yn.user.rentacat.model.backend.DB_manager;
import com.yn.user.rentacat.model.entities.Branch;
import com.yn.user.rentacat.model.entities.Car;
import com.yn.user.rentacat.model.entities.CarModel;
import com.yn.user.rentacat.model.entities.Client;
import com.yn.user.rentacat.model.entities.Order;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 07/11/2017.
 */

public class List_DBManager implements DB_manager{
    final String TAG = "List_DBManager";

    static List<Car> cars;
    static List<CarModel> carModels;
    static List<Client> clients;
    static List<Order> orders;
    static List<Branch> branches;

    static{
        List<Car> cars=new ArrayList<>();
        List<CarModel> carModels=new ArrayList<>();
        List<Client> clients=new ArrayList<>();
        List<Order> orders=new ArrayList<>();
        List<Branch> branches=new ArrayList<>();
    }

    @Override
    public boolean hasClient(long client_id) {
        for (Client client:clients)
            if(client.getId()==client_id)
                return true;
        return false;
    }


    public boolean hasCar(long car_id) {
        for (Car car:cars)
            if(car.getIdCarNumber()==car_id)
                return true;
        return false;
    }


    public boolean hasCarModel(long CarMOde_id) {
        for (CarModel carMOde:carModels)
            if(carMOde.getIdCarModel()==CarMOde_id)
                return true;
        return false;
    }

    @Override
    public long addClient(ContentValues values){
        try {
            Client client = Tools.ContentValuesToClient(values);

            if (hasClient(client.getId()))
                return -1;
            clients.add(client);
            Log.d(TAG, "addClient: "+client.getId());
            return client.getId();
        }catch (Exception e)
        {
            Log.e(TAG, "addClient: "+e.getMessage());
            return -1;

        }

    }

    @Override
    public long addCarModel(ContentValues values) {
        //TODO add try and catch and check for uniq
        try {
            CarModel carModel = Tools.ContentValuesToCarModel(values);
           if(hasCarModel(carModel.getIdCarModel()))
               return -1;

            carModels.add(carModel);
            return carModel.getIdCarModel();
        }catch (Exception e)
        {
            Log.e(TAG, "addCarModel: "+e.getMessage() );
            return -1;

        }

    }

    @Override
    public long addCar(ContentValues values) {
        try {
            Car car = Tools.ContentValuesToCar(values);
            if (hasCar(car.getCarModelID()))
                return -1;
            cars.add(car);
            return car.getIdCarNumber();
        }catch (Exception e)
            {
                Log.e(TAG, "addCar: "+e.getMessage() );
                return -1;

            }
    }

    @Override
    public Cursor getCarModels()  {
        return Tools.carModelsListToCursor(carModels);
    }


    @Override
    public Cursor getClients() {
        return Tools.clientListToCursor(clients);
    }

    @Override
    public Cursor getBranches() {
        return Tools.branchListToCursor(branches);
    }

    @Override
    public Cursor getCars() {
        return Tools.CarListToCursor(cars);
    }
}
