package com.parkingsystem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class OperatorCons {
    public String operatorFileLocaion = "src/com/parkingsystem/operatorsList.txt";
    public  Map<String, Operator> operatorMap;

    public OperatorCons(){
        operatorMap = loadOperatorFile(operatorFileLocaion);
    }

    public void showMessages(){
        System.out.println("== Welcome to the Parking Lot System : Operator==");

        System.out.println("Please input your ID:");
        Scanner sc = new Scanner(System.in);
        String id = sc.nextLine();

        System.out.println("Please input your password:");
        sc = new Scanner(System.in);
        String pw = sc.nextLine();

        while (this.operatorMap.get(id) == null || !(this.operatorMap.get(id).password.equals(pw))){
            System.out.println("Please Varify the ID and Password");
            System.out.println("Please input your ID:");
            sc = new Scanner(System.in);
            id = sc.nextLine();

            System.out.println("Please input your password:");
            sc = new Scanner(System.in);
            pw = sc.nextLine();
        }


        System.out.println("Press 1 -> parking lot infos:");
        System.out.println("Press 2 -> total revenue");
        System.out.println("Press 3 -> find a car");
        System.out.println("Press 0 -> Quite");

        sc = new Scanner(System.in);
        int i = sc.nextInt();

        while (i != 0){

            if(i == 1){
                System.out.println("Function Not Available.");
            }
            if(i == 2){
                System.out.println("Function Not Available.");
            }
            if(i == 3){
                System.out.println("Function Not Available.");
            }


            System.out.println("Press 1 -> parking lot infos:");
            System.out.println("Press 2 -> total revenue");
            System.out.println("Press 3 -> find a car");
            System.out.println("Press 0 -> Quite");

            sc = new Scanner(System.in);
            i = sc.nextInt();
        }
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
}
