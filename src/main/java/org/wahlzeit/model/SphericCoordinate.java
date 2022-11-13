
// group this class in the same package as the Photo class
package org.wahlzeit.model;

import java.lang.Math;

public class SphericCoordinate implements Coordinate {

	// spheric coordinate components
	// (whether phi is the azimuth or inclination depends on who you ask)
	private final double phi; // azimuth
	private final double theta; // inclination
	private final double radius;


	// constructor that inits phi, theta, and radius
	public SphericCoordinate(double phi, double theta, double radius)
	{
		if(radius < 0
		|| theta < 0 || theta > Math.PI
		|| phi < 0 || phi >= 2. * Math.PI){
			throw new IllegalArgumentException("Spheric Coordinate: phi[0, 2 * PI), theta[0, PI], radius [0, inf)");
		}
	
		this.phi = phi;
		this.theta = theta;
		this.radius = radius;
	}


	// getter for private attributes
	//
	public double getPhi()
	{
		return phi;
	}

	public double getTheta()
	{
		return theta;
	}

	public double getRadius()
	{
		return radius;
	}


	// compute arc length between 'this' and 'other'
	public double getArcLength(SphericCoordinate other)
	{
		if (other == null) {
			throw new NullPointerException("Parameter 'other' was null inside method 'getDistance'.");
		}

		return radius * getCentralAngle(this);
	}

	// check whether 'this' and 'other' are equal
	public boolean isEqual(Coordinate other)
	{
		if(other == null) return false;

		SphericCoordinate sphericOther = other.asSphericCoordinate();
		
		boolean equal = Math.abs(sphericOther.getPhi() - phi) < 0.00001;
		equal = equal && Math.abs(sphericOther.getTheta() - theta) < 0.00001;
		equal = equal && Math.abs(sphericOther.getRadius() - radius) < 0.00001;
		
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
	    double tmp = 71. * 71. * phi + 71. * theta + radius;
	    Double d = new Double(tmp);
		return d.hashCode();
	}
	
	public CartesianCoordinate asCartesianCoordinate()
	{
		double x = radius * Math.sin(theta) * Math.cos(phi);
		double y = radius * Math.sin(theta) * Math.sin(phi);
		double z = radius * Math.cos(theta);
		
		return new CartesianCoordinate(x, y, z);
	}
	
	public SphericCoordinate asSphericCoordinate()
	{
		return this;
	}
	
	public double getCartesianDistance(Coordinate other)
	{
		CartesianCoordinate cartesianThis = this.asCartesianCoordinate();
		return cartesianThis.getCartesianDistance(other);
	}
	
	public double getCentralAngle(Coordinate other)
	{
		SphericCoordinate sphericOther = other.asSphericCoordinate();
		double latitude1 = Math.PI / 2. - this.getTheta();
		double latitude2 = Math.PI / 2. - sphericOther.getTheta();
		double deltaLongitude = sphericOther.getPhi() - this.getPhi();
		
		// Vincenty formula
		double summand1 = Math.cos(latitude2) * Math.sin(deltaLongitude);
		summand1 *= summand1;
		
		double summand2 = Math.cos(latitude1) * Math.sin(latitude2)
			- Math.sin(latitude1) * Math.cos(latitude2) * Math.cos(deltaLongitude);
		summand2 *= summand2;
		
		double numerator = Math.sqrt(summand1 + summand2);
		double denominator = Math.sin(latitude1) * Math.sin(latitude2)
			+ Math.cos(latitude1) * Math.cos(latitude2) * Math.cos(deltaLongitude);
			
		return Math.atan(numerator / denominator);
	}
}
