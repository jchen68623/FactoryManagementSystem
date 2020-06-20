package org.fms.software;

import java.io.Serializable;

import org.fms.hardware.Material;
import org.fms.people.Worker;

public class Request implements Serializable {
	//***********************Members***************************
	private Material material;
	private int quantity;
	private Worker origionator;
	
	
	//***********************Methods***************************
	public Request(Material material, int quantity, Worker w1){
		this.material = material;
		this.quantity = quantity;
		this.origionator = w1;
	}
	
	public Material getMaterial() {
		return material;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public Worker getOrigionator() {
		return this.origionator;
	}
}
