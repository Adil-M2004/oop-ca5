package org.example;

import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Main Main = new Main();
       // Main.feature1();
        Main.feature2(2);

    }

    //TODO FEATURE 2
    public static void feature1() {
        System.out.println("Get all entites");

        String url = "jdbc:mysql://localhost/";
        String dbName = "ca5";
        String userName = "root";
        String password = "";


        // try with resources
        try (Connection conn = DriverManager.getConnection(url + dbName, userName, password)) {
            System.out.println("\nConnected to the database.");


            Statement statement = conn.createStatement();

            // ResultSet stores the result from the SQL query
            String sqlQuery = "select * from cars";
            ResultSet resultSet = statement.executeQuery(sqlQuery);


            double incomeAmount = 0;
            // Iterate over the resultSet to process every row
            while (resultSet.next()) {
                // Columns can be identified by column name OR by number
                // The first column is number 1   e.g. resultSet.getString(1);

                int car_ID = resultSet.getInt(1);


                String make = resultSet.getString(2);

                String model = resultSet.getString(3);  // get third value using index, i.e lastName
                int modelyear = resultSet.getInt(4);

                System.out.print("Income ID = " + car_ID + ", ");
                System.out.print("Title = " + make + ", ");
                System.out.print("Amount = " + model + ", ");
                System.out.println("Date Earned : " + modelyear);


            }
            System.out.println("\nAll Cars displayed");
            System.out.println("\nFinished - Disconnected from database");
        } catch (SQLException ex) {
            System.out.println("SQL Failed - check MySQL Server is running and that you are using the correct database details");
            ex.printStackTrace();
        }
    }

    //TODO FEATURE 2
    public static void feature2(int carID) {
        System.out.println("Get all entites");

        String url = "jdbc:mysql://localhost/";
        String dbName = "ca5";
        String userName = "root";
        String password = "";

//        Scanner keyboard = new Scanner(System.in);
//        System.out.println("Enter Car ID");
//        int carID = keyboard.nextInt();

        // try with resources
        try (Connection conn = DriverManager.getConnection(url + dbName, userName, password)) {
            System.out.println("\nConnected to the database.");


            Statement statement = conn.createStatement();

            // ResultSet stores the result from the SQL query
            String sqlQuery = "select from cars WHERE car_id = " + carID;
            ResultSet resultSet = statement.executeQuery(sqlQuery);


            double incomeAmount = 0;
            // Iterate over the resultSet to process every row
            while (resultSet.next()) {
                // Columns can be identified by column name OR by number
                // The first column is number 1   e.g. resultSet.getString(1);

                int car_ID = resultSet.getInt(1);


                String make = resultSet.getString(2);

                String model = resultSet.getString(3);  // get third value using index, i.e lastName
                int modelyear = resultSet.getInt(4);

                System.out.print("Income ID = " + car_ID + ", ");
                System.out.print("Title = " + make + ", ");
                System.out.print("Amount = " + model + ", ");
                System.out.println("Date Earned : " + modelyear);


            }
            System.out.println("\nAll Cars displayed");
            System.out.println("\nFinished - Disconnected from database");
        } catch (SQLException ex) {
            System.out.println("SQL Failed - check MySQL Server is running and that you are using the correct database details");
            ex.printStackTrace();
        }
    }



}