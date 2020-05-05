/**
 * Graph and network tester program
 * Created to exercise the demonstration classes related to graphs and networks
 * All methods are static for simplicity.
 *
 *  Created by Sally Goldin, 29 April 2020, for CPE111
 *
 */

public class GraphNetworkTester
{

    private static Graph myGraph;
    private static boolean bGraph = true;


    /** print menu of options 
     */
    public static void printMenu()
    {
	System.out.println("Available actions:");
	System.out.println("  1  Clear graph/network");
	System.out.println("  2  Add vertex");
	System.out.println("  3  Add edge");
	System.out.println("  4  Breadth-first traversal");
	System.out.println("  5  Depth-first traversal");
	System.out.println("  6  Print path from V1 to V2");
	System.out.println("  7  Exit");
    }

    /**
     * Process according to the option the user requested
     * @param  option   Numerical option choice from menu
      */
    public static void processOption(int option)
    {
	if ((option < 1) || (option > 6))
	    return;
	int returnValue = 1;
	Graph graph;
	if (bGraph)
	    graph = myGraph;
	else
	    graph = myGraph;  /* change to network later */
	switch(option)
	{
	  case 1:
	      graph.clearGraph();
	      break;
   	  case 2:
	      String key = IOUtils.getString("Enter key for new vertex: ");
	      String data = IOUtils.getString("Enter data for new vertex: ");
	      if (graph.addVertex(key,data))
		  System.out.println("   Success!");
	      else
		  System.out.println("   Error - key already exists");
	      break;
	  case 3:
	      String key1 = IOUtils.getString("Enter key for start vertex: ");
	      String key2 = IOUtils.getString("Enter key for end vertex: ");
	      if (bGraph)
	      {
		  returnValue = graph.addEdge(key1,key2);
	      }
	      else
	      {
		  int weight = IOUtils.getInteger("Enter weight for the edge: ");
		  returnValue = ((Network) graph).addEdge(key1,key2,weight);
		  
	      }
	      if (returnValue == 1)
		  System.out.println("   Success!");
	      else if (returnValue == 0)
		  System.out.println("   Error in vertex keys (non-existent or duplicate)");
	      else if (returnValue == -1)
		  System.out.println("   Edge already exists between " + key1 + " and " + key2);
	      break;
	   case 4:
	      String keyB = IOUtils.getString("Start breadth first at what vertex? ");
	      returnValue = graph.printBreadthFirst(keyB,true);
	      if (returnValue < 0)
		  System.out.println("   Starting vertex does not exist!");
	      break;
	   case 5:
	      String keyD = IOUtils.getString("Start depth first at what vertex? ");
	      returnValue = graph.printDepthFirst(keyD);
	      if (returnValue < 0)
		  System.out.println("   Starting vertex does not exist!");
	      break;
	    case 6:
	      String keyS = IOUtils.getString("Enter key for start vertex: ");
	      String keyE = IOUtils.getString("Enter key for end vertex: ");
	      returnValue = graph.printPath(keyS,keyE);
	      if (returnValue < 0)
		  System.out.println("   Starting or ending vertex does not exist!");
	      break;

	}
    }

    public static void main(String args[])
    {
	int option = 0;
	String structType = IOUtils.getString("Graph (G) or Network (N)? ");
	if (structType.startsWith("G"))
	    myGraph = new Graph(true);
	else
	{
            bGraph = false;
	    myGraph = new Network(true);
	}
	
	while (option != 7)
        {
	    printMenu();
	    option = IOUtils.getInteger("Your choice? ");
	    processOption(option);
	}

    }










}
