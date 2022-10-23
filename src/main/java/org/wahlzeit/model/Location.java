
// group this class in the same package as the Photo class
package org.wahlzeit.model;


public class Location {

	// composition behavior between Location and Coordinate classes
	// final, cause Coordinate object of another Location object should not be assigned here (multiplicity 0..1)
	public final Coordinate coordinate;


	// constructor that takes coordinate components as arguments
	// arguments needed to init the member coordinate that is never null (multiplicity 1 ensured)
	public Location(double x, double y, double z)
	{
		// create new Coordinate object (instead of assigning argument to member) to ensure the multiplicity 0..1
		this.coordinate = new Coordinate(x, y, z);
	}

}
