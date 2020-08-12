package container;

import java.util.EventObject;

// This class is similar to ConversionEvent but is responsible for the initial user input
// When the user clicks the 'Run' button, this event is created to store the hexadecimal value
// and return it to the Controller which decides where it can be utilised
public class FormEvent extends EventObject {

	private String hex;

	public FormEvent(Object source) {
		super(source);
	}

	public FormEvent(Object source, String hex) {
		super(source);

		this.hex = hex;
	}

	public String getHex() {
		return hex;
	}

	public void setHex(String hex) {
		this.hex = hex;
	}
}
