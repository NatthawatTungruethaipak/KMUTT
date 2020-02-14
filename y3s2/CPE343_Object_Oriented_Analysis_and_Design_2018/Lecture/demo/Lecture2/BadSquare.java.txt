/**
 * BAD EXAMPLE
 *  Shows how making data public can cause problems
 *
 *   Created by Sally Goldin, 14 August 2017
 *
 */
public class BadSquare
{
   /* A square can be defined by an upper left corner point plus
    * the length of a side. However, for drawing purposes it is 
    * more convenient to have four corner points in order.
    */ 

   /* FOR BAD EXAMPLE, MAKE THESE DATA PUBLIC */ 
   /** X coordinates of four points */
   public int xcoord[] = new int[4];

   /** Y coordinates of four points */
   public int ycoord[] = new int[4];

   /** also keep the length of one side */
   private int oneside = 0;

   /**
    * Constructor creates a new Square by setting the
    * values of the sets of vertex coordinates.
    * @param     x        Upper left corner X
    * @param     y        Upper left corner Y
    * @param     side     Length of one side
    */
    public BadSquare(int x, int y, int side)
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


    /* I HAVE REMOVED THE GETTER AND SETTER FUNCTIONS 
     * WE DON'T "need" THEM IF DATA ARE PUBLIC, RIGHT?
     */

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
