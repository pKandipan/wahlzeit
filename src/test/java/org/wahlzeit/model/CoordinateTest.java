
package org.wahlzeit.model;

import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;

import java.lang.Math;

public class CoordinateTest {

	private CartesianCoordinate coordinate;

	@Before
	public void initCoordinate() {
		coordinate = CartesianCoordinate.getInstance(4., 5., 6.);
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
		
		CartesianCoordinate other = CartesianCoordinate.getInstance(4., 5., 6.);
		assertTrue(0. == coordinate.getDistance(other));

		other = CartesianCoordinate.getInstance(-6., 2., -1.);
		double dist = Math.sqrt(158.);
		assertTrue(dist == coordinate.getDistance(other));

		//assertThrows(NullPointerException.class, () -> coordinate.getDistance(null));
	}

	// cw07
	@Test(expected = NullPointerException.class)
	public void testGetDistanceNullPtrException() {
		coordinate.getDistance(null);
	}

	@Test
	public void testEquals() {
		
		assertTrue(!coordinate.equals(null));		
		assertTrue(coordinate.equals(coordinate));
		

		CartesianCoordinate other = CartesianCoordinate.getInstance(4., 5., 6.);
		assertTrue(coordinate.equals(other));

		other = CartesianCoordinate.getInstance(-6., 2., -1.);
		assertTrue(!coordinate.equals(other));

		other = CartesianCoordinate.getInstance(-6.265972, 2.894674, -1.65667);
		assertTrue(other.equals(other));
		
		CartesianCoordinate cc = CartesianCoordinate.getInstance(-1., 1., Math.sqrt(6.));
		SphericCoordinate ccAssc = cc.asSphericCoordinate();
		SphericCoordinate sc = SphericCoordinate.getInstance(3. * Math.PI / 4., Math.PI / 6., 2. * Math.sqrt(2.));
		assertTrue(ccAssc.equals(sc));
		
		assertTrue(coordinate.equals(coordinate.asCartesianCoordinate()));
		assertTrue(coordinate.equals(coordinate.asSphericCoordinate()));
	}
	
	@Test
	public void testInterpretation() {
		CartesianCoordinate other = CartesianCoordinate.getInstance(1., 2., 6.);
		
		assertTrue(coordinate.asCartesianCoordinate() instanceof CartesianCoordinate);
		assertTrue(coordinate.asSphericCoordinate() instanceof SphericCoordinate);
	}
	
	@Test
	public void testAssertClassInvariants() {
		CartesianCoordinate newCoordinate = spy(CartesianCoordinate.getInstance(56., 42., 62.));
		
		newCoordinate.getX();
		newCoordinate.getY();
		newCoordinate.getZ();
		newCoordinate.asCartesianCoordinate();
		newCoordinate.asSphericCoordinate();
		
		verify(newCoordinate, atLeast(6)).assertClassInvariants();
	}
	
	// cw07
	@Test(expected = NullPointerException.class)
	public void testAssertIsNotNull() {
		CartesianCoordinate newCoordinate = spy(CartesianCoordinate.getInstance(56., 42., 62.));
		
		newCoordinate.getCartesianDistance(null);
		verify(newCoordinate).assertIsNotNull(any(), any());
		
		newCoordinate.getCentralAngle(null);
		verify(newCoordinate).assertIsNotNull(any(), any());
	}
	
	// cw07
	@Test(expected = RuntimeException.class)
	public void testAssertValidDistance() {
		CartesianCoordinate newCoordinate = spy(CartesianCoordinate.getInstance(56., 42., 62.));
		newCoordinate.getCartesianDistance(any());

		verify(newCoordinate).assertValidDistance(any());
		newCoordinate.assertValidDistance(-5.);
	}
	
	// cw07
	@Test(expected = RuntimeException.class)
	public void testAssertValidCentralAngle() {
		CartesianCoordinate newCoordinate = spy(CartesianCoordinate.getInstance(56., 42., 62.));
		newCoordinate.getCentralAngle(any());

		verify(newCoordinate).assertValidCentralAngle(any());
		newCoordinate.assertValidCentralAngle(2. * Math.PI);
	}
	
	@Test
	public void testSharedValues() {
		int mapSize = AbstractCoordinate.coordinateValueObjects.size();
		CartesianCoordinate cCoord = spy(CartesianCoordinate.getInstance(1.2, 2.4, 6.5));
		assertTrue(AbstractCoordinate.coordinateValueObjects.size() == mapSize + 1);
		
		cCoord = spy(CartesianCoordinate.getInstance(1.2, 2.4, 6.5));
		assertTrue(AbstractCoordinate.coordinateValueObjects.size() == mapSize + 1);
		
		
		mapSize = AbstractCoordinate.coordinateValueObjects.size();
		SphericCoordinate sCoord = spy(SphericCoordinate.getInstance(0.2, 2.4, 2.5));
		assertTrue(AbstractCoordinate.coordinateValueObjects.size() == mapSize + 1);
		
		sCoord = spy(SphericCoordinate.getInstance(0.2, 2.4, 2.5));
		assertTrue(AbstractCoordinate.coordinateValueObjects.size() == mapSize + 1);
	}
}
