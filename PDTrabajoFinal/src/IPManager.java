import java.net.InetAddress;
import java.net.UnknownHostException;


public class IPManager {
	public static String getIP() throws UnknownHostException {
		InetAddress address = InetAddress.getLocalHost();
		 // Cogemos la IP 
		byte[] bIPAddress = address.getAddress();
		
		// IP en formato String
		String sIPAddress = "";
		 
		for (int x=0; x<bIPAddress.length; x++) {
		  if (x > 0) {
		    // A todos los numeros les anteponemos
		    // un punto menos al primero    
		    sIPAddress += ".";
		  }
		  // Jugamos con los bytes y cambiamos el bit del signo
		  sIPAddress += bIPAddress[x] & 255;	   
		}
				
		return sIPAddress;
	}
}
