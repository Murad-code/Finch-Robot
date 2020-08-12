package Main;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Main {

	public static void main(String[] args) throws IOException {
		SetUp();
//		SelectMode();
		
	}
	
	public static void SetUp() throws IOException {
		JFrame frame = new JFrame();
		GridLayout gridLayout = new GridLayout();

		frame.setLayout(gridLayout);
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(6, 0));
		JLabel title = new JLabel("Select a mode:", JLabel.CENTER);
        title.setFont(new Font("Serif", Font.PLAIN, 21));

		JButton dance = new JButton("Dance");
		JButton drawShape = new JButton("Draw Shape");
		JButton light = new JButton("Detect Light");
		JButton zigzag = new JButton("Zigzag");
		JButton detectObject = new JButton("Detect Object");
		
		dance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.FinchApp.FinchDance(null);
			}
		});
		
		drawShape.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					DrawShape.DrawShape.drawShape();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		light.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Light.gg.Start();
			}
		});
		
		zigzag.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Zigzag.FinchTemplateFile.Zigzag();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		detectObject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.FinchApp.FinchDance(null);
			}
		});
		
		panel.add(title);
		panel.add(dance);
		panel.add(drawShape);
		panel.add(light);
		panel.add(zigzag);
		panel.add(detectObject);
		
		frame.add(panel);
		
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 400);
		frame.setVisible(true);
	}
	
	public static void SelectMode() throws IOException {
		System.out.println("Select mode: Dance, DrawShape, Light");
		Scanner scanner = new Scanner(System.in);
		String user = scanner.nextLine();
		user = user.toLowerCase();
		switch (user) {
		
		case "dance":
			controller.FinchApp.FinchDance(null);
			break;
			
		case "drawshape":
			DrawShape.DrawShape.drawShape();
			break;
		
		case "light":
			Light.gg.Start();
			break;
			
		case "detectobject":
			DetectObject.DetectObject.DetectObject(null);
			
		default:
			System.out.println("Unknown input. Try again.");
			SelectMode();
		}
	}

}
