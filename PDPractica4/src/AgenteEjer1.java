import java.lang.management.ManagementFactory;
import java.util.Date;
import jade.core.*;
import jade.core.Runtime;
import jade.wrapper.ContainerController;
import jade.wrapper.ControllerException;

public class AgenteEjer1 extends Agent {

	private static final long serialVersionUID = 1L;
	private int CANT_CONTAINERS = 5;
	private int proximo_agente = 0;
	private Date fechaInicio;
	private Date fechaTermino;
	
	
	public void setup() { 
		// Tomo la fecha de inicio
		fechaInicio = new Date ();
		
		// muevo al primer container
		try {
			Location destino = getNextContainer();
			doMove(destino);
		} 
		catch (ControllerException e) {
			System.out.println("Error al mover el agente al inicio");
			e.printStackTrace();
		}
	}
	
	@Override
	protected void afterMove() {
		// Llego
		System.out.println("El agente " + (getAID()).getName() + " ha llegado a "+here().getName());
		
		// Muestro la info del sistema
		mostrarInfoSistema();
		System.out.println("\n");
		
		// muevo al proximo
		try {
			Location destino = getNextContainer();
			// si no llegue el final, muevo
			if (destino != null) {
				doMove(destino);
			}
			else {
				fechaTermino = new Date ();
				long tiempoTotal = fechaTermino.getTime() - fechaInicio.getTime();
				System.out.println("Fin del recorrido!");
				System.out.println("Timpo del recorrido: "+String.valueOf(tiempoTotal)+" ms.");
			}
		} 
		catch (ControllerException e) {
			System.out.println("Error al mover el agente");
			e.printStackTrace();
		}
		
	}
	
	private Location getNextContainer() throws ControllerException{
		
		Location next = null;
		
		if (proximo_agente < CANT_CONTAINERS)  {
			Runtime rt = Runtime.instance(); 
			Profile p = new ProfileImpl();
			ContainerController cc = rt.createAgentContainer(p);	
			
			next = new ContainerID(cc.getContainerName(), null);
			
			proximo_agente++;			
		}
		else if (proximo_agente == CANT_CONTAINERS) {
			next = new ContainerID("Main-Container", null);
			proximo_agente++;
		}
		
		return next;
	}
	
	private void mostrarInfoSistema() {
		java.lang.management.OperatingSystemMXBean os = ManagementFactory.getOperatingSystemMXBean();

		com.sun.management.OperatingSystemMXBean osDetail = null;

		if ( os instanceof com.sun.management.OperatingSystemMXBean ) {
			osDetail = (com.sun.management.OperatingSystemMXBean)os;
				
			System.out.println("Memoria Libre: "+osDetail.getFreePhysicalMemorySize() / (1024 * 1024)+" Mb.");
			System.out.println("Carga de Procesamiento: "+osDetail.getSystemCpuLoad()+" %");
		}
		else {
			System.out.println("Informacion del sistema no disponible!");
		}
			
	}

}
