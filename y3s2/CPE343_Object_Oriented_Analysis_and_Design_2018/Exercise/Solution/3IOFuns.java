import java.io.*;
/**
 * Class with static terminal I/O functions.
 *
 *  Created by Sally Goldin, 24 August 2017
 */
public class IOFuns
{

    /**
     * Asks for one integer value, and returns it
     * as the function value.
     * @param   prompt    String to print, telling which coordinate
     * @return  the value. Exits with error if user types in
     *          something that can't be read as an integer 
     */
    public static int getOneInteger(String prompt)
       {
       int value = 0;	   
       String inputString;
       int readBytes = 0;
       byte buffer[] = new byte[200]; 
       System.out.println(prompt);
       try
           {
           readBytes = System.in.read(buffer,0,200);
	   }
       catch (IOException ioe)
           {
	   System.out.println("Input/output exception - Exiting");
	   System.exit(1);
           }
       inputString = new String(buffer);
       try 
           {
	   /* modify to work for both Windows and Linux */
	   int pos = inputString.indexOf("\r");
	   if (pos <= 0)
	       pos = inputString.indexOf("\n");
           if (pos > 0)
	      inputString = inputString.substring(0,pos);
           value = Integer.parseInt(inputString);
	   }
       catch (NumberFormatException nfe) 
           {
	   System.out.println("Bad number entered - Exiting");
	   System.exit(1);
           }
       return value;
       }

    /**
     * Asks for a string, and returns it
     * as the function value.
     * @param   prompt    String to print, telling which coordinate
     * @return  the string value entered, without a newline 
     */
    public static String getOneString(String prompt)
       {	   
       String inputString;
       int readBytes = 0;
       byte buffer[] = new byte[200]; 
       System.out.println(prompt);
       try
           {
           readBytes = System.in.read(buffer,0,200);
	   }
       catch (IOException ioe)
           {
	   System.out.println("Input/output exception - Exiting");
	   System.exit(1);
           }
       inputString = new String(buffer);
       /* modify to work for both Windows and Linux */
       int pos = inputString.indexOf("\r");
       if (pos <=0 )
	   pos = inputString.indexOf("\n");
       if (pos > 0)
	   inputString = inputString.substring(0,pos);
       return inputString;
       }
}