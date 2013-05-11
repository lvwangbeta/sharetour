package com.sharetour.dao;

import static org.junit.Assert.*;

import java.sql.Connection;
import org.junit.Test;

import com.sharetour.db.ConnectionPool;

public class UserDAOTest {
	

	@Test
	public void test() {
		Connection con = ConnectionPool.getInstance().getConnection();
		assertNotNull(con);
	}

}
