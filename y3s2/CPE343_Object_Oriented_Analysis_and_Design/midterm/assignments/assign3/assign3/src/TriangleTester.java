import java.io.*;

/**
 * Simple class to exercise our Triangle class.
 *
 *   Created by Sally Goldin, 9 December 2011
 *   25 Jan 2020 - changed to use IOUtils methods
 *
 */
public class TriangleTester
{

   /** Main method asks for coordinates and then
    * creates new triangles. Then it calls the 
    * perimeter and area methods for the newly created
    * triangle.
    */
   public static void main(String arguments[])
      {
      boolean bContinue = true;
      while (bContinue)
         {
	 int x1, x2, x3;
	 int y1, y2, y3;
	 x1 = IOUtils.getInteger("Enter x for point 1 (negative to exit): ");
	 if (x1 < 0)
            {
	    bContinue = false;
	    }
	 else
            {
	    y1 = IOUtils.getInteger("Enter y for point 1: ");
	    x2 = IOUtils.getInteger("Enter x for point 2: ");
	    y2 = IOUtils.getInteger("Enter y for point 2: ");
	    x3 = IOUtils.getInteger("Enter x for point 3: ");
	    y3 = IOUtils.getInteger("Enter y for point 3: ");
	    Triangle triangle = new Triangle(x1,y1,x2,y2,x3,y3);
	    double perim = triangle.calcPerimeter();
	    System.out.println("Perimeter is " + perim);
	    double area = triangle.calcArea();
	    System.out.println("Area is " + area + "\n\n");
	    }
	 }   
      }

}
