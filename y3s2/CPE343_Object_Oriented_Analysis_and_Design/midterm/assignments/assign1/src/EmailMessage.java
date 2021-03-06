import java.util.ArrayList;

/**
 *  
 * EmailMessage
 *
 *   This class represents a single message, either created or
 *   received, in an email client application.
 *
 *   Created by Natthawat Tungruethaipak (Tong) ID 60070503426
 *     16 Jan 2020
 *
 *
 */  
public class EmailMessage 
{
	private String toAddress;
	private String fromAddress;
	private String subject;
	private String body = "";
	private String dateTime;
	
	EmailMessage()
	{
		this.dateTime = IOUtils.getDateTime();
	}
	
	/**
	 * This method set destination of the email
	 * @param toAddress receive destination the email
	 */
	public void setToAddress(String toAddress)
	{
		this.toAddress = toAddress;
	}
	
	/**
	 * This method set source of the email
	 * @param fromAddress receive source the email
	 */
	public void setFromAddress(String fromAddress)
	{
		this.fromAddress = fromAddress;
	}
	
	/**
	 * This method set subject of the email
	 * @param subject receive subject of the email
	 */
	public void setSubject(String subject)
	{
		this.subject = subject;
	}
	
	/**
	 * This method set body of the email by concat the string
	 * @param body receive body of the email
	 */
	public void addToBody(String body)
	{
		this.body += body;
	}
	
	/**
	 * This method print out the email
	 */
	public void send()
	{
		System.out.println("-------------------------------------------------");
		System.out.println("[Message created at "+dateTime+"]");
		System.out.println("-------------------------------------------------");
		System.out.println("TO:\t" + toAddress);
		System.out.println("FROM:\t" + fromAddress);
		System.out.println("SUBJECT:\t" + subject);
		System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - -");
		System.out.println(body);
		System.out.println("-------------------------------------------------");
		System.out.println("[Message sent at "+IOUtils.getDateTime()+"]");
		System.out.println("-------------------------------------------------");
	}
	
	/**
	 * This method return destination of the email
	 * @return destination of the email
	 */
	public String getToAddress()
	{
		return toAddress;
	}
	
	/**
	 * This method return source of the email
	 * @return source of the email
	 */
	public String getFromAddress()
	{
		return fromAddress;
	}
	
	/**
	 * This method return subject of the email
	 * @return subject of the email
	 */
	public String getSubject()
	{
		return subject;
	}
	
	/**
	 * This method return body of the email
	 * @return body of the email
	 */
	public String getBody()
	{
		return body;
	}
}
