
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
		photo.location = new Location(4. ,5. ,6.);
		assertNotNull(photo.location);

		assertTrue(4. == photo.location.coordinate.getX());
		assertTrue(5. == photo.location.coordinate.getY());
		assertTrue(6. == photo.location.coordinate.getZ());
	}
}
