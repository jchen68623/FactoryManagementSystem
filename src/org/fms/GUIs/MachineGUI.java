package org.fms.GUIs;

import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import org.fms.GUIs.*;
import org.fms.hardware.*;
import org.fms.people.*;
import org.fms.software.*;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.Serializable;

public class MachineGUI extends JFrame implements Serializable {
	private Machine thisMachine;
	private JFrame gui;
	
	private JMenuBar menuBar;
	private JPanel panel1;
	private JPanel panel2;

    private JMenu fileMenu;
	private JMenuItem fileExit;
	
	private JButton exitButton;
	private JButton listRecipes;
	private JButton startWorking;
	private JButton checkMaterials;
	private JButton close;

	
	public MachineGUI(String windowTitle, Machine mac1) {
		super(windowTitle);
		
		this.thisMachine = mac1;
		
		setSize(400, 300);
		
		setLayout(new FlowLayout(FlowLayout.CENTER));
		
		add(new JLabel("<HTML><center>You are logged into " + this.thisMachine.getName() + ".</center><BR></HTML>"));

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
		checkMaterials = new JButton("Check Materials");
		checkMaterials.addActionListener(new ButtonListener());
		pane.add(checkMaterials, c);
		c.gridx = 1;
		c.gridy = 0;
		listRecipes = new JButton("List Recipes");
		listRecipes.addActionListener(new ButtonListener());
		pane.add(listRecipes, c);
		c.gridx = 0;
		c.gridy = 1;
		startWorking = new JButton("Start Work");
		startWorking.addActionListener(new ButtonListener());
		pane.add(startWorking, c);
		c.gridx = 1;
		c.gridy = 1;
		exitButton = new JButton("Exit");
		exitButton.addActionListener(new ButtonListener());
		pane.add(exitButton, c);
		
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
			if (source.equals(exitButton)) {
				setVisible(false); 
				dispose();
			}
			else if (source.equals(listRecipes)) {
				listRecipesHandler();
			}
			else if (source.equals(startWorking)) {
				goHandler();
			}
			else if (source.equals(checkMaterials)) {
				checkIngrediantQuantities();
			}
			else if (source.equals(close)) {
				setVisible(false);
				dispose();
			}
		}
		
		public void listRecipesHandler() {
//			thisMachine.getStringRecipes()

			String output = thisMachine.getStringRecipes();
			
			gui = new JFrame("Recipes");

			JPanel panel = new JPanel(new BorderLayout());

			JTextField textfield = new JTextField();
			textfield.setText("Recipes: ");

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
		
		public void checkIngrediantQuantities() {
			JTextArea a = new JTextArea();
			Factory factory = thisMachine.getFactory();
			a.setText(factory.getStringofMaterials());
			JFrame frame = new JFrame();
			JPanel container = new JPanel(new GridBagLayout());
			GridBagConstraints c = new GridBagConstraints();
			container.add(a);
			frame.setTitle("Materials");
			JScrollPane scrPane = new JScrollPane(container);
			scrPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			scrPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			frame.getContentPane().add(scrPane);
			frame.setSize(350, 300);
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
		}
		
		private void goHandler() {
			JTextField recipeInt = new JTextField();
			JTextField quantity = new JTextField();
			
			Object [] message = {
					"Recipe #:", recipeInt,
					"Quantity:", quantity
			};
			
			int option = JOptionPane.showConfirmDialog(null, message, "Make materials", JOptionPane.OK_CANCEL_OPTION);
			
			if (option == JOptionPane.CANCEL_OPTION) {
				
			}
			else if (option == JOptionPane.OK_OPTION) {
				// First Identify Recipe
				// Base 1 not base 0
				int inputIndex = -1;
				Boolean enoughMaterial = false;
				try {
					Integer.parseInt(quantity.getText());
				}
				catch(NumberFormatException e)
				{
					Object [] errormessage = {"Must enter an integer for quantity."};
					JOptionPane.showMessageDialog(null, errormessage, "Error", JOptionPane.ERROR_MESSAGE );	
					goHandler();
				}
				try {
					Integer.parseInt(recipeInt.getText());
					if (Integer.parseInt(recipeInt.getText()) <= thisMachine.getRecipes().size() && Integer.parseInt(recipeInt.getText()) > 0) { //if (input index <= number of recipes and inputIndex > 0) 
						inputIndex = Integer.parseInt(recipeInt.getText()) - 1; //Change to base 0
					}
				}
				catch(NumberFormatException e)
				{
					Object [] errormessage = {"Must enter an integer for recipe number."};
					JOptionPane.showMessageDialog(null, errormessage, "Error", JOptionPane.ERROR_MESSAGE );	
					goHandler();
				}
				
				// Unable to Identify
				if (inputIndex == -1) {
					JOptionPane.showMessageDialog(null, "Invalid Recipe int. Try Again.");
					goHandler();
					return;
				}
				// Check Ingredients quantity For recipe
				else {
					enoughMaterial = thisMachine.produceMaterial(thisMachine.getRecipes().get(inputIndex).getMaterialOut(), Integer.parseInt(quantity.getText()));
				}
				// Not enough of an ingredient
				if (enoughMaterial) {
					// Successful message
					JOptionPane.showMessageDialog(null, (quantity.getText() + " units of " + thisMachine.getRecipes().get(inputIndex).getMaterialOut().getName() + " were made!" ));
				}
				else
				{
					//failure message
					JOptionPane.showMessageDialog(null, "Not enough ingrediants.");

				}
			}
		}
	}
}


















































