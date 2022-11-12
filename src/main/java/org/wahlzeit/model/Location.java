
// group this class in the same package as the Photo class
package org.wahlzeit.model;

import java.sql.*;
import java.net.*;

public class Location {

	protected Coordinate coordinate;


	// constructor that takes a coordinate and assigns it to the 'cooridianate' field
	public Location(Coordinate coordinate)
	{
		// this.coordinate gets initialized with new Coordinate object to ensure multiplicity
	
		if(coordinate instanceof CartesianCoordinate)
		{
			CartesianCoordinate cartesianCoordinate = (CartesianCoordinate)coordinate;
			this.coordinate = new CartesianCoordinate(cartesianCoordinate.getX(), cartesianCoordinate.getY(), cartesianCoordinate.getZ());
		}
		
		else if(coordinate instanceof SphericCoordinate)
		{
			SphericCoordinate sphericCoordinate = coordinate.asSphericCoordinate();
			this.coordinate = new SphericCoordinate(sphericCoordinate.getPhi(), sphericCoordinate.getTheta(), sphericCoordinate.getRadius());
		}
	}
	
	// constructor that takes a ResultSet to read from
	public Location(ResultSet rset) throws SQLException
	{
		readFrom(rset);
	}
	
	/**
	 * 
	 */
	public void readFrom(ResultSet rset) throws SQLException {
		double x = rset.getDouble("cartesian_coordinate_x");
		double y = rset.getDouble("cartesian_coordinate_y");
		double z = rset.getDouble("cartesian_coordinate_z");
		coordinate = new CartesianCoordinate(x, y, z);
	}
	
	/**
	 * 
	 */
	public void writeOn(ResultSet rset) throws SQLException {
		if(coordinate != null)
		{
			if(coordinate instanceof CartesianCoordinate)
			{
				CartesianCoordinate cartesianCoordinate = (CartesianCoordinate)coordinate;
				rset.updateDouble("cartesian_coordinate_x", cartesianCoordinate.getX());
				rset.updateDouble("cartesian_coordinate_y", cartesianCoordinate.getY());
				rset.updateDouble("cartesian_coordinate_z", cartesianCoordinate.getZ());
			}
			else if(coordinate instanceof SphericCoordinate)
			{
				CartesianCoordinate cartesianCoordinate = coordinate.asCartesianCoordinate();
				rset.updateDouble("cartesian_coordinate_x", cartesianCoordinate.getX());
				rset.updateDouble("cartesian_coordinate_y", cartesianCoordinate.getY());
				rset.updateDouble("cartesian_coordinate_z", cartesianCoordinate.getZ());
			}
		}
	}
	
	/**
	 * 
	 * @methodtype get
	 */
	public Coordinate getCoordinate()
	{
		return coordinate;
	}
	
	/**
	 * 
	 * @methodtype set
	 */
	public void setCoordinate(Coordinate newCoordinate)
	{
		coordinate = newCoordinate;
	}

}
