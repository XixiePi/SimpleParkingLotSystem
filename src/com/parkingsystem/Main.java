package com.parkingsystem;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        // This is the main file of the system.
        System.out.println("== Welcome to the Parking Lot System ==");
        System.out.println("Press 1 -> Admin");
        System.out.println("Press 2 -> Operator");
        System.out.println("Press 3 -> Customer ");
        System.out.println("Press 0 -> Quite ");

        System.out.println("Please Choose The Console");

        char i = (char) System.in.read();

        while(i != '0'){
            if(i == '1'){
                Admin admin = new Admin();
                admin.showMessages();
            }
            if(i == '2'){
                OperatorCons operatorcons = new OperatorCons();
                operatorcons.showMessages();
            }
            if(i == '3'){
                System.out.println("Console Not Available.");
            }
        }


    }
}


