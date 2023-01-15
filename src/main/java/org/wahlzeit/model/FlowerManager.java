
package org.wahlzeit.model;

import java.util.Map;
import java.util.HashMap;

import org.wahlzeit.services.*;
import org.wahlzeit.utils.*;

import java.sql.*;
import java.net.*;

public class FlowerManager
{
	/**
	 * 
	 */
	protected static final FlowerManager instance = new FlowerManager();
	
	/**
	 * In-memory cache for flowers
	 */
	protected HashMap<PhotoId, Flower> flowers = new HashMap<PhotoId, Flower>();
	
	/**
	 * 
	 */
	public static final FlowerManager getInstance() {
		return instance;
	}

	public Flower createFlower(FlowerPhoto photo, String typeName, String flowerName, String flowerColor) {
	
		// for now any typeName is valid
		//assertIsValidFlowerTypeName(typeName);
		
		FlowerType ft = FlowerType.getFlowerTypeInstance(typeName);
		Flower result = ft.createFlowerInstance(flowerName, flowerColor);
		result.setIdFromPhoto(photo);
		flowers.put(result.getId(), result);
		return result;
	}
	
	public Flower createFlower(ResultSet rset) throws SQLException {
	
		Flower result = new Flower(rset);
		flowers.put(result.getId(), result);
		return result;
	}
	
	/**
	 * @methodtype get
	 * @methodproperties primitive
	 */
	protected Flower doGetFlowerFromId(PhotoId id) {
		return flowers.get(id);
	}
}
