import java.rmi.Naming;
import java.rmi.registry.Registry;


public class ClienteRMI {
	public static void main(String[] args)
	{
		String servidor;
	
		if (args.length != 1){
			System.out.println("Se necesita un parametro: direccion ip del servidor directorio");
			System.exit(1);
		}
		try {
				String rname = "//" + args[0] + ":" + Registry.REGISTRY_PORT + "/remote";
				System.out.println("Conectando a "+rname);
				IServidorDirectorio remoto = (IServidorDirectorio) Naming.lookup(rname);
				servidor = remoto.solicitarIPServidor();
				System.out.println("Conectado");
				
				String sf= "//" + servidor + ":" + Registry.REGISTRY_PORT + "/remote";
				System.out.println("Conectando a "+sf);
				IServidorRMI servidorFinal = (IServidorRMI) Naming.lookup(sf);
				System.out.println("Conectado");
				
				int bufferlength = 100;
				byte[] buffer = new byte[bufferlength];							
				servidorFinal.sendThisBack(buffer);
				
				System.out.println("Termino");
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
}
