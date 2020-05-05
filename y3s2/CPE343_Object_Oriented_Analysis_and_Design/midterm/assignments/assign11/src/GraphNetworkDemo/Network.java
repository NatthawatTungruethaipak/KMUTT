/**
 * Java implementation of a network.
 * Can be either directed or undirected. If directed, we create edges
 * in both directions when addEdge is called.
 * Most of the functionality is provided by the parent class Graph
 *
 * Created by Sally Goldin, 29 April 2020 for CPE111.
 * Not a complete implementation of the ADT as it does not do removeVertex
 * or removeEdge
 */
import java.util.*;

public class Network extends Graph
{


    /** Constructor creates hashtables, sets bDirected.
     * Replaces initGraph() in the ADT.
     * @param  isDirected    Directed or not"
     */
    public Network(boolean isDirected)
    {
	super(isDirected);
    }

    /** Add an edge between two vertices
     * @param  key1  Key for the first vertex in the edge
     * @param  key2  Key for the second vertex
     * @param  weight  Weight for this edge
     * @return 1 if successful, 0 if failed due to errors
     * in parameters (vertex does not exist, from and to are the same),
     * -1 if an edge already exists in this direction.
     */
    public int addEdge(String key1, String key2, int weight)
    {
	int retval = 1;
	if (key1.compareTo(key2) == 0) /* from and to the same */
	    retval = 0;
	else if ((!vertices.containsKey(key1)) ||  /* at least one vertex does not exist */
		 (!vertices.containsKey(key2)))
	    retval = 0;
	else if (edgeExists(key1,key2))
	    retval = -1;
	if (retval == 1) /* still okay */
	{
	    WeightedEdge e = 
		    new WeightedEdge(vertices.get(key1),vertices.get(key2),weight);
	    insertEdge(key1,e);
	}
	if (!bDirected)  /* undirected - try adding in the other direction */
	    retval = addEdge(key2,key1);
	return retval;
    }

}