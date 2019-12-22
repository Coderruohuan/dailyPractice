package thread.old;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 
 * @author wwn
 *
 */
public class ReentrancyClass {

	public static void main(String[] args) throws UnknownHostException {
		InetAddress address=InetAddress.getLocalHost();
		System.out.println(address.getHostAddress());
	}
}
