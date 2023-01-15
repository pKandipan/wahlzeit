
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

	protected Flower flower;

	/**
	 * 
	 */
	public FlowerPhoto() {
		id = PhotoId.getNextId();
		incWriteCount();
	}
	
	/**
	 * 
	 */
	public FlowerPhoto(Flower flower) {
		id = PhotoId.getNextId();
		setFlower(flower);
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
	
		flower = FlowerManager.getInstance().createFlower(flowerRset);
		// cw07
		try {
			readFrom(photoRset);
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
			if(flower != null)
				flower.writeOn(rset);
		} catch(SQLException e) {
			throw e;
		}
	}

	
	/**
	 * 
	 * @methodtype get
	 */
	public Flower getFlower() {
		return flower;
	}

	/**
	 * 
	 * @methodtype set
	 * ensures that the newly set Flower is managed by the FlowerManager
	 * ensures that the newly set Flower only belogns to one photo
	 * otherwise a copy is set
	 */
	public void setFlower(Flower newFlower) {
		assertIsNotNull(newFlower, "cannot assign a null-Flower to a FlowerPhoto");
		
		if(newFlower.getId() == null)
		{
			flower = newFlower;
		}
		else
		{
			FlowerType ft = newFlower.getFlowerType();
			String fName = newFlower.getFlowerName();
			String fColor = newFlower.getFlowerColor();
			flower = FlowerManager.getInstance().createFlower(this, ft.getTypeName(), fName, fColor);
		}	
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
