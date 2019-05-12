package com.parkingsystem;

import java.util.Map;

public class Operator {

    //Attributes
    public String id;
    public String userName;
    public String password;
    public int parkingLotNum;
    public String[] parkingLotID;
    public String parkingLotFileLocaion;
    public Map<String, ParkingLot> parkingLotMap;

    public int allrevenue;
    //Functions:
    public Operator(String id, String userName, String password, int parkingLotNum, String[] parkingLotID){
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.parkingLotNum = parkingLotNum;
        this.parkingLotID = parkingLotID;
    }

    public int getAllrevenue(){
        for(String key : parkingLotMap.keySet()){
            ParkingLot parkingLot = parkingLotMap.get(key);
        }
        return 0;
    }

}
