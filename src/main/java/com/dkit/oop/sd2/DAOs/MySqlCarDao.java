package com.dkit.oop.sd2.DAOs;


import com.dkit.oop.sd2.DTOs.Car;
import com.dkit.oop.sd2.Exceptions.DaoException;

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
            throw new DaoException("findAllCaresultSet() " + e.getMessage());
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
                throw new DaoException("findAllUsers() " + e.getMessage());
            }
        }
        return carsList;     // may be empty
    }

    //TODO FEATURE 2
    @Override
    public Car findCarById(int id) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //List<Car> carsList = new ArrayList<>();
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
            throw new DaoException("findAllCaresultSet() " + e.getMessage());
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
                throw new DaoException("findCarByID() " + e.getMessage());
            }
        }
        return car;
    }


    @Override
    public int DeleteCarByKey(int id) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int rowsDeleted = 0;
        try {
            //Get connection object using the getConnection() method inherited
            // from the super class (MySqlDao.java)
            connection = this.getConnection();

            String query = "DELETE FROM cars WHERE car_id = " + id;
            preparedStatement = connection.prepareStatement(query);
           // preparedStatement.setInt(1, id);


            Statement statement = connection.createStatement(); {

                System.out.println("\nConnected to the database.");

                // Execute the delete statement
                rowsDeleted = statement.executeUpdate(query);

                if (rowsDeleted > 0) {
                    System.out.println("car with car ID = " + id + " was deleted successfully.");
                } else {
                    System.out.println("No car found with car ID = " + id);
                }

                System.out.println("\nFinished - Disconnected from database");
            }
        } catch (SQLException e) {
            throw new DaoException("findAllCaresultSet() " + e.getMessage());
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
                throw new DaoException("findCarByID() " + e.getMessage());
            }
        }
        return rowsDeleted;

    }

    //TODO FEATURE 4
    @Override
    public int InsertCar(Car car) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //List<Car> carsList = new ArrayList<>();
//        Car car1 = null;
//
//        String make = car.getMake();
//        String model = car.getMake();
//        int year = car.getYear();
//        int price = car.getPrice();

        int rowsInserted = 0;

        try {

            connection = this.getConnection();
            String query = "INSERT INTO cars VALUES(?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);

            preparedStatement = connection.prepareStatement(query);

            // Initializing/Setting '?' in the prepared preparedStatement
            preparedStatement.setInt(1, car.getId());
            preparedStatement.setString(2, car.getMake());
            preparedStatement.setString(3, car.getModel());
            preparedStatement.setInt(4, car.getYear());
            preparedStatement.setInt(5, car.getPrice());


        } catch (SQLException e) {
            throw new DaoException("findAllCaresultSet() " + e.getMessage());
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
                throw new DaoException("InsertCar() " + e.getMessage());
            }
        }
        return rowsInserted;
    }

    //TODO Feature 5

    @Override
    public int UpdateCar(int id, int price) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        int rowsInserted = 0;

        try {

            connection = this.getConnection();
            String query = "UPDATE cars\n" +
                    "SET price = " + price + " WHERE car_ID =" + id;
            preparedStatement = connection.prepareStatement(query);
            // preparedStatement.setInt(1, id);


            Statement statement = connection.createStatement(); {

                System.out.println("\nConnected to the database.");

                // Execute the delete statement
                rowsInserted = statement.executeUpdate(query);

                if (rowsInserted > 0) {
                    System.out.println("Car Price updates successfully with ID: " + id + " NEW PRICE: " + price + " ");
                } else {
                    System.out.println("Failed to add car: " + price + " " + price + " ");
                }


                System.out.println("\nFinished - Disconnected from database");

            }
        } catch (SQLException e) {
            throw new DaoException("findAllCaresultSet() " + e.getMessage());
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
                throw new DaoException("InsertCar() " + e.getMessage());
            }
        }
        return rowsInserted;
    }

    @Override
    public List<Car> filterYear(int year) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Car> carsList = new ArrayList<>();

        try {
            //Get connection object using the getConnection() method inherited
            // from the super class (MySqlDao.java)
            connection = this.getConnection();

            String query = "select * from cars WHERE modelyear <= " + year;
            preparedStatement = connection.prepareStatement(query);

            //Using a PreparedStatement to execute SQL...
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
            throw new DaoException("findAllCaresultSet() " + e.getMessage());
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
                throw new DaoException("findAllUsers() " + e.getMessage());
            }
        }
        return carsList;     // may be empty
    }

    @Override
    public String Jsonstring(int id) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String jsonString = "";

        try {
            //Get connection object using the getConnection() method inherited
            // from the super class (MySqlDao.java)
            connection = this.getConnection();

            String query = "select * from cars";
            preparedStatement = connection.prepareStatement(query);

            //Using a PreparedStatement to execute SQL...
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int Id = resultSet.getInt(1);
                String make = resultSet.getString(2);
                String model = resultSet.getString(3);
                int year1 = resultSet.getInt(4);
                int price = resultSet.getInt(5);
               
            }
        } catch (SQLException e) {
            throw new DaoException("findAllCaresultSet() " + e.getMessage());
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
                throw new DaoException("findAllUsers() " + e.getMessage());
            }
        }
        return carsList;     // may be empty

    }

    @Override
    public List<Car> JsonString() throws DaoException {
        // Call the Jsonstring() method to maintain compatibility with both interfaces
        return Jsonstring() }
}