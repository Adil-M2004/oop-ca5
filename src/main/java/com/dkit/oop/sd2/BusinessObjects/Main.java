package com.dkit.oop.sd2.BusinessObjects;

/** OOP Jan 2025
 * This AppMain demonstrates the use of a Data Access Layer
 * to separate Business logic from Database specific logic.
 * It uses:
 * Data Access Objects (DAOs) to implement the logic required to access a database.
 * Data Transfer Objects (DTOs), to transfer data between layers, and a
 * DAO Interface to define a 'contract' between Business Objects and DAOs.
 *
 * "Use a Data Access Object (DAO) to abstract and encapsulate all
 * access to the data source. The DAO manages the connection with
 * the data source to obtain and store data" Ref: oracle.com
 *
 * Here, we use one DAO per database table.
 *
 * Use the SQL script "Ca5MySQL.sql" included with this project
 * to create the required MySQL user_database and User table.
 */

import com.dkit.oop.sd2.DAOs.MySqlCarDao;
import com.dkit.oop.sd2.DAOs.CarDaoInterface;
import com.dkit.oop.sd2.DTOs.Car;
import com.dkit.oop.sd2.Exceptions.DaoException;

import java.sql.SQLOutput;
import java.util.List;

import java.util.Scanner;
//import org.json.JSONObject;

public class Main {
    public static void main(String[] args) {
        Main Main = new Main();

        Scanner keyboard = new Scanner(System.in);
        System.out.println("Input Feature Option (Milstone 1: 1-6)\n " +
                "1 – Get all Entities\n" +
                "2 - Find and display car by key\n" +
                "3 – Delete an Entity by key\n" +
                "4 - Insert an Entity\n" +
                "5 – Update an existing Entity by ID\n" +
                "6 - Get list of entities matching a filter\n" +
                "7 - Convert List of Entities to a JSON String\n" +
                "8 -  Convert a single Entity by Key into a JSON String\n" +
                "9 - Display Entity by Id\n" +
                "10 - Display all Entities"
        );
        int option = keyboard.nextInt();


        if (option == 1) {
            Main.feature1();
           // menu();
        } else if (option == 2) {
            Main.feature2(2);
           // menu();
        } else if (option == 3) {
           Main.feature3(3);
//            menu();
        } else if (option == 4) {
            Car car = new Car("Ferrari", "458 Italia", 2016, 26000);
           Main.feature4(car);
       //     menu();
        } else if (option == 5) {
            //Main.feature5(4, 45000);
            System.out.println("Input ID");
            int id = keyboard.nextInt();
            System.out.println("Input New Price");
            int price = keyboard.nextInt();
            Main.feature5(id, price);
//            menu();
        } else if (option == 6) {
            Main.feature6(2020);
           menu();            //MILESTONE 1 END
        } else if (option == 7) { //MILSTONE 2 START
            Main.feature7();
//            menu();
        } else if (option == 8) {
            System.out.println("Enter Car Id:");
            int Id = keyboard.nextInt();
            Main.feature8(Id);
//            menu();
//        } else if (option == 9) {
//            System.out.println("Enter Car ID:");
//            int carId = keyboard.nextInt();
//            Main.feature9(carId);


        }// MAIN LB


    }

//    //MENU METHOD
    public static void menu() throws DaoException {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Input Feature Option (Milstone 1: 1-6)\n" +
                "1 – Get all Entities\n" +
                "2 - Find and display car by key\n" +
                "3 – Delete an Entity by key\n" +
                "4 - Insert an Entity\n" +
                "5 – Update an existing Entity by ID\n" +
                "6 - Get list of entities matching a filter\n" +
                "7 - Convert List of Entities to a JSON String\n" +
                "8 -  Convert a single Entity by Key into a JSON String\n"

        );
        int option = keyboard.nextInt();


        if (option == 1) {
            Main.feature1();
            menu();
        } else if (option == 2) {
            Main.feature2(2);
            menu();
        } else if (option == 3) {
            Main.feature3(3);
            menu();
        } else if (option == 4) {
            Car car = new Car("Ferrari", "458 Italia", 2016, 26000);
            Main.feature4(car);
            menu();
        } else if (option == 5) {
            //Main.feature5(4, 45000);
            System.out.println("Input ID");
            int id = keyboard.nextInt();
            System.out.println("Input New Price");
            int price = keyboard.nextInt();
            Main.feature5(id, price);
            menu();
        } else if (option == 6) {
            Main.feature6(2020);
            menu();            //MILESTONE 1 END
        } else if (option == 7) { //MILSTONE 2 START
            Main.feature7();menu();
        } else if (option == 8) {
            System.out.println("Enter Car Id:");
            int Id = keyboard.nextInt();
            Main.feature8(Id);
            menu();

        }
    }

    //FEATURES (METHODS)

    public static void feature1() {
        CarDaoInterface ICarDao = new MySqlCarDao();  //"IUserDao" -> "I" stands for Interface

        try {
            System.out.println("\nCall findAllCars()");
            List<Car> cars = ICarDao.findAllCars();     // call a method in the DAO

            if (cars.isEmpty())
                System.out.println("There are no Cars");
            else {
                for (Car car : cars)
                    System.out.println("Car: " + car.toString());
            }


        } catch (DaoException e) {
            /// This code is executed when the DAO layer throws an exception.
            /// We might place some logic here to deal with the issue, but in this case,
            /// we simply print out the exception error message to the console.
            e.printStackTrace();
        }
    }

    public static void feature2(int id) {

        CarDaoInterface ICarDao = new MySqlCarDao();

        try {
            System.out.println("\nCall findCarByID()");
            Car car = ICarDao.findCarById(id);    // call a method in the DAO


            System.out.println("Car By ID: " + car.toString());

        } catch (DaoException e) {
            /// This code is executed when the DAO layer throws an exception.
            /// We might place some logic here to deal with the issue, but in this case,
            /// we simply print out the exception error message to the console.
            e.printStackTrace();
        }
    }

    public static void feature3(int id) {
        CarDaoInterface ICarDao = new MySqlCarDao();

        try {
            System.out.println("\nCall DeleteCarByKey()");
            int rowdeleted = ICarDao.DeleteCarByKey(id);

            System.out.println(rowdeleted);


        } catch (DaoException e) {
            e.printStackTrace();
        }
    }



    public static void feature4(Car car) {
        CarDaoInterface ICarDao = new MySqlCarDao();

        try {
            System.out.println("\nCall InsertCar()");
            int car1 = ICarDao.InsertCar(car);

            System.out.println(car1);


        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    public static void feature5(int id, int price) {
        CarDaoInterface ICarDao = new MySqlCarDao();

        try {
            System.out.println("\nCall UpdateCar()");
            int car1 = ICarDao.UpdateCar(id, price);

            System.out.println(car1);

        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    public static void feature6(int year) {
        CarDaoInterface ICarDao = new MySqlCarDao();

        try {
            System.out.println("\nCall Filteryear");
            List<Car> cars = ICarDao.filterYear(year);

            System.out.println(cars);


        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
    
    public static void feature7() {
        CarDaoInterface ICarDao = new MySqlCarDao();

        try {
            System.out.println("\n CallEntitiesToJson");
            List<Car> cars = ICarDao.Jsonstring();

            System.out.println(cars);

        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    public static void feature8(int Id) {
        CarDaoInterface ICarDao = new MySqlCarDao();

        try{
            System.out.println("\n CallJsonEntity");
            String entity = ICarDao.jsonEntity(Id);

            System.out.println(entity);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

//    public static void feature9(int Id) {
//        CarDaoInterface ICarDao = new MySqlCarDao();
//
//        try {
//
//        }
//    }

}//LB
