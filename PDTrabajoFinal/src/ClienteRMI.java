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
				// Hago un loookup del ServidorDirectorio 
				String rname = "//" + args[0] + ":" + Registry.REGISTRY_PORT + "/remote";
				System.out.println("Conectando a "+rname);
				IServidorDirectorio remoto = (IServidorDirectorio) Naming.lookup(rname);
				// Solicito la IP del ServidorRMI
				servidor = remoto.solicitarIPServidor();
				System.out.println("Conectado");
				
				// Hago otro loookup para el ServidorRMI 
				String sf= "//" + servidor + ":" + Registry.REGISTRY_PORT + "/remote";
				System.out.println("Conectando a "+sf);
				IServidorRMI servidorFinal = (IServidorRMI) Naming.lookup(sf);
				System.out.println("Conectado");
				
				// uso el ServiodrRMI recibido
				System.out.println("Enviando mensaje...");
				String mensaje = args[1];
				String respuesta = servidorFinal.sendThisBack(mensaje);
				System.out.println("Se recibio:"+respuesta);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
}
