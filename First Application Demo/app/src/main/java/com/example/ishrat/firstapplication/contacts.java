package com.example.ishrat.firstapplication;

/**
 * Created by Ishrat on 7/20/2016.
 */
public class contacts {

    int id;
    String name;
    String phoneNumber;

    // Empty constructor
    public contacts(){

    }
    // constructor
    public contacts(int id, String name, String phoneNumber){
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    // constructor
    public contacts(String name, String phoneNumber){
        this.name = name;
        this.phoneNumber = phoneNumber;
    }
    // getting ID
    public int getID(){
        return this.id;
    }

    // setting id
    public void setID(int id){
        this.id = id;
    }

    // getting name
    public String getName(){
        return this.name;
    }

    // setting name
    public void setName(String name){
        this.name = name;
    }

    // getting phone number
    public String getPhoneNumber(){
        return this.phoneNumber;
    }

    // setting phone number
    public void setPhoneNumber(String phone_number){
        this.phoneNumber = phone_number;
    }

}
