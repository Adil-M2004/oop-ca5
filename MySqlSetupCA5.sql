DROP DATABASE IF EXISTS CA5;
CREATE DATABASE IF NOT EXISTS CA5;

DROP TABLE IF EXISTS cars;

/*CREATE TABLE*/
CREATE TABLE cars(car_ID INT NOT NULL AUTO_INCREMENT,
make VARCHAR(20),
model VARCHAR(20),
modelyear INT,                     
PRIMARY KEY(car_ID));

/*DATA INPUT*/
INSERT INTO cars (car_ID, make, model, modelyear) 
VALUES 
    (1, 'Toyota', 'Camry', 2020),
    (2, 'Honda', 'Civic', 2019),
    (3, 'Ford', 'Mustang', 2021),
    (4, 'Chevrolet', 'Malibu', 2018),
    (5, 'BMW', 'X5', 2022),
    (6, 'Mercedes-Benz', 'C-Class', 2020),
    (7, 'Audi', 'A4', 2021),
    (8, 'Tesla', 'Model S', 2023),
    (9, 'Nissan', 'Altima', 2019),
    (10, 'Hyundai', 'Elantra', 2022);