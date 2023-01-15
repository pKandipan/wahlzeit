
package org.wahlzeit.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;

public class FlowerTypeTest {
	
	@Test
	public void testTypeHierarchy() {
		FlowerType.getFlowerTypeInstance("A/B/C/D1/E");
		FlowerType.getFlowerTypeInstance("A/B/C/D2");
		
		FlowerType type = FlowerType.getFlowerTypeInstance("E");
		assertTrue(type.getTypeHierarchyAsString().equals("A/B/C/D1/E"));
		
		assertTrue(FlowerType.getFlowerTypeInstance("B").isSuperTypeOf(type));
		assertTrue(type.isSubTypeOf(FlowerType.getFlowerTypeInstance("C")));
		assertFalse(type.isSubTypeOf(FlowerType.getFlowerTypeInstance("D2")));
	}
	
	@Test
	public void testHasInstance() {
		FlowerType.getFlowerTypeInstance("A/B/C/D1/E1");
		FlowerType.getFlowerTypeInstance("A/B/C/D1/E2");
		FlowerType.getFlowerTypeInstance("A/B/C/D2");
		
		FlowerType type = FlowerType.getFlowerTypeInstance("E1");
		Flower flower = new Flower(type, "bla", "bla");
		
		assertTrue(type.hasInstance(flower));
		
		type = FlowerType.getFlowerTypeInstance("A");
		assertTrue(type.hasInstance(flower));
		
		type = FlowerType.getFlowerTypeInstance("D2");
		assertFalse(type.hasInstance(flower));
	}
	
	@Test
	public void testValueObject() {
		FlowerType.typeInstances.clear();
		FlowerType.getFlowerTypeInstance("A/B/C/D1/E1");
		FlowerType.getFlowerTypeInstance("A/B/C/D1/E2");
		FlowerType.getFlowerTypeInstance("A/B/C/D2");
		
		assertTrue(FlowerType.typeInstances.size() == 7);
	}
}
