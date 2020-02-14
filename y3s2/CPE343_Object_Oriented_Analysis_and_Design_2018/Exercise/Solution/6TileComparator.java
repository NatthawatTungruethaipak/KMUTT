import java.util.*;
/**
 *  TileComparator.java
 *
 *   Class to compare Tile objects.
 *
 *   Created by Sally Goldin, 2 October 2017
 *   Modified 13 March 2019 to get rid of warnings
 *   Note this comparator NEVER returns 0. If the scores are the
 *   same, it uses the sequence numbers to order the tiles
 */
public class TileComparator implements Comparator<Tile>
{
    /**
     * Fundamental method compares two tiles
     * @param  tile1    First tile
     * @param  tile2    Second tile
     * @return -1 if tile1 score < tile2, 1 if tile1 > tile2, difference
     *          between the sequence numbers if equal
     */
    public int compare(Tile tile1, Tile tile2)
    {
	int score1 = tile1.getTileValue();
	int score2 = tile2.getTileValue();
	int seq1 = tile1.getSequence();
	int seq2 = tile2.getSequence();
	if (score1 < score2)
	    return -1;
	else if (score1 > score2)
	    return 1;
	else
	    return (int) Math.signum(seq1 - seq2);
    }

}