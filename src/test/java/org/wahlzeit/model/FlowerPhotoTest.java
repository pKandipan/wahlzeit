
package org.wahlzeit.model;

import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;

import java.sql.*;
import java.util.*;


public class FlowerPhotoTest {

	@Test
	public void testConstructor() throws SQLException {
		Photo photo = new FlowerPhoto();
		
		assertNotNull(photo);
		assertTrue(photo instanceof FlowerPhoto);
	}
	
	@Test
	public void testWriteOn() throws SQLException {
		ResultSet flowerRset = mock(ResultSet.class);
		FlowerPhoto photo = new FlowerPhoto();
		
		photo.writeOn(flowerRset);
		
		verify(flowerRset).updateInt("id", photo.id.asInt());
		verify(flowerRset).updateString("flower_name", photo.flowerName);
		verify(flowerRset).updateString("flower_color", photo.flowerColor);
	}
	
	@Test
	public void testReadFrom() throws SQLException {
		ResultSet photoRset = mock(ResultSet.class);
		ResultSet flowerRset = mock(ResultSet.class);
		FlowerPhoto photo = mock(FlowerPhoto.class);
		
		doNothing().when(photo).readFrom(any());
		doCallRealMethod().when(photo).readFrom(any(), any());
		
		photo.readFrom(photoRset, flowerRset);
		
		verify(flowerRset).getString("flower_name");
		verify(flowerRset).getString("flower_color");
	}
}
