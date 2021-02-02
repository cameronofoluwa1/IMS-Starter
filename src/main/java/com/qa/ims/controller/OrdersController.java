package com.qa.ims.controller;

import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.OrderlineDAO;
import com.qa.ims.persistence.dao.OrdersDAO;
import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Orderline;
import com.qa.ims.persistence.domain.Orders;
import com.qa.ims.utils.Utils;

public class OrdersController implements CrudController<Orders> {

	public static final Logger LOGGER = LogManager.getLogger();

	private OrdersDAO ordersDAO;
	private OrderlineDAO orderlineDAO;
	private Utils utils;

	public OrdersController(OrdersDAO ordersDAO, OrderlineDAO orderlineDAO, Utils utils) {
		super();
		this.ordersDAO = ordersDAO;
		this.orderlineDAO = orderlineDAO;
		this.utils = utils;
	}
	
	/**
	 * Reads all customers to the logger
	 */
	@Override
	public List<Orders> readAll() {
		List<Orders> orders = ordersDAO.readAll();
		for (Orders order : orders) {
			LOGGER.info(order);
		}
		return orders;
	}

	/**
	 * Creates a customer by taking in user input
	 */
	@Override
	public Orders create() {
		boolean enterNewItem = true;
		Scanner scanner = new Scanner(System.in);
		LOGGER.info("Please enter a valid customer id");
		Long customerID = utils.getLong();
		Orders orders = ordersDAO.create(new Orders(customerID));
		LOGGER.info("Created order, ID " + orders.getOrder_ID());
		LOGGER.info("Please enter an item id = ");
		Long Item_ID = utils.getLong();
		Orderline orderline = orderlineDAO.create(new Orderline(orders.getOrder_ID(), Item_ID, Item_ID));
        do {
            System.out.println("Add a new item? '1' = Yes, '0' = No");
            int exitQue = scanner.nextInt();
            if(exitQue == 0) {
                enterNewItem = false;
            }else {
                System.out.println("Enter item ID = ");
                Long newItem_ID = scanner.nextLong();
                orderline = orderlineDAO.create(new Orderline(orders.getOrder_ID(), newItem_ID, newItem_ID));
            }
        } while(enterNewItem);

        System.out.println("Finished customer order, ID " + orders.getOrder_ID());
		return orders;
	}

	@Override
	public Orders update() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Deletes an existing customer by the id of the customer
	 * 
	 * @return
	 */
	@Override
	public int delete() {
		LOGGER.info("Please enter the id of the customer you would like to delete");
		Long id = utils.getLong();
		return ordersDAO.delete(id);
	}

}
