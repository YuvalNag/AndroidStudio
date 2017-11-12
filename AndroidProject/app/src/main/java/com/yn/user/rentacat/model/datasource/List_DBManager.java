package com.yn.user.rentacat.model.datasource;

import android.content.ContentValues;
import android.database.Cursor;

import com.yn.user.rentacat.model.backend.DB_manager;
import com.yn.user.rentacat.model.entities.Branch;
import com.yn.user.rentacat.model.entities.Car;
import com.yn.user.rentacat.model.entities.CarModel;
import com.yn.user.rentacat.model.entities.Client;
import com.yn.user.rentacat.model.entities.Order;

import java.util.List;

/**
 * Created by USER on 07/11/2017.
 */

public class List_DBManager implements DB_manager{

    static List<Car> cars;
    static List<CarModel> carModels;
    static List<Client> clients;
    static List<Order> orders;
    static List<Branch> branches;

    @Override
    public boolean hasClient(long client_id) {
        for (Client client:clients)
            if(client.getId()==client_id)
                return true;
        return false;
    }

    @Override
    public long addClient(ContentValues values){
        Client client = Tools.ContentValuesToClient(values);
        clients.add(client);
        return client.getId();
    }

    @Override
    public long addCarModel(ContentValues values) {
        CarModel carModel = Tools.ContentValuesToCarModel(values);
        carModels.add(carModel);
        return carModel.getIdCarModel();
    }

    @Override
    public long addCar(ContentValues values) {
        Car car = Tools.ContentValuesToCar(values);
        cars.add(car);
        return car.getIdCarNumber();
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
