import java.util.TreeSet;

public class test
{

	public static void main(String[] args)
	{
		TreeSet<Tile> treeSet = new TreeSet<Tile>();
		treeSet.add(new Tile("B", 2));
		treeSet.add(new Tile("A", 1));
		treeSet.add(new Tile("C", 10));
		treeSet.add(new Tile("Z", 4));
		System.out.println("TreeSet Lowest value = " + treeSet.first().getValue());
		System.out.println("TreeSet Highest value = " + treeSet.last().getValue());
	}

}
