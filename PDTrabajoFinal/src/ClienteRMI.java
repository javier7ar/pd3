import java.rmi.Naming;
import java.rmi.registry.Registry;


public class ClienteRMI {
	public static void main(String[] args)
	{
		String servidor;
	/* Look for hostname and msg length in the command line */
		if (args.length != 1){
			System.out.println("1 argument needed: (remote) hostname");
			System.exit(1);
		}
		try {
				String rname = "//" + args[0] + ":" + Registry.REGISTRY_PORT + "/remote";
				System.out.println("Conectando a "+rname);
				IServidorDirectorio remoto = (IServidorDirectorio) Naming.lookup(rname);
				servidor = remoto.solicitarServidor();
				System.out.println("Conectado");
				
				String sf= "//" + servidor + ":" + Registry.REGISTRY_PORT + "/remote";
				System.out.println("Conectando a "+sf);
				IServidorRMI servidorFinal = (IServidorRMI) Naming.lookup(sf);
				System.out.println("Conectado");
				
				int bufferlength = 100;
				byte[] buffer = new byte[bufferlength];							
				servidorFinal.sendThisBack(buffer);
				
				System.out.println("Done");
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
}
