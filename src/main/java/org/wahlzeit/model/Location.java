
// group this class in the same package as the Photo class
package org.wahlzeit.model;

import java.sql.*;
import java.net.*;

public class Location {

	protected Coordinate coordinate;


	// constructor that takes a coordinate and assigns it to the 'cooridianate' field
	public Location(Coordinate coordinate)
	{
		assertIsNotNull(coordinate, "Parameter 'coordinate' was null inside method Location constructor.");
	
		this.coordinate = coordinate;
	}
	
	// constructor that takes a ResultSet to read from
	// [step 13]
	public Location(ResultSet rset) throws SQLException
	{
		assertIsNotNull(rset, "Parameter 'rset' was null inside Location constructor.");
		
		// [step 14 - 18]
		readFrom(rset);
		assertClassInvariants();
	}
	
	/**
	 * [step 14]
	 */
	public void readFrom(ResultSet rset) throws SQLException {
		assertIsNotNull(rset, "Parameter 'rset' was null inside 'readFrom'.");
	
		// cw07
		try {
			double x = rset.getDouble("cartesian_coordinate_x");
			double y = rset.getDouble("cartesian_coordinate_y");
			double z = rset.getDouble("cartesian_coordinate_z");
			
			// [step 15 - 18]
			coordinate = CartesianCoordinate.getInstance(x, y, z);
		} catch(SQLException e) {
			throw e;
		}
	}
	
	/**
	 * 
	 */
	public void writeOn(ResultSet rset) throws SQLException {
		assertClassInvariants();
		assertIsNotNull(rset, "Parameter 'rset' was null inside 'writeOn'.");
	
		CartesianCoordinate cartesianCoordinate = coordinate.asCartesianCoordinate();
		
		// cw07
		try {
			rset.updateDouble("cartesian_coordinate_x", cartesianCoordinate.getX());
			rset.updateDouble("cartesian_coordinate_y", cartesianCoordinate.getY());
			rset.updateDouble("cartesian_coordinate_z", cartesianCoordinate.getZ());
		} catch(SQLException e) {
			throw e;
		}
	}
	
	/**
	 * 
	 * @methodtype get
	 */
	public Coordinate getCoordinate()
	{
		assertClassInvariants();
		return coordinate;
	}
	
	/**
	 * 
	 * @methodtype set
	 */
	public void setCoordinate(Coordinate newCoordinate)
	{
		assertIsNotNull(newCoordinate, "Parameter 'newCoordinate' was null inside method 'setCoordinate'.");
		coordinate = newCoordinate;
	}
	
	// cw07
	protected void assertIsNotNull(Object ob, String msg)
	{
		if (ob == null) {
			throw new NullPointerException(msg);
		}
	}

	// cw07
	protected void assertClassInvariants()
	{
		if(coordinate == null)
		{
			throw new IllegalStateException("coordinate in Location class is null");
		}
	}
}
