package com.parkingsystem;

/*
Note: The class "admin" for the Admin Console

the admin (only one admin) accesses this portal without password (for simplicity) and can do the following:
a.	Add/Delete operators: each operator has a user name and password, and list of parking lot IDs the operator has access to.
b.	Add/Delete parking lot: Each parking lot have a number of parking spaces and per hour rate.
c.	List all available operators with their usernames/pass/parking lot IDs.
d.	List all parking lot IDs/number of parking spaces/per hour rate.
e.	Save/Load operators and parking lots information to the file system.
*/

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;


public class Admin {

    //Attributes
    public String name = "Admin";
    public String operatorFileLocaion = "src/com/parkingsystem/operatorsList.txt";
    public String parkinglotFileLocaion = "src/com/parkingsystem/parkinglotList.txt";

    public Map<String, Operator> operatorMap;
    public Map<String, ParkingLot> parkingLotMap;

    public Admin() {
        // TODO: Change to read from database
        operatorMap = loadOperatorFile(operatorFileLocaion);
        parkingLotMap = loadParkingLotFile(parkinglotFileLocaion);
    }

    // Show the messages for Admin Account
    public void showMessages() throws IOException {

        System.out.println("== Welcome to the Parking Lot System : Admin==");
        System.out.println("Press 1 -> List all available operators infos ");
        System.out.println("Press 2 -> Add new Operator");
        System.out.println("Press 3 -> Delete Operator");
        System.out.println("Press 4 -> List all available parking lot infos");
        System.out.println("Press 0 -> Quite");

        Scanner sc = new Scanner(System.in);
        int i = sc.nextInt();

        while (i != 0){

            if(i == 1){
                getAllOperatorInfos();
            }
            if(i == 2){
                AddOperator();
            }
            if(i == 3){
                DeleteOperator();
            }
            if(i == 4){
                getAllParkingLotInfos();
            }


            System.out.println("== Welcome to the Parking Lot System : Admin==");
            System.out.println("Press 1 -> List all available operators infos ");
            System.out.println("Press 2 -> Add new Operator");
            System.out.println("Press 3 -> Delete Operator");
            System.out.println("Press 4 -> List all available parking lot infos");
            System.out.println("Press 0 -> Quite");

            sc = new Scanner(System.in);
            i = sc.nextInt();
        }
    }

    public void getAllOperatorInfos(){
        System.out.println("== The Operator Infos ==");
        System.out.println("ID\t UserName\t ParkingLots ID");
        for (String key : this.operatorMap.keySet()) {

            Operator temp_op = this.operatorMap.get(key);

            String[] temp_PlId = temp_op.parkingLotID;
            String ParkingLotIDs = " ";
            for(int i= 0; i<temp_op.parkingLotNum; i++){
                ParkingLotIDs = ParkingLotIDs + temp_PlId[i] + " ; " ;
            }

            System.out.println(temp_op.id + "\t " +
                               temp_op.userName + "\t\t " +
                               ParkingLotIDs
                    );
        }

        System.out.println("Press 1 -> Continue");
        Scanner sc = new Scanner(System.in);
        int i = sc.nextInt();
    }

    public void AddOperator(){
        System.out.println("== Add Operator ==");

        System.out.println("Please input the ID:");
        Scanner sc = new Scanner(System.in);
        String temp_id = sc.nextLine();

        System.out.println("Please input the Name:");
        sc = new Scanner(System.in);
        String temp_name = sc.nextLine();

        System.out.println("Please input the Password:");
        sc = new Scanner(System.in);
        String temp_pw = sc.nextLine();

        System.out.println("Please input the parking lots ID:");
        System.out.println("Parking lots ID should separated by \";\", as showing in the following style");
        System.out.println("Example: ID_1;ID_2;ID_3;");
        sc = new Scanner(System.in);
        String temp_plids = sc.nextLine();

        String[] temp_PlId = temp_plids.split(";");
        int temp_PlNum = temp_PlId.length;

        Operator temp_op = new Operator(temp_id, temp_name, temp_pw, temp_PlNum, temp_PlId);
        operatorMap.put(temp_id, temp_op);

        // Save the updated list to file system;
        SavetoFileSystem();
        System.out.println("Operator Info Saved.");

        System.out.println("Press 1 -> Continue");
        sc = new Scanner(System.in);
        int i = sc.nextInt();
    }


    public void DeleteOperator(){
        System.out.println("== Delete Operator ==");

        System.out.println("Please input the ID to delete:");
        Scanner sc = new Scanner(System.in);
        String temp_id = sc.nextLine();

        // TODO: Add the verification

        if( this.operatorMap.remove(temp_id)!= null ){
            System.out.println("ID has been deleted.");
            // Save the updated list to file system;
            SavetoFileSystem();
        }else{
            System.out.println("No such ID exist, please verify.");
        }

        System.out.println("Press 1 -> Continue");
        sc = new Scanner(System.in);
        int i = sc.nextInt();

    }

    public void getAllParkingLotInfos(){
        System.out.println("== The ParkingLot Infos ==");
        System.out.println("ID\t Rare\t Space");
        for (String key : this.parkingLotMap.keySet()) {

            ParkingLot temp_pl = this.parkingLotMap.get(key);

            System.out.println(temp_pl.id + "\t " +
                    temp_pl.rate + "\t\t " +
                    temp_pl.space
            );
        }

        System.out.println("Press 1 -> Continue");
        Scanner sc = new Scanner(System.in);
        int i = sc.nextInt();
    }

    // Help functions
    // From the file system to load the operatior infos
    public Map<String, Operator> loadOperatorFile(String operatorFileLocaion) {
        Map<String, Operator> operatorMap = new HashMap<String, Operator>();

        File file = new File(operatorFileLocaion);
        BufferedReader reader = null;
        String temp = null;
        int line = 1;
        try {
            reader = new BufferedReader(new FileReader(file));
            while ((temp = reader.readLine()) != null) {

                line++;
                String[] tokens = temp.split(" ");

                // Get the infos for an operator
                String temp_id = tokens[0];
                String temp_name = tokens[1];
                String temp_pw = tokens[2];
                int temp_PlNum = Integer.parseInt(tokens[3]);

                // ToDo: should change array type to ArrayList
                String[] temp_PlId = new String[10];
                for(int i = 0; i < temp_PlNum; i++){
                    temp_PlId[i] = tokens[4 + i];
                }

                Operator temp_op = new Operator(temp_id, temp_name, temp_pw, temp_PlNum, temp_PlId);
                operatorMap.put(temp_id, temp_op);

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return operatorMap;
    }

    public Map<String, ParkingLot> loadParkingLotFile(String operatorFileLocaion) {
        Map<String, ParkingLot> parkingLotMap = new HashMap<String, ParkingLot>();

        File file = new File(parkinglotFileLocaion);
        BufferedReader reader = null;
        String temp = null;
        int line = 1;
        try {
            reader = new BufferedReader(new FileReader(file));
            while ((temp = reader.readLine()) != null) {

                line++;
                String[] tokens = temp.split(" ");

                // Get the infos for an operator
                String temp_id = tokens[0];
                int temp_rate = Integer.parseInt(tokens[1]);
                int temp_space = Integer.parseInt(tokens[2]);
                int temp_spaceFree = Integer.parseInt(tokens[3]);
                ParkingLot temp_pl = new ParkingLot(temp_id, temp_rate, temp_space, temp_spaceFree);
                parkingLotMap.put(temp_id, temp_pl);

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return parkingLotMap;
    }

    public void SavetoFileSystem(){
        File file = new File("src/com/parkingsystem/operatorsList.txt");

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));

            for (String key : this.operatorMap.keySet()) {

                Operator temp_op = this.operatorMap.get(key);

                String[] temp_PlId = temp_op.parkingLotID;
                String ParkingLotIDs = " ";
                for(int i= 0; i<temp_op.parkingLotNum; i++){
                    ParkingLotIDs = ParkingLotIDs + temp_PlId[i] + " " ;
                }

                String data2File = temp_op.id + " " +
                                   temp_op.userName + " " +
                                   temp_op.password + " " +
                                   temp_op.parkingLotNum +
                                   ParkingLotIDs + "\n";
                writer.write(data2File);
            }

            writer.close();
        }catch (IOException e) {
            e.printStackTrace();
        }

    }




}
