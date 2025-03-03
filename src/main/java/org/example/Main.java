package org.example;

import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Car myCar = new Car("Toyota", "Red", 2020);

        Main Main = new Main();
        //Main.feature1();
        //Main.feature2(2);
        //Main.feature3(3);
        Main.feature4(myCar);

    }

    //TODO FEATURE 1
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
        System.out.println("Feature 2");

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
            String sqlQuery = "select * from cars WHERE car_id = " + carID;
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
            System.out.println("\nSpecified car displayed");
            System.out.println("\nFinished - Disconnected from database");
        } catch (SQLException ex) {
            System.out.println("SQL Failed - check MySQL Server is running and that you are using the correct database details");
            ex.printStackTrace();
        }
    }

    //TODO FEATURE 3
    public static void feature3(int carID) {
        String url = "jdbc:mysql://localhost/";
        String dbName = "ca5";
        String userName = "root";
        String password = "";

        // SQL DELETE statement
        String sqlDelete = "DELETE FROM cars WHERE car_ID = " + carID;

        // try with resources
        try (Connection conn = DriverManager.getConnection(url + dbName, userName, password);
             Statement statement = conn.createStatement()) {

            System.out.println("\nConnected to the database.");

            // Execute the delete statement
            int rowsDeleted = statement.executeUpdate(sqlDelete);

            if (rowsDeleted > 0) {
                System.out.println("car with car ID = " + carID + " was deleted successfully.");
            } else {
                System.out.println("No car found with car ID = " + carID);
            }

            System.out.println("\nFinished - Disconnected from database");
        } catch (SQLException ex) {
            System.out.println("SQL Failed - check MySQL Server is running and that you are using the correct database details");
            ex.printStackTrace();
        }
    }

    public static void feature5(int carID, Car car) {
        System.out.println("Updating Car");

        String url = "jdbc:mysql://localhost/";
        String dbName = "ca5";
        String fullURL = url + dbName;  // join together
        String userName = "root";
        String password = "";

        String make1 = car.make;
        String model1 = car.model;
        int modelyear1 = car.modelyear;


        // Prepare the Query String using "?" to indicate field parameters.
        ///
        String query1 = "INSERT INTO cars VALUES (null, ?, ?, ?)";


        // Try-with-Resources style
        try (Connection connection = DriverManager.getConnection(fullURL, userName, password);
             PreparedStatement preparedStatement1 = connection.prepareStatement( query1 );
        )
        {
            System.out.println("Connected to the database");
            System.out.println("Building a PreparedStatement to insert a new row in database.");


            preparedStatement1.setString(1, make1);
            preparedStatement1.setString(2, model1);
            preparedStatement1.setDouble(3, modelyear1);


            preparedStatement1.executeUpdate();  // will INSERT a new row



            // Statements allow us to issue SQL queries to the database
            Statement statement = connection.createStatement();

            // Execute the Prepared Statement and get a result set
            ResultSet resultSet = statement.executeQuery("select * from cars");


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
            System.out.println("Car Added");
        } catch (SQLException ex) {
            System.out.println("Failed to connect to database - check MySQL is running and that you are using the correct database details");
            ex.printStackTrace();
        }
    }

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






    }//LB