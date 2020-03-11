
public class Player
{
	private String name = "";

	private int score;

	private TileCollection playerTiles;

	public Player(String name)
	{
		this.name = name;
	}

	public boolean selectTiles(int howMany)
	{
		for (int i = 0; i < howMany; i++)
		{
			TileManager tileManager = TileManager.getInstance();
			tileManager.selectRandomTile();
		}
		return true;
	}

	public String getName()
	{
		return name;
	}

	public int getScore()
	{
		return score;
	}

	public void updateScore(int points)
	{
		this.score = this.score + points;
	}

	public void printTiles()
	{

	}

	public static void main(String[] args)
	{
		TileCollection playerTileCollection = new TileCollection(0, 100);
		Player player1 = new Player("tong");
		TileManager tileManager = TileManager.getInstance();
		player1.selectTiles(5);
	}
}
