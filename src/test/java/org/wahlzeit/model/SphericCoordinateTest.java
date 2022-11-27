
package org.wahlzeit.model;

import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;

import java.lang.Math;

public class SphericCoordinateTest {

	private SphericCoordinate coordinate;

	@Before
	public void initCoordinate() {
		coordinate = new SphericCoordinate(1., 2., 6.);
	}

	@Test
	public void testConstructor() {
		assertNotNull(coordinate);

		// Check properties after creation
		assertTrue(1. == coordinate.getPhi());
		assertTrue(2. == coordinate.getTheta());
		assertTrue(6. == coordinate.getRadius());
	}

	@Test
	public void testGetCartesianDistance() {
		
		SphericCoordinate other = new SphericCoordinate(2.12, 3., 8.);
		
		double r1 = coordinate.getRadius();
		double r2 = other.getRadius();
		double phi1 = coordinate.getTheta();
		double phi2 = other.getTheta();
		double theta1 = coordinate.getPhi();
		double theta2 = other.getPhi();
		
		double tmp = 2.*r1*r2;
		tmp *= (Math.sin(phi1)*Math.sin(phi2)*Math.cos(theta1-theta2)+Math.cos(phi1)*Math.cos(phi2));
		tmp = r1*r1 + r2*r2 - tmp;
		
		double dist = Math.sqrt(tmp);
		assertTrue(Math.abs(dist - coordinate.getCartesianDistance(other)) < 0.00001);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testArgsValueRange() {
		SphericCoordinate other = new SphericCoordinate(4., 5., 6.);
	}

	@Test
	public void testEquals() {
		
		assertTrue(!coordinate.equals(null));		
		assertTrue(coordinate.equals(coordinate));
		

		SphericCoordinate other = new SphericCoordinate(1., 2., 6.);
		assertTrue(coordinate.equals(other));

		other = new SphericCoordinate(3., 2., 1.);
		assertTrue(!coordinate.equals(other));

		other = new SphericCoordinate(1.265972, 2.894674, 8.65667);
		assertTrue(other.equals(other));
		
		SphericCoordinate sc = new SphericCoordinate(Math.PI/3., Math.PI/6., 8.);
		CartesianCoordinate scAscc = sc.asCartesianCoordinate();
		CartesianCoordinate cc = new CartesianCoordinate(2., 2.*Math.sqrt(3.), 4.*Math.sqrt(3.));
		assertTrue(scAscc.equals(cc));
		
		assertTrue(coordinate.equals(coordinate.asSphericCoordinate()));
		assertTrue(coordinate.equals(coordinate.asCartesianCoordinate()));
	}
	
	@Test
	public void testInterpretation() {
		SphericCoordinate other = new SphericCoordinate(1., 2., 6.);
		
		assertTrue(coordinate.asSphericCoordinate() instanceof SphericCoordinate);
		assertTrue(coordinate.asCartesianCoordinate() instanceof CartesianCoordinate);
	}
	
	@Test
	public void testCentralAngle() {
		assertTrue(coordinate.getCentralAngle(coordinate) < 0.00001);
	}
	
	@Test
	public void testAssertClassInvariants() {
		SphericCoordinate newCoordinate = spy(new SphericCoordinate(1., 2., 2.5));
		
		newCoordinate.getPhi();
		newCoordinate.getTheta();
		newCoordinate.getRadius();
		newCoordinate.asCartesianCoordinate();
		newCoordinate.asSphericCoordinate();
		
		verify(newCoordinate, atLeast(6)).assertClassInvariants();
		verify(newCoordinate, atLeast(6)).assertInValueRange();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testAssertInValueRange() {
		SphericCoordinate other = new SphericCoordinate(40., 50., 6.);
	}
}
