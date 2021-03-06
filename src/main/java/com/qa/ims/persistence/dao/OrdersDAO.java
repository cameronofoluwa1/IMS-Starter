package com.qa.ims.persistence.dao;

import java.sql.Connection;
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
		Long orderID = resultSet.getLong("ordersID");
		Long customerID = resultSet.getLong("customerID");
		Long productID = resultSet.getLong("productID");
		double orderValue = resultSet.getDouble("orderValue");
		return new Orders(orderID, customerID, productID, orderValue);
	}
	
	//Read all customers, @returns a list
	@Override
	public List<Orders> readAll() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT \r\n"
						+ "    orders.ordersID,\r\n"
						+ "    orders.customerID,\r\n"
						+ "    orderline.productID,\r\n"
						+ "    SUM(orderline.orderlineQuantity * items.productValue) AS orderValue\r\n"
						+ "FROM\r\n"
						+ "    orders AS orders\r\n"
						+ "        INNER JOIN\r\n"
						+ "    orderline AS orderline ON orders.ordersID = orderline.ordersID\r\n"
						+ "        INNER JOIN\r\n"
						+ "    items AS items ON orderline.productID = items.productID\r\n"
						+ "    group by orders.ordersID, orderline.productID;");) {
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
				ResultSet resultSet = statement.executeQuery("SELECT \r\n"
						+ "    orders.ordersID,\r\n"
						+ "    orders.customerID,\r\n"
						+ "    orderline.productID,\r\n"
						+ "    SUM(orderline.orderlineQuantity * items.productValue) AS orderValue\r\n"
						+ "FROM\r\n"
						+ "    orders AS orders\r\n"
						+ "        INNER JOIN\r\n"
						+ "    orderline AS orderline ON orders.ordersID = orderline.ordersID\r\n"
						+ "        INNER JOIN\r\n"
						+ "    items AS items ON orderline.productID = items.productID\r\n"
						+ "    group by orders.ordersID, orderline.productID;");) {
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
				PreparedStatement statement = connection.prepareStatement("SELECT \r\n"
						+ "    orders.ordersID,\r\n"
						+ "    orders.customerID,\r\n"
						+ "    orderline.productID,\r\n"
						+ "    SUM(orderline.orderlineQuantity * items.productValue) AS orderValue\r\n"
						+ "FROM\r\n"
						+ "    orders AS orders\r\n"
						+ "        INNER JOIN\r\n"
						+ "    orderline AS orderline ON orders.ordersID = orderline.ordersID\r\n"
						+ "        INNER JOIN\r\n"
						+ "    items AS items ON orderline.productID = items.productID\r\n"
						+ "    where orderline.ordersID = ?\r\n"
						+ "    group by orders.ordersID, orderline.productID;");) {
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
						.prepareStatement("INSERT INTO orders (customerID) VALUES (?)", Statement.RETURN_GENERATED_KEYS);) {
			statement.setLong(1, orders.getCustomerID());
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
				PreparedStatement statement = connection.prepareStatement("DELETE FROM orders WHERE ordersID = ?");) {
			statement.setLong(1, id);
			LOGGER.info(String.format("Deleted order with ID %1$s ", id));
			return statement.executeUpdate();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}

}
