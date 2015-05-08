import java.rmi.RemoteException;
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
	public String solicitarServidor() throws RemoteException {
		proximoServidor++;
		String ip = servidores.get(proximoServidor % servidores.size()) ;
		
		System.out.println("Devuelve "+ip);
		
		return ip;
	}

}
