import java.util.ArrayList;
import java.awt.Point;
import java.awt.Color;
/**
 *  Shape class. Intended to serve as a superclass (generalization) for
 *  individual shapes like Triangle, Square, etc.
 *
 *  V1 - Created by Sally Goldin, 21 August 2017
 */
public class Shape
{
    /** Anchor point X,Y */
    protected Point anchor;   /* determines the "position" of a shape */
    /* Point is a class in package java.awt that has a public x and y member */

    /** list of points */
    protected ArrayList<Point> vertices = new ArrayList<Point>();

    /** how many points? */
    protected int pointCount; 

    /** color */
    protected Color color;

    /** so we can count and label figures */ 
    protected static int counter = 0;
    
    /** collection of all squares */
    protected static ArrayList<Shape> allFigures = new ArrayList<Shape>();

    /** used to cycle through display colors */    
    protected static Color colors[] = {Color.RED, Color.GREEN, Color.BLUE,
			      Color.MAGENTA, Color.ORANGE};


    /* how do we handle the methods???? */
}