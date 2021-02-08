CREATE SCHEMA IF NOT EXISTS `ims`;

USE `ims` ;

CREATE TABLE IF NOT EXISTS `customers` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(40) DEFAULT NULL,
  `surname` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `items` (
  `product_ID` int(50) NOT NULL AUTO_INCREMENT,
  `product_name` varchar(50) NOT NULL,
  `product_description` varchar(250) DEFAULT NULL,
  `product_value` int(11) NOT NULL,
  `product_stockLevels` int(11) NOT NULL,
  PRIMARY KEY (`product_ID`)
);

CREATE TABLE IF NOT EXISTS `orders` (
  `order_ID` int(50) NOT NULL AUTO_INCREMENT,
  `customer_id` int(11) NOT NULL,
  PRIMARY KEY (`order_ID`),
  KEY `customer_id` (`customer_id`),
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`)
);

CREATE TABLE IF NOT EXISTS `orderline` (
  `order_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `orderline_quantity` int(11) NOT NULL,
  KEY `orderline_ibfk_3` (`product_id`),
  KEY `orderline_ibfk_1` (`order_id`),
  CONSTRAINT `orderline_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `orderline_ibfk_3` FOREIGN KEY (`product_id`) REFERENCES `items` (`product_ID`)
);