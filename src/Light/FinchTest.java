package Light;
import java.awt.Color;//*E
import java.util.Random;//*F
import java.util.concurrent.TimeUnit;//*G

import edu.cmu.ri.createlab.terk.robot.finch.Finch;//*H
public class FinchTest {
	
	public static Finch a =new Finch();//(*H) Imported "edu.cmu.ri.createlab.terk.robot.finch.Finch" for finch
	public static int []array = null;//Taking variable for array
	public static long startTime;//Taking variable to store starting time
	public static long endTime;//Taking variable to store ending time
	public static long duration;//Taking a variable to store the duration of execution 
	public static int i;//Taking a variable to store the number of times finch detected the light
	
	public static void main (String args []) {
		

}
	
	public static void StartingGame() {
		
		//(*F) Imported "java.util.concurrent.TimeUnit" for time
		long timerstart = System.currentTimeMillis();//Taking the starting time
	
		
		 i=0;
		System.out.println("Game Start");
		
		//Checking if finch's beak is down to start the program 
		while (a.isBeakUp() == false) {
			array=a.getLightSensors();//Storing all light sensors values in an array
			
			a.setLED(Color.yellow);//(*E) Imported "java.awt.Color" for LED colour 
			a.setWheelVelocities(60,60,4000);
	
			if( a.getLeftLightSensor() >10 && a.getRightLightSensor() >10) {
				i++;//Counting numbers of time the finch detected lights  	
				
				int leftLight=a.getLeftLightSensor();
				int rightLight=a.getRightLightSensor();
				
				System.out.println("Left light sensor: " + a.getLeftLightSensor());
				System.out.println("Right light sensor: " + a.getRightLightSensor());
				
				while((leftLight==a.getLeftLightSensor())&&(rightLight==a.getRightLightSensor())) {
					
					//Additional Specification: "Waiting movement"
					System.out.println("The light is not moving");
					a.buzz(262, 3000);
					light_add.lightandmovement();//Calling "lightandmovement" method from "light_add" class
					
				}
				
					System.out.println("The light is moving");
					light_add.redbrightness();//Calling "redbrightness" method from "light_add" class
					
					
					
				
				
		}
			//Finch is following the light to left
			else if (( a.getLeftLightSensor() > a.getRightLightSensor() )) {
				a.setLED(Color.red);
				System.out.println("Finch is going left");
				a.setWheelVelocities(50, 100,5000);
				a.setWheelVelocities(90, 90);
				
				
			}
			
			//Finch is following the light to right
			 
			else if ((a.getRightLightSensor() > a.getLeftLightSensor())) {
				a.setLED(Color.red);
				System.out.println("Finch is going right");
				a.setWheelVelocities(100,50,5000);
				a.setWheelVelocities(90, 90);
				
			
				
			}
			
			//Finch could not find the light
			else if(( a.getLeftLightSensor()==0) && (a.getRightLightSensor()==0)) {
				
				int min = 1;
			    int max = 2;
			    	
			      //(*F)Imported "java.util.Random"
			      int random_int = (int)(Math.random() * (max - min + 1) + min);//Generates a random number from 1 to 2
			      
			      if (random_int==1) {
			    	  
			    	  System.out.println("Cannot find light source. " + "Finch is going right.");
			    	  	a.setWheelVelocities(100,50,5000);
						a.setWheelVelocities(90, 90);
			    	  
			      }
			      
			      else if (random_int==2){
			    	  
			    	  System.out.println("Cannot find light source. " + "Finch is going left");
						a.setWheelVelocities(50, 100,5000);
						a.setWheelVelocities(90, 90);
			      }
			      
				
			}
			
			
		
		
	}
		
		long fulltime = System.currentTimeMillis() - timerstart;
		duration = fulltime/1000; //Converts into seconds;
		System.out.println(duration + "seconds");//Printing the duration of execution in terminal 
		
		
		
		gg.Log();//Calling "Log" method from "gg" class
		a.quit();
		System.out.println("Game Stops");
		
		
		
		
		
		
	}
	
	
	
}