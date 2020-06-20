package org.fms.software;

import org.fms.people.*;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Job implements Serializable {
	
	//***********************Members***************************
	private String name;
	private ArrayList<Worker> workersWithJob = new ArrayList<Worker>();
	
	
	//***********************Methods***************************
	public void setName(String newName) 
	{
		this.name = newName;
	}
	
	public String getName() 
	{
		return this.name;
	}
	
	public ArrayList<Worker> getWorkersWithJob()
	{
		return this.workersWithJob;
	}
	
	public void addWorkerToJob(Worker newWorker) 
	{
		this.workersWithJob.add(newWorker);
	}
	
}
