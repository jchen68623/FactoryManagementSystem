package org.fms.hardware;

import org.fms.hardware.*;
import org.fms.software.*;
import org.fms.people.*;

import java.io.Serializable;
import java.util.ArrayList;

public class AssembledMaterial extends Material implements Serializable{
	
	//***********************Members***************************

	Job requiredJob;
	
	//********************Constructors*************************
	public AssembledMaterial()
	{
		this.requiredJob = new Assembler();
	}
	//***********************Methods***************************
	public Job getJob()
	{
		return this.requiredJob;
	}
	
}
