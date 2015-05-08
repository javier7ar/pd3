import java.rmi.Remote;
import java.rmi.RemoteException;


public interface IServidorDirectorio extends Remote {
	
	public boolean registrarServidor(String ip) throws RemoteException;
	public String solicitarServidor() throws RemoteException;

}
