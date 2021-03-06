package com.qa.ims.persistence.dao;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.persistence.domain.Orderline;
import com.qa.ims.utils.DBUtils;

@RunWith(MockitoJUnitRunner.class)
public class OrderlineDAOFailTest {

	private final OrderlineDAO DAO = new OrderlineDAO();

	@Before
	public void setup() {
		DBUtils.connect("fail");
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	}
	
	@Test
	public void testCreate() {
		final Long orderID = 1L;
		final Long itemID = 1L;
		//final Long orderQuant = 1L;
		final Orderline created = new Orderline(orderID, itemID);
		assertNull(DAO.create(created));
	}

	@Test
	public void testReadAll() {
		assertEquals(null, DAO.readAll());
	}

	@Test
	public void testRead() {
		assertNull(DAO.read(1L));
	}

	@Test
	public void testUpdate() {
		final Orderline updated = new Orderline(1L, 5L, 5L);
		assertNull(DAO.update(updated));
	}

	@Test
	public void testDelete() {
		assertEquals(0, DAO.delete(1));
	}

}
