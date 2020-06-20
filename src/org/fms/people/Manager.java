package org.fms.people;

import java.io.Serializable;
import java.util.Scanner;

import org.fms.software.*;

public abstract class Manager extends Employee implements Serializable {
	
	//***********************Members***************************
	private double salary;
	protected Factory f;
	
	//***********************Methods***************************
	public void setFactory(Factory f) {
		this.f = f;
	}
	
	public Factory getFactory() {
		return this.f;
	}
	
	public double getSalary() 
	{
		return this.salary;
	}
	
	public void setSalary(double newSalary) 
	{
		this.salary = newSalary;
	}
	
	public void menu() 
	{
		Scanner readerManagerMenu = new Scanner(System.in);
		int menuOption;

		do 
		{
			System.out.println("Manager Menu");
			System.out.println("1) Update Info");
			System.out.println("2) Start Work");
			System.out.println("3) Go Back");
			menuOption = readerManagerMenu.nextInt();
			if (menuOption == 1) 
			{
				this.updateInfo();
			}
			else if (menuOption == 2)
			{
				this.workMenu();
			}
		} while (menuOption != 3 );
		readerManagerMenu.close();
		return;
	}

	private void updateInfo() 
	{
		Scanner readerInfo = new Scanner(System.in);
		int menuOption;

		do 
		{
			System.out.println("Manager Menu");
			System.out.println("1) Update Name");
			System.out.println("2) Update Username");
			System.out.println("3) Update Password");
			System.out.println("4) Go Back");
			menuOption = readerInfo.nextInt();
			if (menuOption == 1) 
			{
				System.out.println("What is your new name?");
				String newName = readerInfo.next();
				this.setName(newName);
			}
			else if (menuOption == 2)
			{
				System.out.println("Enter your new UserName");
				String newUserName = readerInfo.next();
				this.setUserName(newUserName);
			}
			else if (menuOption == 3)
			{
				System.out.println("Enter new Password");
				String newPass = readerInfo.next();
				this.setPassWord(newPass);
			}
		} while (menuOption != 4 );
		readerInfo.close();
		return;		
	}
	
	private void workMenu () 
	{

		Scanner readerInfo = new Scanner(System.in);
		int menuOption;

		do 
		{
			System.out.println("Manager Work Menu");
			System.out.println("1) Check inventory status");
			System.out.println("2) Check Worker Schedules");
			System.out.println("3) Check Goals");
			System.out.println("4) Go Back");
			menuOption = readerInfo.nextInt();
			if (menuOption == 1) 
			{
				System.out.println("What is your new name?");
				String newName = readerInfo.next();
				this.setName(newName);
			}
			else if (menuOption == 2)
			{
				System.out.println("Enter your new UserName");
				String newUserName = readerInfo.next();
				this.setUserName(newUserName);
			}
			else if (menuOption == 3) 
			{
				System.out.println("Enter new Password");
				String newPass = readerInfo.next();
				this.setPassWord(newPass);
			}
		} while (menuOption != 4 );
		
		readerInfo.close();
		
		return;		
	}
}
