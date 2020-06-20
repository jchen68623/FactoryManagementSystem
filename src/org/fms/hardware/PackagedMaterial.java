package org.fms.hardware;

import org.fms.hardware.*;
import org.fms.software.*;
import org.fms.people.*;

import java.io.Serializable;
import java.util.ArrayList;

public class PackagedMaterial extends Material implements Serializable {
	//***********************Members***************************

	private Job requiredJob;
	private double unitCost;
	
	//********************Constructors*************************
	public PackagedMaterial()
	{
		this.requiredJob = new Packager();
	}
	//***********************Methods***************************
	public Job getJob()
	{
		return this.requiredJob;
	}
	
	public double getUnitCost()
	{
		return this.unitCost;
	}
	
	public void setUnitCost(double cost)
	{
		this.unitCost = cost;
	}
}
