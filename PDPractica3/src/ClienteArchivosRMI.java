import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.util.Arrays;


public class ClienteArchivosRMI {
	
	private static int cantXLote = 10;
	
	public static void main(String[] args) {
		
		
		/* Look for hostname and msg length in the command line */ 
	    if (args.length != 1) 
	    { 
	    	System.out.println("1 argument needed: (remote) hostname"); 
	    	System.exit(1); 
	    } 
	    
	    IServidorArchivosRMI servidor;
	 
	    try { 
	    	String rname = "//" + args[0] + ":" + Registry.REGISTRY_PORT + "/remote"; 
 
	    	servidor = (IServidorArchivosRMI) Naming.lookup(rname);
	    	//ServidorArchivosRMI servidor = new ServidorArchivosRMI();
	    	
	    	leerArchivo(servidor,"C:\\prueba.txt");
 
 
	    } catch (Exception e) { 
	    	e.printStackTrace(); 
	    }		  
		
		
	}
	
	private static void leerArchivo(IServidorArchivosRMI servidor, String archivo) throws IOException {

		int cantLeida, pos = 0;
		String leyo;
		byte buffer[] = new byte[cantXLote];
		FileOutputStream fout = new FileOutputStream("C:\\copia.txt");
		
		while (0 < (cantLeida = servidor.Leer(archivo, pos, cantXLote, buffer))) {
			leyo = new String(buffer,0,cantLeida); 
			System.out.println("Leyo: " + leyo);
			
			fout.write(buffer, 0, cantLeida);
			
			pos += cantLeida;
			
		}
		
		fout.close();
		
		System.out.println("Termino de leer");
	}

}
