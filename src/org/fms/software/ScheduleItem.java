package org.fms.software;

import java.io.Serializable;

public class ScheduleItem implements Serializable {
	
	//***********************Members***************************

	private enum Day 
	{
			Monday,
			Tuesday,
			Wednesday,
			Thursday,
			Friday,
			Saturday,
			Sunday
	};
	private Day theDay;
	private int startTime;
	private int endTime;
	private String name;

	
	//***********************Methods***************************
	
	public ScheduleItem(String day, int startAsDec, int endAsDec) 
	{
		this.startTime = startAsDec;
		this.endTime = endAsDec;
		
		if (day.toLowerCase().equals("monday") )
		{
			this.theDay = Day.Monday;
		}
		else if (day.toLowerCase().equals("tuesday")) 
		{
			this.theDay = Day.Tuesday;
		}
		else if (day.toLowerCase().equals("wednesday"))
		{
			this.theDay = Day.Wednesday;
		}
		else if (day.toLowerCase().equals("thursday")) 
		{
			this.theDay = Day.Thursday;
		}
		else if (day.toLowerCase().equals("friday"))
		{
			this.theDay = Day.Friday;
		}
		else if (day.toLowerCase().equals("saturday")) 
		{
			this.theDay = Day.Saturday;
		}
		else if (day.toLowerCase().equals("sunday"))
		{
			this.theDay = Day.Sunday;
		}
		else 
		{
			System.out.println("There was an error setting up this Schedule Item.");
		}
	}
	
	public void setStartTime (int newStartTime)
	{
		this.startTime = newStartTime;
	}
	
	public void setEndTime (int newEndTime)
	{
		this.endTime = newEndTime;
	}
	
	public double getStartTimeDouble() 
	{
		return this.startTime;
	}
	
	public double getEndTimeDouble()
	{
		return this.endTime;
	}
	
	public String getDay() 
	{
		return this.theDay.name();
	}
	
	public void printScheduleItem()
	{
		System.out.println(this.theDay.name());
	}
	
	public Boolean detectConflict(ScheduleItem theItem) //returns true if there is a conflict
	{
		if (this.theDay.name() == theItem.getDay())
		{
			if (theItem.getEndTimeDouble() < this.startTime) //if the event finishes before this starts
			{
				System.out.println("No Conflict");
			}
			else if (theItem.getStartTimeDouble() < this.endTime) //if the event starts after this ends
			{
				System.out.println("No Conflict");
			}
			else {
				System.out.println("Unfortunatly there is an overlap of time");
				return true;
			}
		}
		return false;
	}
	
	public String getName() 
	{
		return this.name;
	}
	
	public void setName(String newName) 
	{
		this.name = newName;
	}
	
	public String getStringOfSchedule() {
		String output = "";
		if (this.theDay == Day.Monday) {
			output += "Monday";
		}
		else if (this.theDay == Day.Tuesday) {
			output += "Tuesday";
		}
		else if (this.theDay == Day.Wednesday) {
			output += "Wednesday";
		}
		else if (this.theDay == Day.Thursday) {
			output += "Thursday";
		}
		else if (this.theDay == Day.Friday) {
			output += "Friday";
		}
		else if (this.theDay == Day.Saturday) {
			output += "Saturday";
		}
		else if (this.theDay == Day.Sunday) {
			output += "Sunday";
		}
		System.out.println(this.theDay);
		output += " from " + Integer.toString(this.startTime) + ":00 to " + Integer.toString(this.endTime) + ":00.";
		return output;
	}
	
}














































