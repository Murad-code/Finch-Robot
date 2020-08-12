package DetectObject;
import java.awt.*;
import javax.swing.*;
import edu.cmu.ri.createlab.terk.robot.finch.Finch;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.util.Random;

public class DetectObject {
	public static Finch myFinch = new Finch();
	public static String Mode = "What Mode?";
	public static int Counter = 0;
	public static long Duration = 0;
	public static String TimeInString = "00:00";
	
	public static void DetectObject(String args[]) {
		
		
		JFrame frame = new JFrame("Detect Object");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(450, 300);
		
		JLabel Label = new JLabel("Select a mode:");
		JPanel LabelPanel = new JPanel();
		LabelPanel.add(Label);
		
		
		
		JPanel panel = new JPanel();
		JRadioButton Scaredy = new JRadioButton("Scaredy Finch");
		JRadioButton Curious = new JRadioButton("Curious Finch");
		JRadioButton Any = new JRadioButton("Any");
		
		JPanel ButtonPane = new JPanel();
		JButton Select = new JButton("Select");
		ButtonPane.add(Select);
		
		ButtonGroup G = new ButtonGroup();
		
		G.add(Scaredy);
		G.add(Curious);
		G.add(Any);
		
		panel.add(Scaredy);
		panel.add(Curious);
		panel.add(Any);
		
	
		
		
		
		frame.getContentPane().add(BorderLayout.NORTH,LabelPanel);
		frame.getContentPane().add(BorderLayout.CENTER,panel);
		frame.getContentPane().add(BorderLayout.SOUTH,ButtonPane);
		frame.setVisible(true);
		
		
		Select.addActionListener(new ActionListener() { 
		    public void actionPerformed(ActionEvent e) { 
		        if(Scaredy.isSelected()) {
		        	ScaredyFinch();
		        }else if (Curious.isSelected()){
		        	CuriousFinch();
		        }else if(Any.isSelected()) {
		        	Any();
		        };
		    } 
		});

		
		
		
		
		
	}
	
	private static void ScaredyFinch() {
		Mode = "Scaredy Finch";
		
		long StartingTime = System.currentTimeMillis();
		while (myFinch.isFinchLevel() && myFinch.isBeakUp() == false){
			
			myFinch.setWheelVelocities(80, 80);
			myFinch.setLED(0,255,0);
			while (myFinch.isObstacle()) { 
				Counter++;
				myFinch.setLED(255,0,0);
				myFinch.buzz(262, 1000);//Buzz at the frequency of 262Hz for 1 second
				myFinch.setWheelVelocities(-80, -80, 2000); //Moving backwards for 2 seconds 
				myFinch.setWheelVelocities(80,-80,1000);//Turning direction to opposite side in 1 second
				myFinch.setWheelVelocities(80, 80, 3000); //Moving in the forward direction for 3 seconds
			};
		};
		long EndingTime = System.currentTimeMillis();
		Duration = EndingTime - StartingTime; //Finding the duration by subtracting the starting time from the ending time
		TimeInString = MilliToNormal(Duration); 
		Termination();
	};
	
	private static void CuriousFinch() {
		Mode = "Curious Finch";
		
		long StartingTime = System.currentTimeMillis();
		
		
		while(myFinch.isFinchLevel() && myFinch.isBeakUp() == false) {
			long before = System.currentTimeMillis();
			myFinch.setLED(0,0,255); // turn the color of LED light to Blue
			myFinch.setWheelVelocities(80,80);
			while(myFinch.isObstacle() && System.currentTimeMillis()-before < 5000) {
				Counter = 1;
				myFinch.stopWheels();
				while(myFinch.isObstacleRightSide()==false || myFinch.isObstacleLeftSide() == false) { //if the Finch has encountered an object already
					myFinch.setLED(0, 255, 0); // turn the color of LED light to Green
					while(myFinch.isObstacle() == false) {
						myFinch.setWheelVelocities(40, 40); //if both sensors are false, it means the object went in the straight direction, therefore Finch should also go in the straight direction
					}
					while(myFinch.isObstacleLeftSide() == false || myFinch.isObstacleRightSide() == true) {
						myFinch.setWheelVelocities(40, 20);// if left sensor is not sensing anything but the right sensor is, it means the object went in the right direction, therefore change the direction of the Finch to right
					}
					while(myFinch.isObstacleLeftSide() == true || myFinch.isObstacleRightSide() == false) {
						myFinch.setWheelVelocities(20, 40);// if right sensor is not sensing anything but the left sensor is, it means the object went in the left direction, therefore change the direction of the Finch to left
					}
				}
				myFinch.stopWheels();//Stop the Finch if the object stops moving
				myFinch.setLED(0, 0, 255); //  Change the LED light back to BLUE if the Finch stops moving 
				
			}
			myFinch.setWheelVelocities(0,0,1000);//Finding the duration by subtracting the starting time from the ending time
			
			
		};
		
		long EndingTime = System.currentTimeMillis();
		Duration = EndingTime - StartingTime;
		TimeInString = MilliToNormal(Duration);
		
		Termination();
	}
	
	private static void Any() {
		int rand = 1 + (int) (Math.random()*10); // Taking a random number within range 1 to 10
		System.out.println(rand);
		if(rand>5) {
			CuriousFinch(); //if the random number is greater than 5, call the Curious Finch function
		} else if(rand<=5) {
			ScaredyFinch();//if the random number is lesser than or equal to 5, call the Curious Finch function
		};
	};
	
	private static String MilliToNormal(long Milliseconds) {
		long Minutes = (Milliseconds/1000)/60; // to find the minutes, divide the time in milliseconds by 1000 and divide it by 60. Quotient will be the minutes
		long Seconds = (Milliseconds/1000)%60;// to find the seconds, divide the time in milliseconds by 1000 and divide it by 60. Remainder will be the seconds
		String Time =  String.valueOf(Minutes) +":"+String.valueOf(Seconds); 
		return Time;
	};
	
	private static void Termination() {
		JFrame frame = new JFrame();
		int j = JOptionPane.showConfirmDialog(frame,"Do you want to see the execution logs for the curent session?", "Termination", JOptionPane.YES_NO_OPTION);
		if (j == JOptionPane.NO_OPTION) {
            System.exit(0);
        } else if (j == JOptionPane.YES_OPTION) {
        	ShowLogs();
        };
        
		
		
	}
	
	private static void ShowLogs() {
		JFrame frame = new JFrame();
		JOptionPane.showMessageDialog(frame,"Mode: " + Mode + " | " + "Duration: " + TimeInString + " | Encounters: " + Counter, "Execution Log", JOptionPane.PLAIN_MESSAGE);
	}; // showing the Execution logs in the end of the program
	
	
	
	
}



