
package org.wahlzeit.model;

import java.lang.Math;

public abstract class AbstractCoordinate implements Coordinate {
	
	public double getCartesianDistance(Coordinate other)
	{
		if (other == null) {
			throw new NullPointerException("Parameter 'other' was null inside method 'getCartesianDistance'.");
		}
		
		CartesianCoordinate cartesianThis = this.asCartesianCoordinate();
		CartesianCoordinate cartesianOther = other.asCartesianCoordinate();

		double dx = cartesianOther.getX() - cartesianThis.getX();
		double dy = cartesianOther.getY() - cartesianThis.getY();
		double dz = cartesianOther.getZ() - cartesianThis.getZ();

		return Math.sqrt(dx * dx + dy * dy + dz * dz);
	}
	
	public double getCentralAngle(Coordinate other)
	{
		SphericCoordinate sphericThis = asSphericCoordinate();
		SphericCoordinate sphericOther = other.asSphericCoordinate();

		double latitude1 = Math.PI / 2. - sphericThis.getTheta();
		double latitude2 = Math.PI / 2. - sphericOther.getTheta();
		double deltaLongitude = sphericOther.getPhi() - sphericThis.getPhi();
		
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
	
	public boolean isEqual(Coordinate other)
	{
		if(other == null) return false;

		CartesianCoordinate cartesianThis = this.asCartesianCoordinate();
		CartesianCoordinate cartesianOther = other.asCartesianCoordinate();
		
		boolean equal = Math.abs(cartesianOther.getX() - cartesianThis.getX()) < 0.00001;
		equal = equal && Math.abs(cartesianOther.getY() - cartesianThis.getY()) < 0.00001;
		equal = equal && Math.abs(cartesianOther.getZ() - cartesianThis.getZ()) < 0.00001;
		
		return equal;
	}
	
	@Override
	public int hashCode() {
		CartesianCoordinate cartesianThis = this.asCartesianCoordinate();
		
	    double tmp = 71. * 71. * cartesianThis.getX() + 71. * cartesianThis.getY() + cartesianThis.getZ();
	    Double d = new Double(tmp);
		return d.hashCode();
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
