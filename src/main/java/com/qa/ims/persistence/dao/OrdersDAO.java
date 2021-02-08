package com.qa.ims.persistence.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.domain.Orders;
import com.qa.ims.utils.DBUtils;

public class OrdersDAO implements Dao<Orders>{

	public static final Logger LOGGER = LogManager.getLogger();
	
	public static Long latestIDGen;

	@Override
	public Orders modelFromResultSet(ResultSet resultSet) throws SQLException {
		Long order_ID = resultSet.getLong("order_ID");
		Long customer_id = resultSet.getLong("customer_id");
		Long product_id = resultSet.getLong("product_id");
		return new Orders(order_ID, customer_id, product_id);
	}
	
	//Read all customers, @returns a list
	@Override
	public List<Orders> readAll() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT orders.order_ID, orderline.product_id, orders.customer_id FROM orders INNER JOIN orderline ON orders.order_id=orderline.order_id order by orderline.order_ID");) {
			List<Orders> orders = new ArrayList<>();
			while (resultSet.next()) {
				orders.add(modelFromResultSet(resultSet));
			}
			return orders;
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}
	
	//Get the last ID generated in the 'orders' table
	public Long getlatestIDGen() {
		return latestIDGen;
	}

	public Orders readLatest() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT orders.order_ID, orderline.product_id, orders.customer_id FROM orders INNER JOIN orderline ON orders.order_id=orderline.order_id order by orderline.order_ID");) {
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public Orders read(Long id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT orders.order_ID, orderline.product_id, orders.customer_id FROM orders INNER JOIN orderline ON orders.order_id=orderline.order_id where orderline.order_ID = ?");) {
			statement.setLong(1, id);
			try (ResultSet resultSet = statement.executeQuery();) {
				resultSet.next();
				return modelFromResultSet(resultSet);
			}
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	
	//Creates a customer order in the database, @param customer_id
	@Override
	public Orders create(Orders orders) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("INSERT INTO orders (customer_id) VALUES (?)", Statement.RETURN_GENERATED_KEYS);) {
			statement.setLong(1, orders.getcustomer_id());
			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			if(rs.next()) {
				latestIDGen = rs.getLong(1);
			}
			return readLatest();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	//Update a customer order in the database
	@Override
	public Orders update(Orders t) {
		// TODO Auto-generated method stub
		return null;
	}

	//Delete a customer order in the database, @param customer_id
	@Override
	public int delete(long id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("DELETE FROM orders WHERE order_id = ?");) {
			statement.setLong(1, id);
			LOGGER.info("\nDeleted order " + id + " from the system.");
			return statement.executeUpdate();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}

}
