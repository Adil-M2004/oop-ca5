package com.dkit.oop.sd2.DAOs;


import com.dkit.oop.sd2.DTOs.Car;
import com.dkit.oop.sd2.Exceptions.DaoException;
import org.json.JSONObject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlCarDao extends MySqlDao implements CarDaoInterface {

    /**
     * Will access and return a List of all users in User database table
     * @return List of User objects
     * @throws DaoException
     */
    @Override
    public List<Car> findAllCars() throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Car> carsList = new ArrayList<>();

        try {
            //Get connection object using the getConnection() method inherited
            // from the super class (MySqlDao.java)
            connection = this.getConnection();

            String query = "SELECT * FROM CARS";
            preparedStatement = connection.prepareStatement(query);

            //Using a PreparedStatement to execute SQL...
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int Id = resultSet.getInt(1);
                String make = resultSet.getString(2);
                String model = resultSet.getString(3);
                int year = resultSet.getInt(4);
                int price = resultSet.getInt(5);
                Car c = new Car(Id, make, model, year, price);
                carsList.add(c);
            }
        } catch (SQLException e) {
            throw new DaoException("findAllCars() " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    freeConnection(connection);
                }
            } catch (SQLException e) {
                throw new DaoException("findAllCars() " + e.getMessage());
            }
        }
        return carsList;     // may be empty
    }

    @Override
    public Car findCarById(int id) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Car car = null;

        try {
            //Get connection object using the getConnection() method inherited
            // from the super class (MySqlDao.java)
            connection = this.getConnection();

            String query = "SELECT * FROM CARS WHERE car_id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            //Using a PreparedStatement to execute SQL...
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int Id = resultSet.getInt(1);
                String make = resultSet.getString(2);
                String model = resultSet.getString(3);
                int year = resultSet.getInt(4);
                int price = resultSet.getInt(5);
                car = new Car(Id, make, model, year, price);
            }
        } catch (SQLException e) {
            throw new DaoException("findCarById() " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    freeConnection(connection);
                }
            } catch (SQLException e) {
                throw new DaoException("findCarById() " + e.getMessage());
            }
        }
        return car;
    }


    @Override
    public int DeleteCarByKey(int id) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int rowsDeleted = 0;

        try {
            connection = this.getConnection();

            String query = "DELETE FROM cars WHERE car_id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            // Execute the delete statement
            rowsDeleted = preparedStatement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Car with car ID = " + id + " was deleted successfully.");
            } else {
                System.out.println("No car found with car ID = " + id);
            }

        } catch (SQLException e) {
            throw new DaoException("DeleteCarByKey() " + e.getMessage());
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    freeConnection(connection);
                }
            } catch (SQLException e) {
                throw new DaoException("DeleteCarByKey() " + e.getMessage());
            }
        }
        return rowsDeleted;
    }

    @Override
    public int InsertCar(Car car) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int rowsInserted = 0;

        try {
            connection = this.getConnection();
            String query = "INSERT INTO cars VALUES(?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);

            // Initializing/Setting '?' in the prepared preparedStatement
            preparedStatement.setInt(1, car.getId());
            preparedStatement.setString(2, car.getMake());
            preparedStatement.setString(3, car.getModel());
            preparedStatement.setInt(4, car.getYear());
            preparedStatement.setInt(5, car.getPrice());

            // Execute the insert statement
            rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("Car inserted successfully with ID: " + car.getId());
            } else {
                System.out.println("Failed to insert car with ID: " + car.getId());
            }

        } catch (SQLException e) {
            throw new DaoException("InsertCar() " + e.getMessage());
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    freeConnection(connection);
                }
            } catch (SQLException e) {
                throw new DaoException("InsertCar() " + e.getMessage());
            }
        }
        return rowsInserted;
    }

    @Override
    public int UpdateCar(int id, int price) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int rowsUpdated = 0;

        try {
            connection = this.getConnection();
            String query = "UPDATE cars SET price = ? WHERE car_ID = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, price);
            preparedStatement.setInt(2, id);

            // Execute the update statement
            rowsUpdated = preparedStatement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Car price updated successfully with ID: " + id + " NEW PRICE: " + price);
            } else {
                System.out.println("No car found with ID: " + id);
            }

        } catch (SQLException e) {
            throw new DaoException("UpdateCar() " + e.getMessage());
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    freeConnection(connection);
                }
            } catch (SQLException e) {
                throw new DaoException("UpdateCar() " + e.getMessage());
            }
        }
        return rowsUpdated;
    }

    @Override
    public List<Car> filterYear(int year) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Car> carsList = new ArrayList<>();

        try {
            connection = this.getConnection();

            String query = "SELECT * FROM cars WHERE modelyear <= ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, year);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int Id = resultSet.getInt(1);
                String make = resultSet.getString(2);
                String model = resultSet.getString(3);
                int year1 = resultSet.getInt(4);
                int price = resultSet.getInt(5);
                Car c = new Car(Id, make, model, year1, price);
                carsList.add(c);
            }
        } catch (SQLException e) {
            throw new DaoException("filterYear() " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    freeConnection(connection);
                }
            } catch (SQLException e) {
                throw new DaoException("filterYear() " + e.getMessage());
            }
        }
        return carsList;     // may be empty
    }

    @Override
    public List<Car> Jsonstring() throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Car> carsList = new ArrayList<>();

        try {
            connection = this.getConnection();

            String query = "SELECT * FROM cars";
            preparedStatement = connection.prepareStatement(query);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int Id = resultSet.getInt(1);
                String make = resultSet.getString(2);
                String model = resultSet.getString(3);
                int year1 = resultSet.getInt(4);
                int price = resultSet.getInt(5);
                Car c = new Car(Id, make, model, year1, price);
                carsList.add(c);

                JSONObject jsonObject = new JSONObject();

                // Adding key=>value pairs.  Keys must be strings, but values can have various types.
                jsonObject.put("Car ID", Id);
                jsonObject.put("Make", make);
                jsonObject.put("Model", model);
                jsonObject.put("Model Year", year1);
                jsonObject.put("Price", price);

                String jsonString = jsonObject.toString();

                System.out.println("JSON String is: \n " + jsonString);


            }

            //Create the JSON OBJECT

        } catch (SQLException e) {
            throw new DaoException("Jsonstring() " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    freeConnection(connection);
                }
            } catch (SQLException e) {
                throw new DaoException("Jsonstring() " + e.getMessage());
            }
        }
        return carsList;     // may be empty
    }

    @Override
    public List<Car> JsonString() throws DaoException {
        // Call the Jsonstring() method to maintain compatibility with both interfaces
        return Jsonstring(); }

    @Override
    public String jsonEntity(int id) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String jsonString = "";

        try {
            connection = this.getConnection();

            String query = "SELECT * FROM cars WHERE car_id = ?";
            preparedStatement = connection.prepareStatement(query);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int Id = resultSet.getInt(1);
                String make = resultSet.getString(2);
                String model = resultSet.getString(3);
                int year1 = resultSet.getInt(4);
                int price = resultSet.getInt(5);
                Car c = new Car(Id, make, model, year1, price);


                JSONObject jsonObject = new JSONObject();

                // Adding key=>value pairs.  Keys must be strings, but values can have various types.
                jsonObject.put("Car ID", Id);
                jsonObject.put("Make", make);
                jsonObject.put("Model", model);
                jsonObject.put("Model Year", year1);
                jsonObject.put("Price", price);

                jsonString = jsonObject.toString();

                System.out.println("JSON String is: \n " + jsonString);


            }

            //Create the JSON OBJECT

        } catch (SQLException e) {
            throw new DaoException("Jsonstring() " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    freeConnection(connection);
                }
            } catch (SQLException e) {
                throw new DaoException("Jsonstring() " + e.getMessage());
            }
        }
        return jsonString;
    }
    @Override
    public String jsonEntity() throws DaoException {
        // Call the Jsonstring() method to maintain compatibility with both interfaces
        return jsonEntity(); }
}