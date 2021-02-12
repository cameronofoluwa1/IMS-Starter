package com.qa.ims.persistence.domain;

import static org.junit.Assert.*;

import java.lang.reflect.Field;

import org.junit.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class OrdersTest {
	
	private final Orders orders = new Orders(1L, 1L, 1L);

	@Test
	public void testGetOrderID() {
		assertEquals(1L, orders.getOrderID().longValue());
	}
	
	@Test
	public void testSetOrderID() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		orders.setOrderID(2L);
		final Field field = orders.getClass().getDeclaredField("orderID");
		field.setAccessible(true);
		assertEquals("Fields didn't match", field.get(orders), 2L);
		}

	@Test
	public void testGetCustomerID() {
		assertEquals(1L, orders.getCustomerID().longValue());
	}
	
	@Test
	public void testSetCustomerID() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		orders.setCustomerID(2L);
		final Field field = orders.getClass().getDeclaredField("customerID");
		field.setAccessible(true);
		assertEquals("Fields didn't match", field.get(orders), 2L);
		}

	@Test
	public void testGetProductID() {
		assertEquals(1L, orders.getProductID().longValue());
	}
	
	@Test
	public void testSetProductID() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		orders.setProductID(2L);
		final Field field = orders.getClass().getDeclaredField("productID");
		field.setAccessible(true);
		assertEquals("Fields didn't match", field.get(orders), 2L);
		}

	@Test
	public void testToString1() {
		final String expected = "Order ID = " + orders.getOrderID() + " Customer ID = " + orders.getCustomerID() + " Product ID = " + orders.getProductID();
		assertEquals(expected, orders.toString1());
	}

	@Test
	public void testEquals() {
		EqualsVerifier.simple().forClass(Customer.class).verify();
	}

}
