/**
 *   Created by Natkanok Poksappaiboon (P) 60070503421, 30 Jan 2020
 *
 */
import java.awt.*;


public class Diamond extends AbstractShape {
    private int horizontalAxis;
    private int verticalAxis;
    public Diamond(int x, int y, int vAxis, int hAxis)
    {
        if ((vAxis % 2) > 0)
            vAxis += 1;
        if ((hAxis % 2) > 0)
            hAxis += 1;
        horizontalAxis = hAxis;
        verticalAxis = vAxis;
        anchor = new Point(x,y);
        vertices.add(anchor);
        vertices.add(new Point(x+hAxis/2,y+vAxis/2));
        vertices.add(new Point(x,y+vAxis));
        vertices.add(new Point(x-hAxis/2,y+vAxis/2));
    }
    public double calcPerimeter()
    {
        Point p1 = anchor;
        Point p2 = vertices.get(1);
        double len = Math.sqrt(Math.pow(p1.x - p2.x,2) +
                Math.pow(p1.y - p2.y,2));
        return len * 4;
    }
    public double calcArea()
    {
        return (double) (horizontalAxis * verticalAxis) / 2;
    }
}
