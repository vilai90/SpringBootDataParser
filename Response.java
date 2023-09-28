package com.example.springboot;

public class Response {

    private int page;
    private int per_page;
    private int total;
    private int total_pages;
    private User[] data;
    private  class Support{
        String url;
        String text;
    }

    public User[] getData(){
        return data;
    }

    public int getPerPage(){
        return per_page;
    }

}
