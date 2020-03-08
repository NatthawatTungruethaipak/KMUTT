import java.awt.*;
/** 
 * Class to test reading of shape command files and displaying of information
 * about instances of AbstractShape that are created.
 *
 *   Created by Sally Goldin, 2 September 2017, for Exercise 4 CPE372 
 *   Modified 9 September 2017 for Exercise 5
 *   Modified 19 Feb 2020 to add movement demonstration
 */
public class ShapeFileTester
{
    /**
     * instance of reader that knows how to parse the files 
     */
    private static ShapeReader reader;

    /**
     * instance of a FigureViewer for drawing the shapes.
     */
    private static FigureViewer viewer; 

    /**
     * main method which controls the reading and displays the results
     */
    static public void main(String args[])
    {
	if (args.length != 1)
	{
	    System.out.println("Usage:   java ShapeFileTester [filetoread]\n");
	    System.exit(0);
	}
	viewer = new FigureViewer();
	viewer.pack();
	viewer.setVisible(true);
	/* give the viewer time to appear */
	reader = new ShapeReader();
	System.out.print("Trying to open'" + args[0] + "' ... ");
	if (!reader.open(args[0]))
	{
	    System.out.println("FAILED!\n\n");
	    System.exit(1);
	}
	System.out.println("Success!\n");
	try
	{
	    Thread.sleep(1000);
	}
	catch (InterruptedException ie)
	{
	}
	Graphics2D graphics = viewer.getViewerGraphics();
	AbstractShape nextShape = reader.readShape();
	while (nextShape != null)
	{    
	    System.out.println("  readShape returned:\n\t" + nextShape.toString());
	    nextShape.draw(graphics);
	    nextShape = reader.readShape();
	}
	reader.close();
	boolean bContinue = true;
	while (true)
	{
	    String response = IOUtils.getString("Want to move a shape? ");
	    if ((!response.startsWith("Y")) && (!response.startsWith("y")))
	    {
		break;
	    }
	    int figNum = IOUtils.getInteger("Which one? (enter figure number)");
	    AbstractShape shape = AbstractShape.getShape(figNum);
	    if (shape != null)
	    {
		int x1 = IOUtils.getInteger("Enter x coordinate of new position: ");
		int y1 = IOUtils.getInteger("Enter y coordinate of new position: ");
		shape.move(x1,y1);
		viewer.clear();
		try 
		{
		    Thread.sleep(1000); // Wait for clear to complete
		}
		catch (InterruptedException ie)
		{
		}
		shape.draw(graphics);
	    }
	    else 
		System.out.println("Shape does not exist");
	} /* end while loop */
    }     /* end main */ 
}