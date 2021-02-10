package com.qa.ims.persistence.dao;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.DBUtils;

public class ItemDAOTest {

	private final ItemDAO DAO = new ItemDAO();

	@Before
	public void setup() {
		DBUtils.connect();
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	}

	@Test
	public void testCreate() {
		final Item created = new Item(2L, "iPad", "iPad 12th Gen", 5.0, 5L);
		assertEquals(created.toString(), DAO.create(created).toString());
	}

	@Test
	public void testReadLatest() {
		assertEquals(new Item(1L, "TV", "A TV", 5d, 5L).toString(), DAO.readLatest().toString());
	}

	@Test
	public void testReadAll() {
		List<Item> expected = new ArrayList<>();
		expected.add(new Item(1L, "TV", "A TV", 5d, 5L));
		assertEquals(expected.toString(), DAO.readAll().toString());
	}
	
	@Test
	public void testRead() {
		final long ID = 1L;
		assertEquals(new Item(ID, "TV", "A TV", 5d, 5L).toString(), DAO.read(ID).toString());
	}

	@Test
	public void testUpdate() {
		final Item updated = new Item(1L, "TV", "A TV", 10d, 5L);
		assertEquals(updated.toString(), DAO.update(updated).toString());

	}
	
	@Test
	public void testDelete() {
		assertEquals(1, DAO.delete(1));
	}

}
