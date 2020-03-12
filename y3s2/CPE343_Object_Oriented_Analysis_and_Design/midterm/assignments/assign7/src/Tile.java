
/**
 * Tile.java
 *
 * This class represents a tile in the Scramble game.
 *
 * Created by Natthawat Tungruethaipak, 11 March 2020
 */

public class Tile implements Comparable<Tile>
{
	/** Keep the tile letter */
	private String tileLetter;

	/** Keep the tile value */
	private int tileValue = 0;

	/** Keep the tile Sequenc */
	private int tileSequence;

	/** Count for sequence of tile **/
	private static int counter = 0;

	/**
	 * Create Tile with specific letter and value.
	 * 
	 * @param letter letter of the tile
	 * @param value  value of the tile
	 */
	public Tile(String letter, int value)
	{
		this.tileLetter = letter;
		this.tileValue = value;
		counter++;
		this.tileSequence = counter;

	}

	/**
	 * Get letter from tile
	 * 
	 * @return Alphabet in tile
	 */
	public String getLetter()
	{
		return tileLetter;
	}

	/**
	 * Get value from tile
	 * 
	 * @return value in tile
	 */
	public int getValue()
	{
		return tileValue;
	}

	/**
	 * Get sequence from tile
	 * 
	 * @return sequence in tile
	 */
	public int getSequence()
	{
		return tileSequence;
	}

	/**
	 * This is my reference
	 * https://howtodoinjava.com/java/collections/java-comparable-interface/ and I
	 * ask Nathaphop about this method. Method that override from the Comparable
	 * interfaces. It can compare its self with another tile. order based on 1) the
	 * score; 2) if same score, the alphabetic order of the letters; 3) if the same
	 * score and the same letter, an internal sequence number that I set in the
	 * constructor.
	 * 
	 * @param otherTile other tile to compare
	 */
	@Override
	public int compareTo(Tile otherTile)
	{
		if (this.tileValue - otherTile.getValue() > 0)
		{
			return this.tileValue - otherTile.getValue();
		} else if (this.getLetter().compareTo(otherTile.getLetter()) > 0)
		{
			return this.getLetter().compareTo(otherTile.getLetter());
		} else
		{
			return this.tileSequence - otherTile.getSequence();
		}
	}
}
