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

import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.utils.DBUtils;

public class CustomerDAO implements Dao<Customer> {

	public static final Logger LOGGER = LogManager.getLogger();

	@Override
	public Customer modelFromResultSet(ResultSet resultSet) throws SQLException {
		Long id = resultSet.getLong("customerID");
		String firstName = resultSet.getString("firstName");
		String surname = resultSet.getString("surname");
		return new Customer(id, firstName, surname);
	}

	//Read all customers, @returns a list
	@Override
	public List<Customer> readAll() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM customers");) {
			List<Customer> customers = new ArrayList<>();
			while (resultSet.next()) {
				customers.add(modelFromResultSet(resultSet));
			}
			return customers;
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}

	public Customer readLatest() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM customers ORDER BY customerID DESC LIMIT 1");) {
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	//Create a customer in the database, @param first_name, last_name
	@Override
	public Customer create(Customer customer) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("INSERT INTO customers(firstName, surname) VALUES (?, ?)");) {
			statement.setString(1, customer.getFirstName());
			statement.setString(2, customer.getSurname());
			statement.executeUpdate();
			LOGGER.info(String.format("Created user with first name %1$s & surname %2$s", customer.getFirstName(), customer.getSurname()));
			return readLatest();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public Customer read(Long customerID) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM customers WHERE customerID = ?");) {
			statement.setLong(1, customerID);
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

	//Update customer in database, @param first_name, last_name, id
	@Override
	public Customer update(Customer customer) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("UPDATE customers SET firstName = ?, surname = ? WHERE customerID = ?");) {
			statement.setString(1, customer.getFirstName());
			statement.setString(2, customer.getSurname());
			statement.setLong(3, customer.getID());
			statement.executeUpdate();
			return read(customer.getID());
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	//Delete a customer in the database, @param id
	@Override
	public int delete(long customerID) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("DELETE FROM customers WHERE customerID = ?");) {
			statement.setLong(1, customerID);
			LOGGER.info(String.format("Deleted user with customerID %1$s", customerID));
			return statement.executeUpdate();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}

}
