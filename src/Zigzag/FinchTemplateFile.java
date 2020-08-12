package Zigzag;
import edu.cmu.ri.createlab.terk.robot.finch.Finch;
import java.util.Scanner;
import java.util.Random;
import java.io.PrintWriter;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

public class FinchTemplateFile
   {
	  static void log(String text) throws IOException {
		  String fileContent = text + "\n";
      	  FileWriter fileWriter = new FileWriter("C:\\ProgramData\\log.txt", true);
      	  fileWriter.write(fileContent);
      	  fileWriter.close();
		  }
	  
	private static int getRandomNumberInRange(int min, int max) {
		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}
		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}
	
   public static void Zigzag() throws IOException
      {
	   
      Finch myFinch = new Finch(); //new finch robot
      Scanner myObj = new Scanner(System.in);	// create a scanner object
      System.out.println("========================");
      System.out.println("===== ZIGZAG FINCH =====");
      System.out.println("========================");
      System.out.println("Enter any key to continue");		//
      String running = myObj.nextLine();
      String errors = "";
      double zigzagNumber = 0;	
      double zigzagLengths = 0;	
      
      while (running != "") {
    	  if (zigzagNumber > 0) {
    	  }else {
    		  System.out.println("========================");
              System.out.println("Enter number of zigzags: ");	
              String stringZigzagNumbers = myObj.nextLine();
          	  int zigzagNumbers = Integer.parseInt(stringZigzagNumbers);	
        	  if (zigzagNumbers > 12 || zigzagNumbers < 1) {
        		  errors = "Invalid input for number of zigzags. Make sure value inputted is between 1 and 12!";
        		  running = "";
        	  }else {
        		  zigzagNumber = zigzagNumbers;
        	  }
    	  }
    	  
    	  if (zigzagLengths > 0) {
    	  }else {
    		  System.out.println("========================");
              System.out.println("Enter length of zigzag: ");	
              String stringLengthZigzag = myObj.nextLine();
          	  int zigzagLength = Integer.parseInt(stringLengthZigzag);	
        	  if (zigzagLength > 85 || zigzagLength < 15) {
        		  if (errors == "") {
            		  errors = "Invalid input for length of zigzags. Make sure value inputted is between 15 and 85!";
        		  }else {
        			  errors = errors + "\n Invalid input for length of zigzags. Make sure value inputted is between 15 and 85!";

        		  }
        		  running = "";
        	  }else {
        		  zigzagLengths = zigzagLength;
        	  }
    	  }
    	  
    	  if (errors == "") {
    		  int speed1 = getRandomNumberInRange(50, 255);
    		  double speed = speed1;
    		  double gradient = 10;
    		  double duration = (zigzagLengths/speed1) * gradient * 1000;
    		  System.out.println("========================");
        	  System.out.println("Zigzagging....");
        	  System.out.println("zigzagLengths is: " + zigzagLengths);
        	  System.out.println("Gradient is: " + gradient);
    		  System.out.println("Speed is: " + speed);
    		  System.out.println("Time is: " + duration);
        	  for (int i = 0; i < zigzagNumber; i++) {
        		  
        		  if (i%2==0) {
        			  myFinch.setLED(0,255,0);
        			  System.out.println("Changed LED colour to green");
        			  if (i!=0) {
        				  myFinch.setWheelVelocities(100,-100,1000);
        			  }
        			  myFinch.setWheelVelocities((int)speed,(int)speed,(int)duration);
        			  System.out.println("Completed zigzag " + (i+1));
        		  }else {
        			  myFinch.setLED(0,0,255);
        			  System.out.println("Changed LED colour to blue");
        			  myFinch.setWheelVelocities(-100,100,1000);
        			  myFinch.setWheelVelocities((int)speed,(int)speed,(int)duration);
        			  System.out.println("Completed zigzag " + (i+1));
        		  }
        		}
        	  
        	  if ((int)zigzagNumber%2==0) {
        		  myFinch.setWheelVelocities(100,-100,1000);
        	  }else {
        		  myFinch.setWheelVelocities(100,-100,1000);
        	  }
        	  System.out.println("========================");
        	  System.out.println("Turning around...");
        	  System.out.println("========================");
        	  for (int i = 0; i < zigzagNumber; i++) {
        		  if (i%2==0) {
        			  myFinch.setLED(0,255,0);
        			  System.out.println("Changed LED colour to green");
        			  myFinch.setWheelVelocities(100,-100,1000);
        			  myFinch.setWheelVelocities((int)speed,(int)speed,(int)duration);
        			  System.out.println("Completed zigzag " + (i+1) + " back");
        		  }else {
        			  myFinch.setLED(0,0,255);
        			  System.out.println("Changed LED colour to blue");
        			  myFinch.setWheelVelocities(-100,100,1000);
        			  myFinch.setWheelVelocities((int)speed,(int)speed,(int)duration);
        			  System.out.println("Completed zigzag " + (i+1) + " back");
        		  }
        		}
        	  myFinch.setWheelVelocities(100,-100,2000);
        	  double distance = Math.sqrt((zigzagLengths*zigzagLengths)*2);
        	  log("User inputs: \n Length of zigzags:" + zigzagLengths + "\n Number of zigzags: " + zigzagNumber + "\nRandomly generated speed: " + speed + "\nLength of traversed path: " + (zigzagLengths * zigzagNumber) +"\nTime taken to complete: "+ (duration*zigzagNumber*2) + "\nDistance travelled: " + distance + "\n");
        	  System.out.println("========================");
        	  myFinch.setLED(0,0,0,1); //comment XDDDDDD
        	  System.out.println("Zigzag complete....");
        	  System.out.println("Log saved at C:/ProgramData/log.txt");
    	  }
    	  while (errors != "") {
        	  System.out.println(errors);
        	  log("\n Error: " + errors + "\n");
        	  errors = "";
        	  running = "";
        	}
    	  running = "";
    	}
      myFinch.quit();
      }


   }