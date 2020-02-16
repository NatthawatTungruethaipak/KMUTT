import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

/**
 * FigureViewer
 *
 * Simple graphical application to display simple geometric figures
 *
 * Created by Sally Goldin, 23 April 2013 for CPE 113 Augmented for CPE372, 13
 * August 2017 Modified for Exercise 2, CPE372 19 August 2017 Removed the
 * drawing completely, added function to return graphics
 * 
 * Modified by Natthawat Tungruethaipak 60070503426, 13 February 2020
 */
public class FigureViewer extends JFrame implements ActionListener, MouseListener
{
	/* UI objects */
	private DrawingCanvas drawCanvas = null;
	private JButton clearButton = null;
	private JButton exitButton = null;

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
		mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		JPanel controlPanel = new JPanel(new FlowLayout());
		controlPanel.setBorder(new EtchedBorder());

		clearButton = new JButton("Clear");
		clearButton.addActionListener(this);
		controlPanel.add(clearButton);

		exitButton = new JButton("Exit");
		exitButton.addActionListener(this);
		controlPanel.add(exitButton);
		mainPanel.add(controlPanel, BorderLayout.NORTH);

		drawCanvas = new DrawingCanvas(400, 400);
		drawCanvas.setBorder(new EtchedBorder());
		drawCanvas.setBackground(Color.white);
		mainPanel.add(drawCanvas, BorderLayout.CENTER);
		drawCanvas.addMouseListener(this);
		getContentPane().add(mainPanel, BorderLayout.CENTER);
	}

	/**
	 * This is the method required for the ActionListener interface. It handles the
	 * necessary actions when buttons are pressed.
	 */
	public void actionPerformed(ActionEvent e)
	{
		Object source = e.getSource();
		if (source == exitButton)
		{
			System.exit(0);
		} else if (source == clearButton)
		{
			drawCanvas.clear();
		}
	}

	/**
	 * Clear the drawing panel.
	 */
	public void clear()
	{
		drawCanvas.clear();
	}

	/**
	 * Return the graphics context associated with the panel used for drawing.
	 * 
	 * @return Graphics context
	 */
	public Graphics2D getViewerGraphics()
	{
		return (Graphics2D) drawCanvas.getGraphics();
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
//		AbstractShape mySquare = new Square(e.getX(), e.getY(), 20);
//		Graphics g = getGraphics();
		int myShape = AbstractShape.allFigures.size();
		for (AbstractShape figures : AbstractShape.allFigures)
		{
			figures.inShape(e.getX(), e.getY());
		}
		System.out.println(myShape);
//		g.setColor(Color.BLUE);
//		g.fillOval(e.getX(), e.getY(), 30, 30);
//		mySquare.draw(this.getViewerGraphics());
		System.out.println(e.getX() + " " + e.getY());
	}

	@Override
	public void mouseEntered(MouseEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		// TODO Auto-generated method stub

	}
}
