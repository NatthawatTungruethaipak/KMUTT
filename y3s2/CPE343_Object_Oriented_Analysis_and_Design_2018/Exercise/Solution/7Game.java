import java.io.IOException;
/**
 * Game.java
 *
 * This is a facade class for our Scrabble example. It represents a single
 * game and coordinates the method calls to different classes that
 * manage the details of the game.
 *
 *   Created by Sally Goldin, 10 October 2017
 */
public class Game
{
    /**
     * Array of players - can be from 2 to 4 
     */
    private Player players[] = new Player[4];

    /**
     * Number of players in this game 
     */
    private int playerCount = 0;

    /** 
     * Reference to the board
     */
    private Board board = null;

    /**
     * Start a new game for 'count' players
     * @param count    Number of players 
     * @return Player object for player that should go first.
     */
    public Player newGame(int count)
    {
	int i = 0;
	int firstPlayer = 0;
	Tile topTile = null;
	Tile currentTile = null;
	playerCount = count;
	TileManager.initialize();  
	/* clear any old information */
	for (i=0; i < 4; i++)
	    players[i] = null;
	/* create the players */
	for (i = 0; i < playerCount; i++)
	{
	    String name = IOFuns.getOneString("Enter name for player " + (i+1));
	    players[i] = new Player(name);
	    if (!players[i].selectTiles(1))
	    {
		System.out.println("Something is wrong; cannot select one tile!");
		System.exit(0);
	    }
	    else
	    {
		currentTile = players[i].getHighest();
		System.out.println("Player " + (i+1) + " picked tile " +
				   currentTile);
		if  ((topTile == null) ||
		     (currentTile.getTileValue() > topTile.getTileValue()))
		{
		    topTile = currentTile;
		    firstPlayer = i;
		}
	    }
        }
	board = new Board();
	board.initialize();
	String file = board.getBoardImage();
	BoardViewer viewer = new BoardViewer();
	viewer.displayBoard(file);
	return players[firstPlayer];
    }

    /**
     * main method to exercise the class. 
     */
    public static void main(String[] args)
    {
	int players = 0;
	Player firstPlayer;
	Game game = new Game();
        while ((players < 2) || (players > 4))
	{
	    players = IOFuns.getOneInteger("How many players? ");
	}
	firstPlayer = game.newGame(players);
	System.out.println("Player " + firstPlayer.getName() + " will go first");
    }

}
