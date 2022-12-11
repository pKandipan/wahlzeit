
// group this class in the same package as the Photo class
package org.wahlzeit.model;

import java.lang.Math;

public class SphericCoordinate extends AbstractCoordinate {

	// cw08
	// spheric coordinate components
	// (whether phi is the azimuth or inclination depends on who you ask)
	private final double phi; // azimuth
	private final double theta; // inclination
	private final double radius;


	public static SphericCoordinate getInstance(double phi, double theta, double radius)
	{
		return getInstance(new SphericCoordinate(phi, theta, radius)).asSphericCoordinate();
	}

	// constructor that inits phi, theta, and radius
	private SphericCoordinate(double phi, double theta, double radius)
	{
		assertInValueRange(phi, theta, radius);
	
		this.phi = phi;
		this.theta = theta;
		this.radius = radius;
		
		// class invariant
		{
			this.assertClassInvariants();
		}
	}


	// getter for private attributes
	//
	public double getPhi()
	{
		// class invariant
		{
			this.assertClassInvariants();
		}
		
		return phi;
	}

	public double getTheta()
	{
		// class invariant
		{
			this.assertClassInvariants();
		}
		
		return theta;
	}

	public double getRadius()
	{
		// class invariant
		{
			this.assertClassInvariants();
		}
		
		return radius;
	}

	// compute arc length between 'this' and 'other'
	public double getArcLength(SphericCoordinate other)
	{
		// precondition
		{
			assertIsNotNull(other, "Parameter 'other' was null inside method 'getArcLength'.");
			this.assertClassInvariants();
			other.assertClassInvariants();
		}

		double arcLength = radius * getCentralAngle(this);

		// postcondition
		{
			assertIsNotNull(other, "Parameter 'other' was null inside method 'getArcLength'.");
			this.assertClassInvariants();
			other.assertClassInvariants();
			assertValidDistance(arcLength);
		}

		return arcLength;
	}
	
	public CartesianCoordinate asCartesianCoordinate()
	{
		// precondition
		{
			this.assertClassInvariants();
		}
	
		double x = radius * Math.sin(theta) * Math.cos(phi);
		double y = radius * Math.sin(theta) * Math.sin(phi);
		double z = radius * Math.cos(theta);
		
		// postcondition
		{
			this.assertClassInvariants();
		}
		
		return CartesianCoordinate.getInstance(x, y, z);
	}
	
	public SphericCoordinate asSphericCoordinate()
	{
		// class invariant
		{
			this.assertClassInvariants();
		}
	
		return this;
	}
	
	protected boolean isInValueRange(double phi, double theta, double radius)
	{
		return !(radius < 0
		|| theta < 0 || theta > Math.PI
		|| phi < 0 || phi >= 2. * Math.PI);
	}
	
	// cw07
	protected void assertInValueRange()
	{
		if(!isInValueRange(this.phi, this.theta, this.radius)){
			throw new IllegalStateException("Spheric Coordinate: phi[0, 2 * PI), theta[0, PI], radius [0, inf)");
		}
	}
	
	// cw07
	protected void assertInValueRange(double phi, double theta, double radius)
	{
		if(!isInValueRange(phi, theta, radius)){
			throw new IllegalArgumentException("Spheric Coordinate: phi[0, 2 * PI), theta[0, PI], radius [0, inf)");
		}
	}
	
	// cw07
	protected void assertNotInIllegalState()
	{
		if(Double.isNaN(this.phi) || Double.isNaN(this.theta) || Double.isNaN(this.radius))
		{
			throw new IllegalStateException("double field in CartesianCoordinate class is NaN");
		}
	}
	
	protected void assertClassInvariants()
	{
		assertNotInIllegalState();
		assertInValueRange();
	}
}
