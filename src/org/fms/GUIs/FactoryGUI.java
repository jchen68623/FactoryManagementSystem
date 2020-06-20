package org.fms.GUIs;

import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import org.fms.hardware.*;
import org.fms.people.*;
import org.fms.software.*;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.Serializable;

public class FactoryGUI extends JFrame implements Serializable 
{
	private Factory factory;

	private JMenuBar menuBar;
	private JPanel panel;

	// File submenus
	private JMenu fileMenu;
	private JMenuItem fileSave; 		//JMenuItem objects are added to JMenu objects as the drop down selections.
	private JMenuItem fileLoad;
	private JMenuItem fileExit;

	private JButton employeeLogIn;
	private JButton managerLogIn;

	public FactoryGUI(String windowTitle, Factory fact1) 
	{
		super(windowTitle);
		
		this.factory = fact1;
		
		setSize(400, 250);
		
		setLayout(new FlowLayout(FlowLayout.CENTER));
		
		add(new JLabel("<HTML><center>Welcome to the Factory Management System." +
				"<BR>Log in by selecting your employee type.</center><BR></HTML>"));
		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		buildGUI();	
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public void buildGUI() 
	{
		menuBar = new JMenuBar();
		//
		Container pane = new Container();
		pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		pane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipadx = 40;
		c.ipady = 30;
		//
		
		fileMenu = new JMenu("File");

		fileSave = new JMenuItem("Save");
		fileLoad = new JMenuItem("Load");
		fileExit = new JMenuItem("Exit");

		employeeLogIn = new JButton("Employee");
		managerLogIn = new JButton("Manager");

		fileSave.addActionListener(new MenuListener());		
		fileLoad.addActionListener(new MenuListener());	
		fileExit.addActionListener(new MenuListener());

		employeeLogIn.addActionListener(new ButtonListener());
		managerLogIn.addActionListener(new ButtonListener());

		fileMenu.add(fileSave);
		fileMenu.add(fileLoad);
		fileMenu.add(fileExit);

		menuBar.add(fileMenu);

		pane.add(employeeLogIn, c);
		pane.add(managerLogIn, c);

		setJMenuBar(menuBar);

		add(pane);
	}

	private class MenuListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) //this is the method MenuListener must implement, as it comes from the ActionListener interface.
		{
			JMenuItem source = (JMenuItem)(e.getSource());

			if(source.equals(fileSave))
			{
				handleSave();
			}
			else if (source.equals(fileLoad))
			{
				handleLoad();
			}
			else if (source.equals(fileExit))
			{
				handleExit();
			}
			else {
				System.out.println("Error in event handler");
			}
		}
		private void handleSave()
		{
			Factory.saveData(factory);
		}
		
		private void handleLoad()
		{
			factory = Factory.loadData();
		}
		
		private void handleExit() 
		{
			System.exit(0); 
		}
	}

	private class ButtonListener implements ActionListener 
	{
		public void actionPerformed(ActionEvent e) //this is the method MenuListener must implement, as it comes from the ActionListener interface.
		{
			JButton source = (JButton) (e.getSource());
			if (source.equals(employeeLogIn)) {
				employeeLogInHandler();
			}
			else if (source.equals(managerLogIn)) {
				managerLogInHandler();
			}
			else {
				System.out.println("Button Classification Problem");
			}
		}

		private void employeeLogInHandler() {
			JTextField userName = new JTextField();
			JPasswordField password = new JPasswordField();

			Object [] message = {
					"UserName:", userName,
					"Password:", password
			};

			int option = JOptionPane.showConfirmDialog(null, message, "Employee Log In", JOptionPane.OK_CANCEL_OPTION);

			if (option == JOptionPane.CANCEL_OPTION) {
				
			}
			else if (option == JOptionPane.OK_OPTION) {
				Boolean crediatialsWorked = false;
//				System.out.println("Text: " + password.getText());
//				System.out.println("getPassword" + password.getPassword());
				for (int i = 0; i < factory.getWorkers().size(); i++) {

					crediatialsWorked = factory.getWorkers().get(i).checkCredentials(userName.getText(), password.getText()); //TODO: use password hash instead
					if (crediatialsWorked) {
						WorkerGUI workerGui = new WorkerGUI("Worker Home Page", factory.getWorkers().get(i));
						break;
					}
				}
				if (!crediatialsWorked) {
					JOptionPane.showMessageDialog(null, "Log in failed. Try Again.");
					employeeLogInHandler();
				}
			}
		}
		
		private void managerLogInHandler() {
			JTextField userName = new JTextField();
			JPasswordField password = new JPasswordField();

			Object [] message = {
					"UserName:", userName,
					"Password:", password
			};
			int option = JOptionPane.showConfirmDialog(null, message, "Manager Log In", JOptionPane.OK_CANCEL_OPTION);
			
			if (option == JOptionPane.CANCEL_OPTION) {
				
			}
			else if (option == JOptionPane.OK_OPTION) {
				Boolean crediatialsWorked = false;
				for (int i = 0; i < factory.getManagers().size(); i++) 
				{
					crediatialsWorked = factory.getManagers().get(i).checkCredentials(userName.getText(), password.getText()); //TODO: use password hash instead
					if (crediatialsWorked) {
						if(factory.getManagers().get(i) instanceof ShiftManager )
						{
							ShiftManagerGUI managerGUI = new ShiftManagerGUI("Shift Manager Home Page", (ShiftManager) factory.getManagers().get(i));
						}
						else if(factory.getManagers().get(i) instanceof ProjectManager )
						{
							ProjectManagerGUI managerGUI = new ProjectManagerGUI("Project Manager Home Page", (ProjectManager) factory.getManagers().get(i));
						}
						break;
					}
				}
				if (!crediatialsWorked) {
					JOptionPane.showMessageDialog(null, "Log in failed. Try Again.");
					managerLogInHandler();
				}
			}
		}
	}
}






















