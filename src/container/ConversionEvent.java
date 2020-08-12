package container;

import java.util.ArrayList;
import java.io.Serializable;

// ConversionEvent is an event which occurs once the Controller calls the Model to process the user's Hexadecimal values
// The Model is responsible for creating this event and constructs it with all values calculated from the conversions
// This can then be used by the controller to pass the data to whichever class may need it like the Database or FormPanel
public class ConversionEvent implements Serializable{ 
	
	// Serialisation used to allow reading/writing objects to a file 
	// In this case it is objects of ConversionEvent 
	// Otherwise, an exception will be thrown
	private static final long serialVersionUID = -82192133219587432L;
	
	private int id, decimal, binary, octal, speed, red, green, blue, time;
	private ArrayList<String> hexList;
	private String hex;
	private static int count = 0;
	private boolean finchMoving;

	// Constructor so whenever this particular event occurs, all values must be specified
	public ConversionEvent(String hex, int decimal, int binary, int octal, ArrayList<String> hexList,
			int speed, int red, int green, int blue, int time, boolean finchMoving) {

		this.hex = hex;
		this.decimal = decimal;
		this.binary = binary;
		this.octal = octal;
		this.hexList = hexList;
		this.speed = speed;
		this.red = red;
		this.green = green;
		this.blue = blue;
		this.time = time;
		this.finchMoving = finchMoving;

		this.id = count;
		System.out.println("id: " + id);
	}
	
	// Below are the getter and setter methods for the values describing the finch 
	// Ensures data encapsulation and prevents accidental changes to the properties 
	public int getTime() {
		return time;
	}
	
	public boolean isFinchMoving() {
		return finchMoving;
	}

	public void setFinchMoving(boolean finchMoving) {
		this.finchMoving = finchMoving;
	}

	public void addCount() {
		count ++;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getHex() {
		return hex;
	}

	public String getSpeed() {
		return Integer.toString(speed);
	}

	public String getRed() {
		return Integer.toString(red);
	}

	public String getGreen() {
		return Integer.toString(green);
	}

	public String getBlue() {
		return Integer.toString(blue);
	}

	public ArrayList<String> getHexList() {
		return hexList;
	}

	public String getId() {
		return Integer.toString(id);
	}

	public String getDecimal() {
		return Integer.toString(decimal);
	}

	public String getBinary() {
		return Integer.toString(binary);
		
	}

	public String getOctal() {
		return Integer.toString(octal);
	}
}
