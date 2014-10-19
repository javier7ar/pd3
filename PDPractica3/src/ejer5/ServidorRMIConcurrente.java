package ejer5;

import java.io.Serializable;

public class ServidorRMIConcurrente implements IServidorRMIConcurrente, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public String echo(String mensaje) {
		
		System.out.println("Entra: "+ mensaje);
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Termina: "+ mensaje);
		
		return mensaje;
	}

}
