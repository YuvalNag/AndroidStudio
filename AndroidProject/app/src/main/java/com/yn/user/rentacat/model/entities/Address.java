package com.yn.user.rentacat.model.entities;

import java.io.Serializable;

/**
 * Created by USER on 07/11/2017.
 */

public class Address implements Serializable {

    private String City,Street;

    private int Number;

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getStreet() {
        return Street;
    }

    public void setStreet(String street) {
        Street = street;
    }

    public int getNumber() {
        return Number;
    }

    public void setNumber(int number) {
        Number = number;
    }

    public Address(String city, String street, int number) {
        City = city;
        Street = street;
        Number = number;
    }
}
