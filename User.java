package com.example.springboot;

public class User{
    private int id;
    private String email;
    private String first_name;
    private String last_name;
    private String avatar;

    public String getFirstName(){
        return first_name;
    }

    public String getLastName(){
        return last_name;
    }

}