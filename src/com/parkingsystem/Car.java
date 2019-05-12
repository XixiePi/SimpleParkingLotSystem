package com.parkingsystem;

public class Car {
    //Attributes
    public String plateNumber;
    public boolean isParked;
    public String parkingLotID;
    public int parkingHour;

    public Car(String plateNumber){
        this.plateNumber = plateNumber;
    }

    public int parkCar(String parkingLotID, String plateNumber) {
        // TODO: Check is the car is parked somewhere else;
        this.isParked = true;
        this.plateNumber = plateNumber;
        this.parkingLotID = parkingLotID;

        return 1;
    }

    public int driveAway(int parkingHour) {
        // write your code here
        this.isParked = false;
        this.plateNumber = null;
        this.parkingLotID = null;
        this.parkingHour = parkingHour;

        return 1;
    }
}
