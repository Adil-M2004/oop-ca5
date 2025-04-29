package org.example;

import com.dkit.oop.sd2.DTOs.Car;
import com.dkit.oop.sd2.Exceptions.DaoException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

public class ClientHandler {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 8888;
    private static Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            System.out.println("Connected to server");

            boolean running = true;
            while (running) {
                displayMenu();
                int option = keyboard.nextInt();
                keyboard.nextLine(); // consume newline

                switch (option) {
                    case 1:
                        getAllCars(out, in);
                        break;
                    case 2:
                        findCarById(out, in);
                        break;
                    case 3:
                        deleteCarById(out, in);
                        break;
                    case 4:
                        insertCar(out, in);
                        break;
                    case 5:
                        updateCar(out, in);
                        break;
                    case 6:
                        filterCarsByYear(out, in);
                        break;
                    case 7:
                        getAllCarsJson(out, in);
                        break;
                    case 8:
                        getCarByIdJson(out, in);
                        break;
                    case 9:
                        displayCarById(out, in);
                        break;
                    case 10:
                        System.out.println("Exiting...");
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid option");
                }
            }
        } catch (IOException e) {
            System.err.println("Client error: " + e.getMessage());
        }
    }

    private static void displayMenu() {
        System.out.println("\nCar Management System");
        System.out.println("1. Get all cars");
        System.out.println("2. Find car by ID");
        System.out.println("3. Delete car by ID");
        System.out.println("4. Insert new car");
        System.out.println("5. Update car price");
        System.out.println("6. Filter cars by year");
        System.out.println("7. Get all cars (JSON)");
        System.out.println("8. Get car by ID (JSON)");
        System.out.println("9. Display car by ID");
        System.out.println("10. Exit");
        System.out.print("Enter option: ");
    }

    private static void displayCarById(PrintWriter out, BufferedReader in) throws IOException {
        System.out.print("Enter Car ID to display: ");
        int id = keyboard.nextInt();
        keyboard.nextLine(); // consume newline

        out.println("DISPLAY_CAR_BY_ID|" + id);
        String response = in.readLine();

        if (response.startsWith("{")) {
            JSONObject jsonCar = new JSONObject(response);
            System.out.println("\nCar Details:");
            System.out.println("ID: " + jsonCar.getInt("id"));
            System.out.println("Make: " + jsonCar.getString("make"));
            System.out.println("Model: " + jsonCar.getString("model"));
            System.out.println("Year: " + jsonCar.getInt("year"));
            System.out.println("Price: $" + jsonCar.getInt("price"));
        } else {
            System.out.println("Error: " + response);
        }
    }

    private static void getAllCars(PrintWriter out, BufferedReader in) throws IOException {
        out.println("GET_ALL_CARS");
        String response = in.readLine();
        System.out.println("\nAll Cars:");
        System.out.println(response);
    }

    private static void findCarById(PrintWriter out, BufferedReader in) throws IOException {
        System.out.print("Enter Car ID: ");
        int id = keyboard.nextInt();
        keyboard.nextLine(); // consume newline

        out.println("FIND_CAR_BY_ID|" + id);
        String response = in.readLine();
        System.out.println("\nCar Found:");
        System.out.println(response);
    }

    private static void deleteCarById(PrintWriter out, BufferedReader in) throws IOException {
        System.out.print("Enter Car ID to delete: ");
        int id = keyboard.nextInt();
        keyboard.nextLine(); // consume newline

        out.println("DELETE_CAR_BY_ID|" + id);
        String response = in.readLine();
        System.out.println(response);
    }

    private static void insertCar(PrintWriter out, BufferedReader in) throws IOException {
        System.out.println("Enter new car details:");
        System.out.print("Make: ");
        String make = keyboard.nextLine();
        System.out.print("Model: ");
        String model = keyboard.nextLine();
        System.out.print("Year: ");
        int year = keyboard.nextInt();
        System.out.print("Price: ");
        int price = keyboard.nextInt();
        keyboard.nextLine(); // consume newline

        out.println("INSERT_CAR|" + make + "|" + model + "|" + year + "|" + price);
        String response = in.readLine();
        System.out.println(response);
    }

    private static void updateCar(PrintWriter out, BufferedReader in) throws IOException {
        System.out.print("Enter Car ID to update: ");
        int id = keyboard.nextInt();
        System.out.print("Enter new price: ");
        int price = keyboard.nextInt();
        keyboard.nextLine(); // consume newline

        out.println("UPDATE_CAR|" + id + "|" + price);
        String response = in.readLine();
        System.out.println(response);
    }

    private static void filterCarsByYear(PrintWriter out, BufferedReader in) throws IOException {
        System.out.print("Enter maximum year: ");
        int year = keyboard.nextInt();
        keyboard.nextLine(); // consume newline

        out.println("FILTER_CARS_BY_YEAR|" + year);
        String response = in.readLine();
        System.out.println("\nFiltered Cars:");
        System.out.println(response);
    }

    private static void getAllCarsJson(PrintWriter out, BufferedReader in) throws IOException {
        out.println("GET_ALL_CARS_JSON");
        String response = in.readLine();
        System.out.println("\nAll Cars (JSON):");
        System.out.println(response);
    }

    private static void getCarByIdJson(PrintWriter out, BufferedReader in) throws IOException {
        System.out.print("Enter Car ID: ");
        int id = keyboard.nextInt();
        keyboard.nextLine(); // consume newline

        out.println("GET_CAR_BY_ID_JSON|" + id);
        String response = in.readLine();
        System.out.println("\nCar (JSON):");
        System.out.println(response);
    }
}