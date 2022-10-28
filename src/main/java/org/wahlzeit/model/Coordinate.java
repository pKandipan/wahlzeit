
// group this class in the same package as the Photo class
package org.wahlzeit.model;


public class Coordinate {

	// coordinate components
	private final double x;
	private final double y;
	private final double z;


	// constructor that inits x, y, and z
	public Coordinate(double x, double y, double z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}


	// getter for private attributes
	//
	public double getX()
	{
		return x;
	}

	public double getY()
	{
		return y;
	}

	public double getZ()
	{
		return z;
	}

}
