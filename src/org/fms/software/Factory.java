package org.fms.software;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import org.fms.hardware.Machine;
import org.fms.hardware.Material;
import org.fms.people.Manager;
import org.fms.people.Worker;

public class Factory implements Serializable {
	
	//***********************Members***************************
	private ArrayList<Material> materials = new ArrayList<Material>();
	private ArrayList<Worker> workers = new ArrayList<Worker>();
	private ArrayList<Manager> managers = new ArrayList<Manager>();
	private ArrayList<Machine> machines = new ArrayList<Machine>();
	private ArrayList<ScheduleItem> hoursOfOperations = new ArrayList<ScheduleItem>();
	private ArrayList<Recipe> recipes = new ArrayList<Recipe>();
	
	private String name;
	private double funds = 0.0;
	
	//***********************Methods***************************
	public void setFunds(double funds)
	{
		this.funds = funds;
	}
	
	public double getFunds()
	{
		return this.funds;
	}
	
	public void addFunds(double funds)
	{
		this.funds += funds;
	}
	
	public void takeFunds(double funds)
	{
		this.funds -= funds;
	}
	
	public void addRecipe(Recipe r)
	{
		this.recipes.add(r);
	}
	
	public ArrayList<Recipe> getRecipes()
	{
		return this.recipes;
	}
	
	public void addNewMaterial(Material newMaterial)
	{
		this.materials.add(newMaterial);
	}
	
	public void addNewWorker(Worker newWorker) 
	{
		newWorker.setFactory(this);
		this.workers.add(newWorker);
	}
	
	public ArrayList<Worker> getWorkers() {
		return this.workers;
	}
	
	public void addNewManager(Manager newManager)
	{
		newManager.setFactory(this);
		this.managers.add(newManager);
	}
	
	public void addNewMachine(Machine newMachine) 
	{
		this.machines.add(newMachine);
	}
	
	public void updateHours (ArrayList<ScheduleItem> newSchedule)
	{
		this.hoursOfOperations = newSchedule;
	}
	
	public ArrayList<Material> getListOfMaterials()
	{
		return this.materials;
	}
	
	public ArrayList<ScheduleItem> getSchedule()
	{
		return this.hoursOfOperations;
	}
	
	public void printHours() 
	{
		System.out.println("The Factory Hours are:");
		for (int a = 0; a < this.hoursOfOperations.size(); a++)
		{
			hoursOfOperations.get(a).printScheduleItem();
		}
	}
	
	public void fireManager(Manager exManager)
	{
		this.managers.remove(exManager);
	}
	
	public void fireWorker(Worker exWorker) 
	{
		this.workers.remove(exWorker);
	}
	
	public void printWorkers()
	{
		System.out.println("List of Workers: ");
		for (int i = 0; i < workers.size();i++) 
		{
			System.out.println(workers.get(i).getName());
		}
	}
	
	public void printManagers() 
	{
		System.out.println("List of Managers: ");
		for (int i = 0; i < managers.size();i++) 
		{
			System.out.println(managers.get(i).getName());
		}
	}
	
	public void printMaterials()
	{
		System.out.println("List of Materials: ");
		for (int i = 0; i < materials.size();i++) 
		{
			System.out.println(materials.get(i).getQuantity() + " units of " + materials.get(i).getName() + " in stock");
		}
	}

	public static void saveData(Factory f1){
		
		
		FileOutputStream fileOut = null;
		ObjectOutputStream objOut= null;

		try 
		{
			fileOut = new FileOutputStream( "Factory.ser" );		
			objOut = new ObjectOutputStream(fileOut);
			objOut.writeObject(f1);
			objOut.close();
			fileOut.close();
	    }
		
		catch(IOException i)
	    {
			i.printStackTrace();
	    }		
 	}
	
	public static Factory loadData()
	{	
		FileInputStream fileIn = null;
		ObjectInputStream objIn = null;
		Factory fact1 = null;
		
		try
		{
			fileIn = new FileInputStream("Factory.ser");
			objIn = new ObjectInputStream(fileIn);
			fact1 = (Factory) objIn.readObject();
			objIn.close();
			fileIn.close();
		}
		catch(IOException i)
		{
			i.printStackTrace();
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}  
		return fact1;
	}
	
	public ArrayList<Manager> getManagers() {
		return this.managers;
	}
	
	public String getStringofMaterials() {
		String returnString = "";
		for (int i = 0; i < this.materials.size(); i++) {
			returnString += Integer.toString(this.materials.get(i).getQuantity()) + " of " + materials.get(i).getName() + "\n";					
		}
		return returnString;
	}
	
	public void setName(String n) {
		this.name = n;
	}
	
	public String getName() {
		return this.name;
	}
	
	public ArrayList<Machine> getMachines() {
		return this.machines;
	}

	public String getStringofWorkerSchedules() {
		String outputString = "";
		if (this.workers.size() == 0) {
			return "There are no workers in the factory. :(";
		}
		for (int i = 0; i <this.workers.size(); i++) {
			outputString += this.workers.get(i).getName() + ") \n" + this.workers.get(i).getStringSchdule();
		}
		return outputString;
	}	 
}


















