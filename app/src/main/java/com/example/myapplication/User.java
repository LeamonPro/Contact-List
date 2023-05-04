package com.example.myapplication;

public class User {
    private int id;



    private String username;





    private String phonenumber;

    public User(int id, String username, String phonenumber) {
        this.id = id;
        this.username=username;
        this.phonenumber=phonenumber;
    }


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

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
