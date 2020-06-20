package org.fms.GUIs;

import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import org.fms.hardware.*;
import org.fms.people.*;
import org.fms.software.Goal;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.Serializable;

public class WorkerGUI extends JFrame implements Serializable {
	
	private Worker thisWorker;
	
	private JFrame gui;
	private JMenuBar menuBar;
	
    private JMenu fileMenu;
	private JMenuItem fileExit;
	
	private JButton logOut;
	private JButton checkTasks;
	private JButton accessMachine;
	private JButton makeRequest;
	
	private JButton close;
	
	public WorkerGUI(String windowTitle, Worker worker1) 
	{
		super(windowTitle);
		
		this.thisWorker = worker1;
		
		setSize(400, 300);
		
		setLayout(new FlowLayout(FlowLayout.CENTER));
		
		add(new JLabel("<HTML><center>Hello Worker " + worker1.getName() + "!" +
				"<BR>Choose an action from the menu.</center><BR></HTML>"));

		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		buildGUI();	
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public void buildGUI() 
	{
	//menu bar items
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
		makeRequest = new JButton("Make Request");
		makeRequest.addActionListener(new ButtonListener());
		pane.add(makeRequest, c);
		c.gridx = 1;
		c.gridy = 0;
		checkTasks = new JButton("Check Tasks");
		checkTasks.addActionListener(new ButtonListener());
		pane.add(checkTasks, c);
		c.gridx = 0;
		c.gridy = 1;
		accessMachine = new JButton("Access Machine");
		accessMachine.addActionListener(new ButtonListener());
		pane.add(accessMachine, c);
		c.gridx = 1;
		c.gridy = 1;
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
	
	
	
	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) //this is the method MenuListener must implement, as it comes from the ActionListener interface.
		{
			JButton source = (JButton) (e.getSource());
			if (source.equals(logOut)) {
				setVisible(false); 
				dispose();
			}
			else if (source.equals(checkTasks)) {
				checkScheduleHandler();
			}
			else if (source.equals(accessMachine)) {
				accessMachineHandler();
			}
			else if (source.equals(makeRequest)) {
				makeRequestHandler();
			}
			else if (source.equals(close)) {
				setVisible(false); 
				dispose();
			}
		}

		private void checkScheduleHandler() {
//			thisWorker.getStringOfTask()
			
			String output = thisWorker.getStringOfTask();
			
			
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
		
		private void accessMachineHandler() {
			if (thisWorker.getMachine() != null) {
				new MachineGUI("MachineGUI", thisWorker.getMachine());
			}
			else {
				JOptionPane.showMessageDialog(null, "No machine Assigned");
			}
		}
		
		private void makeRequestHandler() {
			JTextField material = new JTextField();
			JTextField quantity = new JTextField();
			
			Object [] message = {
					"Material:", material,
					"Quantity:", quantity
			};
			int option = JOptionPane.showConfirmDialog(null, message, "Make materials", JOptionPane.OK_CANCEL_OPTION);
			
			if (option == JOptionPane.CANCEL_OPTION) {
				
			}
			else if (option == JOptionPane.OK_OPTION) {
				// match material
				Material inputMaterial = null;
				for (int i = 0; i < thisWorker.getFactory().getListOfMaterials().size(); i++) {
					if (material.getText().equals(thisWorker.getFactory().getListOfMaterials().get(i).getName())) {
						inputMaterial = thisWorker.getFactory().getListOfMaterials().get(i);
					}
				}
				if (inputMaterial != null) {
					thisWorker.getProjectManager().makeRequest(inputMaterial, Integer.parseInt(quantity.getText()), thisWorker);
					Object [] successmsg = {"Request has been submitted."};
					JOptionPane.showMessageDialog(null, successmsg, "Request Successful", JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					JOptionPane.showMessageDialog(null, "Error, Invalid Material", "Error", JOptionPane.INFORMATION_MESSAGE);

				}
			}
		}
	}
}


















































