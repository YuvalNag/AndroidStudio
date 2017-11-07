package com.yn.user.rentacat.model.entities;

import java.io.Serializable;

/**
 * Created by nissy34 on 07/11/2017.
 */

public class CarModel implements Serializable
{
 long carModel;

 String compenyName;

 String modelName;

 long engineCapacity;

 TransmissionType transmissionType;

 long numOfSeats;

    public CarModel(long carModel, String compenyName, String modelName, long engineCapacity, TransmissionType transmissionType, long numOfSeats) {
        this.carModel = carModel;
        this.compenyName = compenyName;
        this.modelName = modelName;
        this.engineCapacity = engineCapacity;
        this.transmissionType = transmissionType;
        this.numOfSeats = numOfSeats;
    }

    public long getCarModel() {
        return carModel;
    }

    public void setCarModel(long carModel) {
        this.carModel = carModel;
    }

    public String getCompenyName() {
        return compenyName;
    }

    public void setCompenyName(String compenyName) {
        this.compenyName = compenyName;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public long getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(long engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public TransmissionType getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(TransmissionType transmissionType) {
        this.transmissionType = transmissionType;
    }

    public long getNumOfSeats() {
        return numOfSeats;
    }

    public void setNumOfSeats(long numOfSeats) {
        this.numOfSeats = numOfSeats;
    }
}

