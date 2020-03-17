/**
 *  TileManager2.java
 *
 *  This class represents the pool of unselected tiles in the
 *  game of Scrabble.
 *
 *  Use private constructor plus getInstance() to avoid static methods.
 *
 *  Created by Sally Goldin, 13 March 2019
 */
public class TileManager2
{
    private final String tileFileName = "alltiles.txt";

    /**
     * collection of tiles in the pool
     */
    private TileCollection tiles = new TileCollection(0,100);

    /**
     * Single, private instance of the class
     */
    private static TileManager2 singletonInstance = new TileManager2();

    /**
     * Private constructor. Nobody can create new instances
     */
    private TileManager2()
    {
	System.out.println("Private constructor called");
    }


    /**
     * Public method to return the singleton instance
     */
    public static TileManager2 getInstance()
    {
	return singletonInstance;
    }

    /**
     * Set up all the tiles necessary for a new game
     * This could be done via reading from a file or
     * by hardcoding the data
     */
    public void initialize()
    {
	tiles.clear();  /* get rid of any old tiles */
	TileFileReader reader = new TileFileReader();
	if (!reader.open(tileFileName))
	{
	    System.out.println("Error opening tile file " + 
			       tileFileName + " in TileManager2:initialize()");
	    System.exit(0);
	}
        Tile nextTile = null;
	while ((nextTile = reader.getTile()) != null)
	{
	    boolean bOk = tiles.addTile(nextTile);
	    if (bOk)
	    {
		//System.out.println("Successfuly added " + nextTile);
	    }
	    else
	    {
		System.out.println("Error adding " + nextTile);
	    }
	}
	System.out.println("TileManager2 initialized");
	System.out.println("CONTENTS:");
	tiles.printTiles();
    }

    /**
     * Get a random tile from the pool and return it to the
     * user, deleting it from the collection.
     * @return random Tile or null if the pool is empty
     */
    public Tile selectRandomTile()
    {
	Tile theTile = tiles.getRandom();
	if (theTile != null)
	{
	    tiles.removeTile(theTile);
	}
	return theTile;
    }

    /**
     * Get current number of tiles in the pool 
     * @return current size of tile collection
     */
    public int getTilesRemaining()
    {
	return tiles.getTileCount();
    }

    /** Main function for testing */
    public static void main(String args[])
    {
	TileManager2 instance1 = TileManager2.getInstance();
	TileManager2 instance2 = TileManager2.getInstance();
	System.out.println("First instance: " + instance1.toString());
	System.out.println("Second instance: " + instance2.toString());
	System.out.println("About to initialize");
        TileManager2.getInstance().initialize();
	for (int i = 0; i < 5; i++)
	{
	    Tile t = instance1.selectRandomTile();
	    if (t != null)
		System.out.println("Selected " + t + " from instance 1");
	}
	System.out.println(instance2.getTilesRemaining() + " tiles remain in pool (requested from instance 2)");
    }

}