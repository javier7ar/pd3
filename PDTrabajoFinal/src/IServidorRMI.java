import java.rmi.Remote;
import java.rmi.RemoteException;


public interface IServidorRMI extends Remote {
	
	 public String sendThisBack(String data) throws RemoteException;

}
