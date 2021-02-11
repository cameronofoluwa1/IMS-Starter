package com.qa.ims.persistence.dao;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.DBUtils;

public class ItemDAOFailTest {

	private final ItemDAO DAO = new ItemDAO();

	@Before
	public void setup() {
		DBUtils.connect("fail");
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	}

	@Test
	public void testCreate() {
		final Item created = new Item(2L, "iPad", "iPad 12th Gen", 5.0, 5L);
		assertNull(DAO.create(created));
	}
	
	@Test
	public void testRead() {
		final long ID = 1L;
		assertNull(DAO.read(ID));
	}

	@Test
	public void testUpdate() {
		final Item updated = new Item(1L, "TV", "A TV", 10d, 5L);
		assertNull(DAO.update(updated));
	}

	@Test
	public void testReadAll() {
		List<Item> expected = new ArrayList<>();
		expected.add(new Item(1L, "TV", "A TV", 5d, 5L));
		assertEquals("[]", DAO.readAll().toString());
	}

}
