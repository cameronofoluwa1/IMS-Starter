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

	//Reads all items
	@Override
	public List<Item> readAll() {
		List<Item> items = itemDAO.readAll();
		for (Item item : items) {
			LOGGER.info(item);
		}
		return items;
	}

	//Create an item in the database, @param name, description, value, stock_level
	@Override
	public Item create() {
		LOGGER.info("Please enter product name");
		String productName = utils.getString();
		LOGGER.info("Please enter product description");
		String productDescription = utils.getString();
		LOGGER.info("Please enter product value");
		Double productValue = utils.getDouble();
		LOGGER.info("Please enter product stock level");
		Long productStockLevels = utils.getLong();
		Item item = itemDAO.create(new Item(productName, productDescription, productValue, productStockLevels));
		LOGGER.info("Item added to system.");
		return item;
	}

	//Update an item in the database, @param product_id, name, description, value, stock_level
	@Override
	public Item update() {
		LOGGER.info("Please enter the id of the item you would like to update");
		Long id = utils.getLong();
		LOGGER.info("Please enter product name");
		String productName = utils.getString();
		LOGGER.info("Please enter product description");
		String productDescription = utils.getString();
		LOGGER.info("Please enter product value");
		Double productValue = utils.getDouble();
		LOGGER.info("Please enter product stock level");
		Long productStockLevels = utils.getLong();
		Item item = itemDAO.update(new Item(id, productName, productDescription, productValue, productStockLevels));
		LOGGER.info("Item Updated");
		return item;
	}

	//Update an item in the database, @param product_id
	@Override
	public int delete() {
		LOGGER.info("Please enter the id of the item you would like to delete");
		Long id = utils.getLong();
		LOGGER.info("Item Deleted");
		return itemDAO.delete(id);
	}

}
