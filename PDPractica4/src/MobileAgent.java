import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import jade.core.Agent;
import jade.core.ContainerID;
import jade.core.Location;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.ContainerController;

public class MobileAgent extends Agent {
	private ContainerID destino;
	private ContainerID destino2;
	private int resultado = 0;
	
	
	protected void setup(){
		Location origen = here();
		System.out.println("iniciando agente:" + getName()+"\n");
		System.out.println("ubicado en: " + origen.getID() + "\n"); 
		// Get a hold on JADE runtime 
		Runtime rt = Runtime.instance(); 
		// Create a default profile 
		Profile p = new ProfileImpl(); 
		// Create a new non-main container, connecting to the default 
		// main container (i.e. on this host, port 1099) 
		ContainerController cc;		
		cc = rt.createAgentContainer(p);
		
		System.out.println("registrando ubicaciones \n"); 
		destino = new ContainerID("Main-Container", null);
		destino2 = new ContainerID("Container-1", null); 
		comportamiento();			
	}
	
	private void sumar(){
			File entrada = null;
	        FileReader lectura = null;
	        BufferedReader br= null;
	        try{
	            entrada = new File("numeros.txt");
	            lectura = new FileReader(entrada);
	            br = new BufferedReader(lectura);
	            String linea, cad="";
	            int sum=0;
	            while((linea=br.readLine()) != null){
	                for(int x=0; x<linea.length(); x++){
	                    if(String.valueOf(linea.charAt(x)).equals(" ")){
	                        sum+=Integer.parseInt(cad);
	                        cad="0";
	                    }else{
	                        cad+=linea.charAt(x);
	                    }
	                }
	                sum+=Integer.parseInt(cad);
	                cad="0";
	            }           
	            System.out.println("La suma de todos los numeros es : "+sum);
	            resultado = sum;
	        }catch(Exception c){
	            System.out.println("No existe el archivo");
	        }finally{
	            try{
	                if(null != lectura){
	                    lectura.close();
	                }
	            }catch(Exception b){
	                System.out.println("Error al cerrar el archivo");
	            }
	        }
	    }
	
	private void comportamiento(){
		try {			
			//se va a Container-1
			System.out.println("se mueve a Container-1 \n"); 
			doMove(destino2);			
		} 
		catch (Exception e) {
			System.out.println("\n\n\n No fue posible migrar el agente \n\n\n");
			}				
		//suma el archivo de números
		sumar();
		try {			
			//vuelve al main-container
			System.out.println("vuelve a Main-Container \n"); 
			doMove(destino);			
		} 
		catch (Exception e) {
			System.out.println("\n\n\n No fue posible migrar el agente \n\n\n");
			}
		System.out.println("termina su ejecución"); 
		doDelete();
	}	
}

