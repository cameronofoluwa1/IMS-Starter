INSERT INTO `customers` (`first_name`, `surname`) VALUES ('jordan', 'harrison');
INSERT INTO `items` (`product_name`, `product_description`, `product_value`, `product_stockLevels`) VALUES ('TV', 'A TV', 5, 5);
INSERT INTO `orders`(`customer_id`) VALUES (1);
INSERT INTO `orderline`(`order_id`, `product_id`, `orderline_quantity`) VALUES (1, 1, 5);