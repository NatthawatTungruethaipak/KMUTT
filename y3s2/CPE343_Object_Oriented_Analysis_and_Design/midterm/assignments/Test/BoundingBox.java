package org.obicere.utility;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Obicere
 */
public class BoundingBox {

    public BoundingBox(){
        final JFrame frame = new JFrame("Bounding Box");

        frame.add(new DrawingPanel());

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);

    }

    public static void main(final String[] args){
        SwingUtilities.invokeLater(BoundingBox::new);
    }

    public class DrawingPanel extends JPanel {

        private final LinkedList<Polygon> polygons = new LinkedList<>();

        @Override
        public Dimension getPreferredSize(){
            return new Dimension(500, 500);
        }

        @Override
        public void addNotify() {
            super.addNotify();
            addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    final int x = e.getX();
                    final int y = e.getY();

                    final Polygon polygon = new Polygon();
                    polygon.addPoint(x - 5, y - 5);
                    polygon.addPoint(x - 5, y + 5);
                    polygon.addPoint(x + 5, y + 5);
                    polygon.addPoint(x + 5, y - 5);

                    polygons.add(polygon);
                    repaint();
                }
            });
        }

        @Override
        protected void paintComponent(final Graphics g) {
            super.paintComponent(g);

            g.setColor(Color.RED);

            polygons.forEach(g::drawPolygon);

            g.setColor(Color.GREEN);

            final Rectangle box = getBoundingBox(polygons);

            g.drawRect(box.x, box.y, box.width, box.height);
        }

        private Rectangle getBoundingBox(final List<Polygon> polygons) {
            if (polygons == null || polygons.isEmpty()) {
                return new Rectangle(-1, -1, 0, 0);
            }

            int minX = Integer.MAX_VALUE;
            int minY = Integer.MAX_VALUE;
            int maxX = Integer.MIN_VALUE;
            int maxY = Integer.MIN_VALUE;

            for (final Polygon polygon : polygons) {
                final Rectangle polygonBounds = polygon.getBounds();

                int ax = polygonBounds.x;
                int ay = polygonBounds.y;
                int bx = ax + polygonBounds.width;
                int by = ay + polygonBounds.height;

                minX = Math.min(ax, minX);
                minY = Math.min(ay, minY);
                maxX = Math.max(bx, maxX);
                maxY = Math.max(by, maxY);
            }

            final Rectangle boundingBox = new Rectangle(minX, minY, 1, 1);
            boundingBox.add(maxX, maxY);

            return boundingBox;
        }

    }

}