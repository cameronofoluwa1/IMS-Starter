package com.qa.ims.persistence.domain;

import static org.junit.Assert.*;

import java.lang.reflect.Field;

import org.junit.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class OrderlineTest {

	final Orderline orderline = new Orderline(1L, 1L, 100L);
	final Orderline orderline2 = new Orderline(1L, 1L);
	
	@Test
	public void testGetOrderID() {
		assertEquals(1L, orderline.getOrderID().longValue());
	}
	
	@Test
	public void testSetOrderID() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		orderline.setOrderID(2L);
		final Field field = orderline.getClass().getDeclaredField("orderID");
		field.setAccessible(true);
		assertEquals("Fields didn't match", field.get(orderline), 2L);
		}
	
	@Test
	public void testGetItemID() {
		assertEquals(1L, orderline.getItemID().longValue());
	}
	
	@Test
	public void testSetItemID() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		orderline.setItemID(2L);
		final Field field = orderline.getClass().getDeclaredField("itemID");
		field.setAccessible(true);
		assertEquals("Fields didn't match", field.get(orderline), 2L);
		}
	
	@Test
	public void testGetOrderQuant() {
		assertEquals(100L, orderline.getOrderlineQuantity().longValue());
	}
	
	@Test
	public void testSetOrderQuant() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		orderline.setOrderlineQuantity(2L);
		final Field field = orderline.getClass().getDeclaredField("orderlineQuantity");
		field.setAccessible(true);
		assertEquals("Fields didn't match", field.get(orderline), 2L);
		}

	@Test
	public void testToString() {
		final String expected = "Order ID = " + orderline.getOrderID() + " Item ID = " + orderline.getItemID() + " Item Quantity = " + orderline.getOrderlineQuantity();
		assertEquals(expected, orderline.toString());
	}
	
	@Test
	public void testEquals() {
		EqualsVerifier.simple().forClass(Orderline.class).verify();
	}

}
