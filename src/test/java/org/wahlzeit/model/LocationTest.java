
package org.wahlzeit.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class LocationTest {

	private Location location;

	@Before
	public void initLocation() {
		location = new Location(new CartesianCoordinate(4., 5., 6.));
	}

	@Test
	public void testConstructor() {
		assertNotNull(location);

		// Check properties after creation
		assertNotNull(location.getCoordinate());
	}

}
