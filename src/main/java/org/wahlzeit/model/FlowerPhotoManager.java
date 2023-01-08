
package org.wahlzeit.model;

import java.io.*;
import java.sql.*;
import java.util.*;

import org.wahlzeit.main.*;
import org.wahlzeit.services.*;

import org.wahlzeit.utils.PatternInstance;

/**
 * A flower photo manager provides access to and manages photos.
 */
public class FlowerPhotoManager extends PhotoManager {

	/**
	 * 
	 */
	public FlowerPhotoManager() {
		photoTagCollector = FlowerPhotoFactory.getInstance().createPhotoTagCollector();
	}
	
	/**
	 * 
	 */
	protected ResultSet getFlowerRset(PhotoId id) throws SQLException {
		PreparedStatement stmt = getUpdatingStatement("SELECT * FROM flowers WHERE id = ?");
		stmt.setInt(1, id.asInt());
		SysLog.logQuery(stmt);
		return stmt.executeQuery();
	}
	
	/**
	 * 
	 */
	@Override
	protected Photo createObject(ResultSet photoRset) throws SQLException{
	
		int id = photoRset.getInt("id");
		ResultSet flowerRset = null;
	
		// cw07
		try
		{
			flowerRset = getFlowerRset(PhotoId.getIdFromInt(id));
		}
		catch(SQLException e)
		{
			throw new IllegalStateException("there is no entry in flowers table with id: " + String.valueOf(id));
		}
		
	
		FlowerPhotoFactory factory = (FlowerPhotoFactory)FlowerPhotoFactory.getInstance();
		return factory.createPhoto(photoRset, flowerRset);
	}

	/**
	 * 
	 */
	@Override
	public void savePhoto(Photo photo) {
		try {
			PreparedStatement stmt = getUpdatingStatement("SELECT * FROM photos WHERE id = ?");
			updateObject(photo, stmt);
		} catch (SQLException sex) {
			SysLog.logThrowable(sex);
		}

		try {
			PreparedStatement stmt = getUpdatingStatement("SELECT * FROM flowers WHERE id = ?");
			updateObject(photo, stmt);
		} catch (SQLException sex) {
			SysLog.logThrowable(sex);
		}
	}

	/**
	 * 
	 */
	@Override
	public void savePhotos() {
		try {
			PreparedStatement stmt = getUpdatingStatement("SELECT * FROM photos WHERE id = ?");
			updateObjects(photoCache.values(), stmt);
		} catch (SQLException sex) {
			SysLog.logThrowable(sex);
		}

		try {
			PreparedStatement stmt = getUpdatingStatement("SELECT * FROM flowers WHERE id = ?");
			updateObjects(photoCache.values(), stmt);
		} catch (SQLException sex) {
			SysLog.logThrowable(sex);
		}
	}
}
