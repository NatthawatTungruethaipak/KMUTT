import java.awt.Point;

/**
 * Simple class represent integration of menu with the function that draw shape
 * allow user to use draw, drawall, exit
 * 
 * @author Created Natthawat Tungruethaipak, ID 60070503426 on 1 Feb 2020
 *
 */
public class ShapeTesterGraphics extends AbstractShape
{
	private static int x1, x2, x3, y1, y2, y3, vAxis, hAxis, choice; // variable that use with shape and choice

	public static void main(String arguments[])
	{
		boolean bContinue = true;
		FigureViewer viewer = new FigureViewer();
		viewer.pack();
		viewer.setVisible(true);
		while (bContinue)
		{
			System.out.println("1 - Create and draw triangle");
			System.out.println("2 - Create and draw square");
			System.out.println("3 - Create and draw diamond");
			System.out.println("4 - Draw all shapes");
			System.out.println("5 - Exit");
			int length; /* length of one side */
			double perim;
			double area;
			choice = IOUtils.getInteger("Enter your choice: ");
			if (choice == 1) // triangle
			{
				x1 = IOUtils.getInteger("Enter x for point 1: ");
				y1 = IOUtils.getInteger("Enter y for point 1: ");
				x2 = IOUtils.getInteger("Enter x for point 2: ");
				y2 = IOUtils.getInteger("Enter y for point 2: ");
				x3 = IOUtils.getInteger("Enter x for point 3: ");
				y3 = IOUtils.getInteger("Enter y for point 3: ");
				AbstractShape triangle = new Triangle(x1, y1, x2, y2, x3, y3);
				triangle.draw(viewer.getViewerGraphics());
				perim = triangle.calcPerimeter();
				System.out.print("Perimeter is " + perim + "\t");
				area = triangle.calcArea();
				System.out.println("Area is " + area);
				String move = IOUtils.getString("Move it?");
				if ((move.startsWith("Y")) || (move.startsWith("y")))
				{
					x1 = IOUtils.getInteger("New X: ");
					y1 = IOUtils.getInteger("New Y: ");
					Point anchor = new Point(x1, y1);
					triangle.move(anchor);
					viewer.clear();
					try
					{
						Thread.sleep(1000); // Wait for clear to complete
					} catch (InterruptedException ie)
					{
					}
					triangle.draw(viewer.getViewerGraphics());
				}
				else
				{
					continue;
				}
			}
			else if (choice == 2) // square
			{
				x1 = IOUtils.getInteger("Enter x for upper left point: ");
				y1 = IOUtils.getInteger("Enter y for upper left point: ");
				length = IOUtils.getInteger("Length of each side of square: ");
				AbstractShape latestSquare = new Square(x1, y1, length);
				latestSquare.draw(viewer.getViewerGraphics());
				perim = latestSquare.calcPerimeter();
				System.out.print("Perimeter is " + perim + "\t");
				area = latestSquare.calcArea();
				System.out.println("Area is " + area);
				String move = IOUtils.getString("Move it?");
				if ((move.startsWith("Y")) || (move.startsWith("y")))
				{
					x1 = IOUtils.getInteger("New X: ");
					y1 = IOUtils.getInteger("New Y: ");
					Point anchor = new Point(x1, y1);
					latestSquare.move(anchor);
					viewer.clear();
					try
					{
						Thread.sleep(1000); // Wait for clear to complete
					} catch (InterruptedException ie)
					{
					}
					latestSquare.draw(viewer.getViewerGraphics());
				}
				else
				{
					continue;
				}
			}
			else if (choice == 3) // diamond
			{
				x1 = IOUtils.getInteger("Enter x for anchor point: ");
				y1 = IOUtils.getInteger("Enter y for anchor point: ");
				vAxis = IOUtils.getInteger("Enter vertical axis: ");
				hAxis = IOUtils.getInteger("Enter horizontal axis: ");
				AbstractShape diamond = new Diamond(x1, y1, vAxis, hAxis);
				diamond.draw(viewer.getViewerGraphics());
				perim = diamond.calcPerimeter();
				System.out.print("Perimeter is " + perim + "\t");
				area = diamond.calcArea();
				System.out.println("Area is " + area);
				String move = IOUtils.getString("Move it?");
				if ((move.startsWith("Y")) || (move.startsWith("y")))
				{
					x1 = IOUtils.getInteger("New X: ");
					y1 = IOUtils.getInteger("New Y: ");
					Point anchor = new Point(x1, y1);
					diamond.move(anchor);
					viewer.clear();
					try
					{
						Thread.sleep(1000); // Wait for clear to complete
					} catch (InterruptedException ie)
					{
					}
					diamond.draw(viewer.getViewerGraphics());
				}
			}
			else if (choice == 4) // draw all
			{
				AbstractShape.drawAll(viewer.getViewerGraphics());
			}
			else if (choice == 5) // exit
			{
				bContinue = false;
			}
			else // error input
			{
				System.out.println("Please select 1-5");
				continue;
			}
		}
	}

	/**
	 * calculate the perimeter
	 * 
	 * @return area value
	 */
	public double calcPerimeter()
	{
		return 0;
	}

	/**
	 * calculate the area9j
	 * 
	 * @return area value
	 */
	public double calcArea()
	{
		return 0;
	}

}
