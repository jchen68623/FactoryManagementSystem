package org.fms.people;

import java.io.Serializable;
import java.util.ArrayList;

import org.fms.hardware.Machine;
import org.fms.software.*;

public class Worker extends Employee implements Serializable {
	
	//***********************Members***************************
	private double hourlyRate;
	private double hoursWorked;	
	private Job workersJob;
	private ArrayList<Goal> listOfGoals = new ArrayList<Goal>();
	private Machine workersMachine;
	private ProjectManager projectManager;
	private ShiftManager shiftManager;
	
	//***********************Methods***************************
	public double getHourlyRate()
	{
		return this.hourlyRate;
	}
	
	public void setShiftManager(ShiftManager sm1) {
		this.shiftManager = sm1;
	}
	
	public ShiftManager getShiftManager () {
		return this.shiftManager;
	}
	
	public void setProjectManager(ProjectManager pm1) {
		this.projectManager = pm1;
	}
	
	public ProjectManager getProjectManager() {
		return this.projectManager;
	}
	
	public void setHourlyRate(double newRate)
	{
		this.hourlyRate = newRate;
	}
	
	public void giveRaise(double percent)
	{
		this.hourlyRate = this.hourlyRate + this.hourlyRate * percent / 100;
	}
	
	public double getHoursWorked()
	{
		return this.hoursWorked;
	}
	
	public void addHoursWorked(double hoursToAdd)
	{
		this.hoursWorked = this.hoursWorked + hoursToAdd;
	}
	
	public double resetHours()
	{
		double moneyEarned = this.hourlyRate * this.hoursWorked;
		this.hoursWorked = 0;
		return moneyEarned;
	}
	
	public void setJob(Job newJob) 
	{
		this.workersJob = newJob;
	}
	
	public Job getJob() 
	{
		return this.workersJob;
	}
	
	public void addGoal (Goal newGoal) 
	{
		this.listOfGoals.add(newGoal);
	}
	
	public ArrayList<Goal> getGoals() 
	{
		return this.listOfGoals;
	}
	
	public boolean makeMaterial(Goal g) 
	{
		System.out.print(this.getName() + " is working on making " + g.getQuantity() + " " + g.getMaterial().getName() +".\n");
		boolean complete = this.workersMachine.produceMaterial(g.getMaterial(), g.getQuantity());
		g.setComplete(complete);
		return complete;
	}
	
	public void setMachine(Machine newMachine)
	{
		this.workersMachine = newMachine;		
	}
	
	public Machine getMachine() {
		return this.workersMachine;
	}
	
	public String getStringOfTask () {
		String outputString = "";
		outputString += "Completed:\n";
		for (int i = 0; i < listOfGoals.size(); i ++) {
			if (this.listOfGoals.get(i).isComplete()) {
				outputString += "\tMake " + this.listOfGoals.get(i).getQuantity() + " of " + this.listOfGoals.get(i).getMaterial().getName() + ".\n";
			}
		}
		outputString += "\nNeeds to be done:\n";
		for (int i = 0; i < listOfGoals.size(); i ++) {
			if (!this.listOfGoals.get(i).isComplete()) {
				outputString += "\tMake " + this.listOfGoals.get(i).getQuantity() + " of " + this.listOfGoals.get(i).getMaterial().getName() + ".\n";
			}
		}
		if (outputString.equals("")) {
			outputString = "Empty Schedule! Have fun reading your user manual";
		}
		return outputString;
	}

}


























