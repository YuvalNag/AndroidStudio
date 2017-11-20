package com.yn.user.rentacat.model.datasource;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.yn.user.rentacat.model.backend.AppContract;
import com.yn.user.rentacat.model.entities.Address;
import com.yn.user.rentacat.model.entities.Branch;
import com.yn.user.rentacat.model.entities.Car;
import com.yn.user.rentacat.model.entities.CarClass;
import com.yn.user.rentacat.model.entities.CarModel;
import com.yn.user.rentacat.model.entities.Client;


import com.yn.user.rentacat.model.entities.Order;


import com.yn.user.rentacat.model.entities.Client;
import com.yn.user.rentacat.model.entities.Order;
import com.yn.user.rentacat.model.entities.TransmissionType;


import com.yn.user.rentacat.model.entities.TransmissionType;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.concurrent.Phaser;



/**
 * Created by nissy34 on 07/11/2017.
 */

public class Tools {

    public static ContentValues AddressToContentValues(Address address) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(AppContract.Address.CITY, address.getCity());
        contentValues.put(AppContract.Address.STREET, address.getStreet());
        contentValues.put(AppContract.Address.NUMBER, address.getNumber());
        return contentValues;
    }


    public static ContentValues BranchToContentValues(Branch branch) {
        ContentValues contentValues = AddressToContentValues(branch.getBranchAddress());;
        contentValues.put(AppContract.Branch.BRANCH_ID,branch.getBranchID()) ;
        contentValues.put(AppContract.Branch.NUMBER_OF_PARKING_SPACES, branch.getNumberOfParkingSpaces());
        return contentValues;
    }
    public static ContentValues CarToContentValues(Car car) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(AppContract.Car.ID_CAR_NUMBER, car.getIdCarNumber());
        contentValues.put(AppContract.Car.CAR_MODEL_ID, car.getCarModelID());
        contentValues.put(AppContract.Car.BRANCH_NUM, car.getBranchNum());
        contentValues.put(AppContract.Car.KILOMETRERS, car.getKilometers());
        return contentValues;
    }

    public static ContentValues CarModelToContentValues(CarModel carModel) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(AppContract.CarModel.ID_CAR_MODEL, carModel.getIdCarModel());
        contentValues.put(AppContract.CarModel.COMPENY_NAME, carModel.getCompenyName());
        contentValues.put(AppContract.CarModel.ENGINE_COPACITY, carModel.getEngineCapacity());
        contentValues.put(AppContract.CarModel.MODEL_NAME, carModel.getModelName());
        contentValues.put(AppContract.CarModel.TRANSMISSION_TYPE, carModel.getTransmissionType().toString());
        contentValues.put(AppContract.CarModel.NUM_OF_SEATS, carModel.getNumOfSeats());
        contentValues.put(AppContract.CarModel.IMG, carModel.getCarPicByteArray());
        contentValues.put(AppContract.CarModel.CLASS_OF_CAR, carModel.getCarClass().toString());

        return contentValues;
    }

    public static ContentValues ClientToContentValues(Client client) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(AppContract.Client.ID, client.getId());
        contentValues.put(AppContract.Client.FIRST_NAME,   client.getFirstName());
        contentValues.put(AppContract.Client.LAST_NAME,    client.getLastName());
        contentValues.put(AppContract.Client.EMAIL_ADDR,   client.getEmailAdrs());
        contentValues.put(AppContract.Client.PHONE_NUMBER, client.getPhoneNum());
        contentValues.put(AppContract.Client.CRADIT_NUMBER,client.getCraditNumber());
        contentValues.put(AppContract.Client.PASSWORD,client.getPassword());

        return contentValues;
    }
    //TODO public static ContentValues OrderToContentValues(Order order) {}


    public static Address ContentValuesToAddress(ContentValues contentValues)
    {
        Integer number = contentValues.getAsInteger(AppContract.Address.NUMBER);
       // if (number == null || number <= 0 )
       //     throw new IllegalArgumentException();
        return new Address(
                contentValues.getAsString(AppContract.Address.CITY),
                contentValues.getAsString(AppContract.Address.STREET),
                number);
    }

    public static Branch ContentValuesToBranch(ContentValues contentValues)
    {
        Long id = contentValues.getAsLong(AppContract.Branch.BRANCH_ID);
        Integer numberOfParkingSpaces = contentValues.getAsInteger(AppContract.Branch.NUMBER_OF_PARKING_SPACES);
      // if(id <= 0 || id == null || numberOfParkingSpaces < 0 )
      //     throw new IllegalArgumentException();

        return new Branch(id, numberOfParkingSpaces,//TODO check if null.intValue() == 0
                          ContentValuesToAddress(contentValues));
    }
    public static Car ContentValuesToCar(ContentValues contentValues)
    {

               Long branchnuum= contentValues.getAsLong(AppContract.Car.BRANCH_NUM);
               Long carmodelId= contentValues.getAsLong(AppContract.Car.CAR_MODEL_ID);
               Long kilo= contentValues.getAsLong(AppContract.Car.KILOMETRERS);
               Long carNum= contentValues.getAsLong(AppContract.Car.ID_CAR_NUMBER);

        //if(branchnuum==null || branchnuum<0 ||
        //        carmodelId==null || carmodelId<0 ||
        //        kilo==null || kilo<0 ||
        //        carNum==null || carNum<0)
        //    throw new IllegalArgumentException();

        return new Car(
                branchnuum,
                carmodelId,
                kilo,
                carNum
        );

    }
    public static CarModel ContentValuesToCarModel(ContentValues contentValues)
    {

              Long id=  contentValues.getAsLong(AppContract.CarModel.ID_CAR_MODEL);
              String compenyName=  contentValues.getAsString(AppContract.CarModel.COMPENY_NAME);
              String modalName=  contentValues.getAsString(AppContract.CarModel.MODEL_NAME);
              Long engine=  contentValues.getAsLong(AppContract.CarModel.ENGINE_COPACITY);
              TransmissionType transmissionType =  TransmissionType.valueOf(contentValues.getAsString(AppContract.CarModel.TRANSMISSION_TYPE));
              Long numofseats=  contentValues.getAsLong(AppContract.CarModel.NUM_OF_SEATS);
              Bitmap bitmap=byteToImage(contentValues.getAsByteArray(AppContract.CarModel.IMG));
              CarClass classofcar= CarClass.valueOf(contentValues.getAsString(AppContract.CarModel.CLASS_OF_CAR));
             // if(id==null || id<0 ||
             //         numofseats==null || numofseats<0)
             //     throw new IllegalArgumentException();

              return new CarModel(
                id,
                compenyName,
                modalName,
                engine,
                transmissionType,
                numofseats,
                      classofcar ,
                      bitmap
                );
    }
    public static Client ContentValuesToClient(ContentValues contentValues) throws Exception {
        return  new Client(
                contentValues.getAsString(AppContract.Client.LAST_NAME),
                contentValues.getAsString(AppContract.Client.FIRST_NAME),
                contentValues.getAsString(AppContract.Client.EMAIL_ADDR),
                contentValues.getAsLong(AppContract.Client.ID),
                contentValues.getAsString(AppContract.Client.PHONE_NUMBER),
                contentValues.getAsLong(AppContract.Client.CRADIT_NUMBER),
                contentValues.getAsString(AppContract.Client.PASSWORD)

                );
    }


    public static Cursor CarListToCursor(List<Car> cars) {
        String[] columns = new String[]
                {
                        AppContract.Car.ID_CAR_NUMBER,
                        AppContract.Car.CAR_MODEL_ID,
                        AppContract.Car.BRANCH_NUM,
                        AppContract.Car.KILOMETRERS,
                };

        MatrixCursor matrixCursor = new MatrixCursor(columns);

        for (Car car : cars) {
            matrixCursor.addRow(new Object[]
                    {
                            car.getIdCarNumber(),
                            car.getCarModelID(),
                            car.getBranchNum(),
                            car.getKilometers()
                    });
        }

        return matrixCursor;
    }

    public static Cursor carModelsListToCursor(List<CarModel> carModels) {
        String[] columns = new String[]
                {
                        AppContract.CarModel.ID_CAR_MODEL,
                        AppContract.CarModel.COMPENY_NAME,
                        AppContract.CarModel.ENGINE_COPACITY,
                        AppContract.CarModel.MODEL_NAME,
                        AppContract.CarModel.NUM_OF_SEATS,
                        AppContract.CarModel.TRANSMISSION_TYPE,
                        AppContract.CarModel.CLASS_OF_CAR,
                        AppContract.CarModel.IMG

                };

        MatrixCursor matrixCursor = new MatrixCursor(columns);

        for (CarModel carModel : carModels) {
            matrixCursor.addRow(new Object[]
                    {
                            carModel.getIdCarModel(),
                            carModel.getCompenyName(),
                            carModel.getEngineCapacity(),
                            carModel.getModelName(),
                            carModel.getNumOfSeats(),
                            carModel.getTransmissionType(),
                            carModel.getCarClass(),
                            carModel.getCarPicByteArray()
                    });
        }

        return matrixCursor;
    }

    public static Cursor clientListToCursor(List<Client> clients) {
        String[] columns = new String[]
                {
                        AppContract.Client.ID,
                        AppContract.Client.CRADIT_NUMBER,
                        AppContract.Client.EMAIL_ADDR,
                        AppContract.Client.FIRST_NAME,
                        AppContract.Client.LAST_NAME,
                        AppContract.Client.PHONE_NUMBER,
                        AppContract.Client.PASSWORD


                };

        MatrixCursor matrixCursor = new MatrixCursor(columns);

        for (Client client : clients) {
            matrixCursor.addRow(new Object[]
                    {
                            client.getId(),
                            client.getCraditNumber(),
                            client.getEmailAdrs(),
                            client.getFirstName(),
                            client.getLastName(),
                            client.getPhoneNum(),
                            client.getPassword()
                    });
        }

        return matrixCursor;
    }

    public static Cursor branchListToCursor(List<Branch> branches) {
        String[] columns = new String[]
                {
                        AppContract.Branch.BRANCH_ID,
                        AppContract.Branch.ADDRESS,
                        AppContract.Branch.NUMBER_OF_PARKING_SPACES
                };

        MatrixCursor matrixCursor = new MatrixCursor(columns);

        for (Branch branch : branches) {
            matrixCursor.addRow(new Object[]
                    {
                            branch.getBranchID(),
                            branch.getBranchAddress(),
                            branch.getNumberOfParkingSpaces()

                    });



        }
        return matrixCursor;
    }


    public static Cursor addressListToCursor(List<Address> addresses) {
        String[] columns = new String[]
                {
                        AppContract.Address.CITY,
                        AppContract.Address.NUMBER,
                        AppContract.Address.STREET
                };

        MatrixCursor matrixCursor = new MatrixCursor(columns);

        for (Address address : addresses) {
            matrixCursor.addRow(new Object[]
                    {
                            address.getCity(),
                            address.getNumber(),
                            address.getStreet()

                    });



        }
        return matrixCursor;
    }

    public static byte[] imageToByte(Bitmap image) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    public static Bitmap byteToImage(byte[] imageArray) {
      return  BitmapFactory.decodeByteArray(imageArray, 0, imageArray.length);
    }

}



