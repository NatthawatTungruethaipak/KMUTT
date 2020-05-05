/**
 * Java implementation of a graph.
 * Can be either directed or undirected. If directed, we create edges
 * in both directions when addEdge is called.
 *
 * Created by Sally Goldin, 29 April 2020 for CPE111.
 * Not a complete implementation of the ADT as it does not do removeVertex
 * or removeEdge
 */
import java.util.*;

public class Graph
{

    /** local queue for breadth first traversal */
    protected LinkedList<Vertex> localQueue = new LinkedList<Vertex>();

    /** Hash table holding vertices, keyed by vertex key */
    protected Hashtable<String,Vertex> vertices;

    /** Hash table holding edges, key is key of the fromVertex 
     * Value is list of all edges coming out of the vertex.
     */
    protected Hashtable<String,ArrayList<Edge>> edges;

    /** is this a directed graph? */
    boolean bDirected = true;  /* directed is the default */

    /** Constructor creates hashtables, sets bDirected.
     * Replaces initGraph() in the ADT.
     * @param  isDirected    Directed or not"
     */
    public Graph(boolean isDirected)
    {
	vertices = new Hashtable<String,Vertex>();
	edges = new Hashtable<String,ArrayList<Edge>>();
	bDirected = isDirected;
    }

    /** Delete all vertices and edges in the graph
     */
    public void clearGraph()
    {
	vertices.clear();
	edges.clear();
    }


    /** Create a new vertex and add into into the graph.
     * @param  key     Key value or label for the vertex
     * @param  data    Additional information 
     * @return true if all okay, false if a vertex with this key
     * already exists
     */
    public boolean addVertex(String key, String data)
    {
	boolean bOk = true;
	if (vertices.containsKey(key))
	    bOk = false;
	else
	    {
	    Vertex v = new Vertex(key,data);
	    vertices.put(key,v);
	    }
	return bOk;
    }

    /** Add an edge between two vertices
     * @param  key1  Key for the first vertex in the edge
     * @param  key2  Key for the second vertex
     * @return 1 if successful, 0 if failed due to errors
     * in parameters (vertex does not exist, from and to are the same),
     * -1 if an edge already exists in this direction.
     */
    public int addEdge(String key1, String key2)
    {
	int retval = 1;
	if (key1.compareTo(key2) == 0) /* from and to the same */
	    retval = 0;
	else if ((!vertices.containsKey(key1)) ||  /* at least one vertex does not exist */
		 (!vertices.containsKey(key2)))
	    retval = 0;
	else
	{
	    ArrayList<Edge> myEdges = edges.get(key1);
	    if (myEdges != null) /* not the first edge */
	    {
		Iterator it = myEdges.iterator();
		while (it.hasNext())
		{
		    Edge myEdge = (Edge) it.next();
		    if (myEdge.getToVertex().getKey().compareTo(key2) == 0) /* edge exists */
			{
			    retval = -1;
			    break;
			}
		}
	    }
	    if (retval == 1) /* still okay */
	    {
		if (myEdges == null) /* no previous edges */
		    {
		    myEdges = new ArrayList<Edge>(); /* create new list */
		    edges.put(key1,myEdges);         /* put into the hash table */
		    }
		Edge e = new Edge(vertices.get(key1),vertices.get(key2));
		myEdges.add(e);
	    }
	}
	if (!bDirected)  /* undirected - try adding in the other direction */
	    retval = addEdge(key2,key1);
	return retval;
    }


    /** Find a vertex and return its data
     * @param key  Key for the vertex to find
     * @return the data for the vertex or null if not found
     */
    public String findVertex(String key)
    {
	String dataString = null;
	if (vertices.containsKey(key))
	    dataString = vertices.get(key).getVertexData();
	return dataString;
    }

    /** Print out all the nodes reachable from a node by a 
     *  breadth-first search.
     * @param startKey   Key for start vertex
     * @param printVertices  If true, print - if false just
     *                       figure out the path
     * @return 1 if successful, -1 if the start vertex does not exist.
     */
    public int printBreadthFirst(String startKey,boolean printVertices)
    {
	int retval = 1;
	localQueue.clear();
	initAll();
	Vertex current = vertices.get(startKey);
	if (current == null)
	    retval = -1;	
	else 
	{
	    localQueue.addLast(current);
	    while (localQueue.size() > 0)
	    {
 		Vertex v = (Vertex) localQueue.removeFirst();
		v.setColor(Vertex.BLACK);
		if (printVertices)
		    System.out.println(v); /* print this vertex */
		ArrayList<Edge> myEdges = edges.get(v.getKey());
		/* then process any adjacents of this vertex */
		if (myEdges != null)
		{
		    for (int i = 0; i < myEdges.size(); i++)
		    {
			Vertex adjacent = myEdges.get(i).getToVertex();
			if (adjacent.getColor() == Vertex.WHITE)
			    {
			    adjacent.setFromVertex(v);
			    adjacent.setViaEdge(myEdges.get(i));
			    adjacent.setColor(Vertex.GRAY);	
			    localQueue.addLast(adjacent);
			    }
		    }
		}
	    }
	}
	return retval; 
    }

    /**
     * Print path calculated with breadth first traversal 
     * using 'fromVertex' and 'viaEdge' information.
     * @param startkey     Starting key
     * @param endKey       Ending key
     * @return 1 for success, -1 if start or end key does not exist
     */
    public int printPath(String startKey,String endKey)
    {
	int retval = 1;
	if ((!vertices.containsKey(startKey)) ||
            (!vertices.containsKey(endKey)))
	    retval = -1;
	else
	{
	    printBreadthFirst(startKey,false);
	    Vertex end = vertices.get(endKey);
	    Vertex start = vertices.get(startKey);
	    if (end.getColor() == Vertex.WHITE)
	    {
		System.out.println(endKey + " is not reachable from " + startKey);
	    }
	    else
	    {
		ArrayList<Vertex> temp = new ArrayList<Vertex>();
		Vertex current = end;
		temp.add(current);
		while (current.getFromVertex() != null)
		{
		    current = current.getFromVertex();
		    temp.add(current);
		}
		System.out.println("PATH");
		for (int i = temp.size()-1; i >=0; i--)
		{
		    Vertex v = temp.get(i);
		    if (v.getViaEdge() != null)
			System.out.println("Via " + v.getViaEdge() + " to ");
		    System.out.println("Vertex " +v);
		}
		
	    }
	}
	return retval;
    }

    /** Print out all the nodes by a depth-first search starting at startKey
     * @param startKey   Key for start vertex
     * @return 1 if successful, -1 if the start vertex does not exist.
     */
    public int printDepthFirst(String startKey)
    {
	int retval = 1;
	initAll();
	Vertex start = vertices.get(startKey);
	if (start == null)
	    retval = -1;
	else
	    recursiveSearchPrint(start);
	return retval;
    }
    
    /**
     * Set all vertices to be Vertex.WHITE
     */
    protected void initAll()
    {
	Collection<Vertex> valueCollection =  vertices.values();
	Iterator it = valueCollection.iterator();
	while (it.hasNext())
	{
	    Vertex v = (Vertex) it.next();
	    v.setColor(Vertex.WHITE);
	    v.fromVertex = null;
	    v.viaEdge = null;
	}
    }

    /**
     * Component of depth first search - prints vertex as visited. 
     * @param current   Vertex where we are currently located
     */
    protected void recursiveSearchPrint(Vertex current)
    {
	ArrayList<Edge> myEdges = edges.get(current.getKey());
	if (myEdges != null)
	{
	    for (int i = 0; i < myEdges.size(); i++)
	    {
		Vertex adjacent = myEdges.get(i).getToVertex();
		if (adjacent.getColor() == Vertex.WHITE)
		    {
		    adjacent.setColor(Vertex.GRAY);
		    recursiveSearchPrint(adjacent);
		    }
	    }
	}
	System.out.println(current); /* will call the toString() method */
	current.setColor(Vertex.BLACK);
    }

}