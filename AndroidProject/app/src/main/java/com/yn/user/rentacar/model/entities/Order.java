package com.yn.user.rentacar.model.entities;

/**
 * Created by nissy34 on 07/11/2017.
 */
import java.io.Serializable;
import java.util.Date;
import java.util.regex.Pattern;

public class Order implements Serializable{

    private  long idOrderNum;

    private  long clientId;

    private  long carNumber;

    private  String rentDate;

    private  String returnDate;

    private  long kilometersAtRent;

    private  long  kilometersAtReturn;

    private  Boolean fouled;

    private  long amountOfFoul;

    private  long finalAmount;

    public Order(long idOrderNum, long clientId, long carNumber, String rentDate, String returnDate, long kilometersAtRent, long kilometersAtReturn, Boolean fouled, long amountOfFoul, long finalAmount) {
        this.idOrderNum = idOrderNum;
        this.clientId = clientId;
        this.carNumber = carNumber;
        this.rentDate = rentDate;
        this.returnDate = returnDate;
        this.kilometersAtRent = kilometersAtRent;
        this.kilometersAtReturn = kilometersAtReturn;
        this.fouled = fouled;
        this.amountOfFoul = amountOfFoul;
        this.finalAmount = finalAmount;
    }


    public long getIdOrderNum() {
        return idOrderNum;
    }

    public void setIdOrderNum(long idOrderNum) {
        this.idOrderNum = idOrderNum;
    }

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public long getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(long carNumber) {
        this.carNumber = carNumber;
    }

    public String getRentDate() {
        return rentDate;
    }

    public void setRentDate(String rentDate) {
        this.rentDate = rentDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public long getKilometersAtRent() {
        return kilometersAtRent;
    }

    public void setKilometersAtRent(long kilometersAtRent) {
        this.kilometersAtRent = kilometersAtRent;
    }

    public long getKilometersAtReturn() {
        return kilometersAtReturn;
    }

    public void setKilometersAtReturn(long kilometersAtReturn) {
        this.kilometersAtReturn = kilometersAtReturn;
    }

    public Boolean getFouled() {
        return fouled;
    }

    public void setFouled(Boolean fouled) {
        this.fouled = fouled;
    }

    public long getAmountOfFoul() {
        return amountOfFoul;
    }

    public void setAmountOfFoul(long amountOfFoul) {
        this.amountOfFoul = amountOfFoul;
    }

    public long getFinalAmount() {
        return finalAmount;
    }

    public void setFinalAmount(long finalAmount) {
        this.finalAmount = finalAmount;
    }
}
