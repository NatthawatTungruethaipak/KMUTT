import java.io.*;
import java.util.*;
/**
 *   Test program to create and display AdjustableSquares.
 *
 *   Created by Sally Goldin, 19 August 2017
 */
public class AdjustableSquareTesterGraphics
{
    /** Keep hold of the last square we created
     */
    protected static AdjustableSquare latestSquare = null; 


   /* Main method first creates the viewer. Then it
    * asks for coordinates, creates new triangles, and displays them. 
    * Then prints the perimetr and area as well.
    */
   public static void main(String arguments[])
      {
      boolean bContinue = true;
      FigureViewer viewer = new FigureViewer();
      viewer.pack();
      viewer.setVisible(true);
 
      while (bContinue)
         {
	 int x,y;    /* coordinates of upper left point of square */
	 int length; /* length of one side */
	 x = IOUtils.getInteger("Enter x for upper left point (negative to exit): ");
	 if (x < 0)
            {
	    bContinue = false;
	    }
	 else
            {
	    y = IOUtils.getInteger("Enter y for upper left point: ");
	    length = IOUtils.getInteger("Length of each side of square: ");
	    latestSquare = new AdjustableSquare(x,y,length);
	    latestSquare.draw(viewer.getViewerGraphics());
	    double perim = latestSquare.calcPerimeter();
	    System.out.println("Perimeter is " + perim);
	    double area = latestSquare.calcArea();
	    System.out.println("Area is " + area + "\n\n");
	    System.out.println("-----------------------------");
	    String move = IOUtils.getString("Want to move the square (Y/N)?");
	    if ((move.startsWith("Y")) || (move.startsWith("y")))
		{
		x = IOUtils.getInteger("New X: ");    
		y = IOUtils.getInteger("New Y: ");
		latestSquare.move(x,y);
		viewer.clear();
		try 
		{
		    Thread.sleep(1000); // Wait for clear to complete
		}
		catch (InterruptedException ie)
		{
		}
		latestSquare.draw(viewer.getViewerGraphics());
		} 
	    System.out.println("-----------------------------\n");
	    }
	 }   
      System.out.println("About to draw all square");
      AdjustableSquare.drawAll(viewer.getViewerGraphics());
      String dummy = IOUtils.getString("Press return to exit.");
      System.exit(0);
      }

}
