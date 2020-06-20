package org.fms.GUIs;

import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import org.fms.hardware.*;
import org.fms.people.*;
import org.fms.software.Recipe;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.Serializable;

public class ProjectManagerGUI extends JFrame implements Serializable {
	private ProjectManager thisProjectManager;

	private JMenuBar menuBar;
	
	private JPanel panel1;
	private JPanel panel2;
	private JPanel panel3;
	private JPanel panel4;

    private JMenu fileMenu;
	private JMenuItem fileExit;

	private JButton logOut;
	private JButton showWorkers;
	private JButton showFactoryFunds;
	private JButton showMaterials;
	private JButton orderProducts;
	private JButton sellProducts; 
	private JButton addRecipes; 
	private JButton viewRequest; 
	
	private JButton removeRequest;

	public ProjectManagerGUI(String windowTitle, ProjectManager pmanager1) 
	{
		super(windowTitle);
		
		this.thisProjectManager = pmanager1;
		
		setSize(500, 400);
		
		setLayout(new FlowLayout(FlowLayout.CENTER));
		
		add(new JLabel("<HTML><center>Hello Project Manager " + pmanager1.getName() + "!" +
				"<BR>Choose an action from the menu.</center><BR></HTML>"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		
	//Buttons
		Container pane = new Container();
		pane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		pane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipadx = 40;
		c.ipady = 30;
		c.gridx = 0;
		c.gridy = 0;
		viewRequest = new JButton("View Request");
		viewRequest.addActionListener(new ButtonListener());
		pane.add(viewRequest, c);
		c.gridx = 1;
		c.gridy = 0;
		showWorkers = new JButton("Check Schedule");
		showWorkers.addActionListener(new ButtonListener());
		pane.add(showWorkers, c);
		c.gridx = 0;
		c.gridy = 1;
		showFactoryFunds = new JButton("Show Funds");
		showFactoryFunds.addActionListener(new ButtonListener());
		pane.add(showFactoryFunds, c);
		c.gridx = 1;
		c.gridy = 1;
		showMaterials = new JButton("Show Materials");
		showMaterials.addActionListener(new ButtonListener());
		pane.add(showMaterials, c);
		c.gridx = 0;
		c.gridy = 2;
		orderProducts = new JButton("Order Products");
		orderProducts.addActionListener(new ButtonListener());
		pane.add(orderProducts, c);
		c.gridx = 1;
		c.gridy = 2;
		sellProducts = new JButton("Sell Products");
		sellProducts.addActionListener(new ButtonListener());
		pane.add(sellProducts, c);
		c.gridx = 0;
		c.gridy = 3;
		addRecipes = new JButton("Add Recipes");
		addRecipes.addActionListener(new ButtonListener());
		pane.add(addRecipes, c);
		c.gridx = 1;
		c.gridy = 3;
		logOut = new JButton("Log out");
		logOut.addActionListener(new ButtonListener());
		pane.add(logOut, c);
		
		setJMenuBar(menuBar);

		add(pane);

	}

	private ProjectManager getProjectManager() {
		return this.thisProjectManager;
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
			else if (source.equals(showWorkers)) {
				showWorkersHandler();
			}
			else if (source.equals(showFactoryFunds)) {
				showFactoryFundsHandler();
			}
			else if (source.equals(showMaterials)) {
				showMaterialsHandler();
			}
			else if (source.equals(orderProducts)) {
				orderProductsHandler();
			}
			else if (source.equals(sellProducts)) {
				sellProductsHandler();
			}
			else if (source.equals(addRecipes)) {
				addRecipesHandler();
			}
			else if (source.equals(viewRequest)) {
				viewRequestHandler();
			}
			else if (source.equals(removeRequest)) {
				removeRequestHandler();
			}
			else {
				//this means there is a button we havent made a case for yet
				JOptionPane.showMessageDialog(null, "Internal error. Option unavailable");
			}
		}

		private void showWorkersHandler() {
			JFrame gui = new JFrame("Current Workers");
			
			JPanel panel = new JPanel(new BorderLayout());

			JTextField textfield = new JTextField();
			textfield.setText("Workers:");

			JTextArea textarea = new JTextArea();
			textarea.setText(thisProjectManager.getFactory().getStringofWorkerSchedules());
			
			panel.add(textfield, BorderLayout.PAGE_START);
			panel.add(textarea);
			
			JScrollPane scrPane = new JScrollPane(panel);
			scrPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			scrPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			
			gui.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			gui.setSize(300, 300);
			gui.setLocationRelativeTo(null);
			gui.add(scrPane);
			gui.setVisible(true);
		}

		private void showFactoryFundsHandler() {
			removeRequest = new JButton("Factory Funds");
			removeRequest.addActionListener(new ButtonListener());
			
			JFrame gui = new JFrame("Factory Funds");

			JPanel panel = new JPanel(new BorderLayout());

			JTextField textfield = new JTextField();
			textfield.setText("Current Funds:");

			JTextArea textarea = new JTextArea();
			textarea.setText("$"+thisProjectManager.getFactory().getFunds());

			panel.add(textfield, BorderLayout.PAGE_START);
			panel.add(textarea);

			gui.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			gui.setSize(300, 300);
			gui.setLocationRelativeTo(null);
			gui.add(panel);
			gui.setVisible(true);
		}
		
		private void showMaterialsHandler() {
			removeRequest = new JButton("Available Material");
			removeRequest.addActionListener(new ButtonListener());
			
			JFrame gui = new JFrame("Available Material");

			JPanel panel = new JPanel(new BorderLayout());

			JTextField textfield = new JTextField();
			textfield.setText("Available Material:");

			JTextArea textarea = new JTextArea();
			textarea.setText(thisProjectManager.getFactory().getStringofMaterials());

			panel.add(textfield, BorderLayout.PAGE_START);
			panel.add(textarea);

			gui.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			gui.setSize(300, 300);
			gui.setLocationRelativeTo(null);
			gui.add(panel);
			gui.setVisible(true);
		}
		
		private void orderProductsHandler() {
			JTextField product = new JTextField();
			JTextField quantity = new JTextField();

			Object [] message = {
					"Product:", product,
					"Quantity:", quantity
			};
			int option = JOptionPane.showConfirmDialog(null, message, "Order Product", JOptionPane.OK_CANCEL_OPTION);

			if (option == JOptionPane.CANCEL_OPTION) {
				
			}
			else if (option == JOptionPane.OK_OPTION) {
				try {
					Integer.parseInt(quantity.getText());
				}
				catch(NumberFormatException e)
				{
					Object [] errormessage = {"Must enter an integer for quantity."};
					JOptionPane.showMessageDialog(null, errormessage, "Error", JOptionPane.ERROR_MESSAGE );	
					orderProductsHandler();
				}
				if(product.getText().equals("") || quantity.getText().equals(""))
				{
					Object [] errormessage = {"All fields must be filled out."};
					JOptionPane.showMessageDialog(null, errormessage, "Error", JOptionPane.ERROR_MESSAGE );	
					orderProductsHandler();
				}
				else if (Integer.parseInt(quantity.getText()) <= 0)
				{
					Object [] errormessage = {"Quantity must be greater than 0."};
					JOptionPane.showMessageDialog(null, errormessage, "Error", JOptionPane.ERROR_MESSAGE );	
					orderProductsHandler();
				}
				
				else
				{
					boolean foundMatch = false;
					for (Material m : getProjectManager().getFactory().getListOfMaterials()) 
					{
						if(m instanceof RawMaterial)
						{
							if(m.getName().equals(product.getText()))
							{
								foundMatch = true;
								if(thisProjectManager.orderRawMaterial((RawMaterial)m, Integer.parseInt(quantity.getText()), thisProjectManager.getFactory()))
								{
									Object [] successmessage = {"" + quantity.getText() + " " + m.getName() + " successfully ordered."};
									JOptionPane.showMessageDialog(null, successmessage, "Order Successful", JOptionPane.INFORMATION_MESSAGE );
								}
								else
								{
									Object [] errormessage = {"Unable to order " + quantity.getText() + " " + m.getName() + " due to insufficient funds."};
									JOptionPane.showMessageDialog(null, errormessage, "Order Failed", JOptionPane.ERROR_MESSAGE );
								}
							}
						}
					}
					if(!foundMatch)
					{
						Object [] errormessage = {"Entered product does not match any existing Raw Product"};
						JOptionPane.showMessageDialog(null, errormessage, "Error", JOptionPane.ERROR_MESSAGE );	
						orderProductsHandler();
					}
				}
			}
		}

		private void sellProductsHandler() {
			JTextField product = new JTextField();
			JTextField quantity = new JTextField();

			Object [] message = {
					"Product:", product,
					"Quantity:", quantity
			};
			int option = JOptionPane.showConfirmDialog(null, message, "Sell Product", JOptionPane.OK_CANCEL_OPTION);

			if (option == JOptionPane.CANCEL_OPTION) {
				
			}
			else if (option == JOptionPane.OK_OPTION) {
				try {
					Integer.parseInt(quantity.getText());
				}
				catch(NumberFormatException e)
				{
					Object [] errormessage = {"Must enter an integer for quantity."};
					JOptionPane.showMessageDialog(null, errormessage, "Error", JOptionPane.ERROR_MESSAGE );	
					orderProductsHandler();
				}
				if(product.getText().equals("") || quantity.getText().equals(""))
				{
					Object [] errormessage = {"All fields must be filled out."};
					JOptionPane.showMessageDialog(null, errormessage, "Error", JOptionPane.ERROR_MESSAGE );	
					orderProductsHandler();
				}
				else if (Integer.parseInt(quantity.getText()) <= 0)
				{
					Object [] errormessage = {"Quantity must be greater than 0."};
					JOptionPane.showMessageDialog(null, errormessage, "Error", JOptionPane.ERROR_MESSAGE );	
					orderProductsHandler();
				}
				
				else
				{
					boolean foundMatch = false;
					int numsold = 0;
					for (Material m : getProjectManager().getFactory().getListOfMaterials()) 
					{
						if(m instanceof PackagedMaterial)
						{
							if(m.getName().equals(product.getText()))
							{
								foundMatch = true;
								numsold = thisProjectManager.sellProducts((PackagedMaterial) m, thisProjectManager.getFactory(), Integer.parseInt(quantity.getText()));
								if(numsold == Integer.parseInt(quantity.getText())){
									Object [] successmessage = {"Sold " + numsold + " of " + m.getName() + " successfully."};
									JOptionPane.showMessageDialog(null, successmessage, "Selling Successful", JOptionPane.INFORMATION_MESSAGE );
								}
								else if (numsold > 0)
								{
									Object [] successmessage = {"Sold " + numsold + " out of requested "+ quantity.getText() + " " + m.getName() + " successfully."};
									JOptionPane.showMessageDialog(null, successmessage, "Selling Successful", JOptionPane.INFORMATION_MESSAGE );
								}
								else
								{
									Object [] errormessage = {"Unable to sell any " + m.getName() + " due to insufficient product."};
									JOptionPane.showMessageDialog(null, errormessage, "Selling Failed", JOptionPane.ERROR_MESSAGE );
								}
							}
						}
					}
					if(!foundMatch)
					{
						Object [] errormessage = {"Entered product does not match any existing Packaged Product"};
						JOptionPane.showMessageDialog(null, errormessage, "Error", JOptionPane.ERROR_MESSAGE );	
						orderProductsHandler();
					}
				}
			}
		}

		private void addRecipesHandler() {
			JTextField recipe = new JTextField();
			JTextField machine = new JTextField();

			Object [] message = {
					"Recipe:", recipe,
					"Machine:", machine
			};
			int option = JOptionPane.showConfirmDialog(null, message, "Add Recipe", JOptionPane.OK_CANCEL_OPTION);
			
			if (option == JOptionPane.CANCEL_OPTION) {
				
			}
			else if (option == JOptionPane.OK_OPTION) {
				if(recipe.getText().equals("") || machine.getText().equals(""))
				{
					Object [] errormessage = {"Error: All fields must be filled out."};
					JOptionPane.showMessageDialog(null, errormessage, "Error", JOptionPane.ERROR_MESSAGE );	
					addRecipesHandler();
				}
				boolean recipeMatch = false;
				for(Recipe r : thisProjectManager.getFactory().getRecipes())
				{
					System.out.println("Factory Recipes: " + r.getMaterialOut().getName());
					if(recipe.getText().equals(r.getMaterialOut().getName()))
					{
						System.out.println("Match for: " + recipe.getText());
						recipeMatch = true;
						boolean machineMatch = false;
						for(Machine m : thisProjectManager.getFactory().getMachines())
						{
							System.out.println("Factory Machine: " + m.getName());
							if(machine.getText().equals(m.getName()))
							{
								System.out.println("Match for: " + machine.getText());
								machineMatch = true;
								boolean hasRecipe = false;
								for(Recipe mr : m.getRecipes())
								{
									System.out.println("Machine Recipes: " + mr.getMaterialOut().getName());
									if(recipe.getText().equals(mr.getMaterialOut().getName()))
									{
										hasRecipe = true;
										System.out.println(hasRecipe);
									}
								}
								if(!hasRecipe)
								{
									thisProjectManager.addRecipeToMachine(m, r);
									Object [] successmessage = {"Recipe successfully added to machine."};
									JOptionPane.showMessageDialog(null, successmessage, "Recipe Added Successfully", JOptionPane.INFORMATION_MESSAGE );	
								}
								else
								{
									Object [] errormessage = {"Error: Machine already has this recipe."};
									JOptionPane.showMessageDialog(null, errormessage, "Error", JOptionPane.ERROR_MESSAGE );	
									addRecipesHandler();
								}
							}
						}
						if(!machineMatch)
						{
							Object [] errormessage = {"Error: No machine matches entered name."};
							JOptionPane.showMessageDialog(null, errormessage, "Error", JOptionPane.ERROR_MESSAGE );	
							addRecipesHandler();
						}
					}
				}
				if(!recipeMatch)
				{
					Object [] errormessage = {"Error: No recipe matches entered name."};
					JOptionPane.showMessageDialog(null, errormessage, "Error", JOptionPane.ERROR_MESSAGE );	
					addRecipesHandler();
				}
			}
		}

		private void viewRequestHandler() {
			removeRequest = new JButton("Remove Request");
			removeRequest.addActionListener(new ButtonListener());
			
			JFrame gui = new JFrame("Open Request");

			JPanel panel = new JPanel(new BorderLayout());

			JTextField textfield = new JTextField();
			textfield.setText("Request:");

			JTextArea textarea = new JTextArea();
			textarea.setText(thisProjectManager.getStringRequest());

//			JButton button = new JButton("Delete Request");
//			button.setFont(new java.awt.Font("Dialog", 0, 15));
//			button.setBorderPainted(false);
//			button.setFocusable(false);
//			button.setForeground(new java.awt.Color(255, 255, 255));
//			button.setBackground(new java.awt.Color(0, 140, 255));

			panel.add(textfield, BorderLayout.PAGE_START);
			panel.add(textarea);
			panel.add(removeRequest, BorderLayout.SOUTH);
			
			gui.setDefaultCloseOperation(gui.DISPOSE_ON_CLOSE);
			gui.setSize(300, 300);
			gui.setLocationRelativeTo(null);
			gui.add(panel);
			gui.setVisible(true);
		}		
		
		private void removeRequestHandler () {
			JTextField requestIndex = new JTextField();
			
			Object [] message = {
					"Request: ", requestIndex
			};
			int option = JOptionPane.showConfirmDialog(null, message, "Remove Request", JOptionPane.OK_CANCEL_OPTION);
			
			if (option == JOptionPane.CANCEL_OPTION) {
				
			}
			else if (option == JOptionPane.OK_OPTION) {
				try {
					Integer.parseInt(requestIndex.getText());
				}
				catch(NumberFormatException e)
				{
					Object [] errormessage = {"Must enter an integer for request number."};
					JOptionPane.showMessageDialog(null, errormessage, "Error", JOptionPane.ERROR_MESSAGE );	
					orderProductsHandler();
				}
				if (Integer.parseInt(requestIndex.getText()) <= thisProjectManager.getRequestList().size() && Integer.parseInt(requestIndex.getText()) > 0) {
					thisProjectManager.removeRequest(Integer.parseInt(requestIndex.getText()) - 1);
					JOptionPane.showMessageDialog(null, "Successfully deleted!");
				}
				else {
					JOptionPane.showMessageDialog(null, "Invalid entry. Please try again.");					
				}
			}
		}
	}
}



































