package com.example.loginapp;

public class Ambulance {

    private String name, contact;

    public Ambulance() {
    }

    public Ambulance(String driverName, String driverContact) {
        this.name = driverName;
        this.contact = driverContact;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
