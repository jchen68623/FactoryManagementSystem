package org.fms.people;

import java.io.Serializable;
import java.util.ArrayList;

import org.fms.hardware.Machine;
import org.fms.hardware.Material;
import org.fms.hardware.PackagedMaterial;
import org.fms.hardware.RawMaterial;
import org.fms.software.Factory;
import org.fms.software.Goal;
import org.fms.software.Recipe;
import org.fms.software.Request;
import org.fms.software.ScheduleItem;

public class ProjectManager extends Manager implements Serializable {
	
	//***********************Members***************************
	private ArrayList<Goal> goals = new ArrayList<Goal>();
	private ArrayList<Worker> workers = new ArrayList<Worker>();
	private ArrayList<ShiftManager> shiftManagers = new ArrayList<ShiftManager>();
	private ArrayList<ScheduleItem> schedule = new ArrayList<ScheduleItem>();
	private ArrayList<Request> requestList = new ArrayList<Request>();

	//***********************Methods***************************
	
	public int sellProducts(PackagedMaterial mat, Factory f, int num)
	{
		int numsold = 0;
		for(Material m : f.getListOfMaterials())
		{
			if(m instanceof PackagedMaterial)
			{
				if(m.getName().equals(mat.getName()))
				{
					for(int i = 0; i < num; i++)
					{	
						if(this.sellProduct(m, f))
						{
							numsold++;
						}
					}
				}
			}
		}
		System.out.println("Number of " + mat.getName() + " sold: " + numsold);
		return numsold;
	}
	
	public void sellAllProducts(Factory f)
	{
		for(Material m : f.getListOfMaterials())
		{
			if(m instanceof PackagedMaterial)
			{
				while(m.getQuantity() > 0)
				{	
					this.sellProduct(m, f);
				}
			}
		}
	}
	
	public boolean sellProduct(Material m, Factory f)
	{
		for(Material mat : f.getListOfMaterials())
		{
			if(mat instanceof PackagedMaterial)
			{
				if(m.getName().equals(mat.getName()))
				{
					if (mat.getQuantity() > 0)
					{
						f.addFunds(((PackagedMaterial) mat).getUnitCost());
						mat.addQuantity(-1);
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public void checkFactoryFunds(Factory f)
	{
		System.out.println("Factory has $" + f.getFunds() + " dollars.");
	}

	public void makeRequest(Material inputMaterial, int quantity, Worker workerWhoMadeRequest) {
		this.requestList.add(new Request(inputMaterial, quantity, workerWhoMadeRequest));
	}
	
	public ArrayList<Request> getRequestList() {
		return this.requestList;
	}
	
	public String getStringRequest() {
		String returnString = "";
		if (requestList.size() == 0) {
			return "Currently there are no Request.";
		}
		returnString += "Request:\n";
		for (int i = 0; i < requestList.size(); i++) {
			returnString += Integer.toString(i + 1) + ")  " + this.requestList.get(i).getOrigionator().getName() + " requested " + Integer.toString(this.requestList.get(i).getQuantity()) + " of ";
			returnString += this.requestList.get(i).getMaterial().getName() + " \n";
		}
		
		return returnString;
	}
	
	public void removeRequest(int index) {
		this.requestList.remove(index);
	}
	
	public void addToSchedule(ScheduleItem newEvent) 
	{
		Boolean allGood = true;
		for (int i = 0; i < schedule.size(); i++) 
		{
			if (schedule.get(i).detectConflict(newEvent) == true) 
			{
				allGood = false;
				System.out.println(newEvent.getName() + " event could not be scheduled because it overlaps with " + schedule.get(i).getName());
				break;
			}
		}
		if (allGood) 
		{
			schedule.add(newEvent);
			System.out.println("Congrats, manager " + this.getName() + " has scheduel the event, " + newEvent.getName() + " from " + newEvent.getStartTimeDouble() + " to " + newEvent.getEndTimeDouble() + ".");
		}
		return;
	}
	
	public void addGoal(Goal newGoal) 
	{
		this.goals.add(newGoal);
	}
	
	public void addWorker(Worker newWorker) 
	{
		this.workers.add(newWorker);
		newWorker.setProjectManager(this);
	}
	
	public void addShiftManager(ShiftManager newShiftManager) 
	{
		this.shiftManagers.add(newShiftManager);
	}
	
	public ArrayList<Goal> getListOfGoals()
	{
		return this.goals;
	}
	
	public ArrayList<Worker> getListOfWorkers() 
	{
		return this.workers;
	}
	
	public ArrayList<ShiftManager> getListOfShiftManagers() 
	{
		return this.shiftManagers;
	}
	
	public Boolean removeGoal(Goal goal) 
	{
		return this.goals.remove(goal);
	}
	
	public Boolean removeWorker(Worker worker) 
	{
		return this.workers.remove(worker);
	}
	
	public Boolean removeShiftManager(ShiftManager shiftManager)
	{
		return this.shiftManagers.remove(shiftManager);
	}
	
	public void addRecipeToMachine(Machine theMachine, Recipe newRecipe)
	{
		System.out.println("Adding Recipe " + newRecipe.getMaterialOut().getName() + " to machine.");
		theMachine.addRecipe(newRecipe);
	}

	public void assignGoalsToWorker(ArrayList<Goal> goals) 
	{
		this.goals = goals;
	}
	
	public boolean orderRawMaterial(RawMaterial rawMat, int quantity, Factory f)
	{
		//if factory has enough funds to order quantity
		if(rawMat.getUnitCost() * quantity <= f.getFunds())
		{
			boolean success = false;
			//find matching material type
			for(Material m : f.getListOfMaterials())
			{
				if(rawMat.getName().equals(m.getName()))
				{
					m.addQuantity(quantity);
					f.takeFunds(rawMat.getUnitCost() * quantity);
					success = true;
				}
			}
			if(!success)
			{
				System.out.println("No matching material for order of " + rawMat.getName() + " found.");
				return false;
			}
			else
			{
				System.out.println("Ordered " + quantity + " of " + rawMat.getName() + " for $" + rawMat.getUnitCost() * quantity + "." );
				return true; 
			}
		}
		System.out.println("Insufficient funds to order " + rawMat.getName() + ". Total cost for " + quantity + " orders at cost of $" + rawMat.getUnitCost() + " each totals to $" + rawMat.getUnitCost() * quantity + ", more than factory funds of $" + f.getFunds() + ".");
		return false;
	}

	public String getWorkersString() {
		String output = "";
		for(Worker w : this.getListOfWorkers())
		{
			output += w.getJob().getName() + " " + w.getName() + "\n"; 
			//output += "\t" + w.getScheduleString() + "\n\n";
		}
		return output;
	}
}
