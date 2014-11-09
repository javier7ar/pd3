import java.util.ArrayList;
import java.util.List;
import jade.core.Agent;
import jade.core.ContainerID;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.ContainerController;
import jade.wrapper.ControllerException;


/*
 * OPCION 2: 
 * Guardandome los ContainerID de los containers (Location) y tratando de mover directamente ahi 
 * 
 */
public class AgenteEjer1OP2 extends Agent {
	private static final long serialVersionUID = 1L;
	private int CANT_CONTAINERS = 5;
	private int proximo_agente = 0;
	private List<ContainerID> listaContainers = new ArrayList<ContainerID>(); 
	
	
	public void setup() { 
		System.out.println("Arranca el agente " + getLocalName());
		
		// Creo los containers
		try {
			crearContainers();
			System.out.println("Containers creados");
		} catch (ControllerException e) {
			System.out.println("Error al crear los containers");
			e.printStackTrace();
		}
		
		// Muevo al primero
		try {
			ContainerID destino = getNextContainer();
			System.out.println("Moviendo a "+destino.getName());
			doMove(destino);
		} catch (Exception e) {
			System.out.println("Error al mover el agente al inicio");
		}
	}
	
	protected void afterMove() {
		// Llego
		System.out.println("El agente " + (getAID()).getName() + " ha llegado a "+here().getName());
		
		// Muestro la info del sistema
		//mostrarInfoSistema();
		System.out.println("\n");
		
		// muevo al proximo
		try {
			ContainerID destino = getNextContainer();
			// si no llegue el final, muevo
			if (destino != null) {
				System.out.println("Moviendo otra vez, ahora a "+destino.getName());
				doMove(destino);
			}
			else {
				System.out.println("Fin del recorrido!");
			}
		} 
		catch (Exception e) {
			System.out.println("Error al mover el agente");
			e.printStackTrace();
		}
		
	}
	
	
	private void crearContainers() throws ControllerException {
		
		Runtime rt = Runtime.instance(); 
		Profile p = new ProfileImpl(); 
		ContainerController cc;

		for (int i =0; i < CANT_CONTAINERS; i++) {
			// Creo el container
			cc = rt.createAgentContainer(p);
			// me guardo el nombre para buscarlo mas tarde
			listaContainers.add(new ContainerID(cc.getContainerName(), null));
		}
		
	}
	
	
	private ContainerID getNextContainer() {
		
		ContainerID next = null;
		
		if (proximo_agente < CANT_CONTAINERS)  {
			
			next = listaContainers.get(proximo_agente);	
			proximo_agente++;			
		}
		else if (proximo_agente == CANT_CONTAINERS) {
			next = new ContainerID("Main-Container", null);
			proximo_agente++;
		}
		
		return next;
	}
}
