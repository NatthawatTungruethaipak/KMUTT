import java.util.*;
import javax.swing.*;
/**
 * Demo class showing the polymorphic behavior of toString()
 *
 *   Created by Sally Goldin, 28 Aug 2017
 */
public class PolyDemo
{

    /**
     * Array list that can hold any sort of Java object
     */
    public ArrayList<Object> objectlist = new ArrayList<Object>();

    /**
     * Constructor puts a miscellaneous set of objects into the array list
     */
    public PolyDemo()
    {
	objectlist.add(new String("OOD is fun"));
	objectlist.add(new Integer(22));
	objectlist.add(new Double(0.2114));
	objectlist.add(new Double(Math.random()));
	objectlist.add(new Date());
	objectlist.add(new JPanel());
	objectlist.add(new JButton());
	objectlist.add(new Square(22,33,100));
	objectlist.add(new Circle(100,120,40));
	objectlist.add(this);
    }

    /**
     * Method to display the contents of the arraylist
     * using polymorphic toString() method.
     */
    public void showObjects()
    {
	Iterator it = objectlist.iterator();
	while (it.hasNext())
	{
	    Object object = it.next();
	    System.out.println("ToString returns: " +
			       object.toString() + "\n");
	}    
    }


    /**
     * Method to display the contents of the arraylist
     * in terms of what classes they are.
     */
    public void showObjectClasses()
    {
	Iterator it = objectlist.iterator();
	while (it.hasNext())
	{
	    Object object = it.next();
	    System.out.println("Object is an instance of: " +
			       object.getClass().toString() + "\n");
	}    
    }

    /**
     * Main method creates one instance then calls showObjects
     */
    static public void main(String args[])
    {
	PolyDemo pd = new PolyDemo();
        pd.showObjects();

	System.exit(0);
    }

    //System.out.println("---------------------------");
    //pd.showObjectClasses();

}