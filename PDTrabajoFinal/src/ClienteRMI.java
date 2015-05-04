import java.rmi.Naming;
import java.rmi.registry.Registry;


public class ClienteRMI {
	public static void main(String[] args)
	{
	/* Look for hostname and msg length in the command line */
		if (args.length != 1){
			System.out.println("1 argument needed: (remote) hostname");
			System.exit(1);
		}
		try {
				String rname = "//" + args[0] + ":" + Registry.REGISTRY_PORT + "/remote";
				IServidorRMI remote = (IServidorRMI) Naming.lookup(rname);
				int bufferlength = 100;
				byte[] buffer = new byte[bufferlength];							
				remote.sendThisBack(buffer);
				System.out.println("Done");
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
}
