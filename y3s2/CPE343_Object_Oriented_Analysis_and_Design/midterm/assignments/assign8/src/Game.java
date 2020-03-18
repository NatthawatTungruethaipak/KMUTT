/**
 * Game.java
 * 
 * This class handle the game that connect to the user
 * 
 * Created by Natthawat Tungruethaipak, 18 March 2020
 */
public class Game
{
	/**
	 * This method create the player and find the player that has the highest tile
	 * in the group to be start player
	 * 
	 * @param numberPlayer is number of player that going to create
	 * @return player that has the highest tile in the group of players
	 */
	public static Player newGame(int numberPlayer)
	{
		int index = 0; // index of array
		int startPlayer = 0; // player going to start
		Tile currentTile = null; // current tile that considering
		Tile highestTile = null; // highest tile of startPlayer
		Player players[] = new Player[numberPlayer];
		Board board = null;
		TileManager.initialize();
		String[] sequence = { "first", "second", "third", "fourth" };

		/* create the players */
		for (index = 0; index < numberPlayer; index++)
		{
			players[index] = null; // clean old data
			String playerName = IOUtils.getString("Enter " + sequence[index] + " player name");
			players[index] = new Player(playerName);
			if (players[index].selectTiles(1) == false) // try to select a tile from TileManager
			{
				System.out.println("Can't select a tile, system exit");
				System.exit(0);
			} else
			{
				currentTile = players[index].getHighest();
				System.out.println(players[index].getName() + " selected " + currentTile);
				// find highestTile of player in the group
				if ((highestTile == null) || (highestTile.getTileValue() < currentTile.getTileValue()))
				{
					highestTile = currentTile;
					startPlayer = index;
				}
			}
		}
		board = new Board();
		String file = board.getBoardImage();
		System.out.println("Board image is: " + file);
		return players[startPlayer];
	}

	public static void main(String[] args)
	{
		int numberPlayer = 0;
		Player startPlayer;
		while (true)
		{
			numberPlayer = IOUtils.getInteger("Enter number of players (between 2 to 4 players)");
			if ((numberPlayer >= 2) && (numberPlayer <= 4))
			{
				startPlayer = Game.newGame(numberPlayer);
				break;
			}
		}
		System.out.println(startPlayer.getName() + " will start the game");
		System.exit(0);
	}
}
