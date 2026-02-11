package com.ediae.netflix;

import com.ediae.netflix.utils.DBManager;


public class Main {

    public static void main(String[] args) {

        System.out.println("Hello World!");

        System.out.println("Database connection test:");
        DBManager dbManager = new DBManager();
        if (dbManager.getConnection() != null) {
            System.out.println("Successful connection to the database!");
        } else {
            System.out.println("Error connecting to the database.");
        }

        System.out.println("---------------------------------------------");

        System.out.println("Welcome to the Netflix project! Here you can efficiently manage your catalog of movies, series, and users. Enjoy exploring and creating content for your streaming platform!");

        System.out.println("---------------------------------------------");
    }
}
