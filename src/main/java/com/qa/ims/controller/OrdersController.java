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
	
	//Read all orders
	@Override
	public List<Orders> readAll() {
		List<Orders> orders = ordersDAO.readAll();
		for (Orders order : orders) {
			LOGGER.info(order);
		}
		return orders;
	}

	//Create an order
	@Override
	public Orders create() {
		LOGGER.info("\nPlease enter a valid customer id");
		Long customerID = utils.getLong();
		Orders orders = ordersDAO.create(new Orders(customerID));
		LOGGER.info("Please enter an item id = ");
		Long Item_ID = utils.getLong();
		LOGGER.info("Please enter item quantity = ");
		Long orderline_quantity = utils.getLong();
		Orderline orderline = orderlineDAO.create(new Orderline(ordersDAO.getlatestIDGen(), Item_ID, orderline_quantity));
		boolean enterNewItem = true;
        do {
            System.out.println("\nAdd a new item? '1' = Yes, '0' = No");
            Long exitQue = utils.getLong();
            if(exitQue == 0) {
                enterNewItem = false;
            }else {
                System.out.println("Enter item ID = ");
                Long newItem_ID = utils.getLong();
                orderline = orderlineDAO.create(new Orderline(ordersDAO.getlatestIDGen(), newItem_ID, newItem_ID));
            }
        } while(enterNewItem);

        System.out.println("\nCustomer order " + ordersDAO.getlatestIDGen() + " added to system.\n");
		return orders;
	}

	//Update an order
	@Override
	public Orders update() {
		// TODO Auto-generated method stub
		return null;
	}

	//Delete an order
	@Override
	public int delete() {
		LOGGER.info("\nEnter order ID you want to delete : ");
		Long id = utils.getLong();
		return ordersDAO.delete(id);
	}

}
