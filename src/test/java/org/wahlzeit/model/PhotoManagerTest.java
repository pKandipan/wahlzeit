
package org.wahlzeit.model;

import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;

public class PhotoManagerTest {

	@Test
	public void testGetInstance() {
		PhotoManager manager = PhotoManager.getInstance();
		
		assertNotNull(manager);
		assertTrue(manager instanceof FlowerPhotoManager);
	}
}
