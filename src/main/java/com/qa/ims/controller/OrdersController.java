package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.OrderlineDAO;
import com.qa.ims.persistence.dao.OrdersDAO;
import com.qa.ims.persistence.domain.Orderline;
import com.qa.ims.persistence.domain.Orders;
import com.qa.ims.utils.Utils;

public class OrdersController implements CrudController<Orders> {

	public static final Logger LOGGER = LogManager.getLogger();

	private OrdersDAO ordersDAO;
	private OrderlineDAO orderlineDAO;
	private Utils utils;
	private String inputMessage = "Please enter an item id = ";
	private String inputMessage2 = "Please enter item quantity = ";

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
		LOGGER.info(inputMessage);
		Long itemID = utils.getLong();
		LOGGER.info(inputMessage2);
		Long orderlineQuantity = utils.getLong();
		Orderline orderline = orderlineDAO.create(new Orderline(ordersDAO.getlatestIDGen(), itemID, orderlineQuantity));
		boolean enterNewItem = true;
        do {
        	LOGGER.info("\nAdd a new item? '1' = Yes, '0' = No");
            Long exitQue = utils.getLong();
            if(exitQue == 0) {
                enterNewItem = false;
            }else {
    			LOGGER.info(inputMessage);
    			Long newItemID = utils.getLong();
    			LOGGER.info(inputMessage2);
    			Long newOrderlineQuantity = utils.getLong();
    			orderline = orderlineDAO.create(new Orderline(ordersDAO.getlatestIDGen(), newItemID, newOrderlineQuantity));
            }
        } while(enterNewItem);

        LOGGER.info(String.format("%nCustomer order %1$s added to the system.", ordersDAO.getlatestIDGen()));
		return orders;
	}

	//Update an order
	@Override
	public Orders update() {
		LOGGER.info("Please enter the id of the order you would like to update");
		Long id = utils.getLong();
		LOGGER.info("Do you want  to 'add' or 'delete' an item?");
		String newCmd = utils.getString();
		LOGGER.info(newCmd);
		if(newCmd.equalsIgnoreCase("add")) {
			LOGGER.info(inputMessage);
			Long itemID = utils.getLong();
			LOGGER.info(inputMessage2);
			Long orderlineQuantity = utils.getLong();
			Orderline orderline = orderlineDAO.create(new Orderline(id, itemID, orderlineQuantity));
		}else if(newCmd.equalsIgnoreCase("delete")) {
			LOGGER.info("Enter orderline ID oyu want to delete = ");
			Long itemID = utils.getLong();
			//return OrderlineDAO.delete(id);
		}else {
			LOGGER.info("Invalid answer. Returning");
		}
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
