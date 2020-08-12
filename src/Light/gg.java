package Light;


import java.awt.BorderLayout;//*A
import java.awt.GridLayout;//*B
import java.awt.event.ActionEvent;//*C
import java.awt.event.ActionListener;//*D

import javax.swing.*;

public class gg {
	
	//Taking the average value of light sensors
	public static int CalculateAverage() {
		
		int a=0;
		int total=0;
		 
		//Taking array length from "FinchTest" class
		for(int i=0;i<FinchTest.array.length;i++) {
			
			a=a+FinchTest.array[i];
			
		}
		total=a/FinchTest.array.length;
		
		return total;
	}
	
	//Taking the highest value of light sensors
	public static int HighestValue() {
		
		//Taking array values from "FinchTest" class
		int maxValue = FinchTest.array[0]; 
	    for(int h=1;h < FinchTest.array.length;h++){ 
	      if(FinchTest.array[h] > maxValue){ 
	         maxValue = FinchTest.array[h]; 
	      } 
	    } 
	    return maxValue; 
	}
	
	
	//Taking the lowest value of light sensors
	public static int LowestValue() {
	
		//Taking array values from "FinchTest" class
		int minValue = FinchTest.array[0]; 
	    for(int l=1;l<FinchTest.array.length;l++){ 
	      if(FinchTest.array[l] < minValue){ 
	        minValue = FinchTest.array[l]; 
	      } 
	    } 
	    return minValue; 
		
	}
	
	
	//Graphical User Interface
	public static void Start() {
		

	       JFrame frame = new JFrame();
	     
	       
	       JButton button = new JButton("Start");
	       button.setBounds(130, 100, 100, 40);
	       
	       //(*D) Imported "java.awt.event.ActionListener"
	       //(*C) Imported "java.awt.event.ActionEvent"
	       button.addActionListener(new ActionListener(){  
	    	    public void actionPerformed(ActionEvent e){  
	    	            FinchTest.StartingGame();
	    	    }  
	    	    });  
	     
	       
	       JPanel panel = new JPanel();
	       
	       JLabel label1 = new JLabel("Welcome!!");
	       label1.setBounds(190, 80, 20, 20);
	       
	       JLabel label2 = new JLabel("Click 'Start' to continue");
	       label2.setBounds(160, 80, 20, 20);
	      
	       
	       panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
	       panel.setLayout(new GridLayout(0, 1));//(*B) Imported "java.awt.GridLayout" for grid layout
	       panel.add(label1);
	       panel.add(label2);
	       panel.add(button);
	       
	       
	       frame.add(panel, BorderLayout.CENTER);//(*A) Imported "java.awt.BorderLayout" for border layout
	       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	       frame.setTitle("Search for Light");
	       frame.pack();
	       frame.setVisible(true);
		
	}
	

	
	
	//Display Log
	public static void Log() {
		int c =FinchTest.a.getLeftLightSensor();
		int BeginningLeft =FinchTest.a.getLeftLightSensor();
		int BeginningRight =FinchTest.a.getRightLightSensor();
		
		//Getting the beginning value of left light sensor
		if (FinchTest.a.getLeftLightSensor()>BeginningLeft) {
			BeginningLeft = FinchTest.a.getLeftLightSensor();
		}
		
		//Getting the beginning value of left light sensor
		if (FinchTest.a.getLeftLightSensor()>BeginningRight) {
			BeginningRight=FinchTest.a.getLeftLightSensor();
		}
		
		JFrame log = new JFrame();
		JPanel panelLog = new JPanel();
		JLabel label3 = new JLabel("The beginning values of the left sensor was:" + BeginningLeft);//Showing the beginning value of left sensor 
		JLabel label4 = new JLabel("The beginning values of the right sensor was:" + BeginningRight);//Showing the beginning value of right sensor 
		JLabel label5 = new JLabel("Avarage of the lights"+ CalculateAverage());//Showing the average value of both sensors
		JLabel label6 = new JLabel("The highest light sensor value recorded was:" + HighestValue());//Showing the highest value of both sensor
		JLabel label7 = new JLabel("The lowest light sensor value recorded was:" + LowestValue());//Showing the lowest value of both sensor		
		JLabel label8 = new JLabel("The duration of the execution:" + FinchTest.duration + "seconds" );//Showing the duration of the execution in GUI
		JLabel label9 = new JLabel("The number of times the finch detected light:" + FinchTest.i );//Showing number of times the finch detected light
		
	    label3.setBounds(190, 80, 20, 20);
	    label4.setBounds(190, 80, 20, 20);
	    label5.setBounds(190, 80, 20, 20);
	    label6.setBounds(190, 80, 20, 20);
	    label7.setBounds(190, 80, 20, 20);
	    label8.setBounds(190, 80, 20, 20);
	    label9.setBounds(190, 80, 20, 20);
	    
	    
	    panelLog.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
	    panelLog.setLayout(new GridLayout(0, 1));
	    panelLog.add(label3);
	    panelLog.add(label4);
	    panelLog.add(label5);
	    panelLog.add(label6);
	    panelLog.add(label7);
	    panelLog.add(label8);
	    panelLog.add(label9);
	  
	    
	    log.add(panelLog, BorderLayout.CENTER);//(*A) Imported "java.awt.BorderLayout" for border layout
	    log.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    log.setTitle("Display Log");
	    log.pack();
	    log.setVisible(true);
		
	}
	
	public static void main(String[] args) {
		Start();//Calling "Start" method
		  
		    }

	}


