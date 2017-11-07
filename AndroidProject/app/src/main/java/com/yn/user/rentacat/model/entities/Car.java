package com.yn.user.rentacat.model.entities;

import java.io.Serializable;

/**
 * Created by nissy34 on 07/11/2017.
 */

public class Car implements Serializable {

    long branchNum;

    CarModel carModel;

    long kilometers;

    long carNumber;

    public Car(long branchNum, CarModel carModel, long kilometers, long carNumber) {
        this.branchNum = branchNum;
        this.carModel = carModel;
        this.kilometers = kilometers;
        this.carNumber = carNumber;
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

    public long getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(long carNumber) {
        this.carNumber = carNumber;
    }
}
