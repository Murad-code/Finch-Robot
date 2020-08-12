package model;

import java.util.ArrayList;
import java.util.Collections;

import container.ConversionEvent;
import edu.cmu.ri.createlab.terk.robot.finch.Finch;

// Model class is responsible for processing/converting user input into data and finch's movement
public class Model {

	private ArrayList<String> hexList = new ArrayList<>(); // Stores all user's hexadecimal inputs

	private ModelListener modelListener;
	private ErrorListener errorListener;
	
	int decimal = 0, count, time;
	int octal, binary, speed, red, green, blue;
	Finch finch;
	ConversionEvent event;

	public Model() {
		finch = new Finch(); // Connects finch
	}
	
	// This checks if the user's input is a valid hexadecimal number between 1 or 2 digits 
	public void Validation(String hex) {
		time = 0;
		count = 0;
		hex = hex.toUpperCase();
		for (int i = 0; i < hex.length(); i++) {
			if ((hex.charAt(i) != ' ') && (Character.digit(hex.charAt(i), 16) != -1)) {
				count++;
			}
			else {
				Error();
				return;
			}
		}
		
		// Checking count for number of digits to determine time of finch's movements
		if (count == 1) {
			time = 1000; // 1 second
		} else if (count == 2) {
			time = 500; // 0.5 second
		}

		// Invalid inputs result in Error function to be called to signal Controller to display error message
		else {
			Error();
			return;
		}
		
		// User input added to list and passed into Conversion to be converted into required data for the dance
		hexList.add(hex);
		Conversion(hex);
	}

	
	// This Error function creates an event sent to the Controller which can then make an error message display
	public void Error() {
		if (errorListener != null) {
			errorListener.ErrorEventOccurred();
		}
	}

	// Main function for holding all data after calling other functions to convert hexadecimal into
	// binary, octal, decimal equivalents.
	public void Conversion(String hex) {
		decimal = 0; binary = 0; octal = 0; speed = 0; red = 0; green = 0; blue = 0;
		
		decimal = Decimal(hex, decimal);
		binary = Binary(decimal);
		octal = Octal(decimal);
		red = decimal;
		green = (decimal % 80) * 3;
		blue = (red >= green) ? red : green;
		speed = (octal > 255) ? 255 : octal;
		speed = (octal < 60) ? octal + 50 : octal;
		

		Collections.sort(hexList); // Sorts the list of user inputs in ascending order

		// Creates an event in the ConversionEvent container class so it can be accessed by Controller
		event = new ConversionEvent(hex, decimal, binary, octal, hexList, speed, red, green,
				blue, time, true);
		if (modelListener != null) {
			modelListener.ConversionEventOccurred(event);
		}
	}

	// This function makes the Finch move/dance
	public void FinchController() {
		finch.setLED(red, green, blue);
		
		String binaryString = Integer.toString(binary);
		
		// For loop iterating from right to left checking if character is 1 or 0
		for (int i = binaryString.length() - 1; i > -1; i--) {
			if (binaryString.charAt(i) == '1') {
				System.out.println("If called");
				finch.setWheelVelocities(speed, speed, time);
				finch.setWheelVelocities(0, 0, time);
			} else {
				System.out.println("Else called");
				finch.setWheelVelocities(-speed, -speed, time);
				finch.setWheelVelocities(0, 0, time);

			}
		}
		finch.setLED(0, 0, 0);
		finch.stopWheels();
		event.setFinchMoving(false);
	}

	
	// This function converts the hexadecimal to decimal
	public int Decimal(String hex, int decimal) {
		String hexDigits = "0123456789ABCDEF";
		int base = 1;
		int result = 0;

		// Iterates from right to left
		for (int i = hex.length() - 1; i >= 0; i--) {
			char hexChar = hex.charAt(i);
			int digit = hexDigits.indexOf(hexChar);

			decimal = base * digit + decimal; 
			base = 16 * base; // Incrementing the power of the base 
		}
		result = decimal;
		return result;
	}

	// This function converts the decimal into Octal
	private int Octal(int decimal) {
		int octal = 0, result = decimal, remainder = 0, base = 1;
		while (result != 0) {
			remainder = result % 8;
			result = result / 8;
			octal = octal + remainder * base;
			base = base * 10;
		}
		return octal;
	}

	// This function converts the decimal into Binary
	private int Binary(int decimal) {
		int binary = 0, result = decimal, remainder = 0, base = 1;
		while (result != 0) {
			remainder = result % 2;
			result = result / 2;
			binary = binary + remainder * base;
			base = base * 10;
		}
		return binary;
	}

	public void setModelListener(ModelListener listener) {
		this.modelListener = listener;
	}

	public void setErrorListener(ErrorListener listener) {
		this.errorListener = listener;
	}
}
