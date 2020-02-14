import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.*;
import java.io.*;
import javax.imageio.*;

/**
 *  BoardViewer
 *
 *  Simple graphical application to display ScrabbleBoard
 *
 *  Created by Sally Goldin, 20 March 2019 for CPE 372
 */
public class BoardViewer extends JFrame 
                     implements ActionListener
{
   /* UI objects */
   private DrawingCanvas drawCanvas = null;
   private JButton exitButton = null;


   /**
    * Constructor creates the User Interface.
    */
   public BoardViewer()
   {
      super("Board Viewer");
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

      exitButton = new JButton("Exit");
      exitButton.addActionListener(this);
      controlPanel.add(exitButton);
      mainPanel.add(controlPanel, BorderLayout.NORTH);
 
      drawCanvas = new DrawingCanvas(400,400);
      drawCanvas.setBorder(new EtchedBorder());
      drawCanvas.setBackground(Color.white);
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
   }


   /** display an image file in the drawing canvas 
    * @param imageFileName  Name of image file to display
    */
   public void displayBoard(String imageFileName)
   {
       BufferedImage image = null;
       try
       {
          image = ImageIO.read(new File(imageFileName));
       }
       catch (Exception e)
       {
          e.printStackTrace();
          return;
       }
       ImageIcon imageIcon = new ImageIcon(image);
       JLabel jLabel = new JLabel();
       jLabel.setIcon(imageIcon);
       drawCanvas.add(jLabel, BorderLayout.CENTER);
       pack();
       setVisible(true);
   }
}




