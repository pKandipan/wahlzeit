
package org.wahlzeit.model;

import java.lang.Math;
import java.util.HashMap;
import java.util.LinkedList;

public abstract class AbstractCoordinate implements Coordinate {
	
	// key: hashCode of CartesianCoordinate
	// value: list of Coordinate interpretations
	protected static HashMap<Integer, LinkedList<Coordinate>> coordinateValueObjects = new HashMap<>();
	
	// [step 18]
	protected static Coordinate getInstance(Coordinate coordinate)
	{
		int hc = coordinate.hashCode(); // is always hashCode the cartesian representation
		LinkedList<Coordinate> coordinateTypes = coordinateValueObjects.get(hc);
		if(coordinateTypes == null)
		{
			coordinateTypes = new LinkedList<Coordinate>();
			coordinateTypes.add(coordinate);
			
			coordinateValueObjects.put(hc, coordinateTypes);
		}
		else
		{
			for(Coordinate type : coordinateTypes)
			{
				if(type.getClass() == coordinate.getClass())
				{
					return type;
				}
			}
			
			coordinateTypes.add(coordinate);
		}
		return coordinate;
	}
	
	public double getCartesianDistance(Coordinate other)
	{
		// precondition
		{
			assertIsNotNull(other, "Parameter 'other' was null inside method 'getCartesianDistance'.");
			this.assertClassInvariants();
			((AbstractCoordinate)other).assertClassInvariants();
		}
		
		CartesianCoordinate cartesianThis = this.asCartesianCoordinate();
		CartesianCoordinate cartesianOther = other.asCartesianCoordinate();

		double dx = cartesianOther.getX() - cartesianThis.getX();
		double dy = cartesianOther.getY() - cartesianThis.getY();
		double dz = cartesianOther.getZ() - cartesianThis.getZ();

		double dist = Math.sqrt(dx * dx + dy * dy + dz * dz);
		
		// cw07
		if(Double.isNaN(dist))
		{
			throw new RuntimeException("argument of Math.sqrt was NaN or negative");
		}

		// postcondition
		{
			assertIsNotNull(other, "Parameter 'other' was null inside method 'getCartesianDistance'.");
			this.assertClassInvariants();
			((AbstractCoordinate)other).assertClassInvariants();
			assertValidDistance(dist);
		}

		return dist;
	}
	
	public double getCentralAngle(Coordinate other)
	{
		// precondition
		{
			assertIsNotNull(other, "Parameter 'other' was null inside method 'getCentralAngle'.");
			this.assertClassInvariants();
			((AbstractCoordinate)other).assertClassInvariants();		
		}
	
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
			
		double centralAngle = Math.atan(numerator / denominator);
		
		// postcondition
		{
			assertIsNotNull(other, "Parameter 'other' was null inside method 'getCentralAngle'.");
			this.assertClassInvariants();
			((AbstractCoordinate)other).assertClassInvariants();
			assertValidCentralAngle(centralAngle);		
		}
		
		return centralAngle;
	}
	
	// cw08
	public boolean isEqual(Coordinate other)
	{
		if(other == null) return false;
		
		// precondition
		{
			this.assertClassInvariants();
			((AbstractCoordinate)other).assertClassInvariants();
		}

		CartesianCoordinate cartesianThis = this.asCartesianCoordinate();
		CartesianCoordinate cartesianOther = other.asCartesianCoordinate();
		
		boolean equal = Math.abs(cartesianOther.getX() - cartesianThis.getX()) < 0.00001;
		equal = equal && Math.abs(cartesianOther.getY() - cartesianThis.getY()) < 0.00001;
		equal = equal && Math.abs(cartesianOther.getZ() - cartesianThis.getZ()) < 0.00001;
		
		// postcondition
		{
			assertIsNotNull(other, "other became 'null' in isEqual");
			this.assertClassInvariants();
			((AbstractCoordinate)other).assertClassInvariants();
		}
		
		return equal;
	}
	
	// cw08
	@Override
	public int hashCode() {
		CartesianCoordinate cartesianThis = this.asCartesianCoordinate();

		double tmp = 71. * 71. * cartesianThis.getX() + 71. * cartesianThis.getY() + cartesianThis.getZ();
		Double d = new Double(tmp);
		return d.hashCode();
	}
	
	// cw08
	// forward 'equals' to 'isEqual'
	@Override
	public boolean equals(Object obj) {

		if (this == obj) return true;
		if (obj == null) return false;
		if (!(obj instanceof Coordinate)) return false;

		Coordinate other = (Coordinate) obj;
		return isEqual(other);
	}
	
	protected abstract void assertClassInvariants();
	
	// cw07
	protected void assertIsNotNull(Object ob, String msg)
	{
		if (ob == null) 
		{
			throw new NullPointerException(msg);
		}
	}
	
	// cw07
	protected void assertValidDistance(double dist)
	{
		if (dist < 0)
		{
			throw new RuntimeException("distance must be >= 0");
		}
	}
	
	// cw07
	protected void assertValidCentralAngle(double centralAngle)
	{
		if (centralAngle < 0. || centralAngle >= 2. * Math.PI)
		{
			throw new RuntimeException("value range of central angle must be [0, 2*PI)");
		}
	}
}
