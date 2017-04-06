package com.hibernate.demo;

/**
 * Created by akhil on 6/4/17.
 */
public class Address {
    int streetNo;
    String location;
    String State;


    public int getStreetNo() {
        return streetNo;
    }

    public void setStreetNo(int streetNo) {
        this.streetNo = streetNo;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }
}
