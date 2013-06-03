package com.sharetour.service;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CoverServiceTest {

	private CoverService coverService = new CoverService();
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		String content = "<img alt=\"\" src=\"/sharetour/imgs?id=51ac429756b0ae8b7714b68e\" style=\"width: 500px; height: 651px;\" />";
		System.out.println(coverService.getCover(content));
	}

}
