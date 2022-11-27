
// group this class in the same package as the Photo class
package org.wahlzeit.model;

import java.lang.Math;

public class CartesianCoordinate extends AbstractCoordinate {

	// cartesian coordinate components
	private final double x;
	private final double y;
	private final double z;


	// constructor that inits x, y, and z
	public CartesianCoordinate(double x, double y, double z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		
		// class invariant
		{
			this.assertClassInvariants();
		}
	}


	// getter for private attributes
	//
	public double getX()
	{
		// class invariant
		{
			this.assertClassInvariants();
		}
		
		return x;
	}

	public double getY()
	{
		// class invariant
		{
			this.assertClassInvariants();
		}
		
		return y;
	}

	public double getZ()
	{
		// class invariant
		{
			this.assertClassInvariants();
		}
		
		return z;
	}


	// compute distance between 'this' and 'other'
	public double getDistance(CartesianCoordinate other)
	{
		return this.getCartesianDistance(other);
	}
	
	public CartesianCoordinate asCartesianCoordinate()
	{
		// class invariant
		{
			this.assertClassInvariants();
		}
	
		return this;
	}
	
	public SphericCoordinate asSphericCoordinate()
	{
		// precondition
		{
			this.assertClassInvariants();
		}
	
		double radius = Math.sqrt(x * x + y * y + z * z);
		double theta = Math.acos(z/radius);
		double phi = 0.;
		
		if(x > 0){
			phi = Math.atan(y/x);
		}
		else if(x < 0 && y >= 0){
			phi = Math.atan(y/x) + Math.PI;
		}
		else if(x < 0 && y < 0){
			phi = Math.atan(y/x) - Math.PI;
		}
		else if(x == 0 && y > 0){
			phi = Math.PI / 2.;
		}
		else if(x == 0 && y < 0){
			phi = -Math.PI / 2.;
		}
		else if(x == 0 && y == 0){
			// here exception handling from previous assignments
			throw new IllegalStateException("both x and y cannot be 0");
		}
		
		// postcondition
		{
			this.assertClassInvariants();
		}
		
		return new SphericCoordinate(phi, theta, radius);
	}
	
	protected void assertClassInvariants()
	{
		if(Double.isNaN(this.x) || Double.isNaN(this.y) || Double.isNaN(this.z))
		{
			throw new IllegalStateException("double field in CartesianCoordinate class is NaN");
		}
	}
}
