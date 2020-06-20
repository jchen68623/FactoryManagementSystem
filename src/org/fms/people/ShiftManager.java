package org.fms.people;

import java.io.Serializable;
import java.util.ArrayList;

import org.fms.hardware.*;
import org.fms.software.*;
import org.fms.people.*;

public class ShiftManager extends Manager implements Serializable {
	
	//***********************Members***************************
	private ProjectManager pManager;
	private ArrayList<Goal> goals;
	private ArrayList<Worker> workers;
	
	
	public ShiftManager()
	{
		this.goals = new ArrayList<Goal>();
		this.workers = new ArrayList<Worker>();
	}
	
	//***********************Methods***************************
	public void setProjectManager(ProjectManager newManager) 
	{
		this.pManager = newManager;
	}
	
	public ProjectManager getProjectManager()
	{
		return this.pManager;
	}
	
	public void setGoals(ArrayList<Goal> assigned_goals) {
		this.goals = assigned_goals;
	}
	
	public ArrayList<Goal> getGoals(){
		return goals;
	}
	
	public boolean autoAssignGoalsToWorkers() {
		boolean allGoalsAssigned = true;
		ArrayList<Goal> unassignedGoals = new ArrayList<Goal>();
		//loop through all the goals 
		for(Goal g : goals)
		{
			boolean goalAssigned = false;
			//loop through all the workers to find the match
			for(int i = 0; i < workers.size() && !goalAssigned; i++) 
			{
				//if worker job matches goal required job and goal material matches worker machine material
				String goaljob = g.getRequiredJob().getName();
				String workerjob = workers.get(i).getJob().getName();
				String goalmat = g.getMaterial().getName();
				if(goaljob.equals(workerjob))
				{
					for(Recipe r : workers.get(i).getMachine().getRecipes())
					{
						String machinemat = r.getMaterialOut().getName();
						if(goalmat.equals(machinemat))
						{
							workers.get(i).addGoal(g);
							System.out.println("Assigning goal: Make " + g.getQuantity() + " " + g.getMaterial().getName() + " to Worker " + workers.get(i).getName() + ".");
							goalAssigned = true;
						}
					}
				}
			}
			//If no worker matched the goal to assign
			if(!goalAssigned)
			{
				unassignedGoals.add(g);
				allGoalsAssigned = false;
			}
		}
		//Report back any unassigned goals
		if(!allGoalsAssigned)
		{
			System.out.println("\n" + unassignedGoals.size() + " Goals unable to be automatically assigned:");
			for(Goal g : unassignedGoals) 
			{
				System.out.println("Unassigned goal: Create " + g.getQuantity() + " " + g.getMaterial().getName() + ", Required Job: " + g.getRequiredJob().getName() + ", no Worker had matching Job and Machine.");
			}
			return false;
		}
		System.out.println("\nAll Goals Assigned!"
				+ "");
		return true;
	}

	public void assignJob(Worker worker, Job job) 
	{
		worker.setJob(job);
	}

	public void assignMachine(Worker worker, Machine machine) 
	{
		worker.setMachine(machine);
	}
	
	public void addWorker(Worker worker)
	{
		this.workers.add(worker);
		worker.setShiftManager(this);
	}
	
	public void putWorkersToWork()
	{
		Factory f = this.f; // updated so the manager class has f
		System.out.println("\nShift Manager " + this.getName() + " telling workers to execute their first goal:\n");
		//For each worker
		for(int n = 0; n < workers.size(); n++) 
		{
			for(Worker w: workers)
			{
					//For each goal
				for(Goal g : w.getGoals())
				{
					//If not complete
					if(!g.isComplete())
					{
						boolean success = false;
						//For each recipe in worker's machine
						for(Recipe r : w.getMachine().getRecipes())
						{
							//If goal output matches recipe output
							if(r.getMaterialOut().equals(g.getMaterial()))
							{
								//For each material in the recipe
								for(int i = 0; i < r.getInputMaterials().size(); i++)
								{
									boolean possible = true;
									//For each of the materials in the factory
									for(Material m : f.getListOfMaterials())
									{
										//If recipe input material matches found factory input material							
										if (r.getInputMaterials().get(i).getName().equals(m.getName()))
										{
											//If the factory has at least as many materials as needed for the goal recipe quantity
											if(m.getQuantity() >= r.getQuantityIn(i)*g.getQuantity())
											{
												System.out.println("Quantity of " + m.getName() + " is " + m.getQuantity() + ", goal of " + g.getQuantity() + " " + g.getMaterial().getName() + " requires " + r.getQuantityIn(i)*g.getQuantity() + " " + m.getName() + ".");
											}
											else
											{
												System.out.println("ERROR: Quantity of " + m.getName() + " is " + m.getQuantity() + ", but goal of " + g.getQuantity() + " " + g.getMaterial().getName() + " requires " + r.getQuantityIn(i)*g.getQuantity() + " " + m.getName() + ".");
												possible = false;
											}
										}
									}
									if(possible)
									{
										success = true;
									}
								}
							}
						}
						if(!success)
						{	
							System.out.println(w.getName() + " states they could not complete their goal.");
				
						}
						else
						{
							if(w.makeMaterial(g))
							{
								System.out.println(w.getName() + " states their goal was completed.\n");
							}
							else
							{
								System.out.println(w.getName() + " states their goal could not be completed.\n");
							}
						}
					}
				}
			}
		}
	}

	public String getStringMachines() {
		String returnString = "";
		if (this.f.getMachines().size() == 0) {
			return "There are currently no Machines in this Factory";
		}
		else {
			returnString += "Machines:\n\n";
		}
		for (int i = 0; i < this.f.getMachines().size(); i++) {
			returnString += Integer.toString(i + 1) + ")  " + this.f.getMachines().get(i).getName() + "\n";
		}
		return returnString;
	}
}




















