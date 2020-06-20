package org.fms.hardware;

import org.fms.hardware.*;
import org.fms.software.*;
import org.fms.people.*;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Material implements Serializable {
	
	//***********************Members***************************
	private String name;
	private int quantity;
	
	//***********************Methods***************************
	public void setName(String newName)
	{
		this.name = newName;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public void setQuantity(int newQuantity)
	{
		this.quantity = newQuantity;
	}
	
	public int getQuantity() 
	{
		return this.quantity;
	}
	
	//Returns true if successfully added, false if not (over limit)
	public Boolean addQuantity(int amountToAdd)
	{
			this.quantity += amountToAdd;
			return true;
	}
	
	public abstract Job getJob();
	
}
