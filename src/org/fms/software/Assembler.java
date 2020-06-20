package org.fms.software;

import java.io.Serializable;

public class Assembler extends Job implements Serializable{
	
	//***********************Members***************************
	
	String name;
	
	public Assembler()
	{
		this.name = "Assembler";
	}
	
	//***********************Methods***************************

	public String getName()
	{
		return this.name;
		
	}
}
