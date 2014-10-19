import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;



public class ClienteArchivosRMI {
	
	private static int cantXLote = 100;
	private static String rutaArchivosCliente = "/home/lilauth/";
	
	public static void main(String[] args) {
		
		
		// Controlo los argumentos
	    if (args.length != 1) 
	    { 
	    	System.out.println("1 argument needed: (remote) hostname"); 
	    	System.exit(1); 
	    } 
	    
	    IServidorArchivosRMI servidor;
	 
	    try { 
	    	String rname = "//" + args[0] + ":" + Registry.REGISTRY_PORT + "/remote"; 
	    	
	    	// prueba remota
	    	servidor = (IServidorArchivosRMI) Naming.lookup(rname);
	    	
	    	// prueba local
	    	//ServidorArchivosRMI servidor = new ServidorArchivosRMI();
	    	
	    	// leeo y copio el archivo
	    	// el archivo debe existir
	    	leerArchivo(servidor,"prueba.txt");
 
 
	    } 
	    catch (RemoteException e) { 
	    	System.out.println("Excepcion Remota: " + e);
	    }
	    catch (Exception e) { 
	    	e.printStackTrace(); 
	    }		  
		
		
	}
	
	private static void leerArchivo(IServidorArchivosRMI servidor, String archivo) throws IOException {

		int cantLeida, pos = 0;
		String leyo;
		byte buffer[] = new byte[cantXLote];
		FileOutputStream fout = new FileOutputStream(rutaArchivosCliente+"copiaClie.txt");
		
		while (0 < (cantLeida = servidor.Leer(archivo, pos, cantXLote, buffer))) {
			
			leyo = new String(buffer,0,cantLeida); 
			System.out.println("Leyo: " + leyo);
			
			// Voy guardando mi copia
			fout.write(buffer, 0, cantLeida);
			
			// Voy creando la copia del servidor
			servidor.Escribir("copia "+archivo, cantLeida, buffer);
			
			pos += cantLeida;
			
		}
		
		// Guardo los cambios de mi copia
		fout.flush();
		fout.close();
		
		System.out.println("Termino de leer");
	}
	

}
