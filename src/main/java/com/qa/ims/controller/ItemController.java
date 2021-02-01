package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.ItemDAO;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.Utils;

public class ItemController implements CrudController<Item>{
	
	public static final Logger LOGGER = LogManager.getLogger();
	
	private ItemDAO itemDAO;
	private Utils utils;
	
	public ItemController(ItemDAO itemDAO, Utils utils){
		super();
		this.itemDAO = itemDAO;
		this.utils = utils;
	}

	/**
	 * Reads all items to the logger
	 */
	@Override
	public List<Item> readAll() {
		List<Item> items = itemDAO.readAll();
		for (Item item : items) {
			LOGGER.info(item);
		}
		return items;
	}

	/**
	 * Creates an Item by taking in user input
	 */
	@Override
	public Item create() {
		LOGGER.info("Please enter product name");
		String product_name = utils.getString();
		LOGGER.info("Please enter product description");
		String product_description = utils.getString();
		LOGGER.info("Please enter product value");
		Double product_value = utils.getDouble();
		LOGGER.info("Please enter product stock level");
		Long product_stockLevels = utils.getLong();
		Item item = itemDAO.create(new Item(product_name, product_description, product_value, product_stockLevels));
		LOGGER.info("Item added to system.");
		return item;
	}

	/**
	 * Updates an existing item by taking in user input
	 */
	@Override
	public Item update() {
		LOGGER.info("Please enter the id of the item you would like to update");
		Long id = utils.getLong();
		LOGGER.info("Please enter product name");
		String product_name = utils.getString();
		LOGGER.info("Please enter product description");
		String product_description = utils.getString();
		LOGGER.info("Please enter product value");
		Double product_value = utils.getDouble();
		LOGGER.info("Please enter product stock level");
		Long product_stockLevels = utils.getLong();
		Item item = itemDAO.update(new Item(id, product_name, product_description, product_value, product_stockLevels));
		LOGGER.info("Item Updated");
		return item;
	}

	/**
	 * Deletes an existing item by the id of the customer
	 * 
	 * @return
	 */
	@Override
	public int delete() {
		LOGGER.info("Please enter the id of the item you would like to delete");
		Long id = utils.getLong();
		LOGGER.info("Item Deleted");
		return itemDAO.delete(id);
	}

}