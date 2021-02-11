package com.qa.ims.controllers;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.controller.OrdersController;
import com.qa.ims.persistence.dao.OrderlineDAO;
import com.qa.ims.persistence.dao.OrdersDAO;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Orderline;
import com.qa.ims.persistence.domain.Orders;
import com.qa.ims.utils.Utils;
@RunWith(MockitoJUnitRunner.class)
public class OrdersControllerTest {

	@Mock
	private Utils utils;

	@Mock
	private OrdersDAO dao;

	@Mock
	private OrderlineDAO olDAO;
	
	@InjectMocks
	private OrdersController controller;


	@Test
	public void testReadAll() {
		List<Orders> orders = new ArrayList<>();
		orders.add(new Orders(1L));

		Mockito.when(dao.readAll()).thenReturn(orders);

		assertEquals(orders, controller.readAll());

		Mockito.verify(dao, Mockito.times(1)).readAll();
	}

	@Test
	public void testCreateItem() {
		final Long OCUSTID = 1L, OITEMID = 7L, OQUANT = 5L, ONI = 0L;
		final Orders created = new Orders(OCUSTID);
		
		Mockito.when(utils.getLong()).thenReturn(OCUSTID);
		Mockito.when(utils.getLong()).thenReturn(OITEMID, OQUANT);
		Mockito.when(utils.getLong()).thenReturn(ONI);
		Mockito.when(dao.create(any(Orders.class))).thenReturn(created);

		assertEquals(created, controller.create());

		Mockito.verify(utils, Mockito.times(4)).getLong();
		Mockito.verify(dao, Mockito.times(1)).create(any(Orders.class));
	}

	@Test
	public void testUpdateItem() {
		final Long OCUSTID = 1L, OITEMID = 7L, OQUANT = 5L, ONI = 0L;
		final String ONIS = "add";
		final Orders created = new Orders(OCUSTID);
		
		Mockito.when(utils.getLong()).thenReturn(OCUSTID);
		Mockito.when(utils.getString()).thenReturn(ONIS);
		Mockito.when(utils.getLong()).thenReturn(OITEMID, OQUANT);
		Mockito.when(utils.getLong()).thenReturn(ONI);

		assertNull(controller.update());

		Mockito.verify(utils, Mockito.times(3)).getLong();
		Mockito.verify(utils, Mockito.times(1)).getString();
	}

	@Test
	public void testDelete() {
		final long ID = 1L;

		Mockito.when(utils.getLong()).thenReturn(1L);
		Mockito.when(dao.delete(ID)).thenReturn(1);

		assertEquals(1L, this.controller.delete());
		
		Mockito.verify(utils, Mockito.times(1)).getLong();
		Mockito.verify(dao, Mockito.times(1)).delete(ID);
	
	}

}
