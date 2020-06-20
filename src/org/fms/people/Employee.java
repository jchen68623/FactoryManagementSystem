package org.fms.people;

import org.fms.software.*;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Employee implements Serializable {
	
	//***********************Members***************************
	private String name;
	private ArrayList<Goal> employeeGoals = new ArrayList<Goal>();
	private String userName;
	private String passWord;
	private Factory factory;
	private ArrayList<ScheduleItem> schedule = new ArrayList<ScheduleItem>(); //this is a schedule of when they are at work. Different than a schedule of task to complete while at work

	
	//***********************Methods***************************
	public void setName(String newName)
	{
		this.name = newName;
	}
	
	public void setSchedule(ArrayList<ScheduleItem> s)
	{
		this.schedule = s;
	}
	
	public void removeScheduleItem(int index) {
		this.schedule.remove(index);
		return;
	}
	
	public void addScheduleItem (ScheduleItem item) {
		this.schedule.add(item);
	}
	
	public ArrayList<ScheduleItem> getSchedule()
	{
		return this.schedule;
	}
	
	public String getName() 
	{
		return this.name;
	}
	
	public Factory getFactory()
	{
		return this.factory;
	}
	
	public void setFactory(Factory f)
	{
		this.factory = f;
	}
	
	public void addGoal (Goal newGoal)
	{
		this.employeeGoals.add(newGoal);
	}
	
	public ArrayList<Goal> getGoals()
	{
		return this.employeeGoals;
	}
	
	public Boolean finishGoal (Goal completedGoal) 
	{
		return this.employeeGoals.remove(completedGoal); //returns true if found and deleted and false if not found.
	}
	
	public Boolean isAvailable(ScheduleItem eventTime) 
	{
		
		return true;
	}
	
	public boolean checkCredentials(String userName, String passWord) 
	{
		if (userName.equals(this.userName) && passWord.equals(this.passWord))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public void setUserName(String newUserName) 
	{
		this.userName =newUserName;
	}
	
	public String getUserName() {
		return this.userName;
	}
	
	public void setPassWord(String newPassword) 
	{
		this.passWord = newPassword;
	}

	
	public String getStringSchdule() {
		String outputString = "";
		if (this.schedule.size() == 0) {
			return "Employ is not currently Scheduled. \n";
		}
		for (int i = 0; i < this.schedule.size(); i++) {
			outputString += "\t" + this.schedule.get(i).getStringOfSchedule() + "\n";
		}
		return outputString;
	}
	
	public String getStringSchduleWithIndex() {
		String outputString = "";
		if (this.schedule.size() == 0) {
			return "Employ is not currently Scheduled. \n";
		}
		for (int i = 0; i < this.schedule.size(); i++) {
			outputString += Integer.toString(i + 1) + ")  " + this.schedule.get(i).getStringOfSchedule() + "\n";
		}
		return outputString;
	}
}



























