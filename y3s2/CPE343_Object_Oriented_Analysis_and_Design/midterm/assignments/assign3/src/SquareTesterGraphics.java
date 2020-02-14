import java.awt.*;
import java.io.*;
import java.util.*;
/**
 *   Test program to create and display squares.
 *
 *   Created by Sally Goldin, 9 August
 *   Modified for Exercise 2. Now each figure can draw itself. 19 Aug 2017
 *   Modified by Natkanok Poksappaiboon (P) 60070503421, 30 Jan 2020
 */
public class SquareTesterGraphics
{
	/**
	 * FigureViewer instance for drawing shapes.
	 */
	private static FigureViewer viewer;
	/**
	 * Show menu and get option from the user.
	 * @return integer option from 1 to 5
	 */
	private static int getMenuOption()
	{
		int option = 0;
		while ((option <= 0) || (option > 5))
		{
			System.out.println("\t1 – Create and draw triangle");
			System.out.println("\t2 – Create and draw square");
			System.out.println("\t3 – Create and draw diamond");
			System.out.println("\t4 – Draw all shapes");
			System.out.println("\t5 – Exit");
			option = IOUtils.getInteger("Enter your choice: ");
		}
		return option;
	}
	/**
	 * Create, draw and possibly move a triangle.
	 * @param graphics    Graphics context for drawing
	 */
	private static void processTriangle(Graphics2D graphics)
	{
		int x1, x2, x3;
		int y1, y2, y3;
		x1 = IOUtils.getInteger("\nEnter x for point 1: ");
		y1 = IOUtils.getInteger("Enter y for point 1: ");
		x2 = IOUtils.getInteger("Enter x for point 2: ");
		y2 = IOUtils.getInteger("Enter y for point 2: ");
		x3 = IOUtils.getInteger("Enter x for point 3: ");
		y3 = IOUtils.getInteger("Enter y for point 3: ");
		Triangle triangle = new Triangle(x1, y1, x2, y2, x3, y3);
		triangle.draw(graphics);
		double perim = triangle.calcPerimeter();
		double area = triangle.calcArea();
		System.out.format("\tPerimeter: %.2f  Area: %.2f\n", perim, area);
		String response = IOUtils.getString("Move it? ");
		if ((response.startsWith("Y")) || (response.startsWith("y")))
		{
			x1 = IOUtils.getInteger("Enter x coordinate of new position: ");
			y1 = IOUtils.getInteger("Enter y coordinate of new position: ");
			triangle.move(new Point(x1, y1));
			viewer.clear();
			try
			{
				Thread.sleep(1000); // Wait for clear to complete
			} catch (InterruptedException ie)
			{

			}
		}
		triangle.draw(graphics);
		}
/**
 * Create, draw and possibly move a square.
 * @param graphics    Graphics context for drawing
 */
private static void processSquare (Graphics2D graphics)
	{
		int x;
		int y;
		int side;
		x = IOUtils.getInteger("\nEnter x for upper left: ");
		y = IOUtils.getInteger("Enter y for upper left: ");
		side = IOUtils.getInteger("Enter length of one side: ");
		Square square = new Square(x, y, side);
		square.draw(graphics);
		double perim = square.calcPerimeter();
		double area = square.calcArea();
		System.out.format("\tPerimeter: %.2f  Area: %.2f\n", perim, area);
		String response = IOUtils.getString("Move it? ");
		if ((response.startsWith("Y")) || (response.startsWith("y")))
				{
					x = IOUtils.getInteger("Enter x coordinate of new position: ");
					y = IOUtils.getInteger("Enter y coordinate of new position: ");
					square.move(new Point(x,y));
					viewer.clear();
					try
					{
						Thread.sleep(1000); // Wait for clear to complete
					} catch (InterruptedException ie)
					{

					}
					square.draw(graphics);
				}
	}
/**
 * Create, draw and possibly move a diamond.
 * @param graphics    Graphics context for drawing
 */
private static void processDiamond (Graphics2D graphics)
	{
		int x;
		int y;
		int vAxis;
		int hAxis;
		x = IOUtils.getInteger("\nEnter x for top point: ");
		y = IOUtils.getInteger("Enter y for top point: ");
		vAxis = IOUtils.getInteger("Enter length of vertical axis: ");
		hAxis = IOUtils.getInteger("Enter length of horizontal axis: ");
		Diamond diamond = new Diamond(x, y, vAxis, hAxis);
		diamond.draw(graphics);
		double perim = diamond.calcPerimeter();
		double area = diamond.calcArea();
		System.out.format("\tPerimeter: %.2f  Area: %.2f\n", perim, area);
		String response = IOUtils.getString("Move it? ");
		if ((response.startsWith("Y")) || (response.startsWith("y"))) {
			x = IOUtils.getInteger("Enter x coordinate of new position: ");
			y = IOUtils.getInteger("Enter y coordinate of new position: ");
			diamond.move(new Point(x, y));
			viewer.clear();
			try
			{
				Thread.sleep(1000); // Wait for clear to complete
			} catch (InterruptedException ie)
			{

			}
			diamond.draw(graphics);
		}
	}

	/**
	 * Main method first creates the viewer. Then it
	 * enters a loop creating shapes, until the user chooses to
	 * exit.
	 */
	public static void main (String arguments[])
	{
		boolean bContinue = true;
		viewer = new FigureViewer();
		int option;
		viewer.pack();
		viewer.setVisible(true);
		Graphics2D graphics = viewer.getViewerGraphics();
		while (bContinue)
		{
			option = getMenuOption();
			switch (option)
			{
				case 1:
					processTriangle(graphics);
					break;
				case 2:
					processSquare(graphics);
					break;
				case 3:
					processDiamond(graphics);
					break;
				case 4:
					AbstractShape.drawAll(graphics);
					break;
				case 5:
					System.out.println("Bye!");
					System.exit(0);
			}
		}
	}
}