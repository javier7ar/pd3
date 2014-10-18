import java.rmi.Naming;
import java.rmi.registry.Registry;
/**/
public class StartServidorArchivosRMI {

	public static void main(String[] args) {
		try {
			ServidorArchivosRMI servidor = new ServidorArchivosRMI();
			String rname = "//localhost:" + Registry.REGISTRY_PORT  + "/remote"; 
		    Naming.rebind(rname, servidor);
		    System.out.println("Servidor Registrado");
		    
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
