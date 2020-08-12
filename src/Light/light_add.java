package Light;
import java.awt.Color;


import edu.cmu.ri.createlab.terk.robot.finch.Finch;
public class light_add extends FinchTest{

	//Waiting movement
	public static void lightandmovement() {
		
		
		a.setLED(Color.green);
		a.setWheelVelocities(100, 100, 3000);
		a.setWheelVelocities(-100, -100, 3000);

	}



	//Finch's red LED brightness increase
	public static void redbrightness() {
		
		int bright = 0;
		switch(bright) {
		
		case 1: 
			if((a.getLeftLightSensor()>50 && a.getRightLightSensor()>50)) {
				
				a.setLED(100, 0, 0);
				System.out.println("Red Brightness level: 1");
				
			}
			break;
			
		case 2:
			if((a.getLeftLightSensor()>100 && a.getRightLightSensor()>100)) {
				
				a.setLED(150, 0, 0);
				System.out.println("Red Brightness level: 2");
				
			}
			break;
			
		case 3:
			if((a.getLeftLightSensor()>150 && a.getRightLightSensor()>150)) {
				
				a.setLED(200, 0, 0);
				System.out.println("Red Brightness level: 3");
				
			}
			break;
			
		case 4:
			if((a.getLeftLightSensor()>200 && a.getRightLightSensor()>200)) {
				
				a.setLED(250, 0, 0);
				System.out.println("Red Brightness level: 4");
				
			}
			break;
		
		}
	}
	
	
}
