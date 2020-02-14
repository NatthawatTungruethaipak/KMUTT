/**
 * Simple class representing a square figure. Designed 
 * to show the idea of visibility, methods, class data, etc.
 *
 *   Created by Sally Goldin, 9 August 2017
 *
 */
public class Square
{
   /* A square can be defined by an upper left corner point plus
    * the length of a side. However, for drawing purposes it is 
    * more convenient to have four corner points in order.
    */ 
   /** X coordinates of four points */
   private int xcoord[] = new int[4];

   /** Y coordinates of four points */
   private int ycoord[] = new int[4];

   /** also keep the length of one side */
   private int oneside = 0;

   /**
    * Constructor creates a new Square by setting the
    * values of the sets of vertex coordinates.
    * @param     x        Upper left corner X
    * @param     y        Upper left corner Y
    * @param     side     Length of one side
    */
    public Square(int x, int y, int side)
    {
       oneside = side;
       xcoord[0] = x;
       ycoord[0] = y;
       xcoord[1] = x + side;  // upper right
       ycoord[1] = y;
       xcoord[2] = x + side;  // lower right
       ycoord[2] = y + side;
       xcoord[3] = x;
       ycoord[3] = y + side;  // lower left
    }

    /** 
     * Return one of the X coordinates - 0 is upper left 
     * @param  which    0,1, 2, or 3 specifying which vertex we want
     * @return vertex X coordinate, or -1 if argument is wrong
     */
    public int getX(int which)
    {
        int coordVal = -1;
	if ((which >= 0) && (which < 4))
            coordVal = xcoord[which];  
        return coordVal;
    }


    /** 
     * Return one of the Y coordinates
     * @param  which    0,1, 2, or 3 specifying which vertex we want
     * @return vertex Y coordinate, or -1 if argument is wrong
     */
    public int getY(int which)
    {
        int coordVal = -1;
	if ((which >= 0) && (which < 4))
            coordVal = ycoord[which];  
        return coordVal;
    }

    /**
     * Set upper left X. This effectively moves the square to the left or right.
     * The setter re-initializes the other coordinates in the array.
     * @param   X    New upper left X coordinate
     */
    public void setX(int upperLeftX)
    {
       xcoord[0] = upperLeftX;
       xcoord[1] = upperLeftX + oneside;  // upper right
       xcoord[2] = upperLeftX + oneside;  // lower right
       xcoord[3] = upperLeftX;
    }


    /**
     * Set upper left Y. This effectively moves the square up or down.
     * The setter re-initializes the other coordinates in the array.
     * @param   Y    New upper left Y coordinate
     */
    public void setY(int upperLeftY)
    {
       ycoord[0] = upperLeftY;
       ycoord[1] = upperLeftY;   // upper right
       ycoord[2] = upperLeftY + oneside;  // lower right
       ycoord[3] = upperLeftY + oneside;  // lower left
    }

    /**
     * calculate the perimeter of this triangle
     * @return perimeter value
     */
    public double calcPerimeter()
    {
        return (double) oneside * 4;
    }


    /**
     * calculate the area of this triangle
     * @return area value
     */
    public double calcArea()
    {
        return (double) oneside * oneside;
    }

}
