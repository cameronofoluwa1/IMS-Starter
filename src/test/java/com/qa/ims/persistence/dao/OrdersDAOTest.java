package com.qa.ims.persistence.dao;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Orders;
import com.qa.ims.utils.DBUtils;

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
public class OrdersDAOTest {

	private final OrdersDAO DAO = new OrdersDAO();

	@Before
	public void setup() {
		DBUtils.connect();
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	}

	@Test
	public void testCreate() {
		final Orders created = new Orders(1L, 1L, 1L);
		assertEquals("Order ID = 1 Customer ID = 1 Product ID = 1 order Value = £25.0", DAO.create(created).toString());
	}

	@Test
	public void testReadAll() {
		assertEquals("[Order ID = 1 Customer ID = 1 Product ID = 1 order Value = £25.0]", DAO.readAll().toString());
	}

	@Test
	public void testRead() {
		
		assertEquals("Order ID = 1 Customer ID = 1 Product ID = 1 order Value = £25.0", DAO.read(1L).toString());
	}

	@Test
	public void testDelete() {
		assertEquals(1, DAO.delete(1));
	}

}
