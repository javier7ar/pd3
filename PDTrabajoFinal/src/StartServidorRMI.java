import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.registry.Registry;


public class StartServidorRMI {

	public static void main(String[] args) {
		
		if (args.length != 1) 
	    { 
	    	System.out.println("Se necesita un parametro: direccion ip del servidor directorio"); 
	    	System.exit(1); 
	    } 

		try{
			ServidorRMI robject = new ServidorRMI();
						
			String rname = "//localhost:" + Registry.REGISTRY_PORT + "/remote";
			
			Naming.rebind(rname, robject);			
			
			System.out.println("Servidor RMI registrado");			
			
			
		} catch (Exception e) {
			System.out.println("Error en Naming.rebind");
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		try{
					
			String rnameDirectorio = "//" + args[0] + ":" + Registry.REGISTRY_PORT + "/remote"; 			
			System.out.println("rnameDirectorio: "+rnameDirectorio);
	    	
	    	// prueba remota
			IServidorDirectorio servidorDirectorio = (IServidorDirectorio) Naming.lookup(rnameDirectorio);
			System.out.println("lookup hecho");
			
			
			// Registro la IP
			//InetAddress address = InetAddress.getLocalHost();
			String ipLocal = StartServidorRMI.getIP();
			
			System.out.println("Envio IP a registrar "+ipLocal);
			servidorDirectorio.registrarServidor(ipLocal);
			
			System.out.println("Servidor registrado en el directorio");
		
		} catch (Exception e) {
			System.out.println("Error al registrar en directorio");
			e.printStackTrace();
			System.out.println(e.getMessage());
		}


	}
	
	private static String getIP() throws UnknownHostException {
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
		System.out.println("Envio IP a registrar "+sIPAddress);
		
		return sIPAddress;
	}


}
