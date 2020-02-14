import java.awt.Point;

/**
 * Simple class representing a circle object. This version tries to use the
 * superclass method for draw
 *
 * Created 26 Aug 2017 for Lecture 4 Modified 9 Sept 2017 to fix draw method
 * (wrong args)
 */
public class CircleV0 extends AbstractShape
{

	/**
	 * keep radius
	 */
	private int radius;

	/**
	 * Constructor creates a new circle by specifying an x,y for the center of the
	 * circle, plus a radius value.
	 * 
	 * @param x      X coord of center point
	 * @param y      Y coord of centerpoint
	 * @param radius Radius length
	 */
	public CircleV0(int x, int y, int radius)
	{
		super();
		anchor = new Point(x, y);
		vertices.add(anchor);
		this.radius = radius;
	}

	/**
	 * Calculate the perimeter of this circle This is 2*PI*radius.
	 * 
	 * @return perimeter value
	 */
	public double calcPerimeter()
	{
		return (double) Math.PI * 2.0 * radius;
	}

	/**
	 * Calculate the area of this circle This is PI times the radius squared
	 * 
	 * @return area value
	 */
	public double calcArea()
	{
		return (double) Math.PI * Math.pow(radius, 2);
	}

}
