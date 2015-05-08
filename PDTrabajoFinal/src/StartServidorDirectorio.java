import java.rmi.Naming;
import java.rmi.registry.Registry;


public class StartServidorDirectorio {
	public static void main(String[] args) {

		try{
			ServidorDirectorio robject = new ServidorDirectorio();
			
			
			String rname = "//localhost:" + Registry.REGISTRY_PORT + "/remote";
			
			Naming.rebind(rname, robject);
			
			
			System.out.println("Servidor registrado");
			
		} catch (Exception e) {
			System.out.println("Error en Naming.rebind");
			e.printStackTrace();
			System.out.println(e.getMessage());
		}


	}
}
