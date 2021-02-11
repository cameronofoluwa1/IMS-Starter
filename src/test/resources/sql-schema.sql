DROP TABLE IF EXISTS orderline CASCADE;
DROP TABLE IF EXISTS orders CASCADE;
DROP TABLE IF EXISTS items CASCADE;
DROP TABLE IF EXISTS customers CASCADE;

CREATE TABLE customers (
    customerID INT(11) NOT NULL AUTO_INCREMENT,
    firstName VARCHAR(40) DEFAULT NULL,
    surname VARCHAR(40) DEFAULT NULL,
    PRIMARY KEY (customerID)
);

CREATE TABLE items (
    productID INT NOT NULL AUTO_INCREMENT,
    productName VARCHAR(50) NOT NULL,
    productDescription VARCHAR(250) DEFAULT NULL,
    productValue DOUBLE NOT NULL,
    productStockLevels INT NOT NULL,
    PRIMARY KEY (productID)
);

CREATE TABLE orders (
    ordersID INT NOT NULL AUTO_INCREMENT,
    customerID INT NOT NULL,
    PRIMARY KEY (ordersID),
    FOREIGN KEY (customerID)
        REFERENCES customers (customerID)
        ON DELETE CASCADE
);

CREATE TABLE orderline (
    orderlineID INT NOT NULL AUTO_INCREMENT,
    ordersID INT NOT NULL,
    productID INT NOT NULL,
    orderlineQuantity INT,
    PRIMARY KEY (orderlineID),
    FOREIGN KEY (ordersID)
        REFERENCES orders (ordersID)
        ON DELETE CASCADE,
    FOREIGN KEY (productID)
        REFERENCES items (productID)
        ON DELETE CASCADE
);