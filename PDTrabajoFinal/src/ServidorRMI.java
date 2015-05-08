import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class ServidorRMI extends UnicastRemoteObject implements IServidorRMI {

	private static final long serialVersionUID = 1L;

	protected ServidorRMI() throws RemoteException {
		super();
	}

	@Override
	public byte[] sendThisBack(byte[] data) throws RemoteException {
		System.out.println("LLego y se devuelve al Cliente");
		return data;
	}

}
