import java.util.ArrayList;
import java.util.Arrays;

/**
 * TileManager.java
 *
 * This class represent Scramble game.
 *
 * Try to be singleton class just create one time.
 *
 * Created by Natthawat Tungruethaipak, 11 March 2020
 */
public class TileManager
{
	private static TileCollection tiles = new TileCollection(0, 100);

	private static TileManager instance;

	/**
	 * To make this class as singleton let create class privately
	 */
	private TileManager()
	{

	}

	/**
	 * This method use for initial the TileManager and add tile into tile collection
	 */
	public void initTile()
	{
		ArrayList<String> allTiles = new ArrayList<String>(Arrays.asList("- 0 2", "A 1 9", "B 3 2", "C 3 2", "D 2 4",
				"E 1 12", "F 4 2", "G 2 3", "H 4 2", "I 1 9", "J 8 1", "K 5 1", "L 1 4", "M 3 2", "N 1 6", "O 1 8",
				"P 3 2", "Q 10 1", "R 1 6", "S 1 4", "T 1 6", "U 1 4", "V 4 2", "W 4 2", "X 8 1", "Y 4 2", "Z 10 1"));
		String[] fields = new String[3]; /** string array after separate letter, value, and quantity */
		int quantity = 0; /** Quantity of each letter */
		int value = 0; /** Value of each letter */
		Tile tile; /* Tile to be add in tiles collection */

		/*
		 * Get each data with split between letter, quantity and value using " ". And
		 * create new tile with letter, quantity and value and add to tile collection.
		 */
		for (int i = 0; i < allTiles.size(); i++)
		{
			fields = allTiles.get(i).split(" ");
			try
			{
				value = Integer.parseInt(fields[1]);
				quantity = Integer.parseInt(fields[2]);
//				System.out.println("valuse is " + fields[1] + "quantity is " + fields[2]);
			} catch (Exception E)
			{
				System.out.println("Can't convert from string to interger.");
				System.exit(0);
			}

			/** Add follow quantity of letter in to TreeSet */
			for (int j = 0; j < quantity; j++)
			{
				if (fields[0] != null)
				{
					tile = new Tile(fields[0], value);
					tiles.addTile(tile);
//					System.out.println(j);
				}
			}
		}
		// System.out.println(tiles.getHighest().getLetter());
//		System.out.println(tiles.getTileCount());
	}

	/**
	 * This static method use for create instance TileManager class
	 * 
	 * @return TileManager instance
	 */
	public static TileManager getInstance()
	{
		if (instance == null)
			instance = new TileManager();
		return instance;
	}

	/**
	 * Get the random tile from tile manager collection and give it to player and
	 * remove from tile manager collection
	 * 
	 * @return Tile with random selected
	 */
	public Tile selectRandomTile()
	{
		Tile randomTile = tiles.getRandom();
		if (randomTile != null)
		{
			tiles.removeTile(randomTile);
//			System.out.println(tiles.removeTile(randomTile));
		}
		return randomTile;
	}

	/** Main function for testing */
	public static void main(String args[])
	{
		TileManager tileManager = TileManager.getInstance();
		tileManager.initTile();
		for (int i = 0; i < 7; i++)
		{
			Tile randomTile = tileManager.selectRandomTile();
			if (randomTile != null)
				System.out.println(randomTile.getLetter() + " (" + randomTile.getValue() + ")");
		}
		System.out.println(tiles.getTileCount() + " tiles remaining");
	}
}
