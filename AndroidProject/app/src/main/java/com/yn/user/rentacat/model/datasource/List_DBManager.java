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

    public boolean hasBranch(long branch_id) {
        for (Branch branch:branches)
            if(branch.getBranchID()==branch_id)
                return true;
        return false;
    }

    @Override
    public long addClient(ContentValues values){
        try {
            Client client = Tools.ContentValuesToClient(values);

            if (hasClient(client.getId())) {
                Log.i(TAG, "addClient: exist"+client.getId());
                return -2;
            }
            clients.add(client);
            Log.d(TAG, "addClient: "+client.getId());
            return client.getId();
        }catch (Exception e)
        {
            Log.e(TAG, "addClient: "+e.getMessage(),e);
            return -1;

        }

    }

    @Override
    public long addCarModel(ContentValues values) {


        try {
            CarModel carModel = Tools.ContentValuesToCarModel(values);
            if (hasCarModel(carModel.getIdCarModel())) {
                Log.i(TAG, "addCarModel: exist"+carModel.getIdCarModel());
                return -2;
            }
            carModels.add(carModel);
            Log.d(TAG, "addCarModel: "+carModel.getIdCarModel());
            return carModel.getIdCarModel();
        } catch (Exception e) {
            Log.e(TAG, "addCarModel: " + e.getMessage(),e);
            return -1;

        }
    }




    @Override
    public long addCar(ContentValues values) {
        try {
            Car car = Tools.ContentValuesToCar(values);
            if (hasCar(car.getCarModelID())) {
                Log.d(TAG, "addCar: exist"+car.getCarModelID());
                return -2;
            }
            cars.add(car);
            Log.d(TAG, "addCar: "+car.getCarModelID());
            return car.getIdCarNumber();
        }catch (Exception e)
            {
                Log.e(TAG, "addCar: "+e.getMessage(),e );
                return -1;

            }
    }

    @Override
    public long addBranch(ContentValues values) {
        try {
            Branch branch = Tools.ContentValuesToBranch(values);
            if (hasBranch(branch.getBranchID())) {
                Log.d(TAG, "addBranch: exist"+branch.getBranchID());
                return -2;
            }
            branches.add(branch);
            Log.d(TAG, "addBranch: "+branch.getBranchID());
            return branch.getBranchID();
        }catch (Exception e)
        {
            Log.e(TAG, "addBranch: "+e.getMessage(),e );
            return -1;

        }
    }

    @Override
    public boolean removeClient(long id) {
        Client client = null;
        for (Client item : clients)
            if (item.getId() == id) {
                client = item;
                //isUpdate = true;
                break;
            }
        return clients.remove(client);
    }

    @Override
    public boolean removeCarModel(long id) {
        CarModel carModel = null;
        for (CarModel item : carModels)
            if (item.getIdCarModel() == id) {
                carModel = item;
                //isUpdate = true;
                break;
            }
        return carModels.remove(carModel);
    }

    @Override
    public boolean removeCar(long id) {
        Car car = null;
        for (Car item : cars)
            if (item.getIdCarNumber() == id) {
                car = item;
                //isUpdate = true;
                break;
            }
        return cars.remove(car);
    }


    @Override
    public boolean removeBranch(long id) {
        Branch branch = null;
        for (Branch item : branches)
            if (item.getBranchID() == id) {
                branch = item;
                //isUpdate = true;
                break;
            }
        return branches.remove(branch);
    }

    @Override
    public boolean updateClient(long id, ContentValues values) {
        try {
            Client client = Tools.ContentValuesToClient(values);
            for(int i=0;i<clients.size();i++)
                if(clients.get(i).getId()==id) {
                    clients.set(i, client);
                    Log.d(TAG, "updateClient: "+id);
                    return true;
                }
        } catch (Exception e) {
            Log.e(TAG, "updateClient: "+id,e );
        }
        return false;
    }

    @Override
    public boolean updateCar(long id, ContentValues values) {
        try {
            Car car = Tools.ContentValuesToCar(values);
            for(int i=0;i<cars.size();i++)
                if(cars.get(i).getIdCarNumber()==id) {
                    cars.set(i, car);
                    Log.d(TAG, "updateCar: "+id);
                    return true;
                }
        } catch (Exception e) {
            Log.e(TAG, "updateCar: "+id,e );
        }
        return false;
    }

    @Override
    public boolean updateCarModel(long id, ContentValues values) {
        try {
            CarModel carModel = Tools.ContentValuesToCarModel(values);
            for(int i=0;i<carModels.size();i++)
                if(carModels.get(i).getIdCarModel()==id) {
                    carModels.set(i, carModel);
                    Log.d(TAG, "updateCarModel: "+id);
                    return true;
                }
        } catch (Exception e) {
            Log.e(TAG, "updateCarModel: "+id,e);
        }
        return false;
    }

    @Override
    public boolean updateBranch(long id, ContentValues values) {
        try {
            Branch branch = Tools.ContentValuesToBranch(values);
            for(int i=0;i<branches.size();i++)
                if(branches.get(i).getBranchID()==id){
                    branches.set(i,branch);
                    Log.d(TAG, "updateBranch: "+id);
                    return true;
                }
        } catch (Exception e) {
            Log.e(TAG, "updateBranch: "+id,e );
        }
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