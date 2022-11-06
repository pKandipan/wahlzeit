
package org.wahlzeit.model;

import java.sql.*;
import java.net.*;

import org.wahlzeit.services.*;
import org.wahlzeit.utils.*;

/**
 * A flower photo represents a user-provided (uploaded) photo.
 */
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
		id = myId;
		
		incWriteCount();
	}
	
	/**
	 * 
	 * @methodtype constructor
	 */
	public FlowerPhoto(ResultSet photoRset) throws SQLException {
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
		if (photoRset == null || flowerRset == null) {
			throw new NullPointerException("Parameter was null inside method 'readFrom'.");
		}
	
		readFrom(photoRset);
		
		flowerName = flowerRset.getString("flower_name");
		flowerColor = flowerRset.getString("flower_color");
	}
	
	/**
	 * 
	 */
	@Override
	public void writeOn(ResultSet rset) throws SQLException {
		rset.updateInt("id", id.asInt());
		rset.updateString("flower_name", flowerName);
		rset.updateString("flower_color", flowerColor);
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
}
