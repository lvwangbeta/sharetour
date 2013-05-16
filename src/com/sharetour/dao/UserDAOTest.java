package com.sharetour.dao;

import static org.junit.Assert.*;

import java.sql.Connection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sharetour.db.ConnectionPool;

public class UserDAOTest {
	private static UserDAO user;
	@Before
	public void setUp() throws Exception {
		user = new UserDAO();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		System.out.println(user.getAuthorid("gavin"));
	}

}
