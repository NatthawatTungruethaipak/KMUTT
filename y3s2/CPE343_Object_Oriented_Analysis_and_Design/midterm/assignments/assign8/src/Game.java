
public class Game
{

	public Player newGame(int numberPlayer)
	{
		int i = 0;
		int startPlayer = 0;
		Tile currentTile = null;
		Tile highestTile = null;
		Player players[] = new Player[4];
		Board board = null;
		TileManager.initialize();

		/* clear old data */
		for (i = 0; i < 4; i++)
		{
			players[i] = null;
		}
		/* create the players */
		for (i = 0; i < numberPlayer; i++)
		{
			String playerName = IOUtils.getString("Enter name for player " + (i + 1));
			players[i] = new Player(playerName);
			if (!players[i].selectTiles(1))
			{
				System.out.println("Can't select a tile, system exit");
				System.exit(0);
			} else
			{
				currentTile = players[i].getHighest();
				System.out.println("Player " + (i + 1) + " picked tile " + currentTile);
				if ((highestTile == null) || (currentTile.getTileValue() > highestTile.getTileValue()))
				{
					highestTile = currentTile;
					startPlayer = i;
				}
			}
		}
		board = new Board();
		String file = board.getBoardImage();
		System.out.println(file);
		return players[startPlayer];
	}

	public static void main(String[] args)
	{
		int numberPlayer = 0;
		Player startPlayer;
		Game game = new Game();
		while ((numberPlayer < 2) || (numberPlayer > 4))
		{
			numberPlayer = IOUtils.getInteger("Enter number of players");
//			System.out.println(numberPlayer);
		}
		startPlayer = game.newGame(numberPlayer);
		System.out.println("Player " + startPlayer.getName() + " will go first");
	}

}
