package org.fms.software;

import java.io.Serializable;

public class Packager extends Job implements Serializable {
	
	//***********************Members***************************
	
	String name;
	
	public Packager()
	{
		this.name = "Packager";
	}
	
	//***********************Methods***************************

	public String getName()
	{
		return this.name;
		
	}
	
}
