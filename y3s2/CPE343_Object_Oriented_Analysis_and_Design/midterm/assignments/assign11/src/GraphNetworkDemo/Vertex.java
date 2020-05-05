/**
 * This class represents a vertex in a graph or network.
 * This simple version just carries a string as its data.
 * If you wanted a vertex that could hold some other kind of
 * information (e.g. a person, in a social network application)
 * you could just extend this by creating a specific subclass.
 *
 *   Created by Sally Goldin for CPE111, 29 April 2020
 */

public class Vertex
{
    /** declare constants for vertex colors */
    public static final int WHITE = 0;
    public static final int GRAY = 1;
    public static final int BLACK = 2;

    /** Key - should be unique */
    protected String key;

    /** Data value - just a string for now */
    protected String vertexData;

    /** Color for traversals */
    protected int color;

    /** Previous vertex, for traversals and path printing */
    protected Vertex fromVertex;

    /** Edge from the 'fromVertex' for path printing */
    protected Edge viaEdge;

    /** Constructor sets key and data information
     * @param  vKey    Vertex key
     * @param  vData   Vertex data
     */
    public Vertex(String vKey, String vData)
    {
	key = vKey;
	vertexData = vData;
    }

    /**
     * Getter function for key  
     * @return the key
     */
    public String getKey()
    {
	return key;
    }

    /**
     * Getter function for data  
     * @return the key
     */
    public String getVertexData()
    {
	return vertexData;
    }

    /**
     * Getter function for color
     * @return color
     */
    public int getColor()
    {
	return color;
    }

    /**
     * Getter function for fromVertex  
     * @return the prior vertex in the path
     */
    public Vertex getFromVertex()
    {
	return fromVertex;
    }


    /**
     * Getter function via the   
     * @return the edge leading from the 'fromVertex'
     */
    public Edge getViaEdge()
    {
	return viaEdge;
    }

    /**
     * Setter function for color, needed for traversals
     * @param newColor   New color value
     */
    public void setColor(int newColor)
    {
	color = newColor;
    }

    /**
     * Setter function for fromVertex, needed for traversals
     * @param theVertex   From vertex - could be null
     */
    public void setFromVertex(Vertex theVertex)
    {
	fromVertex = theVertex;
    }

    /**
     * Setter function for fromVertex, needed for traversals
     * @param the edge leading from the 'fromVertex' - could be null
     */
    public void setViaEdge(Edge edge)
    {
	viaEdge = edge;
    }


    /**
     * Override toString() to print the info about the vertex
     */
    public String toString()
    {
	StringBuffer buffer = new StringBuffer();
	buffer.append("KEY: " + key);
	buffer.append(" DATA: " + vertexData);
	return buffer.toString();
    }
} 
