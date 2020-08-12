package DrawShape;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JOptionPane;
import edu.cmu.ri.createlab.terk.robot.finch.Finch;
import java.util.Scanner;

public class DrawShape {

	static Scanner UserInput;
	static ArrayList<String> AllShapesInformation = new ArrayList<String>();
	static ArrayList<Integer> SquareArea = new ArrayList<Integer>();
	static ArrayList<Integer> TriangleArea = new ArrayList<Integer>();
	static ArrayList<Integer> HexagonArea = new ArrayList<Integer>();
	static int OverallTime;

	static int SquareCount = 0;
	static int TriangleCount = 0;
	static int HexagonCount = 0;

	public static void drawShape() throws IOException {
		UserInput = new Scanner(System.in); // This Scanner is made to read the user input
		Finch finch = new Finch();

		if (finch.isFinchLevel()) {
			UserDecision();
		} else {
			System.out.println("The finch's beak should be down");
			System.exit(0);
			// This is used to check if the finch is level, if not the program stops.
		}
	}

	public static void UserDecision() throws IOException {

		String Answer = JOptionPane.showInputDialog(null,
				"Please enter S to draw a Square, T to draw a Triangle, H to draw a Hexagon or Q to Quit:"); // This sets the user input as the String Answer for later use.
		Answer = Answer.toUpperCase(); // This is done so both UpperCase and LowerCase letters can be accepted in the user input

		if (Answer.contentEquals("S")) {
			SquareCount = SquareCount + 1; // This counts the number of times a Square is drawn.
			JOptionPane.showMessageDialog(null, "You have chosen to draw a Square");
			DrawSquare(); // If S in input, the method
		} else if (Answer.contentEquals("T")) {
			TriangleCount = TriangleCount + 1; // This counts the number of times a Triangle is drawn.
			JOptionPane.showMessageDialog(null, "You have chosen to draw a Triangle");
			DrawTriangle(); // If T is input, the method DrawTriangle is called.
		} else if (Answer.contentEquals("H")) {
			JOptionPane.showMessageDialog(null, "You have chosen to draw a Hexagon");
			HexagonCount = HexagonCount + 1; // This counts the number of times a Hexagon is drawn.
			DrawHexagon(); // If H is input, the method DrawHexagon is called.
		} else if (Answer.contentEquals("Q")) {
			JOptionPane.showMessageDialog(null, "You have chosen to Output the Text File");
			Quit(); // If Q is input, the method Quit is called.
		} else {
			JOptionPane.showMessageDialog(null, "You haven't entered S,T,C or Q. Please Re-enter");
			UserDecision(); // If S,T,H or Q is not input by the user, the Method UserDecision is called and the process is repeated
		}
	}

	public static void DrawSquare() throws IOException {

		int i = 0;
		int Area = 0;
		int x = 0;
		String y = ""; // This is an empty string where all the squares and their areas are inputted
		Finch finch = new Finch();

		do {

			String Ans = JOptionPane.showInputDialog(null, "input between 15 - 85cm");
			Integer Answer = Integer.parseInt(Ans);

			if (Answer >= 15 && Answer <= 85) {
				int StartTime = (int) System.currentTimeMillis(); // This line starts the timer to count the number of seconds it takes to draw the shape
				x = x + 1;
				for (i = 0; i < 4; i++) { // This for loop is incremented 4 times to draw each side of the square
					finch.setWheelVelocities(150, 150, Answer * 68);
					finch.setWheelVelocities(225, -100, 500);
				}
				int StopTime = (int) System.currentTimeMillis();
				int TimeTaken = StopTime - StartTime; // This line calculates the number of seconds it takes to draw the
				finch.buzz(500, 1000);
				// square
				TimeTaken = (TimeTaken / 1000); // This line converts the time taken from milliseconds to seconds
				JOptionPane.showMessageDialog(null, "Total time to draw this square was: " + TimeTaken + " Seconds");
				OverallTime = (int) (OverallTime + TimeTaken); // This Integer holds the time taken for every shape and adds them together

				Area = Answer * Answer; // This calculated the area of the square
				SquareArea.add(Area);
				JOptionPane.showMessageDialog(null, "The area of the drawn squares are: " + SquareArea);

				y = ("Square: " + Answer);
				AllShapesInformation.add(y);

				drawShape();
			} else if (Answer >= 0) {
				  JOptionPane.showMessageDialog(null , "Length of square sides is not within the boundaries 15-85cm", "Error", JOptionPane.ERROR_MESSAGE);

			} else {
				  JOptionPane.showMessageDialog(null , "Length of each side of the square should be positive and within the boundaries 15-85cm", "Error", JOptionPane.ERROR_MESSAGE);
			}
		} while (x < 1);

		finch.quit();
	}

	public static void DrawTriangle() throws IOException {

		int i = 0;
		int j = 0;
		int k = 0;
		double a = 0;
		double b = 0;
		double c = 0;
		do {
//			String Ans = JOptionPane.showInputDialog(null, "Please input side a of the triangle between 15-85cm" );
//			Integer a = Integer.parseInt(Ans);
			System.out.println("Please input side a of triangle between 15-85cm");
			a = UserInput.nextInt();
			if (a >= 15 && a <= 85) {
				i = i + 1;
			} else if (a >= 0) {
				JOptionPane.showMessageDialog(null , "Length of side a sides is not within the boundaries 15-85cm", "Error", JOptionPane.ERROR_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null , "Length of side a of the triangle should be positive and within the boundaries 15-85cm", "Error", JOptionPane.ERROR_MESSAGE);
			}
		} while (i < 1);
		// This loops takes in the user input for the first side of the triangle, if not
		// within the range, the loop is repeated. The loop only ends when a valid input
		// is entered and i is incremented

		do {
//			String Ans = JOptionPane.showInputDialog(null, "Please input side b of the triangle between 15-85cm" );
//			Integer b = Integer.parseInt(Ans);
			System.out.println("Please input side b of triangle between 15-85cm");
			b = UserInput.nextInt();
			if (b >= 15 && b <= 85) {
				j = j + 1;
			} else if (b >= 0) {
				JOptionPane.showMessageDialog(null , "Length of side b sides is not within the boundaries 15-85cm", "Error", JOptionPane.ERROR_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null , "Length of side b of the triangle should be positive and within the boundaries 15-85cm", "Error", JOptionPane.ERROR_MESSAGE);
			}
		} while (j < 1);

		do {
//			String Ans = JOptionPane.showInputDialog(null, "Please input side b of the triangle between 15-85cm" );
//			Integer c = Integer.parseInt(Ans);
			System.out.println("Please input side c of triangle between 15-85cm");
			c = UserInput.nextInt();
			if (c >= 15 && c <= 85) {
				k = k + 1;
			} else if (c >= 0) {
				JOptionPane.showMessageDialog(null , "Length of side c sides is not within the boundaries 15-85cm", "Error", JOptionPane.ERROR_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null , "Length of side c of the triangle should be positive and within the boundaries 15-85cm", "Error", JOptionPane.ERROR_MESSAGE);
			}
		} while (k < 1);

		CheckTriangle(a, b, c);
	}

	public static void CheckTriangle(double a, double b, double c) throws IOException {

		Finch finch = new Finch();
		double S = (a + b + c) / 2;
		double Area = (Math.sqrt(S * (S - a) * (S - b) * (S - c)));

		if ((a + b > c) && ((a + c > b) && (b + c > a))) {
			JOptionPane.showMessageDialog(null, "Triangle can be formed with the given values");

			double A = a;
			double B = b;
			double C = c;
			double pi = 3.14;
			String y = ""; // This is an empty string where all the triangles and their areas are inputted

			A = ((Math.pow(b, 2) + (Math.pow(c, 2) - Math.pow(a, 2)))) / (2 * b * c); // This gives the cos value of A
			A = Math.acos(A); // This gives the final angle in Radians by doing the inverse of cos A
			A = A * (180 / pi); // Converts the angle from Radians to Decimal

			B = ((Math.pow(a, 2) + (Math.pow(c, 2) - Math.pow(b, 2)))) / (2 * a * c);
			B = Math.acos(B);
			B = B * (180 / pi);

			C = 180 - (B + A);

			DecimalFormat df = new DecimalFormat("0.00"); // This is a variable which will be used later to round the calculated angles to 2dp

			JOptionPane.showMessageDialog(null, "The entered sides are: " + a + " " + b + " " + c);
			JOptionPane.showMessageDialog(null,
					"The Calculated angles are: " + df.format(A) + " " + df.format(B) + " " + df.format(C));

			y = ("Triangle: " + a + ", " + b + ", " + c + " (Angles: " + df.format(A) + ", " + df.format(B) + ", "
					+ df.format(C) + ")"); // This adds all the triangles and angles of triangles into Stirng y which will be output in the log file
			AllShapesInformation.add(y);

			A = 180 - A;
			B = 180 - B;
			C = 180 - C;

			int StartTime = (int) System.currentTimeMillis();

			finch.setWheelVelocities(150, 150, (int) (a * 62));
			finch.setWheelVelocities(150, -150, (int) ((2000 / 360) * A));
			finch.setWheelVelocities(150, 150, (int) (b * 62));
			finch.setWheelVelocities(150, -150, (int) ((2000 / 360) * B));
			finch.setWheelVelocities(150, 150, (int) (c * 62));
			finch.setWheelVelocities(150, -150, (int) ((2000 / 360) * C));

			finch.buzz(500, 1000);

			int StopTime = (int) System.currentTimeMillis();
			int TimeTaken = StopTime - StartTime;
			TimeTaken = (TimeTaken / 1000);
			JOptionPane.showMessageDialog(null, "Total time to draw this Triangle was: " + TimeTaken + " Seconds");
			OverallTime = (int) (OverallTime + TimeTaken);

			TriangleArea.add((int) Area);
			JOptionPane.showMessageDialog(null, "The area of the drawn Triangles are: " + (TriangleArea));

			drawShape();
		} else {
			JOptionPane.showMessageDialog(null , "Triangle cannot be formed with the given values, please re-enter a,b and c", "Error", JOptionPane.ERROR_MESSAGE);
			DrawTriangle();
		}

		finch.quit();

	}

	public static void DrawHexagon() throws IOException {

		int i = 0;

		String y = "";
		Finch finch = new Finch();

		do {

			String Ans = JOptionPane.showInputDialog(null, "Please input side a of Hexagon between 20-60cm");
			Integer Answer = Integer.parseInt(Ans);

			if (Answer >= 20 && Answer <= 60) {

				int StartTime = (int) System.currentTimeMillis();

				for (i = 0; i < 6; i++) {

					finch.setWheelVelocities(150, 150, Answer * 62);
					finch.setWheelVelocities(225, -100, 333);
				}
				// This for loops repeats the same lengths and angles to draw the hexagon 6 times, 1 time for each side and turn.
				finch.buzz(500, 1000);
				finch.buzz(500, 1000);
				finch.setLED(255, 255, 255);
				
				int StopTime = (int) System.currentTimeMillis();
				int TimeTaken = StopTime - StartTime;
				TimeTaken = (TimeTaken / 1000);
				JOptionPane.showMessageDialog(null, "Total time to draw this hexagon is: " + TimeTaken + " Seconds");
				OverallTime = (int) (OverallTime + TimeTaken);

				int Area = (int) (3 * (Math.sqrt(3) / 2) * Math.pow(Answer, 2)); //This is the formula to calculate the area of the hexagon
				HexagonArea.add((int) Area);
				JOptionPane.showMessageDialog(null, "The area of the drawn squares are: " + HexagonArea);

				y = ("Hexagon: " + Answer);
				AllShapesInformation.add(y);
				drawShape();

			} else if (Answer >= 0) {
				JOptionPane.showMessageDialog(null , "Length of the Hexagon is not within the boundaries 20-60cm", "Error", JOptionPane.ERROR_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null , "Length of the Hexagon should be positive and within the boundaries 20-60cm", "Error", JOptionPane.ERROR_MESSAGE);
			}
		} while (i < 6);
		finch.quit();

	}

	public static void Quit() throws IOException {

		String MostFrequentShapeDrawn = null;
		if (SquareCount > TriangleCount && SquareCount > HexagonCount) {
			MostFrequentShapeDrawn = ("The most frequent shape drawn was a Square: " + SquareCount + " Times");
		} else if (TriangleCount > SquareCount && TriangleCount > HexagonCount) {
			MostFrequentShapeDrawn = ("The most frequent shape drawn was a Triangle: " + TriangleCount + " Times");
		} else if (HexagonCount > SquareCount && HexagonCount > TriangleCount) {
			MostFrequentShapeDrawn = ("The most frequent shape drawn was a Hexagon: " + HexagonCount + " Times");
		} else if (SquareCount == TriangleCount) {
			MostFrequentShapeDrawn = ("Square and Triangle were drawn the same number of times: " + SquareCount
					+ " Times");
		} else if (SquareCount == HexagonCount) {
			MostFrequentShapeDrawn = ("Square and Hexagon were drawn the same number of times: " + SquareCount
					+ " Times");
		} else if (TriangleCount == HexagonCount) {
			MostFrequentShapeDrawn = ("Triangle and Hexagon were drawn the same number of times: " + TriangleCount
					+ " Times");
		} else {
			MostFrequentShapeDrawn = ("All shapes were drawn the same amount of times: " + SquareCount + " Times");
		}
		// This takes the number of times the shapes have been drawn and outputs the shape that has been drawn the most

		int OverallCount = SquareCount + TriangleCount + HexagonCount;
		int AverageTime = OverallTime / OverallCount;
		// This calculates the Average time taken to draw the shapes.

		SquareArea.add(0);
		TriangleArea.add(0);
		HexagonArea.add(0);
		// This adds 0 to the arrays of each shape so no arrays are empty, this there is so no errors are given when outputting the largest shape

		String BiggestArea = null;
		int BiggestSquare = Collections.max(SquareArea);
		int BiggestTriangle = Collections.max(TriangleArea);
		int BiggestHexagon = Collections.max(HexagonArea);
		// This searches through the array to get the largest value

		if (BiggestSquare > BiggestTriangle && BiggestSquare > BiggestHexagon) {
			BiggestArea = "The largest shape drawn was the Square: " + BiggestSquare;
		} else if (BiggestTriangle > BiggestSquare && BiggestTriangle > BiggestHexagon) {
			BiggestArea = "The largest shape drawn was the Triangle: " + BiggestTriangle;
		} else {
			BiggestArea = "The largest shape drawn was the Hexagon: " + BiggestHexagon;
		}

		// This if statement calculates the largest area out of all 3 shapes and outputs the largest

		FileWriter MyFile = new FileWriter("All Shapes Information.txt"); // This makes a Text File that will output all the data
		BufferedWriter Mybuffer = new BufferedWriter(MyFile);
		Mybuffer.write("Names of all shapes (and angles of shapes if triangle is drawn): " + AllShapesInformation);
		//This writes all the shapes information (sides, angles of triangles) in the text file																									
		Mybuffer.newLine();
		Mybuffer.write("Average time to draw all the shapes is: " + AverageTime + " Seconds"); // This writes the Average time taken to draw all the shapes in the text file
		Mybuffer.newLine();
		Mybuffer.write(MostFrequentShapeDrawn); // This writes the most frequent shape drawn in the text file
		Mybuffer.newLine();
		Mybuffer.write(BiggestArea); // This writes the largest shape drawn in the text file
		Mybuffer.newLine();
		Mybuffer.close();
	}

}
