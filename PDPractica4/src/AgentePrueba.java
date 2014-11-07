import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jade.core.Agent;
import jade.core.ContainerID;
import jade.core.Location;
import jade.core.ProfileException;
import jade.wrapper.*;
import jade.core.Runtime; 
import jade.core.Profile; 
import jade.core.ProfileImpl;

import java.io.Serializable;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;


public class AgentePrueba extends Agent implements Serializable {

	private static final long serialVersionUID = 1L;
	private int CANT_CONTAINERS = 5;
	private List<Object> listaContainers = new ArrayList<Object>();
	private int proximo_agente = 0;
	private Date fechaInicio;
	private Date fechaTermino;
		
	
	// Ejecutado por única vez en la creación
	public void setup() { 
		Location origen = here();
		System.out.println("Arranca el agente " + getLocalName());
		System.out.println("En location " + origen.getID() + "\n\n"); 
		
		crearContainers();
		
		//ContainerController cc = getNexAgent();
		Location destino = getNexAgent();
		
		// Tomo la fecha de inicio
		fechaInicio = new Date ();
		
		try {
			//ContainerID destino = new ContainerID(cc.getContainerName(), null);
			//ContainerID destino = new ContainerID("Container-1", null);
			//System.out.println("Migrar a " +container);
			//ContainerID destino = new ContainerID(container, null);
			System.out.println("Migrando el agente a " + destino.getID()); 
		 
			doMove(destino); 
		} 
		catch (Exception e) {
			System.out.println("\n\n\nNo fue posible migrar el  agente\n\n\n");
		} 
	}
	 
	@Override
	protected void afterMove() {
		Location origen = here();
		System.out.println("Agente llego a" + origen.getID());
		System.out.println("Y nombre completo... " + getName());
		
		mostrarInfoSistema() ;
		System.out.println("\n\n"); 
		
		//ContainerController cc = getNexAgent();
		Location destino = getNexAgent();
		
		if (destino == null) {
			fechaTermino = new Date ();
			System.out.println("Fin del recorrido ! ");
			System.out.println("Tiempo: " + fechaTermino.compareTo(fechaInicio));
			
		}
		
		System.out.println("\n\n"); 
		
		/*
		// MOVER AL PROXIMO
		if (destino != null) {
			try {
				//System.out.println("Migrar a " +container);
				//ContainerID destino = new ContainerID(cc.getContainerName(), null); 
				//ContainerID destino = new ContainerID(container, null);
				System.out.println("Migrando el agente a " + destino.getID()); 
			 
				doMove(destino); 
			} 
			catch (Exception e) {
				System.out.println("\n\n\nNo fue posible migrar el  agente\n\n\n");
			} 
		}*/
	}
	 
	private void crearContainers() {
		
		// Get a hold on JADE runtime 
		Runtime rt = Runtime.instance(); 
		// Create a default profile 
		Profile p = new ProfileImpl(); 
		// Create a new non-main container, connecting to the default 
		// main container (i.e. on this host, port 1099) 
		ContainerController cc;
		ContainerID destino;
		for (int i =0; i < CANT_CONTAINERS; i++) {
			cc = rt.createAgentContainer(p);
			try {
				destino = new ContainerID(cc.getContainerName(), null);
				//listaContainers.add(cc.getContainerName());
				listaContainers.add((Object)destino);
			} catch (ControllerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	private Location getNexAgent(){
		
		Location next = null;
		
		if (proximo_agente < CANT_CONTAINERS)  {
			//next = "Container-"+String.valueOf(proximo_agente+1);
			next = (Location)listaContainers.get(proximo_agente);
			proximo_agente++;			
			
		}
		/*
		proximo_agente++;
		if (proximo_agente < CANT_CONTAINERS) { 
		Runtime rt = Runtime.instance(); 
		// Create a default profile 
		Profile p = new ProfileImpl();
		ContainerController cc=rt.createAgentContainer(p);
	
		try {
			next = cc.getContainerName();
		} catch (ControllerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		*/
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
