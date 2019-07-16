package com.cucumber.api.stepdefs;

import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks {

	@Before
	public void setUp() {
		System.out.println("############## Running Before Mehtod ############## \n");
	}

	@After
	public void tearDown() {
		System.out.println("############## Running After Mehtod ############### \n");
	}
}
