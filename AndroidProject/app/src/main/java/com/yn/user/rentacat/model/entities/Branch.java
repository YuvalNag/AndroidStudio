package com.yn.user.rentacat.model.entities;

import java.io.Serializable;

/**
 * Created by USER on 07/11/2017.
 */

public class Branch implements Serializable{

<<<<<<< HEAD
    private  int BranchID;

    private  int NumberOfParkingSpaces;

    private Address BranchAddress;

    private android.location.Address AddressBranch;
=======
    private  int branchID,
                 numberOfParkingSpaces;
    private Address branchAddress;
    private android.location.Address addressBranch;
>>>>>>> 515294208f4b1ba1d25ad9584cb7eeb54cc1fe06

    public Branch(int branchID, int numberOfParkingSpaces, Address branchAddress) {
        this.branchID = branchID;
        this.numberOfParkingSpaces = numberOfParkingSpaces;
        this.branchAddress = branchAddress;
    }

    public Branch(int branchID, int numberOfParkingSpaces, android.location.Address addressBranch) {
        this.branchID = branchID;
        this.numberOfParkingSpaces = numberOfParkingSpaces;
        this.addressBranch = addressBranch;
    }

    public int getBranchID() {
        return branchID;
    }

    public void setBranchID(int branchID) {
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
