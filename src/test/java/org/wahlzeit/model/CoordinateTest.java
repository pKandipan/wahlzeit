
package org.wahlzeit.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;

public class CoordinateTest {

	private Coordinate coordinate;

	@Before
	public void initCoordinate() {
		coordinate = new Coordinate(4., 5., 6.);
	}

	@Test
	public void testConstructor() {
		assertNotNull(coordinate);

		// Check properties after creation
		assertTrue(4. == coordinate.getX());
		assertTrue(5. == coordinate.getY());
		assertTrue(6. == coordinate.getZ());
	}

}
