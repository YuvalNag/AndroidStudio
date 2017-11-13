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

    static {
         cars=new ArrayList<>();
         carModels=new ArrayList<>();
         clients=new ArrayList<>();
         orders=new ArrayList<>();
         branches=new ArrayList<>();
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
     try {   //TODO add try and catch and check for uniq
         CarModel carModel = Tools.ContentValuesToCarModel(values);
         carModels.add(carModel);
         return carModel.getIdCarModel();
     }
     catch(Exception e)
     {
         Log.e(TAG, "addCarModel: "+e.getMessage());
        return -1;
     }
    }


    @Override
    public long addCar(ContentValues values) {
        Car car = Tools.ContentValuesToCar(values);
        cars.add(car);
        return car.getIdCarNumber();
    }

    @Override
    public long addBranch(ContentValues values) {
        return 0;
    }

    @Override
    public boolean removeClient(long id) {
        return false;
    }

    @Override
    public boolean removeCarModel(long id) {
        return false;
    }

    @Override
    public boolean removeCar(long id) {
        return false;
    }

    @Override
    public boolean removeBranch(long id) {
        return false;
    }

    @Override
    public boolean updateClient(long id, ContentValues values) {
        return false;
    }

    @Override
    public boolean updateCar(long id, ContentValues values) {
        return false;
    }

    @Override
    public boolean updateCarModel(long id, ContentValues values) {
        return false;
    }

    @Override
    public boolean updateBranch(long id, ContentValues values) {
        return false;
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
