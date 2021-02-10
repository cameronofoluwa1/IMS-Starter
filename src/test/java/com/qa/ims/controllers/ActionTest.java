package com.qa.ims.controllers;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.controller.Action;
import com.qa.ims.utils.Utils;
@RunWith(MockitoJUnitRunner.class)
public class ActionTest {

	@Mock
	private Utils utils;

	@Test
	public void actionsTest() {
		Action action = Action.CREATE;
		assertEquals(action, Action.CREATE);
	}

	@Test
	public void getDescriptionTest() {
		Action action = Action.CREATE;
		assertEquals("CREATE: To save a new entity into the database", action.getDescription());
	}


}
