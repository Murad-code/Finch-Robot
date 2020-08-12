package controller;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import com.sun.glass.events.KeyEvent;

import container.ConversionEvent;
import container.FormEvent;
import model.ModelListener;
import model.Database;
import model.ErrorListener;
import model.Model;
import view.FormListener;
import view.FormPanel;
import view.TablePanel;

public class Controller extends JFrame {

	private FormPanel formPanel;
	private TablePanel tablePanel;
	private Model model;
	private Database db;
	private JFileChooser fileChooser;

	String hex, decimal, binary, octal, speed, red, green, blue;
	int time;
	ArrayList<String> hexList;
	private boolean finchMoving = false;

	public Controller() {
		super("Finch Dance"); // Title for the program
		setJMenuBar(CreateMenuBar()); // Creates a toolbar and sets layout by calling function CreateMenuBar
		setLayout(new BorderLayout());

		// Initialises the other classes so they can execute their constructors and set the GUI
		formPanel = new FormPanel();
		model = new Model();
		tablePanel = new TablePanel();
		db = new Database();
		
		fileChooser = new JFileChooser(); // This is used to open the file directory

		tablePanel.SetData(db.GetResults()); // Sets up the table for GUI

		// Applies the border layout to allow the window to resize equally
		add(formPanel, BorderLayout.WEST);
		add(tablePanel, BorderLayout.CENTER);
		
		// Allows user to press 'Enter' key instead of clicking the 'Run' button
		getRootPane().setDefaultButton(formPanel.runBtn); 

		// Sets the default and minimum window size when program is launched
		setMinimumSize(new Dimension(500, 400));
		setSize(1000, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Closing the window terminates the program
		setVisible(true); // Displays the GUI

		// Creates a listener/connection to the FormPanel class
		// When an event occurs in the FormPanel, the Controller executes the following
		formPanel.setFormListener(new FormListener() { 
			public void FormEventOccurred(FormEvent e) {
				hex = e.getHex();
				model.Validation(hex);
			}
		});

		// Creates a listener/connection to the Model class
		// When an error event occurs in the FormPanel, the Controller executes the following
		model.setErrorListener(new ErrorListener() {
			public void ErrorEventOccurred() {
				Error();
			}
		});
		
		// Creates a listener/connection to the Model class
		// When an event occurs in the FormPanel, the Controller executes the following
		model.setModelListener(new ModelListener() {
			public void ConversionEventOccurred(ConversionEvent e) {				
				e.addCount();
				db.AddResult(e);
				
				// Retrieving values from the ConversionEvent container class
				decimal = e.getDecimal();
				binary = e.getBinary();
				octal = e.getOctal();
				speed = e.getSpeed();
				red = e.getRed();
				green = e.getGreen();
				blue = e.getBlue();
				hexList = e.getHexList();
							
				// Passing the ConversionEvent values into the FormPanel to display in the GUI
				formPanel.ShowResults(decimal, binary, octal, hex, speed, red, green, blue);
				tablePanel.Refresh(); // Updates the values to display in the table

				model.FinchController(); // Calls the function responsible for making the finch dance
				
				// Retrieves value set from FinchController to check if finch has stopped moving
				// If finch has stopped moving, call function to ask if user would like to retry
				finchMoving = e.isFinchMoving(); 
				if (finchMoving == false) {
					Retry(hexList);
				}
			}
		});
	}

	// This function creates the toolbar and includes the keyboard shortcuts for its items
	private JMenuBar CreateMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File...");
		JMenuItem saveItem = new JMenuItem("Save as...");
		JMenuItem exitItem = new JMenuItem("Exit");
		
		JMenu databaseMenu = new JMenu("Database...");
		JMenuItem importItem = new JMenuItem("Import...");
		JMenuItem exportItem = new JMenuItem("Export...");

		fileMenu.add(saveItem);
		fileMenu.addSeparator();
		fileMenu.add(exitItem);
		
		databaseMenu.add(importItem);
		databaseMenu.add(exportItem);
		
		// The following sets the keyboard shortcuts
		importItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK));
		exportItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
		saveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));		
		exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));

		menuBar.add(fileMenu);
		menuBar.add(databaseMenu);
		
		// This listener calls the Save function to allow user to save hexadecimal values to file
		saveItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Save(hexList);
			}
			
		});
		
		// This listener asks user if they would like to exit the program and if so, terminate the program
		exitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int action = JOptionPane.showConfirmDialog(Controller.this, "Are you sure you want to quit?",
						"Quit", JOptionPane.YES_NO_OPTION);
				if (action == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});
		
		// This listener allows user to import a table from a suitable file into the GUI's table
		importItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fileChooser.setDialogTitle("Select location of file");
				int userSelection = fileChooser.showOpenDialog(Controller.this);
				if (userSelection == JFileChooser.APPROVE_OPTION) {
					try {
						db.LoadDatabase(fileChooser.getSelectedFile());
						tablePanel.Refresh();
					}
					catch (IOException e1) {
						JOptionPane.showMessageDialog(Controller.this, "Unable to load file.", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		
		// This listener allows user to export table in GUI to file and excel format
		exportItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fileChooser.setDialogTitle("Export file");
				int userSelection = fileChooser.showSaveDialog(Controller.this);
				if (userSelection == JFileChooser.APPROVE_OPTION) {
					try {
						db.SaveDatabase(fileChooser.getSelectedFile(), tablePanel.table);
						JOptionPane.showMessageDialog(Controller.this, "File Saved.");
					} catch (IOException e1) {
						System.out.println("catch called");
						JOptionPane.showMessageDialog(Controller.this, "Unable to save file.", "Error", JOptionPane.ERROR_MESSAGE);					}
				}
			}
			
		});
		
		// This function is called when setting up the toolbar/menubar in the constructor, so this returns the layout back to it
		return menuBar;
	}

	// Retry function just prompts the user if they would like to reuse the program
	// If 'Yes' then window closes allowing user to continue using program
	// If 'No' then Save function is called for user to save to file
	private void Retry(ArrayList<String> hexList) {
		int action = JOptionPane.showConfirmDialog(Controller.this, "Would you like to enter another hex number?",
				"Quit", JOptionPane.YES_NO_OPTION);
 
		if (action == JOptionPane.NO_OPTION) {
			Save(hexList);
		}
	}
	
	// Error message for invalid input
	private void Error() {
		JOptionPane.showMessageDialog(this, "Invalid input. \nEnter a 1 or 2 digit hexadecimal number", "Error",
				JOptionPane.ERROR_MESSAGE);
	}

	
	// Save function opens the file directory to allow user to specify name and location for file to save hexadecimal values
	private void Save(ArrayList<String> hexList) {
		fileChooser.setDialogTitle("Specify file name");
		int userSelection = fileChooser.showSaveDialog(Controller.this);
		if (userSelection == JFileChooser.APPROVE_OPTION) {
			File fileToSave = fileChooser.getSelectedFile();

			try {
				if (fileToSave.exists()) {
					JOptionPane.showMessageDialog(Controller.this, "File already exists.", "Error",
							JOptionPane.ERROR_MESSAGE);
					Save(hexList);
					return;
				}

				FileWriter writer = new FileWriter(fileToSave);

				// This for each loop writes every value in hexList into file on a new line
				for (String str : hexList) {
					writer.write(str + System.lineSeparator());
				}
				JOptionPane.showMessageDialog(Controller.this, "File Saved.");
				writer.close();
				System.exit(0); // Program then terminates
			}
			
			// Error handling
			catch (IOException e) {
				JOptionPane.showMessageDialog(Controller.this, "Error. Restart the program", "Error",
						JOptionPane.ERROR_MESSAGE);
				Save(hexList);
				return;
			}
		}
	}
}
