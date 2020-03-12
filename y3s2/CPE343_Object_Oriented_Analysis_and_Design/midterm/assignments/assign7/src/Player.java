/**
 * Player.java
 *
 * This class represents the player in the Scramble game. All it can do is just
 * draw a tile from a bag from TileManager
 *
 * Created by Natthawat Tungruethaipak, 11 March 2020
 */
public class Player
{
	/**
	 * Store the name of player
	 */
	private String name = "";

	/**
	 * Store the score of player
	 */
	private int score;

	/**
	 * Tile collection of player
	 */
	private TileCollection playerTileCollection = new TileCollection(0, 7);

	/**
	 * Constructor that set the name of the player
	 * 
	 * @param name
	 */
	public Player(String name)
	{
		this.name = name;
	}

	public boolean selectTiles(int howMany)
	{
		boolean check = true;
		Tile tile = null;
		for (int i = 0; i < howMany; i++)
		{
			TileManager tileManager = TileManager.getInstance();
			tile = tileManager.selectRandomTile();
			if (playerTileCollection.addTile(tile) == false)
			{
				System.out.println("You reached the maximum that you can draw");
				if (i == 0)
					System.out.println("You cannot draw any tile");
				else
					System.out.println("You can draw only " + i + " tiles.");
				check = false;
				break;
			}
		}
		return true;
	}

	/**
	 * Get name of player
	 * 
	 * @return Player's name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Get score of player
	 * 
	 * @return Player's score
	 */
	public int getScore()
	{
		return score;
	}

	/**
	 * Update the score by add the points
	 */
	public void updateScore(int points)
	{
		this.score = this.score + points;
	}

	/**
	 * Print the tile of player
	 */
	public void printTiles()
	{
		System.out.println("Tiles of " + name);
		playerTileCollection.printTiles();
	}

	public static void main(String[] args)
	{
		TileManager tileManager = TileManager.getInstance();
		tileManager.initTile();
		Player player1 = new Player("tong");
		player1.selectTiles(7);
		player1.printTiles();
	}
}
