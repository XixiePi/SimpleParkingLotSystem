package com.parkingsystem;

import java.util.Map;

public class ParkingLot {

    //Attributes
    public String id;
    public int rate;
    public int space;
    public int spaceFree;
    public int revenue;
    public Map<String, Car> carMap;
    public String carMapFileLocaion;

    //functions

    public ParkingLot(String id, int rate, int space, int spaceFree){
        this.id = id;
        this.rate = rate;
        this.space = space;
        this.spaceFree = spaceFree;
    }

    public int getTotalSlot() {
        return space;
    }

    public int getRevenue() {
        revenue = this.rate * (this.space - this.spaceFree);
        return revenue;
    }
}
