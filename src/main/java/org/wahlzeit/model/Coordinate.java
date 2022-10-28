
// group this class in the same package as the Photo class
package org.wahlzeit.model;

import java.lang.Math;

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


	// compute distance between 'this' and 'other'
	public double getDistance(Coordinate other)
	{
		if (other == null) {
			throw new NullPointerException("Parameter 'other' was null inside method 'getDistance'.");
		}

		double dx = other.getX() - x;
		double dy = other.getY() - y;
		double dz = other.getZ() - z;

		return Math.sqrt(dx * dx + dy * dy + dz * dz);
	}

	// check whether 'this' and 'other' are equal
	private boolean isEqual(Coordinate other)
	{
		if(other == null) return false;

		return other.getX() == x && other.getY() == y && other.getZ() == z;
	}

	// forward 'equals' to 'isEqual'
	@Override
	public boolean equals(Object obj) {

		if (this == obj) return true;
		if (obj == null) return false;
		if (!(obj instanceof Coordinate)) return false;

		Coordinate other = (Coordinate) obj;
		return isEqual(other);
	}
}
