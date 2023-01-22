
package org.wahlzeit.model;

import java.sql.*;

import org.wahlzeit.services.*;

import org.wahlzeit.utils.PatternInstance;

/**
 * An Abstract Factory for creating flower photos and related objects.
 */
@PatternInstance(
	type = "Creational",
	patternName = "Abstract Factory",
	participants = {"Concrete Factory"}
)
public class FlowerPhotoFactory extends PhotoFactory{

	private static FlowerPhotoFactory instance = null;
	
	/**
	 * Public singleton access method.
	 * [step 2]
	 */
	public static synchronized FlowerPhotoFactory getInstance() {
		if (instance == null) {
			SysLog.logSysInfo("setting generic PhotoFactory");
			
			// [step 3 - 4]
			setInstance(new FlowerPhotoFactory());
		}
		
		return instance;
	}
	
	/**
	 * Method to set the singleton instance of FlowerPhotoFactory.
	 * [step 4]
	 */
	protected static synchronized void setInstance(FlowerPhotoFactory flowerPhotoFactory) {
		if (instance != null) {
			throw new IllegalStateException("attempt to initialize FlowerPhotoFactory twice");
		}
		
		instance = flowerPhotoFactory;
	}

	/**
	 * [step 3]
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
	 * [step 5]
	 */
	public FlowerPhoto createPhoto(ResultSet photoRs, ResultSet flowerRs) throws SQLException {
		// [step 6 - 18]
		return new FlowerPhoto(photoRs, flowerRs);
	}
}
