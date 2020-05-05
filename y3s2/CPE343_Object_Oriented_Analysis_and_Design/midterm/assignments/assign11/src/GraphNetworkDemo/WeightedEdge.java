/**
 * This class represents a edge in a network.
 * It is a child of a normal Edge, with an additional weight attribute
 *
 *   Created by Sally Goldin for CPE111, 29 April 2020
 */

public class WeightedEdge extends Edge
{
    protected int weight;

    /** Constructor sets the to and from vertices
     * @param from    From vertex
     * @param to      To vertex
     * @param w       Weight for this edge  
     */
    public WeightedEdge(Vertex from, Vertex to,int w)
    {
	super(from,to);
	weight = w;
    }

    /**
     * Getter for weight
     * @return weight of the edge
     */
    public int getWeight()
    {
	return weight;
    }

    /**
     * Setter for the weight. This lets us use
     * almost all of the Graph code for creating an edge.
     * @param w   Weight for this edge
     */
    public void setWeight(int w)
    {
	weight = w;
    }

    /**
     * Override toString to return edge information
     */
    public String toString()
    {
	return new String("Edge from (" + fromVertex +
			  ") to (" + toVertex + ") with weight " + weight);
	/* note that this will call the toString() methods
	 * for the vertices 
	 */
   }
    

}