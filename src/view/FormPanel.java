package view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import container.FormEvent;

// This class sets up the GUI for the form (left side of GUI)
// This section of the GUI is responsible for the user input and showing current dance's conversion and required output
public class FormPanel extends JPanel {

	private JLabel hexLabel;
	private JLabel binaryLabel;
	private JLabel decimalLabel;
	private JLabel octalLabel;

	// These labels store the results of the conversions
	private JLabel binaryResLabel;
	private JLabel decimalResLabel;
	private JLabel octalResLabel;
	private JLabel resultsLabel;
	
	private JTextField hexField; // User input field
	public JButton runBtn;

	private FormListener formListener;

	// Constructor sets the initial GUI when program begins
	public FormPanel() {
		Dimension dim = getPreferredSize();
		dim.width = 300;
		setPreferredSize(dim);

		hexLabel = new JLabel("Hex: ");
		hexField = new JTextField(10);
		runBtn = new JButton("Run");

		decimalLabel = new JLabel("Decimal: ");
		binaryLabel = new JLabel("Binary: ");
		octalLabel = new JLabel("Octal: ");

		decimalResLabel = new JLabel();
		binaryResLabel = new JLabel();
		octalResLabel = new JLabel();

		// Sizes of the results label
		decimalResLabel.setPreferredSize(new Dimension(100, 18));
		binaryResLabel.setPreferredSize(new Dimension(100, 18));
		octalResLabel.setPreferredSize(new Dimension(100, 18));

		resultsLabel = new JLabel();
		resultsLabel.setPreferredSize(new Dimension(200, 70));
		
		// This listener creates an FormEvent when user clicks 'Run' button
		// Sends the data stored (Hex) back to the Controller to decide which class needs to use it
		runBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String hex = hexField.getText();
				FormEvent ev = new FormEvent(this, hex);
				
				if (formListener != null) {
					formListener.FormEventOccurred(ev);
				}
			}
		});

		Border innerBorder = BorderFactory.createTitledBorder("Finch Dance");
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.fill = GridBagConstraints.NONE;

		// This sets the layout for the left column of the FormPanel (labels)
		addObject(hexLabel, gbc, 0, 0, 1, 1);
		addObject(decimalLabel, gbc, 0, 2, 1, 1);
		addObject(binaryLabel, gbc, 0, 3, 1, 1);
		addObject(octalLabel, gbc, 0, 4, 1, 1);

		// This sets the layout for the right column of the FormPanel (results, textfield, button)
		addObjectCol2(hexField, gbc, 1, 0, 1, 1);
		addObjectCol2(runBtn, gbc, 1, 1, 1, 1);
		addObjectCol2(decimalResLabel, gbc, 1, 2, 1, 1);
		addObjectCol2(binaryResLabel, gbc, 1, 3, 1, 1);
		addObjectCol2(octalResLabel, gbc, 1, 4, 1, 1);

		ResultsLabel(resultsLabel, gbc, 1, 5, 1, 1);

	}

	public void addObject(Component component, GridBagConstraints gbc, int gridx, int gridy, int gridWidth,
			int gridHeight) {
		gbc.weightx = 0.1;
		gbc.weighty = 0.1;

		gbc.gridx = gridx;
		gbc.gridy = gridy;

		gbc.gridwidth = gridWidth;
		gbc.gridheight = gridHeight;
		gbc.anchor = GridBagConstraints.LINE_END;

		add(component, gbc);

	}

	public void addObjectCol2(Component component, GridBagConstraints gbc, int gridx, int gridy, int gridWidth,
			int gridHeight) {

		gbc.weightx = 0.1;
		gbc.weighty = 0.1;

		gbc.gridx = gridx;
		gbc.gridy = gridy;

		gbc.gridwidth = gridWidth;
		gbc.gridheight = gridHeight;
		gbc.anchor = GridBagConstraints.LINE_START;

		add(component, gbc);
	}

	public void ResultsLabel(Component component, GridBagConstraints gbc, int gridx, int gridy, int gridWidth,
			int gridHeight) {

		gbc.weightx = 0.3;
		gbc.weighty = 0.3;

		gbc.gridx = gridx;
		gbc.gridy = gridy;

		gbc.gridwidth = gridWidth;
		gbc.gridheight = gridHeight;
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;

		add(component, gbc);
	}

	// This function sets the text for the required output displaying all the key data about the dance
	public void ShowResults(String decimal, String binary, String octal, String hex, String speed, String red,
			String green, String blue) {

		binaryResLabel.setText(binary);
		decimalResLabel.setText(decimal);
		octalResLabel.setText(octal);

		String comma = ", ";

		resultsLabel.setText("<html><i>" + hex + comma + octal + comma + binary + comma + decimal + comma + speed
				+ comma + "<br>" + "LED colour (red " + red + comma + "green " + green + comma + "blue " + blue + ")"
				+ "</i><html>");

		super.update(this.getGraphics());
	}


	public void setFormListener(FormListener listener) {
		this.formListener = listener;
	}
}
