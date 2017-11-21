package com.yn.user.rentacat.model.entities;

import java.io.Serializable;

/**
 * Created by USER on 07/11/2017.
 */

public class Branch implements Serializable{


    private  long branchID;

    private  int numberOfParkingSpaces;

    private Address branchAddress;

    private android.location.Address addressBranch;


    public Branch(long branchID, int numberOfParkingSpaces, Address branchAddress) {

        this.branchID = branchID;
        this.numberOfParkingSpaces = numberOfParkingSpaces;
        this.branchAddress = branchAddress;
    }


    public long getBranchID() {
        return branchID;
    }

    public void setBranchID(long branchID) {
        this.branchID = branchID;
    }

    public int getNumberOfParkingSpaces() {
        return numberOfParkingSpaces;
    }

    public void setNumberOfParkingSpaces(int numberOfParkingSpaces) {
        this.numberOfParkingSpaces = numberOfParkingSpaces;
    }

    public Address getBranchAddress() {
        return branchAddress;
    }

    public void setBranchAddress(Address branchAddress) {
        this.branchAddress = branchAddress;
    }

    public android.location.Address getAddressBranch() {
        return addressBranch;
    }

    public void setAddressBranch(android.location.Address addressBranch) {
        this.addressBranch = addressBranch;
    }
}
