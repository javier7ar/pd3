package ejer5;

import java.rmi.Naming;
import java.rmi.registry.Registry;

public class StartServidorRMIConcurrente {
	public static void main(String[] args) {
		try {
			ServidorRMIConcurrente servidor = new ServidorRMIConcurrente();
			String rname = "//localhost:" + Registry.REGISTRY_PORT  + "/remote"; 
		    Naming.rebind(rname, servidor);
		    System.out.println("Servidor Registrado");
		    
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
