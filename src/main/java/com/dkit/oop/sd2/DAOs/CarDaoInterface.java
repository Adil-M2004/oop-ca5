package com.dkit.oop.sd2.DAOs;

/** OOP Feb 2022
 * UserDaoInterface
 *
 * Declares the methods that all UserDAO types must implement,
 * be they MySql User DAOs or Oracle User DAOs etc...
 *
 * Classes from the Business Layer (users of this DAO interface)
 * should use reference variables of this interface type to avoid
 * dependencies on the underlying concrete classes (e.g. MySqlUserDao).
 *
 * More sophisticated implementations will use a factory
 * method to instantiate the appropriate DAO concrete classes
 * by reading database configuration information from a
 * configuration file (that can be changed without altering source code)
 *
 * Interfaces are also useful when testing, as concrete classes
 * can be replaced by mock DAO objects.
 */

import com.dkit.oop.sd2.DTOs.Car;
import com.dkit.oop.sd2.Exceptions.DaoException;
import java.util.List;

public interface CarDaoInterface
{
    public List<Car> findAllCars() throws DaoException;

  //  public User findUserByUsernamePassword(String username, String password) throws DaoException;

    public Car findCarById(int id) throws DaoException;

    public int DeleteCarByKey(int id) throws DaoException;

    public int InsertCar(Car car) throws DaoException;

    int UpdateCar(int id, int price) throws DaoException;

    public List<Car> filterYear(int year) throws DaoException;

    public List<Car> Jsonstring() throws DaoException;

    public String jsonEntity(int id) throws DaoException;



}

