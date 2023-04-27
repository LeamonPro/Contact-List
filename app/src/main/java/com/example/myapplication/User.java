package com.example.myapplication;

public class User {
    private String username;
    private String phonenumber;

    public User(String username, String phonenumber) {
        this.username = username;
        this.phonenumber = phonenumber;
    }

    public String getUsername() {
        return username;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }
}
