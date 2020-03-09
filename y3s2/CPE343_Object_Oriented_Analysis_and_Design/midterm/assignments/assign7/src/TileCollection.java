import java.util.TreeSet;

public class TileCollection
{
	private TreeSet tiles;

	private int maxTiles = 0;

	private int minTiles = 0;

	public TileCollection(int minTiles, int maxTiles)
	{
		this.minTiles = minTiles;
		this.maxTiles = maxTiles;
	}

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub

	}

	public void printTiles()
	{

	}

	public int getTileCount()
	{
		return 0;
	}

	public boolean addTile(Tile tile)
	{
		return false;
	}

	public boolean removeTile(Tile tile)
	{
		return false;
	}

	public Tile getHighest()
	{
		return null;
	}

	public Tile getLowest()
	{
		return null;
	}

	public Tile getRandom()
	{
		return null;
	}
}
