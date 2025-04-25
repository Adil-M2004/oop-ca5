package com.dkit.oop.sd2.DTOs;

public class Car {
    private int id;
    private String make;
    private String model;
    private int year;
    private int price;

    public Car(int id, String make, String model, int year, int price) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.year = year;
        this.price = price;
    }

    public Car( String make, String model, int year, int price) {
        this.id = 0;
        this.make = make;
        this.model = model;
        this.year = year;
        this.price = price;
    }
    public Car() {

    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getMake() {
        return make;
    }
    public void setMake(String make) {
        this.make = make;
    }
    public String getModel() {
        return model;

    }
    public void setModel(String model) {
        this.model = model;
    }
    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", price=" + price +
                '}';
    }
}
