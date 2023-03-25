package com.sg.classDVDLibrary.ui;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class UserIOConsoleImpl implements UserIO {

    Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {

    }

    @Override
    public void print(String message) {
        System.out.println(message);

    }

    @Override
    public String readString(String prompt) {
        System.out.println(prompt);
        String userInput=scanner.nextLine();
        return userInput;
    }

    @Override
    public int readInt(String prompt) {
        System.out.println(prompt);
        int userInt=scanner.nextInt();
        return userInt;
    }


    @Override
    public int readInt(String prompt, int min, int max) {
        System.out.println(prompt);
        int userInt= scanner.nextInt();
        while (userInt>max || userInt<min){
            System.out.println("Your number does not fall within the range!");
            userInt= scanner.nextInt();
        }
        return userInt;
    }

    @Override
    public double readDouble(String prompt) {
        System.out.println(prompt);
        double userDouble=scanner.nextDouble();
        return userDouble;
    }


    @Override
    public double readDouble(String prompt, double min, double max) {
        System.out.println(prompt);
        double userdouble= scanner.nextDouble();
        while (userdouble>max || userdouble<min){
            System.out.println("Your number does not fall within the range!");
            userdouble= scanner.nextDouble();
        }
        return userdouble;
    }

    @Override
    public float readFloat(String prompt) {
        System.out.println(prompt);
        float userFloat=scanner.nextFloat();
        return userFloat;
    }


    @Override
    public float readFloat(String prompt, float min, float max) {
        System.out.println(prompt);
        float userFloat= scanner.nextFloat();
        while (userFloat>max || userFloat<min){
            System.out.println("Your number does not fall within the range!");
            userFloat= scanner.nextFloat();
        }
        return userFloat;
    }

    @Override
    public long readLong(String prompt) {
        System.out.println(prompt);
        long userLong=scanner.nextLong();
        return userLong;     }


    @Override
    public long readLong(String prompt, long min, long max) {
        System.out.println(prompt);
        long userLong= scanner.nextLong();
        while (userLong>max || userLong<min){
            System.out.println("Your number does not fall within the range!");
            userLong= scanner.nextLong();
        }
        return userLong;    }

}
