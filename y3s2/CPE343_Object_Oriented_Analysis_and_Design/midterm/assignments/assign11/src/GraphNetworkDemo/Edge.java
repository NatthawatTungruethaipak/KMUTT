/**
 * This class represents a edge in a graph.
 *
 *   Created by Sally Goldin for CPE111, 29 April 2020
 */

public class Edge
{
    /** vertex at start of edge */
    protected Vertex fromVertex;

    /** vertex at end of edge */
    protected Vertex toVertex;

    /** Constructor sets the to and from vertices
     * @param from    From vertex
     * @param to      To vertex
     */
    public Edge(Vertex from, Vertex to)
    {
	fromVertex = from;
	toVertex = to;
    }
    
    /**
     * Getter for from vertex
     * @return vertex at start of edge
     */
    public Vertex getFromVertex()
    {
	return fromVertex;
    }

    /**
     * Getter for to vertex
     * @return vertex at end of edge
     */
    public Vertex getToVertex()
    {
	return toVertex;
    }
    
    /**
     * Override toString to return edge information
     */
    public String toString()
    {
	return new String("Edge from (" + fromVertex +
			  ") to (" + toVertex + ")");
	/* note that this will call the toString() methods
	 * for the vertices 
	 */
    }
    

}