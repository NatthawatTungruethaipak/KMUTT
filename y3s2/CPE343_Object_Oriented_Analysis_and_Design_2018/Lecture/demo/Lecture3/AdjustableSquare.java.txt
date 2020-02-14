import java.awt.*;
import java.util.ArrayList;

/**
 * Class representing a square figure that can be resized. 
 * Example to show the concept of inheritance and subclassing
 *
 *   Created by Sally Goldin, 18 August 2017
 *
 */
public class AdjustableSquare extends Square
{
    
    /**
     * Constructor creates a new AdjustableSquare by setting the
     * values of the sets of vertex coordinates.
     * Also increments the counter and sets the figureNumber. 
     * @param     x        Upper left corner X
     * @param     y        Upper left corner Y
     * @param     side     Length of one side
     */
    public AdjustableSquare(int x, int y, int side)
    {
	super(x,y,side); // call the superclass constructor
	System.out.println("Created new AdjustableSquare");
    }


	
}
