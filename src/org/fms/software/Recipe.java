package org.fms.software;

import java.io.Serializable;
import java.util.ArrayList;

import org.fms.hardware.Material;

public class Recipe implements Serializable {
	
	//***********************Members***************************
	private ArrayList<Material> materialsIn = new ArrayList<Material>();
	private ArrayList<Integer> quantitysIn = new ArrayList<Integer>();
	private Material materialOut;
	private int quantityOut;
	private double amountOfTime;
	
	
	//***********************Methods***************************
	public void addInput(Material material, int quantity) 
	{
		if (materialsIn.contains(material))
		{
			quantitysIn.set(materialsIn.indexOf(material), quantity);
		}
		else 
		{
			quantitysIn.add(quantity);
			materialsIn.add(material);
		}
	}
	
	public int getSizeOfList() 
	{
		return this.quantitysIn.size();
	}
	
	public Material getMaterialIn(int index) 
	{
		return materialsIn.get(index);
	}
	
	public ArrayList<Material> getInputMaterials()
	{
		return materialsIn;
	}
	
	public int getQuantityIn(int index)
	{
		return quantitysIn.get(index);
	}
	
	public int getQuantityOut() 
	{
		return this.quantityOut;
	}
	
	public double getTime() 
	{
		return this.amountOfTime;
	}
	
	public void setQuantityOut(int newQuantity)
	{
		this.quantityOut = newQuantity;
	}
	
	public void setTime(double newTime)
	{
		this.amountOfTime = newTime;
	}
	
	public Material getMaterialOut() 
	{
		return this.materialOut;
	}
	
	public void setMaterialOut(Material newMaterial)
	{
		this.materialOut = newMaterial;
	}
	
	public void setOutput(Material material, int quantity)
	{
		this.materialOut = material;
		this.quantityOut = quantity;
	}
}
