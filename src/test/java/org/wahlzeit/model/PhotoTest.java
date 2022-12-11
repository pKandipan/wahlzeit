
package org.wahlzeit.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;

public class PhotoTest {

	private Photo photo;

	@Before
	public void initPhoto() {
		photo = new Photo();
	}

	@Test
	public void testConstructor() {
		assertNotNull(photo);

		// Check properties after creation
		assertNull(photo.location);
	}

	@Test
	public void testLocationMember() {
		photo.setLocation(new Location(CartesianCoordinate.getInstance(4. ,5. ,6.)));
		assertNotNull(photo.getLocation());
		
		CartesianCoordinate cartesianCoordinate = (CartesianCoordinate)photo.getLocation().getCoordinate();

		assertTrue(4. == cartesianCoordinate.getX());
		assertTrue(5. == cartesianCoordinate.getY());
		assertTrue(6. == cartesianCoordinate.getZ());
	}
}
