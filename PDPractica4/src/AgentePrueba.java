import java.util.ArrayList;
import java.util.Date;
import java.util.List;
 



import jade.lang.acl.*;
import jade.content.*;
import jade.content.onto.basic.*;
import jade.content.lang.*;
import jade.content.lang.sl.*;
import jade.core.behaviours.*;
import jade.domain.*;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.ControllerException;
import jade.core.*;
import jade.domain.JADEAgentManagement.*;
import jade.domain.mobility.*;

import java.io.Serializable;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;


public class AgentePrueba extends Agent implements Serializable {

	private static final long serialVersionUID = 1L;
	private int CANT_CONTAINERS = 5;
	private Location origen = null;
	private Location destino= null;
	private List<Location> listaContainers = new ArrayList<Location>();
	private int proximo_agente = 0;
	private Date fechaInicio;
	private Date fechaTermino;
		
	
	// Ejecutado por única vez en la creación
	public void setup() { 
		System.out.println("Arranca el agente " + getLocalName());
		//System.out.println("En location " + origen.getID() + "\n\n");
		
		getContentManager().registerLanguage(new SLCodec());
	    getContentManager().registerOntology(MobilityOntology.getInstance());
	    
		origen = here();
		
		crearContainers();
		actualizarContainers();
		System.out.println("Containers actualizados ");
		
		addBehaviour(new MoverAgenteBehaviour(this));
		
		//ContainerController cc = getNexAgent();
		//Location destino = getNexAgent();
		
		// Tomo la fecha de inicio
		fechaInicio = new Date ();
		
		try {
			//ContainerID destino = new ContainerID(cc.getContainerName(), null);
			//ContainerID destino = new ContainerID("Container-1", null);
			//System.out.println("Migrar a " +container);
			//ContainerID destino = new ContainerID(container, null);
			System.out.println("Migrando el agente a "); 
		 
			//doMove(destino); 
		} 
		catch (Exception e) {
			System.out.println("\n\n\nNo fue posible migrar el  agente\n\n\n");
			e.printStackTrace();
		} 
	}
	 
	@Override
	protected void afterMove() {
		System.out.println("El agente " + (getAID()).getName() + " ha llegado");
		/*
		Location origen = here();
		System.out.println("Agente llego a" + origen.getID());
		System.out.println("Y nombre completo... " + getName());
		
		mostrarInfoSistema() ;
		System.out.println("\n\n"); 
		*/
		//ContainerController cc = getNexAgent();
		//Location destino = getNexAgent();
		/*
		if (destino == null) {
			fechaTermino = new Date ();
			System.out.println("Fin del recorrido ! ");
			System.out.println("Tiempo: " + fechaTermino.compareTo(fechaInicio));
			
		}
		*/
		//System.out.println("\n\n"); 
		
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
			/*try {
				destino = new ContainerID(cc.getContainerName(), null);
				//listaContainers.add(cc.getContainerName());
				listaContainers.add(destino);
			} catch (ControllerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
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
	
	private void actualizarContainers(){
		origen = here();
		listaContainers.clear();
		ACLMessage request= new ACLMessage(ACLMessage.REQUEST);
		request.setLanguage(new SLCodec().getName());
		// Establecemos que MobilityOntology sea la ontologia de este mensaje.
		request.setOntology(MobilityOntology.getInstance().getName());
		// Le solicitamos al AMS una lista de los containers disponibles
		Action action= new Action(getAMS(), new QueryPlatformLocationsAction());
		try {
			getContentManager().fillContent(request, action);
			request.addReceiver(action.getActor());
			send(request);
		 
			// Filtramos los mensajes INFORM que nos llegan desde el AMS
			MessageTemplate mt= MessageTemplate.and(MessageTemplate.MatchSender(getAMS()), MessageTemplate.MatchPerformative(ACLMessage.INFORM));
		 
			ACLMessage resp= blockingReceive(mt);
			ContentElement ce= getContentManager().extractContent(resp);
			Result result=(Result) ce;
			jade.util.leap.Iterator it= result.getItems().iterator();
			// Almacena en un ArrayList "Locations" de los "Containers" a los que puede moverse el agente movil.
			while(it.hasNext()) {
				Location loc=(Location) it.next();
				listaContainers.add(loc);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
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
	
	protected void verContainers(){
	    //ACTUALIZAR
	    actualizarContainers();
	    //VISUALIZAR
	    System.out.println("******Containers disponibles: *******");
	    for(int i=0; i<listaContainers.size(); i++){
	      System.out.println("["+ i + "] " + ((Location)listaContainers.get(i)).getName());
	    }
	  }
	
	protected void beforeMove(){
	    System.out.println("El agente " + (getAID()).getName() + " se mueve al nuevo container");
	  }
	
	class MoverAgenteBehaviour extends SimpleBehaviour {
		private static final long serialVersionUID = 1L;
		private boolean parar= false;
			 
		public MoverAgenteBehaviour(Agent a) {
			super(a);
		}
			 
		public void action() {
			//verContainers();
			verContainers();
			proximo_agente++;
			try{
				destino=(Location)listaContainers.get(proximo_agente);
				// Metodo al que llama el agente para desplazarse a su nuevo destino.
				System.out.println("Moviendo el agente a "+destino.getName()); 
				doMove(destino);
			}catch(Exception ex){
				System.out.println("Problema al intentar mover el agente");
			}

			parar = true;
		}
		 
		public boolean done() {
			return parar;
		}
	}
	

}
