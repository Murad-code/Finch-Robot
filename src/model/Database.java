package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.TableModel;

import container.ConversionEvent;

// This class stores all data regarding the table in the GUI and functions for importing/exporting table to a file
public class Database {
	// Every time finch dances, all data for each turn is stored in this list of type 'ConversionEvent'
	private List<ConversionEvent> totalResults;

	public Database() {
		totalResults = new LinkedList<ConversionEvent>(); // Linked list used because inserting a lot of data
	}

	// Function to add another dance's data into list
	public void AddResult(ConversionEvent result) {
		totalResults.add(result);
	}

	// Function used by Controller to get all the data to pass into tablePanel to set the table
	public List<ConversionEvent> GetResults() {
		return Collections.unmodifiableList(totalResults); // Prevents accidental overwriting of data
	}
	
	// This function is used when user is exporting table to a file
	public void SaveDatabase(File file, JTable table) throws IOException {
		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		
		// Store all the results into an array to write into the file, set size of array to number of elements in list
		ConversionEvent[] totalResultsArray = totalResults.toArray(new ConversionEvent[totalResults.size()]);
		
		oos.writeObject(totalResultsArray);
		oos.close();
		
		// The following try/catch statement creates another file so table can be opened in Excel
		// By storing the data in a TSV format
		try{
	        TableModel model = table.getModel();
	        FileWriter excel = new FileWriter(file + "Excel"); // Excel added to file name to differentiate from other file

	        // Writing the column headers to the file
	        for(int i = 0; i < model.getColumnCount(); i++){
	            excel.write(model.getColumnName(i) + "\t");
	        }

	        excel.write("\n");

	        // Writing the rows of data from the table into the file
	        for(int i=0; i< model.getRowCount(); i++) {
	            for(int j=0; j < model.getColumnCount(); j++) {
	                excel.write(model.getValueAt(i,j).toString()+"\t");
	            }
	            excel.write("\n");
	        }

	        excel.close();

	    }catch(IOException e){ System.out.println(e); } // Display the error in the console if occurs
		
	}
	
	// This function is used when the user is importing a table into the program
	public void LoadDatabase(File file) throws IOException {
		
		FileInputStream fis = new FileInputStream(file);
		ObjectInputStream ois = new ObjectInputStream(fis);
		
		try {
			ConversionEvent[] totalResultsArray = (ConversionEvent[]) ois.readObject();
			
			// totalResults is cleared so new entries don't interfere with imported data
			totalResults.clear(); 
			totalResults.addAll(Arrays.asList(totalResultsArray));
		} catch (ClassNotFoundException e) {
			e.printStackTrace(); // Prints out error, needed in a try/catch statement
		}
		
		ois.close();
	}
}
