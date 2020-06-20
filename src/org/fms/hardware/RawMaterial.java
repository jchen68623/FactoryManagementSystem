package org.fms.hardware;

import org.fms.hardware.*;
import org.fms.software.*;
import org.fms.people.*;

import java.io.Serializable;
import java.util.ArrayList;

public class RawMaterial extends Material implements Serializable {
	
	//***********************Members***************************
	private String vendor;
	private double unitCost;
		
	//********************Constructors*************************
	public RawMaterial()
	{
		
	}
	//***********************Methods***************************
	public String getVendor() 
	{
		return this.vendor;
	}
	
	public void setVendor(String newVendor) 
	{
		this.vendor = newVendor;
	}
	
	public double getUnitCost()
	{
		return this.unitCost;
	}
	
	public void setUnitCost(double cost)
	{
		this.unitCost = cost;
	}
	
	public Job getJob()
	{
		return null;
	}
}
