import java.util.*;

/**
 *  Class representing a simple email message
 *  Sample solution for CPE 372 Lab 1
 *
 *    Created by Sally E. Goldin, 26 June 2017
 */
public class EmailMessage
{
    /* data items */
    /** Creation date and time, set by the constructor */
    private String created = null;

    /** Who the message is to */
    private String toAddress = null;

    /** Who the message is from */
    private String fromAddress = null;

    /** Subject of the email */
    private String subject= null;

    /** Store body of the email as a list of text lines.
     * Alternatively you could simply create one long string
     * using concatenation.
     */
    private ArrayList<String> bodyText = new ArrayList<String>();

    /** 
     * Constructor calls IOUtils getDateTime() method
     *  to set the create date 
     */
    public EmailMessage()
    {
	created = IOUtils.getDateTime();
    }

    /* Setters and getters for the fields of the message */

    /** Set the value of the toAddress
     * @param input    String to assign
     */
    public void setToAddress(String input)
    {
	toAddress = input;
    }

    /** Set the value of the fromAddress
     * @param input    String to assign
     */
    public void setFromAddress(String input)
    {
	fromAddress = input;
    }

    /** Set the value of the subject
     * @param input    String to assign
     */
    public void setSubject(String input)
    {
	subject = input;
    }

    /** Return the value of the toAddress
     * @return to address
     */
    public String getToAddress()
    {
	return toAddress;
    }


    /** Return the value of the fromAddress
     * @return from address
     */
    public String getFromAddress()
    {
	return fromAddress;
    }


    /** Return the value of the subject line
     * @return subject line
     */
    public String getSubject()
    {
	return subject;
    }

    /**
     * Add a line to the body of the message 
     * @param nextLine   line of text to add
     */
    public void addToBody(String nextLine)
    {
	bodyText.add(nextLine);
    }

    /**
     * Simulate sending the message by printing it all out 
     * on the terminal
     */
    public void send()
    {
	System.out.println("-------------------------------------------------");
	System.out.println("[Message created at " + created + "]");
	System.out.println("-------------------------------------------------");
	System.out.println("TO:\t\t" + toAddress);
	System.out.println("FROM:\t\t" + fromAddress);
	System.out.println("SUBJECT:\t" + subject);
	System.out.println("-  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  - ");
	Iterator it = bodyText.iterator();
	while (it.hasNext())
	{
	    System.out.println((String) it.next());
	}
	System.out.println("-------------------------------------------------");
	System.out.println("[Message sent at " + IOUtils.getDateTime() + "]");
	System.out.println("-------------------------------------------------\n\n");
    }









}