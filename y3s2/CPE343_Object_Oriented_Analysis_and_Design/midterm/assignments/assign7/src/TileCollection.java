
/**
 * TileManager.java
 *
 * This class represents the pool of unselected tiles in the Scramble game.
 *
 * All methods are static because this is a singleton class.
 *
 * Created by Natthawat Tungruethaipak, 11 March 2020
 */

import java.util.TreeSet;

public class TileCollection
{
	/** Set of tile */
	private TreeSet<Tile> tiles = new TreeSet<Tile>();

	/** Maximum number of tile to be allowed */
	private int maxTiles = 0;

	/** Minimum number of tile to be allowed */
	private int minTiles = 0;

	/**
	 * Constructor to create TileCollection with config max and min tile to be
	 * allowed
	 * 
	 * @param minTiles Minimum of tile that want to set to be allowed
	 * @param maxTiles Maximum of tile that want to set to be allowed
	 */
	public TileCollection(int minTiles, int maxTiles)
	{
		this.minTiles = minTiles;
		this.maxTiles = maxTiles;
	}

	/**
	 * Print all the tiles in the collection
	 */
	public void printTiles()
	{
		for (Tile tile : tiles)
		{
			System.out.println(tile.getLetter() + " (" + tile.getValue() + ")");
		}
	}

	/**
	 * Get number of tiles
	 * 
	 * @return Number of tiles
	 */
	public int getTileCount()
	{
		return tiles.size();
	}

	/**
	 * Add a tile to the collection
	 * 
	 * @param tile Tile to add
	 * @return boolean that show can add or not
	 */
	public boolean addTile(Tile tile)
	{
		boolean canAdd = false;

		if (tiles.size() < maxTiles)
		{
			tiles.add(tile);
			canAdd = true;
		}
		return canAdd;
	}

	/**
	 * Remove a tile to the collection
	 * 
	 * @param tile Tile to remove
	 * @return boolean that show can remove or not
	 */
	public boolean removeTile(Tile tile)
	{
		boolean canRemove = false;

		if (tiles.size() > minTiles)
		{
			tiles.remove(tile);
			canRemove = true;
		}
		return canRemove;
	}

	/**
	 * Get the tile with the highest score
	 * 
	 * @return tile that has highest score
	 */
	public Tile getHighest()
	{
		return tiles.last();
	}

	/**
	 * Get the tile with the lowest score
	 * 
	 * @return tile that has lowest score
	 */
	public Tile getLowest()
	{
		return tiles.first();
	}

	/**
	 * This method convert TreeSet to array and return the index
	 * 
	 * @return Tile that randomed
	 */
	public Tile getRandom()
	{
		double randomDouble = Math.random();
		randomDouble = randomDouble * (maxTiles - 1) + 1;
		int randomInt = (int) randomDouble;
//		System.out.println(randomInt);
		if (tiles.isEmpty())
			return null;
		else
		{
			Object tilesInArray[] = tiles.toArray();
			this.maxTiles--;
			return (Tile) tilesInArray[randomInt];
		}
	}

	public static void main(String[] args)
	{
		int index;
		TileCollection myCollection = new TileCollection(0, 7);
		Tile[] myTile = new Tile[10];
		myTile[0] = new Tile("B", 2);
		myTile[1] = new Tile("A", 1);
		myTile[2] = new Tile("C", 10);
		myTile[3] = new Tile("Z", 4);
		myTile[4] = new Tile("P", 3);
		myTile[5] = new Tile("E", 1);
		myTile[6] = new Tile("Q", 10);
		for (Tile tile : myTile)
		{
			myCollection.addTile(tile);
		}
		System.out.println("TreeSet Lowest value = " + myCollection.getLowest().getLetter() + " "
				+ myCollection.getLowest().getValue());
		System.out.println("TreeSet Highest value = " + myCollection.getHighest().getLetter() + " "
				+ myCollection.getHighest().getValue());
		for (index = 0; index < 5; index++)
		{
			Tile randomTile = myCollection.getRandom();
			System.out.println(randomTile.getLetter() + " (" + randomTile.getValue() + ")");
			myCollection.removeTile(randomTile);
		}
	}
}
