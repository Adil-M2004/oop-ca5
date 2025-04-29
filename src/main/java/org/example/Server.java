package org.example;

import com.dkit.oop.sd2.DAOs.CarDaoInterface;
import com.dkit.oop.sd2.DAOs.MySqlCarDao;
import com.dkit.oop.sd2.DTOs.Car;
import com.dkit.oop.sd2.Exceptions.DaoException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class Server {
    private static final int PORT = 8888;
    private static CarDaoInterface carDao = new MySqlCarDao();

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Car Server is running on port " + PORT);

            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

                    System.out.println("New client connected: " + clientSocket.getInetAddress());
                    handleClientRequest(in, out);

                } catch (IOException e) {
                    System.err.println("Error handling client connection: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Could not start server on port " + PORT + ": " + e.getMessage());
        }
    }

    private static void handleClientRequest(BufferedReader in, PrintWriter out) throws IOException {
        String request;
        while ((request = in.readLine()) != null) {
            System.out.println("Received request: " + request);
            String[] parts = request.split("\\|");
            String command = parts[0];

            try {
                switch (command) {
                    case "GET_ALL_CARS":
                        handleGetAllCars(out);
                        break;
                    case "FIND_CAR_BY_ID":
                        handleFindCarById(parts[1], out);
                        break;
                    case "DELETE_CAR_BY_ID":
                        handleDeleteCarById(parts[1], out);
                        break;
                    case "INSERT_CAR":
                        handleInsertCar(parts, out);
                        break;
                    case "UPDATE_CAR":
                        handleUpdateCar(parts[1], parts[2], out);
                        break;
                    case "FILTER_CARS_BY_YEAR":
                        handleFilterCarsByYear(parts[1], out);
                        break;
                    case "GET_ALL_CARS_JSON":
                        handleGetAllCarsJson(out);
                        break;
                    case "GET_CAR_BY_ID_JSON":
                        handleGetCarByIdJson(parts[1], out);
                        break;
                    case "DISPLAY_CAR_BY_ID":
                        handleDisplayCarById(parts[1], out);
                        break;
                    default:
                        out.println("ERROR|Unknown command");
                }
            } catch (DaoException e) {
                out.println("ERROR|" + e.getMessage());
            } catch (ArrayIndexOutOfBoundsException e) {
                out.println("ERROR|Invalid command format");
            }
        }
    }

    private static void handleDisplayCarById(String idStr, PrintWriter out) throws DaoException {
        try {
            int id = Integer.parseInt(idStr);
            Car car = carDao.findCarById(id);

            if (car != null) {
                JSONObject jsonCar = new JSONObject();
                jsonCar.put("id", car.getId());
                jsonCar.put("make", car.getMake());
                jsonCar.put("model", car.getModel());
                jsonCar.put("year", car.getYear());
                jsonCar.put("price", car.getPrice());
                out.println(jsonCar.toString());
            } else {
                out.println("ERROR|Car not found with ID: " + id);
            }
        } catch (NumberFormatException e) {
            out.println("ERROR|Invalid car ID format");
        }
    }

    private static void handleGetAllCars(PrintWriter out) throws DaoException {
        List<Car> cars = carDao.findAllCars();
        StringBuilder response = new StringBuilder();
        for (Car car : cars) {
            response.append(car.toString()).append("\n");
        }
        out.println(response.toString().trim());
    }

    private static void handleFindCarById(String idStr, PrintWriter out) throws DaoException {
        try {
            int id = Integer.parseInt(idStr);
            Car car = carDao.findCarById(id);
            out.println(car != null ? car.toString() : "Car not found with ID: " + id);
        } catch (NumberFormatException e) {
            out.println("ERROR|Invalid car ID format");
        }
    }

    private static void handleDeleteCarById(String idStr, PrintWriter out) throws DaoException {
        try {
            int id = Integer.parseInt(idStr);
            int rowsDeleted = carDao.DeleteCarByKey(id);
            out.println(rowsDeleted > 0 ? "Car deleted successfully" : "No car found with ID: " + id);
        } catch (NumberFormatException e) {
            out.println("ERROR|Invalid car ID format");
        }
    }

    private static void handleInsertCar(String[] parts, PrintWriter out) throws DaoException {
        if (parts.length != 5) {
            out.println("ERROR|Invalid insert format. Expected: INSERT_CAR|make|model|year|price");
            return;
        }

        try {
            String make = parts[1];
            String model = parts[2];
            int year = Integer.parseInt(parts[3]);
            int price = Integer.parseInt(parts[4]);

            Car newCar = new Car(make, model, year, price);
            int generatedId = carDao.InsertCar(newCar);
            out.println("Car inserted successfully with ID: " + generatedId);
        } catch (NumberFormatException e) {
            out.println("ERROR|Invalid year or price format");
        }
    }

    private static void handleUpdateCar(String idStr, String priceStr, PrintWriter out) throws DaoException {
        try {
            int id = Integer.parseInt(idStr);
            int price = Integer.parseInt(priceStr);
            int rowsUpdated = carDao.UpdateCar(id, price);
            out.println(rowsUpdated > 0 ? "Car price updated successfully" : "No car found with ID: " + id);
        } catch (NumberFormatException e) {
            out.println("ERROR|Invalid ID or price format");
        }
    }

    private static void handleFilterCarsByYear(String yearStr, PrintWriter out) throws DaoException {
        try {
            int year = Integer.parseInt(yearStr);
            List<Car> cars = carDao.filterYear(year);
            StringBuilder response = new StringBuilder();
            for (Car car : cars) {
                response.append(car.toString()).append("\n");
            }
            out.println(response.toString().trim());
        } catch (NumberFormatException e) {
            out.println("ERROR|Invalid year format");
        }
    }

    private static void handleGetAllCarsJson(PrintWriter out) throws DaoException {
        List<Car> cars = carDao.findAllCars();
        JSONArray jsonArray = new JSONArray();
        for (Car car : cars) {
            JSONObject jsonCar = new JSONObject();
            jsonCar.put("id", car.getId());
            jsonCar.put("make", car.getMake());
            jsonCar.put("model", car.getModel());
            jsonCar.put("year", car.getYear());
            jsonCar.put("price", car.getPrice());
            jsonArray.put(jsonCar);
        }
        out.println(jsonArray.toString());
    }

    private static void handleGetCarByIdJson(String idStr, PrintWriter out) throws DaoException {
        try {
            int id = Integer.parseInt(idStr);
            Car car = carDao.findCarById(id);
            if (car != null) {
                JSONObject jsonCar = new JSONObject();
                jsonCar.put("id", car.getId());
                jsonCar.put("make", car.getMake());
                jsonCar.put("model", car.getModel());
                jsonCar.put("year", car.getYear());
                jsonCar.put("price", car.getPrice());
                out.println(jsonCar.toString());
            } else {
                out.println("ERROR|Car not found with ID: " + id);
            }
        } catch (NumberFormatException e) {
            out.println("ERROR|Invalid car ID format");
        }
    }
}