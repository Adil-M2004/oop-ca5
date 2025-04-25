package org.example;

import java.io.*;
import java.net.*;
import java.sql.*;

public class Server {
    private static final int PORT = 49001;
    private static final String DB_URL = "jdbc:mysql://localhost:3306/ca5";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Car Server started on port " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                new Thread(() -> handleClient(clientSocket)).start();
            }
        } catch (IOException e) {
            System.err.println("Server error: " + e.getMessage());
        }
    }

    private static void handleClient(Socket clientSocket) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

            String request = in.readLine();
            System.out.println("Received request: " + request);

            if (request == null) {
                out.println("ERROR: No command received");
                return;
            }

            if (request.startsWith("GET_ALL_CARS")) {
                sendAllCars(out);
            }
            else if (request.startsWith("GET_CAR_BY_ID")) {
                int carId = Integer.parseInt(request.split(" ")[1]);
                sendCarById(out, carId);
            }
            else {
                out.println("ERROR: Unknown command. Valid commands: GET_ALL_CARS, GET_CAR_BY_ID");
            }
        } catch (IOException e) {
            System.err.println("Client handling error: " + e.getMessage());
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                System.err.println("Error closing client socket: " + e.getMessage());
            }
        }
    }

    private static void sendAllCars(PrintWriter out) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT car_ID, make, model, modelyear FROM cars")) {

            while (rs.next()) {
                out.println(rs.getInt("car_ID") + "," +
                        rs.getString("make") + "," +
                        rs.getString("model") + "," +
                        rs.getInt("modelyear"));
            }
            out.println("END");
        } catch (SQLException e) {
            out.println("ERROR: Database error - " + e.getMessage());
        }
    }

    private static void sendCarById(PrintWriter out, int carId) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT car_ID, make, model, modelyear FROM cars WHERE car_ID = ?")) {

            stmt.setInt(1, carId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                out.println(rs.getInt("car_ID") + "," +
                        rs.getString("make") + "," +
                        rs.getString("model") + "," +
                        rs.getInt("modelyear"));
            } else {
                out.println("ERROR: Car not found with ID " + carId);
            }
        } catch (SQLException e) {
            out.println("ERROR: Database error - " + e.getMessage());
        }
    }
}