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
 * OPCION 1: 
 * Guardandome los nombres de los containers (strings) y tratando de recuperar el container con new ContainerID("nombre",null) 
 * 
 */
public class AgenteEjer1OP1 extends Agent {

	private static final long serialVersionUID = 1L;
	private int CANT_CONTAINERS = 5;
	private int proximo_agente = 0;
	private List<String> listaContainers = new ArrayList<String>(); 
	
	
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
		String container = getNextContainer();
		try {
			ContainerID destino = new ContainerID(container, null);
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
			String container = getNextContainer();
			// si no llegue el final, muevo
			if (container != null) {
				ContainerID destino = new ContainerID(container, null);
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
			listaContainers.add(cc.getContainerName());
		}
		
	}
	
	
	private String getNextContainer() {
		
		String next = null;
		
		if (proximo_agente < CANT_CONTAINERS)  {
			
			next = listaContainers.get(proximo_agente);	
			proximo_agente++;			
		}
		else if (proximo_agente == CANT_CONTAINERS) {
			next = "Main-Container";
			proximo_agente++;
		}
		
		return next;
	}
}
