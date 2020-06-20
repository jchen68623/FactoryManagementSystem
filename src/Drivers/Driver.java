package Drivers;

import org.fms.people.*;
import org.fms.GUIs.FactoryGUI;
import org.fms.hardware.*;
import org.fms.software.*;
import java.util.ArrayList;

public class Driver {
	public static void main(String[] args) {
		//new factory
		System.out.println("Creating Factory:");
		Factory f1 = new Factory();
		f1.setName("Dr. Dew Factory");
		f1.setFunds(500.00);
		System.out.println("Complete.\n");
	//new project manager
		ProjectManager pm1 = new ProjectManager();
		System.out.println("Creating Project Manager:");
		pm1.setName("Dustin");
		pm1.setUserName("Dustin");
		pm1.setPassWord("isCool");
		f1.addNewManager(pm1);
		System.out.println("Complete.");
		System.out.println("Project Manager is named " + pm1.getName() +".\n");
		
	//new materials
		System.out.println("Creating Materials:");
		//new raw materials
		RawMaterial rm1 = new RawMaterial();
		RawMaterial rm2 = new RawMaterial();
		RawMaterial rm3 = new RawMaterial();
		RawMaterial rm4 = new RawMaterial();
		RawMaterial rm5 = new RawMaterial();
		RawMaterial rm6 = new RawMaterial();
		
		rm1.setName("Molasses");
		rm1.setUnitCost(0.10);
		rm1.setVendor("Mrs.Buttersworth");
		rm1.setQuantity(0);
		
		rm2.setName("Water");
		rm2.setUnitCost(0.02);
		rm2.setVendor("WaterBois");
		rm2.setQuantity(0);
		
		rm3.setName("Raspberries");
		rm3.setUnitCost(0.05);
		rm3.setVendor("BerryGoodBros");
		rm3.setQuantity(0);
		
		rm4.setName("Apples");
		rm4.setUnitCost(0.03);
		rm4.setVendor("JohnnyAppleseed");
		rm4.setQuantity(0);
		
		rm5.setName("Plastic");
		rm5.setUnitCost(0.05);
		rm5.setVendor("Mr.Fantastic");
		rm5.setQuantity(0);
		
		rm6.setName("Labels");
		rm6.setUnitCost(0.02);
		rm6.setVendor("MablesLabels");
		rm6.setQuantity(0);
		
		//new processed materials
		ProcessedMaterial prm1 = new ProcessedMaterial();
		ProcessedMaterial prm2 = new ProcessedMaterial();
		ProcessedMaterial prm3 = new ProcessedMaterial();
		ProcessedMaterial prm4 = new ProcessedMaterial();

		prm1.setName("Sugar");
		prm1.setQuantity(0);
		
		prm2.setName("Carbonated Water");
		prm2.setQuantity(0);
		
		prm3.setName("Raspberry Apple Flavoring");
		prm3.setQuantity(0);
		
		prm4.setName("Plastic Bottles");
		prm4.setQuantity(0);
		
		//new assembled materials
		AssembledMaterial am1 = new AssembledMaterial();
		AssembledMaterial am2 = new AssembledMaterial();
				
		am1.setName("RaspApple Sodawater");
		am1.setQuantity(0);
		
		am2.setName("Labelled Bottles");
		am2.setQuantity(0);
		
		//new packaged materials
		PackagedMaterial pam1 = new PackagedMaterial();
		
		pam1.setName("Diet Dr. Dew");
		pam1.setUnitCost(1.00);
		pam1.setQuantity(0);
		
		System.out.println("Complete.\n");
		
		//add materials to factory
		f1.getListOfMaterials().add(rm1);
		f1.getListOfMaterials().add(rm2);
		f1.getListOfMaterials().add(rm3);
		f1.getListOfMaterials().add(rm4);
		f1.getListOfMaterials().add(rm5);
		f1.getListOfMaterials().add(rm6);
		f1.getListOfMaterials().add(prm1);
		f1.getListOfMaterials().add(prm2);
		f1.getListOfMaterials().add(prm3);
		f1.getListOfMaterials().add(prm4);
		f1.getListOfMaterials().add(am1);
		f1.getListOfMaterials().add(am2);
		f1.getListOfMaterials().add(pam1);
		
		//new machines
		System.out.println("Creating machines:");
		Machine m1 = new Machine(f1);
		m1.setName("Machine 1");
		f1.addNewMachine(m1);
		Machine m2 = new Machine(f1);
		m2.setName("Machine 2");
		f1.addNewMachine(m2);
		Machine m3 = new Machine(f1);
		m3.setName("Machine 3");
		f1.addNewMachine(m3);
		Machine m4 = new Machine(f1);
		m4.setName("Machine 4");
		f1.addNewMachine(m4);
		Machine m5 = new Machine(f1);
		m5.setName("Machine 5");
		f1.addNewMachine(m5);
		Machine m6 = new Machine(f1);
		m6.setName("Machine 6");
		f1.addNewMachine(m6);
		Machine m7 = new Machine(f1);
		m7.setName("Machine 7");
		f1.addNewMachine(m7);
		System.out.println("Complete.\n");
		
	//new recipes (for machines)
		System.out.println("Creating Recipes:");
		Recipe r1 = new Recipe();
		Recipe r2 = new Recipe();
		Recipe r3 = new Recipe();
		Recipe r4 = new Recipe();
		Recipe r5 = new Recipe();
		Recipe r6 = new Recipe();
		Recipe r7 = new Recipe();
		
		//recipe 1 makes 1 sugar from 2 molasses
		r1.setOutput(prm1, 1);
		r1.addInput(rm1, 2);
		
		//recipe 2 makes 1 carbonated water from 1 water
		r2.setOutput(prm2, 1);
		r2.addInput(rm2, 1);
		
		//recipe 3 makes raspapple flavoring from 2 raspberries and 1 apple
		r3.setOutput(prm3, 1);
		r3.addInput(rm3, 2);
		r3.addInput(rm4, 1);
		
		//recipe 4 makes 1 plastic bottle from 2 plastics
		r4.setOutput(prm4, 1);
		r4.addInput(rm5, 2);
		
		//recipe 5 makes 1 raspapple sodawater from 2 sugar, 2 carbonated water, and 1 raspapple flavoring
		r5.setOutput(am1, 1);
		r5.addInput(prm1, 2);
		r5.addInput(prm2, 2);
		r5.addInput(prm3, 1);
		
		//recipe 6 makes 1 labeled bottle from 1 plastic bottle and 1 label
		r6.setOutput(am2, 1);
		r6.addInput(prm4, 1);
		r6.addInput(rm6, 1);
		
		//recipe 7 makes 1 Diet Dr. Dew from 1 raspapple sodawater and 1 labelled bottle
		r7.setOutput(pam1, 1);
		r7.addInput(am1, 1);
		r7.addInput(am2, 1);
		System.out.println("Complete.\n");
		
		//add recipes to factory list
		f1.addRecipe(r1);
		f1.addRecipe(r2);
		f1.addRecipe(r3);
		f1.addRecipe(r4);
		f1.addRecipe(r5);
		f1.addRecipe(r6);
		f1.addRecipe(r7);
		
	//project manager can load new machine functionalities
		System.out.println("Installing Recipes into machines:\n");
		pm1.addRecipeToMachine(m1, r1);
		pm1.addRecipeToMachine(m2, r2);
		pm1.addRecipeToMachine(m3, r3);
		pm1.addRecipeToMachine(m4, r4);
		pm1.addRecipeToMachine(m5, r5);
		pm1.addRecipeToMachine(m6, r6);
		pm1.addRecipeToMachine(m7, r7);
		System.out.println("Complete.\n");
		
	//new shift manager
		System.out.println("Creating new Shift Manager:");
		ShiftManager sm1 = new ShiftManager();
		System.out.println("Complete.");
		
		sm1.setName("Ramos");
		sm1.setUserName("Ramos");
		sm1.setPassWord("isCool");
		f1.addNewManager(sm1);
		System.out.println("Shift Manager is named " + sm1.getName() + ".\n");
		
	//new workers
		System.out.println("Creating Workers:");
		Worker w1 = new Worker();
		Worker w2 = new Worker();
		Worker w3 = new Worker();
		Worker w4 = new Worker();
		Worker w5 = new Worker();
		Worker w6 = new Worker();
		Worker w7 = new Worker();
		
		w1.setName("John");
		w1.setUserName("John");
		w1.setPassWord("isCool");
		w1.addScheduleItem(new ScheduleItem("Monday", 9, 17));
		w1.addScheduleItem(new ScheduleItem("Wednesday", 9, 17));
		w1.addScheduleItem(new ScheduleItem("Friday", 9, 17));
		w2.setName("Ben");
		w2.setUserName("Ben");
		w2.setPassWord("isCool");
		w2.addScheduleItem(new ScheduleItem("Tuesday", 8, 16));
		w2.addScheduleItem(new ScheduleItem("Thursday", 8, 16));
		w3.setName("Dave");
		w3.setUserName("Dave");
		w3.setPassWord("isCool");
		w3.addScheduleItem(new ScheduleItem("Monday", 9, 17));
		w3.addScheduleItem(new ScheduleItem("Wednesday", 9, 17));
		w3.addScheduleItem(new ScheduleItem("Friday", 9, 17));
		w4.setName("Brandon");
		w4.setUserName("Brandon");
		w4.setPassWord("isCool");
		w4.addScheduleItem(new ScheduleItem("Tuesday", 8, 16));
		w4.addScheduleItem(new ScheduleItem("Thursday", 8, 16));
		w5.setName("Patrick");
		w5.setUserName("Patrick");
		w5.setPassWord("isCool");
		w5.addScheduleItem(new ScheduleItem("Monday", 9, 17));
		w5.addScheduleItem(new ScheduleItem("Wednesday", 9, 17));
		w5.addScheduleItem(new ScheduleItem("Friday", 9, 17));
		w6.setName("Jerry");
		w6.setUserName("Jerry");
		w6.setPassWord("isCool");
		w6.addScheduleItem(new ScheduleItem("Tuesday", 8, 16));
		w6.addScheduleItem(new ScheduleItem("Thursday", 8, 16));
		w7.setName("Andrew");
		w7.setUserName("Andrew");
		w7.setPassWord("isCool");
		w7.addScheduleItem(new ScheduleItem("Monday", 9, 17));
		w7.addScheduleItem(new ScheduleItem("Wednesday", 9, 17));
		w7.addScheduleItem(new ScheduleItem("Friday", 9, 17));
		f1.addNewWorker(w1);
		f1.addNewWorker(w2);
		f1.addNewWorker(w3);
		f1.addNewWorker(w4);
		f1.addNewWorker(w5);
		f1.addNewWorker(w6);
		f1.addNewWorker(w7);
		System.out.println("Complete.\n");
		
	//Shift manager sets new job assignments for workers
		System.out.println("Shift Manager is assigning jobs to workers:");
		sm1.assignJob(w1, new Processor());
		sm1.assignJob(w2, new Processor());
		sm1.assignJob(w3, new Processor());
		sm1.assignJob(w4, new Processor());
		sm1.assignJob(w5, new Assembler());
		sm1.assignJob(w6, new Assembler());
		sm1.assignJob(w7, new Packager());
		System.out.println("Complete.\n");

	//employees get their machines
		System.out.println("Shift Manager is assigning machines to workers:");
		sm1.assignMachine(w1, m1);
		sm1.assignMachine(w2, m2);
		sm1.assignMachine(w3, m3);
		sm1.assignMachine(w4, m4);
		sm1.assignMachine(w5, m5);
		sm1.assignMachine(w6, m6);
		sm1.assignMachine(w7, m7);
		System.out.println("Complete.\n");

	//add workers to shift manager list
		sm1.addWorker(w1);
		sm1.addWorker(w2);
		sm1.addWorker(w3);
		sm1.addWorker(w4);
		sm1.addWorker(w5);
		sm1.addWorker(w6);
		sm1.addWorker(w7);
		
	//add workers to project manager list	
		pm1.addWorker(w1);
		pm1.addWorker(w2);
		pm1.addWorker(w3);
		pm1.addWorker(w4);
		pm1.addWorker(w5);
		pm1.addWorker(w6);
		pm1.addWorker(w7);
		
	//manager assign goals
		System.out.println("Creating goals:");
		//goal to make 5 sodas
		Goal g1 = new Goal(pam1, 5);
		//goal to make 5 labeled bottles
		Goal g2 = new Goal(am2, 5);
		//goal to make 5 plastic bottles
		Goal g3 = new Goal(prm4, 5);
		//goal to make 5 rasp apple sodawater
		Goal g4 = new Goal(am1, 5);
		//goal to make 5 rasp apple flavoring
		Goal g5 = new Goal(prm3, 5);
		//goal to make 10 carbonated water
		Goal g6 = new Goal(prm2, 10);
		//goal to make 10 sugar
		Goal g7 = new Goal(prm1, 10);
		
		ArrayList<Goal> goals = new ArrayList<Goal>();
		
		goals.add(g1);
		goals.add(g2);
		goals.add(g3);
		goals.add(g4);
		goals.add(g5);
		goals.add(g6);
		goals.add(g7);
		System.out.println("Complete.\n");
		
		//This is the automated assign goals feature
		System.out.println("Commanding Shift Manager to assign goals:");
		sm1.setGoals(goals);
		
		sm1.autoAssignGoalsToWorkers();
		
		//Project manager orders 20 molasses
		pm1.orderRawMaterial(rm1, 20, f1);
	//Project manager orders 10 water
		pm1.orderRawMaterial(rm2, 10, f1);
	//Project manager orders 10 raspberries
		pm1.orderRawMaterial(rm3, 10, f1);
	//Project manager orders 5 apples
		pm1.orderRawMaterial(rm4, 5, f1);
		//Project manager orders 10 plastics
		pm1.orderRawMaterial(rm5, 10, f1);
	//Project manager orders 5 labels
		pm1.orderRawMaterial(rm6, 5, f1);
		
		//sm1.putWorkersToWork();
		
		//manager observe stock
		//for(Material m : f1.getListOfMaterials())
		//{
		//	if (m.getName().equals(pam1.getName()))
		//	{
		//		System.out.println(m.getQuantity() + " " + m.getName() + " created.");
		//	}
		//}

		new FactoryGUI("Factory Menu", f1);
	}
}
