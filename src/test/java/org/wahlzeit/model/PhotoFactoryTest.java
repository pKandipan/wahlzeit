
package org.wahlzeit.model;

import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;

public class PhotoFactoryTest {

	@Test
	public void testGetInstance() {
		PhotoFactory factory = PhotoFactory.getInstance();
		assertNotNull(factory);

		assertTrue(factory instanceof FlowerPhotoFactory);
	}
	
	@Test
	public void testCreatePhoto() {
		PhotoFactory factory = PhotoFactory.getInstance();

		assertTrue(factory.createPhoto() instanceof FlowerPhoto);
	}
}
