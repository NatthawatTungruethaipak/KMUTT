public class ShapeReader extends TextFileReader
{
	public AbstractShape readShape()
	{
		AbstractShape abstractShape = null;
		String lineRead;
		while ((lineRead = getNextLine()) != null)
		{
			String fields[] = lineRead.split(" ");
			if (fields.length == 4) /* circle and square */
			{
//				System.out.println(lineRead);
//				System.out.println(fields[0]);
				if ((fields[0].equals("SQUARE")) || (fields[0].equals("square")))
				{
					abstractShape = new Square(Integer.parseInt(fields[1]), Integer.parseInt(fields[2]),
							Integer.parseInt(fields[3]));
					return abstractShape;
				} else if ((fields[0].equals("CIRCLE")) || (fields[0].equals("circle")))
				{
					abstractShape = new Circle(Integer.parseInt(fields[1]), Integer.parseInt(fields[2]),
							Integer.parseInt(fields[3]));
					return abstractShape;
				}
			} else if (fields.length == 5) /* diamond */
			{
//				System.out.println(lineRead);
//				System.out.println(fields[0]);
				if ((fields[0].equals("DIAMOND")) || (fields[0].equals("diamond")))
				{
					abstractShape = new Diamond(Integer.parseInt(fields[1]), Integer.parseInt(fields[2]),
							Integer.parseInt(fields[3]), Integer.parseInt(fields[4]));
					return abstractShape;
				}
			} else if (fields.length == 7) /* triangle */
			{
//				System.out.println(lineRead);
//				System.out.println(fields[0]);
				if ((fields[0].equals("TRIANGLE")) || (fields[0].equals("triangle")))
				{
					abstractShape = new Triangle(Integer.parseInt(fields[1]), Integer.parseInt(fields[2]),
							Integer.parseInt(fields[3]), Integer.parseInt(fields[4]), Integer.parseInt(fields[5]),
							Integer.parseInt(fields[6]));
					return abstractShape;
				}
			}
		}
		return abstractShape;
	}
}
