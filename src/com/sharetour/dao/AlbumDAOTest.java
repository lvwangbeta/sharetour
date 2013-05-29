package com.sharetour.dao;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;

public class AlbumDAOTest {

	private AlbumDAO album = new AlbumDAO();
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetAlbumsOfUser() {
		System.out.println(new Gson().toJson(album.getAlbum("51a22e527ea8a4615a1b952c")));
		
	}

}
