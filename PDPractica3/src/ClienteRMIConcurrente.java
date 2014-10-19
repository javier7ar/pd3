

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;

public class ClienteRMIConcurrente {

	public static void main(String[] args) {
		// Controlo los argumentos
	    if (args.length != 2) 
	    { 
	    	System.out.println("Se necesita 2 argumentos (servidor y mensaje)"); 
	    	System.exit(1); 
	    } 
	    
	    
	    IServidorRMIConcurrente servidor;
	    
	    try { 
	    	String rname = "//" + args[0] + ":" + Registry.REGISTRY_PORT + "/remote"; 
	    	
	    	// prueba remota
	    	servidor = (IServidorRMIConcurrente) Naming.lookup(rname);
	    	
	    	// prueba local
	    	//ServidorArchivosRMI servidor = new ServidorArchivosRMI();
	    	
	    	servidor.echo(args[1]);
 
 
	    } 
	    catch (RemoteException e) { 
	    	System.out.println("Excepcion Remota: " + e);
	    }
	    catch (Exception e) { 
	    	e.printStackTrace(); 
	    }
	    

	}

}
