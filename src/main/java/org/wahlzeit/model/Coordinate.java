
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
	public double get_x()
	{
		return x;
	}

	public double get_y()
	{
		return y;
	}

	public double get_z()
	{
		return z;
	}

}
