
// group this class in the same package as the Photo class
package org.wahlzeit.model;

import java.lang.Math;

public class CartesianCoordinate implements Coordinate {

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
	public double getDistance(CartesianCoordinate other)
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
	public boolean isEqual(Coordinate other)
	{
		if(other == null) return false;

		CartesianCoordinate cartesianOther = other.asCartesianCoordinate();
		
		boolean equal = Math.abs(cartesianOther.getX() - x) < 0.00001;
		equal = equal && Math.abs(cartesianOther.getY() - y) < 0.00001;
		equal = equal && Math.abs(cartesianOther.getZ() - z) < 0.00001;
		
		return equal;
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
	
	@Override
	public int hashCode() {
	    double tmp = 71. * 71. * x + 71. * y + z;
	    Double d = new Double(tmp);
		return d.hashCode();
	}
	
	public CartesianCoordinate asCartesianCoordinate()
	{
		return this;
	}
	
	public SphericCoordinate asSphericCoordinate()
	{
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
			throw new IllegalStateException("both x and y cannot be 0");
		}
		
		return new SphericCoordinate(phi, theta, radius);
	}
	
	public double getCartesianDistance(Coordinate other)
	{
		CartesianCoordinate cartesianOther = other.asCartesianCoordinate();
		return getDistance(cartesianOther);
	}
	
	public double getCentralAngle(Coordinate other)
	{
		SphericCoordinate sphericThis = asSphericCoordinate();
		return sphericThis.getCentralAngle(other);
	}
}
