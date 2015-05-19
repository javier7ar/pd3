import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class ServidorRMI extends UnicastRemoteObject implements IServidorRMI {

	private static final long serialVersionUID = 1L;

	protected ServidorRMI() throws RemoteException {
		super();
	}

	@Override
	public String sendThisBack(String data) throws RemoteException {
		System.out.println("LLego: "+data);
		String ip;
		try {
			ip = IPManager.getIP();
		} catch (UnknownHostException e) {
			ip = "[no se puede obtener la IP]";
		}
		
		String respuesta = data + "desde servidor "+ ip;
		System.out.println("Se devuelve al Cliente: "+respuesta);
		return respuesta;
	}

}
