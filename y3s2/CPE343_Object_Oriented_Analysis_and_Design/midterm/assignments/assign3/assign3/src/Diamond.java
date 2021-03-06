import java.awt.Point;

/**
 * Simple class representing a diamond figure. Designed to show the idea of
 * visibility, methods, class data, etc.
 *
 * Created by Natthawat Tungruethaipak, ID 60070503426 on 1 Feb 2020
 */
public class Diamond extends AbstractShape
{
	/*
	 * A square can be defined by an upper left corner point plus the length of a
	 * side. However, for drawing purposes it is more convenient to have four corner
	 * points in order.
	 */

	private int hAxis;

	private int vAxis;

	/** figure number for a particular square */
//	private int figureNumber = -1;

	/** color to use for drawing a particular square */
//	private Color drawColor = null;

	/* static data */
	/** so we can count and label figures */
//	private static int counter = 0;

	/** collection of all squares */
//	private static ArrayList<Diamond> allFigures = new ArrayList<Diamond>();

	/** used to cycle through display colors */
//	private static Color colors[] = { Color.RED, Color.GREEN, Color.BLUE, Color.MAGENTA, Color.ORANGE };

	/**
	 * Constructor creates a new Square by setting the values of the sets of vertex
	 * coordinates. Also increments the counter and sets the figureNumber.
	 * 
	 * @param x    Upper left corner X
	 * @param y    Upper left corner Y
	 * @param side Length of one side
	 */
	public Diamond(int x1, int y1, int vAxis, int hAxis)
	{
		if ((vAxis % 2) > 0)
			vAxis += 1;
		if ((hAxis % 2) > 0)
			hAxis += 1;
		this.hAxis = hAxis;
		this.vAxis = vAxis;
		anchor = new Point(x1, y1); // use with abstract shape to use as reference
		vertices.add(new Point(x1, y1)); // bottom point
		vertices.add(new Point(x1 + hAxis / 2, y1 + vAxis / 2)); // right point
		vertices.add(new Point(x1, y1 + vAxis)); // top point
		vertices.add(new Point(x1 - hAxis / 2, y1 + vAxis / 2)); // left point
//		counter++;
//		figureNumber = counter;
//		allFigures.add(this);
//		drawColor = colors[counter % 5]; // set so will always be same color
	}

	/**
	 * Move the square somewhere else, determined by new upper left x and y. The
	 * function re-initializes the other coordinates in the array to keep the figure
	 * square.
	 * 
	 * @param upperLeftX New upper left X coordinate
	 * @param upperLeftY New upper left Y coordinate
	 */
//	public void move(Point newAnchor)
//	{
//		int diffX = newAnchor.x - anchor.x;
//		int diffY = newAnchor.y - anchor.y;
//		vertices.set(0, new Point(newAnchor.x, newAnchor.y));
//		vertices.set(1, new Point(vertices.get(1).x + diffX, vertices.get(1).y + diffY));
//		vertices.set(2, new Point(vertices.get(2).x + diffX, vertices.get(2).y + diffY));
//		vertices.set(3, new Point(vertices.get(3).x + diffX, vertices.get(3).y + diffY));
//	}

	/**
	 * calculate the perimeter of this triangle
	 * 
	 * @return perimeter value
	 */
	public double calcPerimeter()
	{
		double perimeter = 0;
		for (int i = 1; i < 5; i++)
		{
			perimeter = perimeter + calcLength(i);
		}
		return perimeter;
	}

	/**
	 * calculate the area of this triangle
	 * 
	 * @return area value
	 */
	public double calcArea()
	{
		return (double) (hAxis * vAxis) / 2;
	}

	/**
	 * Calculate the length of one side of the triangle. This is private method used
	 * by calcPerimeter and calcArea.
	 * 
	 * @param which 1,2 or 3, for which side
	 * @return length of side, or -1 if 'which' is out of range.
	 */
	private double calcLength(int which)
	{
		double len = -1;
		int index1 = -1;
		int index2 = -1;
		switch (which)
		{
			case 1:
				index1 = 0;
				index2 = 1;
				break;
			case 2:
				index1 = 1;
				index2 = 2;
				break;
			case 3:
				index1 = 2;
				index2 = 3;
				break;
			case 4:
				index1 = 0;
				index2 = 3;
				break;
			default:
				System.out.println("Invalid argument to calcLength!");
		}
		if (index1 >= 0)
		{
			len = Math.sqrt(Math.pow(vertices.get(index1).x - vertices.get(index2).x, 2)
					+ Math.pow(vertices.get(index1).y - vertices.get(index2).y, 2));
		}
		return len;
	}

	/**
	 * Draw the square. The passed graphics2D contains the information necessary for
	 * this.
	 * 
	 * @param graphics Class with info to do the drawing
	 */
//	public void draw(Graphics2D graphics)
//	{
//		graphics.setPaint(drawColor);
//		int x1, y1, x2, y2;
//		/*
//		 * cycle around the outside of the square starting at the upper left. Get the
//		 * current corner and the next corner, then draw a line between them.
//		 */
//		for (int i = 0; i < 4; i++)
//		{
//			int pt1 = i;
//			int pt2 = ((i + 1) % 4);
//			x1 = vertices.get(pt1).x;
//			y1 = vertices.get(pt1).y;
//			x2 = vertices.get(pt2).x;
//			y2 = vertices.get(pt2).y;
//			x1 *= 10; /* multiply by 10 so we can use small numbers for coords */
//			y1 *= 10;
//			x2 *= 10;
//			y2 *= 10;
//			graphics.drawLine(x1, y1, x2, y2);
//		}
//		/* label in the center */
//		int ulx = vertices.get(0).x * 10;
//		int uly = vertices.get(0).y * 10;
//		graphics.setColor(Color.BLACK);
//		graphics.drawString(new String(" " + figureNumber), (ulx + 10), (uly - 10));
//	}

	/**
	 * static method to draw all the squares that have been created so far.
	 * 
	 * @param graphics Graphics context for drawing.
	 */
//	public static void drawAll(Graphics2D graphics)
//	{
//		for (int i = 0; i < counter; i++)
//		{
//			Diamond diamond = allFigures.get(i);
//			diamond.draw(graphics);
//		}
//	}

}
