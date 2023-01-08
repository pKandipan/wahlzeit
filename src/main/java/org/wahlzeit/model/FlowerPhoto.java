
package org.wahlzeit.model;

import java.sql.*;
import java.net.*;

import org.wahlzeit.services.*;
import org.wahlzeit.utils.*;

/**
 * A flower photo represents a user-provided (uploaded) photo.
 */
@PatternInstance(
	type = "Creational",
	patternName = "Abstract Factory",
	participants = {"Concrete Product"}
)
public class FlowerPhoto extends Photo {

	protected String flowerName;
	protected String flowerColor;

	/**
	 * 
	 */
	public FlowerPhoto() {
		id = PhotoId.getNextId();
		incWriteCount();
	}
	
	/**
	 * 
	 * @methodtype constructor
	 */
	public FlowerPhoto(PhotoId myId) {
		assertIsNotNull(myId, "Parameter 'myId' was null inside FlowerPhoto constructor");
	
		id = myId;
		
		incWriteCount();
	}
	
	/**
	 * 
	 * @methodtype constructor
	 */
	public FlowerPhoto(ResultSet photoRset) throws SQLException {
		assertIsNotNull(photoRset, "Parameter 'photoRset' was null inside FlowerPhoto constructor.");
		readFrom(photoRset);
	}
	
	/**
	 * 
	 * @methodtype constructor
	 */
	public FlowerPhoto(ResultSet photoRset, ResultSet flowerRset) throws SQLException {
		readFrom(photoRset, flowerRset);
	}

	/**
	 * 
	 */
	public void readFrom(ResultSet photoRset, ResultSet flowerRset) throws SQLException {
		assertIsNotNull(photoRset, "Parameter 'photoRset' was null inside 'readFrom'.");
		assertIsNotNull(flowerRset, "Parameter 'flowerRset' was null inside 'readFrom'.");
	
		// cw07
		try {
			readFrom(photoRset);
		
			flowerName = flowerRset.getString("flower_name");
			flowerColor = flowerRset.getString("flower_color");
		} catch(SQLException e) {
			throw e;
		}
	}
	
	/**
	 * 
	 */
	@Override
	public void writeOn(ResultSet rset) throws SQLException {
		assertIsNotNull(rset, "Parameter 'rset' was null inside 'writeOn'.");
	
		// cw07
		try {
			rset.updateInt("id", id.asInt());
			rset.updateString("flower_name", flowerName);
			rset.updateString("flower_color", flowerColor);
		} catch(SQLException e) {
			throw e;
		}
	}


	/**
	 * 
	 * @methodtype get
	 */
	public String getFlowerName() {
		return flowerName;
	}

	/**
	 * 
	 * @methodtype set
	 */
	public void setFlowerName(String newFlowerName) {
		flowerName = newFlowerName;
		incWriteCount();
	}

	/**
	 * 
	 * @methodtype get
	 */
	public String getFlowerColor() {
		return flowerColor;
	}

	/**
	 * 
	 * @methodtype set
	 */
	public void setFlowerColor(String newFlowerColor) {
		flowerColor = newFlowerColor;
		incWriteCount();
	}
	
	// cw07
	protected void assertIsNotNull(Object ob, String msg)
	{
		if (ob == null) {
			throw new NullPointerException(msg);
		}
	}
}
