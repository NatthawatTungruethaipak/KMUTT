import java.awt.*;
/** 
 * Class to test reading of shape command files and displaying of information
 * about instances of AbstractShape that are created.
 *
 *   Created by Sally Goldin, 2 September 2017, for Exercise 4 CPE372 
 *   Modified 9 September 2017 for Exercise 5
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
	Graphics2D graphics = viewer.getViewerGraphics();
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
	AbstractShape nextShape = reader.readShape();
	while (nextShape != null)
	{    
	    System.out.println("  readShape returned:\n\t" + nextShape.toString());
	    nextShape.draw(graphics);
	    nextShape = reader.readShape();
	}
	reader.close();
    }

}