import java.util.ArrayList;
import java.util.Iterator;
import java.awt.*;
/**
S *  AbstractShape class. Intended to serve as a superclass (generalization) for
 *  individual shapes like Triangle, Square, etc.
 *
 *  Created by Sally Goldin, 21 August 2017
 *  Modified 15 Feb 2020 to add method for finding all selected shapes.
 *  Modified 19 Feb 2020 - separate calculateBoundingBox so we can update on move
 */
public abstract class AbstractShape
{
    /** Anchor point X,Y 
     * determines the "position" of a shape 
     */
    protected Point anchor;   
    /* Point is a class in package java.awt that has a public x and y member */

    /** list of points */
    protected ArrayList<Point> vertices = new ArrayList<Point>();

    /** id for this shape */
    protected int shapeId;

    /** color */
    protected Color drawColor;

    /** 
     *  Bounding box coordinates for a shape: 
     *  Minimum X value
     */
    protected int minX;

    /** 
     *  Bounding box coordinates for a shape: 
     *  Minimum Y value
     */
    protected int minY;

    /** 
     *  Bounding box coordinates for a shape: 
     *  Maximum X value
     */
    protected int maxX;

    /** 
     *  Bounding box coordinates for a shape: 
     *  Maximum Y value
     */
    protected int maxY;
   
    
    /** so we can count and label figures */ 
    private static int counter = 0;
    
    /** collection of all shapes */
    private static ArrayList<AbstractShape> allFigures = new ArrayList<AbstractShape>();

    /** used to cycle through display colors */    
    private static Color colors[] = {Color.RED, Color.GREEN, Color.BLUE,
			      Color.MAGENTA, Color.ORANGE};

    /**
     * Constructor increments counter, sets shapeId, puts shape
     * into the allFigures list, and sets the color.
     * These operations will occur regardless of the type of shape.
     */
    public AbstractShape()
    {
	counter++;
	shapeId = counter;
	drawColor = colors[counter % 5];
	allFigures.add(this);
    }

    /**
     * Move the shape to a new location, specified by
     * the passed x and y coordinates. We can do this in a general manner by
     * calculating the difference between the old and new locations
     * in X and Y directions, then adding this difference to each
     * point. We also need to change the anchor point data item.
     * @param  x    Y coordinates of new reference/anchor point
     * @param  y    Y coordinates of new reference/anchor point
     */
    public void move(int x, int y)
    {
	Point newAnchor = new Point(x,y);
	int deltaX = newAnchor.x - anchor.x; /* difference between old & new posn */
	int deltaY = newAnchor.y - anchor.y;
        anchor = newAnchor;
	int points = vertices.size();
	for (int i = 0; i < points; i++)
	{
	    Point p = vertices.get(i);
	    p.x += deltaX;
	    p.y += deltaY;
	    /* we don't need to put it back in the arraylist */
	    /* since it is still there. */
	}
	calculateBoundingBox();  /* bb will change when a figure moves */
    } 

    /**
     * Draw the shape.
     * This will only work correctly for closed shapes with a finite
     * number of vertices (like triangles, squares and diamonds)
     * @param  graphics    Graphics context for drawing
     */
    public void draw(Graphics2D graphics)
    {
	graphics.setPaint(drawColor);
	int points = vertices.size();
	for (int i = 0; i < points; i++)
	{
	    Point p1 = vertices.get(i);
	    Point p2 = vertices.get((i+1)%points);
	    graphics.drawLine(p1.x,p1.y,p2.x,p2.y);
	}
	/* label it near the anchor point */
	int labelx = anchor.x + 5;
	int labely = anchor.y - 5;
	graphics.drawString(new String(" " + shapeId),labelx,labely);
    }

    /**
     * Draw and fill the shape.
     * This will only work correctly for closed shapes with a finite
     * number of vertices (like triangles, squares and diamonds)
     * @param  graphics    Graphics context for drawing
     * @param  fillColor   Color to use for filling.
     */
    public void draw(Graphics2D graphics,Color fillColor)
    {
	draw(graphics);  /* draw the outline */
	int size = vertices.size();
	int x[] = new int[size];
	int y[] = new int[size];
	for (int i = 0; i < size; i++)
	{
	    Point p = vertices.get(i);
	    x[i] = p.x;
	    y[i] = p.y;
	}
	graphics.setPaint(fillColor);
	graphics.fillPolygon(x,y,size);
    }

    /**
     * Determine if passed X,Y point is inside
     * the bounding box for a shape.
     * @param  X   X coordinate of point
     * @param  Y   Y coordinate of point
     * @return true if inside the BB, else false 
     */
    public boolean inShape(int X, int Y)
    {
	if ((X >= minX) && (X <= maxX) && 
	    (Y >= minY) && (Y <= maxY))
	    return true;
	else
	    return false;
    }

    /**
     * Calculate and return the perimeter.
     * @return  Length of shape boundary
     */
    public abstract double calcPerimeter();

    /**
     * Calculate and return the area of the shape.
     * @return  area
     */
    public abstract double calcArea();

    /**
     * Calculate the bounding box. This will work for
     * all the polygonal shapes but must be overridden for Circle.
     * Sets minX, minY, maxX, maxY
     */
    protected void calculateBoundingBox()
    {
	minX = 10000;
	minY = 10000;
        maxX = 0;
	maxY = 0;
	for (int i = 0; i < vertices.size(); i++)
	{
	    Point v = vertices.get(i);
	    if (v.x < minX)
		minX = v.x;
	    if (v.y < minY)
		minY = v.y;
	    if (v.x > maxX)
		maxX = v.x;
	    if (v.y > maxY)
		maxY = v.y;
	}
    }

    /** 
     * Print the bounding box coordinates so we
     * can examine them 
     */
    public void printBoundingBox()
    {
	System.out.println("BB from (" + minX + "," + minY + ") to (" +
			   maxX + "," + maxY +  ")");
    }
    /** 
     * static method to draw all the shapes 
     * that have been created so far.
     * @param  graphics   Graphics context for drawing.
     */
    public static void drawAll(Graphics2D graphics)
    {
	for (int i=0; i < allFigures.size(); i++)
	{
	    AbstractShape shape = allFigures.get(i);
	    shape.draw(graphics);
	}
    }

    /** 
     * static method to find and return the first shape 
     * for which the passed point is inside the bounding box.
     * @param  X    X coordinate of selected point
     * @param  Y    Y coordinate of selected point
     * @return first shape found, or null if point is not in any shape.
     */
    public static AbstractShape findSelectedShape(int X, int Y)
    {
	AbstractShape found = null;
	Iterator<AbstractShape> it = allFigures.iterator();
	while (it.hasNext() && (found == null))
	{
	    AbstractShape shape = it.next();
	    //shape.printBoundingBox();
	    if (shape.inShape(X,Y))
		found = shape;
	}
	return found;
    }

    /** 
     * static method to find and return all the shapes
     * for which the passed point is inside the bounding box.
     * @param  X    X coordinate of selected point
     * @param  Y    Y coordinate of selected point
     * @return array list of all selected shapes. This will
     *         have size of 0 if no shapes selected.
     */
    public static ArrayList<AbstractShape> findAllSelectedShapes(int X, int Y)
    {
	ArrayList<AbstractShape> shapelist = new ArrayList<AbstractShape>();
	Iterator<AbstractShape> it = allFigures.iterator();
	while (it.hasNext())
	{
	    AbstractShape shape = it.next();
	    //shape.printBoundingBox();
	    if (shape.inShape(X,Y))
		shapelist.add(shape);
	}
	return shapelist;
    }

    /**
     * Look through the shape collection to find the shape
     * whose shapeId matches the passed figureNum
     * @param figureNum   Shape ID of the shape we want
     * @return shape we want, or null if it does not exist
     */
    public static AbstractShape getShape(int figureNum)
    {
	AbstractShape foundShape = null;
	Iterator<AbstractShape> it = allFigures.iterator();
	while ((it.hasNext()) && (foundShape == null))
	{
	    AbstractShape shape = it.next();
	    if (shape.shapeId == figureNum)
		foundShape = shape;
	}
	return foundShape;
    }
    /** 
     * Return this shape's drawing color
     * @return drawing color
     */
    public Color getDrawColor()
    {
	return drawColor;
    }
}
