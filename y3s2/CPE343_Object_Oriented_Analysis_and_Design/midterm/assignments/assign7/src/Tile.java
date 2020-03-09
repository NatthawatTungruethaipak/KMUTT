
public class Tile
{

	private String tileLetter = "";

	private int tileValue = 0;

	public Tile(String letter, int value)
	{
		this.tileLetter = letter;
		this.tileValue = value;
	}

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub

	}

	public String getLetter()
	{
		return tileLetter;
	}

	public int getValue()
	{
		return tileValue;
	}
}
