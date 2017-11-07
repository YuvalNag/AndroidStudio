package com.yn.user.rentacat.model.entities;

import android.provider.Telephony;

import java.io.Serializable;

/**
 * Created by USER on 07/11/2017.
 */

public class Branch implements Serializable{

    private  int BranchID;

    private  int NumberOfParkingSpaces;

    private Address BranchAddress;

    private android.location.Address AddressBranch;

    public Branch(int branchID, int numberOfParkingSpaces, Address branchAddress) {
        BranchID = branchID;
        NumberOfParkingSpaces = numberOfParkingSpaces;
        BranchAddress = branchAddress;
    }

    public Branch(int branchID, int numberOfParkingSpaces, android.location.Address addressBranch) {
        BranchID = branchID;
        NumberOfParkingSpaces = numberOfParkingSpaces;
        AddressBranch = addressBranch;
    }

    public int getBranchID() {
        return BranchID;
    }

    public void setBranchID(int branchID) {
        BranchID = branchID;
    }

    public int getNumberOfParkingSpaces() {
        return NumberOfParkingSpaces;
    }

    public void setNumberOfParkingSpaces(int numberOfParkingSpaces) {
        NumberOfParkingSpaces = numberOfParkingSpaces;
    }

    public Address getBranchAddress() {
        return BranchAddress;
    }

    public void setBranchAddress(Address branchAddress) {
        BranchAddress = branchAddress;
    }

    public android.location.Address getAddressBranch() {
        return AddressBranch;
    }

    public void setAddressBranch(android.location.Address addressBranch) {
        AddressBranch = addressBranch;
    }
}
