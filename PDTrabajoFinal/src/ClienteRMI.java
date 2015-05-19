import java.rmi.Naming;
import java.rmi.registry.Registry;


public class ClienteRMI {
	public static void main(String[] args)
	{
		String servidor;
	
		if (args.length != 2){
			System.out.println("Se necesita 2 parametros: direccion ip del servidor directorio y mensaje a enviar");
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
				
				//int bufferlength = 100;
				//byte[] buffer = new byte[bufferlength];
				System.out.println("Enviando mensaje...");
				String mensaje = args[0];
				String respuesta = servidorFinal.sendThisBack(mensaje);
				System.out.println("Se recibio:"+respuesta);
				
				System.out.println("Termino");
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
}
