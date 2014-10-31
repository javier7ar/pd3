import jade.core.Agent;
import jade.core.ContainerID;
import jade.core.Location;
import jade.mtp.TransportAddress;
import jade.wrapper.*;
import jade.core.Runtime; 
import jade.core.Profile; 
import jade.core.ProfileImpl; 


public class AgentePrueba extends Agent {
	// Ejecutado por única vez en la creación
	 public void setup() 
	 { 
	 Location origen = here();
	 System.out.println("\n\nHola, agente con nombre local " + getLocalName());
	 System.out.println("Y nombre completo... " + getName());
	 System.out.println("Y en location " + origen.getID() + "\n\n"); 
	 
	 
	// Get a hold on JADE runtime 
	 Runtime rt = Runtime.instance(); 
	 // Create a default profile 
	 Profile p = new ProfileImpl(); 
	 // Create a new non-main container, connecting to the default 
	 // main container (i.e. on this host, port 1099) 
	 ContainerController cc = rt.createAgentContainer(p); 
	
	 
	 
	 try {
		 		 ContainerID destino = new ContainerID(cc.getContainerName(), null); 
		 		 System.out.println("Migrando el agente a " + destino.getID()); 
		 

		 		 doMove(destino); 
		 } catch (Exception e) {System.out.println("\n\n\nNo fue posible migrar el  agente\n\n\n");} 
		 }
	 
	 @Override
	 protected void afterMove()
	 {
	 Location origen = here();
	 System.out.println("\n\nHola, agente migrado con nombre local " + getLocalName());
	 System.out.println("Y nombre completo... " + getName());
	 System.out.println("Y en location " + origen.getID() + "\n\n"); 
	 }

}
