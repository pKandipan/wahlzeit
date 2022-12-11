
package org.wahlzeit.model;

import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.*;
import java.util.*;

public class LocationTest {

	private Location location;

	@Before
	public void initLocation() {
		location = new Location(CartesianCoordinate.getInstance(4., 5., 6.));
	}

	@Test
	public void testConstructor() {
		assertNotNull(location);

		// Check properties after creation
		assertNotNull(location.getCoordinate());
	}
	
	// cw08
	@Test
	public void testCoordinateInterchangability() {
		location.setCoordinate(SphericCoordinate.getInstance(2., 1., 8.));
		assertNotNull(location.getCoordinate());
		assertTrue(location.getCoordinate() instanceof SphericCoordinate);
		
		location.setCoordinate(CartesianCoordinate.getInstance(4., 9., 0.));
		assertNotNull(location.getCoordinate());
		assertTrue(location.getCoordinate() instanceof CartesianCoordinate);
	}
	
	@Test
	public void testWriteOn() throws SQLException {
		CartesianCoordinate cartesianCoordinate = (CartesianCoordinate)location.getCoordinate();
		
		ResultSet rset = mock(ResultSet.class);
		location.writeOn(rset);
		
		verify(rset).updateDouble("cartesian_coordinate_x", cartesianCoordinate.getX());
		verify(rset).updateDouble("cartesian_coordinate_y", cartesianCoordinate.getY());
		verify(rset).updateDouble("cartesian_coordinate_z", cartesianCoordinate.getZ());
	}
	
	@Test
	public void testReadFrom() throws SQLException {
		ResultSet rset = mock(ResultSet.class);
		Location location = new Location(rset);
		
		verify(rset).getDouble("cartesian_coordinate_x");
		verify(rset).getDouble("cartesian_coordinate_y");
		verify(rset).getDouble("cartesian_coordinate_z");
	}

}
