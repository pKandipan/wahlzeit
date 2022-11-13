
// group this class in the same package as the Photo class
package org.wahlzeit.model;

public interface Coordinate {


	public CartesianCoordinate asCartesianCoordinate();
	
	public SphericCoordinate asSphericCoordinate();

	public double getCartesianDistance(Coordinate other);
	
	public double getCentralAngle(Coordinate other);

	public boolean isEqual(Coordinate other);
}
