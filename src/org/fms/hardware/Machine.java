package org.fms.hardware;

import java.io.Serializable;
import java.util.ArrayList;
import org.fms.software.*;

public class Machine implements Serializable {
	
	//***********************Members***************************
	private String name;
	private ArrayList<ScheduleItem> schedule = new ArrayList<ScheduleItem>();
	private ArrayList<Recipe> recipes = new ArrayList<Recipe>();
	private Factory factory;
	
	public Machine(Factory f) {
		this.factory = f;
	}

	//***********************Methods***************************
	public Factory getFactory () {
		return this.factory;
	}
	
	
	public void setName(String newName) 
	{
		this.name = newName;
	}
	
	public String getName() 
	{
		if (name == null) {
			return "No Machine Name";
		}
		return this.name;
	}
	
	public Boolean addEvent(ScheduleItem eventTime) 
	{
		Boolean scheduleConflicts = false;
		for (int i = 0; i < schedule.size();i++) 
		{
			if (schedule.get(i).detectConflict(eventTime)) 
			{
				scheduleConflicts = true;
				break;
			}
		}
		if (scheduleConflicts == false) 
		{
			schedule.add(eventTime);
		}
		return !scheduleConflicts;
	}
	
	public void addRecipe(Recipe newRecipe) 
	{
		this.recipes.add(newRecipe);
	}
	
	public String getStringRecipes() {
		String outputString = "Recipe: \n";
		for (int i = 0; i < this.recipes.size(); i++) {
			outputString += Integer.toString(i + 1) + ")  " + recipes.get(i).getMaterialOut().getName() + ":  \n";
			for (int j = 0; j < this.recipes.get(i).getInputMaterials().size(); j++) {
				outputString += "      " + Integer.toString(this.recipes.get(i).getQuantityIn(j)) + " " + this.recipes.get(i).getInputMaterials().get(j).getName() + "\n";
			}
			outputString += "\n\n";
		}
		return outputString;
	}
	
	public ArrayList<Recipe> getRecipes()
	{
		return this.recipes;
	}
	
	public boolean produceMaterial(Material materialToProduce, int numToMake)
	{
		//see if the machine makes this material
		int indexOfRecipe = -1;
		for (int index = 0; index < this.recipes.size(); index++)
		{
			if (this.recipes.get(index).getMaterialOut().getName().equals(materialToProduce.getName()))
			{
				indexOfRecipe = index;
			}
		}
		//if machine does not make this material
		if (indexOfRecipe == -1)
		{
			System.out.println("The machine cannot make that Material.");
			return false;
		}
		//see if the machine has enough input materials for the quantity to make
		boolean haveEnough = true;
		for(int i = 0; i < recipes.get(indexOfRecipe).getInputMaterials().size(); i++)
			for(Material m : this.factory.getListOfMaterials())
			{
				if(recipes.get(indexOfRecipe).getInputMaterials().get(i).getName().equals(m.getName()))
				{
					if(m.getQuantity() >= recipes.get(indexOfRecipe).getQuantityIn(i) * numToMake)
					{
						//have enough
					}
					else
					{
						System.out.println("Have " + m.getQuantity() + " of " + m.getName() + " but need " + recipes.get(indexOfRecipe).getQuantityIn(i) * numToMake + ".");
						haveEnough = false;
					}
				}
			}
			//if don't have enough of one of the materials
			if (!haveEnough)
			{
				return false;
			}
		
		// Have enough of all ingredients
		this.recipes.get(indexOfRecipe).getMaterialOut().addQuantity(numToMake);
		for (int index = 0; index < recipes.get(indexOfRecipe).getSizeOfList(); index++)
		{
			this.recipes.get(indexOfRecipe).getMaterialIn(index).setQuantity(this.recipes.get(indexOfRecipe).getMaterialIn(index).getQuantity() - (this.recipes.get(indexOfRecipe).getQuantityIn(index) / this.recipes.get(indexOfRecipe).getQuantityOut() * numToMake));
		}
		
		return true;
	}
}
