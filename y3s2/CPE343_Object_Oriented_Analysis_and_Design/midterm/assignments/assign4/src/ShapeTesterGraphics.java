import java.util.*;
import java.awt.*;
import javax.swing.*;

/**
 *   Test program to create and display various shapes.
 *
 *   Created by Sally Goldin, 24 August 2017 for Exercise 3.
 *   Removed IO routines and put into IOUtils.java
 *   26 Aug 2017 - added code to test overloaded draw() method with fill
 *   1 Feb 2020  - modified to use IOUtils
 */
public class ShapeTesterGraphics
{

    /**
     * Array of colors for testing fill
     */
    private static Color fillcolors[] = {Color.GREEN, Color.BLUE, Color.MAGENTA,
				  Color.YELLOW, Color.ORANGE, Color.RED};

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
	    System.out.println("\t1 – Create and draw triangle (filled)");
	    System.out.println("\t2 – Create and draw square (filled)");
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
	x1 = IOUtils.getInteger("\nTRIANGLE\nEnter x for point 1: ");
	y1 = IOUtils.getInteger("Enter y for point 1: ");
	x2 = IOUtils.getInteger("Enter x for point 2: ");
	y2 = IOUtils.getInteger("Enter y for point 2: ");
	x3 = IOUtils.getInteger("Enter x for point 3: ");
	y3 = IOUtils.getInteger("Enter y for point 3: ");
	Triangle triangle = new Triangle(x1,y1,x2,y2,x3,y3);
	int index = (int) (Math.random() * 100);
	index = index % 6;
	Color color = fillcolors[index];
	triangle.draw(graphics,color);
	double perim = triangle.calcPerimeter();
	double area = triangle.calcArea();
	System.out.format("\tPerimeter: %.2f  Area: %.2f\n", perim, area);
	String response = IOUtils.getString("Move it? ");
	if ((response.startsWith("Y")) || (response.startsWith("y")))
	{
	    x1 = IOUtils.getInteger("Enter x coordinate of new position: ");
	    y1 = IOUtils.getInteger("Enter y coordinate of new position: ");
	    triangle.move(x1,y1);
	    viewer.clear();
	    try 
		{
		    Thread.sleep(1000); // Wait for clear to complete
		}
	    catch (InterruptedException ie)
		{
		}
	    triangle.draw(graphics,color);
	} 
    }

    /**
     * Create, draw and possibly move a square.
     * @param graphics    Graphics context for drawing
     */
    private static void processSquare(Graphics2D graphics)
    {
	int x;
	int y;
	int side;
	x = IOUtils.getInteger("\nSQUARE\nEnter x for upper left: ");
	y = IOUtils.getInteger("Enter y for upper left: ");
	side = IOUtils.getInteger("Enter length of one side: ");
	Square square = new Square(x,y,side);
	int index = (int) (Math.random() * 100);
	index = index % 6;
	Color color = fillcolors[index];
	square.draw(graphics,color);
	//square.draw(graphics);
	double perim = square.calcPerimeter();
	double area = square.calcArea();
	System.out.format("\tPerimeter: %.2f  Area: %.2f\n", perim, area);
	String response = IOUtils.getString("Move it? ");
	if ((response.startsWith("Y")) || (response.startsWith("y")))
	{
	    x = IOUtils.getInteger("Enter x coordinate of new position: ");
	    y = IOUtils.getInteger("Enter y coordinate of new position: ");
	    square.move(x,y);
	    viewer.clear();
	    try 
		{
		    Thread.sleep(1000); // Wait for clear to complete
		}
	    catch (InterruptedException ie)
		{
		}
	    square.draw(graphics,color);
	} 
    }

    /**
     * Create, draw and possibly move a diamond.
     * @param graphics    Graphics context for drawing
     */
    private static void processDiamond(Graphics2D graphics)
    {
	int x;
	int y;
	int vAxis;
	int hAxis;
	x = IOUtils.getInteger("\nDIAMOND\nEnter x for top point: ");
	y = IOUtils.getInteger("Enter y for top point: ");
	vAxis = IOUtils.getInteger("Enter length of vertical axis: ");
	hAxis = IOUtils.getInteger("Enter length of horizontal axis: ");
	Diamond diamond = new Diamond(x,y,vAxis,hAxis);
	diamond.draw(graphics);
	double perim = diamond.calcPerimeter();
	double area = diamond.calcArea();
	System.out.format("\tPerimeter: %.2f  Area: %.2f\n", perim, area);
	String response = IOUtils.getString("Move it? ");
	if ((response.startsWith("Y")) || (response.startsWith("y")))
	{
	    x = IOUtils.getInteger("Enter x coordinate of new position: ");
	    y = IOUtils.getInteger("Enter y coordinate of new position: ");
	    diamond.move(x,y);
	    viewer.clear();
	    try 
		{
		    Thread.sleep(1000); // Wait for clear to complete
		}
	    catch (InterruptedException ie)
		{
		}
	    diamond.draw(graphics);
	} 
    }

    /**
     * Create, draw and possibly move a circle.
     * @param graphics    Graphics context for drawing
     */
    private static void processCircle(Graphics2D graphics)
    {
	int x;
	int y;
	int radius;
	x = IOUtils.getInteger("\nCIRCLE\nEnter x for center point: ");
	y = IOUtils.getInteger("Enter y for center point: ");
	radius = IOUtils.getInteger("Enter radius: ");
	Circle circle = new Circle(x,y,radius);
	circle.draw(graphics);
	double perim = circle.calcPerimeter();
	double area = circle.calcArea();
	System.out.format("\tPerimeter: %.2f  Area: %.2f\n", perim, area);
	String response = IOUtils.getString("Move it? ");
	if ((response.startsWith("Y")) || (response.startsWith("y")))
	{
	    x = IOUtils.getInteger("Enter x coordinate of new position: ");
	    y = IOUtils.getInteger("Enter y coordinate of new position: ");
	    circle.move(x,y);
	    viewer.clear();
	    try 
		{
		    Thread.sleep(1000); // Wait for clear to complete
		}
	    catch (InterruptedException ie)
		{
		}
	    circle.draw(graphics);
	} 
    }


    /** 
     * Main method first creates the viewer. Then it
     * enters a loop creating shapes, until the user chooses to
     * exit.
     */
   public static void main(String arguments[])
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
	      //processCircle(graphics);
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
