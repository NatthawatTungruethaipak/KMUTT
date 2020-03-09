import java.net.InetAddress;

class IPAddressExample
{
	public static void main(String args[]) throws Exception
	{
		InetAddress inetAddress = InetAddress.getLocalHost();
		System.out.println("IP Address:- " + inetAddress.getHostAddress());
		System.out.println("Host Name:- " + inetAddress.getHostName());
//		Enumeration e = NetworkInterface.getNetworkInterfaces();
//		while (e.hasMoreElements())
//		{
//			NetworkInterface n = (NetworkInterface) e.nextElement();
//			System.out.println(n);
//			Enumeration ee = n.getInetAddresses();
//			while (ee.hasMoreElements())
//			{
//				InetAddress i = (InetAddress) ee.nextElement();
//				System.out.println(i.getHostAddress());
//			}
//		}
	}
}