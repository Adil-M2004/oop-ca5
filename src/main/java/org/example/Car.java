package org.example;

public class Car {
    // Fields (properties)
    int id;
    String make;
    String model;
    int modelyear;
    int price;

    // Constructor (used to initialize objects)
    public Car(String brand, String color, int year, int price) {
        this.make = brand;
        this.model = color;
        this.modelyear = year;
        this.price = price;
    }

    //empty constuctor
    public Car() {

    }

    public void setMake(String make) {
        this.make = make;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setModelyear(int modelyear) {
        this.modelyear = modelyear;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
