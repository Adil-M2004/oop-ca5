package org.example;

public class Car {
    // Fields (properties)
    int id;
    String make;
    String model;
    int modelyear;
    int price;


    //empty constuctor
    public Car() {

    }
//CONSTUCTOR
    public Car(int carId, String make, String model, int modelyear, int price) {
        this.id = carId;
        this.make = make;
        this.model = model;
        this.modelyear = modelyear;
        this.price = price;


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
