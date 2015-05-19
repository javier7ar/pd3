import java.rmi.Naming;
import java.rmi.registry.Registry;


public class Cliente2RMI {
	public static void main(String[] args)
	{
		IServidorRMI servidor;
	
		if (args.length != 1){
			System.out.println("Se necesita un parametro: direccion ip del servidor directorio");
			System.exit(1);
		}
		try {
				String rname = "//" + args[0] + ":" + Registry.REGISTRY_PORT + "/remote";
				System.out.println("Solicitando servidor a "+rname);
				IServidorDirectorio remoto = (IServidorDirectorio) Naming.lookup(rname);
				servidor = remoto.solicitarServidor();
				System.out.println("Servidor recibido");
				
				if (servidor != null) {
					int bufferlength = 100;
					byte[] buffer = new byte[bufferlength];							
					servidor.sendThisBack(buffer);
					
					System.out.println("Termino");
				}
				else {
					System.out.println("Se recibio un servidor nulo");
				}
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
}
