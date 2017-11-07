package com.yn.user.rentacat.model.entities;

import java.io.Serializable;

/**
 * Created by nissy34 on 07/11/2017.
 */

public class Car implements Serializable {

    private long branchNum;

    private CarModel carModel;

    private long kilometers;

   private long idCarNumber;

    public Car(long branchNum, CarModel carModel, long kilometers, long idCarNumber) {
        this.branchNum = branchNum;
        this.carModel = carModel;
        this.kilometers = kilometers;
        this.idCarNumber = idCarNumber;
    }

    public long getBranchNum() {
        return branchNum;
    }

    public void setBranchNum(long branchNum) {
        this.branchNum = branchNum;
    }

    public CarModel getCarModel() {
        return carModel;
    }

    public void setCarModel(CarModel carModel) {
        this.carModel = carModel;
    }

    public long getKilometers() {
        return kilometers;
    }

    public void setKilometers(long kilometers) {
        this.kilometers = kilometers;
    }

    public long getIdCarNumber() {
        return idCarNumber;
    }

    public void setIdCarNumber(long idCarNumber) {
        this.idCarNumber = idCarNumber;
    }
}
