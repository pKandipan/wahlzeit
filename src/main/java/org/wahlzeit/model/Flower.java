
package org.wahlzeit.model;

import org.wahlzeit.services.*;
import org.wahlzeit.utils.*;

import java.sql.*;
import java.net.*;

public class Flower extends DataObject
{
	/**
	 * this is the id of the photo to which this flower belongs to
	 * id is null if no photo of this Flower exists
	 */
	protected PhotoId id = null;
	protected FlowerType flowerType;

	protected String flowerName;
	protected String flowerColor;

	public Flower(FlowerType flowerType, String flowerName, String flowerColor)
	{
		this.flowerName = flowerName;
		this.flowerColor = flowerColor;
		this.flowerType = flowerType;
		incWriteCount();
	}

	// [step 10]
	public Flower(ResultSet rset) throws SQLException
	{
		// [step 11]
		readFrom(rset);
	}

	/**
	 * [step 11]
	 */
	public void readFrom(ResultSet flowerRset) throws SQLException {
		assertIsNotNull(flowerRset, "Parameter 'flowerRset' was null inside 'readFrom'.");
	
		try {
			// delegate to type class
			String typeName = flowerRset.getString("flower_type");
			flowerType = FlowerType.getFlowerTypeInstance(typeName);
			
			id = PhotoId.getIdFromInt(flowerRset.getInt("id"));
			flowerName = flowerRset.getString("flower_name");
			flowerColor = flowerRset.getString("flower_color");
		} catch(SQLException e) {
			throw e;
		}
	}
	
	/**
	 * 
	 */
	public void writeOn(ResultSet rset) throws SQLException {
		assertIsNotNull(rset, "Parameter 'rset' was null inside 'writeOn'.");
	
		try {
			// delegate to type class
			flowerType.writeOn(rset);
			
			// id is written by FlowerPhoto
			
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
	public String getIdAsString() {
		return String.valueOf(id.asInt());
	}
	
	/**
	 * 
	 */
	public void writeId(PreparedStatement stmt, int pos) throws SQLException {
		stmt.setInt(pos, id.asInt());
	}

	/**
	 * 
	 * @methodtype get
	 */
	public PhotoId getId() {
		return id;
	}


	/**
	 * 
	 * @methodtype get
	 */
	public FlowerType getFlowerType() {
		return flowerType;
	}

	/**
	 * 
	 * @methodtype set
	 */
	public void setFlowerType(FlowerType ft) {
		flowerType = ft;
		incWriteCount();
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
	
	public void setIdFromPhoto(Photo photo) {
		id = photo.getId();
	}
	
	protected void assertIsNotNull(Object ob, String msg)
	{
		if (ob == null) {
			throw new NullPointerException(msg);
		}
	}
}
