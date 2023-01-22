
package org.wahlzeit.model;

import org.wahlzeit.services.*;
import org.wahlzeit.utils.*;

import java.sql.*;
import java.net.*;

import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;

public class FlowerType extends DataObject
{
    protected static HashMap<String, FlowerType> typeInstances = new HashMap<String, FlowerType>();
    
    private String name;
    private FlowerType superType;
    private Set<FlowerType> subTypes = new HashSet<FlowerType>();
    
    private FlowerType(String name, FlowerType superType)
    {
        assertValidName(name);
        this.name = name;
        this.superType = superType;
    }
    
    // [step 12]
    public static FlowerType getFlowerTypeInstance(String name)
    {
        String[] splits = name.split("/");
        
        FlowerType typeInstance = null;
        FlowerType child = null;
        
        for(int i = splits.length-1; i >= 0; --i)
        {
            String typeName = splits[i];
            typeInstance = typeInstances.get(typeName);
            
            if(typeInstance != null)
            {
                if(child != null)
                {
                	typeInstance.addSubType(child);
            	    child.setSuperType(typeInstance);
                }
                break;
            }
            
            typeInstance = new FlowerType(typeName, null);
            typeInstances.put(typeName, typeInstance);
            
            if(child != null)
            {
            	typeInstance.addSubType(child);
            	child.setSuperType(typeInstance);
            }
            
            child = typeInstance;
            
        }
        return typeInstances.get(splits[splits.length-1]);
    }
    
    public String getTypeName()
    {
        return name;
    }
    
    public FlowerType getSuperType()
    {
        return superType;
    }
    
    public void setSuperType(FlowerType ft)
    {
        assertIsNotNull(ft, "tried to set null super-type!");
        superType = ft;
    }
    
    public void addSubType(FlowerType ft)
    {
        assertIsNotNull(ft, "tried to set null sub-type!");
        ft.setSuperType(this);
        subTypes.add(ft);
    }
    
    public Iterator<FlowerType> getSubTypeIterator()
    {
        return subTypes.iterator();
    }
    
    public String getTypeHierarchyAsString()
    {
        if(superType == null) return name;
        return superType.getTypeHierarchyAsString() + "/" + name;
    }
    
    public Flower createFlowerInstance(String flowerName, String flowerColor)
    {
        return new Flower(this, flowerName, flowerColor);
    }
    
    // return whether flower is instanceof 'this'
    public boolean hasInstance(Flower flower)
    {
        assertIsNotNull(flower, "asked about null object");
        
        return isSuperTypeOf(flower.getFlowerType());
    }
    
    // return whether 'this' is superType of ft
    public boolean isSuperTypeOf(FlowerType ft) {
        assertIsNotNull(ft, "asked about null object");
        
        if (ft == this) {
            return true;
        }
        
        for (FlowerType type : subTypes) {
            if (type.isSuperTypeOf(ft)) {
                return true;
            }
        }
        return false;
    }
    
    // return whether 'this' is a subType of ft
    public boolean isSubTypeOf(FlowerType ft) {
        return ft.isSuperTypeOf(this);
    }
    
    /**
	 * 
	 */
	public void readFrom(ResultSet flowerRset) throws SQLException {
		throw new UnsupportedOperationException("this is a value object --> cannot read from ResultSet");
	}
	
	/**
	 * 
	 */
	public void writeOn(ResultSet rset) throws SQLException {
		assertIsNotNull(rset, "Parameter 'rset' was null inside 'writeOn'.");
	
		try {
			rset.updateString("flower_type", getTypeHierarchyAsString());
		} catch(SQLException e) {
			throw e;
		}
	}
	
	/**
	 * 
	 * @methodtype get
	 */
	public String getIdAsString() {
		throw new UnsupportedOperationException("FlowerType has no id!");
	}
	
	/**
	 * 
	 */
	public void writeId(PreparedStatement stmt, int pos) throws SQLException {
		throw new UnsupportedOperationException("FlowerType has no id!");
	}

	@Override
	public int hashCode()
	{
		return name.hashCode();
	}
    
	@Override
	public boolean equals(Object obj)
	{
		return this.hashCode() == obj.hashCode();
	}
    
	private void assertValidName(String name)
	{
		if(name.contains("/"))
		    throw new IllegalArgumentException("name cannot contain '/'");
	}
    
	protected void assertIsNotNull(Object ob, String msg)
	{
		if (ob == null) 
		{
			throw new NullPointerException(msg);
		}
	}
}

