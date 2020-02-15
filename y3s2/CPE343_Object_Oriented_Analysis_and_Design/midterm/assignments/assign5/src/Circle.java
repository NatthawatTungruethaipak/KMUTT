import java.awt.Graphics2D;
import java.awt.Point;

/**
 * Simple class representing a circle object.
 *
 * Created 26 Aug 2017 for Lecture 4
 *
 */
public class Circle extends AbstractShape
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
	public Circle(int x, int y, int radius)
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

	public void draw(Graphics2D graphics)
	{
		graphics.setPaint(drawColor);
		/* drawOval takes center plus width and height */
		graphics.drawOval(anchor.x, anchor.y, 2 * radius, 2 * radius);
		/* label it near the anchor point */
		int labelx = anchor.x + 5;
		int labely = anchor.y - 5;
		graphics.drawString(new String(" " + shapeId), labelx, labely);
	}

	/**
	 * Override toString to give more informative information
	 */
	public String toString()
	{
		String value = "Circle: center at (" + anchor.x + "," + anchor.y + ") with radius " + radius;
		return value;
	}

	@Override
	public boolean inShape(int x, int y)
	{
		// TODO Auto-generated method stub
		return false;
	}
}
