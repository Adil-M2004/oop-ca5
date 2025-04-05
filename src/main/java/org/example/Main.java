package org.example;

import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.Scanner;
import org.json.JSONObject;

public class Main
{
    private static final String DB_URL = "jdbc:mysql://localhost:3306/ca5";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);

        while(true) {
            System.out.println("\n=== Car Database Menu ===");
            System.out.println("1. Get all Entities");
            System.out.println("2. Find car by ID");
            System.out.println("3. Delete car by ID");
            System.out.println("4. Insert new car");
            System.out.println("5. Update car year");
            System.out.println("6. Filter cars by year");
            System.out.println("7. Convert all cars to JSON");
            System.out.println("8. Convert single car to JSON");
            System.out.println("9. Display car by ID (Client-Server)");
            System.out.println("10. Display all cars (Client-Server)");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");

            int option = keyboard.nextInt();

            switch(option) {
                case 1:
                    try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                         Statement stmt = conn.createStatement();
                         ResultSet rs = stmt.executeQuery("SELECT * FROM cars")) {

                        System.out.println("\nAll Cars:");
                        while (rs.next()) {
                            System.out.printf("ID: %d, Make: %s, Model: %s, Year: %d\n",
                                    rs.getInt("car_ID"), rs.getString("make"),
                                    rs.getString("model"), rs.getInt("modelyear"));
                        }
                    } catch (SQLException ex) {
                        System.out.println("Database error: " + ex.getMessage());
                    }
                    break;

                case 2:
                    System.out.print("Enter Car ID: ");
                    int searchId = keyboard.nextInt();
                    try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                         Statement stmt = conn.createStatement();
                         ResultSet rs = stmt.executeQuery("SELECT * FROM cars WHERE car_ID = " + searchId)) {

                        if (rs.next()) {
                            System.out.printf("\nCar Found:\nID: %d, Make: %s, Model: %s, Year: %d\n",
                                    rs.getInt("car_ID"), rs.getString("make"),
                                    rs.getString("model"), rs.getInt("modelyear"));
                        } else {
                            System.out.println("Car not found");
                        }
                    } catch (SQLException ex) {
                        System.out.println("Database error: " + ex.getMessage());
                    }
                    break;

                case 3:
                    System.out.print("Enter Car ID to delete: ");
                    int deleteId = keyboard.nextInt();
                    try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                         Statement stmt = conn.createStatement()) {

                        int rows = stmt.executeUpdate("DELETE FROM cars WHERE car_ID = " + deleteId);
                        System.out.println(rows > 0 ? "Car deleted" : "Car not found");
                    } catch (SQLException ex) {
                        System.out.println("Database error: " + ex.getMessage());
                    }
                    break;

                case 4:
                    System.out.print("Enter Make Model Year (space separated): ");
                    String make = keyboard.next();
                    String model = keyboard.next();
                    int year = keyboard.nextInt();

                    try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                         Statement stmt = conn.createStatement()) {

                        String sql = String.format("INSERT INTO cars (make, model, modelyear) VALUES ('%s', '%s', %d)",
                                make, model, year);
                        int rows = stmt.executeUpdate(sql);
                        System.out.println(rows > 0 ? "Car added" : "Failed to add");
                    } catch (SQLException ex) {
                        System.out.println("Database error: " + ex.getMessage());
                    }
                    break;

                case 5:
                    System.out.print("Enter Car ID and New Year: ");
                    int updateId = keyboard.nextInt();
                    int newYear = keyboard.nextInt();

                    try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                         Statement stmt = conn.createStatement()) {

                        int rows = stmt.executeUpdate("UPDATE cars SET modelyear = " + newYear + " WHERE car_ID = " + updateId);
                        System.out.println(rows > 0 ? "Year updated" : "Car not found");
                    } catch (SQLException ex) {
                        System.out.println("Database error: " + ex.getMessage());
                    }
                    break;

                case 6:
                    System.out.print("Enter maximum year: ");
                    int maxYear = keyboard.nextInt();

                    try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                         Statement stmt = conn.createStatement();
                         ResultSet rs = stmt.executeQuery("SELECT * FROM cars WHERE modelyear <= " + maxYear)) {

                        System.out.println("\nFiltered Cars:");
                        while (rs.next()) {
                            System.out.printf("ID: %d, Make: %s, Model: %s, Year: %d\n",
                                    rs.getInt("car_ID"), rs.getString("make"),
                                    rs.getString("model"), rs.getInt("modelyear"));
                        }
                    } catch (SQLException ex) {
                        System.out.println("Database error: " + ex.getMessage());
                    }
                    break;

                case 7:
                    try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                         Statement stmt = conn.createStatement();
                         ResultSet rs = stmt.executeQuery("SELECT * FROM cars")) {

                        System.out.println("\nAll Cars as JSON:");
                        while (rs.next()) {
                            JSONObject car = new JSONObject();
                            car.put("car_ID", rs.getInt("car_ID"));
                            car.put("make", rs.getString("make"));
                            car.put("model", rs.getString("model"));
                            car.put("modelyear", rs.getInt("modelyear"));
                            System.out.println(car.toString());
                        }
                    } catch (SQLException ex) {
                        System.out.println("Database error: " + ex.getMessage());
                    }
                    break;

                case 8:
                    System.out.print("Enter Car ID: ");
                    int jsonId = keyboard.nextInt();

                    try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                         Statement stmt = conn.createStatement();
                         ResultSet rs = stmt.executeQuery("SELECT * FROM cars WHERE car_ID = " + jsonId)) {

                        if (rs.next()) {
                            JSONObject car = new JSONObject();
                            car.put("car_ID", rs.getInt("car_ID"));
                            car.put("make", rs.getString("make"));
                            car.put("model", rs.getString("model"));
                            car.put("modelyear", rs.getInt("modelyear"));
                            System.out.println("\nCar as JSON:\n" + car.toString());
                        } else {
                            System.out.println("Car not found");
                        }
                    } catch (SQLException ex) {
                        System.out.println("Database error: " + ex.getMessage());
                    }
                    break;

                case 9:
                    System.out.print("Enter Car ID: ");
                    int serverId = keyboard.nextInt();

                    try (Socket socket = new Socket("localhost", 49001);
                         PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                         BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

                        out.println("GET_CAR_BY_ID " + serverId);
                        String response = in.readLine();

                        if (!response.startsWith("ERROR")) {
                            String[] parts = response.split(",");
                            System.out.println("\nCar Details from Server:");
                            System.out.println("ID: " + parts[0]);
                            System.out.println("Make: " + parts[1]);
                            System.out.println("Model: " + parts[2]);
                            System.out.println("Year: " + parts[3]);
                        } else {
                            System.out.println(response);
                        }
                    } catch (IOException e) {
                        System.out.println("Server error: " + e.getMessage());
                    }
                    break;

                case 10:
                    try (Socket socket = new Socket("localhost", 49001);
                         PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                         BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

                        out.println("GET_ALL_CARS");
                        System.out.println("\nAll Cars from Server:");
                        System.out.println("ID | Make       | Model       | Year");
                        System.out.println("----------------------------------");

                        String response;
                        while (!(response = in.readLine()).equals("END")) {
                            String[] parts = response.split(",");
                            if (parts.length == 4) {
                                System.out.printf("%-3s| %-10s | %-10s | %-4s\n",
                                        parts[0], parts[1], parts[2], parts[3]);
                            }
                        }
                    } catch (IOException e) {
                        System.out.println("Server error: " + e.getMessage());
                    }
                    break;

                case 0:
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid option");
            }
        }
    }
}