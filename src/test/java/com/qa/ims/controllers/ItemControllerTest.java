package com.qa.ims.controllers;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.any;

import java.util.ArrayList;
import java.util.List;

import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.controller.ItemController;
import com.qa.ims.persistence.dao.CustomerDAO;
import com.qa.ims.persistence.dao.ItemDAO;
import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.Utils;

@RunWith(MockitoJUnitRunner.class)
public class ItemControllerTest {

	@Mock
	private Utils utils;

	@Mock
	private ItemDAO dao;

	@InjectMocks
	private ItemController controller;

	@Test
	public void testReadAll() {
		List<Item> items = new ArrayList<>();
		items.add(new Item(1L, "TV", "A TV", 5.0, 5L));

		Mockito.when(dao.readAll()).thenReturn(items);

		assertEquals(items, controller.readAll());

		Mockito.verify(dao, Mockito.times(1)).readAll();
	}

	@Test
	public void testCreate() {
		final String P_NAME = "TV";
		final String P_DESC = "A TV";
		final Double P_VALUE = 5.0;
		final Long P_STOCK = 5L;
		final Item created = new Item(P_NAME, P_DESC, P_VALUE, P_STOCK);

		Mockito.when(utils.getString()).thenReturn(P_NAME, P_DESC);
		Mockito.when(utils.getDouble()).thenReturn(P_VALUE);
		Mockito.when(utils.getLong()).thenReturn(P_STOCK);
		Mockito.when(dao.create(any(Item.class))).thenReturn(created);

		assertEquals(created, controller.create());

		Mockito.verify(utils, Mockito.times(2)).getString();
		Mockito.verify(utils, Mockito.times(1)).getDouble();
		Mockito.verify(utils, Mockito.times(1)).getLong();
		Mockito.verify(dao, Mockito.times(1)).create(any(Item.class));
	}

	@Test
	public void testUpdate() {
		Item updated = new Item(1L, "TV", "A TV", 5.0, 5L);

		Mockito.when(this.utils.getLong()).thenReturn(1L);
		Mockito.when(this.utils.getString()).thenReturn(updated.getProduct_name(), updated.getProduct_description());
		Mockito.when(utils.getDouble()).thenReturn(updated.getProduct_value());
		Mockito.when(utils.getLong()).thenReturn(updated.getProduct_stockLevels());
		Mockito.when(this.dao.update(any(Item.class))).thenReturn(updated);

		assertEquals(updated, controller.update());

		Mockito.verify(this.utils, Mockito.times(2)).getLong();
		Mockito.verify(this.utils, Mockito.times(2)).getString();
		Mockito.verify(this.utils, Mockito.times(1)).getDouble();
		Mockito.verify(this.dao, Mockito.times(1)).update(any(Item.class));
	}
	
	@Test
	public void testDelete() {
		final long ID = 1L;

		Mockito.when(utils.getLong()).thenReturn(ID);
		Mockito.when(dao.delete(ID)).thenReturn(1);

		assertEquals(1L, this.controller.delete());

		Mockito.verify(utils, Mockito.times(1)).getLong();
		Mockito.verify(dao, Mockito.times(1)).delete(ID);
	}

}
