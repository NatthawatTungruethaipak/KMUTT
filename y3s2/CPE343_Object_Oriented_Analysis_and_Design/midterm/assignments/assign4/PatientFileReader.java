/*  PatientFileReader.java
 *
 *  Reads records from a patient file and returns instances of
 *  the ERPatient class
 *
 *  Created by Sally Goldin, 21 March 2012
 */

import java.io.*;

/**
 * Class extends TextFileReader to return ERPatients instead of
 * Strings.
 */
public class PatientFileReader extends TextFileReader
{
    
    /* Note we do not need to override the open function */
   
    /**
     * Read the next line and parse it to get data for a patient
     * @return  Patient object or null if an error occurred (including
     *          a line that doesn't match the expected format
     */
    public ERPatient getNextPatient()
       {
       ERPatient patient = null;
       String lineRead = getNextLine(); /* try to read the next line */
       if (lineRead != null)
           {
	   String fields[] = lineRead.split(" ");
           if (fields.length == 3)  /* good format */
               {
               try
                   {
		   int severity = Integer.parseInt(fields[2]);
                   patient = new ERPatient();
                   patient.name = fields[0];
                   patient.problem = fields[1];
                   patient.severity = severity;
                   }
               catch (NumberFormatException nfe)
                   {
		       /* If we end up here, this means the
                          severity field was not an integer.
                          So we should return null for the
                          function result.
		       */ 
                   }
	       } /* end if the right number of fields */
	   }     /* end if we got a line from the file */
       return patient;  /* will be null if any error occurred */
       }   

    /**
     * Main - exists just for testing 
     */
    public static void main(String args[])
       {
       if (args.length < 1)
          {
	  System.out.println("You must specify a patient data file as a command line argument");
	  System.exit(0);
          }
       PatientFileReader pReader = new PatientFileReader();
       boolean bOk = pReader.open(args[0]);
       if (bOk)
          {
	  ERPatient patient = null;
	  while ((patient = pReader.getNextPatient()) != null)
	     {
	     patient.printDebug();
	     System.out.println("\n");
	     }
	  pReader.close();
          }
       else
          {
	  System.out.println("Error opening file \"" + args[0] + "\"");
	  System.exit(1);
          }
       }

}
