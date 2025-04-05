package org.example;

import org.json.JSONObject;

public class jsonConverterClass {

    public static void jsonconverter(int car_ID, String make, String model, int modelyear, int price) {

        //Create the JSON OBJECT
        JSONObject jsonObject = new JSONObject();

        // Adding key=>value pairs.  Keys must be strings, but values can have various types.
        jsonObject.put("Car ID", car_ID);
        jsonObject.put("Make", make);
        jsonObject.put("Model", model);
        jsonObject.put("Model Year", modelyear);
        jsonObject.put("Price", price);

        String jsonString = jsonObject.toString();

        System.out.println("JSON String is: \n " + jsonString);
    }

    public static void jsonconverter2(int car_ID, String make, String model, int modelyear, int price) {

        //Create the JSON OBJECT
        JSONObject jsonObject = new JSONObject();

        // Adding key=>value pairs.  Keys must be strings, but values can have various types.
        jsonObject.put("Car ID", car_ID);
        jsonObject.put("Make", make);
        jsonObject.put("Model", model);
        jsonObject.put("Model Year", modelyear);
        jsonObject.put("Price", price);

        String jsonString = jsonObject.toString();

        System.out.println("JSON String is: \n " + jsonString);
    }

}
