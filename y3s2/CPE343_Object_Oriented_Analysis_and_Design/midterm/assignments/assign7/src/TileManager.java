
public class TileManager
{
	private TileCollection tiles = new TileCollection(0, 100);

	private static TileManager instance;

	private TileManager()
	{

	}

	public static TileManager getInstance()
	{
		if (instance == null)
			instance = new TileManager();
		return instance;
	}

	public Tile selectRandomTile()
	{
		return tiles.getRandom();

	}
}
