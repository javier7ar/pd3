import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;


public class ServidorDirectorio extends UnicastRemoteObject implements IServidorDirectorio {

	private static final long serialVersionUID = 1L;
	private List<String> servidores = new ArrayList<String>();
	private int proximoServidor = -1;

	protected ServidorDirectorio() throws RemoteException {
		super();
	}

	@Override
	public boolean registrarServidor(String ip) throws RemoteException {
		
		// Usado por los ServidorRMI para registrarse al directorio
		
		// Agrego el servidor (la IP) a la lista
		servidores.add(ip);
		System.out.println("Registra IP "+ip);
		
		return true;
	}

	@Override
	public String solicitarIPServidor() throws RemoteException {
		
		// Obtengo la IP del siguiente servidor
		String ip = ipProxServidor();
		
		System.out.println("Devuelve "+ip);
		
		return ip;
	}

	@Override
	public IServidorRMI solicitarServidor() throws RemoteException {
		
		// Obtengo la IP del siguiente servidor
		String ip = ipProxServidor();
		
		// Servidor a devolver, si no hay servidores registrados se devuelve null
		IServidorRMI servidor = null;
		
		if (ip != "") {	
			String sf= "//" + ip + ":" + Registry.REGISTRY_PORT + "/remote";
			System.out.println("Conectando a "+sf);
			
			try {
				servidor = (IServidorRMI) Naming.lookup(sf);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			System.out.println("Devuelve Servidor de ip: "+ip);
		} 
		else {
			System.out.println("No hay servidores registrados para devolver");
		}
		
		return servidor;
	}
	
	private String ipProxServidor(){	
		
		// Devuelve el proximo ServidorRMI a usar
		// Funciona como una lista circular FIFO, para tratar de balancear la carga de los Servidores
		
		// IP a devolver, si no hay servidor registrado se devuelve ""
		String ip = "";
		
		// Obtengo el proximo
		proximoServidor++;
		if (servidores.size() > 0) {
			ip = servidores.get(proximoServidor % servidores.size()) ;
		}
		
		return ip;
	}

}
