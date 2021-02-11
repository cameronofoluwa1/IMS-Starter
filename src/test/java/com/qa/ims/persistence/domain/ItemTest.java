package com.qa.ims.persistence.domain;

import static org.junit.Assert.*;

import org.junit.Test;

public class ItemTest {

	final Item item = new Item(1L, "TV", "A TV", 5.0, 100L);
	final Item item1 = new Item("TV", "A TV", 5.0, 100L);
	final Item item3 = new Item(1L, "TV", "A TV");
	final Item item4 = new Item("TV", "A TV");
	
	@Test
	public void testGetProductID() {
		assertEquals(1L, item.getproductID().longValue());
	}

	@Test
	public void testGetProductName() {
		assertEquals("TV", item.getProductName());
	}

	@Test
	public void testGetProductDescription() {
		assertEquals("A TV", item.getProductDescription());
	}

	@Test
	public void testGetProductValue() {
		assertEquals(5.0, item.getProductValue().doubleValue(), 0);
	}

	@Test
	public void testGetProductStockLevels() {
		assertEquals(100L, item.getProductStockLevels().longValue());
	}

	@Test
	public void testToString() {
		final String expected = "ID = " + item.getproductID() + " Product name = " + item.getProductName() + " Product Description = " + item.getProductDescription()+ " Product Value = £" + item.getProductValue() + " Product Stock Levels = " + item.getProductStockLevels();
		assertEquals(expected, item.toString());
	}

}
