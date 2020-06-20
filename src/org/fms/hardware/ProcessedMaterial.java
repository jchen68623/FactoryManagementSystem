package org.fms.hardware;

import org.fms.hardware.*;
import org.fms.software.*;
import org.fms.people.*;

import java.io.Serializable;
import java.util.ArrayList;

public class ProcessedMaterial extends Material implements Serializable {
	//***********************Members***************************

	Job requiredJob;
	
	//********************Constructors*************************
	public ProcessedMaterial()
	{
		this.requiredJob = new Processor();
	}
	//***********************Methods***************************
	public Job getJob()
	{
		return this.requiredJob;
	}
}
