import jade.core.Agent;
import jade.core.ContainerID;
import jade.core.Location;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.ContainerController;

import java.io.Serializable;


public class OtroAgente extends Agent implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private ContainerID destino;
	private ContainerID destino2;
	private int cont = 0;
	
	// Ejecutado por única vez en la creación
	public void setup(){
		Location origen = here();
		System.out.println("\n \n Hola, agente con nombre local " + getLocalName());
		System.out.println("Y nombre completo... " + getName());
		System.out.println("Y en location " + origen.getID() + "\n\n"); 
		// Get a hold on JADE runtime 
		Runtime rt = Runtime.instance(); 
		// Create a default profile 
		Profile p = new ProfileImpl(); 
		// Create a new non-main container, connecting to the default 
		// main container (i.e. on this host, port 1099) 
		ContainerController cc;		
		cc = rt.createAgentContainer(p);
		
		// Para migrar el agente
		try {
			destino = new ContainerID("Main-Container", null);
			destino2 = new ContainerID("Container-1", null); 
			
			System.out.println("Migrando el agente a " + destino2.getID()); 
			doMove(destino2);
		} 
		catch (Exception e) {
			System.out.println("\n\n\n No fue posible migrar el agente \n\n\n");
			}				
	}
	
	
	
	protected void afterMove(){
		Location origen = here();
		System.out.println("\n\n Hola, agente migrado con nombre local " + getLocalName());
		System.out.println("Y nombre completo... " + getName());
		System.out.println("Y en location " + origen.getID() + "\n\n");
		
		if (cont == 0){
			cont = cont + 1;
			doMove(destino);
		}
	}
	
	
}
