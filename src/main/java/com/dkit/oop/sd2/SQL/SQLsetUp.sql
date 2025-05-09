DROP DATABASE IF EXISTS CA5;
CREATE DATABASE IF NOT EXISTS CA5;

DROP TABLE IF EXISTS cars;

/*CREATE TABLE*/
CREATE TABLE cars(car_ID INT NOT NULL AUTO_INCREMENT,
                  make VARCHAR(20),
                  model VARCHAR(20),
                  modelyear INT,
                  price INT,
                  PRIMARY KEY(car_ID));

/*DATA INPUT*/
INSERT INTO cars (car_ID, make, model, modelyear, price)
VALUES
    (1, 'Toyota', 'Camry', 2020, 25000),
    (2, 'Honda', 'Civic', 2019, 46000),
    (3, 'Ford', 'Mustang', 2021, 46000),
    (4, 'Chevrolet', 'Malibu', 2018, 45000),
    (5, 'BMW', 'X5', 2022, 67000),
    (6, 'Mercedes-Benz', 'C-Class', 2020, 16000),
    (7, 'Audi', 'A4', 2021, 17000),
    (8, 'Tesla', 'Model S', 2023, 27000),
    (9, 'Nissan', 'Altima', 2019, 36000),
    (10, 'Hyundai', 'Elantra', 2022, 34000);