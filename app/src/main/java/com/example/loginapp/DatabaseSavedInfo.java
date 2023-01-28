package com.example.loginapp;

public class DatabaseSavedInfo {

    private String name,age,sex,contact;


    public DatabaseSavedInfo() {

    }


    public DatabaseSavedInfo(String name, String age, String sex, String contact) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.contact = contact;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
