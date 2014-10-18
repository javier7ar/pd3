import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;


public class ClienteArchivosRMI {

	public static void main(String[] args) {
		
		int cant = 100000;
		byte buffer[] = new byte[cant];
		
		/* Look for hostname and msg length in the command line */ 
	    if (args.length != 1) 
	    { 
	    	System.out.println("1 argument needed: (remote) hostname"); 
	    	System.exit(1); 
	    } 
	 
	    try { 
	    	String rname = "//" + args[0] + ":" + Registry.REGISTRY_PORT + "/remote"; 
 
	    	IServidorArchivosRMI servidor = (IServidorArchivosRMI) Naming.lookup(rname); 
	    	
	    	//ServidorArchivosRMI servidor = new ServidorArchivosRMI();
			servidor.Leer("C:\\prueba.txt", 0, cant, buffer);
			System.out.println("Leyo: " +buffer);
			System.out.println("Termino cliente");
 
 
	    } catch (Exception e) { 
	    	e.printStackTrace(); 
	    }		
		
		
	}

}
