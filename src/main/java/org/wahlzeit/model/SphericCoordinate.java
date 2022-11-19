
// group this class in the same package as the Photo class
package org.wahlzeit.model;

import java.lang.Math;

public class SphericCoordinate extends AbstractCoordinate {

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
}
