import java.lang.management.ManagementFactory;
import java.util.Date;
import jade.core.*;


public class AgenteEjer1 extends Agent {

	private static final long serialVersionUID = 1L;
	private int CANT_CONTAINERS = 3;
	private int proximo_agente = 1;
	private Date fechaInicio;
	private Date fechaTermino;
	
	
	public void setup() { 
		// Tomo la fecha de inicio
		fechaInicio = new Date ();
		
		// muevo al Main-Container para arrancar
		try {
			ContainerID destino = new ContainerID("Main-Container", null); 
			 
			doMove(destino);
		} 
		catch (Exception e) {
			System.out.println("Error al mover el agente al inicio");
			e.printStackTrace();
		}
	}
	
	@Override
	protected void afterMove() {
		// Llego
		System.out.println("El agente " + (getAID()).getName() + " ha llegado a "+here().getName()+"\n");
		
		// Muestro la info del sistema
		mostrarInfoSistema();
		System.out.println("\n");
		
		long espera = 2000;
		if (proximo_agente == 1) {
			System.out.println("Esperando para volver a recorrer... \n");
			espera = 5000;
		}
		
		try {
			Thread.currentThread().sleep(espera);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("moviendo al proximo... \n");
		
		String proximo = nextAgente();
		
		try {
			//ContainerID destino = new ContainerID("Main-Container", null); 
			ContainerID destino = new ContainerID(proximo, null); 
			//Location destino = getNextContainer();
			doMove(destino);
		} 
		catch (Exception e) {
			System.out.println("Error al mover el agente al inicio");
			e.printStackTrace();
		}
		
	}
	
	private String nextAgente() {
		String proximo;
		
		if (proximo_agente == CANT_CONTAINERS) {
			proximo = "Main-Container";	
			proximo_agente = 1;
		}
		else {
			proximo = "Container-"+String.valueOf(proximo_agente);			
			proximo_agente++;
		}
		
		return proximo;
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
