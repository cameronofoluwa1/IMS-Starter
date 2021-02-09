package com.qa.ims.controllers;

import static org.junit.Assert.*;

import org.junit.Test;
import org.mockito.Mock;

import com.qa.ims.controller.Action;
import com.qa.ims.utils.Utils;

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

	@Test
	public void printActionsTest() {
		Action action = Action.CREATE;
		assertEquals("Invalid selection please try again", Action.getAction(utils));
	}


}
