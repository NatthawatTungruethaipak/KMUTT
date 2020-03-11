import java.util.TreeSet;

public class TileCollection
{
	private TreeSet<Tile> tiles = new TreeSet<Tile>();

	private int maxTiles = 0;

	private int minTiles = 0;

	public TileCollection(int minTiles, int maxTiles)
	{
		this.minTiles = minTiles;
		this.maxTiles = maxTiles;
	}

	public void printTiles()
	{

	}

	public int getTileCount()
	{
		return tiles.size();
	}

	public boolean addTile(Tile tile)
	{
		tiles.add(tile);
		return false;
	}

	public boolean removeTile(Tile tile)
	{
		tiles.remove(tile);
		return false;
	}

	public Tile getHighest()
	{
		return tiles.first();
	}

	public Tile getLowest()
	{
		return tiles.last();
	}

	public Tile getRandom()
	{
		return null;
	}
}
