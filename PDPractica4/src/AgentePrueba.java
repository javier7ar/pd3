import jade.core.Agent;
import jade.core.Location;


public class AgentePrueba extends Agent {
	// Ejecutado por �nica vez en la creaci�n
	 public void setup() 
	 { 
	 Location origen = here();
	 System.out.println("\n\nHola, agente con nombre local " + getLocalName());
	 System.out.println("Y nombre completo... " + getName());
	 System.out.println("Y en location " + origen.getID() + "\n\n"); 
	 }
}
