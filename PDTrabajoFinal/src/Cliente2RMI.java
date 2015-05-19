import java.rmi.Naming;
import java.rmi.registry.Registry;


public class Cliente2RMI {
	public static void main(String[] args)
	{
		IServidorRMI servidor;
	
		if (args.length != 2){
			System.out.println("Se necesita 2 parametros: direccion ip del servidor directorio y mensaje a enviar");
			System.exit(1);
		}
		try {
				String rname = "//" + args[0] + ":" + Registry.REGISTRY_PORT + "/remote";
				System.out.println("Solicitando servidor a "+rname);
				IServidorDirectorio remoto = (IServidorDirectorio) Naming.lookup(rname);
				servidor = remoto.solicitarServidor();
				System.out.println("Servidor recibido");
				
				if (servidor != null) {
					//int bufferlength = 100;
					//byte[] buffer = new byte[bufferlength];		
					System.out.println("Enviando mensaje...");
					String mensaje = args[0];
					String respuesta = servidor.sendThisBack(mensaje);
					
					System.out.println("Se recibio:"+respuesta);
				}
				else {
					System.out.println("Se recibio un servidor nulo");
				}
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
}
