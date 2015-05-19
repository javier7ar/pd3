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
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean registrarServidor(String ip) throws RemoteException {
		System.out.println("Registra IP "+ip);
		servidores.add(ip);
		return true;
	}

	@Override
	public String solicitarIPServidor() throws RemoteException {

		String ip = ipProxServidor();
		
		System.out.println("Devuelve "+ip);
		
		return ip;
	}

	@Override
	public IServidorRMI solicitarServidor() throws RemoteException {
		String ip = ipProxServidor();
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
		proximoServidor++;
		String ip = "";
		if (servidores.size() > 0) {
			ip = servidores.get(proximoServidor % servidores.size()) ;
		}
		
		return ip;
	}

}
