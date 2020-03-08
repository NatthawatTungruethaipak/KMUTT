import java.awt.*;
import java.util.ArrayList;

/**
 * Simple class representing a circle object. 
 *
 *   Created 26 Aug 2017 for Lecture 4
 *   19 Feb 2020 - separate calculateBoundingBox so we can update on move
 *
 */
public class Circle extends AbstractShape
{
    
    /**
     * keep radius
     */
    private int radius;


    /**
     * Constructor creates a new circle by specifying an x,y 
     * for the center of the circle, plus a radius value.
     * @param     x        X coord of center point
     * @param     y        Y coord of centerpoint
     * @param     radius   Radius length   
     */
    public Circle(int x, int y, int radius)
    {
       super();
       anchor = new Point(x,y);
       vertices.add(anchor);
       this.radius = radius;
       calculateBoundingBox();
    }

    /**
     * Calculate the perimeter of this circle
     * This is 2*PI*radius.
     * @return perimeter value
     */
    public double calcPerimeter()
    {
	return (double) Math.PI * 2.0 * radius;
    }

    /**
     * Calculate the area of this circle
     * This is PI times the radius squared
     * @return area value
     */
    public double calcArea()
    {
        return (double) Math.PI * Math.pow(radius,2);
    }

    @Override
    /**
     * Draw the circle
     * @param  graphics    Graphics context for drawing
     */
    public void draw(Graphics2D graphics)
    {	
       graphics.setPaint(drawColor);
       /* drawOval takes upper left plus width and height */
       graphics.drawOval(anchor.x-radius,anchor.y-radius,2*radius,2*radius);
       /* label it near the anchor point */
       int labelx = anchor.x + 5;
       int labely = anchor.y - 5;
       graphics.drawString(new String(" " + shapeId),labelx,labely);
    }


    @Override
    /**
     * Draw and fill the circle.
     * @param  graphics    Graphics context for drawing
     * @param  fillColor   Color to use for filling.
     */
    public void draw(Graphics2D graphics,Color fillColor)
    {	
       draw(graphics);
       graphics.setPaint(fillColor);
       /* fillOval takes upper left plus width and height */
       graphics.fillOval(anchor.x-radius,anchor.y-radius,2*radius,2*radius);
    }

    /**
     * Override toString to give more informative information
     */
    public String toString()
    {
	String value = "Circle " + shapeId+ ": center at (" + anchor.x+","+anchor.y+") with radius " + radius;
	return value;
    }

    @Override
    /**
     * Calculate the bounding box. 
     */
    protected void calculateBoundingBox()
    {
       minX = anchor.x - radius;
       minY = anchor.y - radius;
       maxX = anchor.x + radius;
       maxY = anchor.y + radius;
    }

}
