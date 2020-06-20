package org.fms.software;

import java.io.Serializable;

import org.fms.hardware.*;
import org.fms.software.*;
import org.fms.people.*;

public class Goal implements Serializable {
	
	//***********************Members***************************
	private Boolean complete;
	private Material material;
	private int quantityOfMaterial;
	
	//***********************Methods***************************
	public Goal()
	{
		complete = false;
	}
	
	public Goal(Material mat, int quantity)
	{
		this.material = mat;
		this.quantityOfMaterial = quantity;
		this.complete = false;
	}
	
	public void setQuantity(int newQuantity)
	{
		this.quantityOfMaterial = newQuantity;
	}
	
	public int getQuantity() 
	{
		return this.quantityOfMaterial;
	}
	
	public void setMaterial (Material newMaterial)
	{
		this.material = newMaterial;
	}
	
	public Material getMaterial()
	{
		return material;
	}
	
	public void setComplete(Boolean complete) 
	{
		this.complete = complete;
	}
	
	public boolean isComplete() 
	{
		return this.complete;
	}

	public Job getRequiredJob() 
	{
		return this.material.getJob();
	}
}
