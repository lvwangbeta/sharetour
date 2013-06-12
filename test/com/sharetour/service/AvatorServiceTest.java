package com.sharetour.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sharetour.model.UserInfo;

public class AvatorServiceTest {

	private AvatorService avatorService = new AvatorService();
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetAvatorOfUser() {
		List<UserInfo> users = UserService.getPopUsers(6);
		for(UserInfo user : users) {
			System.out.println(avatorService.getAvatorOfUser(user.getId()).getAvatorId().toString());
		}
		
	}

}
