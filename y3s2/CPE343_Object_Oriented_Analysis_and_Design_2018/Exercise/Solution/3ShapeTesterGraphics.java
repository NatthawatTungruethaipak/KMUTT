import java.util.*;
import java.awt.*;
import javax.swing.*;

/**
 *   Test program to create and display various shapes.
 *
 *   Created by Sally Goldin, 24 August 2017 for Exercise 3.
 *   Removed IO routines and put into IOFuns.java
 */
public class ShapeTesterGraphics
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
	    option = IOFuns.getOneInteger("Enter your choice: ");
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
	x1 = IOFuns.getOneInteger("\nEnter x for point 1: ");
	y1 = IOFuns.getOneInteger("Enter y for point 1: ");
	x2 = IOFuns.getOneInteger("Enter x for point 2: ");
	y2 = IOFuns.getOneInteger("Enter y for point 2: ");
	x3 = IOFuns.getOneInteger("Enter x for point 3: ");
	y3 = IOFuns.getOneInteger("Enter y for point 3: ");
	Triangle triangle = new Triangle(x1,y1,x2,y2,x3,y3);
	triangle.draw(graphics);
	double perim = triangle.calcPerimeter();
	double area = triangle.calcArea();
	System.out.format("\tPerimeter: %.2f  Area: %.2f\n", perim, area);
	String response = IOFuns.getOneString("Move it? ");
	if ((response.startsWith("Y")) || (response.startsWith("y")))
	{
	    x1 = IOFuns.getOneInteger("Enter x coordinate of new position: ");
	    y1 = IOFuns.getOneInteger("Enter y coordinate of new position: ");
	    triangle.move(x1,y1);
	    viewer.clear();
	    try 
		{
		    Thread.sleep(1000); // Wait for clear to complete
		}
	    catch (InterruptedException ie)
		{
		}
	    triangle.draw(graphics);
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
	x = IOFuns.getOneInteger("\nEnter x for upper left: ");
	y = IOFuns.getOneInteger("Enter y for upper left: ");
	side = IOFuns.getOneInteger("Enter length of one side: ");
	Square square = new Square(x,y,side);
	square.draw(graphics);
	double perim = square.calcPerimeter();
	double area = square.calcArea();
	System.out.format("\tPerimeter: %.2f  Area: %.2f\n", perim, area);
	String response = IOFuns.getOneString("Move it? ");
	if ((response.startsWith("Y")) || (response.startsWith("y")))
	{
	    x = IOFuns.getOneInteger("Enter x coordinate of new position: ");
	    y = IOFuns.getOneInteger("Enter y coordinate of new position: ");
	    square.move(x,y);
	    viewer.clear();
	    try 
		{
		    Thread.sleep(1000); // Wait for clear to complete
		}
	    catch (InterruptedException ie)
		{
		}
	    square.draw(graphics);
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
	x = IOFuns.getOneInteger("\nEnter x for top point: ");
	y = IOFuns.getOneInteger("Enter y for top point: ");
	vAxis = IOFuns.getOneInteger("Enter length of vertical axis: ");
	hAxis = IOFuns.getOneInteger("Enter length of horizontal axis: ");
	Diamond diamond = new Diamond(x,y,vAxis,hAxis);
	diamond.draw(graphics);
	double perim = diamond.calcPerimeter();
	double area = diamond.calcArea();
	System.out.format("\tPerimeter: %.2f  Area: %.2f\n", perim, area);
	String response = IOFuns.getOneString("Move it? ");
	if ((response.startsWith("Y")) || (response.startsWith("y")))
	{
	    x = IOFuns.getOneInteger("Enter x coordinate of new position: ");
	    y = IOFuns.getOneInteger("Enter y coordinate of new position: ");
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
