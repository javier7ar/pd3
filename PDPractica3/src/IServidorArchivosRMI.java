import java.rmi.Remote;
import java.rmi.RemoteException;


public interface IServidorArchivosRMI extends Remote {
	public int Leer(String nombreArchivo, int posicion, int cantidad, byte[] buffer) throws RemoteException;
	public int Escribir(String nombreArchivo, int cantidad, byte[] buffer) throws RemoteException;
}
