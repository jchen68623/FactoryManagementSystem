package org.fms.software;

import java.io.Serializable;

public class Processor extends Job implements Serializable{
	
	//***********************Members***************************
	
	String name;
	
	public Processor()
	{
		this.name = "Processor";
	}
	//***********************Methods***************************

	public String getName()
	{
		return this.name;
		
	}
	
}
