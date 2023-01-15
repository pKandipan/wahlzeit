
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
		FlowerType flowerType = mock(FlowerType.class);
		Flower flower = spy(new Flower(flowerType, "rose", "rosa"));
		FlowerPhoto photo = new FlowerPhoto(flower);
		
		doCallRealMethod().when(flower).writeOn(flowerRset);
		doCallRealMethod().when(flowerType).writeOn(flowerRset);
		
		photo.writeOn(flowerRset);		
		
		verify(flower).writeOn(flowerRset);
		verify(flowerType).writeOn(flowerRset);
		
		verify(flowerRset).updateInt("id", photo.id.asInt());
		verify(flowerRset).updateString("flower_type", photo.getFlower().getFlowerType().getTypeHierarchyAsString());
		verify(flowerRset).updateString("flower_name", photo.getFlower().getFlowerName());
		verify(flowerRset).updateString("flower_color", photo.getFlower().getFlowerColor());
	}
	
	@Test
	public void testReadFrom() throws SQLException {
		ResultSet photoRset = mock(ResultSet.class);
		ResultSet flowerRset = mock(ResultSet.class);
		FlowerPhoto photo = mock(FlowerPhoto.class);
		
		doNothing().when(photo).readFrom(any());
		doCallRealMethod().when(photo).readFrom(any(), any());
		
		// getFlowerTypeInstance will be called with following return value:
		when(flowerRset.getString(any())).thenReturn("this is a flowers type");
		
		photo.readFrom(photoRset, flowerRset);
		
		verify(flowerRset).getInt("id");
		verify(flowerRset).getString("flower_type");
		verify(flowerRset).getString("flower_name");
		verify(flowerRset).getString("flower_color");
	}
}
