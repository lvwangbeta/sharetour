package com.sharetour.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sharetour.model.UserInfo;

public class UserDAOTest {

	private UserDAO userDao = new UserDAO();
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetUsersOrderByPostCount() {
		List<UserInfo> users = userDao.getUsersOrderByPostCount(6);
		for(UserInfo user: users) {
			System.out.println(user.getId());
		}
	}

}
