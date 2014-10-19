package ejer5;

import java.rmi.Remote;

public interface IServidorRMIConcurrente extends Remote {
	public String echo(String mensaje);
}
