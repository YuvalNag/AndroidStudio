package com.yn.user.rentacat.model.entities;

import java.io.Serializable;

/**
 * Created by nissy34 on 07/11/2017.
 */

public class CarModel implements Serializable
{
    private long idCarModel;

    private String compenyName;

    private String modelName;

    private long engineCapacity;

    private TransmissionType transmissionType;

    private long numOfSeats;

    private char carClass;

    private String imageCarPath;

    public CarModel(long idCarModel, String compenyName, String modelName,
                    long engineCapacity, TransmissionType transmissionType, long numOfSeats,char carClass, String imageCarPath) {
         setIdCarModel(idCarModel);
         setCompenyName(compenyName);
         setModelName( modelName);
         setEngineCapacity(engineCapacity);
         setTransmissionType(transmissionType);
         setNumOfSeats(numOfSeats);
         setCarClass(carClass);
         setImageCarPath(imageCarPath);

    }
    public String getImageCarPath() { return imageCarPath; }

    public void setImageCarPath(String imageCarPath) { this.imageCarPath = imageCarPath; }

    public char getCarClass() {
        return carClass;
    }

    public void setCarClass(char carClass) {
        this.carClass = carClass;
    }

    public long getIdCarModel() {
        return idCarModel;
    }

    public void setIdCarModel(long idCarModel) {
        this.idCarModel = idCarModel;
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
        this.engineCapacity = Math.max(engineCapacity,1);
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
        this.numOfSeats = Math.max(numOfSeats,1);
    }
}

