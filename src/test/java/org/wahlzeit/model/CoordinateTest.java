
package org.wahlzeit.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;

import java.lang.Math;

public class CoordinateTest {

	private CartesianCoordinate coordinate;

	@Before
	public void initCoordinate() {
		coordinate = new CartesianCoordinate(4., 5., 6.);
	}

	@Test
	public void testConstructor() {
		assertNotNull(coordinate);

		// Check properties after creation
		assertTrue(4. == coordinate.getX());
		assertTrue(5. == coordinate.getY());
		assertTrue(6. == coordinate.getZ());
	}

	@Test
	public void testGetDistance() {
		
		CartesianCoordinate other = new CartesianCoordinate(4., 5., 6.);
		assertTrue(0. == coordinate.getDistance(other));

		other = new CartesianCoordinate(-6., 2., -1.);
		double dist = Math.sqrt(158.);
		assertTrue(dist == coordinate.getDistance(other));

		//assertThrows(NullPointerException.class, () -> coordinate.getDistance(null));
	}

	@Test(expected = NullPointerException.class)
	public void testGetDistanceNullPtrException() {
		coordinate.getDistance(null);
	}

	@Test
	public void testEquals() {
		
		assertTrue(!coordinate.equals(null));		
		assertTrue(coordinate.equals(coordinate));
		

		CartesianCoordinate other = new CartesianCoordinate(4., 5., 6.);
		assertTrue(coordinate.equals(other));

		other = new CartesianCoordinate(-6., 2., -1.);
		assertTrue(!coordinate.equals(other));

		other = new CartesianCoordinate(-6.265972, 2.894674, -1.65667);
		assertTrue(other.equals(other));
		
		CartesianCoordinate cc = new CartesianCoordinate(-1., 1., Math.sqrt(6.));
		SphericCoordinate ccAssc = cc.asSphericCoordinate();
		SphericCoordinate sc = new SphericCoordinate(3. * Math.PI / 4., Math.PI / 6., 2. * Math.sqrt(2.));
		assertTrue(ccAssc.equals(sc));
		
		assertTrue(coordinate.equals(coordinate.asCartesianCoordinate()));
		assertTrue(coordinate.equals(coordinate.asSphericCoordinate()));
	}
	
	@Test
	public void testInterpretation() {
		CartesianCoordinate other = new CartesianCoordinate(1., 2., 6.);
		
		assertTrue(coordinate.asCartesianCoordinate() instanceof CartesianCoordinate);
		assertTrue(coordinate.asSphericCoordinate() instanceof SphericCoordinate);
	}
}
