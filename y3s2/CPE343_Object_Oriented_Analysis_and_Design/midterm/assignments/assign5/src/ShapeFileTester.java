/**
 * Class to test reading of shape command files and displaying of information
 * about instances of AbstractShape that are created.
 *
 * Created by Sally Goldin, 2 September 2017, for Exercise 4 CPE372
 * 
 * Modified by Natthawat Tungruethaipak 60070503426, 13 February 2020
 */
public class ShapeFileTester
{
	/**
	 * instance of reader that knows how to parse the files
	 */
	private static ShapeReader reader;

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
		reader = new ShapeReader();
		System.out.print("Trying to open'" + args[0] + "' ... ");
		if (!reader.open(args[0]))
		{
			System.out.println("FAILED!\n\n");
			System.exit(1);
		}
		System.out.println("Success!\n");
		FigureViewer viewer = new FigureViewer();
		viewer.pack();
		viewer.setVisible(true);
		AbstractShape nextShape = reader.readShape();
		while (nextShape != null)
		{
			nextShape.draw(viewer.getViewerGraphics());
			System.out.println("  readShape returned an object: " + nextShape.getClass().toString());
			System.out.println("      toString: " + nextShape.toString());
			// System.out.println(" perimeter: " + nextShape.calcPerimeter());
			nextShape = reader.readShape();
		}
		reader.close();
		System.out.println("\nClosing file and exiting...\n\n");
	}

}