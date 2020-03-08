import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

/**
 *  FigureViewer
 *
 *  Simple graphical application to display simple geometric figures
 *
 *  Created by Sally Goldin, 23 April 2013 for CPE 113
 *  Augmented for CPE372, 13 August 2017
 *  Modified for Exercise 2, CPE372 19 August 2017
 *     Removed the drawing completely, added function to return graphics 
 *  Modified 5 Sept 2017 for Exercise 5, CPE372, to implement MouseListener and
 *     highlight selected shape
 *  Modified 19 Feb 2019 to use the shape's drawing color for fill.
 */
public class FigureViewer extends JFrame 
                     implements ActionListener,MouseListener
{
   /* UI objects */
   private DrawingCanvas drawCanvas = null;
   private JButton selectButton = null;
   private JButton clearButton = null;
   private JButton drawAllButton = null;
   private JButton exitButton = null;
   private boolean bMultiSelect = false;

   /**
    * Constructor creates the User Interface.
    */
   public FigureViewer()
   {
      super("Figure Viewer");
      buildUI();
    
   }

   /**
    * Create the visible part of the user interface. 
    */
   private void buildUI()
   {
      JPanel mainPanel = new JPanel(new BorderLayout());
      mainPanel.setBorder(new EmptyBorder(10,10,10,10));
      JPanel controlPanel = new JPanel(new FlowLayout());
      controlPanel.setBorder(new EtchedBorder());

      selectButton = new JButton("Single Select");
      selectButton.addActionListener(this);
      controlPanel.add(selectButton);

      clearButton = new JButton("Clear");
      clearButton.addActionListener(this);
      controlPanel.add(clearButton);

      drawAllButton = new JButton("Draw all");
      drawAllButton.addActionListener(this);
      controlPanel.add(drawAllButton);

      exitButton = new JButton("Exit");
      exitButton.addActionListener(this);
      controlPanel.add(exitButton);
      mainPanel.add(controlPanel, BorderLayout.NORTH);
 
      drawCanvas = new DrawingCanvas(400,400);
      drawCanvas.setBorder(new EtchedBorder());
      drawCanvas.setBackground(Color.white);
      drawCanvas.addMouseListener(this);
      mainPanel.add(drawCanvas, BorderLayout.CENTER);
      getContentPane().add(mainPanel, BorderLayout.CENTER);
   }

   /** 
    * This is the method required for the ActionListener 
    * interface. It handles the necessary actions when 
    * buttons are pressed.
    */
   public void actionPerformed(ActionEvent e)
   {
       Object source = e.getSource();
       if (source == exitButton)
       {
	   System.exit(0);
       }
       else if (source == selectButton)
       {
	   if (bMultiSelect)
	   {
	       selectButton.setText("Select Single");
	       System.out.println("\nSINGLE SELECT MODE\n");
	   }
	   else 
	   {
	       selectButton.setText("Select Multiple");
	       System.out.println("\nMULTI-SELECT MODE\n");
	   }
	   bMultiSelect = ! bMultiSelect;
       }
       else if (source == clearButton)
       {
	   drawCanvas.clear();
       }
       else if (source == drawAllButton)
       {
	   AbstractShape.drawAll((Graphics2D) drawCanvas.getGraphics());
       }
   }

     /** MouseListener methods **/
     /* we are only interested in clicks, so we leave
      * the other methods empty. If these events
      * happen, the program will simply ignore them.
      */
     public void mouseEntered(MouseEvent mouseEv)
        {
        }
     public void mouseExited(MouseEvent mouseEv)
        {
        }
     public void mousePressed(MouseEvent mouseEv)
        {
        }
     public void mouseReleased(MouseEvent mouseEv)
        {
        }

     /**
      * If the user clicks the mouse in the drawing area,
      * get the point and pass it to findSelectedShape or
      * findAllSelectedShapes
      */
     public void mouseClicked(MouseEvent mouseEv)
     {
	System.out.println("Clicked at point " + mouseEv.getX() + "," +
			    mouseEv.getY());
	if (bMultiSelect)
	{
	    ArrayList<AbstractShape> list = 
		AbstractShape.findAllSelectedShapes(mouseEv.getX(),mouseEv.getY());
	    if (list.size() > 0)
	    {
		Iterator it = list.iterator();
		while (it.hasNext())
		{
		    AbstractShape selected = (AbstractShape) it.next();
		    System.out.println("Selected: " + selected.toString());
		    selected.draw((Graphics2D) drawCanvas.getGraphics(),selected.getDrawColor());
		}
	    }	
	}
	else
	{
	    AbstractShape selected = 
		AbstractShape.findSelectedShape(mouseEv.getX(), mouseEv.getY());
	    
	    if (selected != null)
	    {
		System.out.println("Selected: " + selected.toString());
		selected.draw((Graphics2D) drawCanvas.getGraphics(),selected.getDrawColor());
	    }
	}
     }

   /** Clear the drawing panel.
    */
   public void clear()
   {
       drawCanvas.clear();
   }

   /**
    * Return the graphics context associated with
    * the panel used for drawing.
    @return  Graphics context
    */
   public Graphics2D getViewerGraphics()
   {
       return (Graphics2D) drawCanvas.getGraphics();
   }
}




