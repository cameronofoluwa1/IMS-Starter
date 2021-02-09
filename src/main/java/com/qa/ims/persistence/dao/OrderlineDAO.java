package com.qa.ims.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.domain.Orderline;
import com.qa.ims.persistence.domain.Orders;
import com.qa.ims.utils.DBUtils;

public class OrderlineDAO implements Dao<Orderline>{

	public static final Logger LOGGER = LogManager.getLogger();

	@Override
	public Orderline modelFromResultSet(ResultSet resultSet) throws SQLException {
		Long order_ID = resultSet.getLong("orderID");
		Long item_ID = resultSet.getLong("itemID");
		Long orderline_quantity = resultSet.getLong("orderlineQuantity");
		return new Orderline(order_ID, item_ID, orderline_quantity);
	}
	
	@Override
	public List<Orderline> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Orderline read(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Creates a customer in the database
	 * 
	 * @param customer - takes in a customer object. id will be ignored
	 */
	@Override
	public Orderline create(Orderline orderline) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("INSERT INTO orderline(ordersID, productID, orderlineQuantity) VALUES (?, ?, ?)");) {
			statement.setLong(1, orderline.getOrderID());
			statement.setLong(2, orderline.getItemID());
			statement.setLong(3, orderline.getOrderlineQuantity());
			statement.executeUpdate();
			return (new Orderline(orderline.getOrderID(), orderline.getItemID(), orderline.getOrderlineQuantity()));
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public Orderline update(Orderline t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(long id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
