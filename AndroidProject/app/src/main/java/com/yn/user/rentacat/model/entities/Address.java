package com.yn.user.rentacat.model.entities;

import java.io.Serializable;

/**
 * Created by USER on 07/11/2017.
 */

public class Address implements Serializable {
    private String city, street;
    private int number;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Address(String city, String street, int number) {
        this.city = city;
        this.street = street;
        this.number = number;
    }
}
