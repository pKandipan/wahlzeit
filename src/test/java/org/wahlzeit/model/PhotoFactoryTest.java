
package org.wahlzeit.model;

import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;

public class PhotoFactoryTest {

	@Test
	public void testGetInstance() {
		PhotoFactory factory = FlowerPhotoFactory.getInstance();
		assertNotNull(factory);

		assertTrue(factory instanceof FlowerPhotoFactory);
	}
	
	@Test
	public void testCreatePhoto() {
		PhotoFactory factory = FlowerPhotoFactory.getInstance();

		assertTrue(factory.createPhoto() instanceof FlowerPhoto);
	}
}
