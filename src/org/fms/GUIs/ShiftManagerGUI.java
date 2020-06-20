package org.fms.GUIs;

import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import org.fms.hardware.*;
import org.fms.people.*;
import org.fms.software.ScheduleItem;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.Serializable;

public class ShiftManagerGUI extends JFrame implements Serializable {
	private ShiftManager thisShiftManager;
	private Worker workerIDK; //used in add/remove schedule

	private JMenuBar menuBar;
	JFrame gui;
	
	private JMenu fileMenu;
	private JMenuItem fileExit;

	private JButton logOut;
	private JButton assignJobs;
	private JButton assignMachine;
	private JButton autoAssignGoal;
	private JButton autoWork;
	private JButton checkSchedule;
	private JButton editSchedule;
	
	private JButton selectMachine;
	private JButton close;
	private JButton addScheduleItem;
	private JButton removeScheduleItem;
	private JButton removeItem;
	
	public ShiftManagerGUI(String windowTitle, ShiftManager shManager1) 
	{
		super(windowTitle);
		
		this.thisShiftManager = shManager1;
		
		setSize(500, 400);
		
		setLayout(new FlowLayout(FlowLayout.CENTER));
		
		add(new JLabel("<HTML><center>Hello Shift Manager " + shManager1.getName() + "!" +
				"<BR>Choose an action from the menu.</center><BR></HTML>"));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		buildGUI();	
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public void buildGUI() 
	{
		menuBar = new JMenuBar();
     	
		fileMenu = new JMenu("File");
		fileExit = new JMenuItem("Exit");
		fileExit.addActionListener(new MenuListener());
		fileMenu.add(fileExit);
		menuBar.add(fileMenu);
		setJMenuBar(menuBar);
		
		Container pane = new Container();
		pane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		pane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipadx = 40;
		c.ipady = 30;
		c.gridx = 0;
		c.gridy = 0;
		assignMachine = new JButton("Assign Machine");
		assignMachine.addActionListener(new ButtonListener());
		pane.add(assignMachine, c);
		c.gridx = 1;
		c.gridy = 0;
		autoAssignGoal = new JButton("Auto Assign Goals");
		autoAssignGoal.addActionListener(new ButtonListener());
		pane.add(autoAssignGoal, c);
		c.gridx = 0;
		c.gridy = 1;
		checkSchedule = new JButton("Check Schedule");
		checkSchedule.addActionListener(new ButtonListener());
		pane.add(checkSchedule, c);
		c.gridx = 1;
		c.gridy = 1;
		editSchedule = new JButton("Edit Schedule");
		editSchedule.addActionListener(new ButtonListener());
		pane.add(editSchedule, c);
		c.gridx = 0;
		c.gridy = 2;
		autoWork = new JButton("Auto Work");
		autoWork.addActionListener(new ButtonListener());
		pane.add(autoWork, c);
		c.gridx = 1;
		c.gridy = 2;
		logOut = new JButton("Log out");
		logOut.addActionListener(new ButtonListener());
		pane.add(logOut, c);
		
		add(pane);
	}

	private class MenuListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) //this is the method MenuListener must implement, as it comes from the ActionListener interface.
		{
			JMenuItem source = (JMenuItem)(e.getSource());
			if (source.equals(fileExit))
			{
				System.exit(0);
			}
		}
	}

	private class ButtonListener implements ActionListener 
	{
		public void actionPerformed(ActionEvent e) //this is the method MenuListener must implement, as it comes from the ActionListener interface.
		{
			JButton source = (JButton) (e.getSource());
			
			if (source.equals(logOut)) {
				setVisible(false); 
				dispose();
			}
			else if (source.equals(assignJobs)) {
				assignJobsHandler();
			}
			else if (source.equals(assignMachine)) {
				assignMachineHandler();
			}
			else if (source.equals(autoAssignGoal)) {
				autoAssignGoalsHandler();
			}
			else if (source.equals(autoWork)) {
				autoWorkHandler();
			}
			else if (source.equals(checkSchedule)) {
				checkScheduleHandler();
			}
			else if (source.equals(editSchedule)) {
				editScheduleHandler();
			}
			else if (source.equals(selectMachine)) {
				selectMachineHandler();
			}
			else if (source.equals(close)) {
				gui.setVisible(false);
				gui.dispose();
			}
			else if (source.equals(addScheduleItem)) {
				addScheduelItemHandler();
			}
			else if (source.equals(removeScheduleItem)) {
				removeScheduleItemHandler();
			}
			else if (source.equals(removeItem)) {
				removeItemHandler();
			}
			else {
				//this means there is a button we havent made a case for yet
				JOptionPane.showMessageDialog(null, "Internal error. Option unavailable");
			}
			
		}

		private void selectMachineHandler() {
			JTextField workerName = new JTextField();
			JTextField machineNum = new JTextField();

			Object [] message = {
					"Worker Name:", workerName,
					"Machine Number: ", machineNum
			};

			int option = JOptionPane.showConfirmDialog(null, message, "Assign Worker", JOptionPane.OK_CANCEL_OPTION);

			if (option == JOptionPane.CANCEL_OPTION) {
				
			}
			else if (option == JOptionPane.OK_OPTION) {
				//find Worker
				Worker w1 = null;
				Machine m1 = null;
				for (int i = 0; i < thisShiftManager.getFactory().getWorkers().size(); i ++) {
					if (workerName.getText().equals(thisShiftManager.getFactory().getWorkers().get(i).getName())) {
						w1 = thisShiftManager.getFactory().getWorkers().get(i);
					}
				}
				//find Machine
				try {
					if (Integer.parseInt(machineNum.getText()) > 0 && Integer.parseInt(machineNum.getText()) <= thisShiftManager.getFactory().getMachines().size()) {
						m1 = thisShiftManager.getFactory().getMachines().get(Integer.parseInt(machineNum.getText()) - 1);
					}				}
				catch(NumberFormatException e)
				{
					Object [] errormessage = {"Must enter a valid integer for machine number."};
					JOptionPane.showMessageDialog(null, errormessage, "Error", JOptionPane.ERROR_MESSAGE );	
					selectMachineHandler();
				}
				if (w1 != null && m1 != null) {
					//pair machine to worker.
					thisShiftManager.assignMachine(w1, m1);
					//Success Message
					String output = "Worker, "  + w1.getName() + ", assigned to machine "+ m1.getName();
					JOptionPane.showMessageDialog(null, output);			
				}
				else if (w1 == null) {
					//handle errors
					JOptionPane.showMessageDialog(null, "No Worker with that name found.");			

				}
				else if (m1 == null) {
					//handle errors
					JOptionPane.showMessageDialog(null, "No machine matches this index.");			
				}
			}
		}

		private void assignJobsHandler() {
			// TODO Auto-generated method stub
			
		}

		private void assignMachineHandler() {
			selectMachine = new JButton("Select Machine");
			selectMachine.addActionListener(new ButtonListener());
			
			JFrame gui = new JFrame("Assign Machine");

			JPanel panel = new JPanel(new BorderLayout());

			JTextField textfield = new JTextField();
			textfield.setText("Machines:");

			JTextArea textarea = new JTextArea();
			textarea.setText(thisShiftManager.getStringMachines());

			selectMachine.setFont(new java.awt.Font("Dialog", 0, 15));
			selectMachine.setBorderPainted(false);
			selectMachine.setFocusable(false);
			selectMachine.setForeground(new java.awt.Color(255, 255, 255));
			selectMachine.setBackground(new java.awt.Color(0, 140, 255));

			panel.add(textfield, BorderLayout.PAGE_START);
			panel.add(textarea);
			panel.add(selectMachine, BorderLayout.SOUTH);
			
			gui.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			gui.setSize(300, 300);
			gui.setLocationRelativeTo(null);
			gui.add(panel);
			gui.setVisible(true);
		}

		private void autoAssignGoalsHandler() {
			if (thisShiftManager.autoAssignGoalsToWorkers()) {
				JOptionPane.showMessageDialog(null, "All goals have been assigned!");	
			}
			else {
				JOptionPane.showMessageDialog(null, "Looks like not all of the goals have been assigned because\nschedules are full. Either manually assign goals or wait for employees\nto complete their goals.");		
			}
		}

		private void autoWorkHandler() {
			thisShiftManager.putWorkersToWork();
			JOptionPane.showMessageDialog(null, "Workers have been put to work");			
		}

		private void checkScheduleHandler() {
			String output = thisShiftManager.getFactory().getStringofWorkerSchedules();
			
			
			gui = new JFrame("Schedules");

			JPanel panel = new JPanel(new BorderLayout());

			JTextField textfield = new JTextField();
			textfield.setText("Schedule: ");

			JTextArea textarea = new JTextArea();
			textarea.setText(output);

			close = new JButton("Go Back");
			close.setFont(new java.awt.Font("Dialog", 0, 15));
			close.setBorderPainted(false);
			close.setFocusable(true);
			close.setForeground(new java.awt.Color(255, 255, 255));
			close.setBackground(new java.awt.Color(0, 140, 255));
			close.addActionListener(new ButtonListener());

			panel.add(textfield, BorderLayout.PAGE_START);
			panel.add(textarea);
			panel.add(close, BorderLayout.SOUTH);
			
			JScrollPane scrPane = new JScrollPane(panel);
			scrPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			scrPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			
			gui.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			gui.setSize(300, 300);
			gui.setLocationRelativeTo(null);
			gui.add(scrPane);
			gui.setVisible(true);
		}

		private void editScheduleHandler() {
			JTextField name = new JTextField();

			Object [] message = {
					"Worker's Name: ", name,
			}; 
			int option = JOptionPane.showConfirmDialog(null, message, "Edit Schedule", JOptionPane.OK_CANCEL_OPTION);

			if (option == JOptionPane.CANCEL_OPTION) {
				
			}
			else if (option == JOptionPane.OK_OPTION) {
				Worker w1 = null;
				for (int i = 0; i < thisShiftManager.getFactory().getWorkers().size(); i++) {
					if (name.getText().equals(thisShiftManager.getFactory().getWorkers().get(i).getName())) {
						w1 = thisShiftManager.getFactory().getWorkers().get(i);
					}
				}
				if (w1 != null) {
					workerIDK = w1;
					addOrRemoveWindow();
				}
				else {
					// employ not found
					JOptionPane.showMessageDialog(null, "Unable to find that employee. please try again.");	
					editScheduleHandler();
				}
			}
		}

		private void addOrRemoveWindow() {
			JFrame addRemoveGUI = new JFrame();
			
			addRemoveGUI.setSize(340, 200);
			addRemoveGUI.setLayout(new FlowLayout(FlowLayout.CENTER));
			addRemoveGUI.add(new JLabel("<HTML><center>Welcome to the Factory Management System." +
					"<BR>Choose an action from the above menus.</center><BR></HTML>"));
			addRemoveGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			
			JPanel addRemovePanel = new JPanel();
			
			addScheduleItem = new JButton("Add Item");
			addScheduleItem.addActionListener(new ButtonListener());
			addRemovePanel.add(addScheduleItem);
			
			removeScheduleItem = new JButton("Remove Item");
			removeScheduleItem.addActionListener(new ButtonListener());
			addRemovePanel.add(removeScheduleItem);
			
			addRemoveGUI.add(addRemovePanel);
			
			addRemoveGUI.setVisible(true);
		}
		
		private void addScheduelItemHandler() {
			JTextField startTime = new JTextField();
			JTextField endTime = new JTextField();
			JTextField day = new JTextField();
			
			Object [] message = {
					"Start Time: ", startTime,
					"End Time: ", endTime,
					"Day:", day
			}; 
			
			int option = JOptionPane.showConfirmDialog(null, message, "Edit Schedule", JOptionPane.OK_CANCEL_OPTION);

			if (option == JOptionPane.CANCEL_OPTION) {
				
			}
			else if (option == JOptionPane.OK_OPTION) {
				workerIDK.addScheduleItem(new ScheduleItem(day.getText(), Integer.parseInt(startTime.getText()), Integer.parseInt(endTime.getText()) ));
				JOptionPane.showMessageDialog(null, "Added Item to " + workerIDK.getName() + "'s schedule!");	
			}
			
		}
		
		private void removeScheduleItemHandler() {
			removeItem = new JButton("Remove Item");
			removeItem.addActionListener(new ButtonListener());
			
			JFrame gui = new JFrame(workerIDK.getName() + "'s schedule: ");

			JPanel panel = new JPanel(new BorderLayout());

			JTextField textfield = new JTextField();
			textfield.setText("Remove Item: ");

			JTextArea textarea = new JTextArea();
			textarea.setText(workerIDK.getStringSchduleWithIndex());

//			JButton button = new JButton("Delete Request");
//			button.setFont(new java.awt.Font("Dialog", 0, 15));
//			button.setBorderPainted(false);
//			button.setFocusable(false);
//			button.setForeground(new java.awt.Color(255, 255, 255));
//			button.setBackground(new java.awt.Color(0, 140, 255));

			panel.add(textfield, BorderLayout.PAGE_START);
			panel.add(textarea);
			if (workerIDK.getSchedule().size() > 0) {
				panel.add(removeItem, BorderLayout.SOUTH);
			}
			
			gui.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			gui.setSize(300, 300);
			gui.setLocationRelativeTo(null);
			gui.add(panel);
			gui.setVisible(true);
		}
		
		private void removeItemHandler() {			
			JTextField indexText = new JTextField();

			Object [] message = {
					"Schedule Index: ", indexText
			}; 
			int option = JOptionPane.showConfirmDialog(null, message, "Edit Schedule", JOptionPane.OK_CANCEL_OPTION);

			if (option == JOptionPane.CANCEL_OPTION) {
				
			}
			else if (option == JOptionPane.OK_OPTION) {
				int index = Integer.parseInt(indexText.getText()) - 1;
				if (index >= 0 && index < workerIDK.getSchedule().size()) {
					workerIDK.removeScheduleItem(index);
				}
				else {
					JOptionPane.showMessageDialog(null, "Invalid Schedule Item. Please Try Again.");	
					removeItemHandler();
				}
				
			}
		}
		
	}
}



































