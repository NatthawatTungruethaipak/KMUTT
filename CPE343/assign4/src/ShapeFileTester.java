
public class ShapeFileTester
{

	public static void main(String[] args)
	{
		if (args.length != 1)
		{
			System.out.println("Please input file name as argument");
			System.exit(0);
		}
		ShapeReader shapeReader = new ShapeReader();
		boolean fileOk = shapeReader.open(args[0]);
		System.out.print("Trying to open file '" + args[0] + "'.... ");

		if (fileOk)
		{
			System.out.println("success!");
		} else
		{
			System.out.println("fail!");
		}

		AbstractShape shape = shapeReader.readShape();
		while (shape != null)
		{
			System.out.println("  readShape returned an object: " + shape.getClass().toString());
			System.out.println("     toString: " + shape.toString());
			System.out.println("     perimeter: " + shape.calcPerimeter());
			shape = shapeReader.readShape();
		}
		shapeReader.close();
		System.out.println("\nClosing file and exiting");
	}

}
