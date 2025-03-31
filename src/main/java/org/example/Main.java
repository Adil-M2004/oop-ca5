package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.json.JSONObject;

public class Main {
    public static void main(String[] args)
    {
        Main Main = new Main();
       // Car myCar = new Car("Toyota", "Red", 2020, 21000);

        Scanner keyboard = new Scanner(System.in);
        System.out.println("Input Feature Option (Milstone 1: 1-6)\n " +
                "1 – Get all Entities\n" +
                "2 - Find and display car by key\n"+
                        "3 – Delete an Entity by key\n" +
                        "4 - Insert an Entity\n"+
                        "5 – Update an existing Entity by ID\n" +
                        "6 - Get list of entities matching a filter\n"+
                        "7 - Convert List of Entities to a JSON String"
                );
        int option = keyboard.nextInt();


            if(option==1) {
                Main.feature1();
                menu();
                } else if(option==2) {
                    Main.feature2(2);
                menu();
                } else if(option==3) {
                    Main.feature3(3);
                menu();
                } else if(option==4) {
                    Main.feature4("Ferrari","F8 Spyder",2020, 315000);
                menu();
                } else if(option==5) {
                    //Main.feature5(4, 45000);
                System.out.println("Input ID");
                int id = keyboard.nextInt();
                System.out.println("Input New Price");
                int price = keyboard.nextInt();
                Main.feature5(id, price);
                menu();
                } else if(option==6) {
                    Main.feature6(2020);
                    menu();            //MILESTONE 1 END
                } else if(option==7) { //MILSTONE 2 START
                    Main.feature7();
                    menu();
               }



        //Main.feature1();
       // Main.feature2(2);
//        Main.feature3(3);
//        Main.feature4("Ferrari","F8 Spyder",2020, 315000);
       //   Main.feature5(3, 14000);
       // Main.feature6(2020);
    }

    public static void menu() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Input Feature Option (Milstone 1: 1-6)\n" +
                "1 – Get all Entities\n" +
                "2 - Find and display car by key\n"+
                "3 – Delete an Entity by key\n" +
                "4 - Insert an Entity\n"+
                "5 – Update an existing Entity by ID\n" +
                "6 - Get list of entities matching a filter"
        );
        int option = keyboard.nextInt();

        if(option==1) {
            Main.feature1();
        } else if(option==2) {
            Main.feature2(2);
        } else if(option==3) {
            Main.feature3(3);
        } else if(option==4) {
            Main.feature4("Ferrari","F8 Spyder",2020, 315000);
        } else if(option==5) {
            Main.feature5(4, 45000);
        } else if(option==6) {
            Main.feature6(2020);
        } else if(option==7) {
            Main.feature7();
        }
    }

    //TODO FEATURE 1
    public static void feature1() {
        System.out.println("Feature 1 – Get all Entities");

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



//            ArrayList<String> carslist = new ArrayList<String>();

            // Iterate over the resultSet to process every row
            while (resultSet.next()) {
                // Columns can be identified by column name OR by number
                // The first column is number 1   e.g. resultSet.getString(1);


                int car_ID = resultSet.getInt(1);

                String make = resultSet.getString(2);

                String model = resultSet.getString(3);  // get third value using index, i.e lastName
                int modelyear = resultSet.getInt(4);

                int price = resultSet.getInt(5);


                System.out.print("Car ID = " + car_ID + ", ");
                System.out.print("Make = " + make + ", ");
                System.out.print("Model = " + model + ", ");
                System.out.println("Model Year : " + modelyear);
                System.out.println("Price: " + price);


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
        System.out.println("Feature 2 – Find and Display (a single) Entity by Key");

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


            // Iterate over the resultSet to process every row
            while (resultSet.next()) {
                // Columns can be identified by column name OR by number
                // The first column is number 1   e.g. resultSet.getString(1);

                int car_ID = resultSet.getInt(1);


                String make = resultSet.getString(2);

                String model = resultSet.getString(3);  // get third value using index, i.e lastName
                int modelyear = resultSet.getInt(4);

                int price = resultSet.getInt(5);


                System.out.print("Car ID = " + car_ID + ", ");
                System.out.print("Make = " + make + ", ");
                System.out.print("Model = " + model + ", ");
                System.out.println("Model Year : " + modelyear);
                System.out.println("Price: " + price);
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
        System.out.println("Feature 3 – Delete an Entity by key ");
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

    // Feature 4
    public static void feature4(String carID,String model,int year, int price)
    {
        System.out.println("Insert an Entity");
        String url = "jdbc:mysql://localhost/";
        String dbName = "ca5";
        String userName = "root";
        String password = "";

        String sqlInsert = "INSERT INTO cars (make, model, modelYear, price) VALUES ('" + carID + "', '" + model + "', " + year + "', " + price +")";

        try (Connection conn = DriverManager.getConnection(url + dbName, userName, password);
             Statement statement = conn.createStatement())
        {
            System.out.println("\nConnected to the database.");

            int rowsInserted = statement.executeUpdate(sqlInsert);

            if (rowsInserted > 0) {
                System.out.println("Car added successfully: " + carID + " " + model + " "   + year + " " + price + " ");
            } else {
                System.out.println("Failed to add car: " + carID + " " + model + " " + year + " " + price+ " ");
            }

            System.out.println("\nFinished - Disconnected from database");

        }
        catch (SQLException ex)
        {
            System.out.println("SQL Failed - check MySQL Server is running and that you are using the correct database details");
            ex.printStackTrace();
        }
    }

    //TODO FEUATURE5 *******************************************************************************
    public static void feature5(int carID, int price) {

        System.out.println("Feature 5 – Update an existing Entity by ID using supplied Player object ");
    String url = "jdbc:mysql://localhost/";
    String dbName = "ca5";
    String userName = "root";
    String password = "";

    String update = "UPDATE cars\n" +
            "SET price = " + price + " WHERE car_ID =" + carID;

    try (Connection conn = DriverManager.getConnection(url + dbName, userName, password);
    Statement statement = conn.createStatement())
    {
        System.out.println("\nConnected to the database.");

        int rowsInserted = statement.executeUpdate(update);

        if (rowsInserted > 0) {
            System.out.println("Car Price updates successfully with ID: " + carID + " NEW PRICE: " + price + " ");
        } else {
            System.out.println("Failed to add car: " + carID + " " + price + " ");
        }


        System.out.println("\nFinished - Disconnected from database");

    }
    catch (SQLException ex)
    {
        System.out.println("SQL Failed - check MySQL Server is running and that you are using the correct database details");
        ex.printStackTrace();
    }
}

    //TODO FEATURE 6
    public static void feature6(int filteryear) {
        System.out.println("Feature 6- Filter cars by Year (Cars Before seleced Year)");

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
            String sqlQuery = "select * from cars WHERE modelyear <= " + filteryear;
            ResultSet resultSet = statement.executeQuery(sqlQuery);


            // Iterate over the resultSet to process every row
            while (resultSet.next()) {
                // Columns can be identified by column name OR by number
                // The first column is number 1   e.g. resultSet.getString(1);

                int car_ID = resultSet.getInt(1);


                String make = resultSet.getString(2);

                String model = resultSet.getString(3);  // get third value using index, i.e lastName
                int modelyear = resultSet.getInt(4);

                int price = resultSet.getInt(5);


                System.out.print("Car ID = " + car_ID + ", ");
                System.out.print("Make = " + make + ", ");
                System.out.print("Model = " + model + ", ");
                System.out.println("Model Year : " + modelyear);
                System.out.println("Price: " + price);


            }
            System.out.println("\nSpecified Max model year cars displayed");
            System.out.println("\nFinished - Disconnected from database");
        } catch (SQLException ex) {
            System.out.println("SQL Failed - check MySQL Server is running and that you are using the correct database details");
            ex.printStackTrace();
        }
    }

    public static void feature7() {
        System.out.println("Feature 7 - Convert List of Entities to a JSON String ");

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



//            ArrayList<String> carslist = new ArrayList<String>();

            // Iterate over the resultSet to process every row
            while (resultSet.next()) {
                // Columns can be identified by column name OR by number
                // The first column is number 1   e.g. resultSet.getString(1);


                int car_ID = resultSet.getInt(1);

                String make = resultSet.getString(2);

                String model = resultSet.getString(3);  // get third value using index, i.e lastName
                int modelyear = resultSet.getInt(4);

                int price = resultSet.getInt(5);

                //Create the JSON OBJECT
                JSONObject jsonObject = new JSONObject();

                // Adding key=>value pairs.  Keys must be strings, but values can have various types.
                jsonObject.put("Car ID", car_ID);
                jsonObject.put("Make", make);
                jsonObject.put("Model", model);
                jsonObject.put("Model Year", modelyear);
                jsonObject.put("Price", price);

                String jsonString = jsonObject.toString();

                System.out.println("JSON String is: \n " + jsonString);


//                System.out.print("Car ID = " + car_ID + ", ");
//                System.out.print("Make = " + make + ", ");
//                System.out.print("Model = " + model + ", ");
//                System.out.println("Model Year : " + modelyear);
//                System.out.println("Price: " + price);


            }
            System.out.println("\n*********All Cars displayed in JSON FORMAT******");
            System.out.println("\nFinished - Disconnected from database");
        } catch (SQLException ex) {
            System.out.println("SQL Failed - check MySQL Server is running and that you are using the correct database details");
            ex.printStackTrace();
        }


    }



//     while (resultSet.next()) {
//        // Columns can be identified by column name OR by number
//        // The first column is number 1   e.g. resultSet.getString(1);
//
//        int car_ID = resultSet.getInt(1);
//
//
//        String make = resultSet.getString(2);
//
//        String model = resultSet.getString(3);  // get third value using index, i.e lastName
//        int modelyear = resultSet.getInt(4);
//
//        System.out.print("Income ID = " + car_ID + ", ");
//        System.out.print("Title = " + make + ", ");
//        System.out.print("Amount = " + model + ", ");
//        System.out.println("Date Earned : " + modelyear);
//
//




}//LB