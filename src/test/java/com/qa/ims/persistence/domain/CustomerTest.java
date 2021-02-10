package com.qa.ims.persistence.domain;

import static org.junit.Assert.assertEquals;


import org.junit.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class CustomerTest {
	
	private final Customer cust = new Customer(1L, "Cameron", "Ofoluwa");

	@Test
	public void testGetID() {
		assertEquals(1L, cust.getID().longValue());
	}

	@Test
	public void testGetFirstName() {
		assertEquals("Cameron", cust.getFirstName());
	}

	@Test
	public void testGetSurname() {
		assertEquals("Ofoluwa", cust.getSurname());
	}

	@Test
	public void testToString() {
		final String expected = "ID = " + cust.getID() + " First name = " + cust.getFirstName() + " Surname = " + cust.getSurname();
		assertEquals(expected, cust.toString());
	}

	@Test
	public void testEquals() {
		EqualsVerifier.simple().forClass(Customer.class).verify();
	}

}
