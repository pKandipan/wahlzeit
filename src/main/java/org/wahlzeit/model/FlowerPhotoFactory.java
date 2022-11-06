
package org.wahlzeit.model;

import java.sql.*;

import org.wahlzeit.services.*;

/**
 * An Abstract Factory for creating flower photos and related objects.
 */
public class FlowerPhotoFactory extends PhotoFactory{

	/**
	 * 
	 */
	protected FlowerPhotoFactory() {
		// do nothing
	}

	/**
	 * @methodtype factory
	 */
	public FlowerPhoto createPhoto() {
		return new FlowerPhoto();
	}
	
	/**
	 * 
	 */
	public FlowerPhoto createPhoto(PhotoId id) {
		return new FlowerPhoto(id);
	}
	
	/**
	 * 
	 */
	public FlowerPhoto createPhoto(ResultSet photoRs) throws SQLException {
		return new FlowerPhoto(photoRs);
	}
	
	/**
	 * 
	 */
	public FlowerPhoto createPhoto(ResultSet photoRs, ResultSet flowerRs) throws SQLException {
		return new FlowerPhoto(photoRs, flowerRs);
	}
}
